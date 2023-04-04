package com.project.db;


import java.awt.*;
import java.net.URI;
import java.net.URL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class dcontrol implements Initializable {
    @FXML
    private Button student;
    @FXML
    private Button home;
    @FXML
    private Button Teacher;
    @FXML
    private Label userr;
    @FXML
    private Hyperlink forgotla;

    @FXML
    private TextField id_your;

    @FXML
    private Button loginbut;
    @FXML
    private Label erorlogin;
    @FXML
    private PasswordField u_pass;
    private Connection connect;
    private Statement statement;
    private PreparedStatement prepare;
    private ResultSet result;



    private double x = 0;
    private double y = 0;
    @FXML
    private ImageView Gmail;
    @FXML
    private ImageView facebook;

    public void acount(){
       // userr.setText(user.username);

    }


    public void navButton(){
        home.setOnMouseClicked((MouseEvent event) ->{

            home.setStyle("-fx-background-color: #444b56");

            student.setStyle("-fx-background-color: #444b56");
            Teacher.setStyle("-fx-background-color: #444b56");

        });
        student.setOnMouseClicked((MouseEvent event) ->{

            home.setStyle("-fx-background-color: #444b56");

            student.setStyle("-fx-background-color: #444b56");
            Teacher.setStyle("-fx-background-color: #444b56");


        });
        Teacher.setOnMouseClicked((MouseEvent event) ->{

            home.setStyle("-fx-background-color: #444b56");
            student.setStyle("-fx-background-color: #444b56");
            Teacher.setStyle("-fx-background-color: #444b56");


        });

    }
    public void login(){
        String idd=id_your.getText();
        String pas=u_pass.getText();
        if(idd.isEmpty()||pas.isEmpty()){
            erorlogin.setText("        Please enter your ID and password");
        }else{
        erorlogin.setText(" ");
        connect=dbase.connectdb();
        String sql="SELECT * FROM MANGER WHERE MID=? and PASSWORD=?";
        String sql1="SELECT * FROM STUDENT WHERE STUID=? and PASWORD=?";
        String sql2="SELECT * FROM TEACHER WHERE TUID=? and PASSWORD=?";
        try {
            prepare=connect.prepareStatement(sql);
            prepare.setString(1,id_your.getText());
            prepare.setString(2,u_pass.getText());
            result= prepare.executeQuery();
            if(result.next()){
                /*Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sucsess");
                alert.setHeaderText(null);
                alert.setContentText("Sucsess login");
                alert.showAndWait();
                System.out.println("ss");*/
                loginbut.getScene().getWindow().hide();
            try {
                Parent roo= FXMLLoader.load(getClass().getResource("manger.fxml"));
                Scene scene=new Scene(roo);
                Stage stage=new Stage();
                stage.initStyle(StageStyle.TRANSPARENT);
                stage.setScene(scene);
                stage.show();

            }catch (Exception e){
                Logger.getLogger(HelloController.class.getName()).log(Level.SEVERE, null, e);

            }


            }else{
                prepare=connect.prepareStatement(sql1);
                prepare.setString(1,id_your.getText());
                prepare.setString(2,u_pass.getText());
                result= prepare.executeQuery();
                if(result.next()){
                    userstu.username=result.getString("FNAME")+" "+result.getString("LNAME");
                    userstu.id=result.getInt("STUID");
                    userstu.email=result.getString("EMAIL");
                    userstu.plang=result.getString("PROLANGUGE");
                    userstu.gander=result.getString("GANDER");
                    userstu.age=result.getString("BDATA");
                    /*Alert alert=new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Sucsess");
                    alert.setHeaderText(null);
                    alert.setContentText("Sucsess login");
                    alert.showAndWait();
                    System.out.println("ss");*/
                    loginbut.getScene().getWindow().hide();

                    Parent roo= FXMLLoader.load(getClass().getResource("testt.fxml"));
                    Scene scene=new Scene(roo);
                    Stage stage=new Stage();
                    stage.initStyle(StageStyle.TRANSPARENT);
                    stage.setScene(scene);
                    stage.show();

            }else{
                    prepare=connect.prepareStatement(sql2);
                    prepare.setString(1,id_your.getText());
                    prepare.setString(2,u_pass.getText());
                    result= prepare.executeQuery();
                    if(result.next()){
                        user.username=result.getString("FNAME")+" "+result.getString("LNAME");
                        user.id=result.getInt("TUID");
                        user.email=result.getString("EMAIL");
                        user.plang=result.getString("PRO_LANGUAGES");
                        user.gander=result.getString("GANDER");
                        user.sal=result.getInt("SALARY");
                        user.age=result.getString("BDATA");
                      /*  Alert alert=new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Sucsess");
                        alert.setHeaderText(null);
                        alert.setContentText("Sucsess login");
                        alert.showAndWait();
                        System.out.println("ss");*/
                        loginbut.getScene().getWindow().hide();

                        Parent ro= FXMLLoader.load(getClass().getResource("teacherli.fxml"));
                        Scene scene=new Scene(ro);
                        Stage stage=new Stage();
                        stage.initStyle(StageStyle.TRANSPARENT);
                        stage.setScene(scene);
                        stage.show();
                }else{
                        erorlogin.setText("               Invalid ID and/or password.");
                   /* Alert alert=new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Errormasege");
                    alert.setHeaderText(null);
                    alert.setContentText("Woring user name or password?!");
                    System.out.println("not");
                    alert.showAndWait();*/
                    }
                }


                }
            /*

            }*/
        }catch (Exception e){

        }
    }}
    @FXML
    private ImageView eye;

    @FXML
    private ImageView eyec;
    @FXML
    private TextField shpas;


    public  void ext(){
        System.exit(0);
    }
    public void ey(){
        eyec.setOnMouseClicked((MouseEvent event) ->{
            shpas.setText(u_pass.getText());
            shpas.setVisible(true);
            u_pass.setVisible(false);
            eye.setVisible(true);
            eyec.setVisible(false);

        });
        eye.setOnMouseClicked((MouseEvent event) ->{
            u_pass.setText(shpas.getText());
            u_pass.setVisible(true);
            shpas.setVisible(false);
            eyec.setVisible(true);
            eye.setVisible(false);

        });
    }
    public void sochal(){
        Gmail.setOnMouseClicked((MouseEvent event) ->{
            try {
                Desktop d=Desktop.getDesktop();
                d.browse(new URI("mailto:support@codeacademi2022@gmail.com"));
            }catch (Exception e){
                System.out.println("NO WEP");
            }

        });
        facebook.setOnMouseClicked((MouseEvent event) ->{
            try {
                Desktop d=Desktop.getDesktop();
                d.browse(new URI("https://www.facebook.com/adam.akrm.2/"));
            }catch (Exception e){
                System.out.println("NO WEP");
            }

        });
    }

    public void initialize(URL ur, ResourceBundle r){
        acount();
    }
}
