#ifndef TURRET_H
#define TURRET_H

#include "stepper.h"
#include "servo.h"

//Permet de retenir l'etat des moteurs de la tourelle 
typedef struct turret
{
    stepper_t stepperX;
    stepper_t stepperY;
    servo_t servo;
}turret_t;


//La variable symbolisant la tourelle
extern turret_t tourelle;

//Les commandes executable par la tourelle
typedef enum turretCommand
{
    MOVE_TOP,
    MOVE_RIGHT,
    MOVE_BOTTOM,
    MOVE_LEFT,
    MOVE_RECALIBRATE,
    SHOOT,
    EXIT
} command_e;

//Les directions de déplacement de la tourelle
typedef enum direction
{
    TOP,
    RIGHT,
    BOTTOM,
    LEFT,
    RECALIBRATE
} direction_e;

//Un block de la file de message
typedef struct command_msg command_msg;
struct command_msg
{
    command_e command;
    command_msg* next;
};

//La file de message
typedef struct queue 
{
    command_msg* first;
}commandQueue;

//Initialisation de la tourelle 
void initTurret(
    const int ctrlPinServo,
    const int numberOfStep, 
    const int dirPinX, const int stepPinX, const int endSwitchPinX,
    const int dirPinY, const int stepPinY, const int endSwitchPinY);

//Déplace la tourelle d'un pas vers "direction"
int moveTurret(direction_e direction);

//Recalibrer la tourelle
void recalibrateTurret();

//Tire une bille
int shoot();

/*Initialiser la file de commande
    Retourne NULL si erreur, le pointeur vers la file si réussite  
*/
commandQueue* commandQ_init();

//Voir si la file de commande est vide
int commandQ_isEmpty(commandQueue* queue);

//Mettre une commande dans la file de commande
int commandQ_Push(commandQueue* queue, command_e command);

//Retourne la commande la plus ancienne de la file de commande (FIFO)
command_e commandQ_Pop(commandQueue* queue);

//Détruire la file de commande
int commandQ_destroy(commandQueue* queue);

//Fonction principale du thread gerant la connexion
void* connectionThread();


#endif //TURRET_H