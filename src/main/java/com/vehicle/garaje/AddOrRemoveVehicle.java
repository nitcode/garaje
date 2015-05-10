package com.vehicle.garaje;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddOrRemoveVehicle{
	
	public static JPanel addOrRemoveVehicle = new JPanel(new FlowLayout());
    public static JPanel panel1;
    public static JPanel panel2;
    static JTextField getLicensePlateNumber = new JTextField(20);
    static JComboBox selectLevel = new JComboBox();
    static JLabel parkingLeft = new JLabel();
    static JLabel leftlabel = new JLabel("");
    
	static JPanel load() {
		addOrRemoveVehicle.setLayout(new BoxLayout(addOrRemoveVehicle, BoxLayout.Y_AXIS));
		addOrRemoveVehicle.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED, Color.GRAY, Color.DARK_GRAY), "ADD/REMOVE VEHICLE"));
		panel1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 3,3));
		JLabel licensePlateNumber = new JLabel("License PlateNumber:");
		panel1.add(licensePlateNumber);
        panel1.add(getLicensePlateNumber);
        selectLevel.addItem("Select Level");
        selectLevel.addItem("1");
        panel1.add(selectLevel);
        panel1.add(parkingLeft);
        panel1.add(leftlabel);
        
        selectLevel.addActionListener(new ActionListener() {
        	
        	public void actionPerformed(ActionEvent e) {
        		if(selectLevel.getSelectedIndex()>0) {
        			int totalParkSpace=ManageParking.parkSpace.get(Integer.parseInt(selectLevel.getSelectedItem().toString()));
        			int leftParkSpace=0;
        			if(ManageParking.saveVehicle.containsKey(Integer.parseInt(selectLevel.getSelectedItem().toString()))) {
        				leftParkSpace=ManageParking.saveVehicle.get(Integer.parseInt(selectLevel.getSelectedItem().toString())).size();
        			}
        			int space=totalParkSpace-leftParkSpace;
	        		parkingLeft.setText(String.valueOf(space));
	        		leftlabel.setText("Space left");
        		}
        	}
        });
        
		
		Font textFont = new Font("TimesNewRoman", Font.PLAIN, 22);
        Font textFieldFont = new Font("TimesNewRoman", Font.PLAIN, 20);

        licensePlateNumber.setFont(textFont);
        getLicensePlateNumber.setFont(textFieldFont);
		
        panel2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 3,3));
        
        JButton addButton = new JButton("Add");
        JButton removeButton = new JButton("Remove");
        JButton clearButton = new JButton("Clear");
        
        panel2.add(addButton);
        panel2.add(removeButton);
        panel2.add(clearButton);
        
        addButton.addActionListener(new ActionListener() {
        	
        	public void actionPerformed(ActionEvent e) {
        		
        		String licensePlateNumber = getLicensePlateNumber.getText().trim();
        		// check validity of input
        		if((licensePlateNumber.length() == 0)) {
        			JOptionPane.showMessageDialog((Component) panel2, 
                                                     "Enter license plate number",
                                                     "ERROR",JOptionPane.ERROR_MESSAGE);
        		}
        		else if(selectLevel.getSelectedIndex()==0) {
        			JOptionPane.showMessageDialog((Component) panel2, 
                            "Select level for parking",
                            "ERROR",JOptionPane.ERROR_MESSAGE);
        		}
        		else if(Integer.parseInt(parkingLeft.getText())==0) {
    			JOptionPane.showMessageDialog((Component) panel2, 
                        "Level parking is full",
                        "ERROR",JOptionPane.ERROR_MESSAGE);
        		}
        		else {
        			boolean result = MainWindow.manageParking.vehicleEnter(licensePlateNumber,Integer.parseInt(selectLevel.getSelectedItem().toString()));
        			if(!result) {
        				JOptionPane.showMessageDialog((Component) panel2, 
        						"Duplicate license plate number. As vehicle is already in the garaje. " +
        								"Please try again.",
        								"ERROR", JOptionPane.ERROR_MESSAGE);
        			}
        			else {
        				JOptionPane.showConfirmDialog((Component)
        						panel2,"The vehicle has been added. Add another vehicle to the garaje?",
        						"Add Vehicle",
        						JOptionPane.YES_NO_OPTION);

        				getLicensePlateNumber.setText("");
        				selectLevel.setSelectedIndex(0);
        				parkingLeft.setText("");
        				leftlabel.setText("");
                                                                    
        			}                                      
        		}
        	}
        });
        
        removeButton.addActionListener(new ActionListener() {
        	
        	public void actionPerformed(ActionEvent e) {
        		String licensePlateNumber = getLicensePlateNumber.getText().trim();
        		if((licensePlateNumber.length() == 0)) {
        			JOptionPane.showMessageDialog((Component) panel2, 
        					"Enter license plate number",
        					"ERROR", JOptionPane.ERROR_MESSAGE);
        		}
        		else {
        			boolean result = MainWindow.manageParking.vehicleRemove(licensePlateNumber);
        			if(!result) {
                        JOptionPane.showMessageDialog((Component) panel2, 
        						"Entered license plate number is not in the garaje or invalid. Please try agin.",
        						"ERROR", JOptionPane.ERROR_MESSAGE);
                        getLicensePlateNumber.setText(""); 
        				selectLevel.setSelectedIndex(0);
        				parkingLeft.setText("");
        				leftlabel.setText("");
        			}
        			else {
        				JOptionPane.showConfirmDialog((Component)
        						panel2,"The vehicle has been removed. Do you want to remove another vehicle?", "Remove Vehicle",
        						JOptionPane.YES_NO_OPTION);

        				getLicensePlateNumber.setText(""); 
        				selectLevel.setSelectedIndex(0);
        				parkingLeft.setText("");
        				leftlabel.setText("");
        			}                                       
        		}
        	}
        });
        
        clearButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		getLicensePlateNumber.setText("");
				selectLevel.setSelectedIndex(0);
				parkingLeft.setText("");
				leftlabel.setText("");
        	}
        });
        
        addOrRemoveVehicle.add(panel1);
        addOrRemoveVehicle.add(panel2);
        
        return addOrRemoveVehicle;
    }
}
