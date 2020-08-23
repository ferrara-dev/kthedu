#include <pic32mx.h>



void initGPIO(void) {
    /** Configure button inputs **/
    TRISFSET = 0x2;           /* set btn 1 as input */
    delay(10000);
    TRISDSET = 0xe0;          /* set btn 2, 3, 4 as input */
    delay(10000);
    /** Configure slide switches as input **/
    TRISDSET = 0xf00;
    delay(10000);
    /** Configure LED's as output **/
    TRISE &= 0xff00;  // set all LED pins as output -->  Set bits 0 through 7 to 0 (output)
    delay(10000);
}

/** returns current value from switches */
int getSwitches(void) {
    return (PORTD & (0xf << 8)) >> 8;
}

/** returns current value from chosen switch */
int getSwitch(int index) {
    switch (index) {
        case 1:
            return  (PORTD & (0x1 << 8)) >> 8;
        case 2:
            return (PORTD & (0x1 << 9)) >> 9;
        case 3:
            return (PORTD & (0x1 << 10)) >> 10;
        case 4:
            return (PORTD & (0x1 << 11)) >> 11;
        default:
            return;
    }
    return 0;
}

/** returns value of specified button input value */
int getBtn(int index) {
    switch (index) {
        case 1:
            return (PORTF>>1) & 0x1;
        case 2:
            return (PORTD>>5) & 0x1;
        case 3:
            return (PORTD>>6) & 0x1;
        case 4:
            return (PORTD>>7) & 0x1;
        default:
            return;
    }
    return 0;
}

/** Returns raw button values
 *  return [btn4,btr3,btn2,btn1]
 */
int getButtonInput(void) {
    return ( ((PORTF>>1) & 0x1) | ((PORTD & (0x7 << 5)) >> 4));
}

int isButton1Pressed(void) {
    return ((PORTF >> 1) & 1);
}

void tick(void)
{
    static int invoc = 0;

    input_poll();

    /* Only run this routine every other invocation */
    if(++invoc & 1)
    {
        input_update();
    }

    /* Reset interrupt flag */
    IFS(0) &= ~(1 << 8);
}

/** Returns board input as a byte with the
 * input represented bitwise as
 * SW4 SW3 SW2 SW1 | BTN4 BTN3 BTN2 BTN1
 */
