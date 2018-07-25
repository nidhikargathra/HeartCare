import java.io.*;
import java.io.FileOutputStream;
import org.apache.poi.xwpf.usermodel.Borders;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.VerticalAlign;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

  
public class createreport {  
 public static void main(String[] args)throws IOException {  
  
  BufferedReader br1 =new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
  
  String line=br1.readLine();
  BufferedReader br2 =new BufferedReader(new InputStreamReader(new FileInputStream("reportinfo.txt")));
  
  String name="",age="",gender="",email="";
  name=br2.readLine();
age=br2.readLine(); 
 gender=br2.readLine();
  
  email=br2.readLine();
  
  String a[]=new String[14];
  a=(line.split(","));
  
  
  
  //Blank Document
   XWPFDocument document= new XWPFDocument(); 
   //Write the Document in file system
   FileOutputStream out = new FileOutputStream(
   new File("report.docx"));
      
 //create Paragraph
	  XWPFParagraph paragraph = document.createParagraph();
   paragraph.setAlignment(ParagraphAlignment.CENTER);
 
   XWPFRun run=paragraph.createRun();
     run.setBold(true);
	 run.setFontSize(26);
     

   run.setText("HEART CARE+\n\n\n\n");
   
   
   
    paragraph = document.createParagraph();
    paragraph.setAlignment(ParagraphAlignment.CENTER);
	run=paragraph.createRun();
	run.setFontSize(14);
	
	
	//Set bottom border to paragraph
	
   paragraph.setBorderBottom(Borders.BASIC_BLACK_DASHES);
        
   //Set left border to paragraph
   paragraph.setBorderLeft(Borders.BASIC_BLACK_DASHES);
        
   //Set right border to paragraph
   paragraph.setBorderRight(Borders.BASIC_BLACK_DASHES);
        
   //Set top border to paragraph
   paragraph.setBorderTop(Borders.BASIC_BLACK_DASHES);
   
   
   run.setText("AUTOMATICALLY GENERATED HEART ANALYSIS REPORT");
   
    paragraph = document.createParagraph();
    paragraph.setAlignment(ParagraphAlignment.LEFT);
	run=paragraph.createRun();
	run.setFontSize(12);
	
	run.setText("Name: "+name);
	run.addBreak();
	run.setText("Age: "+age);
	run.addBreak();
	run.setText("Gender: "+gender);
	run.addBreak();
	run.setText("Email ID: "+email);
	
	
   
   XWPFTable table = document.createTable();
   
    XWPFTableRow tabler = table.getRow(0);
	
   tabler.getCell(0).setText("Symptom");
   tabler.addNewTableCell().setText("Normal/Standard value");
   tabler.addNewTableCell().setText("Actual Value");
   String s="";
    XWPFTableRow tableRowOne = table.createRow();
   tableRowOne.getCell(0).setText("Chest Pain Type");
   tableRowOne.getCell(1).setText("Non-Anginal Pain");
   if(Double.parseDouble(a[2])==1)
	   s="Typical Angina";
   else if(Double.parseDouble(a[2])==2)
	   s="Atypical Angina";
   else if(Double.parseDouble(a[2])==1)
	   s="Non- Angina Pain";
   else
	   s="Asymptomatic";
	   
   tableRowOne.getCell(2).setText(s);
   //create second row
   XWPFTableRow tableRowTwo = table.createRow();
   tableRowTwo.getCell(0).setText("Resting Blood Pressure             ");
   tableRowTwo.getCell(1).setText("120 mm Hg");
   tableRowTwo.getCell(2).setText(a[3]+"mm Hg");
   //create third row
   XWPFTableRow tableRowThree = table.createRow();
   tableRowThree.getCell(0).setText("Cholesterol");
   tableRowThree.getCell(1).setText("140-250 mg/dl");
   tableRowThree.getCell(2).setText(a[4]+"mg/dl");
   //4
   XWPFTableRow tableRowFour = table.createRow();
   tableRowFour.getCell(0).setText("Fasting blood sugar");
   tableRowFour.getCell(1).setText("70-110 (<120) mg/dl");
   if(Double.parseDouble(a[5])==0)
	   s="<120 mg/dl";
   else
	   s=">120 mg/dl";
   
   tableRowFour.getCell(2).setText(s);
   //5
   XWPFTableRow tableRowFive = table.createRow();
   tableRowFive.getCell(0).setText("Resting ECG report");
   tableRowFive.getCell(1).setText("Normal");
   
   if(Double.parseDouble(a[6])==0)
	   s="Normal";
   else if(Double.parseDouble(a[6])==1)
	   s="Abnormality";
   else
	   s="Hypertrophy";
	   
   
   tableRowFive.getCell(2).setText(s);
   //6
   XWPFTableRow tableRowSix = table.createRow();
   tableRowSix.getCell(0).setText("Maximum Heart Rate achieved ");
   tableRowSix.getCell(1).setText("150-180 beats per minute");
   tableRowSix.getCell(2).setText(a[7]+"beats per minute");
   //7
   XWPFTableRow tableRowSeven = table.createRow();
   tableRowSeven.getCell(0).setText("Exercise induced angina");
   tableRowSeven.getCell(1).setText("No");
   
   if(Double.parseDouble(a[8])==1)
	   s="Yes";
   else
	   s="No";
   tableRowSeven.getCell(2).setText(s);
   //8
   XWPFTableRow tableRowEight = table.createRow();
   tableRowEight.getCell(0).setText("Old Peak");
   tableRowEight.getCell(1).setText("<2.6");
   tableRowEight.getCell(2).setText(a[9]);
   //9
   XWPFTableRow tableRowNine = table.createRow();
   tableRowNine.getCell(0).setText("Slope");
   tableRowNine.getCell(1).setText("Flat");
  
   
   if(Double.parseDouble(a[10])==1)
	   s="Upslope";
   else if(Double.parseDouble(a[2])==2)
	   s="Flat";
   else
	   s="Downslope";
   tableRowNine.getCell(2).setText(s); 
   
   //10
   XWPFTableRow tableRowTen = table.createRow();
   tableRowTen.getCell(0).setText("No. of blood vessels colored");
   tableRowTen.getCell(1).setText("0");
   tableRowTen.getCell(2).setText(a[11]);
 //11
  XWPFTableRow tableRowEleven = table.createRow();
   tableRowEleven.getCell(0).setText("Thal");
   tableRowEleven.getCell(1).setText("Normal");
   
   if(Double.parseDouble(a[12])==3)
	   s="Normal";
   else if(Double.parseDouble(a[10])==6)
	   s="Fixed Defect";
   else
	   s="Reversible Defect";
   
   tableRowEleven.getCell(2).setText(s);
   
   
   
   
     paragraph = document.createParagraph();
    paragraph.setAlignment(ParagraphAlignment.CENTER);
	run=paragraph.createRun();
	run.setFontSize(18);
	run.setText("");
	
	paragraph = document.createParagraph();
    paragraph.setAlignment(ParagraphAlignment.CENTER);
	run=paragraph.createRun();
	run.setFontSize(14);
	run.setText("");
	//Set bottom border to paragraph
	
   paragraph.setBorderBottom(Borders.BASIC_BLACK_DASHES);
        
   //Set left border to paragraph
   paragraph.setBorderLeft(Borders.BASIC_BLACK_DASHES);
        
   //Set right border to paragraph
   paragraph.setBorderRight(Borders.BASIC_BLACK_DASHES);
        
   //Set top border to paragraph
   paragraph.setBorderTop(Borders.BASIC_BLACK_DASHES);
   String result="";
   if(Double.parseDouble(a[13])>0)
	   result="Positive";
	   else
		   result="Negative";
	   	   
   
   
   run.setText("Heart disease diagnosis result: ");
   
   run.setBold(true);
   run.setText(result);
   
   
   
   
   paragraph = document.createParagraph();
    paragraph.setAlignment(ParagraphAlignment.CENTER);
	run=paragraph.createRun();
	run.setFontSize(12);
   
   run.setText("Note: The results are only 80% accurate. Please consult the doctors to confirm the diagnosis.");
   
   
   
   paragraph = document.createParagraph();
    paragraph.setAlignment(ParagraphAlignment.CENTER);
	run=paragraph.createRun();
	run.setFontSize(6);
	
   
   run.setText("Report automatically generated by Technical Team, Heart Care+.");   
   
   
   
   
   
  





  document.write(out);
   out.close();
   System.out.println("report.docx written successfully");
   }
}