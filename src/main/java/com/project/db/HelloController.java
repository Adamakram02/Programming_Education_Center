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
//import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.swing.JRViewer;
import oracle.jdbc.datasource.impl.OracleDataSource;

import javax.swing.*;
import java.io.*;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/*import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.swing.JRViewer;*/


public class HelloController implements Initializable {

    @FXML
    private Button exilo;

    private Connection conect;
    private Statement statt;
    private PreparedStatement pre;
    private ResultSet res;
    @FXML
    private TableColumn<stut, String> bdateclm;

    @FXML
    private TableColumn<stut, String> editclm;

    @FXML
    private TableColumn<stut, String> emailclm;

    @FXML
    private TableColumn<stut, String> fnameclm;

    @FXML
    private TableColumn<stut, String> ganderclm;

    @FXML
    private TableColumn<stut, Integer> idclm;

    @FXML
    private TableColumn<stut, String> lnameclm;

    @FXML
    private TableColumn<stut, String> passclm;

    @FXML
    private TableColumn<stut, String> plclm;

    @FXML
    private TableView<stut> stutable;
    @FXML
    private Button teacherb;

    @FXML
    private AnchorPane thmoe;

    @FXML
    private AnchorPane ts;

    @FXML
    private AnchorPane ttexhr;
    @FXML
    private Button studentb;
    @FXML
    private Button homeb;
    @FXML
    private Button logout;
    @FXML
    private TableColumn<teacheer, String> passwordte;
    @FXML
    private TableColumn<teacheer, String> prollangugete;
    @FXML
    private TableColumn<teacheer, Integer> salaryte;
    @FXML
    private TableColumn<teacheer, String> lnamete;

    @FXML
    private TableColumn<teacheer, Integer> idte;
    @FXML
    private TableColumn<teacheer, String> fnamete;

    @FXML
    private TableColumn<teacheer, String> gander;
    @FXML
    private TableColumn<teacheer, String> emilte;
    @FXML
    private TableColumn<teacheer, String> syte;
    @FXML
    private TableView<teacheer> tetable;
    @FXML
    private TableColumn<teacheer, String> bdatete;
    @FXML
    private Button vstu;

    @FXML
    private Button vteacher;
    @FXML
    private Label numst;

