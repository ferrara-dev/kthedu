# Lab 3
### Assignments
##### Assignment 1: Polling switches

   -  C.) 
        Add code in function labinit to initialize Port E so that bits 7 through 0
        of Port E are set as outputs (i.e., the 8 least significant bits). These bits are connected to 8 green
        LEDs on the Basic IO Shield. Register TRISE has address 0xbf886100. You should initialize the
        port using your own volatile pointer, that is, you should not use the definitions in pic32mx.h, yet.
        Do not change the function (direction) of any other bits of Port E.
   
   -   D.) John Adams In file mipslabwork.c, add code in function labwork to increase the binary value shown on the
                          8 green LEDs once each time the function tick is called. Initialize the value to 0, so that the LEDs
                          show how many "ticks" have occurred since the program was started. See below. You should use
                          your own volatile pointer. Register PORTE has address 0xbf886110.
   -   E.) In file mipslabwork.c, add code in function labinit to initialize port D so that bits 11 through 5
          of Port D are set as inputs. You should do this by using the definitions in pic32mx.h Do not change
          the function (direction) of any other bits of Port D.
          
   -   F.)  f) Create a new file time4io.c. Begin the file with the following three lines:
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
               
   - G.)        In file time4io.c, add a C function that returns the current status of the push-buttons BTN2,
                BTN3, and BTN4 with the following specification1.
                Function prototype: int getbtns(void);
                Parameter: none.
                Return value: The 3 least significant bits of the return value must contain current data from push
                buttons BTN4, BTN3, and BTN2. BTN2 corresponds to the least significant bit. All other bits of
                the return value must be zero.
                Notes: The function getbtns will never be called before Port D has been correctly initialized. The
                buttons BTN4, BTN3, and BTN2, are connected to bits 7, 6 and 5 of Port D.
   - H.) In file mipslabwork.c, modify the function labwork to also call getbtns. If any button is
                pressed, the function getsw must be called, and the value of the variable mytime updated as follows
                (see  page).
                • Pressing BTN4 copies the value from SW4 through SW1 into the first digit of mytime. If
                SW4 through SW1 are set to 0100, time would change from, say, 16:53 into 46:53.
                • Pressing BTN3 copies the value from SW4 through SW1 into the second digit of mytime. If
                SW4 through SW1 are set to 0100, time would change from, say, 16:53 into 14:53.
                • Pressing BTN2 copies the value from SW4 through SW1 into the third digit of mytime. If
                SW4 through SW1 are set to 0100, time would change from, say, 16:53 into 16:43.
                • Pressing two (or three) buttons simultaneously should update both (or all three) relevant
                digits of mytime.
                The figures on the next page illustrate this with an example.

    