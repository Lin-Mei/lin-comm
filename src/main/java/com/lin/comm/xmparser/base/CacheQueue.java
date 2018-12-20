package com.lin.comm.xmparser.base;

public interface CacheQueue {
    void put(Object e);
    Object poll();
    int size();
}
