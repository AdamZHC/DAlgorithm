package notes;

import java.util.*;

public class Collections {
    public static void main(String[] args) {
        //本来想使用新的数据结构，奈何这个数据结构太水了，不支持在遍历中改变该数据结构，
        //所以还是使用优先队列 + 列表集合，从而实现的对应的功能吧，我觉得好像也可以啊，不过需要
        //每次获得迭代器以后，只实现用到上面第一个就不需要用到循环了，这样似乎也可以，而且就不需要写优先队列的比较器
        //还能直接实现String在里面存，虽然空间复杂度比较高，但是比较直观
        Set<Integer> s1 = new HashSet<>();
        Set<Integer> s2 = new HashSet<>();
        s1.add(1);s1.add(2);s1.add(4);s1.add(5);
        s2.add(1);s2.add(4);s2.add(5);
        System.out.println(s1.equals(s2));
    }
    static Collections create() {
        return new Collections();
    }
}
