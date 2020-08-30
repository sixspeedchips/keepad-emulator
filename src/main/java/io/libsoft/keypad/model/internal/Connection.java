package io.libsoft.keypad.model.internal;

import io.libsoft.keypad.model.internal.MessageHandling;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Optional;

public class Connection implements Runnable {

  private final Socket socket;
  private final MessageHandling messageHandler;
  private BufferedReader reader;
  private BufferedWriter writer;
  private boolean running;

  public Connection(Socket socket, MessageHandling messageHandler) {
    this.socket = socket;
    this.messageHandler = messageHandler;
    try {
      writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
      reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    } catch (IOException ignored) {
    }
  }


  @Override
  public void run() {
    running = true;
    while (running) {

    }
  }

  private Optional<String> getMessage() {
    Optional<String> message = Optional.empty();
    try {
      message = Optional.of(reader.readLine());
    } catch (IOException ignored) {
    }
    return message;
  }

  public void close() {
    running = false;
  }


}
