import java.io.*;
import java.nio.file.*;


class test1{
    //static String attrib[][]={{null},{"0.0","1.0"},{"1.0","2.0","3.0","4.0"},{null},
    //                          {null},{"0.0","1.0"},{"0.0","1.0","2.0"},{null},
    //                          {"0.0","1.0"},{null},{"1.0","2.0","3.0"},{"0.0","1.0","2.0","3.0"},{"3.0","6.0","7.0"}};
     static String attrib[][]={{"40","60","60"},{"0.0","1.0"},{"1.0","2.0","3.0","4.0"},{"80","90","90"},
                              {"200","400","400"},{"0.0","1.0"},{"0.0","1.0","2.0"},{"100","150","150"},
                              {"0.0","1.0"},{"1","1"},{"1.0","2.0","3.0"},{"0.0","1.0","2.0","3.0"},{"3.0","6.0","7.0"}};
    static int b_size[]={3,2,4,3,3,2,3,3,2,2,3,4,3};
    static boolean flag[]={false,false,false,false,false,false,false,false,false,false,false,false,false};
    static int counter=1;
    static double input[]=new double[13];
    
    static double entropy_calc(double p,double n){
        double entropy=-((p/(p+n))*(Math.log(p/(p+n))/Math.log(2))+(n/(p+n))*(Math.log(n/(p+n))/Math.log(2)));
        return entropy;
    }

    public static void count()throws IOException{                            // calculate number of yes and no
        PrintWriter writer0 = new PrintWriter("no.txt", "UTF-8");
        PrintWriter writer4 = new PrintWriter("yes.txt", "UTF-8");
        PrintWriter writer = new PrintWriter("count.txt", "UTF-8");
        BufferedReader br1 =new BufferedReader(new InputStreamReader(new FileInputStream("binvalueoutput.txt")));
        String line=new String();
        int yes=0,no=0;
        while((line=br1.readLine())!=null){
            String s[]=line.split(",");
            if(Float.parseFloat(s[13])==0){
                no++;
                for(int q=0;q<14;q++){
                    writer0.print(s[q]+",");
                }
                writer0.println("");
                writer0.flush();
            }
            else{
                yes++;
                for(int q=0;q<14;q++){
                    writer4.print(s[q]+",");
                }
                writer4.println("");
                writer4.flush();
            }
        }
        writer.println(no);
        writer.flush();
        writer.println(yes);
        writer.flush();
        writer.close();
        writer0.close();
        writer4.close();
        br1.close();
    }
    
