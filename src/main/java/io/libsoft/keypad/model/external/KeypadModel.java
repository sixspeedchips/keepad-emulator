package io.libsoft.keypad.model.external;

import io.libsoft.keypad.model.network.Connection;
import io.libsoft.keypad.model.network.MessageHandling;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class KeypadModel implements MessageHandling {


  private Connection connection;
  private Socket socket;
  private final ExecutorService es = Executors.newCachedThreadPool();

  public KeypadModel() {
    try {
      System.out.println("Keypad model starting...");
      socket = new Socket(InetAddress.getLoopbackAddress(), 10000);
      connection = new Connection(socket, this);

    } catch (IOException e) {
      e.printStackTrace();
    }
  }


  public void sendMessage(String message) {
    connection.sendMessage(message);
  }


  @Override
  public void handleMessage(String message) {
    System.out.println(message);
  }
}
