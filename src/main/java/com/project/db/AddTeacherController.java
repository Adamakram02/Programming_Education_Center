package com.project.db;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import javax.mail.MessagingException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddTeacherController implements Initializable {

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
    private TableView<teacheer> tetable;
    @FXML
    private TableColumn<teacheer, String> bdatete;
    @FXML
    private TextField agete;

    @FXML
    private TextField emte;

    @FXML
    private ComboBox<String> gante;

    @FXML
    private PasswordField paste;

    @FXML
    private TextField pte;

    @FXML
    private TextField ssnte;

    @FXML
    private TextField ste;

    @FXML
    private TextField tefname;

    @FXML
    private TextField telname;


    String query1 ;
    Connection connection ;
    ResultSet resultSet ;
    PreparedStatement preparedStatement;
    teacheer tea ;
    private boolean update;
    int tetId;

    private String gender1[]={"M","F"};
    public void combo(){
        List<String> list = new ArrayList<>();

        for(String data: gender1){

            list.add(data);

        }

        ObservableList dataList = FXCollections.observableArrayList(list);

        gante.setItems(dataList);
    }




    @FXML
    private void save(MouseEvent event) {

        connection = dbase.connectdb();
        String idd=ssnte.getText();
        String fname = tefname.getText();
        String lname =  telname.getText();
        String email = emte.getText();

        String birth = agete.getText();
        String gander = String.valueOf(gante.getValue());
        String passw = paste.getText();
        String prolan =pte.getText();
        String salary =ste.getText();

        if (idd.isEmpty()||fname.isEmpty() ||lname.isEmpty()|| birth.isEmpty() || gander.isEmpty() || email.isEmpty()|| passw.isEmpty() || prolan.isEmpty() ||salary.isEmpty()) {
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
        tefname.setText(null);
        telname.setText(null);
        agete.setText(null);
        emte.setText(null);
        gante.setValue(null);
        pte.setText(null);
        paste.setText(null);
        ste.setText(null);
        ssnte.setText(null);

    }

    private void getQuery() {

        if (update == false) {
            System.out.println("1");
            query1 = "INSERT INTO TEACHER (TUID, FNAME, LNAME, EMAIL, SALARY,BDATA, GANDER, PRO_LANGUAGES,PASSWORD) VALUES (?, ?, ?, ?, ?, ?, ?, ?,?)";

        }else{
            System.out.println("2");
            query1 = "UPDATE TEACHER SET "
                    + "TUID=?,"
                    + "FNAME=?,"
                    + "LNAME=?,"
                    + "EMAIL=?,"
                    + "SALARY=?,"
                    + "BDATA=?,"
                    + "GANDER=?,"
                    + "PRO_LANGUAGES=?,"
                    + "PASSWORD= ? WHERE TUID = '"+tetId+"'";
        }

    }

    private void insert() {
        try {

            preparedStatement = connection.prepareStatement(query1);
            preparedStatement.setString(1, ssnte.getText());
            preparedStatement.setString(2, tefname.getText());
            preparedStatement.setString(3, telname.getText());
            preparedStatement.setString(4, emte.getText());
            preparedStatement.setString(5, ste.getText());
            preparedStatement.setString(6, agete.getText());
            preparedStatement.setString(7, gante.getSelectionModel().getSelectedItem());
            preparedStatement.setString(9, paste.getText());
            preparedStatement.setString(8, pte.getText());
            String s="Hi " +tefname.getText()+
                    "\nYou are now registered as a teacher in our "+ pte.getText() +" courses " +
                    "\nWe have created an account for you on our program to monitor the students registered in the course and give them tasks and explanations for them\n Your Id="+ssnte.getText()+"\nyour Password="+paste.getText()+"\n";
            emailcode.sendMailTo(emte.getText(),ssnte.getText(),tefname.getText(),paste.getText(),s);


            preparedStatement.execute();

        } catch (SQLException ex) {
            System.out.println("noo");
            Logger.getLogger(AddStudentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
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
    void setTextField(IntegerProperty id, StringProperty fname, StringProperty lname, StringProperty emaill,IntegerProperty salary, StringProperty  birth, StringProperty gan, StringProperty plan, StringProperty pass) {
        // System.out.println(id.intValue()+" "+gan.getValue()+" "+LocalDate.parse(birth));
        tetId = id.getValue();
        tefname.setText(fname.getValue());
        telname.setText(lname.getValue());
        emte.setText(emaill.getValue());
        agete.setText(birth.getValue());
        gante.setValue(gan.getValue());
        pte.setText(plan.getValue());
        paste.setText(pass.getValue());
        ste.setText(String.valueOf(salary.getValue()));
        ssnte.setText(String.valueOf(id.getValue()));
    }

    void setUpdate(boolean b) {
        this.update = b;

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        combo();
    }
}
