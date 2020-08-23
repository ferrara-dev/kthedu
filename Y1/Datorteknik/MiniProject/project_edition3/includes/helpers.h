//
// Created by root on 2020-02-17.
//

#ifndef PROJECT_EDITION3_HELPERS_H
#define PROJECT_EDITION3_HELPERS_H

#include <pic32mx.h>
#include <stdlib.h>
#include <stdint.h>
void *stdin, *stdout, *stderr, *errno;
unsigned int seed(void);
//
// Created by root on 2020-02-17.
//



/* BEGINNING OF COPIED CODE */
void quicksleep(int cyc);

uint8_t spi_send_recv(uint8_t data);


/* Integer to decimal string conversion function
 */
void itodsconv(char *buffer, int x);


/* END OF COPIED CODE */

unsigned int seed(void);
/* Reverse every bit in one byte
 */
int8_t reverse_byte(int8_t x);

int randint(int min, int max);

float randfloat(float min, float max);
/********************************
 * Had to write my own function
 * to calculate sqrt.
 * Couldn't get #include<math.h>
 * to work.
 ******************************/
int randomize(int chance);
float sqrt(int a);
/* Calculates the distance between to xy-positions. */
/*  @author Johan Edman */
int dist(float x1, float y1, float x2, float y2);



#endif //PROJECT_EDITION3_HELPERS_H