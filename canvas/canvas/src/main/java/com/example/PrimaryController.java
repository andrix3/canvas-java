package com.example;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Color;

public class PrimaryController implements Initializable {

  @FXML
  private Slider slider;

  @FXML
  private Canvas canvas;

  private GraphicsContext gc;

  @FXML
  private RadioButton seno;

  @FXML
  private RadioButton coseno;

  @FXML
  private ToggleGroup group;

  private double k = 15;
  private int selection = 0;

  @FXML
  void setSeno(ActionEvent event) {
    selection = 1;
    gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    gc.setFill(Color.BLACK);
    gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    gc.setFill(Color.WHITE);
    for (double i = 0; i < canvas.getWidth(); i += 1/k) {
      gc.fillRect(i * k, (canvas.getHeight() / 2 + Math.sin(i) * (-k)), 1, 1);
    }
  }

  @FXML
  void setCoseno(ActionEvent event) {
    selection = 2;
    gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    gc.setFill(Color.BLACK);
    gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    gc.setFill(Color.WHITE);
    for (double i = 0; i < canvas.getWidth(); i += 1/k) {
      gc.fillRect(i * k, (canvas.getHeight() / 2 - Math.cos(i) * k), 1, 1);
    }
  }

  @FXML
  public void initialize(URL location, ResourceBundle resources) {
    canvas.setHeight(300);
    gc = canvas.getGraphicsContext2D();
    gc.setFill(Color.BLACK);
    gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    gc.setFill(Color.WHITE);

    slider.valueProperty().addListener(new ChangeListener<Number>() {

      @Override
      public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
        k = slider.getValue();

        if(k == 0) 
          k = 1;

        System.out.println(k);

        if(selection == 1)
          setSeno(null);
        else if(selection == 2)
          setCoseno(null);
      }
    });
  }
}
