package com.lin.comm.design_pattern.proxy;

import java.util.Date;

/**
 * @Author meilin
 * @Date 2018/11/1317:25
 * @Version 1.0
 **/
public class GamePlayer implements IGamePlayer{
    private String name;
    @Override
    public void login(String userName, String pwd) {
        this.name=userName;
        System.out.println(String.format("%s登陆游戏，时间%s",userName,new Date()));
    }

    @Override
    public void killBoss() {
        System.out.println(String.format("%s杀了一个怪",name));
    }

    @Override
    public void update() {
        System.out.println(String.format("%s升了一级",name));
    }
}
