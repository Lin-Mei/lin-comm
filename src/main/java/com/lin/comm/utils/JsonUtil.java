package com.lin.comm.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author meilin
 * @Date 2018/9/1818:52
 * @Version 1.0
 **/
public class JsonUtil {
    final static ObjectMapper MAPPER = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
    static final Logger LOGGER = LoggerFactory.getLogger(JsonUtil.class);

    public static String toJsonString(Object value) {
        try {
            return MAPPER.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            LOGGER.error("error when converting object to string", e);
            return null;
        }
    }
}
