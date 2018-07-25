import java.io.IOException;  
import org.jsoup.Jsoup;  
import org.jsoup.nodes.Document;  
import org.jsoup.nodes.Element;  
import org.jsoup.select.Elements;  
public class FirstJsoupExample2 {  
     public static void main( String[] args ) throws IOException{  
        Document doc = Jsoup.connect("http://www.webmd.com/heart-disease/features/cardiologists-guide-to-a-healthy-heart").get();
Elements el = doc.select("div#textArea");
for (Element e : el) {
    System.out.println(e.text());
}

//page2

        Document doc1 = Jsoup.connect("http://www.webmd.com/heart-disease/features/cardiologists-guide-to-a-healthy-heart?page=2").get();
Elements el1 = doc1.select("div#textArea");
for (Element e : el1) {
    System.out.println(e.text());
}



}  
}  



