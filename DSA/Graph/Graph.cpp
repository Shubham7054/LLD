#include <bits/stdc++.h>
using namespace std;


//pair<int,int>
class Graph{
public:
    vector<int> Dijkastra(vector<vector<pair<int,int>>>&v, int src){
        priority_queue<pair<int,int>, vector<pair<int,int>>, greater<pair<int,int>>>pq;
        
        pq.push({0, src});
        vector<int>mn(v.size(), INT_MAX);
        mn[src] = 0;
        
        while(!pq.empty()){
            auto t = pq.top();
            pq.pop();
            
            int dist = t.first;
            int u = t.second;
            
            if (dist > mn[u]) continue;
            
            for (auto& edge : v[u]) {
                int neighbor = edge.first;
                int weight = edge.second;
                
                // Relaxation step
                if (mn[u] + weight < mn[neighbor]) {
                    mn[neighbor] = mn[u] + weight;
                    pq.push({mn[neighbor], neighbor});
                }
            }
        }
        return mn;
    }
    
    vector<int> bellmanFord(int V, vector<vector<int>>& edges, int src) {
    	vector<int> dist(V, 1e8);
    	dist[src] = 0;
      
    	for (int i = 0; i < V; i++) {
    	    
    		for (vector<int> edge : edges) {
    			int u = edge[0];
    			int v = edge[1];
    			int wt = edge[2];
    			if (dist[u] != 1e8 && dist[u] + wt < dist[v]) {
                    if(i == V - 1)
                        return {-1};
                   
                    dist[v] = dist[u] + wt;
                }
    		}
    	}
    
        return dist;
    }


    void floydWarshall(vector<vector<int>> &dist) {
        int V = dist.size();
        int INF = 1e8;
    
        // for each intermediate vertex
        for (int k = 0; k < V; k++) {
    
            // Pick all vertices as source one by one
            for (int i = 0; i < V; i++) {
    
                // Pick all vertices as destination
                // for the above picked source
                for (int j = 0; j < V; j++) {
    
                    // shortest path from i to j 
                    if(dist[i][k] != INF && dist[k][j]!= INF )
                        dist[i][j] = min(dist[i][j],
                                         dist[i][k] + dist[k][j]);
                }
            }
        }
    }
};

int main(){
  return 0;
}
