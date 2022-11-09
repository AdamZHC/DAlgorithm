# @`LeetCode`94. 二叉树的中序遍历

### 思路

使用**迭代方法**处理，感觉就是记住这个代码就可以，没有什么**技巧可言**

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
    public List<Integer> inorderTraversal(TreeNode root) {
        //非递归遍历
        Stack<TreeNode> st = new Stack<>();
        TreeNode p = root;
        List<Integer> ans = new ArrayList<>();
        while(!st.isEmpty() || p != null) {
            while(p != null) {
                st.push(p);
                p = p.left;
            }
            if(!st.isEmpty()) {
                p = st.peek();
                ans.add(st.pop().val);
                p = p.right;
            }
        }
        return ans;
    }
}
```

