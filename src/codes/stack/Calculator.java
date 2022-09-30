package codes.stack;

import java.util.HashMap;

public class Calculator {
    //哈希映射的声明
    static  HashMap <Character,Integer> map = new HashMap <Character,Integer>() ;
    public static void initial(){
        map.put('*',1); map.put('+',0); map.put('-',0);
    }
    public static boolean compare(char a, char b){
        return map.get(a) > map.get(b);
    }
    public static void main(String args[]){
        //强调封装的思想，强调注释清楚
        //字符串直接拼接
        //初始化比较哈希映射
        initial();
        //初始化两个栈，操作数栈和操作符栈
        Stack num = new Stack(10);
        Stack ore = new Stack(10);
        //扫描字符串入栈
        String e = "3-7*9*5*2*8-1";
        for(int i=0; i<e.length();i++){
            //比较的时候'7'>'9',但是'9'!=(char)9
            //比较从字符串中获取的字符时，比较标准应该直接是'0' '9'，而不是(char)9
            //因为'9'的ASCII码和9不是一致的,0的ASCII是48,所以说(char)48=='0'
            //因此记住一点，比较从字符串中获取的数字字符时，直接比较相应的数字字符即可
            if( e.charAt(i)<= '9'&& e.charAt(i)>='0' ){
                num.push((int)e.charAt(i)-48);
            }else{
                //比较当前栈顶位置的操作符优先级和待入栈的操作符优先级
                if(ore.getTop()==0){
                        //第一次加直接加，啥也不管直接加
                }else {//注意需要判断多次，后几次添加，一直要到可以添加为止
                    //如果当前运算符优先级大于栈顶元素，当前运算符入栈
                    //如果当前运算符优先级小于等于栈顶元素，栈顶元素出栈，需要把操作数两个出栈，再进行运算，当前运算符入栈再比较
                    //直到当前的运算符大于当前的栈顶元素，这时就会入栈
                    while (ore.top!=-1 && !compare(e.charAt(i), (char) ore.getTop())) {
                        int a = num.pop();
                        int b = num.pop();
                        //就是出栈的时候顺便维护另一个栈即可
                        if ((char) ore.getTop() == '+') {
                            num.push(a + b);
                        } else if ((char) ore.getTop() == '-') {
                            num.push(b - a);
                        } else if ((char) ore.getTop() == '*') {
                            num.push(a * b);
                        }
                        //把该部分比较完毕，之后操作符出栈
                        ore.pop();
                    }

                }
                ore.push((int)e.charAt(i));
            }

            //只可能出现一次，比如说*号在中间的情况，两边是同级的符号，因为前面肯定都是正确的顺序，所以不需要考虑

        }
        //入栈结束
        //此时两端出栈
        while(num.top!=0){
            int a = num.pop();
            int b = num.pop();
            if((char)ore.getTop()=='+'){
                num.push(a+b);
            }else if((char)ore.getTop()=='-'){
                num.push(b-a);
            }else if((char)ore.getTop()=='*'){
                num.push(a*b);
            }
            ore.pop();
        }
        System.out.println(""+num.getTop());
    }
}
//class Stack{
//    private int maxSize;
//    private int top;
//    private int [] arr;
//    public Stack(int maxSize){
//        this.maxSize = maxSize;//方便索引
//        top = -1;//栈顶指针初始化时赋为-1
//        arr = new int[maxSize];//创建对应的栈实体
//    }
//    public void push(int num){
//        if(top == maxSize-1){
//            System.out.println("栈满");
//            return;
//        }
//        top++;//栈顶指针上移
//        arr[top] = num;//栈实体赋值
//    }
//    public int pop(){
//        if(top==-1){
//            System.out.println("栈空");
//        }
//        top--;
//        return arr[top+1];
//    }
//    public int getPop(){
//        if(top == -1){
//            return 0;
//        }
//        return arr[top];
//    }
//}
//子类默认会有内些变量，所以需要在子类方法中先调用父类的构造方法实现
//初始化父类中的变量
