/*Find two numbers in an array that add up to a specific target value.
Input: 
nums = [2, 7, 11, 15], target = 9
Output: [0, 1] 
(since 2 + 7 = 9) 
 Input: nums = [3, 2, 4], target = 6
Expected Output: [1, 2] (since 2 + 4 = 6)
 Input: nums = [3, 3], target = 6
Output: [0, 1]
Input: 
nums = [5, 10], target = 15
Output: [0, 1] 
Input: nums = [-3, 4, 3, 90], target = 0
Output: [0, 2]
Input: nums = [-1, -2, -3, -4, -5], target = -8
Output: [2, 4] (since -3 + -5 = -8)
Input: nums = [0, 4, 3, 0], target = 0
Output: [0, 3]*/
import java.util.*;
class TwoSum{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter n");
		int n = sc.nextInt();
		int[] arr = new int[n];
		System.out.println("Enter values");
		for(int i=0;i<n;i++){
			arr[i] = sc.nextInt();
		}
		System.out.println("Enter target");
		int tar = sc.nextInt();
		int[] res = new int[1];
		res = twoSum(arr,tar);
		System.out.println(Arrays.toString(res));
		
	}
	public static int[] twoSum(int[] arr,int tar){
		HashMap<Integer,Integer> hm = new HashMap<>();
		for(int i=0;i<arr.length;i++){
			if(hm.containsKey(tar-arr[i]))
				return new int[]{hm.get(tar-arr[i]),i};
			
			hm.put(arr[i],i);
		}
		return new int[] {-1,-1};
	}
}