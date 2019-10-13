import com.thedickensdev.util.*;
import com.thedickensdev.util.NN.*;

import java.util.*;
NeuralNetwork nn;
Random s;
DataSet[] dataSet = new DataSet[4];
void setup() {
  s = new Random();
  size(400, 400);
  nn = new NeuralNetwork(2, 4, 1);
  double a[] = {1, 1};
  double ao[] = {0};
  double b[] = {0, 0};
  double bo[] = {0};
  double c[] = {0, 1};
  double co[] = {1};
  double d[] = {1, 0};
  double du[] = {1};
  dataSet[0] = new DataSet(a, ao);
  dataSet[1] = new DataSet(b, bo);
  dataSet[2]  = new DataSet(c, co);
  dataSet[3]  = new DataSet(d, du);

  try {
    for (int i = 0; i < 10000; i++) {
      for (int j = 0; j < 4; j++) {

        nn.train(dataSet[j].getInputs(), dataSet[j].getOutput());
      }
    }
  }
  catch(MatrixCalculationException ex) {
    println(ex);
  }
}

float rows, cols, resolution;
void draw() {
  resolution = 10;
  background(0);

  rows = width / resolution;
  cols = height / resolution;


  try {

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        double x1 = j / cols;
        double x2 = i / rows;

        double inputs[] = {x1, x2};

        double y[] = matrix.toArray(nn.predict(inputs));
        fill((int) round((float)y[0] * 255));
        rect(j*resolution, i*resolution, resolution, resolution);
      }
    }
  }
  catch(MatrixCalculationException ex) {
    println(ex);
  }
}
