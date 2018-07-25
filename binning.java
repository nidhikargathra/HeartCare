import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

class binning{
	static String attrib[][]={{null},{"0.0","1.0"},{"1.0","2.0","3.0","4.0"},{null},{null},{"0.0","1.0"},{"0.0","1.0","2.0"},{null},
                              {"0.0","1.0"},{null},{"1.0","2.0","3.0"},{"0.0","1.0","2.0","3.0"},{"3.0","6.0","7.0"}};
	static int counte=0;
    public static void main(String args[])throws IOException{
		BufferedReader br1 =new BufferedReader(new InputStreamReader(new FileInputStream("missingvalue.txt")));
		String counts="";counte=0;
		while((counts=br1.readLine())!=null)counte++;
		
        float arr[][]=new float[counte][14];
        int y=0;
        for(int p=0;p<14;p++){
            int x=0;
           
            BufferedReader br =new BufferedReader(new InputStreamReader(new FileInputStream("missingvalue.txt")));
            String line=new String();
            Map<Integer,Float> map=new TreeMap<Integer,Float>();
            Map<Integer,Float> sortedMap=new TreeMap<Integer,Float>();
            int i=0;
            float a[]=new float[counte];
            float temp=0;
            while((line=br.readLine())!=null){
                String s[]=line.split(",");
                temp=Float.parseFloat(s[p]);
                map.put(i++,temp);
            }
            sortedMap = sortByValue(map);
            binning(sortedMap,p);
            TreeMap<Integer, Float> sortedtree = new TreeMap<>(sortedMap);
            //System.out.println("Data set after binning and sorting of "+p);
            
            for (Float value : sortedtree.values()) {
                arr[x][y]=value;
                x++;
            } 
            y++;
            
        } 
        PrintWriter writer = new PrintWriter("binvalueoutput.txt", "UTF-8");
            for(int q=0;q<counte;q++){
                for(int w=0;w<14;w++){
					if(w!=13&&attrib[w][0]!=null){
                   // System.out.print("  "+arr[q][w]);
						int check=0;
						for(int a=0;a<attrib[w].length;a++){
							if(arr[q][w]==Float.parseFloat(attrib[w][a]))
								check=1;
						}
						if(check==0){
							double min=1000;
							for(int a=0;a<attrib[w].length;a++){
								double dist=Math.abs(arr[q][w]-Float.parseFloat(attrib[w][a]));
								if(dist<=min)
									min=Float.parseFloat(attrib[w][a]);
							}
							writer.print(min+",");
						}
						else{
							writer.print(arr[q][w]+",");
						}
					}
					else{
						if(w!=13)
							writer.print(arr[q][w]+",");
						//else
						//	writer.print(arr[q][w]);
						else{
							if(arr[q][13]>0)
								writer.print("2");
							else
								writer.print("0");
						}
						} 
					}
				
				if(q!=counte-1)
					writer.println("");
			}
            
           // System.out.println("");
			
           
            writer.close();
		}
        

    public static void binning(Map sortedMap,int p){
		if(p==13)
		{
			Iterator<Map.Entry<Integer, Float>> entries1 = sortedMap.entrySet().iterator();
			Map.Entry<Integer,Float> entry = entries1.next();
			
		}
		else{
			DecimalFormat df = new DecimalFormat();
			if(p==9){
				df.setMaximumFractionDigits(1);
			}
			else{
				df.setMaximumFractionDigits(0);
			}
			float a[]=new float[sortedMap.size()];
			int i=0;int count=0;
			Iterator<Map.Entry<Integer, Float>> entries = sortedMap.entrySet().iterator();
			while (entries.hasNext()) {
				Map.Entry<Integer,Float> entry = entries.next();
				a[i++]= entry.getValue();
			}
			int k=0;
			String bin[]=new String[(counte/5)+1];
			float sum=0;float avg=0;
			for(int j=0;j<counte;j++){
				if(j%5==0&&j!=0){
					avg=sum/5;
					bin[k++]=df.format(avg);
					sum=0;
				}
				sum=sum+a[j];
			}
			int o=0;
			count=0;
			Iterator<Map.Entry<Integer, Float>> entries1 = sortedMap.entrySet().iterator();
			while (entries1.hasNext()) {
				Map.Entry<Integer,Float> entry = entries1.next();
				if(count%5==0&&count<counte-5&&count!=0)o++;
				entry.setValue(Float.parseFloat(bin[o]));
				count++;
			}
		}
    }


    public static Map sortByValue(Map unsortedMap) {
	Map sortedMap = new TreeMap(new ValueComparator(unsortedMap));
	sortedMap.putAll(unsortedMap);
	return sortedMap;
	}
    }

class ValueComparator implements Comparator {
    Map map;
    public ValueComparator(Map map) {
	this.map = map;
    }
 
    @Override
    public int compare(Object keyA, Object keyB) {
        Comparable valueA = (Comparable) map.get(keyA);
        Comparable valueB = (Comparable) map.get(keyB);
        int a=valueB.compareTo(valueA);
        if(a!=1)
            return -1;
        else
            return 1;
    }

}