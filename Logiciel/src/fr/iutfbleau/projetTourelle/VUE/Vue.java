package fr.iutfbleau.projetTourelle.VUE;
import fr.iutfbleau.projetTourelle.CONTROLEUR.*;
import fr.iutfbleau.projetTourelle.MODELE.*;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;

/**
 * <b>Vue est la classe qui gere la fentre de l'application</b>
 * <p>
 *
 * @author Quentin LACOMBE
 * @version 1.0
 */
public class Vue extends JFrame{

  /**
   * Le gestionnaire
   */
  private GridBagConstraints gbc;

  /**
   * Le panneau pricipal qui se substitue à la fenetre
   */
  private JPanel panneauPrincipal;

  /**
   * Le panneau du titre
   */
  private PanneauTitre titre;

  /**
   * Le panneau des logs
   */
  private PanneauLogs logs;

  /**
   * Le panneau des deux boutons du bas
   */
  private PanneauBoutons boutons;

  /**
   * Le panneau de la video
   */
  private PanneauVideo video;

  /**
   * Le panneau de connexion
   */
  private PanneauConnexion recherche;

  /**
   * Le modele de l'application
   */
  private Modele modele;

  /**
   * Le controleur des touches du clavier
   */
  private ControleurTouches controle;

  /**
   * Le controleur des differents boutons
   */
  private ControleurBoutons controleBoutons;

  /**
   * Vue
   * <p>
   * Ce constructeur construit la vue avec ses differents panneaux
   * </p>
   */
  public Vue() throws Exception{
    super("Logiciel de controle de la tourelle");
    this.setSize(840, 850);
    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
    int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
    this.setLocation(x, y);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.controleBoutons = new ControleurBoutons(this);

    this.panneauPrincipal = new JPanel(new GridBagLayout());
    this.gbc = new GridBagConstraints();

    this.recherche = new PanneauConnexion();
    this.recherche.getBoutonConnexion().addActionListener(this.controleBoutons);

    gbc.gridx = 0;      // la plage de cellules commence à la première colonne
    gbc.gridy = 0;      // la plage de cellules commence à la premiere ligne
    gbc.gridwidth = 1;  // la plage de cellules englobe une colonnes
    gbc.gridheight = 1; // la plage de cellules englobe une seule ligne
    gbc.fill = GridBagConstraints.BOTH;     // occupe tout l'espace de la plage
    gbc.anchor = GridBagConstraints.NORTH; // se place en haut au centre de la plage
    gbc.weightx = 1.0;  // souhaite plus de largeur si possible
    gbc.weighty = 1.0;  // souhaite plus de hauteur si possible
    gbc.insets = new Insets(0, 0, 0, 0); // Aucune marge sur aucun des cotés
    this.panneauPrincipal.add(this.recherche, gbc);

    this.add(this.panneauPrincipal);
  }

