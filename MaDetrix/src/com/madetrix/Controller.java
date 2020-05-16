package com.madetrix;

import javax.swing.UIManager;
import java.awt.EventQueue;

public class Controller{
   public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					MatrixModel matrixModel = new MatrixModel();
					MaDetrix frame = new MaDetrix(matrixModel);
					frame.setResizable(false);
					frame.setVisible(true);				
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}