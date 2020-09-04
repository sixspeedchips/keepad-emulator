package io.libsoft.keypad.model.internal;

import io.libsoft.keypad.model.network.ConnectionHandler;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class ValidatorModel implements Runnable {


  private final ConnectionHandler connectionHandler = new ConnectionHandler();
  private ServerSocket serverSocket;
  private boolean running;

  public ValidatorModel() {

    try {
      serverSocket = new ServerSocket(8888, 10, InetAddress.getLoopbackAddress());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


  @Override
  public void run() {
    running = true;
    System.out.println("Lock validation service running...");
    while (running) {
      try {
        System.out.println(
            "Awaiting keypads on port: " + serverSocket.getLocalPort() + " Host: " + serverSocket.getInetAddress());
        Socket accepted = serverSocket.accept();
        connectionHandler.addConnection(accepted);

        System.out.println("Accepted " + accepted);
      } catch (IOException ignored) {
      }

    }
  }

  public void quit() {
    running = false;
  }
}
