package codes.leetcode;

public class TreeDP {
    /**
     * Definition for a binary codes.tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode() {}
     *     TreeNode(int val) { this.val = val; }
     *     TreeNode(int val, TreeNode left, TreeNode right) {
     *         this.val = val;
     *         this.left = left;
     *         this.right = right;
     *     }
     * }
     */
    public int rob(TreeNode root) {
        if(root.left != null){
            rob(root.left);
        }
        if(root.right != null){
            rob(root.right);
        }
        int f = Flayer(root);
        int s = Slayer(root);
        root.val = Math.max(f,s+root.val);
        return  root.val;
    }
    public int Flayer(TreeNode node){
        int leftNum = node.left == null? 0:node.left.val;
        int rightNum = node.right == null? 0:node.right.val;
        return leftNum+rightNum;
    }
    public int Slayer(TreeNode node){
        TreeNode leftNode = node.left;
        int maxLeft = leftNode == null ? 0:Flayer(leftNode);
        TreeNode rightNode = node.right;
        int maxRight = rightNode == null ? 0:Flayer(rightNode);
        return maxLeft + maxRight;
    }

}
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}