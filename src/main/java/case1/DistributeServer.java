package case1;

import org.apache.zookeeper.*;

import java.io.IOException;

public class DistributeServer {
    private String connectString = "hadoop102:2181,hadoop103:2181,hadoop104:2181";
    private int sesionTimeOut = 2000;
    private ZooKeeper zk;

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        DistributeServer server = new DistributeServer();
        // 1. 获取zk连接
        server.getConnect();
        // 2. 注册服务器zk集群信息
        server.regist(args[0]);

        // 3. 启动业务逻辑(sleep)
        server.business();
    }

    private void business() throws InterruptedException {
        Thread.sleep(Long.MAX_VALUE);
    }

    private void regist(String hostname) throws InterruptedException, KeeperException {
        String create = zk.create("/servers/"+hostname,
                hostname.getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println(hostname + "is online");
    }

    private void getConnect() throws IOException {
        zk = new ZooKeeper(connectString, sesionTimeOut, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {

            }
        });
    }
}
