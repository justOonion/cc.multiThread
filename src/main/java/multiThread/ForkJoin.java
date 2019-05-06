package multiThread;

import java.util.concurrent.RecursiveTask;

public class ForkJoin extends RecursiveTask<int[]> {

    public int[] intArr;

    public void makeArr() {
        if(intArr == null) {
            intArr = new int[100000];
        }
        for(int i = 0; i < intArr.length; i++) {
            intArr[i] = (int)Math.random()*10000;
        }
    }


    @Override
    protected int[] compute() {
        return new int[0];
    }

    public static void main(String[] args) {

    }

}
