/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientOp;

import java.io.File;
import java.util.ArrayList;
import jxl.Workbook;
import jxl.write.WritableWorkbook;
import jxl.write.*;

/**
 *
 * @author HP
 */
public class ExcelFileDownloader {
    public boolean isDownloaded(ArrayList completedata,String finalpath){
       boolean flag=false;
       try{
           File file=new File(finalpath);
            WritableWorkbook workbook = Workbook.createWorkbook(file); 
            WritableSheet sheet=workbook.createSheet("Sheet 1", 0);
            WritableFont cellFont = new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD);
            WritableCellFormat cellFormat=new WritableCellFormat(cellFont);
            
            Label lb1=null;
            
            for (int i = 0; i < completedata.size(); i++) {
               ArrayList row=(ArrayList) completedata.get(i);
                for (int j = 0; j < row.size(); j++) {
                    String content=(String)row.get(j);
                    lb1= new Label(j,i,content,cellFormat);
                    sheet.addCell(lb1);
                }
           }
            workbook.write();
            workbook.close();
           
           flag=true;
       }
       catch(Exception e){
           System.out.println("Exception is occured in ExcelFileDownloader in method isDownloaded()"+e);
       }
       return flag;
    }
}
