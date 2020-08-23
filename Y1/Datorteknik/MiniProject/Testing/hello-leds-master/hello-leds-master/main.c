#include <pic32mx.h>
#include <stddef.h>   /* Declarations of integer sizes and the like, part 1 */
#include <stdint.h>   /* Declarations of integer sizes and the like, part 2 */

void saveinfo(char s[], void *a, size_t ns);
void showinfo(void);
//void u32init(void);

void pause() {

    while (1) {
        quicksleep(5000);
        if (checkButton(2)) {
            break;
        }
    }

}

/**
 * Setup port D as button input
 */
void enableButtons() {
    TRISD |= (0x3f << 5);
    TRISFSET = 0x1; /* Set bit index 1 to 1 (input) */
}

/**
 * Returns raw button values at port D
 */
int getButtonInput(void) {
    // int buttonInput =  ( PORTD >> 4) | (PORTF << 1 );
    //return buttonInput;
    return (PORTD & (0x7 << 5)) >> 5;
}

/**
 * Returns if button was pressed and allows
 * pressing of any length.
 */

int buttons[] = {0, 0, 0, 0, 0};

int isButtonPressed(int i) {
    int btnVal = getButtonInput();
    int shift = (i > 0 && i < 5) ? (-2) + i : 0;
    int pin = 0x1 << shift;
    i--;

    if (buttons[i] == 0 && btnVal & pin) {
        buttons[i] = 1;
        return 1;
    } else if (buttons[i] == 1 && !(btnVal & pin)) {
        buttons[i] = 0;
    }

    return 0;
}


/**
 * Enables multi vector mode which allows
 * multiple distinct interrupt handlers.
 */
void enableMultiVectorMode() {
    INTCONSET = 0x1000;
}

/**
 *  Set up and start timer 2
 */
void enableTimer2(int period, int priority, int prescaling, int interrupts) {
    T2CON = 0x0;            // stop timer
    TMR2 = 0;               // clear timer
    PR2 = period;           // set period
    IPCSET(2) = priority;   // set priority

    IFSCLR(0) = 0x100;      // reset timer interrupt status flag

    // enable timer interrupts
    if (interrupts == 1) { IECSET(0) = 0x100; }

    // set prescaling
    T2CONSET = prescaling << 4;
    // enable
    T2CONSET = 0x8000;
}

/**
 *  Set up and start timer 3
 */
void enableTimer3(int period, int priority, int prescaling, int interrupts) {
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
}

int isButton1Pressed(void) {
    return ((PORTF >> 1) & 1);
}

int button[] = {0, 0, 0, 0};

int checkButton(int index) {
    button[0] = ((PORTF >> 1) & 1);
    int k = 1;
    while (k < 4) {
        button[k] = isButtonPressed(k + 1);
        quicksleep(1000);
        k++;
    }
    return button[index];
}

int main(void) {
    unsigned int sweep = 0x1;
    /* Set up output pins */
    AD1PCFG = 0xFFFF;
    ODCE = 0x0;
    TRISECLR = 0xFF;

    /* Set up input pins */
    PORTE = 0x2A;
    enableButtons();
    quicksleep(5000000);
    enableMultiVectorMode();
    //__asm__ ("ei");
    for (;;) {
        PORTE = sweep;
        quicksleep(50000);
        if (checkButton(1)) {
            pause();
            //delay(10000000);
            continue;
        }

        if(checkButton(3)){
            pause();
            //delay(10000000);
            continue;
        }
        sweep = (sweep >> 1);
        if (!(sweep))
            sweep = 0x80;
        quicksleep((checkButton(0)) ? 150000 : 1000000);
    }
    return 0;
}


/**
 * ISR Interrupt handler for timer 2
 */
void timer2_interrupt_handler(void) {
    IFSCLR(0) = 0x100;
}

/**
 * ISR Interrupt handler for timer 3
 */
void timer3_interrupt_handler(void) {}

/**
 * ISR general interrupt handler
 */
void core_interrupt_handler(void) {}


