#include <pic32mx.h>
#include <stddef.h>   /* Declarations of integer sizes and the like, part 1 */
#include <stdint.h>   /* Declarations of integer sizes and the like, part 2 */

#define TMR2PERIOD ((80000000 / 256) / 10)

int timeoutcount = 0;

///* Interrupt Service Routine *///
void user_isr(void) {
    return;
}

///* initialization of TMR2 *///
void initTMR2(void) {
    // defining the int pointer, trise, volatile because you
    //don't want the c compiler to optimise
    volatile int *E = (volatile int *) 0xbf886100;
    //
    volatile int *portE = (volatile int *) 0xbf886110;
    *portE = 0x0; // for LED outputs
    // for LEDs 1 ticking
    *portE = (*portE + 0x1);
    // only for the last 8 bits
    *E = *E & 0xFF00;

    PR2 = TMR2PERIOD;
    T2CONSET = 0x70; // setting the prescale
    TMR2 = 0; // reset timer to 0
    T2CONSET = 0x8000; // turn timer on, set bit 15 to 1

    return;
}

void testTimers(void) {
    while ((timeoutcount < 100)) {
        PORTE = timeoutcount & 0xFF;
        if (IFS(0) & 0x100) {
            // incrementing counter
            timeoutcount++;
            // reseting flag
            IFSCLR(0) = 0x100;
        }
    }

    // only execute on every tenth time-out cycle
    if (timeoutcount >= 100) {
        PORTE = PORTE & 0xff;
        timeoutcount++;
        if (timeoutcount == 300) {
            PORTE = 0;
            timeoutcount = 0;
            IFSCLR(0) = 0x100;
            return;
        }

    }
}