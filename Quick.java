import java.util.Random;

public class Quick{

  public static String toString(int [] data) {
    String display = "";
    for (int i = 0; i < data.length; i++) {
      display += data[i] + " ";
    }
    return display;
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

    System.out.println(num);
    System.out.println(toString(data));

    return index;
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
