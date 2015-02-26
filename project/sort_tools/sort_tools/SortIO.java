package sort_tools;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.Exception;
import java.lang.Integer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class SortIO {
  public static Integer[] getData(String filename) throws Exception {
    Scanner inFile = new Scanner(new File(filename)).useDelimiter(",\\s*");
    List<Integer> temps = new ArrayList<Integer>();
    String first = inFile.next();
    first = first.substring(1);
    temps.add(Integer.parseInt(first));
    while (inFile.hasNext()) {
      try {
        Integer token1 = inFile.nextInt();
        temps.add(token1);
      } catch (Exception ex) {
        String last = inFile.next();
        last = last.substring(0, last.length() - 2);
        temps.add(Integer.parseInt(last));
      }
    }
    inFile.close();

    return temps.toArray(new Integer[0]);
  }

  public static Long[] getMeasData(String filename) throws Exception {
    Scanner inFile = new Scanner(new File(filename));
    List<Long> temps = new ArrayList<Long>();
    while (inFile.hasNext()) {
      temps.add(inFile.nextLong());
    }
    inFile.close();
    return temps.toArray(new Long[0]);
  }

  public static void writeData(Integer[] list, String output) throws Exception {
    PrintWriter outputWriter = new PrintWriter(new BufferedWriter(new FileWriter(output, true)));
    outputWriter.println(Arrays.toString(list));
    outputWriter.close();
  }

  public static void writeMeas(long start, long stop, String file) throws Exception {
    PrintWriter outputWriter = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
    outputWriter.println(Long.toString(stop - start));
    outputWriter.close();
  }

  public static long getAvg(Long[] data) {
    long med;
    int med_idx;
    List<Long> out = new ArrayList<Long>();
    if(data.length % 2 == 0) {
      med_idx = data.length/2 - 1;
      med = (data[med_idx] + data[med_idx + 1])/2;
    }
    else {
      med_idx = (data.length - 1)/2;
      med = data[med_idx];
      med_idx -= 1;
    }
    int Q1_idx, Q3_idx;
    long Q1, Q3;

    if((med_idx + 1) % 2 == 0) {
      Q1_idx = (med_idx + 1) / 2 - 1;
      Q1 = (data[Q1_idx] + data[Q1_idx + 1])/2;
      Q3_idx = med_idx + Q1_idx + 1;
      Q3 = (data[Q3_idx] + data[Q1_idx + 1])/2;
    }
    else {
      Q1_idx = med_idx / 2;
      Q1 = data[Q1_idx];
      Q3_idx = med_idx + Q1_idx + 1;
      Q3 = data[Q3_idx];
    }
    long range = Q3 - Q1;
    double inner_factor = range * 1.5;
    double iQ1 = Q1 - inner_factor;
    double iQ3 = Q3 + inner_factor;
    double outer_factor = range * 3;
    double oQ1 = Q1 - outer_factor;
    double oQ3 = Q3 + outer_factor;

    long total = 0;
    for(int i = 0; i < data.length; i++) {
      if(data[i] >= iQ1 && data[i] <= iQ3) {
        out.add(data[i]);
        total += data[i];
      }
    }
    return total/out.size();
  }

  public static void main(String[] args ) throws Exception {
    if(args.length < 2) {
      System.out.println("Usage: SortIO <input file> <output file>");
      System.exit(1);
    }
    String filename = args[0];
    String output = args[1];
    Long[] data = getMeasData(filename);
    Arrays.sort(data);
    long avg = getAvg(data);
    PrintWriter outputWriter = new PrintWriter(new BufferedWriter(new FileWriter(output)));
    outputWriter.println(avg);
    outputWriter.close();
  }
}
