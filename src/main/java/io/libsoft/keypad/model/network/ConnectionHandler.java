package io.libsoft.keypad.model.network;

import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConnectionHandler implements MessageHandling {


  private final ExecutorService es = Executors.newCachedThreadPool();
  private final List<Connection> connections = new LinkedList<>();

  public void addConnection(Socket acceptedSocket) {
    connections.add(new Connection(acceptedSocket, this));
  }


  @Override
  public void handleMessage(String message) {
    System.out.println(message);
  }
}
