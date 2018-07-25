import java.io.*;
class valueCorrection{
    static String attrib[][]={{"40","60","60"},{"0.0","1.0"},{"1.0","2.0","3.0","4.0"},{"80","90","90"},
                              {"200","400","400"},{"0.0","1.0"},{"0.0","1.0","2.0"},{"100","150","150"},
                              {"0.0","1.0"},{"1","1"},{"1.0","2.0","3.0"},{"0.0","1.0","2.0","3.0"},{"3.0","6.0","7.0"}};
    public static void main(String args[]) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream("binvalueoutput1.txt")));
        PrintWriter pw1=new PrintWriter(new FileWriter("binvalueoutput.txt"));
        pw1.print("");
        pw1.close();
        PrintWriter pw2=new PrintWriter(new FileWriter("binvalueoutput.txt",true));
        String line="";
        while((line=br.readLine())!=null){
            String s[]=line.split(",");
            for(int i=0;i<13;i++){
                if(!(i==0||i==3||i==5||i==7||i==9)){
                    int check=0;
                    for(int j=0;j<attrib[i].length;j++){
                        if(s[i].equals(attrib[i][j])){
                            check=1;
                        }
                    }
                    if(check==0){
                        float min=100;
                        int index=100;
                        for(int j=0;j<attrib[i].length;j++){
                            float x=(Math.abs(Float.parseFloat(s[i])-Float.parseFloat(attrib[i][j])));
                            if(x<min){
                                min=x;
                                index=j;
                            }
                        }
                        s[i]=attrib[i][index];
                    }
                }
                pw2.print(s[i]+",");
                pw2.flush();
            }
            pw2.println();
            pw2.flush();
        }
    }
}