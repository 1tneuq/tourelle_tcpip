package fr.iutfbleau.projetTourelle.VUE;

import javax.swing.*;
import java.awt.*;
import java.io.*;

/**
 * <b>PanneauConnexion est la classe qui gere la fenetre de connexion</b>
 * <p>
 * Cette classe comprend un bouton pour initier la la connexion et les deux champs pour adresse ip et port tcp
 *
 * @author Quentin LACOMBE
 * @version 1.0
 */
public class PanneauConnexion extends GraphPanneau{

  /**
   * Bouton de connexion
   */
  private JButton connexion;

  /**
   * Champs pour l'adresse ip
   */
  private JTextField adresseServeur;

  /**
   * Champs pour le port tcp
   */
  private JTextField portServeur;

  /**
   * PanneauConnexion.
   * <p>
   * Ce constructeur construit le panneau de connexion dans lequel on va rentrer
   * les informations de conexion à la tourelle
   * Il initialise le panneau et tous ses elements
   * </p>
   */
  public PanneauConnexion(){
    this.setLayout(new GridBagLayout());

    this.adresseServeur = new JTextField("Adresse ip du serveur");
    adresseServeur.setFont(super.getPoliceMontserratRegular());
    adresseServeur.setBackground(super.getCouleurFond());
    adresseServeur.setForeground(super.getCouleurTexte());
    adresseServeur.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(220,220, 220)));

    this.portServeur = new JTextField("Port TCP du serveur");
    portServeur.setFont(super.getPoliceMontserratRegular());
    portServeur.setBackground(super.getCouleurFond());
    portServeur.setForeground(super.getCouleurTexte());
    portServeur.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(220,220, 220)));

    this.connexion = new JButton("CONNEXION");
    connexion.setFont(super.getPoliceMontserratRegular());
    connexion.setBorderPainted(false);
    connexion.setBackground(super.getCouleurBoutonSauvegarder());
    connexion.setForeground(Color.WHITE);
    connexion.setFocusPainted(false);
    connexion.setPreferredSize(new Dimension(280, 60));

    //Recherche
    JLabel recherche = new JLabel("INITIER LA CONNEXION AVEC LA TOURELLE");
    recherche.setFont(super.getPoliceMontserratRegular());
    recherche.setFont(recherche.getFont().deriveFont(recherche.getFont().getSize() * 1.4F));

    GridBagConstraints gbc = new GridBagConstraints();

    gbc.gridx = 1;
    gbc.gridy = 0;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.NORTH;
    gbc.weightx = 0;
    gbc.weighty = 1.0;
    gbc.insets = new Insets(50, 0, 0, 0);
    recherche.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    this.add(recherche, gbc);

    gbc.gridx = 0;
    gbc.gridy = 3;
    gbc.gridwidth = 3;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.NORTH;
    gbc.weightx = 1.0;
    gbc.weighty = 1.0;
    gbc.insets = new Insets(0, 50, 0, 50);
    this.add(adresseServeur, gbc);

    gbc.gridx = 0;
    gbc.gridy = 4;
    gbc.gridwidth = 3;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.NORTH;
    gbc.weightx = 1.0;
    gbc.weighty = 1.0;
    gbc.insets = new Insets(0, 50, 0, 50);
    this.add(portServeur, gbc);

    gbc.gridx = 1;
    gbc.gridy = 5;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.NONE;
    gbc.anchor = GridBagConstraints.NORTH;
    gbc.weightx = 1.0;
    gbc.weighty = 1.0;
    gbc.insets = new Insets(0, 0, 0, 0);
    this.add(connexion, gbc);

    //this.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, super.getCouleurBordurePanneau()));
  }


  /**
   * Retourne le champs de l'adresse IP
   *
   * @return adresseServeur
   *                        Le champs dans lequel on l'adresse IP du serveur (la tourelle)
   *
   */
  public JTextField getAdresseServeur(){
    return this.adresseServeur;
  }

  /**
   * Retourne le champs du port du serveur
   *
   * @return portServeur
   *                    Le champs dans lequel on rentre le port de la tourelle
   *
   */
  public JTextField getPortServeur(){
    return this.portServeur;
  }

  /**
   * Retourne le bouton "Connexion"
   *
   * @return connexion
   *                  Le bouton vert pour lancer la connexion
   *
   */
  public JButton getBoutonConnexion(){
    return this.connexion;
  }
}
