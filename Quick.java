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
*/
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
    /*

    if (first >= second) {
      if (second >= third) {
        return second;
      }
      if (third >= first) {
        return first;
      }
      return third;
    }

    if (third >= second) {
      return second;
    }
    if (first >= third) {
      return first;
    }
    return third;
    */
  }

  public static int partition (int [] data, int start, int end) {
    //Random rng = new Random();
    int pivot =  findPivot(data, start, end); //rng.nextInt(end-start+1) + start; //
    int origStart = start;
    int index = start;

    int num = data[pivot];
    data[pivot] = data[start];
    data[start] = num;
    start++;
    boolean moveLeft = true;

    while (start < end) {
      int temp = data[start];
      if (data[start] == num) {
        if (moveLeft) {
          start++;
          moveLeft = false;
        }
        else {
          data[start] = data[end];
          data[end] = temp;
          end--;
          moveLeft = true;
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

    while (index < data.length-1 && data[index+1] <= num) {
      index++;
    }

    data[origStart] = data[index];
    data[index] = num;

    return index;
  }

  public static void quicksort(int[] data) {
    quicksort(data, 0, data.length);
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

    int [] A = new int [10000000];
    for (int i = 0; i < 10000000; i++) {
      A[i] = 10000000-i;
    }

    int[] B = new int[] {0, 7, 6, 2, 3, 8, 9};
    System.out.println(findPivot(B, 1, 5));
    System.out.println(quickselect(A, 4));
    System.out.println(quickselect(A, 6));
    System.out.println(quickselect(A, 15));
  }
}
