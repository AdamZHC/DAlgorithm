package notes;


interface myQueue {
    public void append(Object obj) throws Exception;

    public Object delete() throws Exception;

    public Object getFront() throws Exception;

    public boolean notEmpty();
}

public class test {
    public static void main(String[] args) throws Exception {
        int[] targetNumbers = {1111, 1110, 0, 1};
        RadixSort aTest = new RadixSort();
        aTest.sort(targetNumbers, 11, 2);
    }
}


class SeqQueue implements myQueue {
    static final int defaultSize = 100;
    int front;
    int rear;
    int count;
    int maxSize;
    Object[] data;

    public SeqQueue() {
        initiate(defaultSize);
    }

    public SeqQueue(int size) {
        initiate(size);
    }

    private void initiate(int size) {
        maxSize = size;
        front = rear = 0;
        count = 0;
        data = new Object[size];
    }

    @Override
    public void append(Object obj) throws Exception {
        if (count > 0 && rear == front) {
            throw new Exception("队列已满");
        }

        data[rear] = obj;
        if (rear + 1 < maxSize) rear++;
        else rear = (rear++) % maxSize;
        this.count++;
    }

    @Override
    public Object delete() throws Exception {
        if (count == 0) {
            throw new Exception("队列已空");
        }

        Object temp = data[front];
        if (front + 1 < maxSize) front++;
        else front = (front++) % maxSize;
        this.count--;
        return temp;
    }

    @Override
    public Object getFront() throws Exception {
        if (count == 0) {
            throw new Exception("队列已空");
        }
        return data[front];
    }

    @Override
    public boolean notEmpty() {
        return count != 0;
    }
}

class RadixSort {
    int numbersAmount;

    public void sort(int[] targetNumbers, int m, int d) throws Exception {
        int DIGIT_NUMBER = m; //数字最高位数为m
        int BUCKET_AMOUNT = d;//基数为d，
        int power = 1;
        numbersAmount = targetNumbers.length;
        SeqQueue[] bucket = new SeqQueue[BUCKET_AMOUNT];
        int[] resultNumbers = new int[numbersAmount];
        for (int everyDigit = 0; everyDigit < DIGIT_NUMBER; everyDigit++) {
            //幂指数
            if (everyDigit == 0) power = 1;
            //逐次递增
            else power *= BUCKET_AMOUNT;
            //初始化桶
            for (int i = 0; i < BUCKET_AMOUNT; i++) {
                bucket[i] = new SeqQueue();
            }
            int targetOrder = 0;
            //获取每一位数，入队
            for (int i = 0; i < numbersAmount; i++) {
                int iDigitValue = targetNumbers[i] / power - (targetNumbers[i] / (power * BUCKET_AMOUNT)) * BUCKET_AMOUNT;
                bucket[iDigitValue].append(targetNumbers[i]);
            }
            //出队
            for (int j = 0; j < BUCKET_AMOUNT; j++) {

                while (bucket[j].notEmpty()) {
                    resultNumbers[targetOrder] = (int) bucket[j].getFront();
                    bucket[j].delete();
                    targetOrder++;
                }
            }
            for (int i = 0; i < numbersAmount; i++) {
                targetNumbers[i] = resultNumbers[i];
            }
        }
        for (int i = 0; i < numbersAmount; i++) {
            System.out.println(targetNumbers[i]);
        }
    }
}