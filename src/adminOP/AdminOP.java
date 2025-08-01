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
public class AdminOP {
    public boolean isAdminExisted(String user_name,String pass_word){
        boolean flag=true;
        
        try{
             
             DBDriver db = new DBDriver();
             Statement st = db.getStatement();
            String query = "SELECT * FROM admin_info WHERE username='" + user_name + "' AND password='" + pass_word + "'";
             System.out.println("Query is: " + query);
             ResultSet rs1 = st.executeQuery(query);
             if(rs1.next())
                 flag=true;
             db.st.close();
             db.con.close();
            
        }catch(Exception e){
            System.out.println("Exception occured in AdminOP in method isAdminExisted()"+e);
            flag=false;
        }
        return flag;
    }
    public ArrayList getAdminCredentials(){
        ArrayList<String> admindata=new ArrayList<String>();
        try{
            String adminname="admin";
            DBDriver db = new DBDriver();
            Statement st = db.getStatement();
            String query = "SELECT * FROM admin_info WHERE username='" + adminname + "'" ;
            System.out.println("Query is: " + query);
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
            admindata.add(rs.getString(1)); 
            admindata.add(rs.getString(3)); 
            admindata.add(rs.getString(2)); 
        } else {
             System.out.println("No admin found with username: " + adminname);
       }

            db.st.close();
            db.con.close();
            
            
        }catch(Exception e){
            System.out.println("Exception is occured in AdminOP in method getAdminCredentials()"+e);
        }
        return admindata;
    }
    
   public boolean setAdminCredentials(String username, String password, String emailid) {
    boolean flag = false;
    try {
        DBDriver db = new DBDriver();
        Statement st = db.getStatement();

        String query = "INSERT INTO admin_info (username, password, emailid) VALUES ('" 
                        + username + "', '" + password + "', '" + emailid + "')";
        System.out.println("Query: " + query);

        int x = st.executeUpdate(query);
        if (x > 0) {
            flag = true;
        }

        
        st.close();
        db.con.close();

    } catch (Exception e) {
        flag = false;
        System.out.println("Exception occurred in AdminOP in method setAdminCredentials(): " + e);
    }
    return flag;
}
   public static boolean isEdited(String password,String emailid){
            boolean flag=false;
            
            try{
                String adminname="admin";
                DBDriver db = new DBDriver();
            Statement st = db.getStatement();
            
           String query="update admin_info set password='"+password+"',emailid ='"+emailid+"' where username='"+adminname+"'";
            
           int x=st.executeUpdate(query);
           if(x>0){
               flag=true;
               st.close();
               db.con.close();
               db.st.close();
           }
            }
            catch(Exception e){
                System.out.println("Exception occuried !!! \n  in isEdited method"+e);
            }
            return flag;
        }

}
