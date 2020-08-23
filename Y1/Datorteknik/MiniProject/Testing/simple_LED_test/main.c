
#include <stddef.h>   /* Declarations of integer sizes and the like, part 1 */
#include <stdint.h>   /* Declarations of integer sizes and the like, part 2 */
#include <pic32mx.h>  /* Declarations of system-specific addresses etc */

int main() {
    /* Port E is used for the LED */
    TRISE = TRISE & 0xff00;  // set all LED pins as output -->  Set bits 0 through 7 to 0 (output)
    PORTE = 0x00;           // set the value of the out put to 1

    /*
     * bit 1 of TRISF sets pushbutton 1 as input/output
     * bits 5 - 7 of TRISD sets pushbuttons 2-4 as input/output
     */
    TRISF |= 0x1; /* Set bit index 1 to 1 (input) */
    TRISD |= 0xd0;
    volatile int output =
    while(1) {
        /*
        PORTE = (PORTF>>1) & 0x1; // if button 1 is pushed --> light up LED 1
        quicksleep(1000);
        PORTE = (PORTD>>4) & 0x2; // if button 2 is pushed --> light up LED 2
        quicksleep(1000);
        PORTE = (PORTD>>4) & 0x4; // if button 3 is pushed --> light up LED 3
        quicksleep(1000);
        PORTE = (PORTD>>4) & 0x8; // if button 4 is pushed --> light up LED 4
        quicksleep(1000);
       */
    }

}

