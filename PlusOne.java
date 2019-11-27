public class PlusOne {

    public int[] plusOne(int[] digits) {
        int plusOneIndex = digits.length - 1;

        for (int i = digits.length-1; i >= 0; i--) {
            if (i == plusOneIndex) {
                if (digits[i]+1 > 9) {
                    plusOneIndex--;
                    digits[i] = 0;
                } else {
                    digits[i] = digits[i] + 1;
                }
            }
        }

        // 如果首位需进位
        if (plusOneIndex < 0) {
            int[] newArray = new int[digits.length+1];
            newArray[0] = 1;
            newArray[1] = 0;
            for (int i = 1; i < digits.length; i++) {
                newArray[i+1] = digits[i];
            }
            digits = newArray;
        }

        return digits;
    }
}
