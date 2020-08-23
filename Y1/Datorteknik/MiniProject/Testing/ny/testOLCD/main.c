/* main.c

   This file written 2015 by F Lundevall and David Broman

   Latest update 2015-09-15 by David Broman

   For copyright and licensing, see file COPYING */
//#include "pheriperals.c"
#include <stddef.h>   /* Declarations of integer sizes and the like, part 1 */
#include <stdint.h>   /* Declarations of integer sizes and the like, part 2 */
#include <pic32mx.h>  /* Declarations of system-specific addresses etc */


/**
 * Setup buttons 1-4 as button input
 */
void enableButtons() {
    TRISF |= 0x1; /* Set bit index 1 to 1 (input) */
    TRISD |= 0xd0;
}

/* Port E is used for the LED */
void enableLEDs() {
    TRISE &= 0xff00;  // set all LED pins as output -->  Set bits 0 through 7 to 0 (output)
    PORTE = 0x00;    //  init LED output to 0
}
/**
 * Returns raw button values at port D
 */
int getButtonInput_1(void){
    return (PORTD & (0x7 << 5)) >> 5;
}

/**
 * Returns if button was pressed and allows
 * pressing of any length.
 */
int buttons[] = {0, 0, 0, 0, 0};
int isButtonPressed(int i) {
    int btnVal = getButtonInput_1();
    int shift = (i > 1 && i < 5) ? (-2) + i : 0;
    int pin = 0x1 << shift;
    i--;

    if (buttons[i] == 0 && btnVal & pin) {
        buttons[i] = 1;
        return 1;
    }else if (buttons[i] == 1 && !(btnVal & pin)) {
        buttons[i] = 0;
    }

    return 0;
}

void saveinfo(char s[], void *a, size_t ns);
void u32init(void);
void showinfo(void);
//void u32init(void);

int gv; /* Global variable. */
int in = 3; /* Global variable, initialized to 4711. */


/* This is the main function */
int main() {
    /* init push-buttons */
    quicksleep(5000000);
    enableLEDs();
    quicksleep(5000000);
    if(isButtonPressed(0) && isButtonPressed(1))
        PORTE = 0xff;

    }

