import java.util.*;
class CountNumbers{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a String with numbers: ");
        String str = sc.nextLine();
        String st[] = str.split(",");
		ArrayList<Integer> al = new ArrayList<>();
		int c = 0;
		for(int i=0;i<str.length();i++){
			try{
				int k = Integer.parseInt(st[i]);
				if(k>=0 || k<0){
					c++;
					al.add(k);
				}
			}
			catch(Exception e){
				continue;
			}
		}
		System.out.println("string with number count is: "+c+"\n"+"With numbers "+al);
		
    }
}