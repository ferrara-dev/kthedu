#include <stdlib.h>
#include <pic32mx.h>
#include "includes/peripherals.h"
#include "../game/includes/game.h"
#include "../game/includes/level.h"
#include "../includes/entity.h"
#include "../graphics/includes/interface.h"


/**
 * Enables multi vector mode which allows
 * multiple distinct interrupt handlers.
 */
void enableMultiVectorMode(void) {
    INTCONSET = 0x1000;
}

void enable_tmr2(void) {
    /* Setup timer used to update display every 0.1 seconds */
    T2CONCLR = 1 << 15;	  // Disable timer
    T2CONSET = 7 << 4;	   // Prescaler = 256
    PR2 = 80000000 / (256 * 10); // Tick every 0.1 seconds
    TMR2 = 0;		    // Reset timer value
    T2CONSET = 1 << 15;	  // Enable timer
}

void enable_tmr2_interrupt(void) {
    IEC(0) |= 1 << 8;	    // Set interrupt 2 to listen to timer 2
    IPC(2) |= 7 << 0;	    // Set interrupt 2 priority to 7 (highest)
    __asm__ ("ei");	      // Enable interrupts
}

void enable_tmr3(void){
    T3CON = 0;
    PR3 = 31250;
    IPC(3) = IPC(3) | 0x10;    // IEC Bit 16
    IECSET(0) = 0x1000; //
    T3CONSET = 0x70;
    TMR3 = 0;
    T3CONSET = 0x8000;
}