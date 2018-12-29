package concurrency;

public class MultiLock2 {
    private synchronized void f1(int count) {
        if (count-- > 0) {
            System.out.println("... begin call f2 ... " + count);
            f2(count);
        }
        System.out.println("call f1 over ... " + count);
        System.out.println("++++++++++++");
    }

    private synchronized void f2(int count) {
        if (count-- > 0) {
            System.out.println("... begin call f1 ... " + count);
            f1(count);
        }
        System.out.println("call f2 over ... " + count);
    }

    public static void main(String[] args) {
        final MultiLock2 ml2 = new MultiLock2();
        new Thread() {
            @Override
            public void run() {
                ml2.f1(10);
            };
        }.start();
    }

}
