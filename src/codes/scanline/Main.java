package codes.scanline;

public class Main {

    public static void main(String[] args) {
        int[] arr = {1,3,5,7,9,11};
        SegmentTree segmentTree =  new SegmentTree(arr);
        segmentTree.update(0,5,0, 4);
        segmentTree.update(0,3,0, 1);
        System.out.println(segmentTree.query(0, 1, 0));
        segmentTree.detail();
        StringBuilder s = new StringBuilder(10);

    }
}
