package com.lin.comm.leetcode;
/**
 * @Author meilin
 * @Date 2018/11/1611:26
 * @Version 1.0
 **/
public class TowSum {
    public static class ListNode {
     int val;
     ListNode next;
     ListNode(int x) { val = x; }
 }
    public static void main(String[] args) {
        ListNode listNode1=new ListNode(2);
        ListNode listNode2=new ListNode(4);
        ListNode listNode3=new ListNode(5);
        listNode1.next=listNode2;
        listNode2.next=listNode3;
        ListNode listNode21=new ListNode(5);
        ListNode listNode22=new ListNode(6);
        ListNode listNode23=new ListNode(4);
        listNode21.next=listNode22;
        listNode22.next=listNode23;


        Long time=System.currentTimeMillis();
        ListNode tem1=listNode1,tem2=listNode21,res=new ListNode(0),currt=res;
        int sum=0;
        while (tem1!=null||tem2!=null){
            if(tem1!=null){
                sum+=tem1.val;
                tem1=tem1.next;
            }
            if(tem2!=null) {
                sum+=tem2.val;
                tem2=tem2.next;
            }
            if(sum<10){
                currt.next=new ListNode(sum);
                sum=0;
            }else{
                currt.next=new ListNode(sum-10);
                sum=1;
            }
            currt=currt.next;
        }
        if(sum>0){
            currt.next=new ListNode(sum);
        }
        currt=res.next;
        System.out.println(System.currentTimeMillis()-time);
        while (currt!=null){
            System.out.print(currt.val);
            currt=currt.next;
        }

    }
}
