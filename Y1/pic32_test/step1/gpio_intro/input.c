
#include <pic32mx.h>
#include <stddef.h>   /* Declarations of integer sizes and the like, part 1 */
#include <stdint.h>   /* Declarations of integer sizes and the like, part 2 */

/* As input_poll() will be called continously, data will need to be
 * collected continously and then reset when acted upon
 */
static volatile int8_t data = 0;

enum INPUT {
    BTN1 = 0x01,
    BTN2 = 0x02,
    BTN3 = 0x04,
    BTN4 = 0x08,
    SW1 = 0x10,
    SW2 = 0x20,
    SW3 = 0x40,
    SW4 = 0x80
};

uint8_t input_poll(void) {
    data |= getSwitches() << 4;
    data |= getButtonInput();
    return data;
}

void input_update(void) {
    /* State needs to be saved in case the player pauses whilst under */
    /* some kind of boost */
    static int prev_state;

    /* As data can be volatile, save it in a local variable for reading */
    int8_t const input = data;

    /* Set inversion flag */
    // invert = val & SW3;
    if(data & SW2) {
            sweepLEDS();
    }

    delay(10000);
    while (data & SW1) {
        testTimers();
    }
}


