package com.project.db;
import javax.xml.transform.Result;
import java.sql.*;

public  class dbase {

    public static Connection connectdb(){
       // Connection conect=;
        try {
            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","adam","123321");
            return  con;
        } catch (SQLException e) {
           System.out.println("NOO");
        }

        return null;
    }
}
