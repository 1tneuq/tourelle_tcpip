package fr.iutfbleau.projetTourelle.MODELE;
import fr.iutfbleau.projetTourelle.VUE.*;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;
import java.lang.*;

/**
 * <b>Modele est la classe qui gere la connexion à la tourelle ainsi que la sauvegarde des actions</b>
 * <p>
 *
 * @author Quentin LACOMBE
 * @version 1.0
 */
public class Modele{

  /**
   * Constantes qui représentent les actions
   */
  public static final int HAUT = 0;
  public static final int GAUCHE = 1;
  public static final int BAS = 2;
  public static final int DROITE = 3;
  public static final int ESPACE = 4;

  /**
   * File des 10 actions qui sont affichées dans le terminal de logs de l'application
   */
  private LinkedList<String> texteLogs;

  /**
   * Liste qui garde en mémoire la totalité des actions de la tourelle
   */
  private ArrayList<String> traceLogs;

  /**
   * Le socket du client
   */
  private Socket socket;

  /**
   * Le truc pour écrire
   */
  private PrintWriter scribe;

  /**
   * La vue de l'application
   */
  private Vue vue;

  /**
   * Modele.
   * <p>
   * Constructeur qui construit le modele
   *
   * @param view
   *                        La vue
   * @param adresseServeur
   *                        L'adresse IP du serveur
   * @param portServeur
   *                        Le port TCP du serveur
   * </p>
   */
  public Modele(Vue view, InetAddress adresseServeur, int portServeur) throws Exception{
    this.vue = view;
    this.texteLogs = new LinkedList<String>();
    this.traceLogs = new ArrayList<String>();
    try{
      this.socket = new Socket(adresseServeur, portServeur);
    }catch(SocketException exept){
      JOptionPane.showMessageDialog(this.vue, "Erreur connexion");
      SwingUtilities.updateComponentTreeUI(this.vue);
    }catch(UnknownHostException exept1){
      JOptionPane.showMessageDialog(this.vue, "Erreur connexion");
      SwingUtilities.updateComponentTreeUI(this.vue);
    }catch(NullPointerException exept2){
      JOptionPane.showMessageDialog(this.vue, "Erreur connexion");
      SwingUtilities.updateComponentTreeUI(this.vue);
    }
    JOptionPane.showMessageDialog(this.vue, "Connecté à la tourelle: " + this.socket.getInetAddress());
  }

  /**
   * Met a jour les logs, et envoie les infos au serveur (la tourelle)
   *
   * @param direction
   *                  L'action de tourelle
   *
   */
  public void mettreAJour(int direction) throws IOException{
    this.scribe = new PrintWriter(this.socket.getOutputStream(), true);

    if(this.texteLogs.size() == 9){
      this.texteLogs.pollFirst();
    }

    switch(direction){
      case Modele.HAUT:
        this.texteLogs.add(this.texteLogs.size(), "<font color='#582F0E'>Inclinaison de la tourelle vers le haut</font>");
        this.traceLogs.add(this.traceLogs.size(),"Inclinaison de la tourelle vers le haut");
        this.scribe.println("haut");
        this.scribe.flush();
        break;

      case Modele.GAUCHE:
        this.texteLogs.add(this.texteLogs.size(), "<font color='#333D29'>Rotation de la tourelle vers la gauche</font>");
        this.traceLogs.add(this.traceLogs.size(),"Rotation de la tourelle vers la gauche");
        this.scribe.println("gauche");
        this.scribe.flush();
        break;

      case Modele.BAS:
        this.texteLogs.add(this.texteLogs.size(), "<font color='#7F4F24'>Inclinaison de la tourelle vers le bas</font>");
        this.traceLogs.add(this.traceLogs.size(),"Inclinaison de la tourelle vers le bas");
        this.scribe.println("bas");
        this.scribe.flush();
        break;

      case Modele.DROITE:
        this.texteLogs.add(this.texteLogs.size(), "<font color='#414833'>Rotation de la tourelle vers la droite</font>");
        this.traceLogs.add(this.traceLogs.size(),"Rotation de la tourelle vers la droite");
        this.scribe.println("droite");
        this.scribe.flush();
        break;

      case Modele.ESPACE:
        this.texteLogs.add(this.texteLogs.size(), "<font color='black'>La tourelle tire</font>");
        this.traceLogs.add(this.traceLogs.size(),"La tourelle tire");
        this.scribe.println("tir");
        this.scribe.flush();
        break;

      default:
        break;
    }

    this.vue.getPanneauLogs().ecrire(this.texteLogs);
  }

  /**
   * Ecrit le contenu de la liste des logs dans un fichier .txt
   */
  public void sauvegarderTrace(){
    try{
      File fichier = new File("./Logs_Tourelle.txt");
      if (fichier.createNewFile()){
        System.out.println("Fichier créé avec succes: " + fichier.getName());
      }else {
        System.out.println("Le Fichier existe deja");
      }
    }catch(IOException e){
      System.out.println("erreur");
      e.printStackTrace();
    }

    try {
     FileWriter fichierLogs = new FileWriter("./Logs_Tourelle.txt");
     fichierLogs.write("Initialisation tourelle...\n");
     for(int i=0; i<this.traceLogs.size(); i++){
       fichierLogs.write(this.traceLogs.get(i)+"\n");
     }
     fichierLogs.write("Fin");
     fichierLogs.close();
     System.out.println("Ecriture effectuée avec succes");
    }catch(IOException e){
     System.out.println("erreur");
     e.printStackTrace();
   }
  }
}
