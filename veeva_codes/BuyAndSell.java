import java.util.*;
class BuyAndSell{
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		ArrayList<Integer> al = new ArrayList<>();
		System.out.println("Enter n");
		int n = sc.nextInt();
		int[] arr = new int[n];
		System.out.println("Enter elements into array");
		for(int i=0;i<n;i++){
			arr[i] = sc.nextInt();
		}
		System.out.println("The best profit is :"+ maxProfit(arr));
	}

	public static int maxProfit(int[] prices) {
       // Code here
		int profit = 0;
		int stock = prices[0];
        int n = prices.length;
        for(int i=0;i<n;i++){
            if(prices[i]>stock){
                profit = Math.max(profit,prices[i]-stock);
            }
            else if(prices[i]<stock){
                stock = prices[i];
            }
            else 
                continue;
        }
        return profit;
    }
}