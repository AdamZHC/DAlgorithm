# @`LeetCode`105. 从前序与中序遍历序列构造二叉树

### 思路

经典题了属于是，通过**左右子树大小**控制就行

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
    int[] pre;
    int[] in;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.pre = preorder;
        this.in = inorder;
        int n = pre.length;
        TreeNode root = new TreeNode();
        build(root, 0, n - 1, 0, n - 1);
        return root;
    }
    void build(TreeNode root, int Lp, int Rp, int Li, int Ri) {
        //找到根节点
        int rpIdx = Lp;
        int riIdx = 0;
        for(int i = Li; i <= Ri; ++i)
            if(in[i] == pre[rpIdx]) {
               riIdx = i;
               break; 
            }
        int llen = riIdx - Li;
        int rlen = Ri - riIdx;
        root.val = pre[Lp];
        if(llen > 0) {
            root.left = new TreeNode();
            build(root.left, Lp + 1, Lp + llen, Li, Li + llen - 1);
        }
        if(rlen > 0) {
            root.right = new TreeNode();
            build(root.right, Lp + llen + 1, Rp, Li + llen + 1, Ri);
        }
    }

}
```

