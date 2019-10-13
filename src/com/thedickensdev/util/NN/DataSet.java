/*
 * Copyright (c) 2019. Author Tawanda Chiteshe
 */

package com.thedickensdev.util.NN;

public class DataSet {
    private double[] inputs;
    private double[] output;

    public DataSet(double[] inputs, double[] output) {
        this.inputs = inputs;
        this.output = output;
    }

    public double[] getInputs() {
        return inputs;
    }

    public void setInputs(double[] inputs) {
        this.inputs = inputs;
    }

    public double[] getOutput() {
        return output;
    }

    public void setOutput(double[] output) {
        this.output = output;
    }
}
