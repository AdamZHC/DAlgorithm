package template.线段树;

class KMP{
	//获取对应的next数组
    static int match(String s, String part) {
        int n = s.length();
        int m = part.length();
        int[] next = getNext(part);
        int i = 0, j = 0;
        while(i < n && j < m) {
            if(j == -1 || s.charAt(i) == part.charAt(j)) {
                ++i;
                ++j;
            } else 
                j = next[j];
        }
        if(j == m)
            return i - m;
        else
            return -1;
    }
    static int[] getNext(String ps) {
        char[] p = ps.toCharArray();
        int[] next = new int[p.length];
        next[0] = -1;
        int j = 0;
        int k = -1;
        while (j < p.length - 1) {
            if (k == -1 || p[j] == p[k]) {
                if (p[++j] == p[++k]) { // 当两个字符相等时要跳过
                    next[j] = next[k];
                } else {
                    next[j] = k;
                }
            } else {
                k = next[k];
            }
        }
        return next;
    }
}
	