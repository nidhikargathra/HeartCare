import java.io.*;

class hi{

	public static void main(String args[]){
try{
	PrintWriter writer1 = new PrintWriter(new FileWriter("hi1.txt"));
            writer1.println("hi");
            writer1.flush();
}catch(Exception e){ }
}}