package com.lin.comm.bp.mybp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @Author meilin
 * @Date 2018/9/2917:34
 * @Version 1.0
 **/
public class Mybp {
    private double []hidel_x;
    private double [][]hidel_w;
    private double []hidel_erros;

    private double[]out_x;
    private double[][]out_w;
    private double []out_erros;

    private double []target;
    private double rate;

    public Mybp(int input_node,int hidel_node,int out_node,double rate){
        super();
        hidel_x=new double[input_node+1];
        hidel_w=new double[hidel_node][input_node+1];
        hidel_erros=new double[hidel_node];

        out_x=new double[hidel_node+1];
        out_w=new double[out_node][hidel_node+1];
        out_erros=new double[out_node];
        target=new double[out_node];
        this.rate=rate;

        init_weight();
    }
    private void  init_weight(){
        set_weight(hidel_w);
        set_weight(out_w);
    }
    private  void set_weight(double[][]w){
        for(int i=0;i<w.length;i++)
            for(int j=0;j<w[i].length;j++)
                w[i][j]=0;
    }
    /**设置原始值***********/
    private void setHidel_x(double[]data){
        if(data.length!=hidel_x.length-1){
            throw new IllegalArgumentException("原始数据与定义的输入层个数不一样");
        }
        System.arraycopy(data,0,hidel_x,1,data.length);
        hidel_x[0]=1.0;
    }
    private void setTarget(double[]tartget){
        if(tartget.length!=this.target.length){
            throw new IllegalArgumentException("目标数据与定义的输出层个数不一样");
        }
        System.arraycopy(tartget,0,this.target,0,tartget.length);
    }

    /**
     * 计算单个节点的输出
     * @param x 输入x向量
     * @param w 节点的权重向量
     * @return
     */
    private double gen_node_out(double[] x,double[]w){
        double z=0d;
        for(int i=0;i<x.length;i++)
            z+=x[i]*w[i];
        //激励函数
        return 1d/(1d+Math.exp(-z));
    }
    /***计算一层网络的输出*************************/
    private void gen_net_out(double[]x,double[][]w,double[]out){
        out[0]=1;
        for(int i=0;i<w.length;i++)
            out[i+1]=gen_node_out(x,w[i]);
    }
    public void forword(double[]x,double[]ouput){
        gen_net_out(x,hidel_w,out_x);
        gen_net_out(out_x,out_w,ouput);
    }

    /**计算输出层误差*********************/
    public void gen_out_erros(double[]output,double[]target,double[]out_erros){
        for (int i = 0; i < target.length; i++) {
            out_erros[i]=(target[i]-output[i+1])*output[i+1]*(1d-output[i+1]);
        }
    }
    public void gen_hidel_erros(double[]nextErros,double[][]nextW,double[]output,double []erros){
        for(int i=0;i<erros.length;i++){
            double sum=0;
            for(int j=0;j<nextW.length;j++){
                sum+=nextW[j][i+1]*nextErros[j];
            }
            erros[i]=sum*output[i+1]*(1d-output[i+1]);
        }
    }
    public void update_weight(double[]erros,double[][]w,double[]x){
        double newWeight=0;
        for (int i = 0; i < w.length; i++) {
            for(int j=0;j<w[i].length;j++){
                newWeight=rate*erros[i]*x[j];
                w[i][j]=w[i][j]+newWeight;
            }
        }
    }
    public void backpropargation(double[]output){
        gen_out_erros(output,target,out_erros);
        gen_hidel_erros(out_erros,out_w,out_x,hidel_erros);
        update_weight(hidel_erros,hidel_w,hidel_x);
        update_weight(out_erros,out_w,out_x);
    }
    public void train(double[]trainData,double[]target){
        setHidel_x(trainData);
        setTarget(target);
        double[] outout=new double[out_x.length+1];
        forword(hidel_x,outout);
        backpropargation(outout);
    }
    public void predict(double[]x,double[]output){
        double[]out_y=new double[out_w.length+1];
        setHidel_x(x);
        forword(hidel_x,out_y);
        System.arraycopy(out_y,1,output,0,output.length);
    }
    public static double[] toBinary(int value){
        double[] binary=new double[32];
        for(int i=31;i>=0;i--){
            binary[31-i]=value>>>i&1;
        }
        return binary;
    }
    public static double[] genTarget(int value){
        double[] real = new double[4];
        if (value >= 0)
            if ((value & 1) == 1)
                real[0] = 1;
            else
                real[1] = 1;
        else if ((value & 1) == 1)
            real[2] = 1;
        else
            real[3] = 1;
        return real;
    }
    public static void main(String[] args) throws IOException {
        Mybp mybp=new Mybp(32,15,4,0.05);
        Random random = new Random();
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i != 6000; i++) {
            int value = random.nextInt();
            list.add(value);
        }
        for (int i = 0; i !=25; i++) {
            for (int value : list) {
                double[]real=genTarget(value);
                double[] binary = toBinary(value);
                mybp.train(binary, real);
            }
        }
        System.out.println("训练完毕，下面请输入一个任意数字，神经网络将自动判断它是正数还是复数，奇数还是偶数。");
        while (true) {
            byte[] input = new byte[10];
            System.in.read(input);
            Integer value = Integer.parseInt(new String(input).trim());
            int rawVal = value;
            double[] binary = toBinary(value);
            double[] result =new double[4];
            mybp.predict(binary,result);

            double max = -Integer.MIN_VALUE;
            int idx = -1;
            for (int i = 0; i != result.length; i++) {
                if (result[i] > max) {
                    max = result[i];
                    idx = i;
                }
            }
            switch (idx) {
                case 0:
                    System.out.format("%d是一个正奇数\n", rawVal);
                    break;
                case 1:
                    System.out.format("%d是一个正偶数\n", rawVal);
                    break;
                case 2:
                    System.out.format("%d是一个负奇数\n", rawVal);
                    break;
                case 3:
                    System.out.format("%d是一个负偶数\n", rawVal);
                    break;
            }
        }
    }

}
