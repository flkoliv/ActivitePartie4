package fr.ocr.ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.ocr.sql.HsqldbConnection;

public class VehiculeDetailsDialogBox extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5569032811733161877L;
	String id = new String();
	/**
	 * 
	 */

	private JLabel nomLabel, marqueLabel, moteurLabel, prixLabel, optionLabel, prixTotalLabel;

	public VehiculeDetailsDialogBox(JFrame parent, String title, boolean modal, String id) {

		super(parent, title, modal);

		this.setSize(560, 370);

		this.setLocationRelativeTo(null);
		this.id = id;
		this.setResizable(false);

		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.initComponent();
		this.setVisible(true);

	}

	private void initComponent() {

		Double prixTotal = 0d;
		Connection connect = HsqldbConnection.getInstance();
		Statement state;
		try {
			state = connect.createStatement();
			ResultSet result = state.executeQuery("SELECT * FROM vehicule WHERE id=" + id);
			// ResultSetMetaData resultMeta = result.getMetaData();
			result.next();
			String marqueId = new String(result.getString(1));
			String moteurId = new String(result.getString(2));
			nomLabel = new JLabel(result.getString(4));
			prixLabel = new JLabel("Prix sans options : " + result.getString(3) + "€");
			prixTotal += result.getDouble(3);
			result = state.executeQuery("SELECT * FROM marque WHERE id=" + marqueId);
			result.next();
			marqueLabel = new JLabel(result.getString(2));
			result = state.executeQuery("SELECT * FROM moteur WHERE id=" + moteurId);
			result.next();
			String cylindre = result.getString(2);
			String idTypeMoteur = result.getString(3);
			result = state.executeQuery("SELECT * FROM type_moteur WHERE id=" + idTypeMoteur);
			result.next();
			moteurLabel = new JLabel(result.getString(2) + " " + cylindre);
			result = state.executeQuery("SELECT * FROM vehicule_option WHERE id_vehicule=" + id);

			String options = new String();
			ResultSet resultat;
			while (result.next()) {

				resultat = state.executeQuery("SELECT * FROM option WHERE id=" + result.getString(2));
				resultat.next();
				System.out.println(resultat.getString(2));
				options += resultat.getString(2) + "(" + resultat.getString(3) + "€) ";
				prixTotal += resultat.getDouble(3);

			}

			optionLabel = new JLabel(options);
			prixTotalLabel = new JLabel(prixTotal.toString() + "€");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Le nom
		JPanel panNom = new JPanel();
		panNom.setBackground(Color.white);
		panNom.setPreferredSize(new Dimension(220, 60));
		panNom.setBorder(BorderFactory.createTitledBorder("Nom du Véhicule"));
		// nomLabel = new JLabel("LAGS");
		panNom.add(nomLabel);

		// La marque
		JPanel panMarque = new JPanel();
		panMarque.setBackground(Color.white);
		panMarque.setPreferredSize(new Dimension(220, 60));
		panMarque.setBorder(BorderFactory.createTitledBorder("Marque du Véhicule"));

		panMarque.add(marqueLabel);

		// Type de moteur
		JPanel panMoteur = new JPanel();
		panMoteur.setBackground(Color.white);
		panMoteur.setPreferredSize(new Dimension(440, 60));
		panMoteur.setBorder(BorderFactory.createTitledBorder("Type de moteur du véhicule"));
		// moteurLabel = new JLabel("DIESEL 250 Hdi");
		panMoteur.add(moteurLabel);

		// prix du véhicule
		JPanel panPrix = new JPanel();
		panPrix.setBackground(Color.white);
		panPrix.setPreferredSize(new Dimension(220, 60));
		panPrix.setBorder(BorderFactory.createTitledBorder("Prix du Véhicule"));
		// prixLabel = new JLabel("Prix sans options : 10000.0€");
		panPrix.add(prixLabel);

		// options
		JPanel panOptions = new JPanel();
		panOptions.setBackground(Color.white);
		panOptions.setPreferredSize(new Dimension(520, 60));
		panOptions.setBorder(BorderFactory.createTitledBorder("Options disponibles"));
		// optionLabel = new JLabel("Toit ouvrant(150€)");
		panOptions.add(optionLabel);

		// prix total du véhicule
		JPanel panPrixTotal = new JPanel();
		panPrixTotal.setBackground(Color.green);
		panPrixTotal.setPreferredSize(new Dimension(330, 60));
		panPrixTotal.setBorder(BorderFactory.createTitledBorder("Prix Total du Véhicule"));
		// prixTotalLabel = new JLabel("15034.0€");
		panPrixTotal.add(prixTotalLabel);

		JPanel content = new JPanel();
		content.setBackground(Color.white);
		content.add(panNom);
		content.add(panMarque);
		content.add(panMoteur);
		content.add(panPrix);
		content.add(panOptions);
		content.add(panPrixTotal);

		this.getContentPane().add(content, BorderLayout.CENTER);

	}

}
