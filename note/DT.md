# @分治

关于分治的递归的关系，根据题目的不同其实差别还是有的，比如说表达式的选择，运算符的选择这里就是涉及到分治算法，但是回溯算法就是容易重复，所以考虑的问题比较多，需要处理的问题比较多

## 回溯

```java
class Solution {
    //这道题用的是分治，这里和回溯还是有些区别的！！！
    //咱也不知道有啥区别，但是就是写不出来了
    List<Integer> ans = new ArrayList<>();
    public List<Integer> diffWaysToCompute(String expression) {
        //还是典型的dfs——只不过需要对这个字符串频繁的操作(还是用线性表比较较好)
        //刚才尝试了一下确实LinkedList的删除效率非常地高 
        int n = expression.length();
        List<String> regex = sp(expression);
        boolean[] marks = new boolean[n];
        Arrays.fill(marks, true);
        dfs(regex, marks);
        return ans;
    }
    //这里需要一个marks标记正负号才能实现对应加减
    void dfs(List<String> regex, boolean[] marks) {
        //这里一般就是用LinkedList这样实现地效率比较高
        // System.out.println(Arrays.toString(marks));
        int n = regex.size();
        if(regex.size() == 1) {
            int num = Integer.parseInt(regex.get(0));
            ans.add(marks[0] ? num : -num);
            return;
        }
        for(int i = 0; i < n; ++i) {
            //这里的判断条件就是是否为数字
            while(i < n && isNumber(regex.get(i).charAt(0)))
                ++i;
            if(i >= n) break;
            int num = 0;
            int a = Integer.parseInt(regex.get(i - 1)); a = marks[i - 1] ? a : -a;
            int b = Integer.parseInt(regex.get(i + 1)); b = marks[i + 1] ? b : -b;
            if(regex.get(i).equals("*")) {
                num = a * b;
            } else if(regex.get(i).equals("+")) {
                num = a + b;
            } else {
                num = a - b;
            }
            System.out.printf("a:%d b:%d num:%d\n", a, b, num);
            List<String> newRefex = new LinkedList<>();
            boolean[] newMarks = new boolean[n - 2];
            int idx = 0;
            for(int j = 0; j < i - 1; ++j) {
                newRefex.add(regex.get(j));
                newMarks[idx++] = marks[j];
            } 
            newRefex.add(Math.abs(num) + "");
            newMarks[idx++] = num > 0;
            for(int j = i + 2; j < n; ++j) {
                newRefex.add(regex.get(j));
                newMarks[idx++] = marks[j];
            }
            dfs(newRefex, newMarks);
        }
    }
    //获取对应的处理list
    List<String> sp (String expression) {
        List<String> ss = new LinkedList<>();
        int n = expression.length();
        int i = 0;
        while(i < n) {
            String num = "";
            while(i < n && isNumber(expression.charAt(i))) {
                num += expression.charAt(i++);
            }
            ss.add(num);
            if(i < n)
                ss.add(expression.charAt(i++) + "");
        }
        return ss;
    }
    //这个也需要对于是否为number_string的关系来处理
    boolean isNumber(char ch) {
        return ch >= '0' && ch <= '9';
    }
}
```

## 分治(涉及到记忆化搜索技巧)

```java
class Solution{
        public List<Integer> diffWaysToCompute(String expression) {
        return divideAndConquer(expression.toCharArray());
    }

    public static List<Integer> divideAndConquer(char[] expression){
        List<Integer> res = new ArrayList<>();
        //处理一个数字的情况也就是分治划分到最底层的时候
        //isOneNum函数用来判断当前的表达式是否为一个单独的数字
        if (isOneNum(expression)){
            int num = 0;
            //将该数字从char数组转换为一个int型数值
             for (int i=0;i<expression.length;i++){
                 num = num*10 +expression[i]-'0';
             }
             res.add(num);
            return res;
        }

        for (int i=0;i<expression.length;i++){
            if (!Character.isDigit(expression[i])){
                char[] left = new char[i];
                char[] right = new char[expression.length-i-1];
                //切分左右分治所使用的表达式数组
                System.arraycopy(expression,0,left,0,i);
                System.arraycopy(expression,i+1,right,0,expression.length-i-1);
                //对左边的表达式在进行一次同样的操作
                List<Integer> leftList = divideAndConquer(left);
                //对右边的表达式在进行一次同样的操作
                List<Integer> rightList = divideAndConquer(right);
                //计算左右两个表达式在当前用来切分的运算符进行运算后得到的所有可能的结果
                List<Integer> tempRes = calculate(leftList,rightList,expression[i]);
                //将这些结果加入最后的列表中作为这一层分治的最终结果
                for (Integer num:tempRes){
                    res.add(num);
                }
            }
        }
        return res;
    }
//calculate函数用来对两个列表的数值逐个进行计算
    public static List<Integer> calculate(List<Integer> listOne,List<Integer> listTwo,char operator){
        List<Integer> res = new ArrayList<>();
        for (int i=0;i<listOne.size();i++){
            for (int j=0;j<listTwo.size();j++){
                res.add(calculateTwoNums(listOne.get(i),listTwo.get(j),operator));
            }
        }
        return res;
    }
    //简单的计算函数
    public static Integer calculateTwoNums(int num1,int num2,char operator){
        switch(operator) {
            case '+':
                return num1+num2;
            case '-':
                return num1-num2;
            default:
                return num1*num2;
        }
    }
//判断是否当前表达式是否为一个数字
    public static boolean isOneNum(char[] expression){
        for (int i=0;i<expression.length;i++){
            if (!Character.isDigit(expression[i]))
                return false;
        }
        return true;
    }
}
```

