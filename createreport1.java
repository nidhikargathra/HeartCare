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

  
public class createreport1 {  
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
   new File("report1.docx"));
      
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
	
	
   paragraph = document.createParagraph();
    paragraph.setAlignment(ParagraphAlignment.LEFT);
	run=paragraph.createRun();
	run.setFontSize(12);
   
	
   run.setText("Symptom");
   run.setText("Normal/Standard value		");
   run.setText("Actual Value		");
   run.addBreak();
   String s="";
    
   run.setText("Chest Pain Type		");
  run.setText("Non-Anginal Pain		");
   if(Double.parseDouble(a[2])==1)
	   s="Typical Angina";
   else if(Double.parseDouble(a[2])==2)
	   s="Atypical Angina";
   else if(Double.parseDouble(a[2])==1)
	   s="Non- Angina Pain";
   else
	   s="Asymptomatic";
	   
  run.setText(s);
  
  run.addBreak();
   //create second row
  
   run.setText("Resting Blood Pressure		");
  run.setText("120 mm Hg		");
   run.setText(a[3]+"mm Hg");
   //create third row
   
   run.addBreak();
  
   run.setText("Cholesterol		");
   run.setText("140-250 mg/dl		");
   run.setText(a[4]+"mg/dl");
   
   run.addBreak();
   //4
   
   run.setText("Fasting blood sugar		");
   run.setText("70-110 (<120) mg/dl		");
   if(Double.parseDouble(a[5])==0)
	   s="<120 mg/dl";
   else
	   s=">120 mg/dl";
   
   run.setText(s);
   
   run.addBreak();
   //5

   run.setText("Resting ECG report		");
   run.setText("Normal		");
   
   if(Double.parseDouble(a[6])==0)
	   s="Normal";
   else if(Double.parseDouble(a[6])==1)
	   s="Abnormality";
   else
	   s="Hypertrophy";
	   
   
   run.setText(s);
   run.addBreak();
   //6
   
   run.setText("Maximum Heart Rate achieved		");
   run.setText("150-180 bpm		");       
   run.setText(a[7]+"bpm");
   //7
   
   run.addBreak();
   
   
  run.setText("Exercise induced angina		");
  run.setText("No		");
   
   if(Double.parseDouble(a[8])==1)
	   s="Yes";
   else
	   s="No";
   run.setText(s);
   //8
   run.addBreak();
  
  run.setText("Old Peak		");
  run.setText("<2.6		");
  run.setText(a[9]);
   //9
   run.addBreak();
   
   run.setText("Slope		");
   run.setText("Flat		");
  
   
   if(Double.parseDouble(a[10])==1)
	   s="Upslope";
   else if(Double.parseDouble(a[2])==2)
	   s="Flat";
   else
	   s="Downslope";
   run.setText(s); 
   
   //10
   run.addBreak();
   
   run.setText("No. of blood vessels colored		");
   run.setText("0		");
   run.setText(a[11]);
 //11
 run.addBreak();
   run.setText("Thal		");
   run.setText("Normal		");
   
   if(Double.parseDouble(a[12])==3)
	   s="Normal";
   else if(Double.parseDouble(a[10])==6)
	   s="Fixed Defect";
   else
	   s="Reversible Defect";
   
   run.setText(s);
   
   
   
   
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