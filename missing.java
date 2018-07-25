import java.io.*;
import java.text.DecimalFormat;
import java.util.*;
class missing{
    public static void main(String args[])throws IOException{
        
       LineNumberReader  lnr = new LineNumberReader(new FileReader("SwissData.txt"));
lnr.skip(Long.MAX_VALUE);
int lines=lnr.getLineNumber();
//System.out.println(lnr.getLineNumber() + 1); //Add 1 because line index starts at 0
// Finally, the LineNumberReader object should be closed to prevent resource leak
lnr.close();
BufferedReader br =new BufferedReader(new InputStreamReader(new FileInputStream("SwissData.txt")));
       DecimalFormat df = new DecimalFormat();
       df.setMaximumFractionDigits(1);
        String line1,line2="";
        String line=new String();
        String arr[][]=new String[14][lines+1];
        int i=0,j=0;
       
        String co;
        while((line=br.readLine())!=null){

            StringTokenizer st=new StringTokenizer(line,",",false);
            i=0;
            while(st.hasMoreTokens()){
                co=st.nextToken();
                         
               
                arr[i][j]=co;
               
                i++;   
            }
            j++;
        }
       
        
        BufferedReader br1 =new BufferedReader(new InputStreamReader(new FileInputStream("missVals.txt")));
       
        while((line1=br1.readLine())!=null){

            StringTokenizer st=new StringTokenizer(line1," ",false);
            
        
                    
            for(j=0;j<lines+1;j++){
                for(i=0;i<14;i++){
                if(arr[i][j].equals("?")){
                   arr[i][j]=st.nextToken();
                   
                }    
                }
            }
        }
           PrintWriter writer = new PrintWriter("missingvalue.txt", "UTF-8");

        //System.out.println("You may proceed to check the accuracy");
        for(int m=0;m<lines+1;m++){
        for(int l=0;l<14;l++){
            if(l!=13)
                writer.print(arr[l][m]+",");
            else
                writer.print(arr[l][m]);
           }
        if(m<lines)
            writer.println("");
        }

			writer.close();
                        
        BufferedReader br2 =new BufferedReader(new InputStreamReader(new FileInputStream("missingvalue.txt")));
        while((line1=br2.readLine())!=null){
            line2=line1;
        }
        PrintWriter writer1 = new PrintWriter("input.txt", "UTF-8");
        writer1.println(line2);
        writer1.flush();
    }
}