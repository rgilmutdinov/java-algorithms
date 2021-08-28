class Trie {

    public class TrieNode {        
        public HashMap<Character, TrieNode> children;        
        public boolean isWord;
        
        public TrieNode() {
            children = new HashMap<>();            
            isWord = false;            
        }
        
        public boolean hasChild(char c) {
            return children.containsKey(c);
        }
        
        public TrieNode getChild(char c) {
            return children.get(c);
        }
        
        public void setChild(char c, TrieNode child) {
            children.put(c, child);
        }
    }
    
    private TrieNode root;    
    public Trie() {
        root = new TrieNode();
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {            
            char c = word.charAt(i);
            if (!curr.hasChild(c)) {                
                curr.setChild(c, new TrieNode());
            }
            
            curr = curr.getChild(c);
        }
        
        curr.isWord = true;
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {   
            char c = word.charAt(i);
            if (!curr.hasChild(c)) {
                return false;
            }
            
            curr = curr.getChild(c);
        }
        
        return curr.isWord;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode curr = root;
        for (int i = 0; i < prefix.length(); i++) { 
            char c = prefix.charAt(i);
            if (!curr.hasChild(c)) {
                return false;
            }
            
            curr = curr.getChild(c);
        }
        
        return true;
    }
}