/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientOp;



/**
 *
 * @author HP
 */
public class DigitValidator {
    
    public boolean isValid(String s){
        boolean flag=true;
        try{
           Long.parseLong(s);
           
        }
        catch(Exception e){
            System.out.println("Exception: "+e);
            flag=false;
        }
        return flag;
    }
    
    
}
