/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adminOP;

import db.DBDriver;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class ClientInfoFetcher {
    public static ArrayList getClientInfo(){
      ArrayList clientdata=new ArrayList();
      try{
         DBDriver db=new DBDriver();
            Statement st=db.getStatement();
            String query="Select * from client_data_info";
            ResultSet rs=st.executeQuery(query);
            while(rs.next()){
                String clientname=rs.getString(1);
                String tablename=rs.getString(2);
                ArrayList row=new ArrayList();
                row.add(clientname);
                row.add(tablename);
                clientdata.add(row);
            }
          
          
          
      }catch(Exception e){
          System.out.println("Exception is occured in ClientInfoFetcher in method getClientInfo()"+e);
      }
        return clientdata;
    }
   
}
