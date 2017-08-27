package fr.ocr.ihm.listener;

import java.awt.event.ActionEvent;

import fr.ocr.ihm.VehiculeDetailsDialogBox;

public class ViewDetailVehiculeListener extends ButtonListener {
	private Integer id;

	public void actionPerformed(ActionEvent e) {
		/*
		
		Vous devez définir comment afficher le détail d'un véhicule
		ceci dans un popup personnalisée
		
		*/
		System.out.println("details voiture");
		VehiculeDetailsDialogBox  box = new VehiculeDetailsDialogBox(null, "Detail du véhicule", true);
		
	}
}
