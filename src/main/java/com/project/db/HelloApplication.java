package com.project.db;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.mail.MessagingException;
import java.io.IOException;

public class HelloApplication extends Application {
    private double x=0;
    private double y=0;
    @Override
    public void start(Stage stage) throws IOException {

        // FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));

        Parent root=FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        Scene scene = new Scene(root);
        //stage.setTitle("Hello!");
       root.setOnMousePressed((MouseEvent event) ->{
            x=event.getSceneX();
            y=event.getSceneY();
       } );
       root.setOnMouseDragged((MouseEvent event )->{
           stage.setX(event.getSceneX()-x);
           stage.setY(event.getSceneY()-y);
       });
       stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws MessagingException {
        launch(args);
       // emailcode.sendMailTo("a","a","a","a");
    }
}