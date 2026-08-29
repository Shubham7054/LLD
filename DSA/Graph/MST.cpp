#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>

using namespace std;

// Disjoint Set Union (DSU) with Path Compression & Union by Rank
class DSU {
private:
    vector<int> parent;
    vector<int> rank;

public:
    DSU(int n) {
        parent.resize(n);
        rank.resize(n, 0);
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    int find(int u) {
        if (parent[u] == u)
            return u;
        return parent[u] = find(parent[u]); // Path compression
    }

    bool unite(int u, int v) {
        int rootU = find(u);
        int rootV = find(v);

        if (rootU != rootV) {
            // Union by rank
            if (rank[rootU] < rank[rootV]) {
                swap(rootU, rootV);
            }
            parent[rootV] = rootU;
            if (rank[rootU] == rank[rootV]) {
                rank[rootU]++;
            }
            return true; // Successfully united
        }
        return false; // Already in the same set (creates a cycle)
    }
};

class MST {
public:
    // 1. Kruskal's Algorithm: O(E log E)
    // Edge format: {u, v, weight}
    int kruskalsMST(int V, vector<vector<int>>& edges) {
        // Sort edges based on weight
        sort(edges.begin(), edges.end(), [](const vector<int>& a, const vector<int>& b) {
            return a[2] < b[2];
        });

        DSU dsu(V);
        int mstWeight = 0;
        int edgesCount = 0;

        for (const auto& edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int wt = edge[2];

            if (dsu.unite(u, v)) {
                mstWeight += wt;
                edgesCount++;
                if (edgesCount == V - 1) break; // MST complete
            }
        }

        return (edgesCount == V - 1) ? mstWeight : -1; // -1 if graph isn't connected
    }

    // 2. Prim's Algorithm: O(E log V)
    // Adjacency list format: adj[u] = {{neighbor, weight}, ...}
    int primsMST(int V, const vector<vector<pair<int, int>>>& adj) {
        // Min-heap storing {weight, node}
        priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq;
        vector<bool> visited(V, false);

        int mstWeight = 0;
        int nodesCount = 0;

        // Start from node 0 (weight = 0)
        pq.push({0, 0});

        while (!pq.empty()) {
            auto [wt, u] = pq.top();
            pq.pop();

            if (visited[u]) continue; // Skip already included nodes

            visited[u] = true;
            mstWeight += wt;
            nodesCount++;

            for (const auto& edge : adj[u]) {
                int neighbor = edge.first;
                int weight = edge.second;

                if (!visited[neighbor]) {
                    pq.push({weight, neighbor});
                }
            }
        }

        return (nodesCount == V) ? mstWeight : -1; // -1 if graph isn't connected
    }
};

int main() {
    int V = 4;

    // --- Graph Data for Kruskal's (Edge List) ---
    // Format: {u, v, weight}
    vector<vector<int>> edges = {
        {0, 1, 10},
        {0, 2, 6},
        {0, 3, 5},
        {1, 3, 15},
        {2, 3, 4}
    };

    // --- Graph Data for Prim's (Adjacency List) ---
    // Format: adj[u] = {{v, weight}, ...}
    vector<vector<pair<int, int>>> adj(V);
    adj[0].push_back({1, 10}); adj[1].push_back({0, 10});
    adj[0].push_back({2, 6});  adj[2].push_back({0, 6});
    adj[0].push_back({3, 5});  adj[3].push_back({0, 5});
    adj[1].push_back({3, 15}); adj[3].push_back({1, 15});
    adj[2].push_back({3, 4});  adj[3].push_back({2, 4});

    MST solver;

    cout << "Kruskal's MST Total Weight: " << solver.kruskalsMST(V, edges) << endl;
    cout << "Prim's MST Total Weight:    " << solver.primsMST(V, adj) << endl;

    return 0;
}
