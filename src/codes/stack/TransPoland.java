package codes.stack;

import java.util.HashMap;

import java.util.Stack;

public class TransPoland {
    /*中缀表达式转后缀表达式思路：
    1.初始化两个栈操作数栈，操作符栈
    2.从左到右扫描字符串（只讨论10以内的加减乘除），多位则使用正则表达式
    3.  (1)如果是数字直接入操作数栈
        (2)如果是操作符的话：
            1)如果是操作符栈是空的话，直接入栈
            2)如果是操作符优先级大于栈顶元素直接入栈
            3)如果操作符优先级小于等于栈顶元素的话，将该栈顶元素加入操作数栈，直到该操作符大于栈顶元素
        (3)如果括号的话：
            1)左括号直接压入
            2)右括号一直出操作符栈，直到遇到左括号，把该两括号的元素同时出栈
    4.最终倒序就是对应的逆波兰表达式
    */
    static HashMap<Character,Integer> map = new HashMap <Character,Integer>() ;
    public static void initial(){
        map.put('*',1); map.put('+',0); map.put('-',0); map.put('/',1);
        map.put('(',-1);
    }
    public static boolean compare(char a, char b){
        return map.get(a) > map.get(b);
    }
    public static void main(String[] args){
        //初始化
        initial();
        //初始化两个栈
        Stack <Character> num = new Stack<>();
        Stack <Character> operate = new Stack<>();
        //设置一个中缀表达式2*(3+7)+7
        String exp = "1+((2+3)*4)-5";
        //注释的判断添加方法可以在判断方法前添加，看得清楚，但是没必要过度要求
        for(int i = 0 ;i <exp.length() ; i++ ){
            //判断字符的类型
            char ch = exp.charAt(i);
            //注意此时字符直接比大小
            //如果是数字类型--直接入栈
            if (ch>='0' && ch<='9'){
                num.push(ch);
            //如果是操作符类型
            }else if (ch == '+'||ch == '-'||ch == '*'||ch == '/'){
                //判空直接入栈
                if(operate.isEmpty()){
                    operate.push(ch);
                //如果运算符优先级比栈顶元素大，直接入栈
                }else if(compare(ch, operate.peek())){
                    operate.push(ch);
                //如果运算符优先级比栈顶元素小，需要循环一直把操作符栈得元素压入操作数栈
                }else{
                    while(!operate.isEmpty() && !compare(ch,operate.peek())){
                        num.push(operate.pop());
                    }
                    //判断完之后，这时把对应的操作符压入操作符栈
                    operate.push(ch);
                }
            //如果是括号类型
            }else{
                //如果是左括号
                if(ch=='('){
                    operate.push(ch);
                //如果是右括号，需要把在遇到下一个左括号之前，把所有的操作符出栈到入栈操作数，最后遇到左括号一起出栈，括号是不会到后缀表达式里的
                }else{
                    while(operate.peek()!='('){
                        num.push(operate.pop());
                    }
                    operate.pop();
                }
            }
        }
        //最终输出对应的字符串
        //操作符栈剩余的元素压入操作数栈

        while(!operate.isEmpty()){
            num.push(operate.pop());
        }
        while (!num.isEmpty()){
            System.out.print(""+num.pop());
        }
    }
    //没有指针的数据类型，所以使用的就是栈本身的内存
    //向跳出递归的条件逼近，跳出的条件就是方法体内不执行本方法的代码块
    //可以结合在抽象数据类型开辟堆空间的特性，有可能每一个方法栈中都是操作同一个堆内存的空间

}
