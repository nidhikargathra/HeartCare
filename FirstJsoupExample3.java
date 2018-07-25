import java.io.IOException;  
import org.jsoup.Jsoup;  
import org.jsoup.nodes.Document;  
import org.jsoup.nodes.Element;  
import org.jsoup.select.Elements;  
import java.io.*;
import java.util.*;
public class FirstJsoupExample3 {  
     public static void main( String[] args ) throws IOException{  
PrintStream out = new PrintStream(new FileOutputStream("webExpertAdvice.txt"));
System.setOut(out);       
System.out.println(" ");

        Document doc = Jsoup.connect("https://www.nhlbi.nih.gov/health/health-topics/topics/hb/treated").get();
Elements el = doc.select("div.field-item.even");
for (Element e : el) {
    System.out.println(e.text());
System.out.println();


}
System.out.println(" ");
System.out.println("This article was fetched from");
System.out.println("The official website of National Institute of health. US Department of health & human services");
System.out.println("LINK:https://www.nhlbi.nih.gov/health/health-topics/topics/hb/treated");



}  
}  



