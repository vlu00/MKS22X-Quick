import java.util.Random;
import java.io.*;
import java.util.*;

public class Quick{

  public static String toString(int [] data) {
    String display = "";
    for (int i = 0; i < data.length; i++) {
      display += data[i] + " ";
    }
    return display;
  }

  public static int quickselect(int[] data, int k) {
    int max = data.length-1;
    int min = 0;
    int pivot = partition(data, 0, data.length-1);

    while (pivot != k) {
      if (k < pivot) {
        max = pivot-1;
        pivot = partition(data, min, max);
      }
      else {
        min = pivot+1;
        pivot = partition(data, min, max);
      }
    }
    return data[k];
  }

  public static int findPivot(int [] data, int start, int end) {
/*
    int first = data[start];
    int second = data[(start+end)/2];
    int third = data[end];

    int total = data[start] + data[(start+end)/2] + data[end];
    int max = Math.max(data[start], Math.max(data[(start+end)/2], data[end]));
    int min = Math.min(data[start], Math.min(data[(start+end)/2], data[end]));
    int median = total - max - min;

    if (median == data[start]) {
      return start;
    }
    if (median == data[end]) {
      return end;
    }
    return (start+end)/2;
    */

    if (data[start] >= data[(start+end)/2]) {
      if (data[(start+end)/2] >= data[end]) {
        return (start+end)/2;
      }
      if (data[end] >= data[start]) {
        return start;
      }
      return end;
    }

    if (data[end] >= data[(start+end)/2]) {
      return (start+end)/2;
    }
    if (data[start] >= data[end]) {
      return start;
    }
    return end;

  }

  public static int partition (int [] data, int start, int end) {
    if (data.length == 1) {
      return 0;
    }
    
    Random rng = new Random();
    int pivot = rng.nextInt(end-start+1) + start; //findPivot(data, start, end); //
    int origStart = start;

    int num = data[pivot];
    data[pivot] = data[start];
    data[start] = num;
    start++;

    while (start < end) {
      int temp = data[start];
      if (data[start] == num) {
        if (rng.nextInt(2) == 0) {
          start++;
        }
        else {
          data[start] = data[end];
          data[end] = temp;
          end--;
        }
      }
      else if (data[start] > num) {
        data[start] = data[end];
        data[end] = temp;
        end--;
      }
      else {
        start++;
      }
    }

    if (data[start] > num) {
      data[origStart] = data[start-1];
      data[start-1] = num;
      return start-1;
    }
    else {
      data[origStart] = data[start];
      data[start] = num;
      return start;
    }
  }

  public static void quicksort(int[] data) {
    quicksort(data, 0, data.length-1);
  }

  public static void quicksort(int[] data, int lo, int hi) {
    if (lo >= hi) {
      return;
    }
    int pivot = partition(data, lo, hi);
    quicksort(data, lo, pivot-1);
    quicksort(data, pivot+1, hi);
  }


  public static void main (String[] args) {
    System.out.println("Size\t\tMax Value\tquick/builtin ratio ");
  int[]MAX_LIST = {1000000000,500,10};
  for(int MAX : MAX_LIST){
    for(int size = 31250; size < 2000001; size*=2){
      long qtime=0;
      long btime=0;
      //average of 5 sorts.
      for(int trial = 0 ; trial <=5; trial++){
        int []data1 = new int[size];
        int []data2 = new int[size];
        for(int i = 0; i < data1.length; i++){
          data1[i] = (int)(Math.random()*MAX);
          data2[i] = data1[i];
        }
        long t1,t2;
        t1 = System.currentTimeMillis();
        Quick.quicksort(data2);
        t2 = System.currentTimeMillis();
        qtime += t2 - t1;
        t1 = System.currentTimeMillis();
        Arrays.sort(data1);
        t2 = System.currentTimeMillis();
        btime+= t2 - t1;
        if(!Arrays.equals(data1,data2)){
          System.out.println("FAIL TO SORT!");
          System.exit(0);
        }
      }
      System.out.println(size +"\t\t"+MAX+"\t"+1.0*qtime/btime);
    }
    System.out.println();
  }
/*
    int [] A = new int [10000000];
    for (int i = 0; i < 10000000; i++) {
      A[i] = 10000000-i;
    }

    int[] B = new int[] {0, 7, 6, 2, 3, 8, 9};
    quicksort(B);
    System.out.println(toString(B));
    System.out.println(quickselect(A, 4));
    System.out.println(quickselect(A, 6));
    System.out.println(quickselect(A, 15));
    */
  }
}
