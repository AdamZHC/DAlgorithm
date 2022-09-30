package codes.scanline;

public class Node {
    //维护的左边界
    int L;
    //维护的右边界
    int R;
    //存放的数据
    int data;
    //懒标
    int lazyTag;
    public Node(int L, int R, int data, int lazyTag){
        this.data = data;
        this.L = L;
        this.R = R;
        this.lazyTag = lazyTag;
    }
    //结点信息展示
    public void detail(){
        System.out.printf("[%d, %d] > data:%d lazyTag:%d \n", L, R, data, lazyTag);
    }
}
