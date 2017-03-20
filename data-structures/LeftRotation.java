import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

/**
 * A left rotation operation on an array of size  shifts each of the array's elements
 *   unit to the left. The first line contains two space-separated integers denoting
 *   the respective values of  (the number of integers) and  (the number of left
 *   rotations you must perform). The second line contains  space-separated
 *   integers describing the respective elements of the array's initial state.
 *   Print a single line of  space-separated integers denoting the final state of
 *   the array after performing  left rotations.
 *
 *   https://www.hackerrank.com/challenges/ctci-array-left-rotation
 */

public class LeftRotation {

    /**
     * Save the first k elements in a helper array, then copy the rest n-k
     * elements into the beginning of the array. Then continue filling the array
     * with the elements from the helper array.
     * @param  a array to be rotated
     * @param  n length of array
     * @param  k how often to rotate
     * @return   rotated array
     */
    public static int[] arrayLeftRotation(int[] a, int n, int k) {
        int[] temp = new int[k];
        for (int i = 0; i < k; i++){
            temp[i] = a[i];
        }
        for (int i = 0; i < a.length; i++){
            if (i < n - k){
                a[i] = a[i+k];
            } else { // i > k
                a[i] = temp[i-(n-k)];
            }
        }
        return a;

    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        int a[] = new int[n];
        for(int a_i=0; a_i < n; a_i++){
            a[a_i] = in.nextInt();
        }

        //int[] output = new int[n]; - can be used but we'll use in-place rotation
        a = arrayLeftRotation(a, n, k);
        for(int i = 0; i < n; i++)
            System.out.print(a[i] + " ");

        System.out.println();

    }
}
