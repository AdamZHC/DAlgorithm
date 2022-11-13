# @`LeetCode`98. 验证二叉搜索树

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
    List<Integer> ll = new ArrayList<>();
    public boolean isValidBST(TreeNode root) {
        dfs(root);
        for(int i = 1; i < ll.size(); ++i)
            if(ll.get(i) <= ll.get(i - 1))
                return false;
        return true;
    }
    void dfs(TreeNode root) {
        if(root.left != null) 
            dfs(root.left);
        ll.add(root.val);
        if(root.right != null)
            dfs(root.right);
    }
}
```

