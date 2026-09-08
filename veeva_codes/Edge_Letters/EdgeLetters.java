import java.util.*;
import java.io.*;
//abacaba
//2 
//a b
//b b

class EdgeLetters {
    public static void main(String args[] ) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("enter the string");
        String str = br.readLine().trim();
		System.out.println("enter the queries");
        int q = Integer.parseInt(br.readLine());
		long[][] count = new long[26][26];
		int[] freq = new int[26];
		for(char ch:str.toCharArray()){
			int end = ch-'a';
			for(int st=0;st<26;st++){
				count[st][end] += freq[st];
			}
			count[end][end]++;
			freq[end]++;
		}
		while(q>0){
			System.out.println("enter start and end");
			StringTokenizer st1 = new StringTokenizer(br.readLine());
            char st = st1.nextToken().charAt(0);
            char end = st1.nextToken().charAt(0);
			System.out.println(count[st-'a'][end-'a']);
			q--;
		}
        /*for(int j=0;j<q;j++){
			System.out.println("enter start and end");
			StringTokenizer st1 = new StringTokenizer(br.readLine());
            char st = st1.nextToken().charAt(0);
            char end = st1.nextToken().charAt(0);
			int st_c = 0;
			int end_c = 0;
			int total = 0;
			for(int i=0;i<str.length();i++){
				char ch = str.charAt(i);
				if(ch == st){
					st_c += 1;
				}
				if(ch == end){
					total += st_c;g
				}
			}
			System.out.println("string is:"+str);
			System.out.println("total substrings are: "+total);
        }	*/
    }
}
