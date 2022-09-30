package leetcode;

public class BitOperation {
    public static void main(String[] args) {
        int res = pow(13);

    }
    static int pow(int n){
        int sum = 1;
        int tmp = 2;
        while(n != 0){
            if( (n & 1) == 1){
                sum *= tmp;
            }
            tmp *= tmp;
            n = n >> 1;
        }

        return sum;
    }
}
