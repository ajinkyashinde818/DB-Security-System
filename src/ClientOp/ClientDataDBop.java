/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientOp;

import db.DBDriver;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class ClientDataDBop {
    public boolean isTableCreated(ArrayList<String>columnnames,String tablename){
        boolean flag=true;
        
        try{
            DBDriver db=new DBDriver();
            Statement st=db.getStatement();
            
            String query1="create table IF NOT EXISTS "+tablename+"(";
            String query2="";
            for(int i=0;i<columnnames.size();i++){
                String singlecolumn=columnnames.get(i);
                query2=query2+singlecolumn+" VARCHAR(45),";
            }
            System.out.println("Query 1:"+query1);
            System.out.println("Query 2:"+query2);
            
            
            String finalquery=query1+query2+" PRIMARY KEY ("+columnnames.get(0)+"))";
            System.out.println("Final Query Create to Table:"+finalquery);
            st.executeUpdate(finalquery);
            
        }
        catch(Exception e){
            System.out.println("Exception Occured ClientDataDBop in isTableCreated():"+e);
            flag=false;
        }
        return flag;
    }
    
    
   public boolean isDataInfoStored(String clientname,String filename,String date_time){
       boolean flag=true;
       //username, name, dob, mobile, email, password, date_time
        try{
            
            System.out.println("Inside isDataInfoStored() function ");
            DBDriver db=new DBDriver();
            Statement st=db.getStatement();
            String query="Insert into client_data_info values('"+clientname+"','"+filename+"','"+date_time+"')";
            System.out.println("Query : "+query);
            int x=st.executeUpdate(query);
            if(x>0)
            {
                flag=true;
                flag=true;
                db.st.close();
                db.con.close();
            }
        }
        catch(Exception e){
            flag=false;
            System.out.println("Exception occured in ClientDataDBop in isDataInfoStored()"+e);
            
        }
        return flag;
    }
   
   
   public ArrayList  getStoredFileInfo(String client_name){
       ArrayList data=new ArrayList();
       
       try{
            DBDriver db=new DBDriver();
            Statement st=db.getStatement();
            String query="Select * from client_data_info where clientname = '"+client_name+"'";
            ResultSet rs=st.executeQuery(query);
            while(rs.next()){
                String filename=rs.getString(2);
                String date_time=rs.getString(3);
                ArrayList row=new ArrayList();
                row.add(filename);
                row.add(date_time);
                data.add(row);
            }
           
           
       }catch(Exception e){
           System.out.println("Exception is occured ClientDataDBop in getStoredFileInfo()"+e);
       }
       return data;
   }
}