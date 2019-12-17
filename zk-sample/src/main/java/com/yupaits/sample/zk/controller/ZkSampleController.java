package com.yupaits.sample.zk.controller;

import com.yupaits.sample.zk.model.ZkNode;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yupaits
 * @date 2019/7/9
 */
@RestController
public class ZkSampleController {
    private static final String OK = "ok";
    private static final String FAIL = "fail";
    private static final String ZOOKEEPER_SERVERS = "localhost:2181,localhost:2182,localhost:2183";
    private static final String TEST_PATH = "/zk_test";
    private static final byte[] TEST_DATA = "Hello, ZooKeeper!".getBytes();

    private final Watcher watcher;
    private final ZooKeeper zooKeeper;

    public ZkSampleController() throws IOException {
        this.watcher = event -> System.out.println("接收事件：" + event);
        this.zooKeeper = new ZooKeeper(ZOOKEEPER_SERVERS, 20000, watcher);
    }

    @PostMapping("/zkset")
    public String setZkData() throws KeeperException, InterruptedException {
        if (zooKeeper.exists(TEST_PATH, watcher) == null) {
            zooKeeper.create(TEST_PATH, TEST_DATA, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }
        System.out.println();
        return OK;
    }

    @GetMapping("/zktree")
    public List<ZkNode> getZkTree() throws KeeperException, InterruptedException {
        List<ZkNode> nodes = new ArrayList<>();
        ls("/", nodes);
        return nodes;
    }

    private void ls(String path, List<ZkNode> nodes) throws KeeperException, InterruptedException {
        List<String> children = zooKeeper.getChildren(path, null);
        if (children == null || children.isEmpty()) {
            return;
        }
        for (String child : children) {
            String childPath;
            List<ZkNode> subNodes = new ArrayList<>();
            if ("/".equals(path)) {
                childPath = path + child;
            } else {
                childPath = path + "/" + child;
            }
            ls(childPath, subNodes);
            nodes.add(new ZkNode(childPath, subNodes));
        }
    }

    @GetMapping("/zkget")
    public String getZkData() throws KeeperException, InterruptedException {
        final byte[] data = zooKeeper.getData(TEST_PATH, watcher, zooKeeper.exists(TEST_PATH, watcher));
        return new String(data);
    }

    @DeleteMapping("/zkdelete")
    public String deleteZkData(@RequestParam String path) throws KeeperException, InterruptedException {
        Stat stat = zooKeeper.exists(path, false);
        if (stat != null) {
            zooKeeper.delete(path, stat.getVersion());
        }
        return OK;
    }
}
