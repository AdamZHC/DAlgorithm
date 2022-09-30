package review;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

//只有根的树的深度为0——视觉层数减1
public class MLinkedQueue {
    Node front;
    Node rear;
    public MLinkedQueue(){
        List<Integer> list = new ArrayList<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2) -> o1[2] - o2[2]);

    }

    public void add(int element) {
        rear.next = new Node(element);
        rear = rear.next;
    }

    public int poll() {
        int ans = front.data;
        front = front.next;
        return ans;
    }
}
