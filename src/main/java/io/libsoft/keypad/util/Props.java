package io.libsoft.keypad.util;


import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import lombok.Data;

@Data
public class Props {


  public static Props get() {
    return InstanceHolder.INSTANCE;
  }

  private static class InstanceHolder {

    private final static Props INSTANCE;

    static {
      INSTANCE = new Props();
      Properties prop = new Properties();
      try (InputStream inputStream = Props.class.getClassLoader().getResourceAsStream("config.properties")) {
        prop.load(inputStream);
        // example how to load properties from file
        // INSTANCE.nodes = Integer.parseInt(prop.getProperty("nodes", String.valueOf(INSTANCE.nodes)));


      } catch (IOException ioException) {

      } catch (NullPointerException nullPointerException){
        System.out.println("Failed to load properties.");
        System.out.println("Config file not found.");
        System.out.println("Searched paths:");
        System.out.println(Props.class.getResource("/"));
      }
    }

  }


}
