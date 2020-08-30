package io.libsoft.keypad.model.internal;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Combination {



  private List<String> combo = new LinkedList<>();

  {
    combo.addAll(Arrays.asList("1","2","3","4","5"));
  }

  public List<String> getCombo() {
    return combo;
  }
}
