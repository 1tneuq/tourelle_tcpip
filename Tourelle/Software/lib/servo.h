/*
Permet de gérer un servomoteur basé sur un signal PWM avec une fréquence de 50hz 
et un rapport cyclique situé entre 5%(1ms) et 20%(2ms)
*/

#ifndef SERVO_H
#define SERVO_H

#define SERVO_CLOCK_DIVIDOR 1920
#define SERVO_PWM_RANGE 200

#define SERVO_BOTTOM_POSITION 5
#define SERVO_MIDDLE_POSITION 15
#define SERVO_TOP_POSITION 25
#define SERVO_DELAY_BETWEEN_MOVE 360        

//Information sur un servomoteur
typedef struct servo
{   
    int controlPin;
    int position;
}servo_t;

//Gestion nameMangling C++
#ifdef __cplusplus
extern "C"{
#endif

/*
* Initialisation d'un servomoteur
* "controlPin" est le numéro de la pin de controle du servomoteur
* La fonction retourne un servo_t comportant les informations du servomoteur
*/
servo_t initServo(const int controlPin);

/*
* Déplacer le servomoteur "servo" à la position "position"
* Retourne 0 si réussi et 1 si echec (position non reconnu)
*/
int moveServo(servo_t *servo, int position);

/*
* Réinitialise les pin utilisées par "servo"
*/
void cleanServoPin(servo_t servo);

#ifdef __cplusplus
}
#endif

#endif //SERVO_H