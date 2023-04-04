package com.project.db;

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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Teacherpage implements Initializable {

    @FXML
    private Button exilo;

    private Connection conect;
    private Statement statt;
    private PreparedStatement pre;
    private ResultSet res;
    @FXML
    private TableColumn<stut, String> bdates;
    @FXML
    private TableColumn<stut, String> pls;
    @FXML
    private TableView<stut> stutab;
    @FXML
    private TableColumn<stut, String> edits;

    @FXML
    private TableColumn<stut, String> emails;


    @FXML
    private TableColumn<stut, String> fnames;




    @FXML
    private TableColumn<stut, String> ganders;



    @FXML
    private TableColumn<stut, Integer> ids;



    @FXML
    private TableColumn<stut, String> lnames;
    @FXML
    private TableColumn<tsk, String> Sympolle;


    @FXML
    private Button teacherb;

    @FXML
    private AnchorPane thmoe;

    @FXML
    private AnchorPane ts;
    @FXML
    private Button pirsonalb;
    @FXML
    private AnchorPane ttexhr;
    @FXML
    private Button studentb;
    @FXML
    private Button homeb;
    @FXML
    private Button logout;


    @FXML
    private Button vstu;
    @FXML
    private Button shows;

    @FXML
    private Button vteacher;
    @FXML
    private Label ss;

    public void logout(ActionEvent event){
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
    public void nacolor(){
        homeb.setOnMouseClicked((MouseEvent event) ->{

            homeb.setStyle("-fx-background-color: #444b56");

            studentb.setStyle(" -fx-background-color: #39424e");

            teacherb.setStyle(" -fx-background-color: #39424e");

        });
        studentb.setOnMouseClicked((MouseEvent event) ->{

            studentb.setStyle("-fx-background-color: #444b56");

            homeb.setStyle("-fx-background-color: #39424e");

            teacherb.setStyle("-fx-background-color: #39424e");

        });
        teacherb.setOnMouseClicked((MouseEvent event) ->{

            teacherb.setStyle("-fx-background-color: #444b56");

            homeb.setStyle("-fx-background-color: #39424e");

            studentb.setStyle("-fx-background-color: #39424e");

        });
    }

    public void changePage(){

        if(homeb.isFocused()){
            totalStudent();
            thmoe.setVisible(true);
            ts.setVisible(false);
            ttexhr.setVisible(false);

        }else if(studentb.isFocused()){

            thmoe.setVisible(false);
            ts.setVisible(true);
            ttexhr.setVisible(false);

        }else if(teacherb.isFocused()){

            thmoe.setVisible(false);
            ts.setVisible(false);
            ttexhr.setVisible(true);

        }

    }
    public  void ext(){
        System.exit(0);
    }


    tsk taskk=null;
    ObservableList<stut> stulist= FXCollections.observableArrayList();
    ObservableList<tsk> tasklist= FXCollections.observableArrayList();
    ObservableList<teacheer>tealist= FXCollections.observableArrayList();



    @FXML
    private TableColumn<tsk, String> Dtask;
    @FXML
    private TableColumn<tsk, Integer> idte;

    @FXML
    private TableColumn<tsk, String> TASKTITLE;
    @FXML
    private TableView<tsk> tasktable;

    @FXML
    private void refrshtask() {
        conect=dbase.connectdb();
        try {
            tasklist.clear();
            String query = "SELECT * FROM Task where TEID='"+user.id+"'";
            pre =  conect.prepareStatement(query);
            res = pre.executeQuery();
            System.out.println("yy");
            while (res.next()){
                tasklist.add(new tsk(
                        res.getInt("TASKID"),
                        res.getString("TASKTITEL"),
                        res.getString("DESCRIPTIONTASK"),
                        res.getString("TEID")
                ));

                tasktable.setItems(tasklist);
            }


        } catch (SQLException ex) {
            System.out.println("NN");
            //Logger.getLogger(HelloController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


    //  ObservableList<stut>  StudentList = FXCollections.observableArrayList();

    @FXML
    private void addtsk(MouseEvent event)  {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("Addtask.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
            System.out.println("dd");
        } catch (IOException ex) {
            System.out.println("no");
            Logger.getLogger(HelloController.class.getName()).log(Level.SEVERE, null, ex);

        }

    }
    private void loadDatetasks() {
        conect = dbase.connectdb();
        refrshtask();
        idte.setCellValueFactory(cellData -> cellData.getValue().gettaskid().asObject());
        TASKTITLE.setCellValueFactory(cellData -> cellData.getValue().gettitel());
        Dtask.setCellValueFactory(cellData -> cellData.getValue().getddtask());

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

                        Button deleteIcon = new Button("delete");
                        Button editIcon = new Button("edit");

                        deleteIcon.setStyle(
                                "-fx-background-color: #ff1744;" +
                                        "    -fx-background-radius: 10px;" +
                                        "    -fx-cursor: hand;" +
                                        "-fx-text:#fff;"
                        );
                        editIcon.setStyle(
                                " -fx-background-color: #00E676;"
                                        + "-fx-background-radius: 10px;;"
                                        + "-fx-cursor: hand;" +
                                        "-fx-text:#fff;"

                        );
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {

                            try {
                                taskk = tasktable.getSelectionModel().getSelectedItem();
                                //System.out.println();
                                String query = "DELETE FROM Task WHERE TASKID  ="+taskk.gettaskid().intValue();
                                Connection conect1 = dbase.connectdb();
                                PreparedStatement preparedStatement = conect1.prepareStatement(query);
                                preparedStatement.executeQuery();
                                refrshtask();

                            } catch (SQLException ex) {
                                System.out.println("kinan");
                                Logger.getLogger(HelloController.class.getName()).log(Level.SEVERE, null, ex);
                            }





                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {

                            taskk = tasktable.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("Addtask.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {

                                Logger.getLogger(HelloController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            addtask aa = loader.getController();
                            aa.setUpdatee(true);
                            // System.out.println(student.getberth().getValue());
                            aa.settxt(taskk.gettaskid(),taskk.gettitel(),taskk.getddtask());
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();




                        });

                        HBox managebtn = new HBox(editIcon, deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
        Sympolle.setCellFactory(cellFoctory);
        tasktable.setItems(tasklist);
    }

    //**************************************************************

    //**************************************************************
    private Connection conect1;
    private Statement statt1;
    private PreparedStatement pre1;
    private ResultSet res1;
    public ObservableList<stut>listdata(){
        ObservableList<stut>listd=FXCollections.observableArrayList();
        conect1=dbase.connectdb();
        System.out.println(user.plang);
        String ss="SELECT * FROM STUDENT where prolanguge='"+user.plang+"'";
        try {

            statt1 =conect1.createStatement();
            res1=statt1.executeQuery(ss);
            stut st;
            while (res1.next()){
                st=new stut(res1.getInt("STUID"),
                        res1.getString("FNAME"),
                        res1.getString("LNAME"),
                        res1.getString("EMAIL"),
                        res1.getString("BDATA"),
                        res1.getString("GANDER"),
                        res1.getString("PROLANGUGE"),
                        res1.getString("PASWORD"));
                listd.addAll(st);
            }
        }catch (Exception e){
            System.out.println("iam");
            e.printStackTrace();
        }
        return listd;
    }
   /* public ObservableList<teacheer>listdatate(){
        ObservableList<teacheer>listdte=FXCollections.observableArrayList();
        Connection conect2=dbase.connectdb();
        String ss="SELECT * FROM TEACHER";
        try {

            Statement statt2 =conect2.createStatement();
            ResultSet res2=statt2.executeQuery(ss);
            // stut st;
            teacheer te;
            while (res2.next()){
                te=new teacheer(
                        res2.getInt("TUID"),
                        res2.getString("FNAME"),
                        res2.getString("LNAME"),
                        res2.getString("EMAIL"),
                        res2.getInt("SALARY"),
                        res2.getString("BDATA"),
                        res2.getString("GANDER"),
                        res2.getString("PRO_LANGUAGES"),
                        res2.getString("PASSWORD"));
                listdte.addAll(te);
                /*System.out.println(res1.getInt("STUID")+" "+
                        res1.getString("FNAME")+" "+
                        res1.getString("LNAME")+" "+
                        res1.getString("EMAIL")+" "+
                        res1.getDate("BDATA")+" "+
                        res1.getString("GANDER")+" "+
                        res1.getString("PROLANGUGE")+" "+
                        res1.getString("PASWORD"));*/
      /*      }
        }catch (Exception e){
            System.out.println("iam");
            e.printStackTrace();
        }
        return listdte;
    }*/
    private ObservableList<teacheer>shwte;

    private ObservableList<stut>shw;
    public void showd(){
        try {

            ids.setCellValueFactory(cellData -> cellData.getValue().getid().asObject());
            fnames.setCellValueFactory(cellData -> cellData.getValue().getfname());
            lnames.setCellValueFactory(cellData -> cellData.getValue().getlname());
            emails.setCellValueFactory(cellData -> cellData.getValue().getemail());
            bdates.setCellValueFactory(cellData -> (ObservableValue<String>) cellData.getValue().getberth());
            pls.setCellValueFactory(cellData -> cellData.getValue().getplan());
            ganders.setCellValueFactory(cellData -> cellData.getValue().getgan());
            System.out.println("oooo");
            shw=listdata();
            stutab.setItems(shw);
        }catch (Exception e){
            System.out.println("adam");
            Logger.getLogger(HelloController.class.getName()).log(Level.SEVERE, null, e);
        }

    }
    public void vt(){
        if(shows.isFocused()){

            thmoe.setVisible(false);
            ts.setVisible(true);
            ttexhr.setVisible(false);

            studentb.setStyle("-fx-background-color: #444b56");

            homeb.setStyle("-fx-background-color: #39424e");

            teacherb.setStyle("-fx-background-color: #39424e");

        }

    }
    public void totalStudent(){

        Connection connect = dbase.connectdb();
//        WE WILL COUNT THE SURNAME WHICH INDICATES THE TOTAL OF STUDENT
        String sql = "SELECT count(STUID) FROM STUDENT where prolanguge='"+user.plang+"'";

        try{

            PreparedStatement prepare = connect.prepareStatement(sql);
            ResultSet result = prepare.executeQuery();

            while(result.next()){

                String totalStudent = result.getString("count(STUID)");

                ss.setText(totalStudent);

            }

        }catch(Exception e){}

    }

    @FXML
    private TextField taget;

    @FXML
    private TextField temailt;
    @FXML
    private TextField tidt;

    @FXML
    private TextField tnamet;

    @FXML
    private TextField tpt;
    @FXML
    private TextField tsext;

    @FXML
    private TextField tst;
        public void ac(){
            tidt.setText(String.valueOf(user.id));
            tst.setText(String.valueOf(user.sal));
            tnamet.setText(user.username);
            temailt.setText(user.email);
            taget.setText(user.age);
            tsext.setText(user.gander);
            tpt.setText(user.plang);

            tidt.setEditable(false);
            tst.setEditable(false);
            tnamet.setEditable(false);
            temailt.setEditable(false);
            taget.setEditable(false);
            tsext.setEditable(false);
            tpt.setEditable(false);

        }
        @FXML
        private Label stunum;
    public void totalstu(){

        Connection connect = dbase.connectdb();

        String sql = "SELECT count(STUID) FROM STUDENT where prolanguge='"+user.plang+"'";

        try{

            PreparedStatement prepare = connect.prepareStatement(sql);
            ResultSet result = prepare.executeQuery();

            while(result.next()){

                String totalGraduated = result.getString("count(STUID)");

                stunum.setText(totalGraduated);

            }

        }catch(Exception e){}

    }
    @FXML
    private AnchorPane pirsan;
    @FXML
    private Button rep;
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
    @Override
    public void initialize(URL ur, ResourceBundle r){
        //loadDatete();
       // loadDate();
        loadDatetasks();
        showd();
        totalstu();
        ac();
    }
}
