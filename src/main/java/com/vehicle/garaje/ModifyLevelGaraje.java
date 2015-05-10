package com.vehicle.garaje;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.NumberFormat;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.text.NumberFormatter;

public class ModifyLevelGaraje {

	public static JPanel modifyLevelPanel = new JPanel(new FlowLayout());
    public static JPanel panel1;
    public static JPanel panel2;
    public static JPanel panel3;
    static JTextField getLevel = new JTextField(10);
    static JTextField getParkingSize = new JTextField(10);
    
	static JPanel load() {
		modifyLevelPanel.setLayout(new BoxLayout(modifyLevelPanel, BoxLayout.Y_AXIS));
		modifyLevelPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED, Color.GRAY, Color.DARK_GRAY), "RESIZE GARAJE LEVEL"));
		Font textFont = new Font("TimesNewRoman", Font.PLAIN, 22);
        Font textFieldFont = new Font("TimesNewRoman", Font.PLAIN, 20);
		
		panel1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 3,3));
		JLabel levelLabel = new JLabel("Add Level To Garaje:");
		panel1.add(levelLabel);
		panel1.add(getLevel);
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
		getLevel.addKeyListener(keyListener);
		
        levelLabel.setFont(textFont);
        getLevel.setFont(textFieldFont);
		
        panel2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 3,3));
        JLabel parkingSizeLabel = new JLabel("Add Parking Space  :");
		panel2.add(parkingSizeLabel);
		panel2.add(getParkingSize);
		getParkingSize.addKeyListener(keyListener);
		
		parkingSizeLabel.setFont(textFont);
		getParkingSize.setFont(textFieldFont);
        
        panel3 = new JPanel(new FlowLayout(FlowLayout.CENTER, 3,3));
        JButton insertButton = new JButton("Insert");
        JButton clearButton = new JButton("Clear");
        panel3.add(insertButton);
        panel3.add(clearButton);
        
        insertButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String levelText = getLevel.getText().trim();
        		String parkingText = getParkingSize.getText().trim();
        		if((levelText.length() == 0)) {
        			JOptionPane.showMessageDialog((Component) panel2, 
                                                     "Enter Level",
                                                     "ERROR",JOptionPane.ERROR_MESSAGE);
        		}
        		else if(parkingText.length() == 0) {
        			JOptionPane.showMessageDialog((Component) panel2, 
                            "Enter Parking",
                            "ERROR",JOptionPane.ERROR_MESSAGE);
        		}
        		else {
        			int level = Integer.parseInt(getLevel.getText().trim());
            		int parkingSize = Integer.parseInt(getParkingSize.getText().trim());
            		boolean result = MainWindow.manageParking.insertLevel(level,parkingSize);
            		if(!result) {
        				JOptionPane.showMessageDialog((Component) panel2, 
        								"Level already exist.",
        								"ERROR", JOptionPane.ERROR_MESSAGE);
        				getLevel.setText("");
        				getParkingSize.setText("");
        			}
        			else {
        				AddOrRemoveVehicle.selectLevel.addItem(level);
        				ModifyParkingSize.selectLevel.addItem(level);
        				JOptionPane.showMessageDialog((Component)
        						panel2,"Level added",
        						"Modify Garaje",
        						JOptionPane.INFORMATION_MESSAGE);
        				
        				getLevel.setText("");
        				getParkingSize.setText("");                                               
        			} 
        		}
        	}
        });
        
        clearButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		getLevel.setText("");
        		getParkingSize.setText("");
        	}
        });
   
        modifyLevelPanel.add(panel1);
        modifyLevelPanel.add(panel2);
        modifyLevelPanel.add(panel3);
        
        return modifyLevelPanel;
	}
}