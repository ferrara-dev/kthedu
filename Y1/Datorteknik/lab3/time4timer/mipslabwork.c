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
#define TMR2PERIOD ((80000000 / 256) / 10)
int timeoutcount = 0;
int mytime = 0x5957;

char textstring[] = "text, more text, and even more text!";

/* Interrupt Service Routine */
void user_isr(void) {
    return;
}

/* Lab-specific initialization goes here */
void labinit(void) {
                            //// Assignment 1 C.) ////
    /** Define pointer to trise. initialized as volatile to prevent unwanted compiler optimisation **/
    volatile int * trisE = (volatile int *) 0xbf886100;
    /** Set LEDs as output (TRISE BIT 0-7) without changing any of the other bits **/
    * trisE = * trisE & 0xFF00;

                    //// Assignment 1 E.) ////
    /** initializing port D as input using pic32mc system **/
    TRISDSET = 0xFE0;


    /** initializing timer2 using pic32mc system **/
    PR2 = TMR2PERIOD; /// set timer period to 31250 HZ (
    T2CONSET = 0x70; /// setting the prescale to 0111 0000
    TMR2 = 0; /// reset timer to 0
    T2CONSET = 0x8000; /// turn timer on, set bit 15 to 1

    return;

}
//// Assignment 1 D.) ////
/** Define volatile pointer for LED outputs**/
volatile int * portE = (volatile int *) 0xbf886110;

/* This function is called repetitively from the main program */
void labwork(void) {

    //for button 2
   // if(getbtns() == 1 || getbtns() == 3 || getbtns() == 5 || getbtns() == 7)
   if(getBtn(2)){
        mytime = mytime & 0xFF0F; /// mask value that is to be changed to 0
        mytime = ( getsw() << 4) | mytime; /// copy switch input value to the right nibble
    }
    // for button 3
    //if(getbtns() == 2 || getbtns() == 3 || getbtns() == 6 || getbtns() == 7){
    if(getBtn(3)){
        mytime = mytime & 0xF0FF;
        mytime = (getsw() << 8) | mytime;
    }
    // for button 4
    //if(getbtns() == 4 || getbtns() == 5 || getbtns() == 6 || getbtns() == 7){
    if(getBtn(4)){
        mytime = mytime & 0x0FFF;
        mytime = (getsw() << 12) | mytime;
    }

    if(IFS(0) & 0x100){
        // incrementing counter
        timeoutcount++;
        // reseting flag
        IFSCLR(0) = 0x100;
    }

    // only execute on every tenth time-out cycle
    if(timeoutcount == 10){

        time2string( textstring, mytime );
        display_string( 3, textstring );
        display_update();
        tick( &mytime );
        display_image(96, icon);
        *portE = *portE + 1; // counter --> LEDs are incremented
        timeoutcount = 0;

    }

}
