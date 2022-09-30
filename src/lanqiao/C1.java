package lanqiao;

import java.text.SimpleDateFormat;
import java.util.*;

public class C1 {
    public static void main(String[] args) {
        Calendar cr = new GregorianCalendar();
        cr.set(Calendar.YEAR, 1900);
        cr.set(Calendar.MONTH, 0);
        cr.set(Calendar.DAY_OF_MONTH, 1);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        System.out.println(sdf.format(cr.getTime()));
        String s = "";
        int ans = 0;
        while(!s.equals("99991231")) {
            cr.add(Calendar.DATE, 1);
            s = sdf.format(cr.getTime());
            if(s.indexOf('2') != -1)
                ans++;
        }
        Map<Integer, Integer> m = new HashMap<>();
        m.keySet();
        System.out.println(ans);
    }
}