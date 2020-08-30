package io.libsoft.keypad.model.internal;

import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public class ConnectionHandler implements MessageHandling {



  private final List<Connection> connections = new LinkedList<>();


  public ConnectionHandler() {

  }

  public void addConnection(Socket acceptedSocket){
    connections.add(new Connection(acceptedSocket, this));
  }



  @Override
  public void handle(String message) {

  }
}
