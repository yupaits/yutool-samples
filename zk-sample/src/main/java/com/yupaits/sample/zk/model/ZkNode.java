package com.yupaits.sample.zk.model;

import java.util.List;

/**
 * @author yupaits
 * @date 2019/7/9
 */
public class ZkNode {
    private String path;
    private List<ZkNode> children;

    public ZkNode(String path, List<ZkNode> children) {
        this.path = path;
        this.children = children;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<ZkNode> getChildren() {
        return children;
    }

    public void setChildren(List<ZkNode> children) {
        this.children = children;
    }
}
