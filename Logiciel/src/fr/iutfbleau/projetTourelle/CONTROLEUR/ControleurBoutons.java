package fr.iutfbleau.projetTourelle.CONTROLEUR;
import fr.iutfbleau.projetTourelle.MODELE.*;
import fr.iutfbleau.projetTourelle.VUE.*;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;
import java.time.*;
import java.sql.Time;

/**
 * <b>ControleurBoutons est la classe qui gere les actions relatives aux boutons quitter, sauvegarder et connexion</b>
 * <p>
 * Ce ControleurBoutons a une VueLogiciel, qu'il "ecoute", et un Modele
 *<p>
 *
 * @author Quentin LACOMBE
 * @version 1.0
 */
public class ControleurBoutons implements ActionListener{

  /**
   * Vue de l'application
   */
  private Vue vue;

  /**
   * Modele de l'application
   */
  private Modele modele;

  /**
   * ControleurBoutons.
   * <p>
   * Constructeur pour les boutons quitter et sauvegarder
   * </p>
   *
   * @param view
   *            La vue dans que le contoleur ecoute
   *
   * @param model
   *            Le modele qui gere les donnees de l'application
   *
   */
  public ControleurBoutons(Vue view, Modele model){
    this.vue = view;
    this.modele = model;
  }

  /**
   * ControleurBoutons.
   * <p>
   * Constructeur exclusif au bouton Connexion car sur la fenetre de connexion on n'a pas encore besoin du modele
   * </p>
   *
   * @param vue
   *            La vue dans que le contoleur ecoute
   *
   */
  public ControleurBoutons(Vue view){
    this.vue = view;
  }

  /**
   * Lorsque le bouton "Sauvegarder" est pressé, sauvegarde les actions effectuees par la tourelle
   * Lorsque le bouton "Quiter" est pressé, ferme l'application
   * Lorsque le bouton "Connexion" est pressé, envoie les infos ip et port au logiciel pour qu'il se connecte à la tourelle
   *
   * @param e
   *          un evenement
   *
   */
  @Override
  public void actionPerformed(ActionEvent e){
    JButton booton = (JButton)e.getSource();

    if(booton.getText().equals("QUITTER")){
      this.vue.setVisible(false);
      this.vue.dispose();
    }

    if(booton.getText().equals("SAUVEGARDER")){
      System.out.println("Sauvegarde...");
      this.modele.sauvegarderTrace();
      JOptionPane.showMessageDialog(this.vue, "Trace Sauvegardée avec succès !");
      this.vue.getPanneauLogs().requestFocus();
    }

    if(booton.getText().equals("CONNEXION")){
      try{
        this.vue.actualiserVue(this.vue.getPanneauConnexion().getAdresseServeur().getText(), this.vue.getPanneauConnexion().getPortServeur().getText());
      }catch(Exception exept){
        exept.printStackTrace();
      }
    }

  }
}
