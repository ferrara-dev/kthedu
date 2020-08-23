//
// Created by root on 2020-02-18.
//


/* interface.c */

#include <string.h>
#include <stddef.h>
#include "../includes/input.h"
#include "../includes/interface.h"
#include "../includes/display.h"

#define MAIN_MENUBARS 4     /* 4 pages/lines available, and as such; 4 buttons */
#define ABOUT_MENUBARS 4
#define PLAY_MENUBARS  3
#define GAMEPAUSED_SURVIVALMODE_MENUBARS 3

int lines;
static Menubar bars[MAIN_MENUBARS];
static Menubar bars_about[ABOUT_MENUBARS];
static Menubar bars_play[PLAY_MENUBARS];
static Menubar bars_paused[GAMEPAUSED_SURVIVALMODE_MENUBARS];
static int selected = 0;



void button_cons(Menubar *button, char const *string, void (*function)(void))
{
    if(strlen(string) < MENUBAR_BUFFER_SIZE)
        strcpy(button->string, string);
    else
        strcpy(button->string, "INVALID");
    button->function = function;
}

void button_press(Menubar const *button)
{
    if(button->function != NULL)
        button->function();
}

void button_draw(Menubar const *button, int line)
{
    int const sz     = strlen(button->string);
    int const offset = (HEIGHT / 2) - ((sz / 2) * FONT_WIDTH) - ((sz & 1) ? (FONT_WIDTH / 2) : 0);
    graphics_print(offset, line, button->string);
}

/**************************************
 * Draw all of the menu buttons,
 * and mark the currently selected one
 *************************************/
void draw_menu() {
    /* Draw buttons */
    for (int i = 0; i < MAIN_MENUBARS; ++i) {
        button_draw(&bars[i], i);
    }

    /* Draw outline around selected button */
    int const page = selected * 8;

    for (int y = page; y < page + 8; ++y) {
        for (int x = 0; x < HEIGHT; ++x) {
            if ((x == 0 || x == HEIGHT - 1) || (y == page || y == page + 7))
                set_pixel(x, y, 1);
        }
    }
}


/***************************************
 * Press the currently selected button,
 * and reset the selected index
 **************************************/
void interface_bar_press(void) {
    button_press(&bars[selected]);

    if (bars[selected].function != NULL)
        selected = 0;
}


void interface_bar_prev(void) {
    if (--selected < 0)
        selected = lines - 1;
}


void interface_bar_next(void) {
    if (++selected >= lines)
        selected = 0;
}


void interface_menu_load_main(void) {
    state = STATE_MENU_MAIN;

    button_cons(&bars[0], "MAIN MENU", NULL);
    button_cons(&bars[1], "TEST GPIO", NULL);
    button_cons(&bars[2], "HIGHSCORES", NULL);
    button_cons(&bars[3], "ABOUT", interface_menu_load_about);
}

void interface_menu_load_about(void) {
    state = STATE_MENU_ABOUT;
    button_cons(&bars[0], "BTNX: MOVE           ", NULL);
    button_cons(&bars[1], "SW4 : PAUSE          ", NULL);
    button_cons(&bars[2], "SW3 : INVERT DISPLAY ", NULL);
    button_cons(&bars[3], "BACK", interface_menu_load_main);
}

void interface_menu_load_gpioTest(void) {
    state = STATE_MENU_PLAY;
    button_cons(&bars[0], "STORY MODE", NULL);
    button_cons(&bars[2], "SURVIVAL MODE ", NULL);
    button_cons(&bars[3], "BACK", interface_menu_load_main);
}


void interface_menu_load_paused(void) {
    state = STATE_MENU_PAUSED;
    button_cons(&bars[0], "PAUSED", NULL);
    button_cons(&bars[1], "RETURN TO MAIN MENU", interface_menu_load_main);
    button_cons(&bars[2], "CONTINUE", NULL);

}
