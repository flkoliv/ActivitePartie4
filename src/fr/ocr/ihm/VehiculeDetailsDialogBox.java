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
import javax.swing.JTextArea;

import fr.ocr.sql.HsqldbConnection;

/**
 * Classe permettant d'afficher une boite de dialogue pour les details du véhicule
 *
 * 
 * @author flkoliv
 */



public class VehiculeDetailsDialogBox extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5569032811733161877L;
	String id = new String();
	/**
	 * 
	 */

	private JLabel nomLabel, marqueLabel, moteurLabel, prixLabel, prixTotalLabel;
	JTextArea optionText;

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
			String requete = "SELECT * FROM vehicule ";
			requete += "INNER JOIN marque ON vehicule.marque = marque.id ";
			requete += "INNER JOIN moteur ON vehicule.moteur = moteur.id ";
			requete += "INNER JOIN type_moteur ON moteur.moteur = type_moteur.id ";
			requete += "WHERE vehicule.id = " + id;
			ResultSet result = state.executeQuery(requete);
			result.next();
			nomLabel = new JLabel(result.getString(4));
			prixLabel = new JLabel("Prix sans options : " + result.getString(3) + "€");
			prixTotal += result.getDouble(3);
			marqueLabel = new JLabel(result.getString(7));
			moteurLabel = new JLabel(result.getString(13) + " " + result.getString(9));
			requete = "SELECT description,prix FROM vehicule_option ";
			requete += "INNER JOIN option ON vehicule_option.id_option = option.id ";
			requete += "WHERE id_vehicule=" + id;
			result = state.executeQuery(requete);
			String options = new String();
			while (result.next()) {
				options += result.getString(1) + "(" + result.getString(2) + "€) ";
				prixTotal += result.getDouble(2);
			}

			optionText = new JTextArea(options);

			prixTotalLabel = new JLabel(prixTotal.toString() + "€");

		} catch (SQLException e) {

			e.printStackTrace();
		}

		// Le nom
		JPanel panNom = new JPanel();
		panNom.setBackground(Color.white);
		panNom.setPreferredSize(new Dimension(220, 60));
		panNom.setBorder(BorderFactory.createTitledBorder("Nom du Véhicule"));
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
		panMoteur.add(moteurLabel);

		// prix du v�hicule
		JPanel panPrix = new JPanel();
		panPrix.setBackground(Color.white);
		panPrix.setPreferredSize(new Dimension(220, 60));
		panPrix.setBorder(BorderFactory.createTitledBorder("Prix du Véhicule"));
		panPrix.add(prixLabel);

		// options
		JPanel panOptions = new JPanel();
		panOptions.setBackground(Color.white);
		panOptions.setPreferredSize(new Dimension(520, 70));
		panOptions.setBorder(BorderFactory.createTitledBorder("Options disponibles"));
		optionText.setEditable(false);
		optionText.setLineWrap(true);
		optionText.setWrapStyleWord(true);
		optionText.setPreferredSize(new Dimension(500, 40));
		panOptions.add(optionText);

		// prix total du véhicule
		JPanel panPrixTotal = new JPanel();
		panPrixTotal.setBackground(Color.green);
		panPrixTotal.setPreferredSize(new Dimension(330, 60));
		panPrixTotal.setBorder(BorderFactory.createTitledBorder("Prix Total du Véhicule"));
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
