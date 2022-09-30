package leetcode;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TestDemo {
    public static void main(String[] args){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateNow = sdf.format(new Date());
        System.out.println(dateNow);
        Map<Integer,Integer> map = new HashMap<Integer,Integer>();
        map.put(1,3);
        map.put(2,3);
        System.out.println(map.size());
    }
}
