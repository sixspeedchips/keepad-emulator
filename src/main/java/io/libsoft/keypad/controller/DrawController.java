package io.libsoft.keypad.controller;


import io.libsoft.keypad.view.FontSelector;
import io.libsoft.keypad.view.element.Keypad;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;

public class DrawController {


  private final GFXUpdater updater = new GFXUpdater();
  public Keypad keyPad;
  public FontSelector fontSelector;

  @FXML
  private void initialize() {

    updater.start();




  }

  public void start() {

  }


  public void stop() {

  }

  private void updateView() {

  }


  private class GFXUpdater extends AnimationTimer {

    @Override
    public void handle(long now) {
      updateView();
    }

  }

}
