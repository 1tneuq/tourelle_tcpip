package fr.iutfbleau.projetTourelle.VUE;

import javax.swing.*;
import java.awt.*;
import java.io.*;

/**
 * <b>PanneauBoutons est la classe qui gere le panneau contenant les boutons sauvegarder et quitter</b>
 * <p>
 *
 * @author Quentin LACOMBE
 * @version 1.0
 */
public class PanneauBoutons extends GraphPanneau{

  /**
   * Bouton quitter
   */
  private JButton quitter;

  /**
   * Bouton sauvegarder
   */
  private JButton sauvegarder;

  /**
   * PanneauBoutons.
   * <p>
   * Ce constructeur construit le panneau qui contient les deux boutons
   * </p>
   */
  public PanneauBoutons(){
    this.setLayout(new GridBagLayout());

    GridBagConstraints gbc = new GridBagConstraints();

    this.quitter = new JButton("QUITTER");
    this.quitter.setFont(super.getPoliceMontserratRegular());
    this.quitter.setBorderPainted(false);
    this.quitter.setBackground(super.getCouleurBoutonQuitter());
    this.quitter.setForeground(Color.WHITE);
    this.quitter.setFocusPainted(false);
    this.quitter.setPreferredSize(new Dimension(240, 50));

    this.sauvegarder = new JButton("SAUVEGARDER");
    this.sauvegarder.setFont(super.getPoliceMontserratRegular());
    this.sauvegarder.setBorderPainted(false);
    this.sauvegarder.setBackground(super.getCouleurBoutonSauvegarder());
    this.sauvegarder.setForeground(Color.WHITE);
    this.sauvegarder.setFocusPainted(false);
    this.sauvegarder.setPreferredSize(new Dimension(240, 50));

    gbc.gridx = 1;      // la plage de cellules commence à la première colonne
    gbc.gridy = 0;      // la plage de cellules commence à la deuxième ligne
    gbc.gridwidth = 1;  // la plage de cellules englobe deux colonnes
    gbc.gridheight = 1; // la plage de cellules englobe une seule ligne
    //gbc.fill = GridBagConstraints.BOTH;     // n'occupe pas tout l'espace de la plage
    gbc.anchor = GridBagConstraints.SOUTH; // se place au centre de la plage
    gbc.weightx = 1.0;  // souhaite plus de largeur si possible
    gbc.weighty = 0;  // n'a pas besoin de hauteur supplémentaire
    gbc.insets = new Insets(35, 0, 25, 0);    // laisse 5 pixels de vide autour du composant
    this.add(this.quitter, gbc);

    gbc.gridx = 0;      // la plage de cellules commence à la première colonne
    gbc.gridy = 0;      // la plage de cellules commence à la deuxième ligne
    gbc.gridwidth = 1;  // la plage de cellules englobe deux colonnes
    gbc.gridheight = 1; // la plage de cellules englobe une seule ligne
    //gbc.fill = GridBagConstraints.BOTH;     // n'occupe pas tout l'espace de la plage
    gbc.anchor = GridBagConstraints.SOUTH; // se place au centre de la plage
    gbc.weightx = 1.0;  // souhaite plus de largeur si possible
    gbc.weighty = 0;  // n'a pas besoin de hauteur supplémentaire
    gbc.insets = new Insets(35, 0, 25, 0);    // laisse 5 pixels de vide autour du composant
    this.add(this.sauvegarder, gbc);
  }

  /**
   * Renvoie le bouton quitter
   *
   * @return quitter
   *                Le bouton quitter
   */
  public JButton getBoutonQuitter(){
    return this.quitter;
  }

  /**
   * Renvoie le bouton sauvegarder
   *
   * @return quitter
   *                Le bouton sauvegarder
   */
  public JButton getBoutonSauvegarder(){
    return this.sauvegarder;
  }
}
