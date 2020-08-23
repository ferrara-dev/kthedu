#include <pic32mx.h>
#include "../includes/input.h"
#include "../includes/helpers.h"
#include "../includes/display.h"
#include "../includes/timers.h"
#include "../includes/memory.h"
#include "../includes/interface.h"

#define MENU_DELAY 1000000

#define HS_MAX 999
#define HS_LEN 3

#define FI_ADDR 0x0001000
#define SE_ADDR 0x0002000
#define TH_ADDR 0x0003000

void tick(void);

void tmr3_ISR(void);

/** First, second and third place in high scores **/
int first_place, second_place, third_place;

void init_game(void) {

}

void init(void) {
    init_input();
    init_adc();
    srand(seed());

    i2c_init();

    if (getSwitch(1)) {
        write_int(FI_ADDR, 0);
        write_int(SE_ADDR, 0);
        write_int(TH_ADDR, 0);
    }

    first_place = read_int(FI_ADDR);
    second_place = read_int(SE_ADDR);
    third_place = read_int(TH_ADDR);


    init_display();

    enable_tmr2();
    enable_tmr2_interrupt();
    enable32BitTimer_45(31250, 0x1B, 0x111, 1);

    enableMultiVectorMode();


    __asm__ ("ei");          // Enable interrupts

    init_game();
}

int main() {
    init();
    /* Load the main menu from interface.c */
    //interface_menu_load_main();
    state = STATE_MENU_MAIN;
    while (1) {
        /* Let interrupts do the work */

        __asm__ volatile("nop");
    }
    return 0;
}

void tick(void) {
    static int invoc = 0;
    int previousState = state;
    /* Only run this routine every other invocation */
    if(++invoc & 0x1) {
       // input_poll();
       // input_update();

        if (state & STATE_MENU) {
            if (state == STATE_MENU_MAIN) {
                PORTE = PORTE & 0x01;
            }
            draw_menu();
        }



        if (state == STATE_PLAYING_SURVIVAL_MODE) {
          //  survival_mode_tick();
        }
        graphics_postprocess();
        graphics_reload();
        graphics_clear();
        //display_update();

    }
    /* Reset interrupt flag */
    IFS(0) &= ~(1 << 8);
}

void tmr3_ISR(void) {
    if (IFS(0) & 0x00100000) {
        /* Reset interrupt flag */
        IFSCLR(0) = 0x00100000;
    }
}