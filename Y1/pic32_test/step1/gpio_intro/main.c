#include <pic32mx.h>
#define TMR2PERIOD ((80000000 / 256) / 10)
//extern void *__use_isr_install;
//extern void _make_syscall();
//extern void _enable_int_number(int int_no, volatile void *IPCx, int priority, int sub_priority);
//extern void _enable_interrupt();

void initGPIO();
void tick();


/**
 *  Set up and start timer 2
 */
void enableTimer2(int priority, int prescaling, int interrupts) {
    T2CON = 0x0;            // stop timer
    TMR2 = 0;               // clear timer
    PR2 = 80000000 / (256 * 10); // Tick every 0.1 seconds
    IPCSET(2) = priority;   // set priority
    IFSCLR(0) = 0x100;      // reset timer interrpt status flag
    // enable timer interrupts
    if (interrupts == 1) { IECSET(0) = 0x100; }
    // set prescaling
    T2CONSET = prescaling << 4;
    // enable
    T2CONSET = 0x8000;

}

void delay(int cyc) {
    int i;
    for (i = cyc; i > 0; i--);
}

int main(void) {
    initGPIO();
    /* Set up SPI as master */
    SPI2CON = 0;
    SPI2BRG = 4;
    /* SPI2STAT bit SPIROV = 0; */
    SPI2STATCLR = 0x40;
    /* SPI2CON bit CKP = 1; */
    SPI2CONSET = 0x40;
    /* SPI2CON bit MSTEN = 1; */
    SPI2CONSET = 0x20;
    /* SPI2CON bit ON = 1; */
    SPI2CONSET = 0x8000;
    initTMR2();
    //// test polling for input combined with timers and interrupts ////
    while (1) {
        tick();
        delay(10000);
    }
    //// test switches and push buttons
    /**
    while (1) {

                testPshBtns();

      if(getSwitches() & 0x1)
          testPshBtns();
    if(getSwitches() & 0x2)
        sweepLEDS();

        if( (getSwitch(4)) && (getSwitch(3))) {

            int i;

            delay(10000000);
            delay(100000);
    }
    */
    return 0;
}


/**
 * ISR Interrupt handler for timer 2
 */
void timer2_interrupt_handler(void) {
    PORTE = !(PORTE) & 0x1;
    IFSCLR(0) = 0x100;
}
/*
PORTE = (PORTF>>1) & 0x1; // if button 1 is pushed --> light up LED 1
delay(1000);
PORTE = (PORTD>>4) & 0x2; // if button 2 is pushed --> light up LED 2
delay(1000);
PORTE = (PORTD>>4) & 0x4; // if button 3 is pushed --> light up LED 3
delay(1000);
PORTE = (PORTD>>4) & 0x8; // if button 4 is pushed --> light up LED 4
delay(1000);
*/