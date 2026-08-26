# DFS Graph Traversal in Java

## Overview
This program implements **Depth-First Search (DFS)** traversal on an undirected graph using an adjacency list. DFS explores as far as possible along each branch before backtracking, using **recursion + a Stack** internally.

---

## Project Structure

```
DFSTraversal.java
├── class DFS                  → Core graph logic
│   ├── addEdge(u, v)          → Add undirected edge
│   ├── display()              → Print adjacency list
│   └── dfsTraversal(start)    → Recursive DFS from start node
└── class DFSTraversal         → Main: user input & execution
```

---

## How It Works

### Classes

#### `DFS`
Core graph class that stores the graph as an adjacency list.

| Field / Method | Description |
|---|---|
| `int n` | Number of nodes |
| `boolean[] vis` | Visited array — shared across recursive calls |
| `ArrayList<ArrayList<Integer>> al` | Adjacency list |
| `DFS(int n)` | Constructor — initializes n empty lists + vis array |
| `addEdge(u, v)` | Adds undirected edge between u and v |
| `display()` | Prints the adjacency list |
| `dfsTraversal(start)` | Recursive DFS from the given start node |

#### `DFSTraversal`
Main class — handles user input via `Scanner` and calls DFS methods.

---

## Algorithm — Step by Step

```
1. Push start node to Stack, mark visited, print it
2. Get adjacency list of current node
3. For each unvisited neighbor:
   a. Recursively call dfsTraversal(neighbor)
      → This goes DEEP before coming back
4. Pop from Stack when no unvisited neighbors remain
5. Repeat until Stack is empty
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

## DFS Traversal — Visual Walkthrough

Starting from **Node 0**:

```
Call dfsTraversal(0)
  Print: 0, mark visited
  Neighbors: [1, 3]
  → 1 not visited → recurse

  Call dfsTraversal(1)
    Print: 1, mark visited
    Neighbors: [0, 2, 4]
    → 0 visited → skip
    → 2 not visited → recurse

    Call dfsTraversal(2)
      Print: 2, mark visited
      Neighbors: [1, 5]
      → 1 visited → skip
      → 5 not visited → recurse

      Call dfsTraversal(5)
        Print: 5, mark visited
        Neighbors: [2]
        → 2 visited → skip
        Stack pop → return

    → 4 not visited → recurse

    Call dfsTraversal(4)
      Print: 4, mark visited
      Neighbors: [1, 3]
      → 1 visited → skip
      → 3 not visited → recurse

      Call dfsTraversal(3)
        Print: 3, mark visited
        Neighbors: [0, 4]
        → 0 visited → skip
        → 4 visited → skip
        Stack pop → return

  Stack pop → return all the way

Final Order: 0 → 1 → 2 → 5 → 4 → 3
```

---

## DFS vs BFS — Traversal Path Comparison

Same graph, different results:

```
Graph:
  0 ——— 1 ——— 2
  |     |     |
  3 ——— 4     5

BFS (level by level):   0 → 1 → 3 → 2 → 4 → 5
DFS (deep path first):  0 → 1 → 2 → 5 → 4 → 3
```

```
BFS explores:           DFS explores:
  Level 0: [0]            Branch: 0→1→2→5
  Level 1: [1, 3]         Backtrack, branch: →4→3
  Level 2: [2, 4]
  Level 3: [5]
```

---

## Recursion Call Stack — Visual

```
main()
 └─ dfsTraversal(0)        ← push 0
     └─ dfsTraversal(1)    ← push 1
         └─ dfsTraversal(2)← push 2
             └─ dfsTraversal(5) ← push 5, pop 5
         └─ dfsTraversal(4)← push 4
             └─ dfsTraversal(3) ← push 3, pop 3
             pop 4
         pop 2
     pop 1
 pop 0
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

DFS Output:
0 ---> 1 ---> 2 ---> 5 ---> 4 ---> 3 --->
```

---

## Source Code

```java
import java.util.*;

class DFS {
    int n;
    boolean[] vis;
    ArrayList<ArrayList<Integer>> al = new ArrayList<>();

    public DFS(int n) {
        this.n = n;
        vis = new boolean[n];
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

    public void dfsTraversal(int start) {
        Stack<Integer> st = new Stack<>();
        st.push(start);
        System.out.print(start + " --->");
        vis[start] = true;
        while (!st.empty()) {
            ArrayList<Integer> list = al.get(start);
            for (int i : list) {
                if (vis[i] == false) {
                    dfsTraversal(i);
                }
            }
            st.pop();
        }
    }
}

class DFSTraversal {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("enter nodes");
        int n = sc.nextInt();
        DFS dfs = new DFS(n);
        outer:
        while (true) {
            System.out.println("Enter u and v edges");
            int u = sc.nextInt();
            int v = sc.nextInt();
            dfs.addEdge(u, v);
            System.out.println("Enter -1 to exit");
            int ch = sc.nextInt();
            if (ch == -1) break outer;
        }
        dfs.display();
        System.out.println("Enter start vertex");
        int start = sc.nextInt();
        dfs.dfsTraversal(start);
    }
}
```

---

## Key Concepts

| Concept | Detail |
|---|---|
| Data structure | `ArrayList<ArrayList<Integer>>` for adjacency list |
| Traversal mechanism | Recursive calls + `Stack<Integer>` |
| Visited tracking | `boolean[] vis` — class-level, shared across recursion |
| Graph type | Undirected (edges added both ways) |
| Time complexity | O(V + E) — V nodes, E edges |
| Space complexity | O(V) for visited array + recursion call stack |

---

## BFS vs DFS — Full Comparison

| Feature | BFS | DFS |
|---|---|---|
| Data structure | Queue (FIFO) | Stack / Recursion |
| Traversal order | Level by level | Deep path first |
| Shortest path | ✅ Yes (unweighted) | ❌ No |
| Memory usage | More (wide graphs) | Less (deep graphs) |
| Implementation | Iterative (Queue) | Recursive / Iterative (Stack) |
| Use case | Shortest path, networking | Topological sort, cycle detection |
| Risk | None | Stack overflow on very deep graphs |

---

## How to Compile and Run

```bash
# Compile
javac DFSTraversal.java

# Run
java DFSTraversal
```

### Requirements
- Java JDK 8 or above

---

*Generated for TalentNext 2026 — SE Learning Phase*
