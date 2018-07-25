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


public class websearch {  

private static Pattern patternDomainName;
  private Matcher matcher;
  private static final String DOMAIN_NAME_PATTERN 
	= "([a-zA-Z0-9]([a-zA-Z0-9\\-]{0,61}[a-zA-Z0-9])?\\.)+[a-zA-Z]{2,6}";
  static {
	patternDomainName = Pattern.compile(DOMAIN_NAME_PATTERN);
  }
     public static void main( String[] args ) throws IOException{  
	 
	 
PrintStream out = new PrintStream(new FileOutputStream("webExpertAdvice.txt"));
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


websearch obj = new websearch();
	Set<String> result = obj.getDataFromGoogle("Treatment for heart disease");
	for(String temp : result){
		System.out.println(temp);
		  String url = temp;

       /* if(Desktop.isDesktopSupported()){
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.browse(new URI(url));
            } catch (IOException | URISyntaxException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }else{
            Runtime runtime = Runtime.getRuntime();
            try {
                runtime.exec("xdg-open " + url);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }*/
	//break;
	}
	System.out.println(result.size());
  }

  public String getDomainName(String url){
		
	String domainName = "";
	matcher = patternDomainName.matcher(url);
	if (matcher.find()) {
		domainName = matcher.group(0).toLowerCase().trim();
	}
	return domainName;
		
  }
	
  private Set<String> getDataFromGoogle(String query) {
		
	Set<String> result = new HashSet<String>();	
	String request = "https://www.google.com/search?q=" + query + "&num=15";
	System.out.println("Sending request..." + request);
		
	try {

		// need http protocol, set this as a Google bot agent :)
		Document doc = Jsoup
			.connect(request)
			.userAgent(
			  "Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)")
			.timeout(30000).get();

		// get all links
		Elements links = doc.select("a[href]");
		for (Element link : links) {

			String temp = link.attr("href");	
//System.out.println(temp);		//ugly absolute url	
		if(temp.startsWith("/url?q=")){
                                //use regex to get domain name
				result.add(getDomainName(temp));
			}
//result.add((temp));
		}

	} catch (IOException e) {
		e.printStackTrace();
	}
		
	return result;

}  
}

