import java.util.Random;
 
/*
The Knuth shuffle (a.k.a. the Fisher-Yates shuffle) is an algorithm for randomly shuffling the elements of an array.

Given an array a with indices ranging from 0 to last, the algorithm can be defined as follows (pseudo-code):

    for i from last downto 1 do:
        let j = random integer in range 0 ≤ j < i
        swap a[i] with a[j]
		   
An equivalent version which shuffles the array in the opposite direction (from lowest index to highest) is:

-- To shuffle an array a of n elements (indices 0..n-1):
    for i from 0 to n−2 do
        let j = random integer such that i ≤ j < n
        swap a[i] and a[j]
*/

public static final Random gen = new Random();

// version for array of ints
public static void shuffle (int[] array) {
    int n = array.length;
    while (n > 1) {
        int k = gen.nextInt(n--); //decrements after using the value
        int temp = array[n];
        array[n] = array[k];
        array[k] = temp;
    }
}
// version for array of references
public static void shuffle (Object[] array) {
    int n = array.length;
    while (n > 1) {
        int k = gen.nextInt(n--); //decrements after using the value
        Object temp = array[n];
        array[n] = array[k];
        array[k] = temp;
    }
}