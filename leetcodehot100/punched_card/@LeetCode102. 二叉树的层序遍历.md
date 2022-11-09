# @`LeetCode`102. 二叉树的层序遍历

### 思路

经典遍历，没啥好说的，控制每层就是通过**当前队列里面的元素**来控制就可以

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
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if(root == null)    
            return ans;
        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        while(!q.isEmpty()) {
            int s = q.size();
            List<Integer> cc = new ArrayList<>();
            for(int i = 0; i < s; ++i) {
                TreeNode p = q.poll();
                cc.add(p.val);
                if(p.left != null) 
                    q.offer(p.left);
                if(p.right != null)
                    q.offer(p.right);
            }
            ans.add(cc);
        }
        return ans;
    }
}
```



