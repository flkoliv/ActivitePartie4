package fr.ocr.ihm.listener;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import fr.ocr.sql.DAOTableFactory;
import fr.ocr.sql.DatabaseTable;
import fr.ocr.sql.HsqldbConnection;

//Notre listener pour le bouton
public class ButtonListener implements ActionListener {
	protected int column, row, id;
	protected JTable table;
	protected JFrame frame;

	
	
	
	public void setColumn(int col) {
		this.column = col;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public void actionPerformed(ActionEvent event) {
		/*
		
		Ici, il vous faut d�finir comment supprimer un véhicule
		et n'oubliez pas de supprimer toutes les options de celui-ci...
		
		*/
		String id = (String)table.getValueAt(this.row, this.column-2);
		Connection connect = HsqldbConnection.getInstance();
		Statement state;
		try {
			state = connect.createStatement();
			String requete = "DELETE FROM vehicule WHERE id="+id;
			System.out.println(requete);
			String request = "DELETE FROM vehicule_option WHERE id_vehicule="+id;
			System.out.println(request);
			ResultSet result = state.executeQuery(request);
			result = state.executeQuery(requete);
			
			
			
			
			
		}
		catch (Exception e) {
			System.out.println(e);
		}
		JFrame frame = (JFrame)(this.table.getParent().getParent().getParent().getParent().getParent().getParent());
		System.out.println(frame);
		frame.getContentPane().removeAll();
		frame.getContentPane().add(
				new JScrollPane(DAOTableFactory.getTable(
						HsqldbConnection.getInstance(), DatabaseTable.VEHICULE)),
				BorderLayout.CENTER);
		frame.getContentPane().revalidate();
		
		
	}
}