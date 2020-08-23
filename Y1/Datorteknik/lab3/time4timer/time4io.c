#include <stdint.h>
#include <pic32mx.h>
#include "mipslab.h"

//// ASSIGNMENT 1 F.) ////
/** least significant bits of the return value contain data from switches SW4, SW3, SW2, and SW1. **/
/* [0000 0000 1111 << 0000 0000 1000] = [1111 0000 0000]
 * [1111 0000 0000 & PORTD] = [xxxx 0000 0000]
 * [xxxx 0000 0000 >> 8] = [0000 0000 xxxx]
 */
int getsw(void){
    return (PORTD & (0xf << 8)) >> 8;
}
//// ASSIGNMENT 1 G.) ////
/** 3 least significant bits of the return value contain data from buttons btn4, btn3, btn2 **/
int getbtns(void){
    return  ((PORTD & (0x7 << 5)) >> 5);
}

int getbtns_all(void){
    return ( ((PORTF>>1) & 0x1) | ((PORTD & (0x7 << 5)) >> 4));
}

/** return pushbutton value at specified index **/
int getBtn(int index){
    switch(index){
        case 1:
            return getbtns_all() & 0x1;

        case 2:
            return ( getbtns_all() & 0x2 ) >> 1;

        case 3:
            return ( getbtns_all() & 0x4 ) >> 2;

        case 4:
            return ( getbtns_all() & 0x8 ) >> 3;

        default:
            return 0;
    }

    return 0;
}
