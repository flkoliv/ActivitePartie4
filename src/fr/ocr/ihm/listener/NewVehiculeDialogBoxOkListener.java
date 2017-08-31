package fr.ocr.ihm.listener;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import fr.ocr.ihm.NewVehiculeDialogBox;

public class NewVehiculeDialogBoxOkListener implements ActionListener{

	NewVehiculeDialogBox dialog;
	
	public NewVehiculeDialogBoxOkListener(JDialog d) {
		dialog = (NewVehiculeDialogBox) d;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent event) {
		Double prix;
		String marque, nom, moteur;
		boolean[] options ; 
		boolean erreur = false;
		
		try {
			prix = new Double(dialog.getPrix());
			if (prix<0) throw new Exception("Prix positif");
		}
		catch (Exception e) {
			//JOptionPane jop2 = new JOptionPane();
			JOptionPane.showMessageDialog(null, "Veuillez renseigner un prix correct !", "Attention", JOptionPane.WARNING_MESSAGE);
			erreur = true;
		}
		
		if (dialog.getNom().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Veuillez renseigner un nom !", "Attention", JOptionPane.WARNING_MESSAGE);
			 erreur=true;
		}
		nom = dialog.getNom();
		marque = dialog.getMarque();
		moteur = dialog.getMoteur();
		options = dialog.getOptions();
		if (!erreur) {
			// remplir la base de donnée
			
			dialog.dispose();
		}
		
	}

}
