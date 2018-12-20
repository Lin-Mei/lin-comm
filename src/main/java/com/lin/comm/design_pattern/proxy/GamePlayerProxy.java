package com.lin.comm.design_pattern.proxy;

/**
 * @Author meilin
 * @Date 2018/11/1317:29
 * @Version 1.0
 **/
public class GamePlayerProxy implements IGamePlayer {
    private IGamePlayer iGamePlayer;
    public GamePlayerProxy(IGamePlayer iGamePlayer){
        this.iGamePlayer=iGamePlayer;
        System.out.println(String.format("游戏一点都不好玩，来自一个游戏代练的忠告"));
    }
    @Override
    public void login(String userName, String pwd) {
        iGamePlayer.login(userName,pwd);
    }

    @Override
    public void killBoss() {
        iGamePlayer.killBoss();
        System.out.println("这个boss真难打，以后要求加钱");
    }

    @Override
    public void update() {
        iGamePlayer.update();
        System.out.println("又升了一个，有小钱钱赚了");
    }
}
