package fr.iutfbleau.projetTourelle.CONTROLEUR;
import fr.iutfbleau.projetTourelle.MODELE.*;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;

/**
 * <b>ControleurTouches est la classe qui ecoute les touches du clavier</b>
 * <p>
 *
 * @author Quentin LACOMBE
 * @version 1.0
 */
public class ControleurTouches implements KeyListener{

  /**
   * Modele de l'application
   */
  private Modele modele;

  /**
   * ControleurTouches.
   * <p>
   * Ce constructeur construit le controleur des touches en initialisanrt le modele
   * </p>
   */
  public ControleurTouches(Modele model){
    this.modele = model;
  }

  /**
   * Permet d'intercepter les actions des touches enfoncees et de les retranscrire
   * en actions de la tourelle.
   *
   * @param e
   *          Le KeyEvent
   *
   */
  @Override
  public void keyPressed (KeyEvent e) {
    int codeTouche = e.getKeyCode();
    switch(codeTouche){
      case KeyEvent.VK_UP:
        try{
          this.modele.mettreAJour(this.modele.HAUT);
        }catch(IOException exept){
          exept.printStackTrace();
        }
        //System.out.println("Haut");
        break;
      case KeyEvent.VK_DOWN:
        try{
          this.modele.mettreAJour(this.modele.BAS);
        }catch(IOException exept){
          exept.printStackTrace();
        }
        //System.out.println("Bas");
        break;
      case KeyEvent.VK_LEFT:
        try{
          this.modele.mettreAJour(this.modele.GAUCHE);
        }catch(IOException exept){
          exept.printStackTrace();
        }
        //System.out.println("Gauche");
        break;
      case KeyEvent.VK_RIGHT :
        try{
          this.modele.mettreAJour(this.modele.DROITE);
        }catch(IOException exept){
          exept.printStackTrace();
        }
        //System.out.println("Droite");
        break;
      case KeyEvent.VK_SPACE :
        try{
          this.modele.mettreAJour(this.modele.ESPACE);
        }catch(IOException exept){
          exept.printStackTrace();
        }
        //System.out.println("Droite");
        break;
      }
  }

  /**
   * Permet d'intercepter les actions des touches relachees
   *
   * @param e
   *          Le KeyEvent
   *
   */
  @Override
  public void keyReleased (KeyEvent e) {}

    /**
     * Permet d'intercepter les actions des touches cliquees
     *
     * @param e
     *          Le KeyEvent
     *
     */
  @Override
  public void keyTyped (KeyEvent e) {}
}
