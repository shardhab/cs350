package sort;
import sort_tools.SortIO;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

public class HeapSort {

    public static void main(String[] args) {
        Integer[] nums;
        Integer[] sorted;
        ThreadMXBean thMxB = ManagementFactory.getThreadMXBean();

        if(args.length != 2) {
            System.out.print("Usage: heapsort <input file> <output file>");
            System.exit(1);
        }
        try {
            String input = args[0];
            String output = args[1];
            SortIO tools = new SortIO();
            nums = tools.getData(input);

            long start = thMxB.getCurrentThreadCpuTime();
            sorted = heapsort(nums);
            long stop = thMxB.getCurrentThreadCpuTime();
            //tools.writeMeas(start, stop, output);
            tools.writeData(sorted, output);
        }
        catch(Exception ex) {
            System.out.print(ex.toString());
        }

    }

    public static Integer[] heapsort(Integer[] nums) {
        int length = nums.length;
        for(int i = (length/2) - 1; i >= 0; i--) {
            siftDown(nums, i, length);
        }
        for(int i = length - 1; i >= 1; i--) {
            swap(nums, 0, i);
            siftDown(nums, 0, i-1);
        }
        return nums;
    }

    private static void siftDown(Integer[] nums, int root, int bottom) {
        boolean done = false;
        int max, temp;

        while((root * 2 <= bottom) && !done) {

            // Find the max child
            if(root * 2 == bottom) {
                max = root * 2;
            }
            else if (nums[root * 2] > nums[root * 2 + 1]) {
                max = root * 2;
            }
            else {
                max = root * 2 + 1;
            }

            // If max child is bigger than root, swap them
            if(nums[root] < nums[max]) {
                swap(nums, root, max);
                root = max;
            }
            else {
                done = true;
            }
        }
    }

    private static void swap(Integer[] nums, int first, int second) {
        int temp = nums[first];
        nums[first] = nums[second];
        nums[second] = temp;
    }
}
