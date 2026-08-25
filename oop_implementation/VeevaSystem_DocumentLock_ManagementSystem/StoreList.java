import java.io.*;
import java.util.*;
class Products{
	int id;
	String product;
	String product_company;
	double price;
	
	public Products(int id,String product_company,double price){
		this.id = id;
		this.product_company = product_company;
		this.price = price;
	}
	
	public Products(String product){
		this.product = product;
	}
}

class StoreList{
	static HashMap<String,ArrayList<Products>> hm = new HashMap<>();
	public static void main(String args[])throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StoreList sl = new StoreList();
		outer:
		while(true){
			System.out.println("enter choices below");
			System.out.println("enter 1 to insert");
			System.out.println("enter 2 to display");
			System.out.println("enter 3 to display Category");
			System.out.println("enter 4 to display category through price wise in asc order");
			System.out.println("enter 5 to display category through price wise in desc order");
			int ch = Integer.parseInt(br.readLine());
			switch(ch){
				case 1:
							System.out.println("enter id,product,product company. and price");
							StringTokenizer st = new StringTokenizer(br.readLine());
							int id = Integer.parseInt(st.nextToken());
							String product = st.nextToken();
							String product_company = st.nextToken();
							double price = Double.parseDouble(st.nextToken());
							sl.insertRecord(new Products(product),new Products(id,product_company,price));
							break;
				case 2:
						sl.display();
						break;
				case 3:
						System.out.println("enter a category");
						String cat = br.readLine().trim();
						sl.displayCategory(cat);
						break;
				case 4:
						System.out.println("enter a category");
						String cat1 = br.readLine().trim();
						sl.displayByPriceAsc(cat1);
				case 5:
						System.out.println("enter a category");
						String cat2 = br.readLine().trim();
						sl.displayByPriceDesc(cat2);
				default:System.out.println("invalid choice");
						break outer;
			}
		}
		
	}
	
	public static void insertRecord(Products category,Products productions){
		ArrayList<Products> list = hm.getOrDefault(category.product,new ArrayList<>());
		list.add(productions);
		hm.put(category.product,list);
	}
	
	public static void display() {

		for (Map.Entry<String, ArrayList<Products>> entry : hm.entrySet()) {

			String category = entry.getKey();

			ArrayList<Products> products = entry.getValue();

			System.out.println("\nCategory: " + category);

			for (Products p : products) {

				System.out.println(
					"ID: " + p.id +
					", Company: " + p.product_company +
					", Price: " + p.price
				);
			}
		}
	}
	
	public void displayCategory(String category){
		if(!hm.containsKey(category)){
			System.out.println("No such product category");
		}
		else{
			ArrayList<Products> pro = hm.get(category);
			for (Products p : pro) {

				System.out.println(
					"ID: " + p.id +
					", Company: " + p.product_company +
					", Price: " + p.price
				);
			}	
		}
	}
	
	public void displayByPriceAsc(String category){
		if(!hm.containsKey(category)){
			System.out.println("No such product category");
		}
		else{
			ArrayList<Products> pro = hm.get(category);
			pro.sort(Comparator.comparingDouble(p->p.price));
			for (Products p : pro) {

				System.out.println(
					"ID: " + p.id +
					", Company: " + p.product_company +
					", Price: " + p.price
				);
			}
		}
	}
	
	public void displayByPriceDesc(String category){
		if(!hm.containsKey(category)){
			System.out.println("No such product category");
		}
		else{
			ArrayList<Products> pro = hm.get(category);
			pro.sort(Comparator.comparingDouble((Products p)->p.price).reversed());
			for (Products p : pro) {

				System.out.println(
					"ID: " + p.id +
					", Company: " + p.product_company +
					", Price: " + p.price
				);
			}
		}
	}
}