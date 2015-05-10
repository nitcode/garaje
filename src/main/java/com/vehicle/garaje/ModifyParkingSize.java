package com.vehicle.garaje;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;

public class ModifyParkingSize {

	public static JPanel modifParkingPanel = new JPanel(new FlowLayout());
    public static JPanel panel1;
    public static JPanel panel2;
    static JTextField getParking= new JTextField(5);
    static JComboBox selectLevel = new JComboBox();
    static JLabel parkingLeft = new JLabel();
    static JLabel leftlabel = new JLabel("");

	static JPanel load() {
		modifParkingPanel.setLayout(new BoxLayout(modifParkingPanel, BoxLayout.Y_AXIS));
		modifParkingPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED, Color.GRAY, Color.DARK_GRAY), "RESIZE PARKING SPACE"));
		panel1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 3,3));
		Font textFont = new Font("TimesNewRoman", Font.PLAIN, 22);
		Font textFieldFont = new Font("TimesNewRoman", Font.PLAIN, 20);
		
		JLabel parkingLabel = new JLabel("Add Parking Space:");
		panel1.add(parkingLabel);
		panel1.add(getParking);
		KeyListener keyListener = new KeyListener() {
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				char c=e.getKeyChar();
				if(!(Character.isDigit(c) || (c==KeyEvent.VK_BACK_SPACE) || (c==KeyEvent.VK_DELETE))) {
					e.consume();
				}		
			}
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub	
			}
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub	
			}
		      
		};
		getParking.addKeyListener(keyListener);
		
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
		
		parkingLabel.setFont(textFont);
		getParking.setFont(textFieldFont);
		
		panel2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 3,3));
        
		JButton insertButton = new JButton("Insert");
		JButton clearButton = new JButton("Clear");
        
		panel2.add(insertButton);
		panel2.add(clearButton);
       
		insertButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(selectLevel.getSelectedIndex()==0) {
        			JOptionPane.showMessageDialog((Component) panel2, 
                            "Select level for parking",
                            "ERROR",JOptionPane.ERROR_MESSAGE);
        		}
				else if(!getParking.getText().equals("")) {
        			int parkSpace=ManageParking.parkSpace.get(selectLevel.getSelectedIndex());
        			ManageParking.parkSpace.put(selectLevel.getSelectedIndex(), Integer.parseInt(getParking.getText())+parkSpace);
        			getParking.setText("");
    				selectLevel.setSelectedIndex(0);
    				parkingLeft.setText("");
    				leftlabel.setText("");
        		}
        		else {
        			JOptionPane.showMessageDialog((Component) panel2, 
                            "Enter Parking Space",
                            "ERROR",JOptionPane.ERROR_MESSAGE);
        		}
        	}
        });
		
		clearButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		getParking.setText("");
				selectLevel.setSelectedIndex(0);
				parkingLeft.setText("");
				leftlabel.setText("");
        	}
        });
       
       modifParkingPanel.add(panel1);
       modifParkingPanel.add(panel2);
        
       return modifParkingPanel;
	}
}