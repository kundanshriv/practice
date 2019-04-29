package com.lore.automation;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;



public class RestAssuredExample {

public static FileInputStream fis;
    
	public static XSSFWorkbook wb;
	 public static XSSFSheet sheet;
	 public static XSSFRow row;
	 public static XSSFCell cell;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		fis=new FileInputStream("G:\\Test Data\\Test.xlsx");
    	wb=new XSSFWorkbook(fis);
    	sheet=wb.getSheet("Sheet2");
    	
    	
	    
    	RestAssured.baseURI="https://api.abuseipdb.com";
	     
	 Response resp=  RestAssured.given().
	                 param("countMinimum",15).
                     param("maxAgeInDays",60).
                     param("confidenceMinimum",90).
                     header("Key","2e12f56336e0c92d2d5f96cd7f4c82f7d14fb0a65cae63e36d8dda5f0ed340428ee329f37f9382dc").
                     header("Accept","application/json").
                     when().get("/api/v2/blacklist").then().assertThat().statusCode(200).extract().response();

                   // System.out.println(resp.asString());
                    
                    JsonPath js=new JsonPath(resp.asString());
                     
                    int count=js.get("data.size()");
                    System.out.println(count);
                    XSSFRow header=sheet.createRow(0);
                    
                    header.createCell(0).setCellValue("IP Address");
                    header.createCell(1).setCellValue("Total Reports");
                    header.createCell(2).setCellValue("Confidence Score");
                    
                    for(int i=1;i<count;i++)
                    {
                    	row=sheet.createRow(i);      				
            			
            			
            			for(int j=0;j<3;j++)
            				{
            					  try {         				
            							
            					if(j==0)
            					{   
            						cell=row.createCell(j);
            						String str0=js.get("data["+(i-1)+"].ipAddress").toString();
            					 	cell.setCellValue(str0);
            					
            					}
            					
            					else if(j==1)
            					{
            					cell=row.createCell(j);
            					String str0=js.get("data["+(i-1)+"].totalReports").toString();
            					cell.setCellValue(str0);
            					}
            					
            					else if(j==2)
            					{
            						cell=row.createCell(j);
            						String str0=js.get("data["+(i-1)+"].abuseConfidenceScore").toString();
            						cell.setCellValue(str0);
            					}
            					
            				}
            				
            			catch(NullPointerException e){
            				
            				if(j==0)
        					{   
        						cell=row.createCell(j);
        						String str0="N/A";
        					 	cell.setCellValue(str0);
        					
        					}
        					
        					else if(j==1)
        					{
        						cell=row.createCell(j);
        						String str0="N/A";
        					 	cell.setCellValue(str0);
        					}
        					
        					else if(j==2)
        					{
        						cell=row.createCell(j);
        						String str0="N/A";
        					 	cell.setCellValue(str0);
        					}
            				
            				
            				
            			}
            			
                    	
                    	
                    }                 
            			System.out.println(i+" set of data set");
                                  
	              
                    }
                    
                    FileOutputStream fos=new FileOutputStream("G:\\Test Data\\Test.xlsx");
        			wb.write(fos); 
		

	}

}
