#include <bits/stdc++.h>
using namespace std;

class TrieNode{
private:
    TrieNode* nxt[26];
    bool isEnd = false;

public:
    TrieNode(){
        for(int i = 0; i < 26; i++){
            nxt[i] = NULL;
        }
        isEnd = false;
    }
    
    void add(char a, TrieNode* t){
        nxt[(a - 'a')] = t;
    }
    
    void end(){
        isEnd = true;
    }
    
    TrieNode* next(char a){
        return nxt[a - 'a'];
    }
    
    bool getEnd() {
        return isEnd;
    }
};

class Trie{
private:
    TrieNode* root;
    
public:
    Trie(){
        root = new TrieNode();
    }
    
    void insert(string word){
        TrieNode* cur = root;
        
        for(int i = 0; i < word.size(); i++){
            if(cur->next(word[i]) == NULL){
                cur->add(word[i], new TrieNode());
            }
            cur = cur->next(word[i]);
        }
        cur->end();
    }
    
    // GAP FIXED: Search for an exact word
    bool search(string word) {
        TrieNode* cur = root;
        for (int i = 0; i < word.size(); i++) {
            if (cur->next(word[i]) == NULL) {
                return false; // Character path doesn't exist
            }
            cur = cur->next(word[i]);
        }
        // Return true only if it's the actual end of an inserted word
        return cur->getEnd(); 
    }

    // GAP FIXED: Check if any word starts with this prefix
    bool startsWith(string prefix) {
        TrieNode* cur = root;
        for (int i = 0; i < prefix.size(); i++) {
            if (cur->next(prefix[i]) == NULL) {
                return false;
            }
            cur = cur->next(prefix[i]);
        }
        return true; // We successfully traversed the prefix path
    }
};

int main() {
    Trie myTrie;
    
    myTrie.insert("apple");
    myTrie.insert("app");
    
    cout << "Search 'apple': " << (myTrie.search("apple") ? "Found" : "Not Found") << endl;
    cout << "Search 'app': " << (myTrie.search("app") ? "Found" : "Not Found") << endl;
    cout << "Search 'appl': " << (myTrie.search("appl") ? "Found" : "Not Found") << endl;
    
    cout << "Starts with 'app': " << (myTrie.startsWith("app") ? "Yes" : "No") << endl;
    cout << "Starts with 'bat': " << (myTrie.startsWith("bat") ? "Yes" : "No") << endl;

    return 0;
}
