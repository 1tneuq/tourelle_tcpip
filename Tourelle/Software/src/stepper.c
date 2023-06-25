#include "../lib/stepper.h"
#include <wiringPi.h>
#include <stdio.h>


stepper_t initStepper(int dirPin, int stepPin, int endSwitchPin, int numberOfStep){

    //Initialisation pin direction
    pinMode(dirPin, OUTPUT);
    digitalWrite(dirPin, STEPPER_ANTICLOCKWISE);
  
    //Initialisation pin step
    pinMode(stepPin, OUTPUT);
    digitalWrite(dirPin, LOW);

    //Initialisation pin switch fin de course
    pinMode(endSwitchPin, INPUT);
    digitalWrite(endSwitchPin, LOW);
    pullUpDnControl(endSwitchPin,PUD_UP); //Resistance de pull-up donc niveau logique de base = 1

    //Détection de la position 0
    int i = digitalRead(endSwitchPin);
    while(i){ //Si le bouton n'est pas enclenché
        SIMPLE_STEP(stepPin);
        i=digitalRead(endSwitchPin);
    }
    
    //Déplace le moteur à la position du milieu
    digitalWrite(dirPin, STEPPER_CLOCKWISE); 
    for(i=0;i<(numberOfStep/2);i++){
        SIMPLE_STEP(stepPin);
    }

    //Création et renvoi du stepper_t
    stepper_t moteur = { dirPin, stepPin, endSwitchPin, numberOfStep/2 , numberOfStep };
    return moteur;
}

int addStep(stepper_t *moteur, int direction){

    switch(direction){
        //Tourner dans le sens horaire
        case STEPPER_CLOCKWISE:
            if( moteur->position == moteur->numberOfStep ) return 0; //Pas nécessaire de modifier la position du moteur si on est déja à la position maximale 
            digitalWrite(moteur->dirPin,STEPPER_CLOCKWISE); //Sens de rotation = horaire
            SIMPLE_STEP(moteur->stepPin); //Déplacement d'un pas au moteur
            moteur->position++; //Modification de la position du moteur
            break;
        //Tourner dans le sens anti-horaire
        case STEPPER_ANTICLOCKWISE:
            if( moteur->position == 0 ) return 0;
            digitalWrite(moteur->dirPin,STEPPER_ANTICLOCKWISE);
            SIMPLE_STEP(moteur->stepPin);
            moteur->position--;
            break;
        //Si la direction n'est pas reconnu
        default:
            return 1;
    }
    return 0;
}

void cleanStepperPin(stepper_t moteur){

    //Pin DIR
    digitalWrite(moteur.dirPin, LOW);
    pinMode(moteur.dirPin, INPUT);

    //Pin STEP
    digitalWrite(moteur.stepPin, LOW);
    pinMode(moteur.stepPin, INPUT);

    //Pin Switch fin de course
    digitalWrite(moteur.endSwitchPin, LOW);
    pinMode(moteur.endSwitchPin,INPUT);
}

