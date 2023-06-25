
#include <pthread.h>
#include "../lib/turret.h"
#include <signal.h>

//Ce pin doit supporter le PWM
#define PIN_SERVO 1

#define NOMBRE_DE_PAS_STEPPER 200

#define PIN_DIR_STEPPER_X 1
#define PIN_STEP_STEPPER_X 1
#define PIN_ENDSWITCH_X 1

#define PIN_DIR_STEPPER_Y 1
#define PIN_STEP_STEPPER_Y 1
#define PIN_ENDSWITCH_Y 1

turret_t tourelle;
commandQueue* fileCommande;
pthread_mutex_t mutex;

void signalHandler(int signum, pthread_t threadConnexion){
    pthread_kill(threadConnexion, SIGTERM);
    commandQ_destroy(fileCommande);
    pthread_mutex_destroy(&mutex);
    exit(1);
}

int main(void){

    signal(SIGINT,signalHandler);
    signal(SIGQUIT,signalHandler);
    signal(SIGTERM,signalHandler);

    int run = 1;
    pthread_t threadConnexion; 
    pthread_mutex_init(&mutex,NULL);
    fileCommande = commandQ_init();
    
    initTurret(PIN_SERVO,
    NOMBRE_DE_PAS_STEPPER,
    PIN_DIR_STEPPER_X, PIN_STEP_STEPPER_X, PIN_ENDSWITCH_X,
    PIN_DIR_STEPPER_Y, PIN_STEP_STEPPER_Y, PIN_ENDSWITCH_Y);
    
    pthread_create(&threadConnexion, NULL, &connectionThread , NULL);

    while(run){
        pthread_mutex_lock(&mutex); //Verrouille le mutex si libre, sinon se met en pause jusqu'à ce que le mutex soit libre
        
        if(! commandQ_isEmpty(fileCommande)){ //Regarde si une commande est en attente d'etre executée
            
            switch(commandQ_Pop(fileCommande)){ //Si oui on execute la commande
                case MOVE_TOP:
                    moveTurret(TOP);
                    break;

                case MOVE_RIGHT :
                    moveTurret(RIGHT);
                    break;

                case MOVE_BOTTOM:
                    moveTurret(BOTTOM);
                    break;

                case MOVE_LEFT:
                    moveTurret(LEFT);
                    break;

                case SHOOT:
                    shoot();
                    break;

                case MOVE_RECALIBRATE:
                    moveTurret(RECALIBRATE);
                    break;

                case EXIT:
                    run = 0;
                    break;
            }
        }
        pthread_mutex_unlock(&mutex);//Libere le mutex
    }

    pthread_join(threadConnexion, NULL);

    pthread_mutex_destroy(&mutex);
    commandQ_destroy(fileCommande);

    return 0;
}