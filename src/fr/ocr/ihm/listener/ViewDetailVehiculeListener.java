package fr.ocr.ihm.listener;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JTable;

import fr.ocr.ihm.VehiculeDetailsDialogBox;

public class ViewDetailVehiculeListener extends ButtonListener {
	private String id;
	private int column, row;
    private JTable table;
    private int nbre = 0;
        
    public void setColumn(int col){this.column = col;}
    public void setRow(int row){this.row = row;}
    public void setTable(JTable table){this.table = table;}
	
	public void actionPerformed(ActionEvent e) {
		/*
		
		Vous devez d�finir comment afficher le d�tail d'un v�hicule
		ceci dans un popup personnalis�e
		
		*/
		System.out.println("details voiture");
		id = (String)table.getValueAt(this.row, this.column-1);
		System.out.println(id);
		VehiculeDetailsDialogBox  box = new VehiculeDetailsDialogBox(null, "Detail du v�hicule", true,id );
	}
}
