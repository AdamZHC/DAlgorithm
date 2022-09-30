package leetcode;

import java.util.HashMap;
import java.util.Map;

public class StringMatch {
    static Map<Character,Integer> map = new HashMap<Character,Integer>();
    public static void main(String[] args){
        checkInclusion("adc","dcda");
    }
    public static boolean checkInclusion(String s1, String s2) {
        //用哈希表来存，我觉得就是暴力匹配的剪枝
        //首先拿到这个s1串生成一个对应的哈希映射
        //另外一个指向s2，似乎不需要第一个指针，前面一个就是生成一个对应的哈希映射
        //s2指针移动一直到匹配到s1的字符---> 准备开始正式匹配
        //首先s3指针指向s2对应的下标-长度的位置
        //一直移动直到匹配到hashmap得到的不为零开始匹配 --->完全匹配
        //  有这样的规则：如果匹配到大于1的话，那就移除，如果匹配到1的话
        //  那就移除，如果还未完全匹配就遇到null的话退出s2指针++
        //1.初始化HashMap
        init(s1);
        for(int origin = 0 ;origin < s2.length(); origin++){
            char ch = s2.charAt(origin);
            if(map.get(ch) != null){
                //开始正式匹配
                for(int match = 0;match < s2.length(); match++){
                    //这里应该是从正式匹配的这里开始算起索引，不然每次都是第一个
                    if(match+origin >= s2.length()){
                        return false;
                    }
                    char key = s2.charAt(match+origin);
                    //如果没有的话，直接跳出
                    if(map.get(key) == null){
                        break;
                    }
                    //如果有的话就这样处理
                    if(map.get(key) == 1){
                        map.remove(key);
                    }else{
                        map.put(key,map.get(key)-1);
                    }
                }
                if(map.size() == 0){
                    return true;
                }else{
                    init(s1);
                }
            }
        }
        return false;
    }
    public static void init(String s1){
        map.clear();
        for(int i = 0 ; i < s1.length(); i++){
            char key = s1.charAt(i);
            map.put(key, (map.get(key) == null ? 1 : map.get(key)+1));
        }

    }
        //有个大致思路，但是具体步骤具体写
}
