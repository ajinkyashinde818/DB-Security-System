/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adminOP;

import DBEngine.NewDBDriver;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class EmailFetcher {
    public ArrayList<String> getEmailAddress(String client){
        ArrayList <String> emailids=new ArrayList<String>();
        
        try{
            NewDBDriver db=new NewDBDriver();
            Statement st=db.getStatement();
            String query1="Select * from admin_info";
            ResultSet rs1=st.executeQuery(query1);
            if(rs1.next()){
            String adminemail=rs1.getString("emailid");
              emailids.add(adminemail);
            }
           
            String query2="Select * from client_info where username='"+client+"'";
            ResultSet rs2=st.executeQuery(query2);
            if(rs2.next()){
            String clientmail=rs2.getString("email"); 
            emailids.add(clientmail);
            }
            
        }catch(Exception e){
            System.out.println("Exception is occured in EmailFetcher in getEmailAddresss()"+e);
        }
        return emailids;
    }
    
}
