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
		NewVehiculeDialogBox box = new NewVehiculeDialogBox(null, "Ajouter un v�hicule", true);
		/*
		 
		 Vous devez d�finir cette m�thode afin d'afficher
		 une popup personnalis�e pour ainsi pouvoir cr�er un nouveau v�hicule
		 
		 */
	}
}
