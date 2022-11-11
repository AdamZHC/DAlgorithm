# @`LeetCode` 236. 二叉树的最近公共祖先

### 代码

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == p || root == q)
            return root;
        TreeNode ll = null;
        if(root.left != null)
            ll = lowestCommonAncestor(root.left, p, q);
        TreeNode rr = null;
        if(root.right != null)
            rr = lowestCommonAncestor(root.right, p, q);
        if(ll != null && rr != null)
            return root;
        if(ll != null)
            return ll;
        if(rr != null)
            return rr;
        return null;
    }
}
```

