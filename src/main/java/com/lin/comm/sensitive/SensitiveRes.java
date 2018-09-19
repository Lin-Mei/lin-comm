package com.lin.comm.sensitive;

import java.util.List;

/**
 * @Author meilin
 * @Date 2018/9/1818:49
 * @Version 1.0
 **/
public class SensitiveRes {
    private String origin;//源字符串
    private String replaceNew;//替换敏感词后的新字符串
    private List<String> sensitives;//检测到的敏感词

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getReplaceNew() {
        return replaceNew;
    }

    public void setReplaceNew(String replaceNew) {
        this.replaceNew = replaceNew;
    }

    public List<String> getSensitives() {
        return sensitives;
    }

    public void setSensitives(List<String> sensitives) {
        this.sensitives = sensitives;
    }
}
