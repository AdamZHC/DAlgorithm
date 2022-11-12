# @`LeetCode`437. 路径总和 III

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
    public int pathSum(TreeNode root, int targetSum) {
        if(root == null)
            return 0;
        int ans = rootSum(root, targetSum);
        
        if(root.left != null) 
            ans += pathSum(root.left, targetSum);        
        
        if(root.right != null) 
            ans += pathSum(root.right, targetSum);
        
        return ans;
    }
    int rootSum(TreeNode root, long targetSum) {
        if(root == null)
            return 0;
        int ans = targetSum == root.val ? 1 : 0;
        if(root.left != null) 
            ans += rootSum(root.left, targetSum - root.val);
        
        if(root.right != null) 
            ans += rootSum(root.right, targetSum - root.val);
        return ans;
    }
}
```

