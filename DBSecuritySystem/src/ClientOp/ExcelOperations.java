/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientOp;

import db.DBDriver;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

/**
 *
 * @author HP
 */
public class ExcelOperations {
    public ArrayList<String> getColumnNames(String filepath){
        ArrayList<String> columnnames =new ArrayList<String>();
        
        
        try{
            
            File file=new File(filepath);
            Workbook wobj=Workbook.getWorkbook(file);
            Sheet sht=wobj.getSheet(0);
            int rows=sht.getRows();
            int columns=sht.getColumns();
            System.out.println("Rows ="+rows+" and Columns ="+columns);
            
            for(int j=0;j<columns;j++){
                Cell c1=sht.getCell(j,0);
                String content=c1.getContents();
                columnnames.add(content);
            }
            wobj.close();
            
        }catch(Exception e){
            System.out.println("Exception occuried in ExcelOperations class & getColumnNames()"+e);
        }
        return columnnames ;
        
    }
    
     public boolean isdataStored(String filepath, ArrayList<String> columnnames, String tablename) {
        boolean flag = true;

        try {
            DBDriver dbd=new DBDriver();
            dbd.getStatement();
            Connection con=dbd.con;
            
            
            String query1 = "INSERT INTO " + tablename + " (";
            String query2="";
            String query3=")VALUES (";
            
            for(int i=0;i<columnnames.size();i++){
                query2=query2+columnnames.get(i)+", ";
                query3=query3+"?, ";
            }
            query2=query2.substring(0, query2.length()-2);
            query3=query3.substring(0, query3.length()-2)+")";
            String finalquery=query1+query2+query3;
            PreparedStatement ps=con.prepareStatement(finalquery);
            
             File file=new File(filepath);
            Workbook wobj=Workbook.getWorkbook(file);
            Sheet sht=wobj.getSheet(0);
            int rows=sht.getRows();
            int columns=sht.getColumns();
            
            
            
            for(int i=0;i<rows;i++){
                for(int j=0;j<columns;j++){
                    Cell c1=sht.getCell(j,i);
                    String cell_contnent=c1.getContents();
                    ps.setString(j+1,cell_contnent);
                    //System.out.print(cell_contnent+"");
                }
               // System.out.println("");
               ps.addBatch();
            }
            ps.executeBatch();
            wobj.close();
            

        } catch (Exception e) {
            System.out.println("Exception occurred in ExcelOperations  in isdataStored(): " + e);
            flag = false;
        }
        return flag;
     }

   
}     