/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBEngine;

import adminOP.DBSecurityEngineFrame;
import adminOP.EmailFetcher;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author HP
 */
public class DBEngineInit extends Thread{
    public String tablename=null;
    public String clientname=null;
    
    public void run(){
        ArrayList columnnames =new EngineDataProvider().getColumnNames(tablename);
        ArrayList orgdata=new EngineDataProvider().getCompleteTableData(tablename, columnnames.size());
        ArrayList<String> emailid=new EmailFetcher().getEmailAddress("ajinkya");
        System.out.println("Email id:"+emailid);
        String adminemailid=emailid.get(0);
        String clientemailid=emailid.get(1);
        
        
        System.out.println("Original Data is Fetched \n\n");
        while(true){
            try{
                Thread.sleep(5000);
                System.out.println("Visited Database Table:"+tablename);
                ArrayList dbuserdata=new NewDBDriver().getDBManagerCredentials();
                String dbusername=(String)dbuserdata.get(0);
                
                
                if(!dbusername.equals("root")){
                     ArrayList currentdata=new EngineDataProvider().getCompleteTableData(tablename, columnnames.size());
                      Date dt = new Date();
                      SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
                      String date_time = sdf.format(dt);
                    
                      if(!orgdata.equals(currentdata)){
                           ArrayList finaldata=new EngineDataProvider().getTamperedFields(orgdata, currentdata, columnnames,tablename);
                           ArrayList temperedfields=(ArrayList)finaldata.get(0);
                           String tamperdid=(String)finaldata.get(1);
                          
                          String message1="ALERT ALERT a table named : "+tablename+" is Tampered on id "+tamperdid;
                          String message2=" and on Fields "+temperedfields+" by the Database manager :"+dbusername+" on "+date_time;
                          String Message=message1+" "+message2+" and Data is Restored Successfully ";
                          System.out.println("Message :"+Message);
                          
                          String adminmessage="Dear Admin \n "+Message+"\n"+" And This Table belongs to Client "+clientname;
                          String endnote="Thanks and Regard \n From \n Automatic Database Security System";
                          String finaladminmessage=adminmessage+endnote;
                          String subject="Alert for Data Tampering";
                          
                          SendEmail se=new SendEmail();
                          se.sendMailNow(finaladminmessage,subject,adminemailid);
                          
                          String clientmessage="Dear Client "+clientname+"\n "+Message;
                          String finalclientmessage=clientmessage+"\n "+endnote;
                          se.sendMailNow(finalclientmessage, subject, clientemailid);
                          
                          
                          
                          String resultstr=DBSecurityEngineFrame.jTextArea1.getText()+"\n\n"+Message;
                          DBSecurityEngineFrame.jTextArea1.setText(resultstr);
                      }
                      System.out.println("Data Fetched at :"+date_time);
                }  
                
            }catch(Exception e){
                System.out.println("Exception is occured in DBEngine in run()"+e);
            }
        }
    }
}
