import java.util.Random;

public class Quick{

  public static String toString(int [] data) {
    String display = "";
    for (int i = 0; i < data.length; i++) {
      display += data[i] + " ";
    }
    return display;
  }

  public static int lastIndexOf(int [] data, int start, int value) {
    while (start < data.length && data[start] < value) {
      start++;
    }
    return start-1;
  }

  public static int indexOf(int [] data, int value) {
    int i = 0;
    while (data[i] < value) {
      i++;
    }
    return i;
  }

  public static int partition (int [] data, int start, int end) {
    Random rng = new Random();
    int pivot = rng.nextInt(end-start+1) + start;
    int mIndex = 1;

    int num = data[pivot];
    data[pivot] = data[start];
    data[start] = num;
    start++;

    while (start < end) {
      int temp = data[start];
      if (data[start] == num) {
        data[start] = data[mIndex];
        data[mIndex] = temp;
        mIndex++;
        start++;
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

    int i = 0;
    while (i < mIndex) {
      int index = lastIndexOf(data, mIndex+i, num);
      int temp = data[index];
      data[index] = data[i];
      data[i] = temp;
      i++;
    }

    return indexOf(data, num);
  }

  public static void main (String[] args) {
    int [] A = new int [100];
    for (int i = 0; i < 100; i++) {
      A[i] = 7;
    }
    //int[] A = new int[] {7, 9, 7, 7, 3, 0, 7};
    System.out.println(partition(A, 0, 99));
  }
}