    public static void main(String args[]) throws IOException{
        
        BufferedReader br_input =new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
        String test;
        PrintWriter clear=new PrintWriter("compare.txt");
        clear.print("");
        while((test=br_input.readLine())!=null){
            PrintWriter compare=new PrintWriter(new FileWriter("compare.txt",true));
            compare.print(test);
            compare.flush();
            compare.close();
            for(int i=0;i<13;i++)
                flag[i]=false;
            counter=1;
    
        String input_line[]= test.split(",");
        for(int x=0;x<=12;x++){
            input[x]=Double.parseDouble(input_line[x]);
        }
        //br_input.close();
        Path source = Paths.get("binvalueoutput.txt");
        Path dest = Paths.get("new.txt");
        CopyOption[] options;
            options = new CopyOption[]{
                StandardCopyOption.REPLACE_EXISTING,
                StandardCopyOption.COPY_ATTRIBUTES
            };
        Files.copy(source, dest, options);
        
        count();
        
        BufferedReader br =new BufferedReader(new InputStreamReader(new FileInputStream("count.txt")));
        double a=Double.parseDouble(br.readLine());
        double b=Double.parseDouble(br.readLine());
        br.close();
        String line="";
        double max_gain=-1;
        int max_attrib=-1;
        double entropy_parent=entropy_calc(a,b);
        for(int i=0;i<13;i++){
            int count[]=new int[4];
            int j=0;
            double p[]=new double[4];
            double n[]=new double[4];
            BufferedReader br1 =new BufferedReader(new InputStreamReader(new FileInputStream("new.txt")));
            while((line=br1.readLine())!=null){
                String s[]=line.split(",");
                for(j=0; j<attrib[i].length; j++){
                    if(i!=0&&i!=3&&i!=4&&i!=7&&i!=9){
                        if(s[i].equals(attrib[i][j])){
                            count[j]++;
                            if(s[13].equals("0")){
                                p[j]++;
                            }
                            else
                                n[j]++;
                        }
                    }
                    else if(i==9){
                        if(Double.parseDouble(s[i])<=Double.parseDouble(attrib[i][0])){
                            count[0]++;
                            if(s[13].equals("0")){
                                p[0]++;
                            }
                            else
                                n[0]++;
                        }
                        else{
                            count[1]++;
                            if(s[13].equals("0")){
                                p[1]++;
                            }
                            else
                                n[1]++;
                        }
                    }
                    else{
                        if(Double.parseDouble(s[i])<=Double.parseDouble(attrib[i][0])){
                            count[0]++;
                            if(s[13].equals("0")){
                                p[0]++;
                            }
                            else
                                n[0]++;
                        }
                        else if(Double.parseDouble(s[i])>Double.parseDouble(attrib[i][0])&&Double.parseDouble(s[i])<Double.parseDouble(attrib[i][1])){
                            count[1]++;
                            if(s[13].equals("0")){
                                p[1]++;
                            }
                            else
                                n[1]++;
                        }
                        else if(Double.parseDouble(s[i])>=Double.parseDouble(attrib[i][2])){
                            count[2]++;
                            if(s[13].equals("0")){
                                p[2]++;
                            }
                            else
                                n[2]++;
                        }
                    }
                }
            }

            double total=0;
            double final_entropy=0;
            double entropy[]=new double[j];
            for(int k=0;k<j;k++)
                total=total+count[k];
            for(int k=0;k<j;k++){
                entropy[k]=entropy_calc(p[k],n[k]);
                final_entropy=final_entropy+((count[k]/total)*entropy[k]);
            }
            double gain=entropy_parent-final_entropy;
            if(gain>max_gain){
                max_gain=gain;
                max_attrib=i;
            }
            br1.close();
        }
        //System.out.println("max_attrib "+max_attrib);
        //System.out.println("-----------------------------------------------------------------------------------");
        entropy_func(max_attrib);
        }
    }
    static String mod_count(int max_attrib,String value) throws IOException{            // segregate into yes no files
        PrintWriter writer0 = new PrintWriter("no.txt", "UTF-8");
        PrintWriter writer4 = new PrintWriter("yes.txt", "UTF-8");
        PrintWriter writer = new PrintWriter("count.txt", "UTF-8");
        
        BufferedReader br1 =new BufferedReader(new InputStreamReader(new FileInputStream("new.txt")));
	String line=new String();
        int yes=0,no=0,linecount=0;
        while((line=br1.readLine())!=null){
            String s[]=line.split(",");
            if(s[max_attrib].equals(value)){
                if(Float.parseFloat(s[13])==0){
                    no++;
                    for(int q=0;q<14;q++){
                        writer0.print(s[q]+",");
                    }
                    writer0.println("");
                    writer0.flush();
                }
                else{
                    yes++;
                    linecount++;
                    for(int q=0;q<14;q++){
                        writer4.print(s[q]+",");
                    }
                    writer4.println("");
                    writer4.flush();
                }
            }
        }
        writer.println(no);
        writer.flush();
        writer.println(yes);
        writer.flush();
        writer.close();
        writer0.close();
        writer4.close();
        br1.close();
        PrintWriter writer5 = new PrintWriter("new.txt");
        writer5.print("");
        writer5.close();
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("new.txt", true)));
        BufferedReader br2 =new BufferedReader(new InputStreamReader(new FileInputStream("no.txt")));
        BufferedReader br3 =new BufferedReader(new InputStreamReader(new FileInputStream("yes.txt")));
        while((line=br2.readLine())!=null){
            out.println(line);
            //System.out.println(line);
            out.flush();
        }
        while(linecount>1&&((line=br3.readLine())!=null)){
            out.println(line);
            //System.out.println(line);
            out.flush();
            --linecount;
        }
        line=br3.readLine();
        if(line!=null){
            out.print(line);
            //System.out.println(line);
            out.flush();
        }
        out.close();
        br2.close();
        br3.close();
        
        if(yes>=no){
           
            return "yes";
        }
        else {
            
            return "no";
        }
    }
    
    static void entropy_func(int max_attrib) throws IOException{           //discrete entropy calc
        
        boolean entropy_check=false;
        String line="";
        String value="";
        
        counter++;
        double max_gain=-1;
        flag[max_attrib]=true;
        int branch=b_size[max_attrib];
        while(branch>0){
            value=attrib[max_attrib][--branch];
            if(value.equals(Double.toString(input[max_attrib])))
                break;
        }
        String result=mod_count(max_attrib,value);
        BufferedReader br =new BufferedReader(new InputStreamReader(new FileInputStream("count.txt")));
	double a=Double.parseDouble(br.readLine());
       	double b=Double.parseDouble(br.readLine());
        br.close();
	double entropy_parent=entropy_calc(a,b);
        for(int i=0;i<13;i++){
            if(!(attrib[i][0] == null) && !flag[i] ){
                int count[]=new int[4];
                int j=0;
                double p[]=new double[4];
                double n[]=new double[4];
                BufferedReader br1 =new BufferedReader(new InputStreamReader(new FileInputStream("new.txt")));
                while((line=br1.readLine())!=null){
                    String s[]=line.split(",");
                    for(j=0; j<attrib[i].length; j++){
                        if(i!=0&&i!=3&&i!=4&&i!=7&&i!=9){
                            if(s[i].equals(attrib[i][j])){
                                count[j]++;
                                if(s[13].equals("0")){
                                    p[j]++;
                                }
                                else
                                    n[j]++;
                            }
                        }
                        else if(i==9){
                            if(Double.parseDouble(s[i])<=Double.parseDouble(attrib[i][0])){
                                count[0]++;
                                if(s[13].equals("0")){
                                    p[0]++;
                                }
                                else
                                    n[0]++;
                            }
                            else{
                                count[1]++;
                                if(s[13].equals("0")){
                                    p[1]++;
                                }
                                else
                                    n[1]++;
                            }
                        }
                        else{
                            if(Double.parseDouble(s[i])<=Double.parseDouble(attrib[i][0])){
                                count[0]++;
                                if(s[13].equals("0")){
                                    p[0]++;
                                }
                                else
                                    n[0]++;
                            }
                            else if(Double.parseDouble(s[i])>Double.parseDouble(attrib[i][0])&&Double.parseDouble(s[i])<Double.parseDouble(attrib[i][1])){
                                count[1]++;
                                if(s[13].equals("0")){
                                    p[1]++;
                                }
                                else
                                    n[1]++;
                            }
                            else if(Double.parseDouble(s[i])>=Double.parseDouble(attrib[i][2])){
                                count[2]++;
                                if(s[13].equals("0")){
                                    p[2]++;
                                }
                                else
                                    n[2]++;
                            }
                        }
                    }
                }
                br1.close();
                double total=0;
                double final_entropy=0;
                double entropy[]=new double[j];
                for(int k=0;k<j;k++)
                    total=total+count[k];
                for(int k=0;k<j;k++){
                    if(p[k]!=0&&n[k]!=0){
                        entropy[k]=entropy_calc(p[k],n[k]);
                        final_entropy=final_entropy+((count[k]/total)*entropy[k]);
                    }
                }
                double gain=entropy_parent-final_entropy;
                if(gain>max_gain){
                    max_gain=gain;
                    max_attrib=i;
                }
                if(final_entropy<=0)
                    entropy_check=true;
                
            }
        }
        PrintWriter writer6 = new PrintWriter("output.txt", "UTF-8");
        if(entropy_check||counter==13){
            PrintWriter compare=new PrintWriter(new FileWriter("compare.txt",true));
            System.out.println("Your diagnosis result is"+" "+result);
            if(result.equals("yes")){
				 writer6.println("yes");
			writer6.flush();
			writer6.close();
               compare.println("2");
			   
			}
            else{
				writer6.println("no");
			writer6.flush();
			writer6.close();
                compare.println("0");
			}
            compare.flush();
            compare.close();
			System.exit(0);
        }
        else{
            //System.out.println("max attrib:"+max_attrib);
            //System.out.println("-----------------------------------------------------------------------------------");
            if(max_attrib==0 ||max_attrib==3 || max_attrib==4 ||max_attrib==7 || max_attrib==9)
                entropy_cont_func(max_attrib);
            else
                entropy_func(max_attrib);
        }
        
    }
    static void entropy_cont_func(int max_attrib)throws IOException{            //continuous entropy part
        
	boolean entropy_check=false;
        String line="";
	double value1=0;
	double value2=0;
	
        counter++;
        double max_gain=-1;
        flag[max_attrib]=true;
        int branch=b_size[max_attrib];
        String result="";
        while(branch>0){
            value1=Double.parseDouble(attrib[max_attrib][--branch]);
            if(branch>1)
                branch--;
            value2=Double.parseDouble(attrib[max_attrib][--branch]);
            if(input[max_attrib]<=value2){
                result=mod_count3(max_attrib,value2);
            }
            if((value1>=input[max_attrib])&&(value2<input[max_attrib])){
                result=mod_count2(max_attrib,value1,value2);
                break;
            }
            if(input[max_attrib]>value1){
                result= mod_count1(max_attrib,value1);
                break;
            }
        }
        BufferedReader br =new BufferedReader(new InputStreamReader(new FileInputStream("count.txt")));
        double a=Double.parseDouble(br.readLine());
       	double b=Double.parseDouble(br.readLine());
        br.close();
	double entropy_parent=entropy_calc(a,b);
        for(int i=0;i<13;i++){
            if(!(attrib[i][0] == null) && !flag[i] ){
                int count[]=new int[4];
                int j=0;
                double p[]=new double[4];
                double n[]=new double[4];
                BufferedReader br1 =new BufferedReader(new InputStreamReader(new FileInputStream("new.txt")));
                while((line=br1.readLine())!=null){
                    String s[]=line.split(",");
                    for(j=0; j<attrib[i].length; j++){
                        if(i!=0&&i!=3&&i!=4&&i!=7&&i!=9){
                            if(s[i].equals(attrib[i][j])){
                                count[j]++;
                                if(s[13].equals("0")){
                                    p[j]++;
                                }
                                else
                                    n[j]++;
                            }
                        }
                        else if(i==9){
                            if(Double.parseDouble(s[i])<=Double.parseDouble(attrib[i][0])){
                                count[0]++;
                                if(s[13].equals("0")){
                                    p[0]++;
                                }
                                else
                                    n[0]++;
                            }
                            else{
                                count[1]++;
                                if(s[13].equals("0")){
                                    p[1]++;
                                }
                                else
                                    n[1]++;
                            }
                        }
                        else{
                            if(Double.parseDouble(s[i])<=Double.parseDouble(attrib[i][0])){
                                count[0]++;
                                if(s[13].equals("0")){
                                    p[0]++;
                                }
                                else
                                    n[0]++;
                            }
                            else if(Double.parseDouble(s[i])>Double.parseDouble(attrib[i][0])&&Double.parseDouble(s[i])<Double.parseDouble(attrib[i][1])){
                                count[1]++;
                                if(s[13].equals("0")){
                                    p[1]++;
                                }
                                else
                                    n[1]++;
                            }
                            else if(Double.parseDouble(s[i])>=Double.parseDouble(attrib[i][2])){
                                count[2]++;
                                if(s[13].equals("0")){
                                    p[2]++;
                                }
                                else
                                    n[2]++;
                            }
                        }
                    }
                }
                br1.close();
                double total=0;
                double final_entropy=0;
                double entropy[]=new double[j];
                for(int k=0;k<j;k++)
                    total=total+count[k];
                for(int k=0;k<j;k++){
                    if(p[k]!=0&&n[k]!=0){
                        entropy[k]=entropy_calc(p[k],n[k]);
                        final_entropy=final_entropy+((count[k]/total)*entropy[k]);
                    }
                }
                double gain=entropy_parent-final_entropy;
                if(gain>max_gain){
                    max_gain=gain;
                    max_attrib=i;
                }
                if(final_entropy<=0)
                    entropy_check=true;
                
            }  
        }
	PrintWriter writer6 = new PrintWriter("output.txt", "UTF-8");
        if(entropy_check||counter==13){
            PrintWriter compare=new PrintWriter(new FileWriter("compare.txt",true));
            System.out.println("Your diagnosis result is"+" "+result);
            if(result.equals("yes")){
				 writer6.println("yes");
			writer6.flush();
			writer6.close();
               compare.println("2");
			}
            else{
				writer6.println("no");
			writer6.flush();
			writer6.close();
                compare.println("0");
			}
            compare.flush();
            compare.close();
			System.exit(0);
        }
        else{
            //System.out.println("max attrib:"+max_attrib);
            //System.out.println("-----------------------------------------------------------------------------------");
            if(max_attrib==0 ||max_attrib==3 || max_attrib==4 ||max_attrib==7 || max_attrib==9)
                    entropy_cont_func(max_attrib);
            else
                    entropy_func(max_attrib);
        }
        
    }

    static String mod_count1(int max_attrib,double value1) throws IOException{
	PrintWriter writer0 = new PrintWriter("no.txt", "UTF-8");
        PrintWriter writer4 = new PrintWriter("yes.txt", "UTF-8");
        PrintWriter writer = new PrintWriter("count.txt", "UTF-8");
        
        BufferedReader br1 =new BufferedReader(new InputStreamReader(new FileInputStream("new.txt")));
        String line=new String();
        int yes=0,no=0,linecount=0;
        while((line=br1.readLine())!=null){
            String s[]=line.split(",");
            if(Double.parseDouble(s[max_attrib])>value1){
                if(Float.parseFloat(s[13])==0){
                    no++;
                    for(int q=0;q<14;q++){
                        writer0.print(s[q]+",");
                    }
                    writer0.println("");
                    writer0.flush();
                }
                else{
                    yes++;
                    linecount++;
                    for(int q=0;q<14;q++){
                        writer4.print(s[q]+",");
                    }
                    writer4.println("");
                    writer4.flush();
                }
            }
        }
        br1.close();
        writer.println(no);
        writer.flush();
        writer.println(yes);
        writer.flush();
        writer.close();
        writer0.close();
        writer4.close();
        
        PrintWriter writer5 = new PrintWriter("new.txt");
        writer5.print("");
        writer5.close();
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("new.txt", true)));
        BufferedReader br2 =new BufferedReader(new InputStreamReader(new FileInputStream("no.txt")));
        BufferedReader br3 =new BufferedReader(new InputStreamReader(new FileInputStream("yes.txt")));
        while((line=br2.readLine())!=null){
            out.println(line);
            //System.out.println(line);
            out.flush();
        }
        while(linecount>1&&((line=br3.readLine())!=null)){
            out.println(line);
            //System.out.println(line);
            out.flush();
            --linecount;
        }
        line=br3.readLine();
        if(line!=null){
            out.print(line);
            //System.out.println(line);
            out.flush();
        }
        out.close();
        br2.close();
        br3.close();
        
	//Simple count compare
        if(yes>=no){
            
            return "yes";
        }
        else {
            
            return "no";
        }
    }
    
    static String mod_count2(int max_attrib,double value1,double value2) throws IOException{
        PrintWriter writer0 = new PrintWriter("no.txt", "UTF-8");
        PrintWriter writer4 = new PrintWriter("yes.txt", "UTF-8");
        PrintWriter writer = new PrintWriter("count.txt", "UTF-8");
        
        BufferedReader br1 =new BufferedReader(new InputStreamReader(new FileInputStream("new.txt")));
        String line=new String();
        int yes=0,no=0,linecount=0;
        while((line=br1.readLine())!=null){
            String s[]=line.split(",");
            if(Double.parseDouble(s[max_attrib])<value1 && Double.parseDouble(s[max_attrib])>=value2){
                if(Float.parseFloat(s[13])==0){
                    no++;
                    for(int q=0;q<14;q++){
                        writer0.print(s[q]+",");
                    }
                    writer0.println("");
                    writer0.flush();
                }
                else{
                    yes++;
                    linecount++;
                    for(int q=0;q<14;q++){
                        writer4.print(s[q]+",");
                    }
                    writer4.println("");
                    writer4.flush();
                }
            }
        }
        br1.close();
        writer.println(no);
        writer.flush();
        writer.println(yes);
        writer.flush();
        writer.close();
        writer0.close();
        writer4.close();
        
        PrintWriter writer5 = new PrintWriter("new.txt");
        writer5.print("");
        writer5.close();
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("new.txt", true)));
        BufferedReader br2 =new BufferedReader(new InputStreamReader(new FileInputStream("no.txt")));
        BufferedReader br3 =new BufferedReader(new InputStreamReader(new FileInputStream("yes.txt")));
        while((line=br2.readLine())!=null){
            out.println(line);
            //System.out.println(line);
            out.flush();
        }
        while(linecount>1&&((line=br3.readLine())!=null)){
            out.println(line);
            //System.out.println(line);
            out.flush();
            --linecount;
        }
        line=br3.readLine();
        if(line!=null){
            out.print(line);
            //System.out.println(line);
            out.flush();
        }
        out.close();
        br2.close();
        br3.close();
		
        // Simple count compare
        if(yes>=no){
            
            return "yes";
        }
        else {
            
            return "no";
        }
    }
    
    static String mod_count3(int max_attrib,double value2) throws IOException{
        PrintWriter writer0 = new PrintWriter("no.txt", "UTF-8");
        PrintWriter writer4 = new PrintWriter("yes.txt", "UTF-8");
        PrintWriter writer = new PrintWriter("count.txt", "UTF-8");
        
        BufferedReader br1 =new BufferedReader(new InputStreamReader(new FileInputStream("new.txt")));
        String line=new String();
        int yes=0,no=0,linecount=0;
        while((line=br1.readLine())!=null){
            String s[]=line.split(",");
            if(Double.parseDouble(s[max_attrib])<=value2){
                if(Float.parseFloat(s[13])==0){
                    no++;
                    for(int q=0;q<14;q++){
                        writer0.print(s[q]+",");
                    }
                    writer0.println("");
                    writer0.flush();
                }
                else{
                    yes++;
                    linecount++;
                    for(int q=0;q<14;q++){
                        writer4.print(s[q]+",");
                    }
                    writer4.println("");
                    writer4.flush();
                }
            }
        }
        br1.close();
        writer.println(no);
        writer.flush();
        writer.println(yes);
        writer.flush();
        writer.close();
        writer0.close();
        writer4.close();
        
        PrintWriter writer5 = new PrintWriter("new.txt");
        writer5.print("");
        writer5.close();
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("new.txt", true)));
        BufferedReader br2 =new BufferedReader(new InputStreamReader(new FileInputStream("no.txt")));
        BufferedReader br3 =new BufferedReader(new InputStreamReader(new FileInputStream("yes.txt")));
        while((line=br2.readLine())!=null){
            out.println(line);
            //System.out.println(line);
            out.flush();
        }
        while(linecount>1&&((line=br3.readLine())!=null)){
            out.println(line);
            //System.out.println(line);
            out.flush();
            --linecount;
        }
        line=br3.readLine();
        if(line!=null){
            out.print(line);
            //System.out.println(line);
            out.flush();
        }
        out.close();
        br2.close();
        br3.close();
		
	//Simple count compare
        if(yes>=no){
            
            return "yes";
        }
        else {
            
            return "no";
        }
    }
}