package com.thedickensdev.util.NN;

import com.thedickensdev.util.MatrixCalculationException;
import com.thedickensdev.util.matrix;

import java.lang.reflect.MalformedParameterizedTypeException;

public class NeuralNetwork {
    private int inputNodes;
    private int hiddenNodes;
    private int outputNodes;
    private double learningRate = 0.1;
    private matrix weightsInputsToHidden;
    private matrix weightsHiddenToOutput;
    private matrix biasOutput;
    private matrix biasHidden;
    public NeuralNetwork(int inputNodes, int hiddenNodes, int outputNodes) {
        this.inputNodes = inputNodes;
        this.hiddenNodes = hiddenNodes;
        this.outputNodes = outputNodes;

        weightsInputsToHidden = new matrix(hiddenNodes,inputNodes);
        weightsHiddenToOutput = new matrix(outputNodes,hiddenNodes);
        weightsInputsToHidden.randomize();
        weightsHiddenToOutput.randomize();
        biasOutput = new matrix(outputNodes,1);
        biasHidden = new matrix(hiddenNodes,1);
        biasOutput.randomize();
        biasHidden.randomize();

    }

    public matrix predict(double[] inputs) throws MatrixCalculationException {
        matrix input = matrix.fromArray(inputs);
        matrix hidden = matrix.multiply(weightsInputsToHidden,input);
        hidden.add(biasHidden);
        hidden.map(new ActivationFunctions());
        matrix output = matrix.multiply(weightsHiddenToOutput,hidden);
        output.add(this.biasOutput);
        output.map(new ActivationFunctions());

        return output;

    }

    public void train(double inputs[], double p[]) throws MatrixCalculationException {
        //convert inputs to matrix
        matrix input = matrix.fromArray(inputs);
        matrix hidden = matrix.multiply(weightsInputsToHidden,input);
        hidden.add(biasHidden);
        hidden.map(new ActivationFunctions());
        //generate outputs
        matrix outputs = matrix.multiply(weightsHiddenToOutput,hidden);
        outputs.add(biasOutput);
        outputs.map(new ActivationFunctions());
        matrix pm = matrix.fromArray(p);
        //output errors
        matrix outputErrors = matrix.subtract(pm,outputs);
        //transpose ouputs errors
        outputErrors.transpose();
        matrix gradients = matrix.mapd(outputs,new ActivationFunctions());
        gradients.multiply(outputErrors);
        gradients.multiply(learningRate);
        matrix hiddenTranspose = matrix.transpose(hidden);
        //generate deltas
        matrix weightsHiddenOutputDeltas = matrix.multiply(gradients,hiddenTranspose);
        weightsHiddenToOutput.add(weightsHiddenOutputDeltas);
        biasOutput.add(gradients);
        matrix weightHiddenTranspose = matrix.transpose(weightsHiddenToOutput);
        matrix hiddenErrors = matrix.multiply(weightHiddenTranspose,outputErrors);
        hiddenErrors.transpose();
        matrix hiddenGradient = matrix.mapd(hidden,new ActivationFunctions());
        hiddenGradient.multiply(hiddenErrors);
        hiddenGradient.multiply(learningRate);
        matrix inputTranspose = matrix.transpose(input);
        matrix inputToHiddenDeltas = matrix.multiply(hiddenGradient,inputTranspose);
        weightsInputsToHidden.add(inputToHiddenDeltas);
        biasHidden.add(hiddenGradient);
    }

    public int getInputNodes() {
        return inputNodes;
    }

    public void setInputNodes(int inputNodes) {
        this.inputNodes = inputNodes;
    }

    public int getHiddenNodes() {
        return hiddenNodes;
    }

    public void setHiddenNodes(int hiddenNodes) {
        this.hiddenNodes = hiddenNodes;
    }

    public int getOutputNodes() {
        return outputNodes;
    }

    public void setOutputNodes(int outputNodes) {
        this.outputNodes = outputNodes;
    }

    public double getLearningRate() {
        return learningRate;
    }

    public void setLearningRate(double learningRate) {
        this.learningRate = learningRate;
    }
}
