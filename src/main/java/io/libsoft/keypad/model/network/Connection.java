package io.libsoft.keypad.model.network;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Connection implements Runnable {

  private final Socket socket;
  private final MessageHandling messageHandler;
  private BufferedReader readerStream;
  private BufferedWriter writerStream;
  private boolean running;
  private ExecutorService es = Executors.newCachedThreadPool();

  public Connection(Socket socket, MessageHandling messageHandler) {
    this.socket = socket;
    this.messageHandler = messageHandler;
    try {
      writerStream = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
      readerStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    } catch (IOException e) {
      e.printStackTrace();
    }
    es.submit(this);
  }


  @Override
  public void run() {
    running = true;
    while (running) {
      try {
        String s = readerStream.readLine();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    System.out.println("Connection.run end");
  }

  private Optional<String> getMessage() {
    System.out.println("Connection.getMessage");
    Optional<String> message = Optional.empty();
    try {
      message = Optional.of(readerStream.readLine());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return message;
  }


  public void sendMessage(String message) {
    try {
      System.out.println("Connection.sendMessage");
      message += "\n";
      writerStream.write(message);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


  public void close() {
    running = false;
  }


}
