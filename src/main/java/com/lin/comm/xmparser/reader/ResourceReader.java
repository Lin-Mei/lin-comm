package com.lin.comm.xmparser.reader;

import java.net.URL;

/**
 * 资源读取器
 * @Author meilin
 * @Date 2018/10/3117:01
 * @Version 1.0
 **/
public interface ResourceReader {
    /**读取资源*************/
    String read(URL url);
}
