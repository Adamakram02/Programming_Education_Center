package com.project.db;

import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import java.awt.Desktop;
import java.net.URI;
import java.net.URL;
import java.util.ResourceBundle;
//import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
//import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
//import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.io.InputStream;
import java.net.URL;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class stupage implements Initializable {
    @FXML
    private TextField ages;



    @FXML
    private Button compailer;


    @FXML
    private TextField emailss;





    @FXML
    private TextField idss;





    @FXML
    private Button logout;

    @FXML
    private TextField nas;



    @FXML
    private TextField pss;



    @FXML
    private TextField sexs;

    @FXML
    private Button shows;

    @FXML
    private Label ss;



    @FXML
    private TableColumn<tsk, String> Dtask1;

    @FXML
    private TableColumn<tsk, String> Sympoll1;

    @FXML
    private TableColumn<tsk, String> TASKTITLE1;





    @FXML
    private TableColumn<tsk, Integer> idte1;



    @FXML
    private Button logout1;



    @FXML
    private AnchorPane pirsan;

    @FXML
    private Button pirsonalb;



    @FXML
    private Button rep;






    tsk taskk1;
    @FXML
    private TableView<tsk> tasktable1;

    @FXML
    private AnchorPane thmoe;

    @FXML
    private AnchorPane ts;

    ObservableList<tsk>  tasklist1 = FXCollections.observableArrayList();

    @FXML
    private AnchorPane ttexhr;
    Connection conect;
    PreparedStatement pre;
    ResultSet res;
    @FXML
    void ext(ActionEvent event) {
        System.exit(0);
    }
    private void refrshtask() {
        conect=dbase.connectdb();
        try {
            tasklist1.clear();
            //String q="Select TUID FROM TEACHER WHERE PROLANGUGE='"+userstu.plang+"'";
           // System.out.println(q);

            String query = "select * from task where teid=(select  TUID from teacher where PRO_LANGUAGES='"+userstu.plang+"' )";
            pre =  conect.prepareStatement(query);
            res = pre.executeQuery();
            System.out.println("yy");
            while (res.next()){
                tasklist1.add(new tsk(
                        res.getInt("TASKID"),
                        res.getString("TASKTITEL"),
                        res.getString("DESCRIPTIONTASK"),
                        res.getString("TEID")
                ));

                tasktable1.setItems(tasklist1);
            }


        } catch (SQLException ex) {
            System.out.println("NN");
            //Logger.getLogger(HelloController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void loadDatetask() {
        conect = dbase.connectdb();
        refrshtask();
        idte1.setCellValueFactory(cellData -> cellData.getValue().gettaskid().asObject());
        TASKTITLE1.setCellValueFactory(cellData -> cellData.getValue().gettitel());
        Dtask1.setCellValueFactory(cellData -> cellData.getValue().getddtask());

        Callback<TableColumn<tsk, String>, TableCell<tsk, String>> cellFoctory = (TableColumn<tsk, String> param) -> {
            // make cell containing buttons
            final TableCell<tsk, String> cell = new TableCell<tsk, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        Button editIcon = new Button("View the task");

                        editIcon.setStyle(
                                " -fx-background-color: #00E676;"
                                        + "-fx-background-radius: 10px;;"
                                        + "-fx-cursor: hand;" +
                                        "-fx-text:#fff;"
                        );
                        editIcon.setOnMouseClicked((MouseEvent event) -> {

                            taskk1 = tasktable1.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("Showtas.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {

                                Logger.getLogger(HelloController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            shw aa = loader.getController();
                            aa.settxt(taskk1.gettaskid(),taskk1.gettitel(),taskk1.getddtask());
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();
                        });

                        HBox managebtn = new HBox(editIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
        Sympoll1.setCellFactory(cellFoctory);
        tasktable1.setItems(tasklist1);
    }
    @FXML
    void logout(ActionEvent event) {
        try {
            if(event.getSource()==logout){
                Parent parent = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
                Scene scene = new Scene(parent);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.initStyle(StageStyle.TRANSPARENT);
                stage.show();
                logout.getScene().getWindow().hide();
            }
        }catch (Exception e){

        }
    }
    public void comp(){
        try {
            Desktop d=Desktop.getDesktop();
            d.browse(new URI("https://www.codechef.com/ide"));
        }catch (Exception e){
            System.out.println("NO WEP");
        }

    }
    @FXML
    private Button homp;
    @FXML
    private Button tsks;
    @FXML
    private Button showtasks;

    public void changePage(){

        if(homp.isFocused()){

            thmoe.setVisible(true);
            ts.setVisible(false);
        }else if(tsks.isFocused()){
            totaltask();
            thmoe.setVisible(false);
            ts.setVisible(true);


        }else if (showtasks.isFocused()){
            totaltask();
            thmoe.setVisible(false);
            ts.setVisible(true);
        }
    }
    public void nacolor(){
        homp.setOnMouseClicked((MouseEvent event) ->{

            homp.setStyle("-fx-background-color: #444b56");

            tsks.setStyle(" -fx-background-color: #39424e");

        });
        tsks.setOnMouseClicked((MouseEvent event) ->{

            tsks.setStyle("-fx-background-color: #444b56");

            homp.setStyle("-fx-background-color: #39424e");


        });
    }

    public void pirs(){
        rep.setVisible(false);
        if(pirsonalb.isFocused()){
            pirsan.setVisible(true);
            pirsonalb.setVisible(false);
            rep.setVisible(true);
        }else if(rep.isFocused()){
            pirsan.setVisible(false);
            pirsonalb.setVisible(true);
            rep.setVisible(false);
        }
    }

    public void acou(){
        idss.setText(String.valueOf(userstu.id));
        nas.setText(userstu.username);
        emailss.setText(userstu.email);
        ages.setText(userstu.age);
        sexs.setText(userstu.gander);
        pss.setText(userstu.plang);

        idss.setEditable(false);
        nas.setEditable(false);
        emailss.setEditable(false);
        ages.setEditable(false);
        sexs.setEditable(false);
        pss.setEditable(false);
    }
    @FXML
    private Label tsklab;
    public void totaltask(){

        Connection connect = dbase.connectdb();

        String sql = "select count(TASKID) from task where teid=(select  TUID from teacher where PRO_LANGUAGES='"+userstu.plang+"' )";

        try{

            PreparedStatement prepare = connect.prepareStatement(sql);
            ResultSet result = prepare.executeQuery();

            while(result.next()){

                String totalGraduated = result.getString("count(TASKID)");

                tsklab.setText(totalGraduated);

            }

        }catch(Exception e){}

    }
    public void initialize(URL ur, ResourceBundle r){
        acou();
        loadDatetask();
        totaltask();
    }
}
