#include <bits/stdc++.h>
using namespace std;

int segment[4*10005];
int lazy[4*100005];

void makeSegment(int idx, int low, int high){
    if(low == high){
        segment[idx] = a[low];
        return;
    }
    int mid = (low+high)/2;
    makeSegment(2*idx+1, low, mid);
    makeSegment(2*idx+2, mid+1,high);
    segment[idx] = segment[2*idx+1] + segment[2*idx+2];
}

int rangeQuery(int idx, int low, int high, int l, int r){
    if(l >= low && r <= high){
        return segment[idx];
    }
    if(high < l || low > r) return 0;
    
    int mid = (low+high)/2;
    int left = rangeQuery(2*idx+1, low, mid, l, r);
    int right = rangeQuery(2*idx+2, mid+1, high, l, r);
    return left + right;
}

void rangeUpdate(int idx, int low, int high, int l, int r, int val){
    if(lazy[idx]){
        segment[idx]+= (high-low+1)*lazy[idx];
        if(low!=high){
            lazy[2*idx+1] += lazy[idx];
            lazy[2*idx+2] += lazy[idx];
        }
        lazy[idx]=0;
    }
    if(r<low || l>high) return;
    if (l <= low && high <= r) {
        segment[idx] += (long long)(high - low + 1) * val;
        if (low != high) {
          lazy[2*idx+1] += val;
          lazy[2*idx+2] += val;
        }
        return;
    }
    
    int mid = (low+high)/2;
    rangeUpdate(2*idx+1, low, mid, l,r, val);
    rangeUpdate(2*idx+2, mid+1, high, l, r, val);
    
    segment[idx] = segment[2*idx+1] + segment[2*idx+2];
}

long long rangeQueryLazy(int idx, int low, int high, int l, int r){
    if (r < low || l > high) return 0;
    
    if (lazy[idx]) {
      segment[idx] += (long long)(high - low + 1) * lazy[idx];
      if (low != high) {
          lazy[2*idx+1] += lazy[idx];
          lazy[2*idx+2] += lazy[idx];
      }
      lazy[idx] = 0;
    }
    
    if (l <= low && high <= r) return segment[idx];
    
    int mid = (low + high) / 2;
    return rangeQuery(2*idx+1, low, mid,  l, r)
       + rangeQuery(2*idx+2, mid+1, high, l, r);
}

int main() {
	// your code goes here
	int n;
	cin>>n;
	int a[n];
	for(int i=0;i<n;i++)
	cin>>a[i];
	
	makeSegment(0,0,n-1,0,n-1);
	
	return 0;
}
