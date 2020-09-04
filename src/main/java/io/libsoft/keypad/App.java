package io.libsoft.keypad;

import io.libsoft.keypad.controller.DrawController;
import io.libsoft.keypad.model.external.KeypadModel;
import io.libsoft.keypad.model.internal.ValidatorModel;
import io.libsoft.keypad.util.Props;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {


  private static final String LAYOUT_RESOURCE = "layout.fxml";
  private final ExecutorService es = Executors.newCachedThreadPool();
  private final ScheduledExecutorService ses = Executors.newScheduledThreadPool(1);
  private DrawController controller;
  private ValidatorModel validatorModel;
  private KeypadModel keypadModel;


  public static void main(String[] args) throws IOException {
    new App().initialize();
//    launch(args);
  }

  private void initialize() {

    Props.get();
//    validatorModel = new ValidatorModel();
//    es.submit(validatorModel);
    keypadModel = new KeypadModel();
    ses.scheduleAtFixedRate(()->keypadModel.sendMessage("1"), 0,1000, TimeUnit.MILLISECONDS);

//    controller.setKeypadModel(keypadModel);

  }

  @Override
  public void start(Stage stage) throws Exception {

    ClassLoader classLoader = getClass().getClassLoader();
    FXMLLoader fxmlLoader = new FXMLLoader(classLoader.getResource(LAYOUT_RESOURCE));
    Parent root = fxmlLoader.load();
    controller = fxmlLoader.getController();
    Scene scene = new Scene(root);
    stage.setResizable(true);
    stage.setScene(scene);
    stage.sizeToScene();
    stage.setTitle("Keypad");
    stage.show();
    setStageSize(stage, root);
    es.submit(this::initialize);

//    stage.setOnCloseRequest(event -> {
//      System.exit(-1);
//    });
  }


  @Override
  public void stop() throws Exception {
    controller.stop();
    super.stop();
  }

  private void setStageSize(Stage stage, Parent root) {
    Bounds bounds = root.getLayoutBounds();
    stage.setMinWidth(root.minWidth(-1) + stage.getWidth() - bounds.getWidth());
    stage.setMinHeight(root.minHeight(-1) + stage.getHeight() - bounds.getHeight());
  }

}
