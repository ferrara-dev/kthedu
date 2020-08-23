/**
// Created by Samuel Ferrara on 2020-02-17.
**/
#include <pic32mx.h>

/** returns value of specified button input value */
int getBtn(int index) {
    switch (index) {
        case 1:
            return (PORTF >> 1) & 0x1;
        case 2:
            return (PORTD >> 5) & 0x1;
        case 3:
            return (PORTD >> 6) & 0x1;
        case 4:
            return (PORTD >> 7) & 0x1;
        default:
            return 0;
    }
    return 0;
}

/** returns current value from chosen switch */
int getSwitch(int index) {
    switch (index) {
        case 1:
            return (PORTD & (0x1 << 8)) >> 8;
        case 2:
            return (PORTD & (0x1 << 9)) >> 9;
        case 3:
            return (PORTD & (0x1 << 10)) >> 10;
        case 4:
            return (PORTD & (0x1 << 11)) >> 11;
        default:
            return 0;
    }
    return 0;
}

void set_sw1_interrupt(void) {
    /** enabling interupts from sw1 **/
    IPC(1) |= 0x1c000000;
    IEC(0) = IEC(0) | (1 << 7);

}

void set_sw2_interrupt(void) {
    /** enabling interupts from sw2  **/
    IECSET(0) = 0x800;    //enable interrupt for int2, bit 11
    IPCSET(2) = 0x1C000000; //set priority of switch 2 to 111
}

void set_sw3_interrupt(void) {
    /** enabling interupts from sw3  **/
    IPC(3) |= 0x1c000000;
    IEC(0) = IEC(0) | (1 << 15);
}

void set_sw4_interrupt(void) {
    /** enabling interupts from sw3  **/
    IPC(4) |= 0x1c000000;
    IEC(0) |= (1 << 19);

}

void set_sw_interrupts(int index) {
    switch (index) {
        case 1:
            /** enabling interupts from sw1 **/
            set_sw1_interrupt();
            break;
        case 2:
            /** enabling interupts from sw2  **/
            set_sw2_interrupt();
            break;
        case 3:
            set_sw3_interrupt();
            break;
        case 4:
            set_sw4_interrupt();
            break;
        default:
            return;
    }
}

int getsw(void) {
    return (PORTD & (0xf << 8)) >> 8;
}

int getbtns_all(void) {
    return (((PORTF >> 1) & 0x1) | ((PORTD & (0x7 << 5)) >> 4));
}


/** Initialize analogue pins and turn on ADC **/
void init_adc() {
    AD1PCFG = 0xFBFF;
    AD1CON1 = 4 << 8 | 7 << 5;
    AD1CHS = 1 << 17;
    TRISBSET = 1 << 11;
    AD1CON2 = 0;
    AD1CON3SET = 1 << 15;

    /* Set up output pins */

    /* Turn on ADC */
    AD1CON1SET = 1 << 15;
}
/** Configuration of push buttons and switches as input **/
void init_input(void) {
    /* PORTD takes SW4-SW1 in bits <11:8> and BTN4-BTN2 in bits <7:5> */
    TRISDSET = 0x7f << 5;
    /* PORTF takes BTN1 in bit <1:1> */
    TRISFSET = 0x01 << 1;
}
