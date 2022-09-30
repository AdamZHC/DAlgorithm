package homework;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        /**
         * 这里有助于我理解底层的实现方式，首先刚才没有实现的原因在于，没有add就不能get,因为不支持不安全的访问
         * 还有就是如果在外层只有有声明那么只有一个引用变量在开辟，上面的地址值为null,但是注意，本身变量的空间是开辟的
         * 但是上面的值是Null,声明外层之后，就是相当是在原来引用变量上给定了值，但是这时原来值有了指向，然后指向的那几个里面
         * 开辟了空间，只不过上面的值为null，一般不是copy，能直接访问到对应的引用变量，如果是非引用变量就是copy和引用是等价的
         */
        List<List<Integer>> lists = new ArrayList<>();
        //System.out.println(lists.get(0));
        Integer a = new Integer(1);
        Integer b = new Integer(2);
        new Test().change(a, b);
        System.out.println(a + " " + b);
    }
    //原因就是这里的传参肯定是复制了一个引用变量（但是引用变量的地址是不可见的），这里是实现了地址的赋值，而不是对于地址上
    //的值的访问，所以会和平时的是一样的，也相当于引用变量所在地址的赋值和复制，而不是真正传过去，没有对于地址上的值操作的话
    //实现的是假性操作
    void change(Integer a , Integer b){
        a = b;
    }
}
