package com.lin.comm.xmparser.reader.utils;

import com.lin.comm.xmparser.reader.ResourceReader;
import com.lin.comm.xmparser.reader.impl.FileResourceReader;
import com.lin.comm.xmparser.reader.impl.RestResourceReader;
import com.lin.comm.xmparser.reader.mode.ReaderType;

/**
 * @Author meilin
 * @Date 2018/10/3118:06
 * @Version 1.0
 **/
public class ReaderFactory {
    public static ResourceReader genInstance(ReaderType readerType){
        switch (readerType){
            case FILE:return new FileResourceReader();
            case REST:return new RestResourceReader();
        }
        return null;
    }
}
