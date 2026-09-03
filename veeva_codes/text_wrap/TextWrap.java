import java.util.*;
import java.io.*;
class TextWrap{
	public static void main(String args[])throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter the n amd m split()");
		StringTokenizer st1 = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st1.nextToken());
		int m = Integer.parseInt(st1.nextToken());
		int[] arr = new int[n];
		int max = 0;
		int sum = 0;
		System.out.println("Enter the array elements");
		StringTokenizer st2 = new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++){
			arr[i] = Integer.parseInt(st2.nextToken());
			sum = sum+arr[i];
			if(arr[i] > max){
				max = arr[i];
			}
		}
		for(int i=0;i<n;i++){
			System.out.println(arr[i]+" ");
		}
		System.out.println("max is: "+max+" sum is: "+(sum+n-1));
		int low = max;
		int high = sum+n-1;
		outer:
		while(low<=high){
			int mid = (low+high)/2;
			int max_lines = minWindow(arr,mid,n,m);
			if(max_lines<m){
				high = mid-1;
			}
			else{
				System.out.println("Min window is: "+mid);
				break;
			}
		}
	}
	public static int minWindow(int[] arr,int window,int n,int m){
		
		int curr_line = 1;
		int curr_width = arr[0];
		for(int i=1;i<n;i++){
			if(curr_width+1+arr[i] <= window){
				curr_width += 1+arr[i];
			}
			else{
				curr_line += 1;
				curr_width = arr[i];
			}
		}
		return curr_line;
	}
}	
	