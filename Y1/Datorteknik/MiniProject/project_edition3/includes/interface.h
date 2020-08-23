//
// Created by root on 2020-02-18.
//
#include <pic32mx.h>
#include <string.h>
#include "display.h"
#ifndef PROJECT_EDITION3_INTERFACE_H
#define PROJECT_EDITION3_INTERFACE_H

#define FONT_WIDTH  6
#define FONT_HEIGHT 8
#define MENUBAR_BUFFER_SIZE 22 /* Highest amount of characters that can fit on one line */

/* Buttons can also be used as menu titles or information,
 * by setting the function variable to NULL
 */
typedef struct MENUBAR
{
    void (*function)(void);
    char  string[MENUBAR_BUFFER_SIZE];
}Menubar;


enum STATE {
    STATE_PLAYING_SURVIVAL_MODE = 0x0001,
    STATE_MENU = 0x0002,
    STATE_MENU_MAIN = 0x0006,
    STATE_MENU_HIGHSCORES = 0x000A,
    STATE_MENU_TESTLED = 0x0012,
    STATE_MENU_PAUSED = 0x0022,
    STATE_MENU_ABOUT = 0x0042,
    STATE_MENU_PLAY = 0x0082,
    STATE_MENU_DEAD = 0x0102,
    STATE_MENU_GAMEOVER = 0x0202,
    STATE_PLAYING_BOOST = 0x0401,
    STATE_PLAYING_BOOST_FREEZE = 0x0801,
    STATE_PLAYING_BOOST_SPEED = 0x1401,
    STATE_MENU_WON = 0x2002
};

int state;
void draw_menu(void);
void interface_bar_prev(void);
void interface_bar_next(void);
void interface_bar_press(void);

/* This is a very ugly solution, but it allows menu buttons to
 * interact more efficiently using function pointers
 */
void interface_menu_load_main(void);
void interface_menu_load_about(void);
void interface_menu_load_play(void);
void interface_menu_load_paused(void);
#endif //PROJECT_EDITION3_INTERFACE_H
