import sort_tools.SortIO;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

public class heapsort {

  public static void main(String[] args) {
    Integer[] nums;
    ThreadMXBean thMxB = ManagementFactory.getThreadMXBean();

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
      heapsort(nums, 0, nums.length);
      long stop = thMxB.getCurrentThreadCpuTime();
  //    tools.writeData(nums, output);
      tools.writeMeas(start, stop, output);
    } catch (Exception ex) {
      System.out.print(ex.toString());
    }
  }

  public static Integer[] heapsort(Integer[] nums, int lo, int high) {
    int length = high - lo;
    for (int i = (length / 2); i >= 1; i--) {
      siftDown(nums, i, length, lo);
    }
    for (int i = length; i > 1; i--) {
      swap(nums, lo, lo + i - 1);
      siftDown(nums, 1, i - 1, lo);
    }
    return nums;
  }

  private static void siftDown(Integer[] nums, int root, int bottom, int lo) {
    int child;
    int d = nums[lo + root - 1];

    while (root * 2 <= bottom) {
      child = 2 * root;
      if (child < bottom && nums[lo + child - 1] < nums[lo + child]) {
        child++;
      }
      if(d >= nums[lo + child - 1]) break;
      nums[lo + root - 1] = nums[lo + child - 1];
      root = child;
    }
    nums[lo + root - 1] = d;
  }

  private static void swap(Integer[] nums, int first, int second) {
    int temp = nums[first];
    nums[first] = nums[second];
    nums[second] = temp;
  }
}
