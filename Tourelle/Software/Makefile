CC=gcc
CFLAGS= 
OBJ = hellomake.o hellofunc.o 

main : servo.o stepper.o turret.o
	$(CC) $(CFLAGS) -lwiringPi -o main servo.o stepper.o turret.o

servo.o : src/servo.c 
	$(CC) $(CFLAGS) -lwiringPi -c  src/servo.c

stepper.o : src/stepper.c 
	$(CC) $(CFLAGS) -lwiringPi -c  src/stepper.c

turret.o : src/turret.c 
	$(CC) $(CFLAGS) -lwiringPi -c  src/turret.c


.PHONY: clean

clean : 
	rm main *.o

