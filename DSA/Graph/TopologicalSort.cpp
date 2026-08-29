#include <iostream>
#include <vector>
#include <queue>
#include <stack>

using namespace std;

class Graph {
private:
    int V; // Number of vertices
    vector<vector<int>> adj; // Adjacency list

    // Helper function for DFS-based Topological Sort
    void dfsHelper(int u, vector<bool>& visited, stack<int>& st) {
        visited[u] = true;

        for (int v : adj[u]) {
            if (!visited[v]) {
                dfsHelper(v, visited, st);
            }
        }

        // Push node to stack after visiting all its neighbors
        st.push(u);
    }

public:
    Graph(int vertices) {
        V = vertices;
        adj.resize(V);
    }

    void addEdge(int u, int v) {
        adj[u].push_back(v); // Directed edge u -> v
    }

    // 1. Topological Sort using BFS (Kahn's Algorithm)
    vector<int> topoSortBFS() {
        vector<int> inDegree(V, 0);

        // Step 1: Calculate in-degrees of all nodes
        for (int u = 0; u < V; u++) {
            for (int v : adj[u]) {
                inDegree[v]++;
            }
        }

        // Step 2: Push all nodes with in-degree 0 into the queue
        queue<int> q;
        for (int i = 0; i < V; i++) {
            if (inDegree[i] == 0) {
                q.push(i);
            }
        }

        vector<int> topoOrder;

        // Step 3: Process nodes in BFS order
        while (!q.empty()) {
            int u = q.front();
            q.pop();
            topoOrder.push_back(u);

            // Reduce in-degree for neighboring nodes
            for (int v : adj[u]) {
                inDegree[v]--;
                if (inDegree[v] == 0) {
                    q.push(v);
                }
            }
        }

        // Cycle check: If topological order size != V, graph contains a cycle
        if (topoOrder.size() != V) {
            cout << "Graph contains a cycle! Topological sort not possible.\n";
            return {};
        }

        return topoOrder;
    }

    // 2. Topological Sort using DFS
    vector<int> topoSortDFS() {
        vector<bool> visited(V, false);
        stack<int> st;

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                dfsHelper(i, visited, st);
            }
        }

        // Extract elements from stack to get the topological ordering
        vector<int> topoOrder;
        while (!st.empty()) {
            topoOrder.push_back(st.top());
            st.pop();
        }

        return topoOrder;
    }
};

int main() {
    // Graph setup: 6 vertices (0 to 5)
    Graph g(6);
    g.addEdge(5, 2);
    g.addEdge(5, 0);
    g.addEdge(4, 0);
    g.addEdge(4, 1);
    g.addEdge(2, 3);
    g.addEdge(3, 1);

    cout << "Topological Sort (BFS / Kahn's Algorithm): ";
    vector<int> bfsResult = g.topoSortBFS();
    for (int node : bfsResult) {
        cout << node << " ";
    }
    cout << "\n";

    cout << "Topological Sort (DFS Algorithm):         ";
    vector<int> dfsResult = g.topoSortDFS();
    for (int node : dfsResult) {
        cout << node << " ";
    }
    cout << "\n";

    return 0;
}
