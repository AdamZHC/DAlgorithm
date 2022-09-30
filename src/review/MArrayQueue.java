package review;

public class MArrayQueue {
    //循环队列——通过失去一个空间来使得队列的循环使用
    int MAXSIZE = 1000;
    int[] arr;
    //rear == front表示空
    //因为是分开操作所以不会受到影响
    //front——指向队头有元素的那个位置
    //rear——指向队尾有元素的后一个位置
    //我刚才思维上犯的一个错误就是用数组的思想来标识队列的头尾指针了——应该是虚拟的才对

    //队列的两个指针都是增加的
    int front = 0;
    int rear = 0;
    public MArrayQueue() {
        arr = new int[MAXSIZE];
    }
    public MArrayQueue(int capacity) {
        MAXSIZE = capacity;
        arr = new int[capacity];
    }

    public void add(int element) {
        if((rear + 1) % MAXSIZE == front){
            System.out.println("full");
            return;
        }
        arr[rear = (rear + 1) % MAXSIZE] = element;
    }

    public int poll() {
        if(front == rear){
            System.out.println("empty");
            return 0x7fffffff;
        }
        return arr[front = (front + 1) % MAXSIZE];
    }
}
