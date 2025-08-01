/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBEngine;

import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class Restorer {
    public boolean isDataRestored(String pkey,String pvalue,ArrayList<String> tamperedcolumnnames,ArrayList<String> replacedata,String tablename){
        boolean flag=false;
        
        try{
            NewDBDriver db=new NewDBDriver();
            Statement st=db.getStatement();
            String query1="Update "+tablename+" set ";
            String query2="";
            for (int i = 0; i <tamperedcolumnnames.size() ; i++) {
                String colname=tamperedcolumnnames.get(i);
                String value=replacedata.get(i);
                query2=query2+colname+"='"+value+"',";
            }
            query2=query2.substring(0, query2.length()-1);
            String query=query1+query2+" where "+pkey+"='"+pvalue+"'";
            System.out.println("Query is :"+query);
            int x=st.executeUpdate(query);
            if(x>0)
                flag=true;
            st.close();
            db.con.close();
            
        }catch(Exception e){
            System.out.println("Exception is occured in Restorer in isDataRestored()"+e);
        }
        return flag;
    }
}
