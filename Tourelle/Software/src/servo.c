#include "../lib/servo.h"
#include <wiringPi.h>

servo_t initServo(const int controlPin){
    
    //Initialisation pin PWM
    pinMode(controlPin,PWM_OUTPUT);
    pwmSetMode(PWM_MODE_MS);

    //Modification des parametre du signal PWM
    pwmSetClock(SERVO_CLOCK_DIVIDOR);
    pwmSetRange(SERVO_PWM_RANGE);

    //Déplacement à la position basse
    pwmWrite(controlPin,SERVO_BOTTOM_POSITION);
    delay(SERVO_DELAY_BETWEEN_MOVE);
    
    //Création et renvoi du servo_t
    servo_t servomoteur = { controlPin, SERVO_BOTTOM_POSITION };
    return servomoteur;
}

int moveServo(servo_t *servo, int position){
   
    //Si la position n'est pas reconnu = erreur
    if(position != SERVO_BOTTOM_POSITION && position != SERVO_MIDDLE_POSITION && position != SERVO_TOP_POSITION) return 1;
    //Si le servo est déja à la position voulu
    if(position == servo->position) return 0 ;

    pwmWrite(servo->controlPin,position);
    delay(SERVO_DELAY_BETWEEN_MOVE);
    servo->position=position;
    return 0;
}

void cleanServoPin(servo_t servo){
    pinMode(servo.controlPin,INPUT);
    digitalWrite(servo.controlPin,LOW);
}