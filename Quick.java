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
    int[][] tempData = partition(data, 0, data.length-1);
    while (tempData[1][0] != k) {
      if (k < tempData[1][0]) {
        tempData = partition(tempData[0], 0, tempData[1][0]-1);
      }
      else {
        tempData = partition(tempData[0], tempData[1][0]+1, data.length-1);
      }
    }
    return tempData[0][k];
  }

  public static int[][] partition (int [] data, int start, int end) {
    Random rng = new Random();
    int pivot = rng.nextInt(end-start+1) + start;
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

    data[0] = data[index];
    data[index] = num;
    int[][] newData = new int[2][];
    newData[0] = data;
    newData[1] = new int[] {index};

    return newData;
  }


  public static void main (String[] args) {
    //int [] A = new int [100];
    //for (int i = 0; i < 100; i++) {
    //  A[i] = 7;
    //}
    int[] A = new int[] {0, 9, 6, 2, 3, 8, 7};
    System.out.println(quickSelect(A, 4));
  }
}
