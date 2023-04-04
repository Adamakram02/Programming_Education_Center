package com.project.db;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
//import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import javax.mail.MessagingException;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.net.URL;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


public class AddStudentController implements Initializable {
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
    private TextField berstu;

    @FXML
    private TextField emailstu;

    @FXML
    private TextField fnamestu;

    @FXML
    private ComboBox<String> ganderstu;

    @FXML
    private TextField lnamestu;

    @FXML
    private PasswordField passstu;

    @FXML
    private ComboBox<String> prstu;

    @FXML
    private TextField ssnsty;

    String query1 ;
    Connection connection ;
    ResultSet resultSet ;
    PreparedStatement preparedStatement;
    stut student ;
    private boolean update;
    int studentId;

    private String gender[]={"M","F"};
    public void combo(){
        List<String> list = new ArrayList<>();

        for(String data1: gender){

            list.add(data1);

        }

        ObservableList dataList = FXCollections.observableArrayList(list);

        ganderstu.setItems(dataList);
    }

   public void plang(){
        connection=dbase.connectdb();
        Statement st;
       ObservableList<String>ll= FXCollections.observableArrayList();

       try {
           resultSet=connection.createStatement().executeQuery("SELECT distinct  PRO_LANGUAGES FROM TEACHER ");
            while (resultSet.next()){
                ll.add(resultSet.getString("PRO_LANGUAGES"));
            }
       } catch (SQLException e) {
           System.out.println("hello");
       }
       prstu.setItems(null);
       prstu.setItems(ll);
   }


    @FXML
    private void save(MouseEvent event) throws MessagingException {

        connection = dbase.connectdb();
        String idd=ssnsty.getText();
        String fname = fnamestu.getText();
        String lname =  lnamestu.getText();
        String email = emailstu.getText();

        String birth = berstu.getText();
        String gander = String.valueOf(ganderstu.getValue());
        String passw = passstu.getText();
        String prolan = String.valueOf(prstu.getValue());
        if (idd.isEmpty()||fname.isEmpty() ||lname.isEmpty()|| birth.isEmpty() || gander.isEmpty() || email.isEmpty()|| passw.isEmpty() || prolan.isEmpty() ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All DATA");
            alert.showAndWait();

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Done");
            alert.showAndWait();
            getQuery();
            insert();
            clean();

        }

    }

    @FXML
    private void clean() {
        fnamestu.setText(null);
        lnamestu.setText(null);
        berstu.setText(null);
        emailstu.setText(null);
        ganderstu.setValue(null);
        prstu.setValue(null);
        passstu.setText(null);
        ssnsty.setText(null);

    }

    private void getQuery() {

        if (update == false) {
            System.out.println("1");
            query1 = "INSERT INTO STUDENT (STUID, FNAME, LNAME, EMAIL, BDATA, GANDER, PASWORD, PROLANGUGE) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        }else{
            System.out.println("2");
            query1 = "UPDATE student SET "
                    + "STUID=?,"
                    + "FNAME=?,"
                    + "LNAME=?,"
                    + "EMAIL=?,"
                    + "BDATA=?,"
                    + "GANDER=?,"
                    + "PASWORD=?,"
                    + "PROLANGUGE= ? WHERE STUID = '"+studentId+"'";
        }

    }

    private void insert() {
        try {

            preparedStatement = connection.prepareStatement(query1);
            preparedStatement.setString(1, ssnsty.getText());
            preparedStatement.setString(2, fnamestu.getText());
            preparedStatement.setString(3, lnamestu.getText());
            preparedStatement.setString(4, emailstu.getText());
            preparedStatement.setString(5, berstu.getText());
            preparedStatement.setString(6, ganderstu.getSelectionModel().getSelectedItem());
            preparedStatement.setString(7, passstu.getText());
            preparedStatement.setString(8, prstu.getSelectionModel().getSelectedItem());
            String s="Hello "+fnamestu.getText()+"\n Your registration in our courses has been completed.\n In order to follow your progress and know the details, log in to our CodeAcademe program\n your id ="+ssnsty.getText()+"\n and password ="+passstu.getText()+"\nGood luck";
            emailcode.sendMailTo(emailstu.getText(),ssnsty.getText(),fnamestu.getText(),passstu.getText(),s);


            preparedStatement.execute();

        } catch (SQLException ex) {
            System.out.println("noo");
            Logger.getLogger(AddStudentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

    public ObservableList<stut>listdata(){
        ObservableList<stut>listd=FXCollections.observableArrayList();
        connection=dbase.connectdb();
        String ss="SELECT * FROM STUDENT";
        try {

            Statement statt1 =connection.createStatement();
            resultSet=statt1.executeQuery(ss);
            stut st;
            while (resultSet.next()){
                st=new stut(resultSet.getInt("STUID"),
                        resultSet.getString("FNAME"),
                        resultSet.getString("LNAME"),
                        resultSet.getString("EMAIL"),
                        resultSet.getString("BDATA"),
                        resultSet.getString("GANDER"),
                        resultSet.getString("PROLANGUGE"),
                        resultSet.getString("PASWORD"));
                listd.addAll(st);
            }
        }catch (Exception e){
            System.out.println("iam");
            e.printStackTrace();
        }
        return listd;
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
   /* public void insert1(){
        connection = dbase.connectdb();
        String idd=ssnsty.getText();
        String fname = fnamestu.getText();
        String lname =  lnamestu.getText();
        String email = emailstu.getText();

        String birth = String.valueOf(berstu.getValue());
        String gander = String.valueOf(ganderstu.getValue());
        String passw = passstu.getText();
        String prolan = String.valueOf(prstu.getValue());
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        String dateFormat = format.format(date);
//        I HAVE 8 COLUMN!
        String sql = "INSERT INTO student VALUES(?,?,?,?,?,?,?,?)";

        try{

            if (idd.isEmpty()||fname.isEmpty() ||lname.isEmpty()|| birth.isEmpty() || gander.isEmpty() || email.isEmpty()|| passw.isEmpty() || prolan.isEmpty() ){

                Alert alert = new Alert(Alert.AlertType.ERROR);

                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Enter all blank fields!");
                alert.showAndWait();

            }else{

                //String file = file_path.getText().replace("\\", "\\\\");

                prepare = connect.prepareStatement(sql);
                prepare.setString(1, id.getText());
                prepare.setString(2, surname.getText());
                prepare.setString(3, given.getText());
                prepare.setString(4, (String)gender.getSelectionModel().getSelectedItem());
                prepare.setString(5, file);
                prepare.setString(6, "");
                prepare.setString(7, dateFormat);

                prepare.executeUpdate();

                //            TO SHOW IMIDDIATELY THE CURRENT DATA!
                showData();
                clear();
            }
        }catch(Exception e){}

    }*/
     void setTextField(IntegerProperty id, StringProperty fname, StringProperty lname, StringProperty emaill, StringProperty  birth, StringProperty gan, StringProperty plan, StringProperty pass) {
       // System.out.println(id.intValue()+" "+gan.getValue()+" "+LocalDate.parse(birth));
        studentId = id.getValue();
        fnamestu.setText(fname.getValue());
        lnamestu.setText(lname.getValue());
        emailstu.setText(emaill.getValue());
        berstu.setText(birth.getValue());
        ganderstu.setValue(gan.getValue());
        prstu.setValue(plan.getValue());
        passstu.setText(pass.getValue());
        ssnsty.setText(String.valueOf(id.getValue()));
    }

    void setUpdate(boolean b) {
        this.update = b;

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        combo();
        plang();
    }
}
