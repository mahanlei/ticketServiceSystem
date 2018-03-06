package com.mahanlei.dao.impl;

import com.mahanlei.dao.DaoHelper;
import org.springframework.stereotype.Repository;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;


public class DaoHelperImpl implements DaoHelper {

 private    String url="jdbc:mysql://localhost:3306/ticketSystem?useUnicode=true&characterEncoding=utf-8";
 private    String username = "root";
 private    String password = "leilei";
 Connection connection;

    public Connection getConnection() {
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch(ClassNotFoundException e){
            System.out.println("No jdbc driver");
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(url,username,password);
            System.out.println("Database get connect success");
        } catch (java.sql.SQLException e) {
          System.out.println("Database get connect failure"  );
            e.printStackTrace();
        }
        return connection;
    }

    public void closeConnection(Connection con) {
        if(connection!=null)
        {
            try {
                con.close();
            } catch (java.sql.SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }

    public void closePreparedStatement(PreparedStatement stmt) {
        if(stmt!=null)
        {

            try {
                stmt.close();
            } catch (java.sql.SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }

    public void closeResult(ResultSet result) {
        try
        {
            result.close();
        } catch (java.sql.SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }


}
