package io.libsoft.keypad.view.element;

import java.util.HashMap;
import java.util.Map;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class KeypadButton extends StackPane {

  private static final Map<String, KeyCode> keyCodeMap;

  static {
    keyCodeMap = new HashMap<>();
    keyCodeMap.put("1", KeyCode.NUMPAD1);
    keyCodeMap.put("2", KeyCode.NUMPAD2);
    keyCodeMap.put("3", KeyCode.NUMPAD3);
    keyCodeMap.put("4", KeyCode.NUMPAD4);
    keyCodeMap.put("5", KeyCode.NUMPAD5);
    keyCodeMap.put("6", KeyCode.NUMPAD6);
    keyCodeMap.put("7", KeyCode.NUMPAD7);
    keyCodeMap.put("8", KeyCode.NUMPAD8);
    keyCodeMap.put("9", KeyCode.NUMPAD9);
    keyCodeMap.put("0", KeyCode.NUMPAD0);
  }

  private final ButtonClickListener buttonPress;
  private final Label label = new Label();
  private Pane bak;
  private String symbol;

  public KeypadButton(String symbol, ButtonClickListener buttonPress) {
    this.symbol = symbol;
    this.buttonPress = buttonPress;
    label.setText(symbol);
    getStyleClass().add("keypadButton");
    setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, new CornerRadii(5), Insets.EMPTY)));
    setBorder(new Border(new BorderStroke(Color.DARKGRAY, BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(2))));

//    bak = new Pane();
//    bak.setBackground(new Background(
//        new BackgroundFill(Color.TRANSPARENT, new CornerRadii(5), Insets.EMPTY)));
//    bak.setEffect(new DropShadow(5, -1, -1, Color.GRAY));
//    getChildren().add(bak);
    getChildren().add(label);
    setOnMouseClicked(event -> {
      clicked();
    });

  }

  public void setButtonValue(String symbol) {
    this.symbol = symbol;
    label.setText(symbol);
  }


  private void clicked() {

    final Animation animation = new Transition() {

      {
        setCycleDuration(Duration.millis(150));
        setInterpolator(Interpolator.LINEAR);
      }

      @Override
      protected void interpolate(double frac) {
        Color vColor = new Color(0, .54, .78, Math.max(1 * .5 - frac, 0));
        KeypadButton.this.setBackground(new Background(
            new BackgroundFill(vColor, new CornerRadii(5), Insets.EMPTY)));
      }
    };
    animation.play();
    buttonPress.onClicked(symbol);
  }

}
