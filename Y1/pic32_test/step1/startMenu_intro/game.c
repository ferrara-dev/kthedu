
/* game.c */

#include <stdlib.h>
#include <pic32mx.h>
#include "game.h"
#include "icon.h"
#include "interface.h"
#include "functions.h"
#include "peripherals.h"
#include "button.h"


//Entity ghosts[GAME_MAX_GHOSTS];
//Entity player;
int state;

int getsw(void);

void interface_menu_load_paused(void);

Ball ball;

void game_init(void) {
    interface_menu_load_main(); /// Load the main menu from interface.c
}

/** Set state for LED test
 */
void LED_test_start(void) {
    state = STATE_MENU_TESTLED;
}

void display_test_start(void) {

    /// x1 is left-most pixel, x3 is right-most pixel
    int i = 0;
    int j = 0;
    short startingY = 16;
    short startingX = 61;
    /// set y positions
    while (i < 12) {
        ball.ball[i][1] = startingY;
        if (i < 6)
            startingY++;
        else
            startingY--;
        i++;
    }
    /// set x positions
    while (j < 12) {
        ball.ball[j][0] = startingX;
        j++;
        startingX++;
    }
    /*
    ball.x1 = 61;
    ball.x2 = 62;
    ball.x3 = 63;
    ball.x4 = 62;
    ball.y1 = 16;
    ball.y2 = 16;
    ball.y3 = 16;
    ball.y4 = 17;
     */
    ball.speedX = 1;
    ball.speedY = 1;
    state = STATE_PLAYING;
}


void draw(Ball ball);

void display_test_tick(void) {
    static int timeoutcount = 0;
    if (IFS(0) & 0x100) {
        // clearing flag
        IFS(0) = 0;

        // graphics_set(62, 20, 1);
        draw(ball);
        timeoutcount++;
        // display_string(0, itoaconv(getbtns_all())); /// Uppdate and Display pushbtn input once every 0,1 second
        //display_update();
        if (timeoutcount == 100) {
            PORTE = PORTE + 0x1;
            // display_string(3, itoaconv(getsw()));   /// Uppdate and Display switch input once every second
            //  display_update();
            // tick(&mytime);
            timeoutcount = 0;
        }
    }
}

void LED_test_tick(void) {
    static int timeoutcount = 0;

    if (IFS(0) & 0x100) {
        // clearing flag
        IFS(0) = 0;
        timeoutcount++;
        // display_string(0, itoaconv(getbtns_all())); /// Uppdate and Display pushbtn input once every 0,1 second
        //display_update();
        if (timeoutcount == 10) {
            PORTE = PORTE + 0x1;
            // display_string(3, itoaconv(getsw()));   /// Uppdate and Display switch input once every second
            //  display_update();
            // tick(&mytime);
            timeoutcount = 0;
        }
    }
}




