/*
 * Copyright (c) 2019. Author Tawanda Chiteshe
 */

package com.thedickensdev.util;



import com.thedickensdev.util.NN.ActivationFunctions;
import com.thedickensdev.util.NN.ActivationFunctionsTypes;
import com.thedickensdev.util.NN.DerivativesOfActivationFunctions;

import java.util.Random;

public class matrix {
    private int num_rows;
    private int num_cols;
   private double data[][];
    private Random random;

    public matrix(int num_rows, int num_cols){
        this.num_rows = num_rows;
        this.num_cols = num_cols;
        data = new double[num_rows][num_cols];
       random = new Random();
    }

    public void randomize(){

        for(int i = 0; i < num_rows; i++) {

            for(int j = 0; j < num_cols; j++) {
                data[i][j] += Math.random() * (2 - 1);
            }
        }
    }
    public static matrix multiply(matrix a,matrix b) throws MatrixCalculationException {
        if(a.getNum_cols() != b.getNum_rows()){
            throw new MatrixCalculationException("please make sure rows are equal to column");
        }else {
            double tempArray[][] = a.getData();
            double bTemp[][] = b.getData();
            double tempData[][] = new double[a.getNum_rows()][b.getNum_cols()];
            for(int i = 0; i < a.getNum_rows(); i++) {
                for(int j = 0; j < b.getNum_cols(); j++) {
                    for(int k = 0 ; k < a.getNum_cols(); k++){
                        tempData[i][j] += tempArray[i][k] * bTemp[k][j];
                    }
                }
            }
            matrix tempMatrix = new matrix(a.getNum_rows(),b.getNum_cols());
            tempMatrix.setData(tempData);
            return  tempMatrix;
        }



    }



    public void multiply(matrix a) throws MatrixCalculationException {
        if(this.num_cols != a.getNum_rows()){
            throw new MatrixCalculationException("please make sure rows are equal to column");
        }else {
            double tempArray[][] = this.data;
            double bTemp[][] = a.getData();
            double tempData[][] = new double[this.num_rows][a.getNum_cols()];
            for(int i = 0; i < this.num_rows; i++) {
                for(int j = 0; j < a.getNum_cols(); j++) {
                    for(int k = 0 ; k < this.num_cols; k++){
                        tempData[i][j] += tempArray[i][k] * bTemp[k][j];
                    }
                }
            }
          this.data = tempData;
        }
    }

    public void multiply(double a){
        for(int i = 0; i < num_rows; i++) {

            for(int j = 0; j < num_cols; j++) {
                data[i][j] *= a;
            }
        }
    }

    public void transpose(){

        double dataTopass[][] = new double[num_cols][num_rows];
        double [][] temp = this.data;
        for (int i = 0; i < num_rows ; i++ ){
            for(int j = 0; j < num_cols; j++){
                dataTopass[j][i] = temp[i][j];
                this.setNum_rows(num_cols);
                this.setNum_cols(num_rows);
                this.setData(dataTopass);
            }
        }

    }

    public static matrix transpose(matrix a){
        matrix trans = new matrix(a.getNum_cols(),a.getNum_rows());
        double dataTopass[][] = new double[a.getNum_cols()][a.getNum_rows()];
        double [][] temp = a.getData();
        for (int i = 0; i < a.getNum_rows() ; i++ ){
            for(int j = 0; j < a.getNum_cols(); j++){
                dataTopass[j][i] = temp[i][j];
                trans.setData(dataTopass);
            }
        }
        return trans;
    }

