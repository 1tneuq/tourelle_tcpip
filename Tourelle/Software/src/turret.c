#include <wiringPi.h>
#include <stdlib.h>
#include "../lib/turret.h"
#include<stdio.h>
#include<pthread.h>
#include <signal.h>
#include <sys/socket.h>
#include <unistd.h>
#include <arpa/inet.h>
#include <netdb.h>
#include <netinet/in.h>
#include <sys/types.h>
#define MAX 80
#define PORT 8080
#define SA struct sockaddr

extern turret_t tourelle;

void initTurret(
    const int ctrlPinServo,
    const int numberOfStep,
    const int dirPinX, const int stepPinX, const int endSwitchPinX,
    const int dirPinY, const int stepPinY, const int endSwitchPinY){

    tourelle.stepperX = initStepper(dirPinX, stepPinX, endSwitchPinX, numberOfStep);
    tourelle.stepperY = initStepper(dirPinY, stepPinY, endSwitchPinY, numberOfStep);
    tourelle.servo = initServo(ctrlPinServo);
}

int moveTurret(direction_e direction){
    switch(direction){
        case TOP:
            addStep(&tourelle.stepperY, STEPPER_CLOCKWISE);
            break;

        case RIGHT:
            addStep(&tourelle.stepperX, STEPPER_CLOCKWISE);
            break;

        case BOTTOM:
            addStep(&tourelle.stepperY, STEPPER_ANTICLOCKWISE);
            break;

        case LEFT:
            addStep(&tourelle.stepperX, STEPPER_ANTICLOCKWISE);
            break;

        case RECALIBRATE:
        recalibrateTurret();
            break;

        default:
            return 1;
    }
    return 0;
}

void recalibrateTurret(){

    int i = digitalRead(tourelle.stepperX.endSwitchPin);
    while(i){
        SIMPLE_STEP(tourelle.stepperX.stepPin);
        i=digitalRead(tourelle.stepperX.endSwitchPin);
    }
    tourelle.stepperX.position = 0;

    i = digitalRead(tourelle.stepperY.endSwitchPin);
    while(i){
        SIMPLE_STEP(tourelle.stepperX.stepPin);
        i=digitalRead(tourelle.stepperX.endSwitchPin);
    }
    tourelle.stepperX.position = 0;
}

int shoot(){
    moveServo(&tourelle.servo, SERVO_MIDDLE_POSITION);
    moveServo(&tourelle.servo, SERVO_BOTTOM_POSITION);
    return 0;
}

commandQueue* commandQ_init(){
    commandQueue *msgQueue = malloc(sizeof(commandQueue));
    if(msgQueue == NULL) return NULL;
    msgQueue->first = NULL;
    printf("pointeur = %p\n",msgQueue);
    return msgQueue;
}

int commandQ_isEmpty(commandQueue* queue){
    return (queue->first==NULL)?1:0;
}

int commandQ_Push(commandQueue* queue, command_e command){

    //Si file n'existe pas
    if(queue == NULL)
        return 1;

    command_msg* newMsg = malloc(sizeof(command_msg*));

    //Si echec du malloc
    if(newMsg == NULL)
        return 1;

    //Le nouveau message à inserer
    newMsg->command = command;
    newMsg->next = NULL;

    //Si la file n'est pas vide
    if(!commandQ_isEmpty(queue)){
        command_msg* msgBuffer = queue->first; //récupere l'adresse du premier block
        while(msgBuffer->next!=NULL){//On parcours la file
            msgBuffer=msgBuffer->next;
        }
        msgBuffer->next = newMsg;//L'ancien dernier message pointe vers le nouveau

    }else queue->first=newMsg; //Si file vide

    return 0;
}

command_e commandQ_Pop(commandQueue* queue){

    if(queue->first!=NULL){
        command_msg* buffer = queue->first;
        queue->first = buffer->next;
        command_e commandValue = buffer->command;
        free(buffer);

        return commandValue;
    } else return -1;
}

int commandQ_destroy(commandQueue* queue){

    if(queue!=NULL){
        printf("pointeur = %p\n",queue);
        int returnValue = 0;
        while(returnValue!=-1){ //Supprime tous les messages de la file
            returnValue = commandQ_Pop(queue);
        }
        free(queue);//Supprime la file
        return 0;
    }else return 1;

}

void connectionThreadSigHandler(int signum){
    extern commandQueue* queue;
    extern pthread_mutex_t mutex;

    commandQ_Push(queue,EXIT);
    pthread_mutex_unlock(&mutex);
    exit(1);
}

void* connectionThread(){
    //waitco

    signal(SIGINT,connectionThreadSigHandler);
    signal(SIGQUIT,connectionThreadSigHandler);
    signal(SIGTERM,connectionThreadSigHandler);

    extern commandQueue* fileCommande;
    extern pthread_mutex_t mutex;

    int sockfd, connfd, len;
    struct sockaddr_in servaddr, cli;

    //creation du socket et verif si ca a bien marchéSSS
    sockfd = socket(AF_INET, SOCK_STREAM, 0);
    if (sockfd == -1) {
        printf("Erreur creation socket\n");
        exit(0);
    }
    else
        printf("Creation du socket reussie\n");
    bzero(&servaddr, sizeof(servaddr));

    //on assigne le port et l'ip
    servaddr.sin_family = AF_INET;
    servaddr.sin_addr.s_addr = inet_addr("192.168.1.100");
    servaddr.sin_port = htons(PORT);

    //on bind le socket avec l'adresse ip qu'on veut
    if ((bind(sockfd, (SA*)&servaddr, sizeof(servaddr))) != 0) {
        printf("erreur bind socket\n");
        exit(0);
    }
    else
        printf("bind du socket reussi\n");

    //mtn le serveur peut ecouter
    if ((listen(sockfd, 5)) != 0) {
        printf("erreur ecoute\n");
        exit(0);
    }
    else {
      printf("la tourelle ecoute sur: IP: 192.168.1.100  PORT:8080\n");
    }

    len = sizeof(cli);

    // reception des paquets du client et verif
    connfd = accept(sockfd, (SA*)&cli, &len);
    if (connfd < 0) {
        printf("echec reception\n");
        exit(0);
    }
    else {
      printf("reception des paquets du logiciel\n");
    }


    // on lance la fonction pour ecouter a l'infini
    char buff[MAX];
    int n;
    //boucle infinie pour ecouter
    for (;;) {
        bzero(buff, MAX);

        //lit le msg du client et l'ecrit dans le buffer
        read(connfd, buff, sizeof(buff));
        //affiche le buffer qui contient les infos du client
        printf("Action tourelle: %s\n", buff);

        pthread_mutex_lock(&mutex);
        switch(buff){
            case "haut":
                commandQ_Push(fileCommande,TOP);
                break;

            case "droite":
                commandQ_Push(fileCommande,RIGHT);
                break;

            case "bas":
                commandQ_Push(fileCommande,BOTTOM);
                break;

            case "gauche":
                commandQ_Push(fileCommande,LEFT);
                break;

            case "tir":
                commandQ_Push(fileCommande,SHOOT);
                break;
          }
          pthread_mutex_unlock(&mutex);
        //pour l'instant faut faire un ctrl+c pour arreter le serveur meme si c'est pas propre
    }

    // fermeture du socket à la fin mais comme ya pas encore de signal handler on arrive jamais ici
    close(sockfd);
    exit(0);
}
