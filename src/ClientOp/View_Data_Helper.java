/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientOp;

import db.DBDriver;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

/**
 *
 * @author HP
 */
public class View_Data_Helper {
    public String[] getColumnNames(String tablename){
        String columnname[]=null;
        
        try{
            DBDriver db=new DBDriver();
            Statement st=db.getStatement();
            String query="select * from "+tablename;
            ResultSet rs=st.executeQuery(query);
            ResultSetMetaData rsmd=rs.getMetaData();
            
            int no_of_Columns=rsmd.getColumnCount();
            columnname =new String[no_of_Columns];
            for(int i=0;i<no_of_Columns;i++)
            columnname[i]=rsmd.getColumnName(i+1);
            
            
            
        }catch(Exception e){
            System.out.println("Exception is occured in View_Data_Helper in getColumnNames()"+e);
        }
        return columnname;
    }
    
    public String[][] getTableData(String tablename,int columncount){
        String table_data[][]=null;
        
        try{
            DBDriver db=new DBDriver();
            Statement st1=db.getStatement();
            Statement st2=db.getStatement();
            
            String query="select * from "+tablename;
            ResultSet rs1=st1.executeQuery(query);
            ResultSet rs2=st2.executeQuery(query);
            int rowcount=0;
            
            while(rs1.next())
                rowcount++;
            table_data=new String[rowcount][columncount];
            int i=0;
            while(rs2.next()){
                for(int j=0;j<columncount;j++){
                    table_data[i][j]=rs2.getString(j+1);
                }
                i++;
            }
            
        }catch(Exception e){
            System.out.println("Exception is occured View_Data_Helper in getTableData()");
        }
        return table_data; 
    }
}
