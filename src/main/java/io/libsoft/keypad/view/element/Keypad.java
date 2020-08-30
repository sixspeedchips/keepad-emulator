package io.libsoft.keypad.view.element;

import java.sql.Time;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import javafx.application.Platform;
import javafx.scene.layout.GridPane;

public class Keypad extends GridPane implements ButtonClickListener {

  private static final Random random = new Random();
  private final List<KeypadButton> buttons = new LinkedList<>();
  private final KeypadButton enter = new KeypadButton("#", this);
  private final KeypadButton aux = new KeypadButton("*", this);
  private final Map<String, Runnable> functions;


  public Keypad() {
    getStyleClass().add("keypad");

    for (int i = 0; i < 10; i++) {
      KeypadButton keyPadButton = new KeypadButton("", this);
      buttons.add(keyPadButton);
    }

    functions = new HashMap<>();
    AtomicBoolean shuffling = new AtomicBoolean(false);
    functions.put("*", ()->{
      if (!shuffling.get()){
        shuffling.set(true);
        new Thread(()->{

          for (int i = 0; i < 10; i++) {
            Platform.runLater(this::shuffleKeypad);
            try {
              Thread.sleep(20);
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
          }
          shuffling.set(false);
        }).start();

      }


    });
    functions.put("#", ()->{

    });



    createGrid();

  }

  public void shuffleKeypad() {
    List<Integer> integers = IntStream.range(0,10).boxed().collect(Collectors.toList());
    Collections.shuffle(integers);
    for (int i = 0; i < integers.size(); i++) {
      buttons.get(i).setButtonValue(integers.get(i).toString());
    }
  }

  public void createGrid() {

    int count = 0;
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        add(buttons.get(count++), j, i);
      }
    }
    add(buttons.get(count), 1, 4);
    add(aux, 0, 4);
    add(enter, 2, 4);

  }


  @Override
  public void onClicked(String value) {
    if (!value.isEmpty()) {
      System.out.println(value);
    }

    if (functions.containsKey(value)){
      functions.get(value).run();
    }


  }


}
