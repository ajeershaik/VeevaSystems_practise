import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

public class SubArrays {

    public static void main(String[] args) throws Exception {

        BufferedReader br =
                new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Enter n");
        int n = Integer.parseInt(br.readLine());

        System.out.println("Enter k");
        int k = Integer.parseInt(br.readLine());

        System.out.println("Enter n elements");

        int[] arr = new int[n];

        System.out.println("Enter the array elements");

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(
                "Total sub arrays are: " + numSubArrays(arr, n, k)
        );
    }


    public static int numSubArrays(int[] arr, int n, int k) {

        HashMap<Integer, Integer> hm = new HashMap<>();

        int count = 0;
        int sum = 0;

        hm.put(0, 1);

        for (int i = 0; i < n; i++) {

            sum = sum + arr[i];

            if (hm.containsKey(sum - k)) {
                count += hm.get(sum - k);
            }

            hm.put(sum, hm.getOrDefault(sum, 0) + 1);
        }

        return count;
    }
}