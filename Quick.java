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
    System.out.println("first partition "+toString(tempData[0]));
    while (tempData[1][0] != k) {
      System.out.println(tempData[1][0]);
      if (k < tempData[1][0]) {
        tempData = partition(tempData[0], 0, tempData[1][0]-1);
        System.out.println(toString(tempData[0]));
      }
      else {
        tempData = partition(tempData[0], tempData[1][0]+1, data.length-1);
        System.out.println(toString(tempData[0]));
      }
    }
    return tempData[0][k];
  }

  public static int[][] partition (int [] data, int start, int end) {
    Random rng = new Random();
    int pivot = rng.nextInt(end-start+1) + start;
    int index = start;
    System.out.println(pivot);


    int num = data[pivot];
    data[pivot] = data[start];
    data[start] = num;
    start++;
    boolean moveLeft = true;

    while (start < end) {
      System.out.println("start " + start);
      System.out.println("end " + end);
      System.out.println(toString(data));

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

    System.out.println(toString(data));

    //int index = 0;
    while (index < data.length-1 && data[index+1] <= num) {
      index++;
    }

    data[0] = data[index];
    data[index] = num;
    int[][] newData = new int[2][];
    newData[0] = data;
    newData[1] = new int[] {index};

    //System.out.println(toString(newData[0]));
    //System.out.println(toString(newData[1]));

    return newData;
  }


  public static void main (String[] args) {
    //int [] A = new int [100];
    //for (int i = 0; i < 100; i++) {
    //  A[i] = 7;
    //}
    int[] A = new int[] {0, 9, 6, 2, 3, 8, 7};
    System.out.println(partition(A, 2, 6));
  }
}
