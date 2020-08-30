package io.libsoft.keypad.model.internal;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class ControllerModel implements Runnable {


  private final ConnectionHandler connectionHandler= new ConnectionHandler();

  private final ServerSocket serverSocket = new ServerSocket(10001, 2, InetAddress.getLoopbackAddress());
  private final ScheduledExecutorService es = Executors.newScheduledThreadPool(1);
  private boolean running;

  public ControllerModel() throws IOException {

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
        System.out.println("Accepted " + accepted);
      } catch (IOException ignored) {
      }

    }
  }

  public void quit(){
    es.shutdownNow();
    running = false;
  }
}
