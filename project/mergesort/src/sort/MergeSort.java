package sort;
import sort_tools.SortIO;
import java.io.IOException;

public class MergeSort {

    public static void main(String[] args) throws IOException {
        Integer[] nums;
        if(args.length != 1) {
            System.out.print("Usage: mergesort <filename>");
            System.exit(1);
        }

        try {
            String name = args[0];
            SortIO tools = new SortIO();
            nums = tools.getData(name);
        }
        catch(Exception ex) {
            System.out.print(ex.toString());
        }
    }
}
