//
// Created by root on 2020-02-18.
//

#ifndef PROJECT_EDITION3_TIMERS_H
#define PROJECT_EDITION3_TIMERS_H

#define TMR2_PLAY 80000000 / (256 * 100)
#define TMR2_MENU 80000000 / (256 * 10)


/**
 * Enables multi vector mode which allows
 * multiple distinct interrupt handlers.
 */
void enableMultiVectorMode(void);

void enable_tmr2(void);

void set_tmr2_P(int p);

void reset_tmr2_P(void);

void enable_tmr2_interrupt(void);

/**
 *  Combine timer 4 and 5 to form a 32-bit timer
 */
void enable32BitTimer_45(int period, int priority, int prescaling, int interrupts);

void enable_tmr3(void);


#endif //PROJECT_EDITION3_TIMERS_H
