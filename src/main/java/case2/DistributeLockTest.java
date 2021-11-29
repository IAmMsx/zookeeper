package case2;

import org.apache.zookeeper.KeeperException;

import java.io.IOException;

public class DistributeLockTest {
    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        final DistributedLock lock1 = new DistributedLock();
        final DistributedLock lock2 = new DistributedLock();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock1.zklock();
                    System.out.println("现程1启动获取到锁");
                    Thread.sleep(5*1000);

                    lock1.unZklock();
                    System.out.println("现程1释放锁");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock2.zklock();
                    System.out.println("现程2启动获取到锁");
                    Thread.sleep(5*1000);

                    lock2.unZklock();
                    System.out.println("现程2释放锁");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
