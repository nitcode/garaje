package com.vehicle.garaje;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import java.awt.*;

import javax.swing.JFrame;


public class MainWindow extends JFrame{
	
	public static JPanel garajeParking = new JPanel();
	public static ManageParking manageParking;
	
	public MainWindow() {
		this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Garaje Parking");
        this.setResizable(false);
        this.setSize(1150, 500);    
        
        garajeParking.setLayout(null);
        garajeParking.setBorder( new TitledBorder(""));
        garajeParking.setBorder(new LineBorder(Color.black, 3));
        JLabel setTitle=new JLabel("PARK YOUR VEHICLE SMARTLY");
        setTitle.setFont(new Font("Serif", Font.BOLD, 22));
        garajeParking.add(setTitle);
        final JPanel addOrRemoveVehicle = AddOrRemoveVehicle.load();
        garajeParking.add(addOrRemoveVehicle);
        final JPanel garajeStatus = GarajeStatus.load();
        garajeParking.add(garajeStatus);
        final JPanel modifyGarajeLevel = ModifyLevelGaraje.load();
        garajeParking.add(modifyGarajeLevel);
        final JPanel modifyParkingSize = ModifyParkingSize.load();
        garajeParking.add(modifyParkingSize);
        final JPanel garajeOverview = GarajeOverview.load();
        garajeParking.add(garajeOverview);
        garajeParking.getComponent(0).setBounds(400,0, 500, 55);
        garajeParking.getComponent(1).setBounds(20,38, 1100, 100);
        garajeParking.getComponent(2).setBounds(20,160, 1100, 100);
        garajeParking.getComponent(3).setBounds(20,282, 500, 170);
        garajeParking.getComponent(4).setBounds(530,282, 580, 100);
        garajeParking.getComponent(5).setBounds(720,400, 170, 50);
        garajeParking.setBounds(300, 200, 750, 220); 
        
        manageParking = new ManageParking(2,1);
        
        this.getContentPane().add(garajeParking);
	}
	
	public static void main(String args[]) {
		MainWindow main = new MainWindow();
        main.setVisible(true);
	  }
}