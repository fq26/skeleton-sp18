import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testPalindrome() {
        String s1 = "racecar";
        assertEquals(true, palindrome.isPalindrome(s1));
        String s2 = "a";
        assertEquals(true, palindrome.isPalindrome(s2));
        String s3 = "nona";
        assertEquals(false, palindrome.isPalindrome(s3));
    }

    @Test
    public void testPalindrom2() {
        OffByOne cc = new OffByOne();
        String s1 = "flake";
        assertEquals(true, palindrome.isPalindrome(s1, cc));
    }
}
