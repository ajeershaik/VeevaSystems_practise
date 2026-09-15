
import java.util.*;
public class DecreasingFrequency
{
	public static void main(String[] args) {
	    Scanner sc = new Scanner(System.in);
	    System.out.println("enter n");
		int n = sc.nextInt();
		int[] arr = new int[n];
		System.out.println("enter elements into array");
		for(int i=0;i<n;i++){
		    arr[i] = sc.nextInt();
		}
		TreeMap<Integer,Integer> tm = new TreeMap<>();
		for(int i=0;i<n;i++){
		    tm.put(arr[i],tm.getOrDefault(arr[i],0)+1);
		}
		LinkedHashMap<Integer,Integer> tm1 = tm.entrySet().
		                                     stream().
		                                     sorted(Map.Entry.<Integer,Integer>comparingByValue().reversed()).
		                                     collect(
		                                         LinkedHashMap::new,
		                                         (map,entry)->map.put(entry.getKey(),entry.getValue()),
		                                         LinkedHashMap::putAll
		                                         );
		                                         
		 for(Map.Entry<Integer,Integer> e:tm1.entrySet()){
		     int value = e.getValue();
		     int key = e.getKey();
		     for(int i=0;i<value;i++){
		         System.out.print(key+" ");
		     }
		 }
	
	}
}