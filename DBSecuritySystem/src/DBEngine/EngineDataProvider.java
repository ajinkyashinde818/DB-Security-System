/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBEngine;

import db.DBDriver;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class EngineDataProvider {
     public ArrayList getColumnNames(String tablename){
        ArrayList columnname=new ArrayList<String>();
        
        try{
            NewDBDriver db=new NewDBDriver();
            Statement st=db.getStatement();
            String query="select * from "+tablename;
            ResultSet rs=st.executeQuery(query);
            ResultSetMetaData rsmd=rs.getMetaData();
            int no_of_Columns=rsmd.getColumnCount();
            
            for (int i = 0; i < no_of_Columns; i++) {
                columnname.add(rsmd.getColumnName(i+1));
            }
            
            
            
        }catch(Exception e){
            System.out.println("Exception is occured in EngineDataProvider in getColumnNames()"+e);
        }
        return columnname;
    }
     
     public ArrayList getCompleteTableData(String tablename,int columncount){
         ArrayList completedata=new ArrayList();
         try{
             NewDBDriver db=new NewDBDriver();
            Statement st=db.getStatement();
            String query="select * from "+tablename;
            ResultSet rs=st.executeQuery(query);
            while(rs.next()){
                ArrayList row=new ArrayList();
                for (int i = 1; i <=columncount; i++) {
                    String field_data=rs.getString(i);
                    row.add(field_data);
                }
                completedata.add(row);
            }
             
         }catch(Exception e){
             System.out.println("Exception is occured in EngineDataProvider in method getCompleteData()"+e);
         }
         return completedata;
     }
     public ArrayList<String> getTamperedFields(ArrayList orgdata,ArrayList currentdata,ArrayList columnnames,String tablename){
         ArrayList<String> tamperedfields=new ArrayList<String>();
         ArrayList<String> replacedata=new ArrayList<String>();
         String pkey=(String)columnnames.get(0);
         String pvalue=null;
         String id=null;
         for (int i = 0; i < orgdata.size(); i++) {
             ArrayList orgrow=(ArrayList)orgdata.get(i);
             
             ArrayList currentrow=(ArrayList)currentdata.get(i);
             if(!orgdata.equals(currentdata)){
                 for (int j = 0; j < orgrow.size(); j++) {
                     String orgfield=(String)orgrow.get(j);
                     String currentfield=(String)currentrow.get(j);
                     if(!orgfield.equals(currentfield)){
                         String field=(String)columnnames.get(j);
                         tamperedfields.add(field);
                         replacedata.add(orgfield);
                         id=(String) orgrow.get(0);
                     }
                 }
             }
         }
         
         ArrayList finaldata=new ArrayList();
         if(new Restorer().isDataRestored(pkey, id, tamperedfields, replacedata, tablename)){
             
              finaldata.add(tamperedfields);
              finaldata.add(id);
              
         }
         return finaldata;
     }
}     
