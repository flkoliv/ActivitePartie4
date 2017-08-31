package fr.ocr.ihm.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;


public class NewVehiculeDialogBoxCancelListener implements ActionListener {
	JDialog dialog;
	
	public NewVehiculeDialogBoxCancelListener (JDialog d) {
		dialog = d;
		
	}
	
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		System.out.print("Cancel");
		dialog.dispose();

	}
}
