package fr.iutfbleau.projetTourelle.VUE;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;

/**
 * <b>PanneauVideo est le panneau ou de qui est filmé par la caméra est retranscrit</b>
 * <p>
 *
 * @author Quentin LACOMBE
 * @version 1.0
 */
public class PanneauVideo extends GraphPanneau{

  /**
   * Le gestionnaire
   */
  private GridBagConstraints gbc;

  /**
   * PanneauVideo.
   * <p>
   * Ce constructeur construit le panneau de la video
   * </p>
   */
  public PanneauVideo(){
    this.setLayout(new GridBagLayout());

    this.gbc = new GridBagConstraints();

    /*gbc.gridx = 0;      // la plage de cellules commence à la première colonne
    gbc.gridy = 0;      // la plage de cellules commence à la deuxième ligne
    gbc.gridwidth = 1;  // la plage de cellules englobe deux colonnes
    gbc.gridheight = 1; // la plage de cellules englobe une seule ligne
    gbc.fill = GridBagConstraints.BOTH;     // n'occupe pas tout l'espace de la plage
    gbc.anchor = GridBagConstraints.NORTHWEST; // se place au centre de la plage
    gbc.weightx = 1.0;  // souhaite plus de largeur si possible
    gbc.weighty = 1.0;  // n'a pas besoin de hauteur supplémentaire    // laisse 5 pixels de vide autour du composant
    gbc.insets = new Insets(0, 5, 0, 0);
    this.add(, gbc);*/

    this.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, super.getCouleurBordurePanneau()));
  }

}
