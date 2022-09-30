package leetcode;

import java.util.ArrayList;
import java.util.List;

public class LeetCode390 {
    public static void main(String[] args) {
        for(int i = 1; i < 100; ++i) {
            print(i);
        }
    }
    public static void print(int n) {
        List<Integer> list = new ArrayList<>(n);
        for(int i = 0; i < n; ++i)
            list.add(i + 1);
        int idx = n;
        while(list.size() > 1) {
            //remove有两种情况index(索引)和Object(对象也即可迭代元素)
            //因为总是隔一个删除，删除之后总能剩下来的
            //删除之后，总可以得到容器大小为1的情况，所以这样的话，判定一下就可以得到最后的结果
            //从头开始删除
            for(int i = 0; i < list.size(); i += 2)
                list.set(i, 0);
            idx = list.indexOf(0);
            while(idx != -1 && list.size() > 1) {
                list.remove(idx);
                idx = list.indexOf(0);
            }
            if(list.size() == 1) break;
            for(int i = list.size() - 1; i >= 0; i -= 2)
                list.set(i, 0);
            idx = list.indexOf(0);
            while(idx != -1 && list.size() > 1) {
                list.remove(idx);
                idx = list.indexOf(0);
            }
            if(list.size() == 1) break;
        }
        System.out.printf("n:%d ans:%d\n", (int)Math.log(n + 1), list.get(0));
    }

}
