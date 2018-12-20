package com.lin.comm.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author meilin
 * @Date 2018/11/1614:03
 * @Version 1.0
 **/
public class LengthOfLongestSubstring {
    public static void main(String[] args) {
        test("=wgdhyw41");
        test("abcabcbb");
        test("bbbbb");
        test("pwwkew");
        test("p");
        test("e");
        test("");
        test("au");
        test("cdd");

        System.out.println("dfsafa".hashCode());
        System.out.println("dfsafaw".hashCode());
        String s=new String("dfsafa");
        System.out.println(s.hashCode()%5+":"+(s.equals("dfsafa")));
    }
    public static void test(String s){
        int n1=f(s),n2=f2(s);
        if(n1==n2){
            System.out.println(String.format("Str:%s,f:%s,f2:%s",s,n1,n2));
        }else{
            System.err.println(String.format("Str:%s,f:%s,f2:%s",s,n1,n2));
        }
    }
    public static int f(String s){
        int n = s.length(), ans = 0;
        int[] index = new int[128];
        for (int j = 0, i =0; j < n; j++) {
            i = Math.max(index[s.charAt(j)], i);
            ans = Math.max(ans, j - i+1);
            index[s.charAt(j)] = j+1;
        }
        return ans;
    }
    public static int f2(String s){
        int n = s.length(), ans = 0,last=0;
        int[] index = new int[128];
        for (int j = 0, i = 0; j < n; j++) {
            last=index[s.charAt(j)];
            if(i<last){
                i = last;
                ans = Math.max(ans, j - i+1);
            }else if(j==n-1){
                ans = Math.max(ans, j - i+1);
            }
            index[s.charAt(j)] = j+1;
        }
        return ans;
    }
}
