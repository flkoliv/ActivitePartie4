package fr.ocr.ihm.listener;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import fr.ocr.ihm.NewVehiculeDialogBox;

public class NewVehiculeDialogBoxOkListener implements ActionListener{

	NewVehiculeDialogBox dialog;
	
	public NewVehiculeDialogBoxOkListener(JDialog d) {
		dialog = (NewVehiculeDialogBox) d;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		System.out.println(dialog.getPrix());
		System.out.println(dialog.getMarque());
		System.out.println(dialog.getNom());
		System.out.println(dialog.getMoteur());
		System.out.println(dialog.getOptions());
		for (int i = 0; i<dialog.getOptions().length;i++) {
			if (dialog.getOptions()[i]) System.out.print(" 1 ");
			else System.out.print(" 0 ");
		
		}
		dialog.dispose();
	}

}
