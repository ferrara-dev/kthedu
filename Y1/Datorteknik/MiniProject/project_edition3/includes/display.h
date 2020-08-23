//
// Created by root on 2020-02-18.
//
#include <stdint.h>
#ifndef PROJECT_EDITION3_DISPLAY_H
#define PROJECT_EDITION3_DISPLAY_H

#define PIXEL_UNIT 8
#define HEIGHT 128
#define WIDTH 32
#define PAGES 4
#define TOTAL_PIXELS (HEIGHT * WIDTH / PIXEL_UNIT)
//#define FONT_WITH 6

typedef unsigned char byte;
byte buffer[TOTAL_PIXELS];
static int8_t video_buffer[128][32];

//extern int8_t const font[][FONT_WITH];
int8_t const font[][6] = {
                {0x00, 0x00, 0x00, 0x00, 0x00, 0x00}, /* <SPACE> */
                {0x00, 0x00, 0x02, 0x00, 0x00, 0x00}, /* <.> */
                {0x00, 0x3c, 0x4a, 0x52, 0x3c, 0x00}, /* <0> */
                {0x00, 0x22, 0x7e, 0x02, 0x00, 0x00}, /* <1> */
                {0x00, 0x26, 0x4A, 0x52, 0x26, 0x00}, /* <2> */
                {0x00, 0x24, 0x42, 0x52, 0x2c, 0x00}, /* <3> */
                {0x00, 0x70, 0x10, 0x10, 0x7e, 0x00}, /* <4> */
                {0x00, 0x64, 0x52, 0x52, 0x4c, 0x00}, /* <5> */
                {0x00, 0x3c, 0x52, 0x52, 0x0c, 0x00}, /* <6> */
                {0x00, 0x40, 0x46, 0x48, 0x70, 0x00}, /* <7> */
                {0x00, 0x2c, 0x52, 0x52, 0x2c, 0x00}, /* <8> */
                {0x00, 0x30, 0x4a, 0x4a, 0x3c, 0x00}, /* <9> */
                {0x00, 0x00, 0x24, 0x00, 0x00, 0x00}, /* <:> */
                {0x00, 0x3e, 0x50, 0x50, 0x3e, 0x00}, /* <A> */
                {0x00, 0x7e, 0x52, 0x52, 0x2c, 0x00}, /* <B> */
                {0x00, 0x3c, 0x42, 0x42, 0x24, 0x00}, /* <C> */
                {0x00, 0x7e, 0x42, 0x42, 0x3c, 0x00}, /* <D> */
                {0x00, 0x7e, 0x52, 0x52, 0x42, 0x00}, /* <E> */
                {0x00, 0x7e, 0x50, 0x50, 0x00, 0x00}, /* <F> */
                {0x00, 0x3c, 0x42, 0x4a, 0x0c, 0x00}, /* <G> */
                {0x00, 0x7e, 0x10, 0x10, 0x7e, 0x00}, /* <H> */
                {0x00, 0x42, 0x7e, 0x42, 0x00, 0x00}, /* <I> */
                {0x00, 0x04, 0x02, 0x02, 0x7c, 0x00}, /* <J> */
                {0x00, 0x7e, 0x10, 0x28, 0x46, 0x00}, /* <K> */
                {0x00, 0x7e, 0x02, 0x02, 0x02, 0x00}, /* <L> */
                {0x00, 0x7e, 0x20, 0x10, 0x20, 0x7e}, /* <M> */
                {0x00, 0x7e, 0x20, 0x10, 0x7e, 0x00}, /* <N> */
                {0x00, 0x3c, 0x42, 0x42, 0x3c, 0x00}, /* <O> */
                {0x00, 0x7e, 0x50, 0x50, 0x20, 0x00}, /* <P> */
                {0x00, 0x3c, 0x42, 0x44, 0x3a, 0x00}, /* <Q> */
                {0x00, 0x7e, 0x50, 0x50, 0x2e, 0x00}, /* <R> */
                {0x00, 0x24, 0x52, 0x52, 0x4c, 0x00}, /* <S> */
                {0x00, 0x40, 0x40, 0x7e, 0x40, 0x40}, /* <T> */
                {0x00, 0x7c, 0x02, 0x02, 0x7c, 0x00}, /* <U> */
                {0x00, 0x60, 0x1c, 0x02, 0x1c, 0x60}, /* <V> */
                {0x00, 0x7e, 0x04, 0x08, 0x04, 0x7e}, /* <W> */
                {0x00, 0x46, 0x28, 0x10, 0x28, 0x46}, /* <X> */
                {0x00, 0x40, 0x20, 0x1e, 0x20, 0x40}, /* <Y> */
                {0x00, 0x46, 0x4a, 0x52, 0x62, 0x00}  /* <Z> */
        };
/**
 * Enables a single pixel on the buffer at a specific location within the
 * 128 x 32 resolution buffer.
 * @param x The x-value of the pixel.
 * @param y The y-value of the pixel.
 * @author  Alex Diaz
 */
void set_pixel(int x, int y, int state);



/**
 * Keeps the counter variable.
 * @return 1 is counter is full, else 0.
 * @author Alex Diaz
 */
int next_frame();

/**
 * Simple delay function.
 * @param del Number of cycles to "stall".
 * @author Alex Diaz
 */
void delay(int del);

/**
 * Initializes the SPI controller and the OLED display.
 * @author Fredrik Lundevall
 */
void init_display();

/**
 * Pushes the display[] array to the SPI buffer to be rendered on the display.
 * @author Fredrik Lundevall, modifed by Alex Diaz
 */
void render();

/**
 * Prints characters on the display.
 * @param x    Initial x-value position
 * @param line Row position, 0 ... 3
 * @param data The string to print
 * @author Fredrik Lundevall, modified by Alex Diaz
 */
void print(int x, int line, const char *data, const int len);

/**
 * Fills the display array with 0s.
 * Author: Alex Diaz
 */
void clear();



/* Write the private video buffer to screen
 */
void graphics_reload(void);


/* If the invert flag is set, invert all the
 * colors on the screen
 */
void graphics_postprocess(void);

/* Clear (black) the video buffer
 */
void graphics_clear(void);


/* Print a string to the screen, lines can be between 0 and DSP_PAGES - 1
 * and offset is limited by DSP_COLUMNS
 * Only supports capital letters, numbers, <:> and <.>
 */

void graphics_print(int offset, int line, char const *chrv);

/* Switch the screen between COMMAND/DATA mode
 */
static void set_command_mode(void);

static void set_data_mode(void);
#endif //PROJECT_EDITION3_DISPLAY_H
