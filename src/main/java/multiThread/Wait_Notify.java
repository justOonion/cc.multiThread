package multiThread;

public class Wait_Notify {

    /*是否等待标记*/
    public static String[] isWait = {"true"};


    public static class ThreadA implements Runnable {
        @Override
        public void run() {
            System.out.println("Thread-A start...");

            /*休眠10毫秒*/
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            /*修改等待标记 -- 不再等待*/
            synchronized (isWait) {
                isWait[0] = "false";
                isWait.notifyAll();
            }

            /*休眠10毫秒*/
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            /*setDaemon后，不一定执行*/
            System.out.println("Thread-A end...");
        }
    }

    /**
     * 打印日志：
     *
     * main-thread start...
     * Thread-A start...
     * main-thread end...
     * Thread-A end...
     */
    public static void main(String[] args) throws InterruptedException {
        System.out.println("main-thread start...");
        Thread A = new Thread(new ThreadA());
        //A.setDaemon(true);
        A.start();
        synchronized (isWait) {
            while ("true".equals(isWait[0])) {
                isWait.wait();
            }
        }
        System.out.println("main-thread end...");
    }

}
