/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbsecuritysystem;

import DBEngine.DBEngineInit;
import DBEngine.NewDBDriver;
import adminOP.EmailFetcher;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.JFrame;

/**
 *
 * @author HP
 */
public class DBSecuritySystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        // TODO code application logic here
        LoginFrame f1=new LoginFrame();
        f1.setVisible(true);
        Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
        f1.setSize(d);
//        new NewDBDriver().getDBManagerCredentials();
//        new EmailFetcher().getEmailAddress("ajinkya");
        
    }
    
}
