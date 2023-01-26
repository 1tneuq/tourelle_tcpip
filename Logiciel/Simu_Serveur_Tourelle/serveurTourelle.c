#include <sys/socket.h>
#include <unistd.h>
#include <arpa/inet.h>
#include <stdio.h>
#include <netdb.h>
#include <netinet/in.h>
#include <stdlib.h>
#include <string.h>
#include <sys/socket.h>
#include <sys/types.h>
#define MAX 80
#define PORT 8080
#define SA struct sockaddr

//fonction pour recevoir les donnees du client (le logiciel)
void func(int connfd){
    char buff[MAX];
    int n;
    //boucle infinie pour ecouter
    for (;;) {
        bzero(buff, MAX);

        //lit le msg du client et l'ecrit dans le buffer
        read(connfd, buff, sizeof(buff));
        //affiche le buffer qui contient les infos du client
        printf("Action tourelle: %s\n", buff);


        //pour l'instant faut faire un ctrl+c pour arreter le serveur meme si c'est pas propre
    }
}


int main(int argc, char const *argv[]) {
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
  servaddr.sin_addr.s_addr = htonl(INADDR_ANY);
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
    printf("la tourelle ecoute sur: IP: 127.0.0.1  PORT:8080\n");
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
  func(connfd);

  // fermeture du socket à la fin mais comme ya pas encore de signal handler on arrive jamais ici
  close(sockfd);

  return 0;
}
