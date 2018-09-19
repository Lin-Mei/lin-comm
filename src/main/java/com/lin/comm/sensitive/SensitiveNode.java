package com.lin.comm.sensitive;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author meilin
 * @Date 2018/9/1816:53
 * @Version 1.0
 **/
public class SensitiveNode {
    boolean isEnd=false;
    Map<String,SensitiveNode> nodes=new HashMap<>();

    public boolean isEnd() {
        return isEnd;
    }

    public void setEnd(boolean end) {
        isEnd = end;
    }

    public Map<String, SensitiveNode> getNodes() {
        return nodes;
    }

    public void setNodes(Map<String, SensitiveNode> nodes) {
        this.nodes = nodes;
    }

    public SensitiveNode put(String key, SensitiveNode node){
        return nodes.put(key,node);
    }
    public SensitiveNode get(String key){
        return nodes.get(key);
    }
}
