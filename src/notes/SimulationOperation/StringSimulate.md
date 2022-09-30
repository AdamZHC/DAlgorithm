# @字符串模拟运算

## 题目：

​		输入数字 `n`，按顺序打印出从 1 到最大的 n 位十进制数。比如输入 3，则打印出 1、2、3 一直到最大的 3 位数 999。 

​		<1>常规思路：利用原生的数组打印

​		<2>**正确思路：字符串模拟**

​				在字符串上模拟运算，每次开始运算的时候，指针从最后一位开始运算，如果对应的是字符'0' ~ '8'此时直接在原来的基础上加1，如果是'9'的话，则该位变为'0'，往前一位重复操作，如果指针指向 -1则直接在前面新加一位数1，

​		//核心循环语句

```java
for(int i = s.length() - 1; ; -- i ){
    //如果到了-1的时候
    if(i == -1){
      	s.insert(0, '1');
        break;
    } 
    char ch = s.charAt(i);
    //如果是0~8的话
    if(ch != '9'){
        s.setCharAt(i, ch + 1);
        break;
	}
    //如果是9的话
    s.setCharAt(i, '0');
}
```


