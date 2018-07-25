import java.io.IOException;  
import org.jsoup.Jsoup;  
import org.jsoup.nodes.Document;  
import org.jsoup.nodes.Element;  
import org.jsoup.select.Elements;  
import java.io.*;
import java.util.*;


import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import java.awt.Desktop;

import java.net.URI;
import java.net.URISyntaxException;


public class web1 {  

private static Pattern patternDomainName;
  private Matcher matcher;
  private static final String DOMAIN_NAME_PATTERN 
	= "([a-zA-Z0-9]([a-zA-Z0-9\\-]{0,61}[a-zA-Z0-9])?\\.)+[a-zA-Z]{2,6}";
  static {
	patternDomainName = Pattern.compile(DOMAIN_NAME_PATTERN);
  }
     public static void main( String[] args ) throws IOException{  
	 
	 int a=(int)(1+(3-1)*(Math.random()));
	 PrintStream out = new PrintStream(new FileOutputStream("webExpertAdvice.txt"));
	 switch(a){
		 case 1:

System.setOut(out);       
System.out.println(" ");
System.out.println("SOURCE ONE");
System.out.println("");
        Document doc = Jsoup.connect("https://www.nhlbi.nih.gov/health/health-topics/topics/hb/treated").get();
Elements el = doc.select("div.field-item.even >*");
for (Element e : el) {
    System.out.println(e.text());
System.out.println();


}
System.out.println(" ");
System.out.println("This article was fetched from");
System.out.println("The official website of National Institute of health. US Department of health & human services");
System.out.println("LINK:https://www.nhlbi.nih.gov/health/health-topics/topics/hb/treated");

System.out.println();
System.out.println();
System.out.println();
System.out.println();
break;
case 2:
System.setOut(out);
System.out.println("SOURCE TWO");
System.out.println("");
Document doc1 = Jsoup.connect("http://www.webmd.com/heart-disease/guide/heart-disease-treatment-care").get();
Elements el1 = doc1.select("div.chapterList_fmt >*");
for (Element e : el1) {
    System.out.println(e.text());
System.out.println();



}  
System.out.println(" ");
System.out.println("This article was fetched from WebMD.com- The leading source for trustworthy and timely health and medical news and information. Providing credible health information, supportive community, and educational services by blending award-winning expertise in content, community services, expert commentary, and medical review.");
System.out.println("The official website of National Institute of health. US Department of health & human services");
System.out.println("LINK:http://www.webmd.com/heart-disease/guide/heart-disease-treatment-care");

System.out.println("");
System.out.println("");
System.out.println("");
System.out.println("");
break;



}
	 }
}	 



