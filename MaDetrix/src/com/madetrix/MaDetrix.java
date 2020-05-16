package com.madetrix;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.Color;
import javax.swing.border.MatteBorder;
import javax.swing.JSeparator;

import javax.swing.UIManager;
import java.awt.SystemColor;

public class MaDetrix extends JFrame {
	private JPanel frame;
	private JTextField A_multiplyWithField;
	private JTextField A_raiseToPowerField;
	private JButton A_RaiseToPower;
	private JButton BxA_Button;
	private JButton AxB_Button;
	private JButton AplusB_Button;
	private JButton AminusB_Button;
	private JSpinner matrixARows;
	private JSpinner matrixAColumns;
	private JSpinner matrixBRows;
	private JSpinner matrixBColumns;
	private JTextField[][] matrixA;
	private JTextField[][] matrixB;
	private JTextField B_multiplyWithField;
	private JTextField B_raiseToPowerField;
	private MatrixModel model;
	private JLabel[][] A_result;
	private JLabel[][] AB_result;
	private JLabel[][] B_result;
	private JPanel outputPanel;
	private JLabel A_outputLabel;
	private JLabel AB_outputLabel;
	private JLabel B_outputLabel;
	private DecimalFormat formatter;

	/**
	 * Create the frame.
	 */
	public MaDetrix(MatrixModel model) {
		setTitle("MaDetrix");
		this.model = model;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 717);
		frame = new JPanel();
		frame.setBackground(UIManager.getColor("Button.background"));
		frame.setSize(800, 800);
		frame.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(frame);
		init();
		
		JLabel A_Label = new JLabel("MATRIX A");
		A_Label.setBounds(10, 11, 298, 22);
		A_Label.setHorizontalAlignment(SwingConstants.CENTER);
		
