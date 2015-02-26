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
    boolean meas = false;
    heap = new heapsort();
    if (args.length < 2) {
      System.out.println("Usage: quicksort <input file> <output file>");
      System.exit(1);
    }
    if (args.length == 3 && args[2] == "-m")
      meas = true;
    try {
      String input = args[0];
      String output = args[1];
      SortIO tools = new SortIO();
      Integer[] getAll = tools.getData(input); //{1,5,4,3,2,7};
      int maxdepth = (int)floor(log(getAll.length)/log(2));
      long start = thMxB.getCurrentThreadCpuTime();
      introsort(getAll, maxdepth, 0, getAll.length - 1);
      long stop = thMxB.getCurrentThreadCpuTime();
      if(meas)
        tools.writeData(getAll, output);
      else
        tools.writeMeas(start, stop, output);
    } catch (Exception ex) {
      System.out.println(ex.toString());
    }
  }

  public static void introsort(Integer[] input, int maxdepth, int lo, int high) {
    if(high - lo <= 1)
      return;
    else if(maxdepth == 0) {
      heap.heapsort(input);
    }
    else {
      int p = partition(input, lo, high);
      introsort(input, maxdepth - 1, lo, p - 1);
      introsort(input, maxdepth - 1, p+1, high);
    }
  }

  public static Integer partition(Integer[] list, int lo, int high) {
    int pivot = list[(lo + high) / 2];
    left = lo;
    right = high;
    while (left <= right) {
      // look for left element to swap
      while (list[left] < pivot)
        ++left;
      // look for right element to swap
      while (list[right] > pivot)
        --right;
      //both found, now swap
      if (left <= right) {
        int temp = list[left];
        list[left] = list[right];
        list[right] = temp;
        ++left;
        --right;
      }
    }
    return right;
  }
}
