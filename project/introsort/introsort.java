import sort_tools.SortIO;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

import static java.lang.Math.floor;
import static java.lang.Math.log;

public class introsort {
  private static Integer[] nums;
  public static int left;
  public static int right;
  private static heapsort heap;

  public static void main(String[] args) throws IOException {
    ThreadMXBean thMxB = ManagementFactory.getThreadMXBean();
    heap = new heapsort();
    if (args.length != 2) {
      System.out.println("Usage: introsort <input file> <output file>");
      System.exit(1);
    }

    try {
      String input = args[0];
      String output = args[1];
      SortIO tools = new SortIO();
      Integer[] getAll = tools.getData(input); //{1,5,4,3,2,7};
      int maxdepth = (int)floor(log(getAll.length)/log(2));
      long start = thMxB.getCurrentThreadCpuTime();
      introsort(getAll, maxdepth, 0, getAll.length - 1);
      long stop = thMxB.getCurrentThreadCpuTime();
      //tools.writeData(getAll, output);
      tools.writeMeas(start, stop, output);
    } catch (Exception ex) {
      System.out.println(ex.toString());
    }
  }

  public static void introsort(Integer[] input, int maxdepth, int left, int right) {
    if(maxdepth == 0) {
      heap.heapsort(input, left, right);
    }
    int pivot = input[(left + right) / 2];
    int l = left;
    int r = right;
    while(l <= r){
      // look for left element to swap
      while(input[l] < pivot)
        ++l;
      // look for right element to swap
      while(input[r] > pivot)
        --r;
      //both found, now swap
      if(l <= r){
        int temp = input[l];
        input[l] = input[r];
        input[r] = temp;
        ++l;
        --r;
      }
    }
    if (left < r)
      introsort(input, maxdepth - 1, left, r);
    if (l < right)
      introsort(input, maxdepth - 1, l, right);
  }

  public static Integer partition(Integer[] list, int lo, int high) {
    left = lo;
    right = high + 1;
    int pivot = list[(lo + high) / 2];
    while (true) {
      while (list[left] < pivot)
        left++;
      right--;
      while (list[right] > pivot)
        right--;
      if (!(left < right))
        return left - 1;
      swap(list, left, right);
      left++;
    }
  }

  private static void swap(Integer[] list, int i, int j) {
    Integer t = list[i];
    list[i] = list[j];
    list[j] = t;
  }
}
