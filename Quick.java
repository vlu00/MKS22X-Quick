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
    return 1;
  }

  public static int[][] partition (int [] data, int start, int end) {
    Random rng = new Random();
    int pivot = rng.nextInt(end-start+1) + start;

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

    int index = 0;
    while (index < data.length-1 && data[index+1] <= num) {
      index++;
    }

    data[0] = data[index];
    data[index] = num;

    int[][] newData = new int[2][];
    //int[] a = new int[] {index};
    newData[0] = data;
    newData[1] = new int[] {index};

    System.out.println(toString(newData[0]));
    System.out.println(toString(newData[1]));

    return newData;
  }


  public static void main (String[] args) {
    //int [] A = new int [100];
    //for (int i = 0; i < 100; i++) {
    //  A[i] = 7;
    //}
    int[] A = new int[] {7, 9, 7, 2, 3, 0, 7};
    System.out.println(partition(A, 0, 6));
  }
}
