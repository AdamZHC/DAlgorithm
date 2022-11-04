# @`LeetCode`146. `LRU` 缓存

### 思路

作为经典的**调度算法**，之前没有考虑过`LRU`是怎么实现的，基本也是不看题解就不会做，因为时间复杂度要求为`O(1)`

核心思路就是**哈希表** + **双向链表** 有趣的是`java`里面有一个封装好的**数据结构**叫做`LinkedHashMap`正好是为了实现`LRU`缓存而设计的

基本操作就是每次`get/put`的时候把命中的元素放到**链表头部**，每次**检查**，如果发现此时超过了要求的容量的话，那就把**链表尾部元素逐出**

另外在设计的过程中最好还是加上`head tail`**辅助结点** 然后需要多熟悉**双向链表**的操作，相较于**单链表**稍复杂

```java
class LRUCache {
    int cc;
    ListNode head = new ListNode(), tail = new ListNode();
    Map<Integer, ListNode> map;
    public LRUCache(int capacity) {
        this.map = new HashMap<>();
        this.cc = capacity;
        init();
    }
    void init() {
        head.last = tail;
        tail.next = head;
    }
    
    public int get(int key) {
        ListNode nn = map.get(key);
        if(nn != null) {
            remove(nn);
            offer(nn);
            // System.out.println(map);
            return nn.val;
        }
        // System.out.println(map);
        return -1;
    }
    
    public void put(int key, int value) {
        ListNode nn = map.get(key);
        if(nn != null) {
            nn.val = value;
            remove(nn);
            offer(nn);
            return;
        }
        if(map.size() == cc)
            poll();
        ListNode vv = new ListNode();
        vv.key = key;
        vv.val = value;
        offer(vv);
        map.put(key, vv);
        // System.out.println(map);
    }

    void poll() {
        int key = tail.next.key;
        map.remove(key);
        tail.next = tail.next.next;
        tail.next.last = tail;
    }

    void offer(ListNode nn) {
        nn.last = head.last;
        nn.next = head;
        head.last.next = nn;
        head.last = nn;
    }
    void remove(ListNode nn) {
        nn.last.next = nn.next;
        nn.last.next.last = nn.last;
    }
}
class ListNode {
    int key, val;
    ListNode next, last;

    @Override
    public String toString() {
        return "" + val;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
```

