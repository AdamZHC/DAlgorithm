import java.math.BigInteger;
import java.util.*;

public class TestCaseCreator {
    static Random r = new Random();
    public static void main(String[] args) {
        char[] chs = new char[8];
        for(int i = 0; i < chs.length; ++i)
            chs[i] = (char)(i + 91);
        Arrays.sort(chs);
        System.out.println();
    }
}