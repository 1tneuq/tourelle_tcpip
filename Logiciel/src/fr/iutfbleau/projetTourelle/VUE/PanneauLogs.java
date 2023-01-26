package fr.iutfbleau.projetTourelle.VUE;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;

/**
 * <b>PanneauLogs est la classe qui gere le panneau ou sont affichées les 10 dernieres actions de la tourelle</b>
 * <p>
 *
 * @author Quentin LACOMBE
 * @version 1.0
 */
public class PanneauLogs extends GraphPanneau{

  /**
   * Là ou sont affichés les logs
   */
  private JLabel logs;

  /**
   * Le gestionnaire
   */
  private GridBagConstraints gbc;

  /**
   * PanneauLogs.
   * <p>
   * Ce constructeur construit le panneau des logs
   * </p>
   */
  public PanneauLogs(){
    this.setLayout(new GridBagLayout());

    this.gbc = new GridBagConstraints();

    this.logs = new JLabel("Pas encore de mouvement detecté...");

    gbc.gridx = 0;      // la plage de cellules commence à la première colonne
    gbc.gridy = 0;      // la plage de cellules commence à la deuxième ligne
    gbc.gridwidth = 1;  // la plage de cellules englobe deux colonnes
    gbc.gridheight = 1; // la plage de cellules englobe une seule ligne
    gbc.fill = GridBagConstraints.BOTH;     // n'occupe pas tout l'espace de la plage
    gbc.anchor = GridBagConstraints.NORTHWEST; // se place au centre de la plage
    gbc.weightx = 1.0;  // souhaite plus de largeur si possible
    gbc.weighty = 1.0;  // n'a pas besoin de hauteur supplémentaire    // laisse 5 pixels de vide autour du composant
    gbc.insets = new Insets(0, 5, 0, 0);
    this.add(this.logs, gbc);

    this.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, super.getCouleurBordurePanneau()));
  }

  /**
   * Ecrit le contenu d'une file sur un JLabel
   *
   * @param logs
   *             La file des dix dernieres actions de la tourelle
   *
   */
  public void ecrire(LinkedList<String> logs){
    String texte = new String("<html>");
    for(int i=0; i<logs.size(); i++){
      texte = texte + logs.get(i);
      texte = texte + "<br>";
    }
    texte = texte + "</html>";
    this.logs = null;
    this.logs = new JLabel(texte);

    this.removeAll();
    this.repaint();
    this.gbc.gridx = 0;      // la plage de cellules commence à la première colonne
    this.gbc.gridy = 0;      // la plage de cellules commence à la deuxième ligne
    this.gbc.gridwidth = 1;  // la plage de cellules englobe deux colonnes
    this.gbc.gridheight = 1; // la plage de cellules englobe une seule ligne
    this.gbc.fill = GridBagConstraints.BOTH;     // n'occupe pas tout l'espace de la plage
    this.gbc.anchor = GridBagConstraints.NORTHWEST; // se place au centre de la plage
    this.gbc.weightx = 1.0;  // souhaite plus de largeur si possible
    this.gbc.weighty = 1.0;  // n'a pas besoin de hauteur supplémentaire    // laisse 5 pixels de vide autour du composant
    this.gbc.insets = new Insets(0, 5, 0, 0);
    this.add(this.logs, gbc);
    this.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, super.getCouleurBordurePanneau()));
    this.revalidate();
  }
}
