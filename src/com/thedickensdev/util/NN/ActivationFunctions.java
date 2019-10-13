package com.thedickensdev.util.NN;

public class ActivationFunctions extends DerivativesOfActivationFunctions{
    public double sigmoid(double x){
        return 1 / (1 + Math.exp(-x));
    }

    public double tahn(double x){
        return Math.tanh(x);
    }
}
