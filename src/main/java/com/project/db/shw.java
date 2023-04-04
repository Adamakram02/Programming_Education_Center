package com.project.db;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import javax.mail.MessagingException;
import java.awt.*;
import java.net.URI;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class shw implements Initializable {

    @FXML
    private Label numchar;

    @FXML
    private TextArea txtarea;
    @FXML
    private TextField TN;

    @FXML
    private TextField TT;
    private boolean update;
    String query1 ;
    int tskid;
    PreparedStatement preparedStatement;
    Connection connection;

    public void chr(){
        numchar.setText("character= "+txtarea.getLength());
        txtarea.setWrapText(true);
    }
   /* public void cle(){
        txtarea.setText(null);
        txtarea.setText(null);
        TN.setText(null);
        TT.setText(null);
        numchar.setText(null);
    }*/
   /* @FXML
    private void save(MouseEvent event) throws MessagingException {

        connection = dbase.connectdb();
        String tnom=TN.getText();
        String ttil = TT.getText();
        String ta =  txtarea.getText();



        if (tnom.isEmpty()||ttil.isEmpty() ||ta.isEmpty()) {
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
            cle();

        }

    }*/

   /* private void getQuery() {

        if (update == false) {
            System.out.println("1");
            System.out.println(user.id);
            query1 = "INSERT INTO Task (TASKID, TASKTITEL, DESCRIPTIONTASK, TEID) VALUES (?, ?, ?,"+user.id+")";

        }else{
            System.out.println("2");
            query1 = "UPDATE TASK SET "
                    + "TASKID=?,"
                    + "TASKTITEL=?,"
                    + "DESCRIPTIONTASK=?," +
                    "TEID="+ user.id
                    +" WHERE TASKID = '"+tskid+"'";
        }

    }*/
   /* void setUpdatee(boolean b) {
        this.update = b;

    }*/
    void settxt(IntegerProperty id, StringProperty Ttit, StringProperty are){
        TN.setText(String.valueOf(id.getValue()));
        //tskid=id.getValue();
        TT.setText(Ttit.getValue());
        txtarea.setText(are.getValue());
    }
    public void comp(){
        try {
            Desktop d=Desktop.getDesktop();
            d.browse(new URI("https://www.codechef.com/ide"));
        }catch (Exception e){
            System.out.println("NO WEP");
        }

    }
    /*private void insert() {
        try {

            preparedStatement = connection.prepareStatement(query1);
            preparedStatement.setString(1, TN.getText());
            preparedStatement.setString(2, TT.getText());
            preparedStatement.setString(3, txtarea.getText());

            // emailcode.sendMailTo("admakrm3@gmail.com",ssnsty.getText(),fnamestu.getText(),passstu.getText());


            preparedStatement.execute();

        } catch (SQLException ex) {
            System.out.println("noo");
            Logger.getLogger(AddStudentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }*/

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
