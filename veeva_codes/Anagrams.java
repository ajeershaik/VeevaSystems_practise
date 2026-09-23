/*
Check if two strings are anagrams of each other
Input String 1 (s1)
Input String 2 (s2)
Expected Output
"listen"
"silent"
True
"anagram" 
"nagaram"
True
"a"
"a"
True
"geeksforgeeks"
"forgeeksgeeks"
True
*/
import java.util.*;
class Anagrams{
	/*
		SOlution one------
		
		public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter two Strings");
		String s1 = sc.next();
		String s2 = sc.next();
		ArrayList<Character> al1 = new ArrayList<>();
		ArrayList<Character> al2 = new ArrayList<>();
		for(char ch:s1.toCharArray())
			al1.add(ch);
		for(char ch:s2.toCharArray())
			al2.add(ch);
		System.out.println(al1);
		System.out.println(al2);
		al1.sort((a,b)->a.compareTo(b));
		al2.sort((a,b)->a.compareTo(b));
		System.out.println(al1);
		System.out.println(al2);
		for(char ch:s1.toCharArray())
			System.out.println(Collections.frequency(al1,ch));
		for(char ch:s2.toCharArray())
			System.out.println(Collections.frequency(al2,ch));
		boolean yes = isAnagram(al1,al2);
		if(yes)
			System.out.println("Anagram");
		else
			System.out.println("Not Anagram");
	}
	public static boolean isAnagram(ArrayList<Character> al1,ArrayList<Character> al2){
		if(al1.size() != al2.size())
			return false;
		int n = al1.size();
		for(int i=0;i<n;i++){
			if(!al1.get(i).equals(al2.get(i)) && Collections.frequency(al1,al1.get(i)) != Collections.frequency(al1,al2.get(i)))
				return false;
		}
		return true;
	}
	
	
	Optimized one------*/
	
	
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter two Strings:");
        String s1 = sc.next().toLowerCase(); // case-insensitive
        String s2 = sc.next().toLowerCase();

        if (isAnagram(s1, s2))
            System.out.println("Anagram");
        else
            System.out.println("Not Anagram");
    }

    public static boolean isAnagram(String s1, String s2) {
        if (s1.length() != s2.length()) return false;

        ArrayList<Character> al1 = new ArrayList<>();
        ArrayList<Character> al2 = new ArrayList<>();

        for (char c : s1.toCharArray()) al1.add(c);
        for (char c : s2.toCharArray()) al2.add(c);

        Collections.sort(al1);
        Collections.sort(al2);

        return al1.equals(al2);
    }
}