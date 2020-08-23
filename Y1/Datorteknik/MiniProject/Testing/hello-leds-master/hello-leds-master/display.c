#include <pic32mx.h>
#include <stddef.h>   /* Declarations of integer sizes and the like, part 1 */

void initDisplay(void){
    display_init();
    display_string(0, "KTH/ICT lab");
    display_string(1, "in Computer");
    display_string(2, "Engineering");
    display_string(3, "Welcome!");
    display_update();


    quicksleep( 10000 );
}