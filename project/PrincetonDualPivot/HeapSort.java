/**
 * Created by kfrye on 3/10/15.
 */

    /*
 * Java Program to Implement Heap Sort
 */

import sort_tools.SortIO;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.Scanner;

/* Class HeapSort */
public class HeapSort
{
    private static int N;
    /* Sort Function */
    public static void sort(Integer arr[])
    {
        heapify(arr);
        for (int i = N; i > 0; i--)
        {
            swap(arr,0, i);
            N = N-1;
            maxheap(arr, 0);
        }
    }
    /* Function to build a heap */
    public static void heapify(Integer arr[])
    {
        N = arr.length-1;
        for (int i = N/2; i >= 0; i--)
            maxheap(arr, i);
    }
    /* Function to swap largest element in heap */
    public static void maxheap(Integer arr[], int i)
    {
        int left = 2*i ;
        int right = 2*i + 1;
        int max = i;
        if (left <= N && arr[left] > arr[i])
            max = left;
        if (right <= N && arr[right] > arr[max])
            max = right;

        if (max != i)
        {
            swap(arr, i, max);
            maxheap(arr, max);
        }
    }
    /* Function to swap two numbers in an array */
    public static void swap(Integer arr[], int i, int j)
    {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
    /* Main method */
    public static void main(String[] args)
    {
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
            Heap.sort(nums);
            long stop = thMxB.getCurrentThreadCpuTime();
            tools.writeMeas(start, stop, output);
        } catch (Exception ex) {
            System.out.print(ex.toString());
        }
    }
}

