/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;
import java.sql.*;

/**
 *
 * @author HP
 */
public class DBDriver {
    public Statement st=null;
    public Connection con=null;
    public Statement getStatement(){
            
    try{
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db_security","root","root");
            st=con.createStatement();
    }
    catch(Exception e){
            System.out.println("Exception is occured: "+e);
    }
    return st;
    }
}
