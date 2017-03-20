import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

/**
 * HackerRank: Hash Tables: Ransom Note
 * https://www.hackerrank.com/challenges/ctci-ransom-note
 *
 * Look if the words from the first collection are enough to produce the words
 * in the second collection. Answer 'Yes'/'No'
 */
public class Ransom {

    private static Hashtable createIndex(String arr[]){
        boolean debug = false;
        Hashtable index = new Hashtable();
        for (int i = 0; i < arr.length; i++){
            String word = arr[i];
            if (index.containsKey(word)){
                int value = ((Integer)index.get(word)).intValue() + 1;
                index.put(word, new Integer(value));
            } else {
                index.put(word, new Integer(1));
            }
        }
        if (debug){
            Enumeration words = index.keys();
            while( words.hasMoreElements()){
                String str = (String) words.nextElement();
                System.out.println(str + " : " + index.get(str));
            }
        }

        return index;
    }

    private static String matchWords(Hashtable message, Hashtable index){
        boolean debug = false;
        String answer = "No";
        Enumeration words = message.keys();
        while (words.hasMoreElements()){
            String word = (String) words.nextElement();
            if (index.containsKey(word)){
                int messageValue = ((Integer) message.get(word)).intValue();
                int indexValue = ((Integer) index.get(word)).intValue();
                if (messageValue <= indexValue){
                    if (debug){
                        System.out.println("word:" + word + " message:" + messageValue +
                                           " index:" + indexValue);
                    }
                } else {
                    break;
                }
            } else {
                break;
            }
            if (! words.hasMoreElements()){
                answer = "Yes";
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();
        int n = in.nextInt();
        String magazine[] = new String[m];
        for(int magazine_i=0; magazine_i < m; magazine_i++){
            magazine[magazine_i] = in.next();
        }

        Hashtable index = createIndex(magazine);

        String ransom[] = new String[n];
        for(int ransom_i=0; ransom_i < n; ransom_i++){
            ransom[ransom_i] = in.next();
        }

        Hashtable message = createIndex(ransom);

        String answer = matchWords(message, index);
        System.out.println(answer);
    }
}
