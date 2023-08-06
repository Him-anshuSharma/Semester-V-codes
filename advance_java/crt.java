public class crt {
    public static void main(String[] args) {
        int num[] = { 3, 4, 5 };
        int rem[] = { 2, 3, 1 };

        int min = num[0];
        for (int i = 1; i < num.length; i++) {
            if (num[i] < min) {
                min = num[i];
            }
        }
        int i = 2;
        int j = 0;
        for (i = min;; i++) {
            j = 0;
            for (j = 0; j < num.length; j++) {
                if (i < num[j]) {
                    break;
                }
                if (i % num[j] == rem[j]) {
                    continue;
                } else {
                    break;
                }
            }
            if (j == rem.length) {
                System.out.println(i);
                break;
            }
        }
    }
}
