import java.util.*;
import java.time.LocalDateTime;
import java.time.Duration;
import java.io.*;

class Document{
	String doc_id;
	String user_id;
	LocalDateTime timestamp;
	public Document(String doc_id,String user_id,LocalDateTime timestamp){
		this.doc_id = doc_id;
		this.user_id = user_id;
		this.timestamp = timestamp;
	}
}

class LockManagement{
	static HashMap<String,List<Document>> hm = new HashMap<>();
	public static void main(String args[])throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		LockManagement lm = new LockManagement();
		outer:
		while(true){
			System.out.println("enter choices below");
			System.out.println("enter 0 to exit");
			System.out.println("enter 1 to request lock");
			System.out.println("enter 2 to release lock");
			System.out.println("enter 3 to expire all locks");
			System.out.println("enetr 4 to display()");
			int ch = Integer.parseInt(br.readLine());
			switch(ch){
				case 0:	System.out.println("bye..!");
						break outer;
				case 1:	System.out.println("enter doc_id and user_id");
						String doc_id = br.readLine();
						String user_id = br.readLine();
						//Document d = new Document(doc_id,user_id);
						lm.requestLock(new Document(doc_id,user_id,LocalDateTime.now()));
						break;
				case 2:	System.out.println("Enter doc_id to release lock");
						String doc_id1 = br.readLine();
						lm.releaseLock(doc_id1);
						break;
				case 3:
						System.out.println("enter duration");
						int duration = Integer.parseInt(br.readLine());
						lm.clearExpLocks(LocalDateTime.now(),duration);
						break;
				case 4: lm.display();
						break;
				default:System.out.println("try to enter within the range");
			}
				
		}
	}
	public void clearExpLocks(LocalDateTime timestamp,int dur){
		Iterator<Map.Entry<String,List<Document>>> it = hm.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry<String,List<Document>> entry = it.next();
			List<Document> list = entry.getValue();
			for(Document l : list){
				long diff = Duration.between(l.timestamp,timestamp).getSeconds();
				if(diff>=dur){
					it.remove();
					System.out.println("Lock expired and removed successfully");
				}
			}
		}
		
	}
	public void releaseLock(String doc_id){
		if(hm.containsKey(doc_id)){
			hm.remove(doc_id);
			System.out.println("removed succesfully");
		}
		else{
			System.out.println("No document exists");
		}
	}
	public static void requestLock(Document d){
		if(hm.containsKey(d.doc_id)){
			System.out.println("document is accessed by someone else");
		}
		else{
			hm.put(d.doc_id,new ArrayList<>());
			List<Document> list = new ArrayList<>();
			list.add(d);
			hm.put(d.doc_id,list);
			System.out.println("Request allocated succesfully");
		}
		//System.out.println(hm);
	}
	
	public static void display(){
		if(hm.size() <= 0){
			System.out.println("No active Locks");
		}
		else{
			System.out.println("Doc_id"+"\t"+"user_id"+"\t"+"TimeStamp");
			for(Map.Entry<String,List<Document>> e:hm.entrySet()){
				String doc_id = e.getKey();
				//System.out.print("DOcumentId: "+doc_id+"\t");
				List<Document> list = e.getValue();
				for(Document d : list){
					System.out.print(d.doc_id+"\t"+d.user_id+"\t"+d.timestamp);
				}
				System.out.println();
			}
		}
	}
}