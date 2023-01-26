package fr.iutfbleau.projetTourelle.VUE;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.URL;

/**
 * <b>GraphPanneau est la classe dont tous les 5 panneaux de la vue vont heriter
 * et qui définit leur aspect graphique</b>
 * <p>
 * Cette classe est "constituee" de 4 couleurs et d'une police.
 * <p>
 *
 * @author Quentin LACOMBE
 * @version 1.0
 */
public class GraphPanneau extends JPanel{

  /**
   * Couleur des boutons verts
   */
  private Color couleurBoutonSauvegarder;

  /**
   * Couleur des boutons marrons/rouges
   */
  private Color couleurBoutonQuitter;

  /**
   * Couleur des textes preremplis dans les champs, soit du gris clair
   */
  private Color couleurTexte;

  /**
   * Couleur des bordures des panneux, soit du gris fonce
   */
  private Color couleurBordurePanneau;

  /**
   * Couleur du fond, soit un blanc un peu grise
   */
  private Color couleurFond;

  /**
   * Police Montserrat Regular
   */
  private Font policeMontserratRegular;

  /**
   * Url qui contient la police Montserrat Regular
   */
  private URL fontUrl;


  /**
   * GraphPanneau.
   * <p>
   * Ce constructeur construit le patern de base des 4 panneuax de la vue.
   * Tout ce qui touche a leur esthetique est configure ici.
   * </p>
   */
  public GraphPanneau(){
    //Les couleurs
    this.couleurBoutonQuitter = new Color(147, 102, 57);
    this.couleurBoutonSauvegarder = new Color(115, 124, 81);
    this.couleurTexte = new Color(128, 128, 128);
    this.couleurBordurePanneau = new Color(105, 105, 105);
    this.couleurFond = new Color(238,238,238);

    //La police Montserrat
    try{
      //this.fichierPoliceMontserratRegular = new File("../../../../../res/polices/Montserrat-Regular.ttf");
      this.fontUrl = new URL("https://dwarves.iut-fbleau.fr/~lacombe/Police/Montserrat-Regular.ttf");
      this.policeMontserratRegular = Font.createFont(Font.TRUETYPE_FONT, this.fontUrl.openStream());
      this.policeMontserratRegular = this.policeMontserratRegular.deriveFont(15.f);
    } catch(Exception exceptionPolice){
      exceptionPolice.printStackTrace();
    }
  }

  /**
   *Retourne la couleur des boutons rouges/marrons
   *
   * @return couleurBoutonQuitter
   *                                La couleur rouge/marron des boutons
   */
  public Color getCouleurBoutonQuitter(){
    return this.couleurBoutonQuitter;
  }

  /**
   *Retourne la couleur des boutons verts
   *
   * @return couleurBoutonSauvegarder
   *                                La couleur verte des boutons
   */
  public Color getCouleurBoutonSauvegarder(){
    return this.couleurBoutonSauvegarder;
  }

  /**
   *Retourne la couleur des textes preremplis
   *
   * @return couleurTexte
   *                                Le gris clair des textes preremplis
   */
  public Color getCouleurTexte(){
    return this.couleurTexte;
  }

  /**
   *Retourne la couleur des bordures des panneaux
   *
   * @return couleurBordurePanneau
   *                              La couleur gris fonce des bords
   */
  public Color getCouleurBordurePanneau(){
    return this.couleurBordurePanneau;
  }

  /**
   *Retourne la couleur de fond
   *
   * @return couleurFond
   *                    Le blanc gris clair du fond
   */
  public Color getCouleurFond(){
    return this.couleurFond;
  }

  /**
   *Retourne la police Montserrat Regular
   *
   * @return policeMontserratRegular
   *                                la police Montserrat Regular
   */
  public Font getPoliceMontserratRegular(){
    return this.policeMontserratRegular;
  }

}
