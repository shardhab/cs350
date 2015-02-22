import sort_tools.SortIO;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

public class QuickSort{
		private Integer[] nums;
	public static void main(String[] args) throws IOException {		
    ThreadMXBean thMxB = ManagementFactory.getThreadMXBean();
		QuickSort sorter = new QuickSort();
		if(args.length != 2){
			System.out.println("Usage: quicksort <input file> <output file>");
			System.exit(1);
		}	
		try{
			String input = args[0];
      String output = args[1];
      SortIO tools = new SortIO();
      Integer[] getAll = tools.getData(input); //{1,5,4,3,2,7};
			//for(int i : getAll)
			//	System.out.print(i + " ");
			// System.out.println();
      long start = thMxB.getCurrentThreadCpuTime();
      sorter.sort(getAll);
      long stop = thMxB.getCurrentThreadCpuTime();
      tools.writeMeas(start, stop, output);
		}
		catch(Exception ex) {
      System.out.println(ex.toString());
    }
	}

	public void sort(Integer[] input){
		this.nums = input;
		quicksort(0, nums.length - 1);
		for (int i : nums)
				System.out.println(i + " ");
	}

	public void quicksort(int left, int right){
		int pivot = nums[(left + right) / 2];
		int l = left;
		int r = right;
		while(l <= r){
			// look for left element to swap
			while(nums[l] < pivot)
				++l;
			// look for right element to swap
			while(nums[r] > pivot)
				--r;
			//both found, now swap
			if(l <= r){
				int temp = nums[l];
				nums[l] = nums[r];
				nums[r] = temp;
				++l;
				--r;
			}
		}
		if (left < r)
			quicksort(left, r);
		if (l < right)
			quicksort(l, right);
	}
}
