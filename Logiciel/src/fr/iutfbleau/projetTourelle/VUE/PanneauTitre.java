package fr.iutfbleau.projetTourelle.VUE;

import javax.swing.*;
import java.awt.*;
import java.io.*;

/**
 * <b>PanneauTitre est la classe qui gere le titre de l'application</b>
 * <p>
 *
 * @author Quentin LACOMBE
 * @version 1.0
 */
public class PanneauTitre extends GraphPanneau{

  /**
   * Le titre
   */
  private JLabel titre;

  /**
   * PanneauTitre.
   * <p>
   * Ce constructeur construit le panneau du titre
   * </p>
   */
  public PanneauTitre(){
    this.titre = new JLabel("CONTROLES: ^, v, >, <, |__|");
    this.titre.setFont(super.getPoliceMontserratRegular());
    this.titre.setFont(this.titre.getFont().deriveFont(this.titre.getFont().getSize() * 1.8F));
    this.titre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

    this.add(this.titre);
  }
}
