
/* graphics.c */

#include <stdint.h>
#include <pic32mx.h>
#include "peripherals.h"
#include "icon.h"
#include "functions.h"



volatile int invert = 0;

/* Pixel manipulation will take place on this static video buffer
 * which will be written to the screen every tick 
 */
static int8_t video_buffer[DSP_COLUMNS][DSP_PAGES];

static void set_command_mode(void);
static void set_data_mode(void);



/* Set up the screen
 */
void graphics_init(void)
{
	/* BEGINNING OF COPIED CODE */
	
	set_command_mode();
	quicksleep(10);
	PORTFCLR = 0x40; /* Activate display VDD */
	quicksleep(1000000);
	
	spi_send_recv(0xAE);
	PORTGCLR = 0x200; /* Activate reset */
	quicksleep(10);
	PORTGSET = 0x200; /* Deactivate reset */
	quicksleep(10);
	
	spi_send_recv(0x8D);
	spi_send_recv(0x14);
	
	spi_send_recv(0xD9);
	spi_send_recv(0xF1);
	
	PORTFCLR = 0x20; /* Activate VBAT */
	quicksleep(10000000);
	
	spi_send_recv(0xA1);
	spi_send_recv(0xC8);
	
	spi_send_recv(0xDA);
	spi_send_recv(0x20);
	
	spi_send_recv(0xAF); 
	
	/* END OF COPIED CODE */
}




/* Set one pixel to either white or black
 */
void graphics_set(int x, int y, int val)
{
	if(x >= DSP_COLUMNS || x < 0)
		return;
	if(y >= DSP_ROWS    || y < 0)
		return;
	
	/* As the page size is 8 bits, y divided by 8 will give the correct */ 
	/* page number and the lower 3 bits will give the bit offset */
	
	/* Clear the pixel that is being written to */
	video_buffer[x][y >> 3] &= ~(1 << (y & 0x07));
	/* Write */
	video_buffer[x][y >> 3] |= (val ? 1 : 0) << (y & 0x07);
}




/* Write the private video buffer to screen
 */
void graphics_reload(void)
{       
	for(int p = 0; p < DSP_PAGES; ++p)
	{
		set_command_mode();
		/* Write to display */
		spi_send_recv(0x22);
		/* Specify which page we're writing to */
		spi_send_recv(p);
		/* Set byte ordering to start at left side of screen*/
		spi_send_recv(0x00);
		spi_send_recv(0x10);
		
		set_data_mode();
		
		/* Write the data to display */
		for(int x = 0; x < DSP_COLUMNS; ++x)
		{
			spi_send_recv(video_buffer[x][p]);
		}
	}
}




/* If the invert flag is set, invert all the 
 * colors on the screen
 */
void graphics_postprocess(void)
{
	if(invert)
	{
		for(int p = 0; p < DSP_PAGES; ++p)
		{
			for(int x = 0; x < DSP_COLUMNS; ++x)
			{
				video_buffer[x][p] = ~video_buffer[x][p];
			}
		}
	}
}




/* Clear (black) the video buffer
 */
void graphics_clear(void)
{
	for(int p = 0; p < DSP_PAGES; ++p)
	{
		for(int x = 0; x < DSP_COLUMNS; ++x)
		{
			video_buffer[x][p] = 0x00;
		}
	}
}




/* Print a string to the screen, lines can be between 0 and DSP_PAGES - 1 
 * and offset is limited by DSP_COLUMNS 
 * Only supports capital letters, numbers, <:> and <.>
 */
void graphics_print(int offset, int line, char const *chrv)
{
	if(offset < 0 || offset >= DSP_COLUMNS)
		return;
	if(line < 0 || line >= DSP_PAGES)
		return;
	
	while(*chrv)
	{
		int idx;
		
		if(*chrv == '.')
			idx = 1;
		else if(*chrv == ':')
			idx = 12;
		else if(*chrv >= '0' && *chrv <= '9')
			idx = *chrv - ('0' - 2);
		else if(*chrv >= 'A' && *chrv <= 'Z')
			idx = *chrv - ('A' - 13);
		else
			idx = 0;
		
		for(int i = 0; i < FONT_WIDTH && offset < DSP_COLUMNS; ++i)
		{
			/* Letters are given with the bits in reverse order */
			video_buffer[offset++][line] |= reverse_byte(font[idx][i]);
		}
		++chrv;
	}
}


int8_t const font[][6] =
        {
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





/* Switch the screen between COMMAND/DATA mode
 */  
static void set_command_mode(void)
{
	PORTFCLR = 0x10;
}

static void set_data_mode(void)
{
	PORTFSET = 0x10;
}
