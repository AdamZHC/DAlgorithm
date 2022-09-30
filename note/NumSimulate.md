# @模拟

有些问题不需要完全去模拟，直接找规律或者在简单的模拟中寻找规律即可

实际模拟

```java
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
```

简化的模拟

```c++
class Solution {
public:
    int lastRemaining(int n) {
        int a1 = 1, an = n;
        int k = 0, cnt = n, step = 1;
        while (cnt > 1) {
            if (k % 2 == 0) { // 正向
                a1 = a1 + step;
                an = (cnt % 2 == 0) ? an : an - step;
            } else { // 反向
                a1 = (cnt % 2 == 0) ? a1 : a1 + step;
                an = an - step;
            }
            k++;
            cnt = cnt >> 1;
            step = step << 1;
        }
        return a1;
    }
};
```

