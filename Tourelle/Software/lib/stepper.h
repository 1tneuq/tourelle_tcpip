/*
Librairie relative à la commande des moteurs pas-à-pas.
Les numéro de pin doivent respecter la numérotation propre à wiringPi
*/

#ifndef STEPPER_H
#define STEPPER_H

//Délai entre chaque pas
#define MICROS_DELAY_BETWEEN_STEP 5000
//Délai entre chaque impulsion sur la pin STEP du driver A4988
#define MICROS_DELAY_BETWEEN_PULSE 2
//Sens de rotation horaire
#define STEPPER_CLOCKWISE HIGH
//Sens de rotation anti-horaire
#define STEPPER_ANTICLOCKWISE LOW

//Avance simplement le moteur d'un pas sans rien faire d'autre contrairement à addStep
#define SIMPLE_STEP(stepPin)    digitalWrite(stepPin, HIGH);\
                                delayMicroseconds(MICROS_DELAY_BETWEEN_PULSE);\
                                digitalWrite(stepPin, LOW);\
                                delayMicroseconds(MICROS_DELAY_BETWEEN_STEP);

//Information sur un moteur pas-à-pas
typedef struct stepper
{
    int dirPin;
    int stepPin;
    int endSwitchPin;
    int position;
    int numberOfStep;
}stepper_t;

//Gestion nameMangling C++
#ifdef __cplusplus
extern "C"{
#endif
/*
* Initialisation d'un moteur, le moteur va au millieu et un stepper_t relatif au moteur est renvoyé
* "dirPin" est le numéro de pin corresondant à DIR sur le driver A4988
* "stepPin" est le numéro de pin correspondant à STEP sur le driver A4988
* "endSwitchPin" est le numéro de pin correspondant au capteur de fin de course
* "numberOfStep" est le nombre de pas que comporte le moteur (varie grace au pin MS1,MS2 et MS3 du driver A4988)
* La fonction retourne un stepper_t comportant les informations du moteur
*/
stepper_t initStepper(const int dirPin, const int stepPin, const int endSwitchPin, const int numberOfStep);

/*
* Nettoyage des pin relatives à "moteur"
*/
void cleanStepperPin(stepper_t moteur);
/*
* Avance le moteur d'un pas vers la direction "direction" et modifie "moteur"
* "moteur" est un pointeur vers le stepper_t relatif au moteur
* "direction" est la direction
* Retourne 0 si reussite et 1 si echec (sens de direction non reconnu)
*/
int addStep(stepper_t *moteur, const int direction);


#ifdef __cplusplus
}
#endif

#endif //STEPPER_H