package com.vehicle.garaje;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class GarajeOverview {
	public static JPanel garajeOverview = new JPanel(new FlowLayout());
	public static JDialog overviewDialog;

	static JPanel load() {
		JButton addButton = new JButton("Garaje Overview");
		garajeOverview.add(addButton);
		
		addButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	    JTable table = new JTable();
        	    JTableHeader header = table.getTableHeader();
        	    header.setBackground(Color.black);
        	    header.setForeground(Color.yellow);
        	    JScrollPane scrollPane = new JScrollPane(table); 
        	    Dimension d=new Dimension(450,130);
        	    scrollPane.setPreferredSize(d);
        	    
        	    DefaultTableModel model = new DefaultTableModel(new String[] { "Level", "Parking Lot", "Licencse Plate Number" },0);
        	    table.setModel(model);
        	    for (Map.Entry<Integer,ArrayList<String>> entery : ManageParking.saveVehicle.entrySet()) {
                	for(int i=0;i<entery.getValue().size();i++) {
                		model.addRow(new Object[]{ entery.getKey(), i+1, entery.getValue().get(i)});
                    }
                }
        	    overviewDialog=new JDialog();
        	    overviewDialog.setTitle("Garaje parking plan");
        	    overviewDialog.setSize(460,160);
        	    overviewDialog.setLocationRelativeTo(null);
        	    overviewDialog.setLayout(new FlowLayout());
        	    overviewDialog.add(scrollPane, BorderLayout.CENTER);
        	    overviewDialog.setVisible(true);
        	}
        });
		
		return garajeOverview;
	}
}