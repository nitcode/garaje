package com.vehicle.garaje;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

public class GarajeStatus {
	
	public static JPanel garajeStatusPanel = new JPanel(new FlowLayout());
    public static JPanel panel1;
    public static JPanel panel2;
    static JTextField getLicensePlateNumber = new JTextField(20);

	static JPanel load() {
		garajeStatusPanel.setLayout(new BoxLayout(garajeStatusPanel, BoxLayout.Y_AXIS));
		garajeStatusPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED, Color.GRAY, Color.DARK_GRAY), "GARAJE STATUS"));
		panel1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 3,3));
		JLabel licensePlateName = new JLabel("License PlateNumber:");
		panel1.add(licensePlateName);
        panel1.add(getLicensePlateNumber);
		
		Font textFont = new Font("TimesNewRoman", Font.PLAIN, 22);
        Font textFieldFont = new Font("TimesNewRoman", Font.PLAIN, 20);

        licensePlateName.setFont(textFont);
        getLicensePlateNumber.setFont(textFieldFont);
		
        panel2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 3,3));
        
        JButton statusButton = new JButton("Garaje Status");
        JButton locateButton = new JButton("Locate");
        JButton clearButton = new JButton("Clear");
        
        panel2.add(statusButton);
        panel2.add(locateButton);
        panel2.add(clearButton);
        
        statusButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {    
             @SuppressWarnings("unused")
             String licensePlate = getLicensePlateNumber.getText().trim();
       		 StringBuilder sb = new StringBuilder();
       		 for(Object key: MainWindow.manageParking.getParking().keySet()) {
       			 
       			 int totalParkSpace=ManageParking.parkSpace.get(key);
       			 int leftParkSpace=0;
       			 if(ManageParking.saveVehicle.containsKey(key)) {
       				 
       				 leftParkSpace=ManageParking.saveVehicle.get(key).size();
       			 }
       			 int space=totalParkSpace-leftParkSpace;
       			 sb.append("Level- "+ key + ", Parking Space- " + MainWindow.manageParking.getParking().get(key) + ", Space Left- " + space);
       			 sb.append("\n");
       		 }       		 
       		 JOptionPane.showMessageDialog((Component) panel2, 
                            "\n" + sb,
                            "Current Garaje Statistics",  JOptionPane.INFORMATION_MESSAGE);
                            
       		 getLicensePlateNumber.setText("");                                          
       	 }
        });
        
        locateButton.addActionListener(new ActionListener() {
        	
       	 	public void actionPerformed(ActionEvent e) {
       	 		String licensePlate = getLicensePlateNumber.getText().trim();
                String licensePlateNumber = MainWindow.manageParking.findVehicleLocation(licensePlate);
                if(!licensePlateNumber.equals("Level- , Lot- ")) {
                	JOptionPane.showMessageDialog((Component) panel2, 
                			"Location of car #" + licensePlate + ":" +
                					"\n" + licensePlateNumber,
                					"Car Location Found", JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                	JOptionPane.showMessageDialog((Component) panel2, 
                			"Location of car #" + licensePlate + ":" 
                					+ "Could not be found." + 
                            		"\nThe vehicle is either not registered or not currently parked.",
                            		"ERROR", JOptionPane.ERROR_MESSAGE);
                }
                getLicensePlateNumber.setText("");                                          
       	 	}
        });
        
        clearButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		getLicensePlateNumber.setText("");
        	}
        });
        
        garajeStatusPanel.add(panel1);
        garajeStatusPanel.add(panel2);
        
        return garajeStatusPanel;
	}
}