package fr.ocr.ihm.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import fr.ocr.ihm.NewVehiculeDialogBox;

public class NewVehiculeListener implements ActionListener {

	private JFrame frame;

	public NewVehiculeListener(JFrame f) {
		frame = f;
	}

	public void actionPerformed(ActionEvent e) {
		System.out.println("nouvelle voiture");
		NewVehiculeDialogBox box = new NewVehiculeDialogBox(null, "Ajouter un véhicule", true);
		/*
		 
		 Vous devez définir cette méthode afin d'afficher
		 une popup personnalisée pour ainsi pouvoir créer un nouveau véhicule
		 
		 */
	}
}
