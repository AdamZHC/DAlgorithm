# @`LeetCode`114. 二叉树展开为链表

### 思路

就是利用**前序遍历**来重新指向下一个结点，值得注意的是我首先展成`left`连接的单链表，我的考虑是，因为前序遍历过程中**先访问左节点**，**再访问右节点**，因此如果把指针的右节点直接指向下一个结点，会导致原来**右节点**的丢失，所以说先整理成**左节点连接**的链表

### 代码

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
    TreeNode tmp = new TreeNode();
    public void flatten(TreeNode root) {
        if(root == null)
            return;
        dfs(root);
        dfss(root);
    }
    void dfs(TreeNode root) {
        tmp.left = root;
        if(root.left != null) {
            tmp = tmp.left;
            dfs(root.left);
        }
        if(root.right != null) {
            tmp = tmp.left;
            dfs(root.right);
            root.right = null;
        }
    }
    void dfss(TreeNode root) {
        root.right = root.left;
        root.left = null;
        if(root.right != null)
            dfss(root.right);
    }
}
```

