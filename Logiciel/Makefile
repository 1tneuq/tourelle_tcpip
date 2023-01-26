# COMMANDES #
JAVAC = javac
# note $$ to get a single shell $
JAVAC_OPTIONS = -d build -cp build:$$CLASSPATH -implicit:none -sourcepath src
JAVA = java
JAR = jar
EXEC_JAR = ${JAVA} -jar

# CHEMINS RELATIFS
SRC = src/fr/iutfbleau/projetTourelle
BUILD = build/fr/iutfbleau/projetTourelle
DOC = doc/fr/iutfbleau/projetTourelle

# CHOIX NOMS
JAR_LOGICIEL_TOURELLE = logiciel_tourelle.jar

# BUTS FACTICES #
.PHONY : run clean doc

# BUT PAR DEFAUT #
run : ${JAR_LOGICIEL_TOURELLE}
	${EXEC_JAR} ${JAR_LOGICIEL_TOURELLE}

# AUTRE BUTS
doc :
	javadoc -d doc src/fr/iutfbleau/projetTourelle/MODELE/*.java src/fr/iutfbleau/projetTourelle/VUE/*.java src/fr/iutfbleau/projetTourelle/CONTROLEUR/*.java

clean :
	rm -rf ${BUILD}/* *.jar

# REGLES DE DEPENDANCE #
## VUE ##
${BUILD}/VUE/Vue.class : ${SRC}/VUE/Vue.java \
	                              ${BUILD}/CONTROLEUR/ControleurBoutons.class \
																${BUILD}/CONTROLEUR/ControleurTouches.class \
																${BUILD}/MODELE/Modele.class \
																${BUILD}/VUE/PanneauBoutons.class \
																${BUILD}/VUE/PanneauConnexion.class \
																${BUILD}/VUE/PanneauLogs.class \
																${BUILD}/VUE/PanneauTitre.class \
																${BUILD}/VUE/PanneauVideo.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/VUE/Vue.java

${BUILD}/VUE/GraphPanneau.class : ${SRC}/VUE/GraphPanneau.java
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/VUE/GraphPanneau.java

${BUILD}/VUE/PanneauBoutons.class : ${SRC}/VUE/PanneauBoutons.java \
																${BUILD}/VUE/GraphPanneau.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/VUE/PanneauBoutons.java

${BUILD}/VUE/PanneauConnexion.class : ${SRC}/VUE/PanneauConnexion.java \
																${BUILD}/VUE/GraphPanneau.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/VUE/PanneauConnexion.java

${BUILD}/VUE/PanneauLogs.class : ${SRC}/VUE/PanneauLogs.java \
																${BUILD}/VUE/GraphPanneau.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/VUE/PanneauLogs.java

${BUILD}/VUE/PanneauTitre.class : ${SRC}/VUE/PanneauTitre.java \
																${BUILD}/VUE/GraphPanneau.class
		${JAVAC} ${JAVAC_OPTIONS} ${SRC}/VUE/PanneauTitre.java

${BUILD}/VUE/PanneauVideo.class : ${SRC}/VUE/PanneauVideo.java \
																${BUILD}/VUE/GraphPanneau.class
		${JAVAC} ${JAVAC_OPTIONS} ${SRC}/VUE/PanneauVideo.java


## CONTROLEUR ##
${BUILD}/CONTROLEUR/ControleurBoutons.class : ${SRC}/CONTROLEUR/ControleurBoutons.java \
																								${BUILD}/VUE/Vue.class \
																								${BUILD}/MODELE/Modele.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/CONTROLEUR/ControleurBoutons.java

${BUILD}/CONTROLEUR/ControleurTouches.class : ${SRC}/CONTROLEUR/ControleurTouches.java \
																								${BUILD}/MODELE/Modele.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/CONTROLEUR/ControleurTouches.java

## MODELE ##
${BUILD}/MODELE/Modele.class : ${SRC}/MODELE/Modele.java \
																${BUILD}/VUE/Vue.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/MODELE/Modele.java


## MAIN ##
 ${BUILD}/MAIN/Main.class : ${SRC}/MAIN/Main.java \
 														${BUILD}/VUE/Vue.class
	${JAVAC} -Xlint:deprecation ${JAVAC_OPTIONS} ${SRC}/MAIN/Main.java

# ## JARS ##
 ${JAR_LOGICIEL_TOURELLE} : ${BUILD}/MAIN/Main.class
	${JAR} cvfe ${JAR_LOGICIEL_TOURELLE} fr.iutfbleau.projetTourelle.MAIN.Main -C build fr
