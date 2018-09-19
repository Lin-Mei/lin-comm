package com.lin.comm.sensitive;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author meilin
 * @Date 2018/9/1816:33
 * @Version 1.0
 **/
public class SensitiveWord {
    private static SensitiveNode root=new SensitiveNode();
    private static char replaceChar='*';
    private static String special="*,.?*&^%$#@!~;   ";
    /**
     * 根据敏感词初始化字典
     * @param stringSet
     */
    public static void init(List<String> stringSet){
        for(String word:stringSet){
            SensitiveNode lastNode=root;
            for(int i=0;i<word.length();i++){
                String key=word.charAt(i)+"";
                SensitiveNode temp=lastNode.get(key);
                if (temp == null) {
                    temp=new SensitiveNode();
                    lastNode.put(key, temp);
                }
                if(i==word.length()-1){
                    temp.setEnd(true);
                }
                lastNode=temp;
            }
        }
    }

    /*********
     * 检测敏感词并根据条件替换，返回找到的敏感词
     * @param s 目标文本
     * @param replace 是否用特殊字符替换敏感词
     * @return
     */
    public static SensitiveRes checkSentivie(String s,boolean replace){
        List<String> sentivies=new ArrayList<>();
        SensitiveRes sensitiveRes=new SensitiveRes();
        sensitiveRes.setOrigin(s);
        if(s!=null&&s!=""){
            SensitiveNode lastNode=root;
            String sentivie="";
            char[] chars=s.toCharArray();
            int start=0;
            for(int i=0;i<chars.length;i++){
                String key=chars[i]+"";
                if(isSpecial(key)&&sentivie!=""){//特殊字符直接跳过并计入敏感词中
                    sentivie=sentivie+key;
                    continue;
                }
                SensitiveNode temp=lastNode.get(key);
                if(temp==null){//复位到根节点开始匹配
                    lastNode=root;
                    temp=lastNode.get(key);
                    sentivie="";
                    if(temp==null){
                        start=i+1;
                        continue;
                    }else{
                        start=i;
                    }
                }
                if(temp!=null){
                    sentivie=sentivie+key;
                    if(temp.isEnd){
                        sentivies.add(new String(sentivie));
                        if(replace){
                            for(int j=start;j<=i;j++){
                                chars[j]=replaceChar;
                            }
                        }
                        sentivie="";
                        lastNode=root;
                        start=i;
                    }else{
                        lastNode=temp;
                    }
                }
            }
            sensitiveRes.setSensitives(sentivies);
            sensitiveRes.setReplaceNew(new String(chars));
        }
        return sensitiveRes;
    }

    /**
     * 判断目标是否是特殊字符
     * @param s
     * @return
     */
    private static boolean isSpecial(String s){
        return special.contains(s);
    }
}
