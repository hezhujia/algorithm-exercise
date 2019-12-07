package sort;

import java.util.Arrays;

public class BobbleSort implements Sort {

    @Override
    public int[] sort(int[] arrays) {

        for (int i = arrays.length-1; i >= 0; i--) {
            boolean isExchanged = false;
            for (int j = 0; j < i; j++) {
                if (arrays[j] > arrays[j+1]) {
                    int temp = arrays[j];
                    arrays[j] = arrays[j+1];
                    arrays[j+1] = temp;
                    isExchanged = true;
                }
            }
            if (!isExchanged) {
                break;
            }
        }
        return arrays;
    }

    public static void main(String[] args) {
        BobbleSort bobbleSort = new BobbleSort();
        int[] arrays = {5, 2, 1, 10, 60, 0};
        System.out.println(Arrays.toString(bobbleSort.sort(arrays)));
    }
    
}
