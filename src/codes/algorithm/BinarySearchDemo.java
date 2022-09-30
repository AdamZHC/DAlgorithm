package codes.algorithm;

public class BinarySearchDemo {
    //非递归实现 --二分查找不想之前复杂的多递归，就是查找一边（不是两边），不需要递归，两边的话应该用递归
    //少看韩老师的代码吧，因为他的代码比较复杂，不有助于理解，听人家的思路就可以了
    public static void main(String[] args){
        int[] arr= {1,2,3,6,8,34,57,70};
        binarySearch(arr,5);
        String s = "+-*/()";
        int[][] compare = {
            {1, -1, 1, 1, -1, -1},
            {1, -1, 1, 1, -1, -1},
            {1, -1, 1, 1, -1, -1},
            {1, -1, 1, 1, -1, -1},
            {1, -1, 1, 1, -1, -1},
            {1, -1, 1, 1, -1, -1},
        };
        char ch  = '+';
        char cur  = '-';
        int index = s.indexOf(ch);
        int index2 = s.indexOf(cur);
    }
    public static void binarySearch(int[] arr, int target){
        //边写注释边写代码
        //思路：每次找到中间的然后根据大小更新左右边界
        //左右边界
        int left = 0,right = arr.length-1;
        int med = (left + right)/2;
        //标记是否找到
        boolean flag = false;
        while(true){
            //如果等于的话就是退出
            if(target == arr[med]){
                flag = true;
                break;
            }
            //当left == right的时候意思是找到了边界，这时不应该跳出，因为边界也有可能是目的值，但是如果是顺序相反的话，一定是没找到
            if(left > right){
                //没有找到的话，就是说明不需要改变flag的值
                break;
            }
            //不是大于就是小于
            if(target > arr[med]){
                left = med+1;
            }else{
                right = med -1;
            }
            med = (left + right)/2;
        }
        if(flag){
            System.out.println(med);
        }else{
            System.out.println("NULL");
        }
    }
}
