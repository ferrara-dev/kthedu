/* mipslabwork.c

   This file written 2015 by F Lundevall
   Updated 2017-04-21 by F Lundevall

   This file should be changed by YOU! So you must
   add comment(s) here with your name(s) and date(s):

   This file modified 2017-04-31 by Ture Teknolog

   For copyright and licensing, see file COPYING

   TODO:
   c) Add code in function labinit to initialize Port E so that bits 7 through 0
    of Port E are set as outputs (i.e., the 8 least significant bits). These bits are connected to 8 green
    LEDs on the Basic IO Shield. Register TRISE has address 0xbf886100. You should initialize the
    port using your own volatile pointer, that is, you should not use the definitions in pic32mx.h, yet.
    Do not change the function (direction) of any other bits of Port E.

    d) In file mipslabwork.c, add code in function labwork to increase the binary value shown on the
    8 green LEDs once each time the function tick is called. Initialize the value to 0, so that the LEDs
    show how many "ticks" have occurred since the program was started. See below. You should use
    your own volatile pointer. Register PORTE has address 0xbf886110.


    e) In file mipslabwork.c, add code in function labinit to initialize port D so that bits 11 through 5
    of Port D are set as inputs. You should do this by using the definitions in pic32mx.h Do not change
    the function (direction) of any other bits of Port D.


    f) Create a new file time4io.c. Begin the file with the following three lines:
    #include <stdint.h>
    #include <pic32mx.h>
    #include "mipslab.h"
    In this file, write a C function that returns the status of the toggle-switches on the board, with the
    following specification.
    Function prototype: int getsw( void );
    Parameter: none.
    Return value: The four least significant bits of the return value should contain data from switches
    SW4, SW3, SW2, and SW1. SW1 corresponds to the least significant bit. All other bits of the return
    value must be zero.
    Notes: The function getsw will never be called before Port D has been correctly initialized. The
    switches SW4 through SW1 are connected to bits 11 through 8 of Port D.

   */

#include <stdint.h>   /* Declarations of uint_32 and the like */
#include <pic32mx.h>  /* Declarations of system-specific addresses etc */
#include "mipslab.h"  /* Declatations for these labs */

int timeoutcount = 0;
int mytime = 0x5957;
int prime = 1234567;
char textstring[] = "text, more text, and even more text!";

/** init counter used to increment LEDS**/
int count = 0;
volatile int *portE = (volatile int *) 0xbf886110;
volatile int *E = (volatile int *) 0xbf886100;

int readD;
void CN_ISR(void){
    readD = PORTD;
    quicksleep(10000);
    IFSCLR(1) = 0x001;
    display_string(0, itoaconv(getbtns_all())); /// Uppdate and Display pushbtn input once every 0,1 second
    display_update();
}
/* Interrupt Service Routine */
void user_isr(void) {
    // check flag
    if (IFS(0) & 0x100) {
        // clearing flag
        IFS(0) = 0;
        timeoutcount++;
     //   display_string(0, itoaconv(getbtns_all())); /// Uppdate and Display pushbtn input once every 0,1 second
      //  display_update();
        if (timeoutcount == 10) {
          //  *portE = *portE + 0x1;
            display_string(3, itoaconv(getsw()));   /// Uppdate and Display switch input once every second
            display_update();
            tick(&mytime);
            timeoutcount = 0;
        }
    }

    /// code counting elapsed time with LEDS
    if (IFS(0) & 0x80) {
        // clearing flag
        IFS(0) = 0;
        // for LEDs ticking
        *portE = *portE + 0x1;
        // only for the last 8 bits
        *E = *E & 0xFF00;
    }

    return;
}

/* Lab-specific initialization goes here */
void labinit(void) {
    /** initializing pushbtns, switches and LEDs**/
    init_input(); /// calling function init_input() in input.c

    /** initializing timer2 **/
    init_tmr2(); /// calling function init_tmr2() in timers.c

    /** Enabling interrupts from Timer 2 **/
    enable_tmr2_interrupt(); /// calling function enable_tmr2_interrupt() in timers.c
    enable_CN_btn2();
    enableMultiVectorMode();
    enable_interrupt();     /// calling ASM function enable_interrupt from labwork.S where interrupts are enabled globally
    return;
}

void labwork(void) {
    //for button 2
    if (getbtns() == 1 || getbtns() == 3 || getbtns() == 5 || getbtns() == 7) {
        mytime = mytime & 0xFF0F;
        mytime = (getsw() << 4) | mytime;
    }
    // for button 3
    if (getbtns() == 2 || getbtns() == 3 || getbtns() == 6 || getbtns() == 7) {
        mytime = mytime & 0xF0FF;
        mytime = (getsw() << 8) | mytime;
    }
    // for button 4
    if (getbtns() == 4 || getbtns() == 5 || getbtns() == 6 || getbtns() == 7) {
        mytime = mytime & 0x0FFF;
        mytime = (getsw() << 12) | mytime;
    }

}