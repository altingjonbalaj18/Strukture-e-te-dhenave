package com.madetrix;

public class MatrixModel {
	public double[][] multiply(double[][] matrixA, double[][] matrixB){
		double[][] result = new double[matrixA.length][matrixB[0].length];
		for(int i = 0; i < matrixA.length; i++) {
			for(int j = 0; j < matrixB[0].length; j++) {
				double total = 0;
				for(int k = 0; k < matrixA[0].length; k++){
					total += matrixA[i][k] * matrixB[k][j];
				}
				result[i][j] = total;
			}
		}
		return result;
	}
	public double determinant(double[][] matrixA){
		if(matrixA.length == 1){
			return matrixA[0][0];
		}
		if(matrixA.length == 2){
			return matrixA[0][0]*matrixA[1][1] - matrixA[0][1]*matrixA[1][0];
		}
		else {
			double result = 0;
			for (int i = 0; i < matrixA.length; i++) {
				result += Math.pow(-1, i) * matrixA[i][0] * determinant(cofactor(matrixA, i, 0));
			}
			return result;
		}
	}
	public double[][] inverseOfMatrix(double[][] matrixA){
		double[][] result = new double[matrixA[0].length][matrixA.length];
		for(int i = 0; i < matrixA.length; i++){
			for(int j = 0; j < matrixA[0].length; j++){
				result[j][i] = Math.pow(-1, i + j) * determinant(cofactor(matrixA, i, j));
			}
		}
		result = scalarMultiply(result, 1 / determinant(matrixA));
		return result;
	}
	public double[][] scalarMultiply(double[][] matrixA, double scalar){
		for(int i = 0; i < matrixA.length; i++){
			for(int j = 0; j < matrixA[i].length; j++){
				matrixA[i][j] = scalar * matrixA[i][j];
			}
		}
		return matrixA;
	}
	public double[][] raiseToPower(double[][] matrixA, int power){
		double[][] result = new double[matrixA.length][matrixA[0].length];
		for(int i = 0; i < result.length; i++) {
			result[i][i] = 1;
		}
		if(power > 0 ) {
			for(int i = 0; i < power; i++) {
				result = multiply(result, matrixA);
			}
		}
		else if(power < 0 ) {
			for(int i = 0; i < -power; i++) {
				result = multiply(result, matrixA);
			}
			result = inverseOfMatrix(result);
		}
		return result;
	}	
	public double[][] add(double[][] matrixA, double[][] matrixB){
		double[][] result = new double[matrixA.length][matrixA[0].length];
		for(int i = 0; i<result.length; i++) {
			for(int j = 0; j<result[0].length; j++) {
				result[i][j] = matrixA[i][j] + matrixB[i][j];
			}
		}
		return result;
	}
	public double[][] substract(double[][] matrixA, double[][] matrixB){
		double[][] result = new double[matrixA.length][matrixA[0].length];
		for(int i = 0; i<result.length; i++) {
			for(int j = 0; j<result[0].length; j++) {
				result[i][j] = matrixA[i][j] - matrixB[i][j];
			}
		}
		return result;
	}
	public double[][] cofactor(double[][] matrixA, int row, int column){
		double[][] result = new double[matrixA.length - 1][matrixA[0].length - 1];
		boolean rowReached = false;
		for(int i = 0; i < matrixA.length; i++){
			if(i == row){
				rowReached = true;
				continue;
			}
			boolean columnReached = false;
			for(int j = 0; j < matrixA[0].length; j++){
				if(j == column){
					columnReached = true;
					continue;
				}
				if(rowReached && columnReached){
					result[i - 1][j - 1] = matrixA[i][j];
				}
				else if(columnReached){
					result[i][j - 1] = matrixA[i][j];
				}
				else if(rowReached){
					result[i - 1][j] = matrixA[i][j];
				}
				else{
					result[i][j] = matrixA[i][j];
				}
			}
		}
		return result;
	}
}