    public void map(ActivationFunctions af){
        for (int i = 0; i < this.num_rows ; i++) {
            for (int j = 0; j < this.num_cols; j++) {
                this.data[i][j] = af.sigmoid(data[i][j]);
            }
        }
    }
    public static matrix mapd(matrix m, DerivativesOfActivationFunctions af){
        matrix tempM = new matrix(m.getNum_rows(),m.getNum_cols());
        double mData[][] = m.getData();
        for (int i = 0; i < m.getNum_rows() ; i++) {
            for (int j = 0; j < m.getNum_cols(); j++) {
                mData[i][j] = af.dSigmoid(mData[i][j]);
                tempM.setData(mData);
            }
        }
        return tempM;
    }
    public static matrix map(matrix m , ActivationFunctions af){
        matrix tempM = new matrix(m.getNum_rows(),m.getNum_cols());
          double mData[][] = m.getData();
          for (int i = 0; i < m.getNum_rows() ; i++) {
              for (int j = 0; j < m.getNum_cols(); j++) {
                  mData[i][j] = af.sigmoid(mData[i][j]);
                  tempM.setData(mData);
              }
          }
          return tempM;

    }
    public static double[] toArray(matrix a){
        int rows = a.getNum_rows();
        int cols = a.getNum_cols();
        double tempArray[] = null;
        if(cols == 1){
            tempArray = new double[rows];
        }else
        {
            tempArray = new double[rows + cols];

        }
        double[][] matrixData = a.getData();
        for (int i = 0; i < rows ; i++) {
            for (int j = 0; j < cols ; j++) {
                for (int k = 0; k < tempArray.length ; k++) {
                    tempArray[k] = matrixData[i][j];
                }
            }
        }
        return  tempArray;

    }
    public static matrix fromArray(double array[]) {
        matrix fromArrayTemp = new matrix(array.length,1);
        double[][] tempArray = new double[array.length][1];
        for (int i = 0; i < array.length ; i++) {
            tempArray[i][0] += array[i];
            fromArrayTemp.setData(tempArray);
        }

        return  fromArrayTemp;
    }

    public matrix copy(){
        matrix tempCopy = new matrix(this.num_rows,this.num_cols);
        double[][] t = new double[this.num_rows][this.num_cols];
        for (int i = 0; i < num_rows ; i++) {
            for (int j = 0; j < num_cols ; j++) {
                t[i][j] = this.data[i][j];
                tempCopy.setData(t);
            }
        }
        return  tempCopy;
    }

    public static matrix subtract(matrix a, matrix b) throws MatrixCalculationException {
        double[][] tempA = a.getData();
        double[][] tempB = b.getData();
        int rows = a.getNum_rows();
        int cols = a.getNum_cols();
        matrix tempSubtractionMatrix = new matrix(rows, cols);
        double[][] tempSubtrationArray = new double[rows][cols];
        if(rows != rows || cols != cols){
            throw new MatrixCalculationException("Columns and Rows of A must match Columns and Rows of B");
        }

        for (int i = 0; i < rows ; i++) {
            for (int j = 0; j < cols; j++) {
             tempSubtrationArray[i][j] += tempA[i][j] - tempB[i][j];
             tempSubtractionMatrix.setData(tempSubtrationArray);
            }
        }
        return tempSubtractionMatrix;
    }


    public static matrix add(matrix a, matrix b) throws MatrixCalculationException {

        double[][] tempA = a.getData();
        double[][] tempB = b.getData();
        int rows = a.getNum_rows();
        int cols = a.getNum_cols();
        matrix tempAddMatrix = new matrix(rows, cols);
        double[][] tempAddArray = new double[rows][cols];


        if(rows != rows || cols != cols){
            throw new MatrixCalculationException("Columns and Rows of A must match Columns and Rows of B");
        }
        for (int i = 0; i < rows ; i++) {
            for (int j = 0; j < cols; j++) {
                tempAddArray[i][j] += tempA[i][j] + tempB[i][j];
                tempAddMatrix.setData(tempAddArray);
            }
        }
        return tempAddMatrix;
    }

    public void add(matrix a){

        for(int i = 0; i < num_rows; i++) {

            for(int j = 0; j < num_cols; j++) {
            data[i][j] += a.data[i][j];
            }
        }

    }

    public int getNum_rows() {
        return num_rows;
    }

    public void setNum_rows(int num_rows) {
        this.num_rows = num_rows;
    }

    public int getNum_cols() {
        return num_cols;
    }

    public void setNum_cols(int num_cols) {
        this.num_cols = num_cols;
    }

    public double[][] getData() {
        return data;
    }

    public void setData(double[][] data) {
        this.data = data;
    }
}
