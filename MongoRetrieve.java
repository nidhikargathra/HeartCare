import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.WriteConcern;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.ServerAddress;
import java.io.PrintWriter;
import java.util.Arrays;
import java.nio.file.*;
import java.io.*;

public class MongoRetrieve{
   public static void main( String args[] )throws IOException{
      try{   
	 // To connect to mongodb server
         MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
         // Now connect to your databases
         DB db = mongoClient.getDB( "Central_DB" );
	 //System.out.println("Connect to database successfully");
        // boolean auth = db.authenticate(myUserName, myPassword);
	 // System.out.println("Authentication: "+auth);         
         DBCollection coll = db.getCollection("patient_data");

         //System.out.println("Collection Profile selected successfully");
         DBCursor cursor = coll.find();
	//System.out.println("hello"+cursor.next());
	//System.out.println("test");
         int i=1;
         PrintWriter pw = new PrintWriter("SwissData1.txt", "UTF-8");

         while (i<=cursor.count()) { 

          //  System.out.println("Inserted Document: "+i); 
            //System.out.println(cursor.next().get("Age")); 
            
            pw.print(cursor.next().get("Age")+","+cursor.curr().get("Sex")+","+cursor.curr().get("Chest Pain")+","+cursor.curr().get("trestbps")
            +","+cursor.curr().get("chol")+","+cursor.curr().get("fbs")+","+cursor.curr().get("restecg")+","+cursor.curr().get("thalach")
            +","+cursor.curr().get("exang")+","+cursor.curr().get("oldpeak")+","+cursor.curr().get("slope")+","+cursor.curr().get("ca")
            +","+cursor.curr().get("thal")+","+cursor.curr().get("num"));
			if(i!=cursor.count())
            pw.println();
            pw.flush();
            i++;
         }
      }catch(Exception e){
	    // System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		System.exit(0);
	  }
	  
	  //copy paste
	  
	  Path source = Paths.get("SwissData1.txt");
        Path dest = Paths.get("SwissData.txt");
        CopyOption[] options;
            options = new CopyOption[]{
                StandardCopyOption.REPLACE_EXISTING,
                StandardCopyOption.COPY_ATTRIBUTES
            };
        Files.copy(source, dest, options);
	  
	  
	  
	  
   }
}