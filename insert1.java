import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.WriteConcern;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.ServerAddress;
import java.util.*;
import java.io.*;
public class insert1 extends BasicDBObject {

   public static void main( String args[] )throws IOException{
String line,s1,s2;
  BufferedReader br =new BufferedReader(new InputStreamReader(new FileInputStream("insertdb.txt")));
 // To connect to mongodb server
         MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
         // Now connect to your databases
         DB db = mongoClient.getDB( "Central_DB" );
	 System.out.println("Connect to database successfully");
        // boolean auth = db.authenticate(myUserName, myPassword);
	// System.out.println("Authentication: "+auth);      
//DBCollection coll = db.createCollection("patient_data");	
        DBCollection coll = db.getCollection("patient_data");
         System.out.println("Collection for Swizerland DB selected successfully");

 while((line=br.readLine())!=null){
          
          //StringTokenizer st1=new StringTokenizer(line," ,",false);
       String s[]=line.split(",");
            //System.out.println(st1.nextToken());	
System.out.println("hell");
         BasicDBObject doc = new BasicDBObject("Age", s[1]).
            append("Sex", s[2]).
            append("Chest Pain", s[3]).
            append("trestbps", s[4]).
	    append("chol",s[5]). 
            append("fbs",s[6]).
            append("restecg",s[7]).
            append("thalach",s[8]).
            append("exang",s[9]).
            append("oldpeak",s[10]).
            append("slope",s[11]).
            append("ca",s[12]).
            append("thal",s[13]).
            append("num",s[14]);
			
//System.out.println("hell");
         coll.insert(doc);

         System.out.println("Document inserted successfully");
}
     
   }
}
