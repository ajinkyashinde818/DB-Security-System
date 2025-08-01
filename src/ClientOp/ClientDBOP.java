/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientOp;

import db.DBDriver;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class ClientDBOP {
    public boolean isClientRegistered(String name,String dob,String mobile,String email,String username,String password,String date_time){
       boolean flag=false;
       //username, name, dob, mobile, email, password, date_time
        try{
            DBDriver db=new DBDriver();
            Statement st=db.getStatement();
            String query="Insert into client_info values('"+username+"','"+name+"','"+dob+"','"+mobile+"','"+email+"','"+password+"','"+date_time+"')";
            System.out.println("Query : "+query);
            if(st.executeUpdate(query)>0){
                flag=true;
                db.st.close();
                db.con.close();
            }
        }
        catch(Exception e){
            flag=false;
            System.out.println("Exception occured in ClientDBOP in isClientRegistered()"+e);
            
        }
        return flag;
    }
        
        public boolean isExisted(String uname, String pass) {
    boolean flag = false;

    try {
        DBDriver db = new DBDriver();
        Statement st = db.getStatement();
        String query = "SELECT * FROM client_info WHERE username='" + uname + "' AND Password='" + pass + "'";
        
        System.out.println("Query is: " + query);

        ResultSet rs = st.executeQuery(query);
        if (rs.next()) {
            flag = true; 
        }
    } catch (Exception e) {
        System.out.println("Exception occurred in isExisted(): " + e);
        flag = false;
    }

    return flag; 
}
        public ArrayList<String> getClientdata(String clientname) {
        ArrayList<String> data = new ArrayList<>();
        try {
            DBDriver db = new DBDriver();
            Statement st = db.getStatement();
            String query = "SELECT * FROM client_info WHERE username='" + clientname + "'";
            System.out.println("Query is: " + query);
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                data.add(rs.getString("username"));   // 1
                data.add(rs.getString("name"));       // 2
                data.add(rs.getString("dob"));        // 3
                data.add(rs.getString("mobile"));     // 4
                data.add(rs.getString("email"));      // 5
                data.add(rs.getString("password"));   // 6
            }
            rs.close();
            st.close();
            db.con.close();
        } catch (Exception e) {
            System.out.println("Exception occurred in getClientdata(): " + e);
        }
        return data;
    }
        public boolean isEdited(String fname,String fdob,String fmobile,String femail,String fusername,String fpassword,String date_time){
            boolean flag=false;
            
            try{
                DBDriver db = new DBDriver();
            Statement st = db.getStatement();
            
           String query = "UPDATE client_info SET " +
               "name = '" + fname + "', " +
               "dob = '" + fdob + "', " +
               "mobile = '" + fmobile + "', " +
               "email = '" + femail + "', " +
               "password = '" + fpassword + "', " +
               "date_time = '" + date_time + "' " +
               "WHERE username = '" + fusername + "'";
            
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
