package fr.ocr.ihm.listener;

import java.awt.event.ActionEvent;

import fr.ocr.ihm.VehiculeDetailsDialogBox;

public class ViewDetailVehiculeListener extends ButtonListener {
	private Integer id;

	public void actionPerformed(ActionEvent e) {
		/*
		
		Vous devez d�finir comment afficher le d�tail d'un v�hicule
		ceci dans un popup personnalis�e
		
		*/
		System.out.println("details voiture");
		VehiculeDetailsDialogBox  box = new VehiculeDetailsDialogBox(null, "Detail du v�hicule", true);
		
	}
}
