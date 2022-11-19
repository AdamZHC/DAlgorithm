# @`LeetCode`617. 合并二叉树

```java
/**
 * Definition for a binary tree node.
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
class Solution {
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if(root1 == null)
            return root2;
        if(root2 == null)
            return root1;
        int v1 = root1 == null ? 0 : root1.val;
        int v2 = root2 == null ? 0 : root2.val;
        TreeNode res = new TreeNode(v1 + v2);
        if(root1.left != null || root2.left != null)
            res.left = mergeTrees(root1.left, root2.left);
        if(root1.right != null || root2.right != null)
            res.right = mergeTrees(root1.right, root2.right);
        return res;
    }
}
```

