package com.lore.automation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReadJSONExample
{
	public static FileInputStream fis;
    
	public static XSSFWorkbook wb;
	 public static XSSFSheet sheet;
	 public static XSSFRow row;
	 public static XSSFCell cell;
 
    @SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException
    {
        //JSON parser object to parse read file
    	
    	fis=new FileInputStream("G:\\Test Data\\Test.xlsx");
    	wb=new XSSFWorkbook(fis);
    	sheet=wb.getSheet("Sheet2");
         try {
        	 String filepath="C:\\Users\\Achint\\Desktop\\achint_selenium\\automation\\data.json";
        	  	 
        	 
        	 			
        	 FileReader reader = new FileReader(filepath);
			 
			
			JSONParser jsonparser=new JSONParser();
			
		   
			
			Object obj = jsonparser.parse(reader);
		
            
			JSONObject jsonobject= (JSONObject) obj;
			
		//	System.out.println((String) jsonobject.get("data").toString());
           
			JSONArray json=(JSONArray)jsonobject.get("data");				
						
			//System.out.println(json.get(0));
			List<JSONObject> list=new ArrayList<JSONObject>();
			
			list.addAll((JSONArray)jsonobject.get("data"));
			
			
			
			for(int i=0;i<list.size();i++)
			{
				row=sheet.createRow(i);
				
			System.out.println((String)list.get(i).get("ipAddress")+" "+list.get(i).get("abuseConfidenceScore")+" "+list.get(i).get("totalReports"));
			
			for(int j=0;j<3;j++)
				{
					
				
				try {
							
					if(j==0)
					{   
						cell=row.createCell(j);
						 String str0=list.get(i).get("ipAddress").toString();
					 	cell.setCellValue(str0);
					
					}
					
					else if(j==1)
					{
					cell=row.createCell(j);
					String str0=list.get(i).get("abuseConfidenceScore").toString();
					cell.setCellValue(str0);
					}
					
					else if(j==2)
					{
						cell=row.createCell(j);
						String str0=list.get(i).get("totalReports").toString();
						cell.setCellValue(str0);
					}
				}
				catch(NullPointerException n)
				{						
											
							cell=row.createCell(j);
							String str0="Data Not Available";
							cell.setCellValue(str0);				
					
				}
				}
			}
			
			
			FileOutputStream fos=new FileOutputStream("G:\\Test Data\\Test.xlsx");
			wb.write(fos);
			
		
		} catch (ParseException e) {e.printStackTrace();}
         catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
         catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
         
    
  }
}
