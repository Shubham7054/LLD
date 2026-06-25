#include <bits/stdc++.h>
using namespace std;

const int MAXN = 100005;
long long segment[4*MAXN];
long long lazy[4*MAXN];
int a[MAXN];

void makeSegment(int idx, int low, int high){
    if (low == high) { segment[idx] = a[low]; return; }
    int mid = (low + high) / 2;
    makeSegment(2*idx+1, low, mid);
    makeSegment(2*idx+2, mid+1, high);
    segment[idx] = segment[2*idx+1] + segment[2*idx+2];
}
    
void rangeUpdate(int idx, int low, int high, int l, int r, long long val){
    if (lazy[idx]) {
        segment[idx] += (long long)(high - low + 1) * lazy[idx];
        if (low != high) {
            lazy[2*idx+1] += lazy[idx];
            lazy[2*idx+2] += lazy[idx];
        }
        lazy[idx] = 0;
    }
    if (r < low || l > high) return;
    if (l <= low && high <= r) {
        segment[idx] += (long long)(high - low + 1) * val;
        if (low != high) {
            lazy[2*idx+1] += val;
            lazy[2*idx+2] += val;
        }
        return;
    }
    int mid = (low + high) / 2;
    rangeUpdate(2*idx+1, low, mid,  l, r, val);
    rangeUpdate(2*idx+2, mid+1, high, l, r, val);
    segment[idx] = segment[2*idx+1] + segment[2*idx+2];
}

long long rangeQuery(int idx, int low, int high, int l, int r){
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
    return rangeQuery(2*idx+1, low, mid,  l, r)+ rangeQuery(2*idx+2, mid+1, high, l, r);
}

int main(){
    int n; cin >> n;
    for (int i = 0; i < n; i++) cin >> a[i];
    makeSegment(0, 0, n-1);
    // example:
    // rangeUpdate(0, 0, n-1, l, r, val);
    // cout << rangeQuery(0, 0, n-1, l, r);
    return 0;
}
