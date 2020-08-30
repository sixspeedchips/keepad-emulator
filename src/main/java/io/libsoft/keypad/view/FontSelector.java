package io.libsoft.keypad.view;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Font;

public class FontSelector extends ComboBox<String> {


  public FontSelector() {
    setItems(FXCollections.observableArrayList(Font.getFamilies()));

  }
}
