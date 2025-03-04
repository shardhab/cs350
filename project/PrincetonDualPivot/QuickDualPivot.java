import sort_tools.SortIO;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

/*************************************************************************
 *  Compilation:  javac QuickDualPivot.java
 *  Execution:    java QuickDualPivot < input.txt
 *  Dependencies: StdOut.java StdIn.java
 *  Data files:   http://algs4.cs.princeton.edu/23quicksort/tiny.txt
 *                http://algs4.cs.princeton.edu/23quicksort/words3.txt
 *   
 *  Sorts a sequence of strings from standard input using dual-pivot
 *  quicksort.
 *
 *  [Warning: not thoroughly tested.]
 *   
 *  % more tiny.txt
 *  S O R T E X A M P L E
 *
 *  % java QuickDualPivot < tiny.txt
 *  A E E L M O P R S T X                 [ one string per line ]
 *    
 *  % more words3.txt
 *  bed bug dad yes zoo ... all bad yet
 *  
 *  % java QuickDualPivot < words3.txt
 *  all bad bed bug dad ... yes yet zoo    [ one string per line ]
 *
 *************************************************************************/

public class QuickDualPivot {

    // quicksort the array a[] using dual-pivot quicksort
    public static void sort(Comparable[] a) {
        //StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
        assert isSorted(a);
    }

    // quicksort the subarray a[lo .. hi] using dual-pivot quicksort
    private static void sort(Comparable[] a, int lo, int hi) { 
        if (hi <= lo) return;

        // make sure a[lo] <= a[hi]
        if (less(a[hi], a[lo])) exch(a, lo, hi);

        int lt = lo + 1, gt = hi - 1;
        int i = lo + 1;
        while (i <= gt) {
            if       (less(a[i], a[lo])) exch(a, lt++, i++);
            else if  (less(a[hi], a[i])) exch(a, i, gt--);
            else                         i++;
        }
        exch(a, lo, --lt);
        exch(a, hi, ++gt);

        // recursively sort three subarrays
        sort(a, lo, lt-1);
        if (less(a[lt], a[gt])) sort (a, lt+1, gt-1);
        sort(a, gt+1, hi);

        assert isSorted(a, lo, hi);
    }



   /***********************************************************************
    *  Helper sorting functions
    ***********************************************************************/
    
    // is v < w ?
    private static boolean less(Comparable v, Comparable w) {
        return (v.compareTo(w) < 0);
    }

    // exchange a[i] and a[j]
    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

   /***********************************************************************
    *  Check if array is sorted - useful for debugging
    ***********************************************************************/
    private static boolean isSorted(Comparable[] a) {
        return isSorted(a, 0, a.length - 1);
    }

    private static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }



    // print array to standard output
    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.println(a[i]);
        }
    }

    // Read strings from standard input, sort them, and print.
    public static void main(String[] args) {
        ThreadMXBean thMxB = ManagementFactory.getThreadMXBean();
        Integer[] nums;

        if (args.length != 2) {
            System.out.print("Usage: heapsort <input file> <output file>");
            System.exit(1);
        }
        try {
            String input = args[0];
            String output = args[1];
            SortIO tools = new SortIO();
            nums = tools.getData(input);
            long start = thMxB.getCurrentThreadCpuTime();
            QuickDualPivot.sort(nums);
            long stop = thMxB.getCurrentThreadCpuTime();
            tools.writeMeas(start, stop, output);
        } catch (Exception ex) {
            System.out.print(ex.toString());
        }
    }

}
