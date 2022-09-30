package leetcode;

public class PassByValueOrReference {
    public static void main(String[] args){
        int a = 1;
        Int b = new Int();
        add(a);
        add(b);
        System.out.println("a:" + a);
        System.out.println("b:" + b.a);
    }
    static void add(Int b) {
        b.a++;
        System.out.println(b);
    }
    static void add(int a) {
        a ++;
        System.out.println(a);
    }

}
class Int {
    int a = 1;
}