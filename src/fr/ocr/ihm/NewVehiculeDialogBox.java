package fr.ocr.ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JTextField;

import fr.ocr.ihm.listener.NewVehiculeDialogBoxCancelListener;
import fr.ocr.ihm.listener.NewVehiculeDialogBoxOkListener;
import fr.ocr.sql.HsqldbConnection;

/**
 * Classe permettant d'afficher une boite de dialogue pour la 
 * saisie d'un nouveau véhicule
 * 
 * @author flkoliv
 */


public class NewVehiculeDialogBox extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3090354837648100435L;
	private JLabel nomLabel, marqueLabel, moteurLabel, prixLabel;
	// private JRadioButton
	private JComboBox<String> marque, moteur;
	private JCheckBox checkBarre, checkClimatisation, checkGPS, checkSieges, checkToit;
	private JTextField nom, prix;
	private JFrame mere;

	public NewVehiculeDialogBox(JFrame parent, String title, boolean modal) {
		// On appelle le constructeur de JDialog correspondant
		super(parent, title, modal);
		// On spécifie une taille
		this.mere = parent;
		this.setSize(560, 370);
		// La position
		this.setLocationRelativeTo(null);
		// La boite ne devra pas être redimensionnable
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.initComponent();
		this.setVisible(true);
		

	}
	boolean[] options;
	public String getPrix() {
		return prix.getText();
	}

	public String getMarque() {
		return marque.getSelectedItem().toString();
	}

	public String getNom()  {
		
		return nom.getText();
	}

	public String getMoteur() {
		return moteur.getSelectedItem().toString();
	}

	public boolean[] getOptions() {
		boolean[] tab = {false, false, false, false, false};
		if (checkBarre.isSelected()) { tab[4] = true; }
		if (checkClimatisation.isSelected()) {tab[1] = true;}
		if (checkGPS.isSelected()) {tab[2] = true;}
		if (checkSieges.isSelected()) {tab[3] = true;}
		if (checkToit.isSelected()) {tab[0] = true;}
		return tab;
		
		
	}
	
	public JFrame getMere() {
		return this.mere;
	}

	private void initComponent() {
		Connection connect = HsqldbConnection.getInstance();
		Statement state;
		
		
		// Le nom
		JPanel panNom = new JPanel();
		panNom.setBackground(Color.white);
		panNom.setPreferredSize(new Dimension(220, 60));
		nom = new JTextField();
		nom.setPreferredSize(new Dimension(100, 25));
		panNom.setBorder(BorderFactory.createTitledBorder("Nom du Véhicule"));
		nomLabel = new JLabel("Saisir un nom :");
		panNom.add(nomLabel);
		panNom.add(nom);

		// La marque
		JPanel panMarque = new JPanel();
		panMarque.setBackground(Color.white);
		panMarque.setPreferredSize(new Dimension(220, 60));
		marque = new JComboBox<String>();
		try {
			state= connect.createStatement();
			String requete = "SELECT nom FROM marque";
			ResultSet result = state.executeQuery(requete);
			while(result.next()) {
				marque.addItem(result.getString(1));
			}
			
		}
		catch (Exception e) {
			
		}
		panMarque.setBorder(BorderFactory.createTitledBorder("Marque du Véhicule"));
		marqueLabel = new JLabel("Marque :");
		panMarque.add(marqueLabel);
		panMarque.add(marque);

		// Type de moteur
		JPanel panMoteur = new JPanel();
		panMoteur.setBackground(Color.white);
		panMoteur.setPreferredSize(new Dimension(440, 60));
		moteur = new JComboBox<String>();
		try {
			state= connect.createStatement();
			String requete = "SELECT * FROM moteur INNER JOIN type_moteur ON moteur.moteur = type_moteur.id";
			ResultSet result = state.executeQuery(requete);
			while(result.next()) {
				moteur.addItem(result.getString(6) + " "+result.getString(2));
			}
			
		}
		catch (Exception e) {
			
		}
		panMoteur.setBorder(BorderFactory.createTitledBorder("Type de moteur du véhicule"));
		moteurLabel = new JLabel("Moteur :");
		panMoteur.add(moteurLabel);
		panMoteur.add(moteur);

		// prix du véhicule
		JPanel panPrix = new JPanel();
		panPrix.setBackground(Color.white);
		panPrix.setPreferredSize(new Dimension(220, 60));
		prix = new JTextField();
		prix.setPreferredSize(new Dimension(100, 25));
		panPrix.setBorder(BorderFactory.createTitledBorder("Prix du Véhicule"));
		prixLabel = new JLabel("Prix :");
		panPrix.add(prixLabel);
		panPrix.add(prix);

		// options
		JPanel panOptions = new JPanel();
		panOptions.setBackground(Color.white);
		panOptions.setPreferredSize(new Dimension(520, 60));
		checkBarre = new JCheckBox("Barre de toit");
		checkClimatisation = new JCheckBox("Climatisation");
		checkGPS = new JCheckBox("GPS");
		checkSieges = new JCheckBox("Sièges chauffants");
		checkToit = new JCheckBox("Toit ouvrant");
		panOptions.setBorder(BorderFactory.createTitledBorder("Options disponibles"));
		panOptions.add(checkBarre);
		panOptions.add(checkClimatisation);
		panOptions.add(checkGPS);
		panOptions.add(checkSieges);
		panOptions.add(checkToit);

		JPanel content = new JPanel();
		content.setBackground(Color.white);
		content.add(panNom);
		content.add(panMarque);
		content.add(panMoteur);
		content.add(panPrix);
		content.add(panOptions);

		JPanel control = new JPanel();
		JButton okBouton = new JButton("OK");
		JButton cancelBouton = new JButton("Annuler");

		okBouton.addActionListener(new NewVehiculeDialogBoxOkListener(this));
		cancelBouton.addActionListener(new NewVehiculeDialogBoxCancelListener(this));

		control.add(okBouton);
		control.add(cancelBouton);

		this.getContentPane().add(content, BorderLayout.CENTER);
		this.getContentPane().add(control, BorderLayout.SOUTH);
		
	}

}
