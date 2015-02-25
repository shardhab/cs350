import sort_tools.SortIO;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

public class mergesort {

    public static void main(String[] args) throws IOException {
        Integer[] nums;
        Integer[] sorted;
        ThreadMXBean thMxB = ManagementFactory.getThreadMXBean();

        if(args.length != 2) {
            System.out.print("Usage: mergesort <input file> <output file>");
            System.exit(1);
        }
        try {
            String input = args[0];
            String output = args[1];
            SortIO tools = new SortIO();
            nums = tools.getData(input);

            long start = thMxB.getCurrentThreadCpuTime();
            sorted = mergesort(nums);
            long stop = thMxB.getCurrentThreadCpuTime();
            tools.writeMeas(start, stop, output);
            //tools.writeData(sorted, output);
        }
        catch(Exception ex) {
            System.out.print(ex.toString());
        }
    }

    public static Integer[] mergesort(Integer[] nums) {
        int length = nums.length;
        if(length <= 1) return nums;

        Integer[] left = new Integer[length / 2];
        Integer[] right = new Integer[length - left.length];
        System.arraycopy(nums, 0, left, 0, left.length);
        System.arraycopy(nums, left.length, right, 0, right.length);

        left = mergesort(left);
        right = mergesort(right);

        nums = merge(left, right);
        return nums;
    }

    public static Integer[] merge(Integer[] left, Integer[] right) {
        int lcount = 0;
        int rcount = 0;
        Integer[] sorted = new Integer[left.length + right.length];

        int i = 0;
        while(lcount < left.length && rcount < right.length) {
            if(left[lcount] < right[rcount]) {
                sorted[i] = left[lcount];
                lcount++;
            }
            else {
                sorted[i] = right[rcount];
                rcount++;
            }
            i++;
        }
        System.arraycopy(left, lcount, sorted, i, left.length - lcount );
        System.arraycopy(right, rcount, sorted, i, right.length - rcount);
        return sorted;
    }
}
