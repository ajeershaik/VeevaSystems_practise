import java.util.*;
/*
Given an array of size n, find the majority element that appears more than (floor n/2) times.
Input: [1], 
Output: 1
 Input: [4, 4]
Output: 4
 Input: [3, 2, 3]
 Output: 3
Input: [2, 2, 1, 1, 2, 2], Output: 2
 Input: [7, 1, 7, 2, 7, 7, 3], Output: 7.
Input: [-5, -5, 2, -5]
Output:-5
 [0, 0, 8, 0, 0],
 Output: 0.
nput: [0, 0, 8, 0, 0], Output: 0.
*/
class Appear{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter n");
		int n = sc.nextInt();
		int[] arr = new int[n];
		int n1 = (int)Math.floor(n/2);
		HashMap<Integer,Integer> hm = new HashMap<>();
		System.out.println("n/2 is "+n1);
		System.out.println("Enter values");
		for(int i=0;i<n;i++){
			arr[i] = sc.nextInt();
			hm.put(arr[i],hm.getOrDefault(arr[i],0)+1);
		}
		System.out.println(hm);
		for(Map.Entry<Integer,Integer> e:hm.entrySet()){
			if(e.getValue()>n1){
				System.out.println(e.getKey());
			}
		}
	}
}
		