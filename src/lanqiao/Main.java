package lanqiao;


import java.text.SimpleDateFormat;
import java.util.*;

class Main {
    static Scanner in = new Scanner(System.in);
    static String date1, date2;
    public static void main(String[] args) {
        read();

        Calendar cr1 = new GregorianCalendar();
        cr1.set(Calendar.YEAR, Integer.parseInt(date1.substring(0, 4)));
        cr1.set(Calendar.MONTH, Integer.parseInt(date1.charAt(4) == '0' ? date1.substring(5, 6) : date1.substring(4, 6)) - 1);
        cr1.set(Calendar.DAY_OF_MONTH, Integer.parseInt(date1.charAt(6) == '0' ? date1.substring(7, 8) : date1.substring(6, 8)));

        Calendar cr2 = new GregorianCalendar();
        cr2.set(Calendar.YEAR, Integer.parseInt(date2.substring(0, 4)));
        cr2.set(Calendar.MONTH, Integer.parseInt(date2.charAt(4) == '0' ? date2.substring(5, 6) : date1.substring(4, 6)) - 1);
        cr2.set(Calendar.DAY_OF_MONTH, Integer.parseInt(date2.charAt(6) == '0' ? date2.substring(7, 8) : date1.substring(6, 8)));

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        long ans = 0;
        while(!date1.equals(date2)) {
            cr1.add(Calendar.DATE, 1);
            date1 = sdf.format(cr1.getTime());
            if(check(date1))
                ans++;
        }
        System.out.println(ans);

    }
    static boolean check(String s) {
        for(int i = 0; i <= 5; ++i)
            if(s.charAt(i) == s.charAt(i + 1) && s.charAt(i + 2) == s.charAt(i + 1))
                return true;
        return false;
    }
    static void read() {
        date1 = in.nextLine();
        date2 = in.nextLine();
    }
}