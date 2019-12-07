package sort;

import java.util.Arrays;
import java.util.Random;

public class SortTest {

    public static void test(Sort sort, int[] testArray) {
        long startTime = System.nanoTime();
        sort.sort(testArray);
        long endTime = System.nanoTime();
        System.out.printf("Sort strategy [%s] cost [%s]ns for array size [%s]\n", sort.getClass().getName(),
                endTime-startTime, testArray.length);
        System.out.printf("Sort strategy [%s] sorting result is:\n [%s]\n", sort.getClass().getName(),
                Arrays.toString(testArray));
    }

    public static int[] createTestArray(int size) {
        Random random = new Random();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt();
        }
        return array;
    }

    public static void main(String[] args) {
        int[] testArray = createTestArray(64);
        test(new BobbleSort(), testArray.clone());
        test(new SelectSort(), testArray.clone());
        test(new InsertSort(), testArray.clone());
    }
}
