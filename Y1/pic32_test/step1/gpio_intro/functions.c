#include <stdlib.h>
#include <pic32mx.h>


void sweepLEDS(void) {
    unsigned int sweep = 0x1;
    delay(5000000);
    while (getSwitch(2)) {
        PORTE = sweep;
        delay(50000);
        sweep = (sweep >> 1);
        if (!(sweep))
            sweep = 0x80;
        delay(getBtn(1) ? 150000 : 1000000);
    }
    return;
}

void testPshBtns(void) {
    while (getSwitches() & 0x1) {
        /** Test push buttons */
        PORTE = getBtn(1); // if button 1 is pushed --> light up LED 1
        delay(10000);
        PORTE = getBtn(2) << 1; // if button 2 is pushed --> light up LED 2
        delay(10000);
        PORTE = getBtn(3) << 2;// if button 3 is pushed --> light up LED 3
        delay(10000);
        PORTE = getBtn(4) << 3; // if button 4 is pushed --> light up LED 4
        delay(10000);
    }
}