import sort_tools.SortIO;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

public class dual_pivot_qs {
		private Integer[] nums;
	public static void main(String[] args) throws IOException {		
    ThreadMXBean thMxB = ManagementFactory.getThreadMXBean();
		dual_pivot_qs sorter = new dual_pivot_qs(); 
		if(args.length != 2){
			System.out.println("Usage: quicksort <input file> <output file>");
			System.exit(1);
		}	
		try{
			String input = args[0];
      String output = args[1];
      SortIO tools = new SortIO();
      Integer[] getAll = tools.getData(input); //{52,1,5,4,11,3,0,0,2,7,10,3,7,7,7}
      long start = thMxB.getCurrentThreadCpuTime();
      sorter.sort(getAll);
      long stop = thMxB.getCurrentThreadCpuTime();
      tools.writeMeas(start, stop, output);
//      tools.writeData(getAll, output);
		}
		catch(Exception ex) {
      System.out.println(ex.toString());
    }
	}

	public void sort(Integer[] input){
		this.nums = input;
		quicksort(0, nums.length - 1);
//		for (int i : nums)
//				System.out.print(i + " ");
//		System.out.println();
	}

	public void quicksort(int left, int right){
		if(right <= left)
			return;

		int l = left + 1;
		int r = right - 1;
		int i = left + 1;
		if(nums[left] > nums[right]){
			int temp = nums[left];
			nums[left] = nums[right];
			nums[right] = temp;
		}

		while(i <= r){
			if(nums[i] < nums[left]){
				int temp = nums[i];
				nums[i] = nums[l];
				nums[l] = temp;
				++l;
				++i;
			}
			else if(nums[right] < nums[i]){
				int temp = nums[i];
				nums[i] = nums[r];
				nums[r] = temp;
				--r;
			}
			else{
				++i;
			}
		}
		--l;
		int temp = nums[l];
		nums[l] = nums[left];
		nums[left] = temp;
		
		++r;
		temp = nums[r];
		nums[r] = nums[right];
		nums[right] = temp;
				
		quicksort(left, l - 1);
		if (nums[l] < nums[r])
			quicksort(l + 1, r - 1);
		quicksort(r + 1, right);
	}
}
