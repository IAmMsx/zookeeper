package zk;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class ckClient {

    // 地址不能有空格
    private String connectString = "hadoop102:2181,hadoop103:2181,hadoop104:2181";
    private int sessionTimeout = 2000;
    private ZooKeeper zkClient;


    @Before
    public void init() throws IOException {
        zkClient = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
//                System.out.println("----------------------------");
//                List<String> children = null;
//                try {
//                    children = zkClient.getChildren("/", true);
//                } catch (KeeperException e) {
//                    e.printStackTrace();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//                for (String child : children) {
//                    System.out.println(child);
//                }
//                System.out.println("----------------------------");
            }
        });
    }

    @Test
    public void create() throws InterruptedException, KeeperException {
        String nodeCreate = zkClient.create(
                "/atguigu", // 路径
                "ss.avi".getBytes(), // 内容
                ZooDefs.Ids.OPEN_ACL_UNSAFE, //权限
                CreateMode.PERSISTENT // 类别 持久/短暂（是否有序号）
        );

    }
    @Test
    public void getChildren() throws InterruptedException, KeeperException {
        List<String> children = zkClient.getChildren("/", true);

        for (String child : children) {
            System.out.println(child);
        }
        // 延时
        Thread.sleep(Long.MAX_VALUE);
    }

    @Test
    public void exist() throws InterruptedException, KeeperException {
        Stat stat = zkClient.exists("/atguigu", true);
        System.out.println(stat==null?"not exit":"exist");

    }

}
