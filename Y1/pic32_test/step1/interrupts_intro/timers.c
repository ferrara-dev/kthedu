#include <stdint.h>
#include <pic32mx.h>
#include "mipslab.h"

#define TMR2PERIOD ((80000000 / 256) / 10)
/**
 TODO:
 [x] implement multivector mode
 [ ] implement input change interrupt
 **/


/**
 * Enables multi vector mode which allows
 * multiple distinct interrupt handlers.
 */
void enableMultiVectorMode(void) {
    INTCONSET = 0x1000;
}

void init_tmr2(void) {
    PR2 = TMR2PERIOD;
    T2CONSET = 0x70;       /// setting the pre-scale
    TMR2 = 0;             /// reset timer to 0
    T2CONSET = 0x8000;   /// turn timer on, set bit 15 to 1
}

void init_tmr3(void) {
    return;
}

void enable_tmr2_interrupt(void) {
    IPC(2) = IPC(2) | 0x10;   /// set interrupt priority IPC(2) = 7;
    IEC(0) = 0x100;          /// set bit no 8 to enable interrupt

    return;
}

void enable_CN_btn2(void){
    CNCON = 0x8000;
    CNEN = 0x00004000;
    //CNPUE = 0x00004000;
    PORTD;
    IPCSET(6) = 0x00170000;
    IFSCLR(1) = 0x001;
    IFSSET(1) = 0x001;
}