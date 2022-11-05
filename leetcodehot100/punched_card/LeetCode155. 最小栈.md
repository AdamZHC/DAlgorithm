# @`LeetCode`155. 最小栈

### 思路

用的是最**暴力**的做法，感觉就是调`api`没啥**技术含量**

就是用一个`TreeMap`去**存对应的值**和`cnt`，然后按照`map`的思路去**修改**就行

最优解就是存**当前值**的最小值，用**辅助栈**

```java
class MinStack {
    Stack<Integer> st;
    TreeMap<Integer, Integer> map; 
    public MinStack() {
        st = new Stack<>();
        map = new TreeMap<>();
    } 
    
    public void push(int val) {
        st.push(val);
        map.put(val, map.getOrDefault(val, 0) + 1);
    }
    
    public void pop() {
        int kk = st.pop();
        int cnt = map.get(kk);
        if(cnt == 1) {
            map.remove(kk);
        } else {
            map.put(kk, cnt - 1);
        }
    }
    
    public int top() {
        return st.peek();
    }
    
    public int getMin() {
        return map.firstKey(); 
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
```

