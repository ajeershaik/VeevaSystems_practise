import java.util.*;
class DFS{
	int n;
	boolean[] vis;
	ArrayList<ArrayList<Integer>> al= new ArrayList<>();
	public DFS(int n){
		this.n = n;
		vis = new boolean[n];
		for(int i=0;i<n;i++){
			al.add(new ArrayList<Integer>());
		}
	}
	public void addEdge(int u,int v){
		if(!al.get(u).contains(v))
			al.get(u).add(v);
		if(!al.get(v).contains(u))
			al.get(v).add(u);
	}
	
	public void display(){
		for(ArrayList<Integer> list : al){
			System.out.print(list);
			System.out.println();
		}
	}
	public void dfsTraversal(int start){
		Stack<Integer> st = new Stack<>();
		st.push(start);
		System.out.print(start+" --->");
		vis[start] = true;
		while(!st.empty()){
			ArrayList<Integer> list = al.get(start);
			for(int i:list){
				if(vis[i] == false){
					dfsTraversal(i);
					
				}
			}
			st.pop();
		}
	}
}

class DFSTraversal{
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		System.out.println("enter nodes");
		int n = sc.nextInt();
		DFS dfs = new DFS(n);
		outer:
		while(true){
			System.out.println("Enter u and v edges");
			int u = sc.nextInt();
			int v  = sc.nextInt();
			dfs.addEdge(u,v);
			System.out.println("Enter -1 to exit");
			int ch = sc.nextInt();
			if(ch == -1)
				break outer;
		}
		dfs.display();
		System.out.println("Enter start vertex");
		int start = sc.nextInt();
		dfs.dfsTraversal(start);
	}
}
	
	