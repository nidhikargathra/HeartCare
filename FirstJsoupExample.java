import java.io.IOException;  
import org.jsoup.Jsoup;  
import org.jsoup.nodes.Document;  
import org.jsoup.nodes.Element;  
import org.jsoup.select.Elements;  
public class FirstJsoupExample {  
     public static void main( String[] args ) throws IOException{  
        Document doc = Jsoup.connect("http://en.wikipedia.org/wiki/Main_Page").get();
Elements el = doc.select("div#mp-tfa");
for (Element e : el) {
    System.out.println(e.text());
}
}  
}  