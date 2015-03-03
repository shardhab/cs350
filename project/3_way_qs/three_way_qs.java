import sort_tools.SortIO;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

public class three_way_qs{
		private Integer[] nums;
	public static void main(String[] args) throws IOException {		
    ThreadMXBean thMxB = ManagementFactory.getThreadMXBean();
		three_way_qs sorter = new three_way_qs();
		if(args.length != 2){
			System.out.println("Usage: quicksort <input file> <output file>");
			System.exit(1);
		}	
		try{
			String input = args[0];
      String output = args[1];
      SortIO tools = new SortIO();
      Integer[] getAll = tools.getData(input); //{52,1,5,4,11,3,0,0,2,7,10,3,7,7,7}; 
		//	for(int i : getAll)
			//	System.out.print(i + " ");
			// System.out.println();
      long start = thMxB.getCurrentThreadCpuTime();
      sorter.sort(getAll);
      long stop = thMxB.getCurrentThreadCpuTime();
      tools.writeData(start, stop, output);
		}
		catch(Exception ex) {
      System.out.println(ex.toString());
    }
	}

	public void sort(Integer[] input){
		this.nums = input;
		quicksort(0, nums.length - 1);
		//for (int i : nums)
		//		System.out.print(i + " ");
		//System.out.println();
	}

	public void quicksort(int left, int right){
		if(left >= right)
			return;

		int l = left;
		int r = right;
		int i = left + 1;
		
		int pivotI = left;
		int pivotV = nums[pivotI];

		while(i <= r){
			if(nums[i] < pivotV){
				int temp = nums[i];
				nums[i] = nums[l];
				nums[l] = temp;
				++l;
				++i;
			}
			else if(pivotV < nums[i]){
				int temp = nums[i];
				nums[i] = nums[r];
				nums[r] = temp;
				--r;
			}
			else{
				++i;
			}
		}
				
		quicksort(left, l - 1);
		quicksort(r + 1, right);
	}
}
