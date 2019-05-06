package multiThread;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinClass extends RecursiveTask<Integer> {

    public int[] intArr;

    public ForkJoinClass(int[] src) {
        this.intArr = src;
    }

    @Override
    protected Integer compute() {
        if(intArr.length <= 2) {
            int count = 0;
            for(int i: intArr) {
                count += i;
            }
            return count;
        } else {
            int mid = intArr.length/2;
            ForkJoinClass leftTask = new ForkJoinClass(Arrays.copyOfRange(intArr, 0, mid));
            ForkJoinClass rightTask = new ForkJoinClass(Arrays.copyOfRange(intArr, mid, intArr.length));
            invokeAll(leftTask,rightTask);
            return leftTask.join() + rightTask.join();
        }
    }

    public static void main(String[] args) {
        int[] intArr = new int[10000];
        for(int i = 0; i < intArr.length; i++) {
            intArr[i] = i;
        }
        System.out.println("makeArr size = " + intArr.length);

        ForkJoinClass sumTask = new ForkJoinClass(intArr);
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(sumTask);

        System.out.println("执行结果：" + sumTask.join());
    }
}
