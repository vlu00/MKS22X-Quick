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

  public static int quickSelect(int[] data, int k) {
    //int[][] tempData = partition(data, 0, data.length-1);
    int max = data.length-1;
    int min = 0;
    int pivot = partition(data, 0, data.length-1);
    System.out.println("first partition "+toString(data));
    while (pivot != k) {
      System.out.println(pivot);
      if (k < pivot) {
        max = pivot-1;
        pivot = partition(data, min, max);
        System.out.println(toString(data));
      }
      else {
        min = pivot+1;
        pivot = partition(data, min, max);
        System.out.println(toString(data));
      }
    }
    return data[k];
  }
  /*

  private static int findPivot (int[] data, int start, int end) {
    Random rng = new Random();
    int[] list = new int[3];
    list[0] = data[start];
    list[1] = data[end];
    list[2] = rng.nextInt(end-start-1) + start + 1;

    for (int i = 0; i < 3; i++) {
    }
  }
*/
  public static int partition (int [] data, int start, int end) {
    Random rng = new Random();
    int pivot = rng.nextInt(end-start+1) + start;
    int index = start;

    System.out.println(pivot);
    System.out.println(data[pivot]);

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

    System.out.println(toString(data));

    /*
    data[0] = data[index];
    data[index] = num;
    int[][] newData = new int[2][];
    newData[0] = data;
    newData[1] = new int[] {index};
*/
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
    /*
    int [] A = new int [50];
    for (int i = 0; i < 50; i++) {
      A[i] = 50-i;
    }
    */
    int[] A = new int[] {0, 7, 6, 2, 3, 8, 9};
    System.out.println(partition(A, 2, 4));
  }
}
