
import java.io.BufferedReader;
import java.io.InputStreamReader;


import java.util.*;

class PrimeQuery {
    public static void main(String args[] ) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter test cases");
        int t = Integer.parseInt(br.readLine());
        while(t-->0){
            System.out.println("Enter n");
            int n = Integer.parseInt(br.readLine());
            long[] arr = new long[n];
            System.out.println("Enter array elements");
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            for(int i=0;i<n;i++){
                arr[i] = Long.parseLong(st1.nextToken());
            }
			int[] zeroPrefix = new int[n+1];
			int[] onePrefix = new int[n+1];
			for(int i=0;i<n;i++){
				zeroPrefix[i+1] = zeroPrefix[i]+(arr[i]==0?1:0);
				onePrefix[i+1] = onePrefix[i]+(arr[i]==1?1:0);
			}
            System.out.println("Enter Q");
            int q = Integer.parseInt(br.readLine());
            System.out.println("Enter l and r");
            while(q>0){
				StringTokenizer st2 = new StringTokenizer(br.readLine());
				int l = Integer.parseInt(st2.nextToken());
				int r = Integer.parseInt(st2.nextToken());
				System.out.println("--- "+l+"--- "+r);
				q = q-1;
				int zeroes = zeroPrefix[r] - zeroPrefix[l-1];
				int ones = onePrefix[r] - onePrefix[l-1];
				int len = r-l+1;
				int totalPairs = len*(len-1)/2;
				int zeroPairs = zeroes*(zeroes-1)/2;
				int zeroOnePairs = zeroes*ones;
				int ans = totalPairs - (zeroOnePairs+zeroPairs);
				System.out.println(ans);
			}
		}
    }		
}
