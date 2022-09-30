# TopK问题

#### 	问题：从一组数据中找到前K大（小）的元素:

##### 			解法一：

​			使用堆去维护前k大或小的元素，时间复杂度O(nlogK)

```java
{
    int nums[] = {...};
    //初始化优先队列
    PrioirtyQueue<Integer> p = new PrioirtyQueue<>(new Comparator<Integer>(){
		@Override
        public int compare(Integer o1, Integer o2){
            return o1 - o2;
        }
    });
    //假设这就是前K大的数
    for(int i = 0; i < k ; i ++) {
        p.add(nums[i]);
	}
    for(int i = k; i < nums.length; i ++){
        if(nums[i] > p.peek()){
            p.remove();
            p.add(nums[i])
		}
	}
    return p.peek();
}
```

##### 	 	解法二：

​		快速排序的思路：

​	本来的快速排序需要分治递归两边都要及快速排序，此时由于K是固定的，并且只找到

K即可，则进行减治法，类似于二分查找

```java
{
    int[] nums = {...};
    //下面是二分的思路
    int begin = 0, end = nums.length - 1;
    while(){
        int i = begin, j = end;
        int edge = nums[begin];
        //这就是找到一次基准位置
        while(true){
            //快排的思路就是这样的：并且先是两次循环，之后交换
            while(nums[j] >= edge && j > i){
                j --;
            }
            while(nums[i] <= edge && i < j){
                i ++;
            }
            if(i < j){
                //位运算交换
                nums[i] = nums[i] ^ nums[j];
                nums[j] = nums[i] ^ nums[j];
                nums[i] = nums[i] ^ nums[j];
            }
        }
        nums[begin] = nums[begin] ^ nums[i];
        nums[i] = nums[begin] ^ nums[i];
        nums[begin] = nums[begin] ^ nums[i];
        //改变基准位置
        //由于快排的特点，这样使得k必定在前面
        //使用二分的思路
        if(i == k) return nums[i];
        if(i > k){
            end = i - 1;
		}else{
            begin = i + 1
        }
	}
}
```