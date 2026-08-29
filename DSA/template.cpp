#include <bits/stdc++.h>
using namespace std;

class DSU{
private:
    vector<int>parent, size;
public:
    DSU(int n){
        parent.resize(n + 1);
        size.resize(n + 1);
        
        for(int i = 0; i < n + 1; i++){
            parent[i] = i;
            size[i] = 1;
        }
    }
    
    int findParent(int u){
        if(u == parent[u])
        return u;
        return parent[u] = findParent(parent[u]);
    }
    
    void unionFind(int u, int v){
        int parent_u = findParent(u);
        int parent_v = findParent(v);
        
        if(parent_u == parent_v) 
        return;
        
        if(size[parent_u] > size[parent_v]){
            parent[parent_v] = parent_u;
            size[parent_u] += size[parent_v];
        }
        else{
            parent[parent_u] = parent_v;
            size[parent_v] += size[parent_u];
        }
    }
};

void nextGreaterElement(vector<int>v){
    stack<int>st;
    
    int n = v.size();
    vector<int>nge(n, -1);
    
    for(int i = 0; i < n; i++){
        while(!st.empty() && v[i] > v[st.top()]){
            nge[st.top()] = v[i];
            st.pop();
        }
        st.push(i);
    }
}

class BIT{
private:
    vector<int>tree;
    
public:
    BIT(int n){
        tree.resize(n + 1, 0);
    }
    
    int query(int n){
        int sum = 0;
        while(n){
            sum += tree[n];
            n -= (n & -n);
        }
        return sum;
    }
    
    void update(int n, int val){
        while(n < tree.size()){
            tree[n] += val;
            n += (n & -n);
        }
    }
};

class SegmentTree{
private:
    vector<int>segTree;

public:
    SegmentTree(int n){
        segTree.resize(4*n);
    }
    
    void build(int node, int start, int end, const vector<int>& arr) {
        if (start == end) {
            // Leaf node: stores the array element
            segTree[node] = arr[start];
            return;
        }

        int mid = start + (end - start) / 2;
        // Build left child (2 * node) and right child (2 * node + 1)
        build(2 * node, start, mid, arr);
        build(2 * node + 1, mid + 1, end, arr);

        // Internal node stores the sum of its children
        segTree[node] = segTree[2 * node] + segTree[2 * node + 1];
    }
    
    int query(int node, int l, int r, int low, int high, vector<int>&arr){
        if(low > r) return 0;
        if(high < l) return 0;
        
        if(r <= high && l >= low) return segTree[node];
        
        int mid = low + (high - low) / 2;
        int leftSum = query(2 * node, low, mid, l, r, arr);
        int rightSum = query(2 * node + 1, mid + 1, high, l, r, arr);

        return leftSum + rightSum;
    }
    
    void update(int node, int start, int end, int idx, int val) {
        if (start == end) {
            // Found the leaf node corresponding to idx
            segTree[node] = val;
            return;
        }

        int mid = start + (end - start) / 2;
        if (idx <= mid) {
            update(2 * node, start, mid, idx, val);
        } else {
            update(2 * node + 1, mid + 1, end, idx, val);
        }

        // Recalculate parent value after child modification
        segTree[node] = segTree[2 * node] + segTree[2 * node + 1];
    }
};

int main() {
	// your code goes here

}
