# @`LeetCode`101. 对称二叉树

### 思路

还是不简单的，感觉**不是**简单题，主要是分清**遍历顺序**

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
	public boolean isSymmetric(TreeNode root) {
		if(root==null) {
			return true;
		}
		return dfs(root.left,root.right);
	}
	
	boolean dfs(TreeNode left, TreeNode right) {
		if(left==null && right==null) {
			return true;
		}
		if(left==null || right==null) {
			return false;
		}
		if(left.val!=right.val) {
			return false;
		}
		return dfs(left.left,right.right) && dfs(left.right,right.left);
	}
}
```

