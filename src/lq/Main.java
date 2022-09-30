package lq;

import java.math.BigInteger;
import java.util.*;
class Main {
    public static void main(String[] args) {
        TreeSet<Integer> set = new TreeSet<>();
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(5);
        set.add(6);
        System.out.println(set.tailSet(4).size());
    }
}