    @FXML
    private Label numt;
    @FXML
    private Label pronum;
    @FXML
    private Label fees;
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
            totalte();
            totalpro();
            totalfees();


        }else if(studentb.isFocused()){

            thmoe.setVisible(false);
            ts.setVisible(true);
            ttexhr.setVisible(false);
            // showd();

           // nav_chart.setVisible(false);

        }else if(teacherb.isFocused()){

            thmoe.setVisible(false);
            ts.setVisible(false);
            ttexhr.setVisible(true);


        }

    }
    public  void ext(){
        System.exit(0);
    }
   /* public  void txtdis(){
        if(id_your.isFocused()){
            id_your.setStyle("-fx-background-color: #fff;"
                  +  "-fx-border-width:2px ");
            u_pass.setStyle("-fx-background-color: transparent;"
                    +  "-fx-border-width:2px ");
        }else if( u_pass.isFocused()){
            id_your.setStyle("-fx-background-color: transparent;"
                    +  "-fx-border-width:2px ");
            u_pass.setStyle("-fx-background-color: #fff;"
                    +  "-fx-border-width:2px ");
        }
    }*/


    ObservableList<stut>stulist= FXCollections.observableArrayList();
    ObservableList<teacheer>tealist= FXCollections.observableArrayList();


    @FXML
    private void addst(MouseEvent event)  {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("addstudent.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            System.out.println("no");
            Logger.getLogger(HelloController.class.getName()).log(Level.SEVERE, null, ex);

        }

    }
    @FXML
    private void addte(MouseEvent event)  {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("addteacher.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            // Logger.getLogger(HelloController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("no");
        }

    }
    @FXML
    private void prin(MouseEvent event)  {

        //Connection con=dbase.connectdb();
        Connection con;
        InputStream input;
        JasperDesign jasperDesign;
        JasperReport jasperReport;
        JasperPrint jasperPrint;
        OutputStream output;
        OracleDataSource ods;
        try {
            ods=new OracleDataSource();
            ods.setURL("jdbc:oracle:thin:@localhost:1521:XE");
            ods.setUser("adam");
            ods.setPassword("123321");
            con=ods.getConnection();
            input=new FileInputStream(new File("C:\\Users\\HP\\JaspersoftWorkspace\\pro\\stt.jrxml"));
            jasperDesign=JRXmlLoader.load(input);
            jasperReport=JasperCompileManager.compileReport(jasperDesign);
            jasperPrint=JasperFillManager.fillReport(jasperReport, null, con);
            //output= new FileOutputStream(new File("EmployeeReport.pdf"));
         //   JasperExportManager.exportReportToPdfStream(jasperPrint,output);
           /* InputStream inp=new FileInputStream("student.jrxml");
            JasperDesign jd= JRXmlLoader.load(inp);
            JasperReport jr= JasperCompileManager.compileReport(jd);
            JasperPrint jp= JasperFillManager.fillReport(jr,null,con);
            JFrame fr=new JFrame("Report");
            fr.getContentPane().add(new JRViewer(jp));
            fr.pack();
            fr.setVisible(true);*/
            input=new FileInputStream(new File("C:\\Users\\HP\\JaspersoftWorkspace\\pro\\stt.jrxml"));
            jasperDesign=JRXmlLoader.load(input);
            jasperReport=JasperCompileManager.compileReport(jasperDesign);
            jasperPrint=JasperFillManager.fillReport(jasperReport, null, con);
           // output= new FileOutputStream(new File("EmployeeReportq.pdf"));
            //JasperExportManager.exportReportToPdfStream(jasperPrint,output);


//you can also use the viewer, so replace the last two lines with
//the following code
            JFrame frame = new JFrame("Student Report");
            frame.getContentPane().add(new JRViewer(jasperPrint));
            frame.pack();
            frame.setVisible(true);

        } catch (Exception ex) {
             Logger.getLogger(HelloController.class.getName()).log(Level.SEVERE, null, ex);
           // System.out.println("no");
        }

    }
    @FXML
    private void printe(MouseEvent event)  {

        //Connection con=dbase.connectdb();
        Connection con;
        InputStream input;
        JasperDesign jasperDesign;
        JasperReport jasperReport;
        JasperPrint jasperPrint;
        OutputStream output;
        OracleDataSource ods;
        try {
            ods=new OracleDataSource();
            ods.setURL("jdbc:oracle:thin:@localhost:1521:XE");
            ods.setUser("adam");
            ods.setPassword("123321");
            con=ods.getConnection();
            input=new FileInputStream(new File("C:\\Users\\HP\\JaspersoftWorkspace\\pro\\teee.jrxml"));
            jasperDesign=JRXmlLoader.load(input);
            jasperReport=JasperCompileManager.compileReport(jasperDesign);
            jasperPrint=JasperFillManager.fillReport(jasperReport, null, con);
            input=new FileInputStream(new File("C:\\Users\\HP\\JaspersoftWorkspace\\pro\\teee.jrxml"));
            jasperDesign=JRXmlLoader.load(input);
            jasperReport=JasperCompileManager.compileReport(jasperDesign);
            jasperPrint=JasperFillManager.fillReport(jasperReport, null, con);
            JFrame frame = new JFrame("Teacher Report");
            frame.getContentPane().add(new JRViewer(jasperPrint));
            frame.pack();
            frame.setVisible(true);

        } catch (Exception ex) {
            Logger.getLogger(HelloController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    @FXML
    private void refrsh() {
        conect=dbase.connectdb();
        try {
            stulist.clear();
            String query = "SELECT * FROM STUDENT";
             pre =  conect.prepareStatement(query);
             res = pre.executeQuery();
            System.out.println("yy");
            while (res.next()){
                stulist.add(new stut(
                        res.getInt("STUID"),
                        res.getString("FNAME"),
                        res.getString("LNAME"),
                        res.getString("EMAIL"),
                        res.getString("BDATA"),
                        res.getString("GANDER"),
                        res.getString("PROLANGUGE"),
                        res.getString("PASWORD")));
                        stutable.setItems(stulist);
            }


        } catch (SQLException ex) {
            System.out.println("NN");
        }

    }
    @FXML
    private void refrshte() {
        Connection conect1=dbase.connectdb();
        try {
            tealist.clear();
            String query1 = "SELECT * FROM TEACHER";
            PreparedStatement pre1 =  conect1.prepareStatement(query1);
            ResultSet res1 = pre1.executeQuery();
            System.out.println("yy");
            while (res1.next()){
                tealist.add(new teacheer(
                        res1.getInt("TUID"),
                        res1.getString("FNAME"),
                        res1.getString("LNAME"),
                        res1.getString("EMAIL"),
                        res1.getInt("SALARY"),
                        res1.getString("BDATA"),
                        res1.getString("GANDER"),
                        res1.getString("PRO_LANGUAGES"),
                        res1.getString("PASSWORD")));
                tetable.setItems(tealist);
            }


        } catch (SQLException ex) {
            System.out.println("NN");
            Logger.getLogger(HelloController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    stut student;
  //  ObservableList<stut>  StudentList = FXCollections.observableArrayList();

    private void loadDate() {
        conect = dbase.connectdb();
        refrsh();
       idclm.setCellValueFactory(cellData -> cellData.getValue().getid().asObject());
       // idclm.setCellValueFactory(new PropertyValueFactory<>("Id"));
       fnameclm.setCellValueFactory(cellData -> cellData.getValue().getfname());
       //fnameclm.setCellValueFactory(new PropertyValueFactory<stut,String>("Fname"));
       lnameclm.setCellValueFactory(cellData -> cellData.getValue().getlname());
       emailclm.setCellValueFactory(cellData -> cellData.getValue().getemail());
       bdateclm.setCellValueFactory(cellData -> (ObservableValue<String>) cellData.getValue().getberth());
       plclm.setCellValueFactory(cellData -> cellData.getValue().getplan());
       ganderclm.setCellValueFactory(cellData -> cellData.getValue().getgan());
       passclm.setCellValueFactory(cellData -> cellData.getValue().getpass());

        Callback<TableColumn<stut, String>, TableCell<stut, String>> cellFoctory = (TableColumn<stut, String> param) -> {
            // make cell containing buttons
            final TableCell<stut, String> cell = new TableCell<stut, String>() {
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
                                student = stutable.getSelectionModel().getSelectedItem();
                                //System.out.println();
                                String query = "DELETE FROM STUDENT WHERE STUID  ="+student.getid().intValue();
                                Connection conect1 = dbase.connectdb();
                                PreparedStatement preparedStatement = conect1.prepareStatement(query);
                                preparedStatement.executeQuery();
                                refrsh();

                            } catch (SQLException ex) {
                                System.out.println("kinan");
                                Logger.getLogger(HelloController.class.getName()).log(Level.SEVERE, null, ex);
                            }





                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {

                            student = stutable.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("addstudent.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {

                                Logger.getLogger(HelloController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            AddStudentController addStudentController = loader.getController();
                            addStudentController.setUpdate(true);
                           // System.out.println(student.getberth().getValue());
                            addStudentController.setTextField(student.getid(),student.getfname()
                            ,student.getlname(),student.getemail(),student.getberth(),student.getgan(),student.getplan(),student.getpass());
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
       editclm.setCellFactory(cellFoctory);
       stutable.setItems(stulist);
    }
    //**************************************************************
    teacheer te;
    //ObservableList<teacheer>  telist = FXCollections.observableArrayList();

    private void loadDatete() {
        Connection conect1 = dbase.connectdb();
        refrshte();
        idte.setCellValueFactory(cellData -> cellData.getValue().getidd().asObject());
        fnamete.setCellValueFactory(cellData -> cellData.getValue().getfnamee());
        lnamete.setCellValueFactory(cellData -> cellData.getValue().getlnamee());
        emilte.setCellValueFactory(cellData -> cellData.getValue().getemaill());
        salaryte.setCellValueFactory(cellData -> cellData.getValue().getsalarry().asObject());
        bdatete.setCellValueFactory(cellData -> (ObservableValue<String>) cellData.getValue().getbertth());
        gander.setCellValueFactory(cellData -> cellData.getValue().getgann());
        prollangugete.setCellValueFactory(cellData -> cellData.getValue().getplann());
        passwordte.setCellValueFactory(cellData -> cellData.getValue().getpaass());

        Callback<TableColumn<teacheer, String>, TableCell<teacheer, String>> cellFoctory1 = (TableColumn<teacheer, String> param) -> {
            final TableCell<teacheer, String> cell1 = new TableCell<teacheer, String>() {
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
                                te = tetable.getSelectionModel().getSelectedItem();
                                //System.out.println();
                                String query = "DELETE FROM TEACHER WHERE TUID  ="+te.getidd().intValue();
                                Connection conect1 = dbase.connectdb();
                                PreparedStatement preparedStatement = conect1.prepareStatement(query);
                                preparedStatement.executeQuery();
                                refrshte();

                            } catch (SQLException ex) {
                                System.out.println("kinan");
                                Logger.getLogger(HelloController.class.getName()).log(Level.SEVERE, null, ex);
                            }





                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {

                            te = tetable.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("addteacher.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {

                                Logger.getLogger(HelloController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            AddTeacherController addt = loader.getController();
                            addt.setUpdate(true);
                            addt.setTextField(te.getidd(),te.getfnamee(),te.getlnamee(),te.getemaill(), te.getsalarry(),te.getbertth(),te.getgann(),te.getplann(),te.getpaass());
                            Parent parent1 = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent1));
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

            return cell1;
        };
        syte.setCellFactory(cellFoctory1);
        tetable.setItems(tealist);
    }
    //**************************************************************
   private Connection conect1;
    private Statement statt1;
    private PreparedStatement pre1;
    private ResultSet res1;
   public ObservableList<stut>listdata(){
        ObservableList<stut>listd=FXCollections.observableArrayList();
        conect1=dbase.connectdb();
        String ss="SELECT * FROM STUDENT";
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
    public ObservableList<teacheer>listdatate(){
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
            }
        }catch (Exception e){
            System.out.println("iam");
            e.printStackTrace();
        }
        return listdte;
    }
    private ObservableList<teacheer>shwte;
    public void showdte(){
        try {

            shwte=listdatate();
            idte.setCellValueFactory(a -> a.getValue().getidd().asObject());
            fnamete.setCellValueFactory(cellData -> cellData.getValue().getfnamee());
            lnamete.setCellValueFactory(cellData -> cellData.getValue().getlnamee());
            emilte.setCellValueFactory(cellData -> cellData.getValue().getemaill());
            salaryte.setCellValueFactory(cellData -> cellData.getValue().getsalarry().asObject());
            bdatete.setCellValueFactory(cellData ->  cellData.getValue().getbertth());
            prollangugete.setCellValueFactory(cellData -> cellData.getValue().getplann());
            gander.setCellValueFactory(cellData -> cellData.getValue().getgann());
            passwordte.setCellValueFactory(cellData -> cellData.getValue().getpaass());
           // System.out.println("oooo");
            tetable.setItems(shwte);
        }catch (Exception e){
            System.out.println("adam");
            Logger.getLogger(HelloController.class.getName()).log(Level.SEVERE, null, e);
        }

    }
    private ObservableList<stut>shw;
    public void showd(){
       try {

           idclm.setCellValueFactory(cellData -> cellData.getValue().getid().asObject());
          // idclm.setCellValueFactory(new PropertyValueFactory<>("Id"));
           fnameclm.setCellValueFactory(cellData -> cellData.getValue().getfname());
           //fnameclm.setCellValueFactory(new PropertyValueFactory<stut,String>("Fname"));
           lnameclm.setCellValueFactory(cellData -> cellData.getValue().getlname());
           //lnameclm.setCellValueFactory(new PropertyValueFactory<stut,String>("Lname"));
           emailclm.setCellValueFactory(cellData -> cellData.getValue().getemail());

          // emailclm.setCellValueFactory(new PropertyValueFactory<stut,String>("Email"));
           bdateclm.setCellValueFactory(cellData -> (ObservableValue<String>) cellData.getValue().getberth());
           plclm.setCellValueFactory(cellData -> cellData.getValue().getplan());
           ganderclm.setCellValueFactory(cellData -> cellData.getValue().getgan());
           passclm.setCellValueFactory(cellData -> cellData.getValue().getpass());
           // bdateclm.setCellValueFactory(new PropertyValueFactory<stut,Date>("Berh date"));
          // plclm.setCellValueFactory(new PropertyValueFactory<stut,String>("pro languge"));
          // ganderclm.setCellValueFactory(new PropertyValueFactory<stut,String>("Gander"));
          // passclm.setCellValueFactory(new PropertyValueFactory<stut,String>("Password"));
           System.out.println("oooo");
           shw=listdata();
           stutable.setItems(shw);
       }catch (Exception e){
           System.out.println("adam");
           Logger.getLogger(HelloController.class.getName()).log(Level.SEVERE, null, e);
       }

    }
    public void vt(){
        if(vstu.isFocused()){

            thmoe.setVisible(false);
            ts.setVisible(true);
            ttexhr.setVisible(false);

            studentb.setStyle("-fx-background-color: #444b56");

            homeb.setStyle("-fx-background-color: #39424e");

            teacherb.setStyle("-fx-background-color: #39424e");

        }else if(vteacher.isFocused()){

            thmoe.setVisible(false);
            ts.setVisible(false);
            ttexhr.setVisible(true);
            teacherb.setStyle("-fx-background-color: #444b56");

            homeb.setStyle("-fx-background-color: #39424e");

            studentb.setStyle("-fx-background-color: #39424e");
        }
    }
    public void totalStudent(){

        Connection connect = dbase.connectdb();
//        WE WILL COUNT THE SURNAME WHICH INDICATES THE TOTAL OF STUDENT
        String sql = "SELECT count(STUID) FROM STUDENT";

        try{

           PreparedStatement prepare = connect.prepareStatement(sql);
           ResultSet result = prepare.executeQuery();

            while(result.next()){

                String totalStudent = result.getString("count(STUID)");

                numst.setText(totalStudent);

            }

        }catch(Exception e){}

    }
    public void totalte(){

        Connection connect = dbase.connectdb();

        String sql = "SELECT count(TUID) FROM TEACHER ";

        try{

            PreparedStatement prepare = connect.prepareStatement(sql);
            ResultSet result = prepare.executeQuery();

            while(result.next()){

                String totalGraduated = result.getString("count(TUID)");

                numt.setText(totalGraduated);

            }

        }catch(Exception e){}

    }
    public void totalpro(){

        Connection connect = dbase.connectdb();

        String sql = "SELECT count(PRO_LANGUAGES) FROM TEACHER ";

        try{

            PreparedStatement prepare = connect.prepareStatement(sql);
            ResultSet result = prepare.executeQuery();

            while(result.next()){

                String totalGraduated = result.getString("count(PRO_LANGUAGES)");

                pronum.setText(totalGraduated);

            }

        }catch(Exception e){}

    }
    public void totalfees(){

        Connection connect = dbase.connectdb();

        String sql = "SELECT sum(SALARY) FROM TEACHER ";

        try{

            PreparedStatement prepare = connect.prepareStatement(sql);
            ResultSet result = prepare.executeQuery();

            while(result.next()){

                String totalGraduated = result.getString("sum(SALARY)");

                fees.setText(totalGraduated);

            }

        }catch(Exception e){}

    }
    @Override
    public void initialize(URL ur, ResourceBundle r){
        loadDatete();
       loadDate();
        vt();
        totalStudent();
        totalfees();
        totalte();
        totalpro();
    }

}