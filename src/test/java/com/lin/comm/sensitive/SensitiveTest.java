package com.lin.comm.sensitive;

import com.lin.comm.utils.JsonUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author meilin
 * @Date 2018/9/1910:29
 * @Version 1.0
 **/
public class SensitiveTest {
    @Test
    public  void test1(){
        List<String> strings=new ArrayList<>();
        strings.add("毒贩");
        strings.add("傻子");
        strings.add("他妈的");
        strings.add("神经病");
        SensitiveWord.init(strings);
        long time=System.currentTimeMillis();
        System.out.println(JsonUtil.toJsonString(SensitiveWord.checkSentivie("最近毒贩很多，出门小心遇到毒***贩",true)));
        System.out.println(JsonUtil.toJsonString(SensitiveWord.checkSentivie("怎么那么多傻子",true)));
        System.out.println(JsonUtil.toJsonString(SensitiveWord.checkSentivie("小明告诉小华，他妈的工作丢了",true)));
        System.out.println(JsonUtil.toJsonString(SensitiveWord.checkSentivie("小明说他妈的神经 病",true)));
        System.out.println(System.currentTimeMillis()-time);

    }
    @Test
    public  void test2(){
        long time=System.currentTimeMillis();
        System.out.println("开始初始化敏感词典");
        List<String> list=new ArrayList<>();
        Map<String,List<String>> map=SensitiveReader.read("classpath:sensitive");
        for (Map.Entry<String, List<String>> item : map.entrySet()) {
            list.addAll(item.getValue());
        }
        SensitiveWord.init(list);
        System.out.println(String.format("初始化词典完成，词条：%s,耗时：%sms", list.size(), (System.currentTimeMillis() - time)));
        List<String> testStrings=new ArrayList<>();
        testStrings.add("有鸦片吗，哪里有卖");
        testStrings.add("有鸦片吗，哪里有卖");
        testStrings.add("街上打人，你看到了吗");
        testStrings.add("某君昆仲，今隐其名，皆余昔日在中学时良友；分隔多年，消息渐阙。日前偶闻其一大病；适归故乡，迂道往访，则仅晤一人，言病者其弟也。劳君远道来视，然已早愈，赴某地 候补⑵矣。因大笑，出示日记二册，谓可见当日病状，不妨献诸旧友。持归阅一过，知所患 盖“迫害狂”之类。语颇错杂无伦次，又多荒唐之言；亦不著月日，惟墨色字体不一，知非 一时所书。间亦有略具联络者，今撮录一篇，以供医家研究。记中语误，一字不易；惟人名 虽皆村人，不为世间所知，无关大体，然亦悉易去。至于书名，则本人愈后所题，不复改也。七年四月二日识。");
        testStrings.add("你们这些发国难财（发&国*难@财）的渣渣啊，fuck，去上CCTV吧，让全国人民都来看看");
        System.out.println("testString:"+JsonUtil.toJsonString(testStrings));
        //第一次因为要加载一些资源，类等，所以耗时比较长
        for(String s:testStrings){
            time=System.currentTimeMillis();
            System.out.println(JsonUtil.toJsonString(SensitiveWord.checkSentivie(s,true)));
            System.out.println("耗时："+(System.currentTimeMillis()-time)+"ms");
        }
    }
}
