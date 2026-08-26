# BFS Graph Traversal in Java

## Overview
This program implements **Breadth-First Search (BFS)** traversal on an undirected graph using an adjacency list representation. BFS explores nodes level by level, starting from a given source node, using a queue to track which nodes to visit next.

---

## Project Structure

```
BFSTraversal.java
├── class BFS              → Core graph logic
│   ├── addEdge(u, v)      → Add undirected edge
│   ├── display()          → Print adjacency list
│   └── bfsTraversal(start)→ BFS from start node
└── class BFSTraversal     → Main: user input & execution
```

---

## How It Works

### Classes

#### `BFS`
Core graph class that stores the graph as an adjacency list.

| Field / Method | Description |
|---|---|
| `int n` | Number of nodes |
| `ArrayList<ArrayList<Integer>> al` | Adjacency list |
| `BFS(int n)` | Constructor — initializes n empty lists |
| `addEdge(u, v)` | Adds undirected edge between u and v |
| `display()` | Prints the adjacency list |
| `bfsTraversal(start)` | Runs BFS from the given start node |

#### `BFSTraversal`
Main class — handles user input via `Scanner` and calls BFS methods.

---

## Algorithm — Step by Step

```
1. Mark start node as visited, add to Queue
2. While Queue is not empty:
   a. Dequeue front node → print it
   b. For each unvisited neighbor:
      - Mark as visited
      - Enqueue the neighbor
3. Repeat until Queue is empty
```

---

## Graph Structure Diagram

```
        0 ——— 1 ——— 2
        |     |     |
        3 ——— 4     5

Nodes : 0, 1, 2, 3, 4, 5
Edges : 0-1, 0-3, 1-2, 1-4, 2-5, 3-4
```

---

## Adjacency List Representation

```
Node 0 → [1, 3]
Node 1 → [0, 2, 4]
Node 2 → [1, 5]
Node 3 → [0, 4]
Node 4 → [1, 3]
Node 5 → [2]
```

Each node stores a list of its direct neighbors. Since the graph is **undirected**, every edge (u → v) is also stored as (v → u).

---

## BFS Traversal — Visual Walkthrough

Starting from **Node 0**:

```
Step 1:  Queue: [0]          Visited: {0}
         Dequeue 0  → Print: 0
         Enqueue neighbors 1, 3

Step 2:  Queue: [1, 3]       Visited: {0, 1, 3}
         Dequeue 1  → Print: 1
         Enqueue unvisited neighbors: 2, 4

Step 3:  Queue: [3, 2, 4]    Visited: {0, 1, 3, 2, 4}
         Dequeue 3  → Print: 3
         Neighbors 0 (visited), 4 (visited) → skip

Step 4:  Queue: [2, 4]       Visited: {0, 1, 3, 2, 4}
         Dequeue 2  → Print: 2
         Enqueue unvisited neighbor: 5

Step 5:  Queue: [4, 5]       Visited: {0, 1, 3, 2, 4, 5}
         Dequeue 4  → Print: 4
         Neighbors 1, 3 (all visited) → skip

Step 6:  Queue: [5]
         Dequeue 5  → Print: 5
         Neighbor 2 (visited) → skip

Step 7:  Queue: []  → DONE

Final Order: 0 → 1 → 3 → 2 → 4 → 5
```

---

## Sample Input / Output

```
enter nodes
6
Enter u and v edges
0 1
Enter -1 to exit
0
Enter u and v edges
0 3
Enter -1 to exit
0
Enter u and v edges
1 2
Enter -1 to exit
0
Enter u and v edges
1 4
Enter -1 to exit
0
Enter u and v edges
2 5
Enter -1 to exit
0
Enter u and v edges
3 4
Enter -1 to exit
-1

Adjacency List:
[1, 3]
[0, 2, 4]
[1, 5]
[0, 4]
[1, 3]
[2]

Enter start vertex
0

BFS Output:
0--> 1--> 3--> 2--> 4--> 5-->
```

---

## Source Code

```java
import java.util.*;

class BFS {
    int n;
    ArrayList<ArrayList<Integer>> al = new ArrayList<>();

    public BFS(int n) {
        this.n = n;
        for (int i = 0; i < n; i++) {
            al.add(new ArrayList<Integer>());
        }
    }

    public void addEdge(int u, int v) {
        if (!al.get(u).contains(v)) al.get(u).add(v);
        if (!al.get(v).contains(u)) al.get(v).add(u);
    }

    public void display() {
        for (ArrayList<Integer> list : al) {
            System.out.print(list);
            System.out.println();
        }
    }

    public void bfsTraversal(int start) {
        boolean[] vis = new boolean[n];
        Arrays.fill(vis, false);
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        vis[start] = true;
        while (!q.isEmpty()) {
            int v = q.poll();
            System.out.print(v + "--> ");
            ArrayList<Integer> list = al.get(v);
            for (int i : list) {
                if (vis[i] == false) {
                    q.add(i);
                    vis[i] = true;
                }
            }
        }
    }
}

class BFSTraversal {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("enter nodes");
        int n = sc.nextInt();
        BFS bfs = new BFS(n);
        outer:
        while (true) {
            System.out.println("Enter u and v edges");
            int u = sc.nextInt();
            int v = sc.nextInt();
            bfs.addEdge(u, v);
            System.out.println("Enter -1 to exit");
            int ch = sc.nextInt();
            if (ch == -1) break outer;
        }
        bfs.display();
        System.out.println("Enter start vertex");
        int start = sc.nextInt();
        bfs.bfsTraversal(start);
    }
}
```

---

## Key Concepts

| Concept | Detail |
|---|---|
| Data structure | `ArrayList<ArrayList<Integer>>` for adjacency list |
| Traversal queue | `Queue<Integer>` via `LinkedList` |
| Visited tracking | `boolean[] vis` array |
| Graph type | Undirected (edges added both ways) |
| Time complexity | O(V + E) — V nodes, E edges |
| Space complexity | O(V) for visited array + queue |

---

## How to Compile and Run

```bash
# Compile
javac BFSTraversal.java

# Run
java BFSTraversal
```

### Requirements
- Java JDK 8 or above

---

## BFS vs DFS — Quick Comparison

| Feature | BFS | DFS |
|---|---|---|
| Data structure | Queue (FIFO) | Stack / Recursion |
| Traversal order | Level by level | Deep path first |
| Shortest path | ✅ Yes (unweighted) | ❌ No |
| Memory | More (wide graphs) | Less (deep graphs) |
| Use case | Shortest path, networking | Topological sort, cycles |

---

*Generated for TalentNext 2026 — SE Learning Phase*
