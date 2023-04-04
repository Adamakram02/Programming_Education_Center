package com.project.db;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.Initializable;

import java.sql.Date;

public class teacheer {

    private IntegerProperty id ;
    private  StringProperty fname;
    private  StringProperty lname;
    private  StringProperty emaill;
    private  IntegerProperty salary ;
    private StringProperty birth;
    private  StringProperty gan;
    private  StringProperty plan;
    private StringProperty pass;

    public teacheer(Integer id, String fname, String lname,String emaill,Integer salary, String birth, String gan,String plan,String pass){
        this.id=new SimpleIntegerProperty(id);
        this.fname=new SimpleStringProperty(fname);
        this.lname=new SimpleStringProperty(lname);
        this.emaill=new SimpleStringProperty(emaill);
        this.salary=new SimpleIntegerProperty(salary);
        this.birth=new SimpleStringProperty(birth);
        this. gan=new SimpleStringProperty(gan);
        this. plan=new SimpleStringProperty(plan);
        this. pass=new SimpleStringProperty(pass);

    }
    /*public void setid(int id){
        this.id=id;
    }*/
    public IntegerProperty getidd(){
        return id;
    }

    /*public void setfname(String fname){
        this.fname=fname;
    }*/
    public StringProperty getfnamee(){
        return fname;
    }
    public IntegerProperty getsalarry(){
        return salary;
    }

    /*public void setlname(String lname){
        this.lname=lname;
    }*/
    public StringProperty getlnamee(){
        return lname;
    }

    /*public void setlemail(String emaill){
        this.emaill=emaill;
    }*/
    public StringProperty getemaill(){
        return emaill;
    }

    /*public void setlberth(Date birth){
        this.birth=birth;
    }*/
    public StringProperty getbertth(){
        return birth;
    }

    /*public void setlgan(String gan){
        this.gan=gan;
    }*/
    public StringProperty getgann(){
        return gan;
    }

    /* public void setplan(String plan){
         this.plan=plan;
     }*/
    public StringProperty getplann(){
        return plan;
    }

    /*public void setpass(String pass){
        this.pass=pass;
    }*/
    public StringProperty getpaass(){
        return pass;
    }

}
