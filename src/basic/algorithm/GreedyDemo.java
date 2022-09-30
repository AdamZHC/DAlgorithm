package basic.algorithm;

import java.util.HashMap;
import java.util.Map;

public class GreedyDemo {
    //可以使用哈希映射的优化来解决集合覆盖问题
    //先初始化一个HashMap把所有需要覆盖的元素都存起来
    public static void main(String[] args){
        Map <String,Integer> map = new HashMap<String,Integer>();
        new GreedyDemo().initial(map);
        String[][] K = new String[5][];//行主序，行必须是固定的
        String[] k1 = {"北京","上海","天津"}; K[0] = k1;
        String[] k2 = {"广州","北京","深圳"}; K[1] = k2;
        String[] k3 = {"成都","上海","杭州"}; K[2] = k3;
        String[] k4 = {"上海","天津"}; K[3] = k4;
        String[] k5 = {"杭州","大连"}; K[4] = k5;
//        System.out.println(match(map,K[3]));
        //记录是否使用过该电台
        boolean []isMatch = new boolean[5];
        //从开始匹配
        while(map.size() == 0){
            int maxFlag = 0; //记录最大的那个索引
            for(int i = 0; i< K.length; i++){
                if(match(map,K[i]) > match(map,K[maxFlag]) && isMatch[i] == false ){//如果匹配过了就不能参与匹配
                    maxFlag = i;
                }
            }
            remove(map,K[maxFlag]);
            isMatch[maxFlag] = true;
        }
    }
    //并删除他们
    public static void remove(Map<String,Integer> map,String[] str){
        for(int i = 0 ; i< str.length ; i++){
            if(map.get(str[i]) != null){
                map.remove(str[i]);
            }
        }
    }
    //每一个String[] 在哈希表里面有几个匹配的
    public static int match (Map<String,Integer> map,String[] str){
        int cnt = 0;
        for(int i = 0 ; i< str.length ; i++){
            if(map.get(str[i]) != null){
                cnt++;
            }
        }
        return cnt;
    }
    public void initial(Map<String,Integer> map){
        map.put("北京",1);
        map.put("上海",1);
        map.put("天津",1);
        map.put("广州",1);
        map.put("深圳",1);
        map.put("成都",1);
        map.put("杭州",1);
        map.put("大连",1);
    }
}