		matrixARows = new JSpinner();
		matrixARows.setBounds(84, 40, 45, 20);
		matrixARows.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				displayMatrix("Matrix A");
			}
		});
		matrixARows.setModel(new SpinnerNumberModel(6, 1, 6, 1));
		
		matrixAColumns = new JSpinner();
		matrixAColumns.setBounds(238, 40, 45, 20);
		matrixAColumns.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				displayMatrix("Matrix A");
			}
		});
		matrixAColumns.setModel(new SpinnerNumberModel(6, 1, 6, 1));
		
		JButton A_FillWith = new JButton("Fill with 0");
		A_FillWith.setBounds(20, 250, 90, 25);
		A_FillWith.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fillWithZero("Matrix A");
			}
		});
		
		JButton A_Trace = new JButton("Trace");
		A_Trace.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				trace("Matrix A");
			}
		});
		A_Trace.setBounds(120, 250, 90, 25);

		JButton A_Transpose = new JButton("Transpose");
		A_Transpose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				transpose("Matrix A");
			}
		});
		A_Transpose.setBounds(220, 250, 90, 25);

		JButton A_Det = new JButton("Det");
		A_Det.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				determinant("Matrix A");
			}
		});
		A_Det.setBounds(20, 285, 90, 25);
		
		JButton A_Inverse = new JButton("Inverse");
		A_Inverse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inverse("Matrix A");
			}
		});
		A_Inverse.setBounds(120, 285, 90, 25);
		
		JButton A_Clear = new JButton("Clear");
		A_Clear.setBounds(220, 285, 90, 25);
		A_Clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear("Matrix A");
			}
		});
		
		JButton A_MultiplyBy = new JButton("Multiply by");
		A_MultiplyBy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scalarMultiply("Matrix A");
			}
		});
		A_MultiplyBy.setBounds(20, 320, 99, 25);
		
		JLabel A_RowsLabel = new JLabel("Rows:");
		A_RowsLabel.setBounds(40, 43, 46, 14);
		
		JLabel A_ColumnsLabel = new JLabel("Columns:");
		A_ColumnsLabel.setBounds(170, 43, 46, 14);
		
		A_multiplyWithField = new JTextField();
		A_multiplyWithField.setBounds(120, 320, 40, 25);
		A_multiplyWithField.setHorizontalAlignment(SwingConstants.CENTER);
		A_multiplyWithField.setColumns(10);
		
		A_raiseToPowerField = new JTextField();
		A_raiseToPowerField.setBounds(270, 320, 40, 25);
		A_raiseToPowerField.setHorizontalAlignment(SwingConstants.CENTER);
		A_raiseToPowerField.setColumns(10);
		
		A_RaiseToPower = new JButton("Raise to power");
		A_RaiseToPower.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				raiseToPower("Matrix A");
			}
		});
		A_RaiseToPower.setBounds(164, 320, 105, 25);
		
		BxA_Button = new JButton("B X A");
		BxA_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				multiply("BxA");
			}
		});
		BxA_Button.setBounds(447, 130, 89, 22);
		
		AxB_Button = new JButton("A X B");
		AxB_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				multiply("AxB");
			}
		});
		AxB_Button.setBounds(447, 97, 89, 22);
		
		AplusB_Button = new JButton("A + B");
		AplusB_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sumAB();
			}
		});
		AplusB_Button.setBounds(447, 163, 89, 22);
		
		AminusB_Button = new JButton("A - B");
		AminusB_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				substract("A-B");
			}
		});
		AminusB_Button.setBounds(447, 194, 89, 22);
		
		matrixBColumns = new JSpinner();
		matrixBColumns.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				displayMatrix("Matrix B");
			}
		});
		matrixBColumns.setBounds(904, 40, 45, 20);
		matrixBColumns.setModel(new SpinnerNumberModel(6, 1, 6, 1));
		
		JLabel B_ColumnsLabel = new JLabel("Columns:");
		B_ColumnsLabel.setBounds(836, 43, 46, 14);
		
		matrixBRows = new JSpinner();
		matrixBRows.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				displayMatrix("Matrix B");
			}
		});
		matrixBRows.setBounds(750, 40, 45, 20);
		matrixBRows.setModel(new SpinnerNumberModel(6, 1, 6, 1));
		
		JLabel B_RowsLabel = new JLabel("Rows:");
		B_RowsLabel.setBounds(706, 43, 46, 14);
		
		JLabel B_Label = new JLabel("MATRIX B");
		B_Label.setBounds(676, 11, 298, 22);
		B_Label.setHorizontalAlignment(SwingConstants.CENTER);
		
		JButton B_FillWith = new JButton("Fill with 0");
		B_FillWith.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fillWithZero("Matrix B");
			}
		});
		B_FillWith.setBounds(676, 250, 90, 25);
		
		JButton B_Trace = new JButton("Trace");
		B_Trace.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				trace("Matrix B");
			}
		});
		B_Trace.setBounds(776, 250, 90, 25);
		
		JButton B_Transpose = new JButton("Transpose");
		B_Transpose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				transpose("Matrix B");
			}
		});
		B_Transpose.setBounds(876, 250, 90, 25);
		
		JButton B_Det = new JButton("Det");
		B_Det.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				determinant("Matrix B");
			}
		});
		B_Det.setBounds(676, 285, 90, 25);
		
		JButton B_Inverse = new JButton("Inverse");
		B_Inverse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inverse("Matrix B");
			}
		});
		B_Inverse.setBounds(776, 285, 90, 25);
		
		JButton B_Clear = new JButton("Clear");
		B_Clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear("Matrix B");
			}
		});
		B_Clear.setBounds(876, 285, 90, 25);
		
		JButton B_MultiplyBy = new JButton("Multiply by");
		B_MultiplyBy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scalarMultiply("Matrix B");
			}
		});
		B_MultiplyBy.setBounds(676, 320, 99, 25);
		
		B_multiplyWithField = new JTextField();
		B_multiplyWithField.setBounds(776, 320, 40, 25);
		B_multiplyWithField.setHorizontalAlignment(SwingConstants.CENTER);
		B_multiplyWithField.setColumns(10);
		
		B_raiseToPowerField = new JTextField();
		B_raiseToPowerField.setBounds(926, 320, 40, 25);
		B_raiseToPowerField.setHorizontalAlignment(SwingConstants.CENTER);
		B_raiseToPowerField.setColumns(10);
		
		JButton B_RaiseToPower = new JButton("Raise to power");
		B_RaiseToPower.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				raiseToPower("Matrix B");
			}
		});
		B_RaiseToPower.setBounds(820, 320, 105, 25);
		frame.setLayout(null);
		frame.add(A_Label);
		frame.add(B_Label);
		frame.add(A_RowsLabel);
		frame.add(matrixARows);
		frame.add(A_ColumnsLabel);
		frame.add(matrixAColumns);
		frame.add(matrixBRows);
		frame.add(B_RowsLabel);
		frame.add(B_ColumnsLabel);
		frame.add(matrixBColumns);
		frame.add(BxA_Button);
		frame.add(AxB_Button);
		frame.add(AplusB_Button);
		frame.add(AminusB_Button);
		frame.add(A_FillWith);
		frame.add(A_Trace);
		frame.add(A_Transpose);
		frame.add(B_FillWith);
		frame.add(B_Trace);
		frame.add(B_Transpose);
		frame.add(A_Det);
		frame.add(A_Inverse);
		frame.add(A_Clear);
		frame.add(B_Det);
		frame.add(B_Inverse);
		frame.add(B_Clear);
		frame.add(A_MultiplyBy);
		frame.add(A_multiplyWithField);
		frame.add(A_RaiseToPower);
		frame.add(A_raiseToPowerField);
		frame.add(B_MultiplyBy);
		frame.add(B_multiplyWithField);
		frame.add(B_RaiseToPower);
		frame.add(B_raiseToPowerField);
      
		outputPanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		outputPanel.setBounds(10, 356, 964, 311);
		frame.add(outputPanel);
		outputPanel.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(SystemColor.textInactiveText);
		separator.setBounds(10, 40, 291, 2);
		outputPanel.add(separator);
		
		A_outputLabel = new JLabel("Output");
		A_outputLabel.setHorizontalAlignment(SwingConstants.CENTER);
		A_outputLabel.setBounds(10, 15, 291, 14);
		outputPanel.add(A_outputLabel);
		
		AB_outputLabel = new JLabel("Output");
		AB_outputLabel.setHorizontalAlignment(SwingConstants.CENTER);
		AB_outputLabel.setBounds(336, 15, 291, 14);
		outputPanel.add(AB_outputLabel);
		
		B_outputLabel = new JLabel("Output");
		B_outputLabel.setHorizontalAlignment(SwingConstants.CENTER);
		B_outputLabel.setBounds(663, 15, 291, 14);
		outputPanel.add(B_outputLabel);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBackground(SystemColor.textInactiveText);
		separator_1.setBounds(336, 40, 291, 2);
		outputPanel.add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBackground(SystemColor.textInactiveText);
		separator_2.setBounds(663, 40, 291, 2);
		outputPanel.add(separator_2);
		
		JButton A_copyToA_Button = new JButton("Copy to A");
		A_copyToA_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				copyTo("A", "Matrix A");
			}
		});
		A_copyToA_Button.setBounds(10, 277, 140, 23);
		outputPanel.add(A_copyToA_Button);
		
		JButton A_copyToB_Button = new JButton("Copy to B");
		A_copyToB_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				copyTo("A", "Matrix B");
			}
		});
		A_copyToB_Button.setBounds(161, 277, 140, 23);
		outputPanel.add(A_copyToB_Button);
		
		JButton AB_copyToB_Button = new JButton("Copy to B");
		AB_copyToB_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				copyTo("AB", "Matrix B");
			}
		});
		AB_copyToB_Button.setBounds(487, 277, 140, 23);
		outputPanel.add(AB_copyToB_Button);
		
		JButton AB_copyToA_Button = new JButton("Copy to A");
		AB_copyToA_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				copyTo("AB", "Matrix A");
			}
		});
		AB_copyToA_Button.setBounds(336, 277, 140, 23);
		outputPanel.add(AB_copyToA_Button);
		
		JButton B_copyToB_Button = new JButton("Copy to B");
		B_copyToB_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				copyTo("B", "Matrix B");
			}
		});
		B_copyToB_Button.setBounds(814, 277, 140, 23);
		outputPanel.add(B_copyToB_Button);
		
		JButton B_copyToA_Button = new JButton("Copy to A");
		B_copyToA_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				copyTo("B", "Matrix A");
			}
		});
		B_copyToA_Button.setBounds(663, 277, 140, 23);
		outputPanel.add(B_copyToA_Button);
		
		JButton BminusA_Button = new JButton("B - A");
		BminusA_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				substract("B-A");
			}
		});
		BminusA_Button.setBounds(447, 223, 89, 23);
		frame.add(BminusA_Button);
	}
	
	private void init() {
		formatter = new DecimalFormat("0.00");
		matrixA = new JTextField[6][6];
		matrixB = new JTextField[6][6];
		A_result = new JLabel[6][6];
		AB_result = new JLabel[6][6];
		B_result = new JLabel[6][6];
		outputPanel = new JPanel();
		outputPanel.setBackground(UIManager.getColor("Button.light"));
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 6; j++) {
				matrixA[i][j] = new JTextField();
				matrixB[i][j] = new JTextField();
				A_result[i][j] = new JLabel();
				AB_result[i][j] = new JLabel();
				B_result[i][j] = new JLabel();
				matrixA[i][j].setBounds(20 + 50 * j, 70 + 30 * i, 40, 20);
				matrixA[i][j].setHorizontalAlignment(SwingConstants.CENTER);
				matrixB[i][j].setBounds(676 + 50 * j, 70 + 30 * i, 40, 20);
				matrixB[i][j].setHorizontalAlignment(SwingConstants.CENTER);
				A_result[i][j].setBounds(20 + 50 * j, 60 + 30 * i, 45, 20);
				AB_result[i][j].setBounds(346 + 50 * j, 60 + 30 * i, 40, 20);
				B_result[i][j].setBounds(673 + 50 * j, 60 + 30 * i, 40, 20);
				frame.add(matrixA[i][j]);
				frame.add(matrixB[i][j]);
				outputPanel.add(A_result[i][j]);
				outputPanel.add(AB_result[i][j]);
				outputPanel.add(B_result[i][j]);
			}
		}
	}
	
	private void displayMatrix(String matrix) {
		if(matrix.equals("Matrix A")) {
			for(int i = 0; i < 6; i++) {
				for(int j = 0; j < 6; j++) {
					if(i >= (int) matrixARows.getValue() || j >= (int) matrixAColumns.getValue() ) {
						matrixA[i][j].setVisible(false);
						matrixA[i][j].setText("");
					}
					else {
						matrixA[i][j].setVisible(true);
					}
				}
			}
		}
		else if(matrix.equals("Matrix B")) {
			for(int i = 0; i < 6; i++) {
				for(int j = 0; j < 6; j++) {
					if(i >= (int) matrixBRows.getValue() || j >= (int) matrixBColumns.getValue() ) {
						matrixB[i][j].setVisible(false);
						matrixB[i][j].setText("");
					}
					else {
						matrixB[i][j].setVisible(true);
					}
				}
			}
		}
	}
	
	private void copyTo(String output, String matrix) {
		JLabel[][] temp = null;
		if(output.equals("A")) {
			temp = A_result;
		}
		else if(output.equals("AB")) {
			temp = AB_result;
		}
		else if(output.equals("B")) {
			temp = B_result;
		}
		if(matrix.equals("Matrix A")) {
			for(int i = 0; i < 6; i++) {
				for(int j = 0; j < 6; j++) {
					matrixA[i][j].setText(temp[i][j].getText());
				}
			}
		}
		if(matrix.equals("Matrix B")) {
			for(int i = 0; i < 6; i++) {
				for(int j = 0; j < 6; j++) {
					matrixB[i][j].setText(temp[i][j].getText());
				}
			}
		}
	}
	
	private void sumAB() {
		if((matrixARows.getValue() != matrixBRows.getValue()) || (matrixAColumns.getValue() != matrixBColumns.getValue())) {
			JOptionPane.showMessageDialog(null, "Error: A and B are not compatible");
			return;
		}
		else if(isValid("Matrix A") && isValid("Matrix B")) {
			double[][] tempA = new double[(int) matrixARows.getValue()][(int) matrixAColumns.getValue()];
			double[][] tempB = new double[(int) matrixBRows.getValue()][(int) matrixBColumns.getValue()];
			for(int i = 0; i<tempA.length; i++) {
			   for(int j = 0; j<tempA[0].length; j++) {
				   tempA[i][j] = Double.parseDouble(matrixA[i][j].getText());
				}
			}
			for(int i = 0; i<tempB.length; i++) {
				for(int j = 0; j<tempB[0].length; j++) {
					 tempB[i][j] = Double.parseDouble(matrixB[i][j].getText());
				}
			}
			double[][] result = model.add(tempA, tempB);
			for(int i = 0; i < 6; i++) {
				for(int j = 0; j < 6; j++) {
					if(i<result.length && j<result[i].length) {
						AB_result[i][j].setText("" + formatter.format(result[i][j]));
					}
					else {
						AB_result[i][j].setText("");
					}
				}
			}
			AB_outputLabel.setText("Matrix: A + B");
		}
	}
	
	private void substract(String template) {
		if((matrixARows.getValue() != matrixBRows.getValue()) || (matrixAColumns.getValue() != matrixBColumns.getValue())) {
			JOptionPane.showMessageDialog(null, "Error: A and B are not compatible");
			return;
		}
		if(isValid("Matrix A") && isValid("Matrix B")) {
			double[][] tempA = new double[(int) matrixARows.getValue()][(int) matrixAColumns.getValue()];
			double[][] tempB = new double[(int) matrixBRows.getValue()][(int) matrixBColumns.getValue()];
			for(int i = 0; i<tempA.length; i++) {
			   for(int j = 0; j<tempA[0].length; j++) {
				   tempA[i][j] = Double.parseDouble(matrixA[i][j].getText());
				}
			}
			for(int i = 0; i<tempB.length; i++) {
				for(int j = 0; j<tempB[0].length; j++) {
					 tempB[i][j] = Double.parseDouble(matrixB[i][j].getText());
				}
			}
			double[][] result;
			if(template.equals("A-B")) {
				result = model.substract(tempA, tempB);
				for(int i = 0; i < 6; i++) {
					for(int j = 0; j < 6; j++) {
						if(i<result.length && j<result[i].length) {
							AB_result[i][j].setText("" + formatter.format(result[i][j]));
						}
						else {
							AB_result[i][j].setText("");
						}
					}
				}
				AB_outputLabel.setText("Matrix: A - B");
			}
			else if(template.equals("B-A")) {
				result = model.substract(tempB, tempA);
				for(int i = 0; i < 6; i++) {
					for(int j = 0; j < 6; j++) {
						if(i<result.length && j<result[i].length) {
							AB_result[i][j].setText("" + formatter.format(result[i][j]));
						}
						else {
							AB_result[i][j].setText("");
						}
					}
				}
				AB_outputLabel.setText("Matrix: B - A");
			}
		}
	}
	
    private void multiply(String template) {
	   if(template.equals("AxB")) {
		   if(matrixAColumns.getValue() != matrixBRows.getValue()) {
			   JOptionPane.showMessageDialog(null, "Error: A and B are not compatible");
			   return;
		   }
		   else if(isValid("Matrix A") && isValid("Matrix B")) {
			   double[][] tempA = new double[(int) matrixARows.getValue()][(int) matrixAColumns.getValue()];
			   double[][] tempB = new double[(int) matrixBRows.getValue()][(int) matrixBColumns.getValue()];
			   for(int i = 0; i<tempA.length; i++) {
				   for(int j = 0; j<tempA[0].length; j++) {
					   tempA[i][j] = Double.parseDouble(matrixA[i][j].getText());
				   }
			   }
			   for(int i = 0; i<tempB.length; i++) {
				   for(int j = 0; j<tempB[0].length; j++) {
					   tempB[i][j] = Double.parseDouble(matrixB[i][j].getText());
				   }
			   }
			   double[][] result = model.multiply(tempA, tempB);
			   for(int i = 0; i<6; i++) {
					for(int j = 0; j < 6; j++) {
						if(i<result.length && j<result[i].length) {
							AB_result[i][j].setText("" + formatter.format(result[i][j]));
						}
						else {
							AB_result[i][j].setText("");
						}
					}
				}
				AB_outputLabel.setText("Matrix: A x B");
		   }
	   }
	   else if(template.equals("BxA")) {
		   if(matrixARows.getValue() != matrixBColumns.getValue()) {
			   JOptionPane.showMessageDialog(null, "Error: B and A are not compatible");
			   return;
		   }
		   else if(isValid("Matrix A") && isValid("Matrix B")) {
			   double[][] tempA = new double[(int) matrixARows.getValue()][(int) matrixAColumns.getValue()];
			   double[][] tempB = new double[(int) matrixBRows.getValue()][(int) matrixBColumns.getValue()];
			   for(int i = 0; i<tempA.length; i++) {
				   for(int j = 0; j<tempA[0].length; j++) {
					   tempA[i][j] = Double.parseDouble(matrixA[i][j].getText());
				   }
			   }
			   for(int i = 0; i<tempB.length; i++) {
				   for(int j = 0; j<tempB[0].length; j++) {
					   tempB[i][j] = Double.parseDouble(matrixB[i][j].getText());
				   }
			   }
			   double[][] result = model.multiply(tempB, tempA);
			   for(int i = 0; i<6; i++) {
					for(int j = 0; j < 6; j++) {
						if(i<result.length && j<result[i].length) {
							AB_result[i][j].setText("" + formatter.format(result[i][j]));
						}
						else {
							AB_result[i][j].setText("");
						}
					}
				}
				AB_outputLabel.setText("Matrix: B x A");
		   }
	   }
   }
   
	private void fillWithZero(String matrix) {
		if(matrix.equals("Matrix A")) {
			for(int i = 0; i < (int) matrixARows.getValue(); i++) {
				for(int j = 0; j < (int) matrixAColumns.getValue(); j++) {
					if(matrixA[i][j].getText().equals("")) {
						matrixA[i][j].setText("0");
					}
				}
			}
		}
		else if(matrix.equals("Matrix B")) {
			for(int i = 0; i < (int) matrixBRows.getValue(); i++) {
				for(int j = 0; j < (int) matrixBColumns.getValue(); j++) {
					if(matrixB[i][j].getText().equals("")) {
						matrixB[i][j].setText("0");
					}
				}
			}
		}
	}
	
	private void clear(String matrix) {
		if(matrix.equals("Matrix A")) {
			for(int i = 0; i < (int) matrixARows.getValue(); i++) {
				for(int j = 0; j < (int) matrixAColumns.getValue(); j++) {
					matrixA[i][j].setText("");
				}
			}
		}
		else if(matrix.equals("Matrix B")) {
			for(int i = 0; i < (int) matrixBRows.getValue(); i++) {
				for(int j = 0; j < (int) matrixBColumns.getValue(); j++) {
					matrixB[i][j].setText("");
				}
			}
		}
	}
	
	private void determinant(String matrix) {
		if(matrix.equals("Matrix A") && isValid("Matrix A")){
			if((int) matrixARows.getValue() != (int) matrixAColumns.getValue()){
				JOptionPane.showMessageDialog(null, "Error: Matrix A, Determinant not defined for MxN");
				return;
			}   
			double[][] temp = new double[(int) matrixARows.getValue()][(int) matrixAColumns.getValue()];
			for(int i = 0; i < temp.length; i++) {
				for(int j = 0; j < temp.length; j++) {
					temp[i][j] = Double.parseDouble(matrixA[i][j].getText());
				}
			}
			double determinant = model.determinant(temp);
			for(int i = 0; i<6; i++) {
				for(int j = 0; j < 6; j++) {
					A_result[i][j].setText("");
				}
			}
			A_result[0][0].setText("" + formatter.format(determinant));
			A_outputLabel.setText("Determinant: A");
		}
		else if(matrix.equals("Matrix B") && isValid("Matrix B")) {
			if((int) matrixBRows.getValue() != (int) matrixBColumns.getValue()){
				JOptionPane.showMessageDialog(null, "Error: Matrix B, Determinant not defined for MxN");
				return;
			} 
			double[][] temp = new double[(int) matrixBRows.getValue()][(int) matrixBColumns.getValue()];
			for(int i = 0; i < temp.length; i++) {
				for(int j = 0; j < temp.length; j++) {
					temp[i][j] = Double.parseDouble(matrixB[i][j].getText());
				}
			}
			double determinant = model.determinant(temp);
			for(int i = 0; i<6; i++) {
				for(int j = 0; j < 6; j++) {
					B_result[i][j].setText("");
				}
			}
			B_result[0][0].setText("" + formatter.format(determinant));
			B_outputLabel.setText("Determinant: B");
		}
	}
	
	private void inverse(String matrix) {
		if(matrix.equals("Matrix A") && isValid("Matrix A")) {
			if((int) matrixARows.getValue() != (int) matrixAColumns.getValue()){
				JOptionPane.showMessageDialog(null, "Error: Matrix A, Inverse not defined for MxN");
				return;
			}
			double[][] temp = new double[(int) matrixARows.getValue()][(int) matrixAColumns.getValue()];
			for(int i = 0; i < temp.length; i++) {
				for(int j = 0; j < temp.length; j++) {
					temp[i][j] = Double.parseDouble(matrixA[i][j].getText());
				}
			}
			if(model.determinant(temp) == 0){
				JOptionPane.showMessageDialog(null, "Error: Matrix A, Inverse not defined");
				return;
			}
			temp = model.inverseOfMatrix(temp);
			for(int i = 0; i<6; i++) {
				for(int j = 0; j < 6; j++) {
					if(i<temp.length && j<temp.length) {
						A_result[i][j].setText("" + formatter.format(temp[i][j]));
					}
					else {
						A_result[i][j].setText("");
					}
				}
			}
			A_outputLabel.setText("Inverse: A");
		}
		else if(matrix.equals("Matrix B") && isValid("Matrix B")) {
			if((int) matrixBRows.getValue() != (int) matrixBColumns.getValue()){
				JOptionPane.showMessageDialog(null, "Error: Matrix B, Inverse not defined for MxN");
				return;
			}
			double[][] temp = new double[(int) matrixBRows.getValue()][(int) matrixBColumns.getValue()];
			for(int i = 0; i < temp.length; i++) {
				for(int j = 0; j < temp.length; j++) {
					temp[i][j] = Double.parseDouble(matrixB[i][j].getText());
				}
			}
			if(model.determinant(temp) == 0){
				JOptionPane.showMessageDialog(null, "Error: Matrix B, Inverse not defined");
				return;
			}
			temp = model.inverseOfMatrix(temp);;
			for(int i = 0; i<6; i++) {
				for(int j = 0; j < 6; j++) {
					if(i<temp.length && j<temp.length) {
						B_result[i][j].setText("" + formatter.format(temp[i][j]));
					}
					else {
						B_result[i][j].setText("");
					}
				}
			}
			B_outputLabel.setText("Inverse: B");
		}
	}
	
	private void scalarMultiply(String matrix) {
		if(matrix.equals("Matrix A") && isValid("Matrix A")) {
			double[][] temp = new double[(int) matrixARows.getValue()][(int) matrixAColumns.getValue()];
			for(int i = 0; i < temp.length; i++) {
				for(int j = 0; j < temp[0].length; j++) {
					temp[i][j] = Double.parseDouble(matrixA[i][j].getText());
				}
			}
			try {
				temp = model.scalarMultiply(temp,Double.parseDouble(A_multiplyWithField.getText()));
			}catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Error: Matrix A, Multiply value is invalid");
				return;
			}
			for(int i = 0; i < 6; i++) {
				for(int j = 0; j < 6; j++) {
					if(i >= (int) matrixARows.getValue() || j >= (int) matrixAColumns.getValue() ) {
						A_result[i][j].setText("");
					}
					else {
						A_result[i][j].setText("" + formatter.format(temp[i][j]));
					}
				}
			}
			A_outputLabel.setText("Matrix: " + A_multiplyWithField.getText() + 'A');
		}
		else if(matrix.equals("Matrix B") && isValid("Matrix B")) {
			double[][] temp = new double[(int) matrixBRows.getValue()][(int) matrixBColumns.getValue()];
			for(int i = 0; i < temp.length; i++) {
				for(int j = 0; j < temp[0].length; j++) {
					temp[i][j] = Double.parseDouble(matrixB[i][j].getText());
				}
			}
			try {
				temp = model.scalarMultiply(temp,Double.parseDouble(B_multiplyWithField.getText()));
			}catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Error: Matrix B, Multiply value is invalid");
				return;
			}
			for(int i = 0; i < 6; i++) {
				for(int j = 0; j < 6; j++) {
					if(i >= (int) matrixBRows.getValue() || j >= (int) matrixBColumns.getValue() ) {
						B_result[i][j].setText("");
					}
					else {
						B_result[i][j].setText("" + formatter.format(temp[i][j]));
					}
				}
			}
			B_outputLabel.setText("Matrix: " + B_multiplyWithField.getText() + 'B');
		}
	}
	
	private void raiseToPower(String matrix) {
		if(matrix.equals("Matrix A") && isValid("Matrix A")){
			if((int) matrixARows.getValue() != (int) matrixAColumns.getValue()) {
				JOptionPane.showMessageDialog(null, "Error: Matrix A, Value not defined for MxN");
				return;
			}
			int power;
			try {
				power = Integer.parseInt(A_raiseToPowerField.getText());
			}
			catch(Exception e) {
				JOptionPane.showMessageDialog(null, "Error: Matrix A, Power value is invalid");
				return;
			}
			double[][] temp = new double[(int) matrixARows.getValue()][(int) matrixAColumns.getValue()]; 
			for(int i = 0; i<temp.length; i++) {
				for(int j = 0; j<temp[0].length; j++) {
					temp[i][j] = Double.parseDouble(matrixA[i][j].getText());
				}
			}
         if(power < 0 && model.determinant(temp) == 0) {
        	 JOptionPane.showMessageDialog(null, "Error: Matrix A, Inverse not defined");
        	 return;
         }
			temp = model.raiseToPower(temp, power);
			for(int i = 0; i < 6; i++) {
				for(int j = 0; j < 6; j++) {
					if(i >= (int) matrixARows.getValue() || j >= (int) matrixAColumns.getValue() ) {
						A_result[i][j].setText("");
					}
					else {
						A_result[i][j].setText("" + formatter.format(temp[i][j]));
					}
				}
			}
			A_outputLabel.setText("Matrix: " + "A^" + A_raiseToPowerField.getText() );
		}
		else if(matrix.equals("Matrix B") && isValid("Matrix B")){
			if((int) matrixBRows.getValue() != (int) matrixBColumns.getValue()) {
				JOptionPane.showMessageDialog(null, "Error: Matrix B, Value not defined");
				return;
			}
			int power;
			try {
				power = Integer.parseInt(B_raiseToPowerField.getText());
			}
			catch(Exception e) {
				JOptionPane.showMessageDialog(null, "Error: Matrix B, Power value is invalid");
				return;
			}
			double[][] temp = new double[(int) matrixBRows.getValue()][(int) matrixBColumns.getValue()]; 
			for(int i = 0; i<temp.length; i++) {
				for(int j = 0; j<temp[0].length; j++) {
					temp[i][j] = Double.parseDouble(matrixB[i][j].getText());
				}
			}
         if(power < 0 && model.determinant(temp) == 0) {
        	 JOptionPane.showMessageDialog(null, "Error: Matrix B, Inverse not defined");
        	 return;
         }
			temp = model.raiseToPower(temp, power);
			for(int i = 0; i < 6; i++) {
				for(int j = 0; j < 6; j++) {
					if(i >= (int) matrixBRows.getValue() || j >= (int) matrixBColumns.getValue() ) {
						B_result[i][j].setText("");
					}
					else {
						B_result[i][j].setText("" + formatter.format(temp[i][j]));
					}
				}
			}
			B_outputLabel.setText("Matrix: " + "B^" + B_raiseToPowerField.getText() );
		}
	}
	
	private void trace(String matrix) {
		if(matrix.equals("Matrix A") && isValid("Matrix A")){
			if((int) matrixARows.getValue() != (int) matrixAColumns.getValue()){
				JOptionPane.showMessageDialog(null, "Error: Matrix A, Trace not defined for MxN");
				return;
			}   
			double total = 1;
			for(int i = 0; i < (int) matrixARows.getValue(); i++) {
				total *= Double.parseDouble(matrixA[i][i].getText());
			}
			for(int i = 0; i < 6; i++) {
				for(int j = 0; j < 6; j++ ) {
					A_result[i][j].setText("");
				}
			}
			A_result[0][0].setText("" + formatter.format(total));
			A_outputLabel.setText("Trace: A");
		}
		else if(matrix.equals("Matrix B") && isValid("Matrix B")){
			if((int) matrixBRows.getValue() != (int) matrixBColumns.getValue()){
				JOptionPane.showMessageDialog(null, "Error: Matrix B, Trace not defined for MxN");
				return;
			}   
			double total = 1;
			for(int i = 0; i < (int) matrixBRows.getValue(); i++) {
				total *= Double.parseDouble(matrixB[i][i].getText());
			}
			for(int i = 0; i < 6; i++) {
				for(int j = 0; j < 6; j++ ) {
					B_result[i][j].setText("");
				}
			}
			B_result[0][0].setText("" + formatter.format(total));
			B_outputLabel.setText("Trace: B");
		}
	}
	
	private void transpose(String matrix) {
		if(matrix.equals("Matrix A")){
			String[][] temp = new String[(int) matrixARows.getValue()][(int) matrixAColumns.getValue()];
			for(int i = 0; i < (int) matrixARows.getValue(); i++) {
				for(int j = 0; j < (int) matrixAColumns.getValue(); j++) {
					temp[i][j] = matrixA[i][j].getText();
				}
			}
			int temporaryValue = (int) matrixARows.getValue();
			matrixARows.setValue(matrixAColumns.getValue());
			matrixAColumns.setValue(temporaryValue);
			for(int i = 0; i < (int) matrixARows.getValue(); i++) {
				for(int j = 0; j < (int) matrixAColumns.getValue(); j++) {
					matrixA[i][j].setText(temp[j][i]);
				}
			}
			displayMatrix("Matrix A");
		}
		else if(matrix.equals("Matrix B")){
			String[][] temp = new String[(int) matrixBRows.getValue()][(int) matrixBColumns.getValue()];
			for(int i = 0; i < (int) matrixBRows.getValue(); i++) {
				for(int j = 0; j < (int) matrixBColumns.getValue(); j++) {
					temp[i][j] = matrixB[i][j].getText();
				}
			}
			int temporaryValue = (int) matrixBRows.getValue();
			matrixBRows.setValue(matrixBColumns.getValue());
			matrixBColumns.setValue(temporaryValue);
			for(int i = 0; i < (int) matrixBRows.getValue(); i++) {
				for(int j = 0; j < (int) matrixBColumns.getValue(); j++) {
					matrixB[i][j].setText(temp[j][i]);
				}
			}
			displayMatrix("Matrix B");
		}
	}
	
	private boolean isValid(String matrix) {
		if(matrix.equals("Matrix A")) {
			for(int i = 0; i < (int) matrixARows.getValue(); i++) {
				for(int j = 0; j < (int) matrixAColumns.getValue(); j++) {
					try {
						double test = Double.parseDouble(matrixA[i][j].getText());
					}
					catch(Exception e) {
						JOptionPane.showInternalMessageDialog(null, "Error: Matrix A, Element A" + (i + 1) + (j + 1));
						return false;
					}
				}
			}
		}
		else if(matrix.equals("Matrix B")) {
			for(int i = 0; i < (int) matrixBRows.getValue(); i++) {
				for(int j = 0; j < (int) matrixBColumns.getValue(); j++) {
					try {
						double test = Double.parseDouble(matrixB[i][j].getText());
					}
					catch(Exception e) {
						JOptionPane.showInternalMessageDialog(null, "Error: Matrix B, Element B" + (i + 1) + (j + 1));
						return false;
					}
				}
			}
		}
		return true;
	}
}