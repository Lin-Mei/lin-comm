package com.lin.comm.design_pattern.proxy;

/**
 * @Author meilin
 * @Date 2018/11/1317:24
 * @Version 1.0
 **/
public interface IGamePlayer {
    void login(String userName,String pwd);
    void killBoss();
    void update();
}
