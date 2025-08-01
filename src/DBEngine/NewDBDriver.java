/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBEngine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class NewDBDriver {
     public Statement st=null;
    public Connection con=null;
    public Statement getStatement(){
            ArrayList data=getDBManagerCredentials();
            String dbusername=(String)data.get(0);
            String dbpassword=(String)data.get(1);
    try{
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db_security",dbusername,dbpassword);
            st=con.createStatement();
    }
    catch(Exception e){
            System.out.println("Exception is occured: "+e);
    }
    return st;
    }
    
    
    public ArrayList<String> getDBManagerCredentials() {
    ArrayList<String> data = new ArrayList<>();

    String dblogpath = "C:\\Users\\HP\\AppData\\Roaming\\MySQL\\Workbench\\log\\wb.log";
    try (BufferedReader reader = new BufferedReader(new FileReader(dblogpath))) {
        String line;

        while ((line = reader.readLine()) != null) {
            if (line.contains("Opened connection")) {
               
                int start = line.indexOf('\'');
                int end = line.indexOf('\'', start + 1);

                if (start != -1 && end != -1) {
                    String connectionName = line.substring(start + 1, end).trim();

                    
                    if (!connectionName.toLowerCase().contains("local instance")) {
                        System.out.println("DB Name: " + connectionName);

                       
                        String password = connectionName.substring(0, 1).toUpperCase()
                                            + connectionName.substring(1) + "@123";

                        System.out.println("Password: " + password);
                        data.add(connectionName);
                        data.add(password);
                    }
                }
            }
        }

        System.out.println("Collected DB Info: " + data);
    } catch (Exception e) {
        System.out.println("Exception is occured in class NewDBDriver in method getDBManagerCredientels()"+e);
    }

    return data;
}
}


    
