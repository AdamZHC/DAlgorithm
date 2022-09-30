package leetcode;

public class FindSort {
    public static void main(String[] args){

        int[] a = {1,7};
        int[] b = {2,4,8};
        Solution2 s = new Solution2();
        System.out.println(s.findMedianSortedArrays(a,b));
    }

}
class Solution2 {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        //二分法处理
        //思路就是通过二分找到比中位数大的数的个数以及比中位数小的数，直到比中位数大的个数和小的个数都满了之后
        //就可以把剩下的数输出
        //看情况不一定要减一，因为不同的思路是不一样的
        int left1 = 0,right1 = nums1.length-1;
        int left2 = 0,right2 = nums2.length-1;
        int mid1 = (left1 + right1) / 2,mid2 = (left2 + right2) / 2;
        int sum = nums1.length + nums2.length;
        //开始二分查找
        //要有减一
        while(true){
            mid1 = (left1 + right1) / 2;
            mid2 = (left2 + right2) / 2;
            if(nums1[mid1] > nums2[mid2]){
                //换指针
                right1 = mid1;
                mid1 = (left1 + right1) / 2;
                left2 = mid2 ;
                mid2 = (left2 + right2) / 2;
            }else{
                left1 = mid1 ;
                mid1 = (left1 + right1) / 2;
                right2 = mid2 ;
                mid2 = (left2 + right2) / 2;
            }
            if(left1 == right1 || left2 ==  right2 ){
                break;
            }
        }
        System.out.println(left1 == right1 && left2 == right2);
        if(left1 != right1){
            while(left1 != right1){
                if(nums1[mid1] > nums2[mid2]){
                    right1 = mid1 - 1;
                    mid1 = (left1 + right1) / 2;
                }else{
                    left1 = mid1 + 1;
                    mid1 = (left1 + right1) / 2;
                }
            }
        }else if(left2 != right2){
            while(left2 != right2){
                if(nums1[mid1] > nums2[mid2]){
                    left2 = mid2 + 1;
                    mid2 = (left2 + right2) / 2;
                }else{
                    right2 = mid2 - 1;
                    mid2 = (left2 + right2) / 2;
                }
            }
        }
        int num = Math.max( nums1[mid1] , nums2[mid2] );
        if(sum % 2 != 0){
            return num;
        }else{
            if( num == nums1[mid1]) return (num + Math.min(nums1[mid1-1] , nums2[mid2]))/2;
            else return (num + Math.min( nums1[mid1] , nums2[mid2-1]) ) / 2.0;
        }
        // return mid2;
    }
}
//class Solution {
//    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
//        //获取数组长度
//        int sum = nums1.length+ nums2.length;
//        //确定中位数数组大小
//        int len = sum % 2 == 0 ? sum/2 + 1: (sum+1) / 2;
//        int mid[] = new int[len];
//        int i = 0 , j = 0 ,cnt =0;
//        //往新数组中并入
//        while(i < nums1.length && j < nums2.length && nums1.length != 0 && nums2.length != 0){
//            //true为i, false为j
//            // boolean flag = nums1[i] < nums1[j];
//            // mid[cnt++] = flag ? nums1[i]:nums2[j];
//            // i += flag ? 1 : 0;
//            // j += flag ? 1 : 0;
//            //这里少考率一个条件，就是应该是中序数组提前找到，不需要，没有超出范围，
//            //比如说两个很大的数组一样长，那就在中间，不能等到把所有的其中的数组遍历完
//            if( nums1[i] < nums2[j]) mid[cnt++] = nums1[i++];
//            else mid[cnt++] = nums2[j++];
//            if(cnt == len ) return sum % 2 == 0? (mid[len-1]+mid[len-2])/2.0 : mid[len-1];
//        }
//        if(i == nums1.length){
//            while(j < len - nums1.length){
//                mid[cnt++] = nums2[j++];
//            }
//        }else{
//            while(i < len - nums2.length){
//                mid[cnt++] = nums1[i++];
//            }
//        }
//        return sum % 2 == 0? (mid[len-1]+mid[len-2])/2.0 : mid[len-1];
//    }
//}//!!!归并排序的思想一定要加一个额外的数组！！！！，空间换时间，空间换爽！！！
////看题解一，归并排序一定要开辟新的数组，新的数组
////看解法二，是我之前的思路，但是需要很清楚的思路，按我之前的思路就太麻烦了
////人家有很好的优化，但是需要多个指针，要有多个指针跳来跳去，这样才能完成
