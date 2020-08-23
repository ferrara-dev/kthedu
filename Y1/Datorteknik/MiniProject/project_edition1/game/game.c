
/* game.c */

#include <stdlib.h>
#include <pic32mx.h>
#include "includes/game.h"
#include "../graphics/includes/icon.h"
#include "../graphics/includes/interface.h"
#include "../includes/functions.h"
#include "../IO/includes/peripherals.h"
#include "../IO/includes/button.h"
#include "includes/objects.h"
#include "../graphics/includes/graphics.h"
#include "../IO/includes/input.h"
#include "../IO/includes/timers.h"

//Entity ghosts[GAME_MAX_GHOSTS];
//Entity player;
int state;


void interface_menu_load_paused(void);

int m1_spawned = 0;
int m2_spawned = 0;
static int missileCount = 1;
int ammo;

void move(char c) {
    switch (c) {
        case 'p': {
            if (getbtns_all() == 1 || getbtns_all() == 5) {
                if (p.posX < DSP_COLUMNS - 10) {
                    p.posX += p.velX;
                }
            } else if (getbtns_all() == 8 || getbtns_all() == 9) {
                if (p.posX > 10) {
                    p.posX -= p.velX;
                }
            }
            break;
        }

        case 'm': {
            int i = 0;
            for (i = 0; i < AMMO; i++) {
                if (m_array[i].is_alive)
                    m_array[i].posX += m_array[i].velX;
            }


            break;
        }
    }
}

/* Spawn missile */
void spawn_missile(void) {
        if (!m_array[missileCount].is_alive)
            m_array[missileCount++] = missile_new(p);
        if (missileCount > AMMO - 1) {
            missileCount = 0;
        }
}

int cooldown = 0;
int counter = 0;
void shoot(void) {
    if(IFS(0) & 0x1000) {
        IFS(0) &= ~(1 << 12);
        if (!cooldown) {
            spawn_missile();
            cooldown = 1;
        } else
            cooldown--;
    }
        }



void game_init(void) {
    interface_menu_load_main(); /// Load the main menu from interface.c
}

/** Set state for LED test
 */
void LED_test_start(void) {
    state = STATE_MENU_TESTLED;
}

void display_test_start(void) {
    /// Init player ///
    player_new();
    state = STATE_PLAYING;
}

void display_test_tick(void) {
    // graphics_set(62, 20, 1);
        draw(p, 1);
            for (int i = 0; i < AMMO; i++)
                if (m_array[i].is_alive) {
                    object_update(&m_array[i]);
                }
}


void LED_test_tick(void) {
    static int timeoutcount = 0;
    if (IFS(0) & 0x9000) {
        IFSCLR(0) = 0x9000;
        timeoutcount++;
        // display_string(0, itoaconv(getbtns_all())); /// Uppdate and Display pushbtn input once every 0,1 second
        //display_update();
        //if (timeoutcount == 10) {
        //  PORTE = PORTE + 0x1;
        // display_string(3, itoaconv(getsw()));   /// Uppdate and Display switch input once every second
        //  display_update();
        // tick(&mytime);
        //
    }
}




