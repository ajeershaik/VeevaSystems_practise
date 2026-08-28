//Given an unsorted array of integers, find all pairs of elements that have the minimum absolute difference between them.
/*
Input: [6, 1, 15, 10]
Expected: [[6, 10]]
Min Difference: 4

Input: [4, 2, 1, 3]
Expected: [[1, 2], [2, 3], [3, 4]]
Min Difference: 1

Input: [-5, 2, -1, 7]
Expected: [[-5, -1]]
Min Difference: 4

Input: [-10, -2, -5, -7]
Expected: [[-7, -5]]
Min Difference: 2

Input: [3, 8, 3, 12]
Expected: [[3, 3]]
Min Difference: 0

Input: [5, 1, 5, 1]
Expected: [[1, 1], [5, 5]]
Min Difference: 0
*/
import java.io.*;
import java.util.*;
class MinDifference{
	public static void main(String args[])throws Exception{
		MinDifference md = new MinDifference();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("enter no.of elements");
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		System.out.println("enter elements into array .split()");
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++){
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		int min = min(arr,n);
		System.out.println("Minimum diff is: "+min);
		ArrayList<ArrayList<Integer>> pairs = md.minDiffPairs(arr,min,n);
		for(ArrayList<Integer> list :pairs){
			System.out.println(list);
		}
	}
	public static ArrayList<ArrayList<Integer>> minDiffPairs(int[] arr,int min,int n){
		ArrayList<ArrayList<Integer>> list = new ArrayList<>();
		int left = 0;
		int right = 1;
		while(right<n){
			if(Math.abs(arr[left]-arr[right]) == min){
				ArrayList<Integer> al = new ArrayList<>();
				al.add(arr[left]);
				al.add(arr[right]);
				list.add(al);
			}
			right++;
			left++;
		}
		return list;
	}
	public static int min(int[] arr,int n){
		int min = 9999999;
		for(int i=0;i<n-1;i++){
			min = Math.min(min,Math.abs(arr[i]-arr[i+1]));
		}
		return min;
	}
}

