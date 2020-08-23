#include <stdint.h>
#include <pic32mx.h>
#include "mipslab.h"

#define TMR2PERIOD ((80000000 / 256) / 10)

void init_input(void){
    /** Set LEDs as output (TRISE BIT 0-7) without changing any of the other bits **/
    TRISECLR = 0xFF;
    //*E = *E & 0xFF00;
    /** initializing port D as input using pic32mc system **/
    TRISDSET = 0xFE0;
    /** initializing port F - bit2 as input using pic32mc system **/
    TRISFSET = 0x2;
}

/** least significant bits of the return value contain data from switches SW4, SW3, SW2, and SW1. **/
/* [0000 0000 1111 << 0000 0000 1000] = [1111 0000 0000]
 * [1111 0000 0000 & PORTD] = [xxxx 0000 0000]
 * [xxxx 0000 0000 >> 8] = [0000 0000 xxxx]
 */
int getsw(void){
    return (PORTD & (0xf << 8)) >> 8;
}

/** 3 least significant bits of the return value contain data from buttons btn4, btn3, btn2 **/
int getbtns(void){
    return  ((PORTD & (0x7 << 5)) >> 5);
}

int getbtns_all(void){
    return ( ((PORTF>>1) & 0x1) | ((PORTD & (0x7 << 5)) >> 4));
}
