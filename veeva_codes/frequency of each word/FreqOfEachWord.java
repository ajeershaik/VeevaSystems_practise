import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;

class FreqOfEachWord{
	public static void main(String args[])throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("ENter thge sentence");
		String str = br.readLine().trim();
		//System.out.println("Sentence is: "+ str);
		frequency(str);
	}
	
	public static void frequency(String str){
		HashMap<String,Integer> hm = new HashMap<>();
		String words[] = str.split("[ ,!:.]+");
		int n = words.length;
		//System.out.println(Arrays.toString(words));
		for(int i=0;i<n;i++){
			String s = words[i];
			hm.put(s,hm.getOrDefault(s,0)+1);
		}
		System.out.println("words ---------> frequency");
		for (Map.Entry<String, Integer> e : hm.entrySet()){
			String key = e.getKey();
			Integer value = e.getValue();
			System.out.println(key+"--------->"+value);
		}
	}
}