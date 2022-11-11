# @`LeetCode`297. 二叉树的序列化与反序列化

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
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root == null)
            return "()";
        String res = "" + root.val;
        res = res + serialize(root.left);
        res = res + serialize(root.right);
        return "(" + res + ")";
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data.equals("()"))
            return null;
        char[] chs = data.toCharArray();
        int s = 0, n = chs.length, tt = 0, ti = 0, v = 0;
        boolean f = false;
        for(int i = 0; i < n; ++i) {
            if(chs[i] == '(')
                s++;
            if(chs[i] == ')')
                s--;

            if(s == 2 && !f) {
                tt = i;
                v = Integer.parseInt(data.substring(1, i));
            }
            
                
            if(s == 1 && f) {
                ti = i;
                break;
            }
            if(s == 2)
                f = true;
        }
        TreeNode root = new TreeNode(v);
        root.left = deserialize(data.substring(tt, ti + 1));
        root.right = deserialize(data.substring(ti + 1, n - 1));
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));
```

