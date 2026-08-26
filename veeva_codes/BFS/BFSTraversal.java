import java.util.*;
class BFS{
	int n;
	ArrayList<ArrayList<Integer>> al= new ArrayList<>();
	public BFS(int n){
		this.n = n;
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
	public void bfsTraversal(int start){
		boolean[] vis = new boolean[n];
		Arrays.fill(vis,false);
		Queue<Integer> q = new LinkedList<>();
		q.add(start);
		vis[start] = true;
		while(!q.isEmpty()){
			int v = q.poll();
			System.out.print(v+"--> ");
			ArrayList<Integer> list = al.get(v);
			for(int i:list){
				if(vis[i] == false){
					q.add(i);
					vis[i] = true;
				}
			}
		}
	}
}

class BFSTraversal{
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		System.out.println("enter nodes");
		//BFSTraversal bfst = new BFSTraversal();
		int n = sc.nextInt();
		BFS bfs = new BFS(n);
		outer:
		while(true){
			System.out.println("Enter u and v edges");
			int u = sc.nextInt();
			int v  = sc.nextInt();
			bfs.addEdge(u,v);
			System.out.println("Enter -1 to exit");
			int ch = sc.nextInt();
			if(ch == -1)
				break outer;
		}
		bfs.display();
		System.out.println("Enter start vertex");
		int start = sc.nextInt();
		bfs.bfsTraversal(start);
	}
}
	
	