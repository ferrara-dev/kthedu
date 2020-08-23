//
// Created by root on 2020-02-18.
//

#include <stdlib.h>
#include <pic32mx.h>
#include "../includes/timers.h"

/**
 * Enables multi vector mode which allows
 * multiple distinct interrupt handlers.
 */

void enableMultiVectorMode(void) {
    INTCONSET = 0x1000;
}

void enable_tmr2(void) {
    /* Setup timer used to update display */
    T2CONCLR = 1 << 15;	  // Disable timer
    T2CONSET = 7 << 4;	   // Prescaler = 256
    PR2 = 80000000 / (256 * 10); // Tick every 0.1 seconds
    TMR2 = 0;		    // Reset timer value
    T2CONSET = 1 << 15;	  // Enable timer
}

void set_tmr2_P(int p){
    PR2 = p;
}

void reset_tmr2_P(void){
    PR2 = 80000000 / (256 * 10);

}

void enable_tmr2_interrupt(void) {
    IEC(0) |= 1 << 8;	    // Set interrupt 2 to listen to timer 2
    IPC(2) |= 7 << 0;
    //IPCSET(2) = 0xD;// Set interrupt 2 priority to 7 (highest)
}

/**
 *  Combine timer 4 and 5 to form a 32-bit timer
 */
void enable32BitTimer_45(int period, int priority, int prescaling, int interrupts) {
    T4CON = 0x0; // Stop any 16/32-bit Timer4 operation
    T5CON = 0x0; // Stop any 16-bit Timer5 operation
    T4CONSET = 0x0038; // Enable 32-bit mode, prescaler 1:8,
    // internal peripheral clock source
    TMR4 = 0x0; // Clear contents of the TMR4 and TMR5
    PR4 = 80000000 / (8); // Load PR4 and PR5 registers with 32-bit value
    // T4CONSET = 0x8000; // Start Timer4/5

    IPCSET(5) = 0x00000004; // Set priority level = 1
    IPCSET(5) = 0x00000001; // Set sub-priority level = 1
// Can be done in a single operation by assigning
// IPC5SET = 0x00000005
    IFSCLR(0) = 0x00100000; // Clear the Timer5 interrupt status flag
    IECSET(0) = 0x00100000; // Enable Timer5 interrupts
    T4CONSET = 0x8000; // Start the timer
}

void enable_tmr3(void){
    /*
    T3CON = 0x0;            // stop timer
    TMR3 = 0;               // clear timer
    PR3 = period;           // set period
    IPCSET(3) = priority;   // set priority

    IFSCLR(0) = 0x1000;      // reset timer interrupt status flag

    // enable timer interrupts
    if (interrupts == 1) { IECSET(0) = 0x1000; }

    // set prescaling
    T3CONSET = prescaling << 4;

    // enable
    T3CONSET = 0x8000;
     */
}