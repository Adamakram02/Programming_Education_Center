package com.project.db;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.Date;


public class stut {
    private IntegerProperty id ;
    private StringProperty fname;
    private  StringProperty lname;
    private  StringProperty emaill;
    private  StringProperty birth;
    private  StringProperty gan;
    private  StringProperty plan;
    private  StringProperty pass;

   public stut(Integer id1, String fname1, String lname1,String emaill1, String birth1, String gan1,String plan1,String pass1){
        this.id=new SimpleIntegerProperty(id1);
       this.fname=new SimpleStringProperty(fname1);
       this.lname=new SimpleStringProperty(lname1);
       this.emaill=new SimpleStringProperty(emaill1);
       this.birth= new SimpleStringProperty(birth1);
       this. gan=new SimpleStringProperty(gan1);
       this. plan=new SimpleStringProperty(plan1);
       this. pass=new SimpleStringProperty(pass1);

    }
    /*public void setid(int id){
        this.id=id;
    }*/
    public IntegerProperty getid(){
       return id;
    }

    /*public void setfname(String fname){
        this.fname=fname;
    }*/
    public StringProperty getfname(){
        return fname;
    }

    /*public void setlname(String lname){
        this.lname=lname;
    }*/
    public StringProperty getlname(){
        return lname;
    }

    /*public void setlemail(String emaill){
        this.emaill=emaill;
    }*/
    public StringProperty getemail(){
        return emaill;
    }

    /*public void setlberth(Date birth){
        this.birth=birth;
    }*/
    public StringProperty getberth(){
        return birth;
    }

    /*public void setlgan(String gan){
        this.gan=gan;
    }*/
    public StringProperty getgan(){
        return gan;
    }

   /* public void setplan(String plan){
        this.plan=plan;
    }*/
    public StringProperty getplan(){
        return plan;
    }

    /*public void setpass(String pass){
        this.pass=pass;
    }*/
    public StringProperty getpass(){
        return pass;
    }
}
