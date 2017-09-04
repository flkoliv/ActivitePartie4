package fr.ocr.ihm.listener;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import fr.ocr.ihm.NewVehiculeDialogBox;
import fr.ocr.sql.DAOTableFactory;
import fr.ocr.sql.DatabaseTable;
import fr.ocr.sql.HsqldbConnection;

/**
 * Classe permettant d'écouter le bouton OK de la boite nouveau véhicule
 *
 * 
 * @author flkoliv
 */


public class NewVehiculeDialogBoxOkListener implements ActionListener {

	NewVehiculeDialogBox dialog;

	public NewVehiculeDialogBoxOkListener(JDialog d) {
		dialog = (NewVehiculeDialogBox) d;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		Double prix;
		String nom, marque, moteur;
		boolean[] options;
		boolean erreur = false;
		try {
			prix = new Double(dialog.getPrix());
			if (prix < 0)
				throw new Exception("Prix positif");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Veuillez renseigner un prix correct !", "Attention",
					JOptionPane.WARNING_MESSAGE);
			erreur = true;
		}
		if (dialog.getNom().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Veuillez renseigner un nom !", "Attention",
					JOptionPane.WARNING_MESSAGE);
			erreur = true;
		}
		nom = dialog.getNom();
		moteur = dialog.getMoteur();
		options = dialog.getOptions();
		marque = dialog.getMarque();
		prix = Double.parseDouble(dialog.getPrix());
		if (!erreur) {
			remplirBDD(nom, marque, moteur, prix, options);
			JFrame frame = (JFrame) dialog.getParent();
			frame.getContentPane().removeAll();
			frame.getContentPane().add(
					new JScrollPane(DAOTableFactory.getTable(
							HsqldbConnection.getInstance(), DatabaseTable.VEHICULE)),
					BorderLayout.CENTER);
			frame.getContentPane().revalidate();
			dialog.dispose();
		}
	}

	private void remplirBDD(String nom, String marque, String moteur, Double prix, boolean[] options) {
		Connection connect = HsqldbConnection.getInstance();
		Statement state;
		int i = 0;
		do {
			i++;
		} while (moteur.charAt(i) != ' ');
		moteur = moteur.substring(i + 1);
		try {
			state = connect.createStatement();
			String requete = "SELECT MAX(id) FROM vehicule";
			ResultSet result = state.executeQuery(requete);
			result.next();
			int id = result.getInt(1) + 1;
			requete = "SELECT id FROM moteur WHERE cylindre='" + moteur + "'";
			result = state.executeQuery(requete);
			result.next();
			int idMoteur = result.getInt(1);
			requete = "SELECT id FROM marque WHERE nom='" + marque + "'";
			result = state.executeQuery(requete);
			result.next();
			int idMarque = result.getInt(1);
			requete = "INSERT INTO vehicule VALUES(" + idMarque + "," + idMoteur + "," + prix + ",'" + nom + "'," + id
					+ ")";
			result = state.executeQuery(requete);
			for (int j = 0; j < options.length; j++) {
				if (options[j]) {
					requete = "INSERT INTO vehicule_option VALUES(" + id + "," + j + ")";
					result = state.executeQuery(requete);
					
				}
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
