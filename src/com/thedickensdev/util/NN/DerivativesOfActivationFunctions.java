/*
 * Copyright (c) 2019. Author Tawanda Chiteshe
 */

package com.thedickensdev.util.NN;

public class DerivativesOfActivationFunctions {
    public double dSigmoid(double x){
        return x * (1 - x);
    }

    public double dTanh(double x){
        return 1 - (x * x);
    }
}
