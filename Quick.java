import java.util.Random;
import java.io.*;
import java.util.*;

public class Quick{

  public static void insertionsort(int[] data, int lo, int hi) {
    if (hi-lo+1 > 1) { //if length is 0 or 1, data remains the same
      int counter = lo;
      while (counter < hi) {
        int x = data[counter+1];
        int index = counter+1;
        for (int n = counter+1; n != lo && data[n-1] > x; n--) {
          data[n] = data[n-1];
          index--;
        }
        data[index] = x;
        counter++;
      }
    }
  }

  public static int quickselect(int[] data, int k) {
    int max = data.length-1; //upper bound. Ignore numbers after this
    int min = 0; //lower bound. Ignore number before this
    int pivot = partition(data, 0, data.length-1);

    while (pivot != k) { //if kth element not placed
      if (k < pivot) {
        max = pivot-1; //change upper bound
        pivot = partition(data, min, max); //new pivot
      }
      else {
        min = pivot+1; //change lower bound
        pivot = partition(data, min, max); //new pivot
      }
    }
    return data[k];
  }

  private static int findPivot(int [] data, int start, int end) {
    int total = data[start] + data[(start+end)/2] + data[end]; //total of 3 numbers (first, last and middle element)
    int max = Math.max(data[start], Math.max(data[(start+end)/2], data[end]));
    int min = Math.min(data[start], Math.min(data[(start+end)/2], data[end]));
    int median = total - max - min; //total subtract max and min is the number in the middle

    if (median == data[start]) {
      return start;
    }
    if (median == data[end]) {
      return end;
    }
    return (start+end)/2;
  }

  public static int partition (int [] data, int start, int end) {
    if (data.length == 1) {
      return 0; //don't have to do everything below
    }

    Random rng = new Random();
    int pivot = findPivot(data, start, end); //optimized pivot
    int origStart = start;

    int num = data[pivot];
    data[pivot] = data[start]; //move num to front
    data[start] = num; //swap whatever was in first position
    start++;
    boolean moveLeft = true; //to optimize when there are duplicates

    //sorts
    while (start < end) { //while there are still unsorted
      int temp = data[start];
      if (data[start] == num) { //if the numbers are equal
        if (moveLeft) { //half the time
          start++; //keep in current position
          moveLeft = false;
        }
        else { //other half
          data[start] = data[end]; //move to end
          data[end] = temp;
          end--;
          moveLeft = true;
        }
      }
      else if (data[start] > num) { //move to end
        data[start] = data[end];
        data[end] = temp;
        end--;
      }
      else { //keep in current position and continue
        start++;
      }
    }

    //determines where pivot goes
    if (data[start] > num) { //if final number checked is greater than num
      data[origStart] = data[start-1]; //place num before that final number
      data[start-1] = num;
      return start-1;
    }
    else { //final number is smaller than num
      data[origStart] = data[start]; //move smaller number to start
      data[start] = num;
      return start;
    }
  }

  public static void quicksort(int[] data) {
    quicksort(data, 0, data.length-1); //helper method
  }

  //trying with insertionsort
  public static void quicksort(int[] data, int lo, int hi) {
    if (hi-lo+1 < 20) { //if length of portion being sorted is less than 20
      insertionsort(data, lo, hi); //insertionsort yeilds faster result
    }
    else {
      int pivot = partition(data, lo, hi); //new pivot and one element is in correct position
      quicksort(data, lo, pivot-1); //sort first half
      quicksort(data, pivot+1, hi); //sort second half
    }
  }

}
