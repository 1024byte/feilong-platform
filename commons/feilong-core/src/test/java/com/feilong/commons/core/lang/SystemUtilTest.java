/*
 * Copyright (C) 2008 feilong (venusdrogon@163.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.feilong.commons.core.lang;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.tools.json.JsonUtil;

/**
 * The Class SystemUtilTest.
 * 
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.7 2014年6月3日 下午2:31:50
 * @since 1.0.7
 */
public class SystemUtilTest{

    /** The Constant log. */
    private static final Logger log = LoggerFactory.getLogger(SystemUtilTest.class);

    /**
     * Nano time.
     */
    @Test
    public void nanoTime(){
        //返回最准确的可用系统计时器的当前值，以毫微秒为单位。
        log.info("" + System.nanoTime());

        //返回以毫秒为单位的当前时间。注意，当返回值的时间单位是毫秒时，值的粒度取决于底层操作系统，并且粒度可能更大。例如，许多操作系统以几十毫秒为单位测量时间。 
        log.info("" + System.currentTimeMillis());

    }

    /**
     * Gets the property.
     * 
     */
    @Test
    public void testGetProperty(){
        log.info(System.getProperty("path"));

    }

    /**
     * Gets the env.
     * 
     */
    @Test
    public void testGetenv(){
        Map<String, String> map = System.getenv();
        Object[] key = map.keySet().toArray();
        Arrays.sort(key);
        for (int i = 0; i < key.length; i++){
            log.info(key[i] + "======>" + map.get(key[i]));
        }
    }

    /**
     * Gets the env map for log.
     * 
     */
    @Test
    public void testGetEnvMapForLog(){
        Map<String, String> envMapForLog = SystemUtil.getEnvMapForLog();
        if (log.isInfoEnabled()){
            log.info(JsonUtil.format(envMapForLog));
        }
    }

    /**
     * Path.
     */
    @Test
    public void path(){
        String path = System.getenv("Path");
        // log.info(path);
        String[] strings = path.split(";");
        Arrays.sort(strings);
        for (String p : strings){
            log.info(p);
        }
    }

    /**
     * Current time millis.
     */
    @Test
    public void currentTimeMillis(){
        log.info("" + System.currentTimeMillis());
    }

    /**
     * Test system.
     */
    @Test
    public void testSystem(){

        System.getProperties().list(System.out);

        Properties properties = System.getProperties();
        Object[] key = properties.keySet().toArray();
        Arrays.sort(key);
        for (int i = 0; i < key.length; i++){
            log.info(key[i] + "======>" + properties.get(key[i]));
        }
        // OutputStream os = IOUtil.getFileOutputStream("E:/1.xml");
        // try{
        // properties.storeToXML(os, "ceshi");
        // }catch (IOException e){
        // log.error(e.getClass().getName(), e);
        // }
        // FeiLongTestUtil.print(properties);

    }

    /**
     * Gets the properties map for log.
     * 
     */
    @Test
    public void testGetPropertiesMapForLog(){
        Map<String, String> propertiesMapForLog = SystemUtil.getPropertiesMapForLog();
        if (log.isInfoEnabled()){
            log.info(JsonUtil.format(propertiesMapForLog));
        }
    }

    /**
     * Test.
     */
    @Test
    public void test(){
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("aaaa", "aaaa");
        map.put("cccc", "cccc");
        map.put("bbbb", "bbbb");
        map.put("dddd", "dddd");
        Iterator<String> iterator = map.keySet().iterator();
        while (iterator.hasNext()){
            Object key = iterator.next();
            Object obj = map.get(key);
            log.info("" + obj);
        }
        log.info("---------------------------");
        Object[] key = map.keySet().toArray();
        Arrays.sort(key);
        for (int i = 0; i < key.length; i++){
            log.info(map.get(key[i]));
        }
    }
}
