package review;

public class Traverse {
    static int totalLevel = 4;
    public static void main(String[] args) {
        int[][] tree = new int[7][2];
        tree[0][0] = 1;
        tree[0][1] = 2;
        tree[1][0] = 3;
        tree[1][1] = 0x7fffffff;
        tree[2][0] = 4;
        tree[2][1] = 5;
        tree[3][0] = 0x7fffffff;
        tree[3][1] = 6;
        tree[4][0] = 6;
    }
    public void traverse(int[][] tree, int i, int level) {
        if(tree[i][0] != 0x7fffffff) {
            traverse(tree, tree[i][0], level + 1);
        }
        for(int j = 0; j < totalLevel - level; ++j) {
            System.out.print("    ");
        }
        System.out.printf("%d---",i);
        if(tree[i][1] != 0x7fffffff) {
            traverse(tree, tree[i][1], level + 1);
        }
    }
}