  /**
   * actualise la fenetre pour passer de la conexion à la vue d'ensemble de la tourelle
   *
   * @param getAdresseServeur
   *                          L'adresse ip du serveur (la tourelle)
   * @param portServeur
   *                          Le port tcp de la tourelle
   *
   */
  public void actualiserVue(String adresseServeur, String portServeur) throws Exception{
    this.panneauPrincipal.removeAll();
    this.panneauPrincipal.repaint();

    this.titre = new PanneauTitre();
    this.logs = new PanneauLogs();
    this.boutons = new PanneauBoutons();
    this.video = new PanneauVideo();

    this.modele = new Modele(this, InetAddress.getByName(adresseServeur), Integer.parseInt(portServeur));
    this.controle = new ControleurTouches(this.modele);
    this.controleBoutons = new ControleurBoutons(this, this.modele);

    /*HAUT*/
    gbc.gridx = 0;      // la plage de cellules commence à la première colonne
    gbc.gridy = 0;      // la plage de cellules commence à la deuxième ligne
    gbc.gridwidth = 1;  // la plage de cellules englobe deux colonnes
    gbc.gridheight = 1; // la plage de cellules englobe une seule ligne
    gbc.fill = GridBagConstraints.BOTH;     // n'occupe pas tout l'espace de la plage
    gbc.anchor = GridBagConstraints.NORTH; // se place au centre de la plage
    gbc.weightx = 1.0;  // souhaite plus de largeur si possible
    gbc.weighty = 0;  // n'a pas besoin de hauteur supplémentaire
    gbc.insets = new Insets(5, 5, 5, 5);    // laisse 5 pixels de vide autour du composant
    this.panneauPrincipal.add(this.titre, gbc);

    /*Video*/
    gbc.gridx = 0;      // la plage de cellules commence à la première colonne
    gbc.gridy = 1;      // la plage de cellules commence à la deuxième ligne
    gbc.gridwidth = 2;  // la plage de cellules englobe deux colonnes
    gbc.gridheight = 2; // la plage de cellules englobe une seule ligne
    gbc.fill = GridBagConstraints.BOTH;     // n'occupe pas tout l'espace de la plage
    gbc.anchor = GridBagConstraints.NORTHWEST; // se place au centre de la plage
    gbc.weightx = 1.0;  // souhaite plus de largeur si possible
    gbc.weighty = 1.0;  // n'a pas besoin de hauteur supplémentaire
    //gbc.insets = new Insets(5, 5, 5, 5);    // laisse 5 pixels de vide autour du composant
    gbc.insets = new Insets(12, 32, 12, 32);
    this.panneauPrincipal.add(this.video, gbc);

    /*Milieu*/
    gbc.gridx = 0;      // la plage de cellules commence à la première colonne
    gbc.gridy = 3;      // la plage de cellules commence à la deuxième ligne
    gbc.gridwidth = 2;  // la plage de cellules englobe deux colonnes
    gbc.gridheight = 1; // la plage de cellules englobe une seule ligne
    gbc.fill = GridBagConstraints.BOTH;     // n'occupe pas tout l'espace de la plage
    gbc.anchor = GridBagConstraints.NORTHWEST; // se place au centre de la plage
    gbc.weightx = 1.0;  // souhaite plus de largeur si possible
    gbc.weighty = 0;  // n'a pas besoin de hauteur supplémentaire
    //gbc.insets = new Insets(5, 5, 5, 5);    // laisse 5 pixels de vide autour du composant
    gbc.insets = new Insets(12, 32, 12, 32);
    this.panneauPrincipal.add(this.logs, gbc);

    /*BAs*/
    gbc.gridx = 0;      // la plage de cellules commence à la première colonne
    gbc.gridy = 4;      // la plage de cellules commence à la deuxième ligne
    gbc.gridwidth = 2;  // la plage de cellules englobe deux colonnes
    gbc.gridheight = 1; // la plage de cellules englobe une seule ligne
    gbc.fill = GridBagConstraints.BOTH;     // n'occupe pas tout l'espace de la plage
    gbc.anchor = GridBagConstraints.NORTHWEST; // se place au centre de la plage
    gbc.weightx = 1.0;  // souhaite plus de largeur si possible
    gbc.weighty = 0;  // n'a pas besoin de hauteur supplémentaire
    this.panneauPrincipal.add(this.boutons, gbc);

    this.logs.setFocusable(true);
    this.logs.requestFocus();
    this.logs.addKeyListener(this.controle);

    this.boutons.getBoutonQuitter().addActionListener(this.controleBoutons);
    this.boutons.getBoutonSauvegarder().addActionListener(this.controleBoutons);
    this.panneauPrincipal.revalidate();
  }

  /**
   * Renvoie le panneau de logs
   *
   * @return logs
   *             Le panneau des logs
   *
   */
  public PanneauLogs getPanneauLogs(){
    return this.logs;
  }

  /**
   * Renvoie le panneau de connexion
   *
   * @return recherche
   *                   Le panneau de connexion
   *
   */
  public PanneauConnexion getPanneauConnexion(){
    return this.recherche;
  }
}
