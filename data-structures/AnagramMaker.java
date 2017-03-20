import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

/**
 * Counts the number of characters to be deleted to make anagrams
 * out of input strings.
 * Two strings are considered to be anagrams of each other
 * if the first string's letters can be rearranged to form
 * the second string. In other words, both strings must contain
 * the same exact letters in the same exact frequency.
 * For example, bacdc and dcbac are anagrams, but bacdc and dcbad are not.
 *
 * https://www.hackerrank.com/challenges/ctci-making-anagrams
 */
public class AnagramMaker {

    /**
     * [numberNeeded description]
     * @param  first  first String
     * @param  second second String
     * @return        number of characters to be deleted
     */
    public static int numberNeeded(String first, String second) {
        int result = 0;

        StringBuilder s1, s2;
        if (first.length() > second.length()){
            s1 = new StringBuilder(first);
            s2 = new StringBuilder(second);
        } else {
            s1 = new StringBuilder(second);
            s2 = new StringBuilder(first);
        }

        for (int i = 0; i < s2.length(); i++){
            int j = s1.indexOf( Character.toString(s2.charAt(i)) );
            if ( j >= 0 ){
                s1.deleteCharAt(j);
                s2.deleteCharAt(i);
                i--;
            }
        }
        //System.out.println("s1 " + s1.toString());
        //System.out.println("s2 " + s2.toString());
        result = s1.length() + s2.length();

        return result;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String a = in.next();
        String b = in.next();
        System.out.println(numberNeeded(a, b));
    }
}
