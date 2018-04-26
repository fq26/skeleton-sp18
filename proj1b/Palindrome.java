public class Palindrome {

    /** Return a deque linkedlist which stores characters of the string in the same order */
    public Deque<Character> wordToDeque(String word){
        Deque<Character> res = new LinkedListDeque<>();
        for(char c: word.toCharArray()) {
            res.addLast(c);
        }
        return res;
    }

    public boolean isPalindrome(String word) {
        Deque<Character> deque = this.wordToDeque(word);
        while( deque.size() > 1 ) {
            Character first = deque.removeFirst();
            Character last  = deque.removeLast();
            if(first != last) return false;
        }
        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> deque = this.wordToDeque(word);
        while( deque.size() > 1 ) {
            Character first = deque.removeFirst();
            Character last  = deque.removeLast();
            if(!cc.equalChars(first, last)) return false;
        }
        return true;
    }
}
