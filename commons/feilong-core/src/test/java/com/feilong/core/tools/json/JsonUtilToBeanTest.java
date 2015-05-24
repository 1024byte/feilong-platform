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
package com.feilong.core.tools.json;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JsonConfig;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.core.net.HttpMethodType;
import com.feilong.core.tools.json.JsonUtil;
import com.feilong.test.MyBean;
import com.feilong.test.Order;
import com.feilong.test.Person;
import com.feilong.test.User;
import com.feilong.test.UserInfo;

/**
 * JsonUtil测试类 (C) 2009-9-11, jzj.
 * 
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.7 2014-6-25 15:31:51
 */

public class JsonUtilToBeanTest{

    /** The Constant log. */
    private static final Logger log = LoggerFactory.getLogger(JsonUtilToBeanTest.class);

    /**
     * 从json串转换成实体对象，且实体中Date属性能正确转换 void.
     */
    @Test
    public void toBean1(){
        String json = "{'name':'get','dateAttr':'2009-11-12'}";
        Person person = JsonUtil.toBean(json, Person.class);
        // print: get
        log.info(person.getName());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // print: 2009-11-12
        log.info(sdf.format(person.getDateAttr()));
    }

    /**
     * 从json串转换成实体对象，并且实体集合属性存有另外实体Bean void.
     */
    @Test
    public void toBean(){
        String json = "{'data':[{'name':'get'},{'name':'set'}],'id':5}";
        Map<String, Class<?>> classMap = new HashMap<String, Class<?>>();
        classMap.put("data", Person.class);

        MyBean myBean = JsonUtil.toBean(json, MyBean.class, classMap);
        log.info(JsonUtil.toJSON(myBean).toString(4, 4));
    }

    /**
     * To bean n ull.
     */
    @Test
    public void toBeanNUll(){
        log.info(JsonUtil.toJSON(null, null).toString(4, 4));
    }

    /**
     * To json.
     */
    @Test
    public void toJSON(){
        HttpMethodType httpMethodType = HttpMethodType.GET;
        log.info(JsonUtil.toJSON(httpMethodType, null).toString(4, 4));
    }

    /**
     * To bean n ulluser.
     */
    @Test
    public void toBeanNUlluser(){
        User user = new User();
        user.setId(8L);
        user.setName("feilong");

        JsonConfig jsonConfig = new JsonConfig();

        // String[] excludes = { "userInfo" };
        // jsonConfig.setExcludes(excludes);

        Class<UserInfo> target = UserInfo.class;
        String[] properties = { "age" };
        jsonConfig.registerPropertyExclusions(target, properties);
        log.info(JsonUtil.toJSON(user, jsonConfig).toString(4, 4));
    }

    /**
     * 把一个json数组串转换成普通数组 void.
     */
    @Test
    public void toArray(){
        String json = "['get',1,true,null]";
        Object[] objArr = JsonUtil.toArray(json);
        for (int i = 0; i < objArr.length; i++){
            log.info(objArr[i].getClass() + " " + objArr[i]);
        }
        /*
         * print: class java.lang.String get class java.lang.Integer 1 class java.lang.Boolean true class net.sf.json.JSONNull null
         */
    }

    /**
     * 把一个json数组串转换成实体数组 void.
     */
    @Test
    public void toArray2(){
        String json = "[{'name':'get'},{'name':'set'}]";
        Person[] objArr = JsonUtil.toArray(json, Person.class);

        log.info(JsonUtil.toJSON(objArr).toString(4, 4));

        /*
         * print: class comm.test.Person name = get class comm.test.Person name = set
         */
    }

    /**
     * 把一个json数组串转换成实体数组，且数组元素的属性含有另外实例Bean void.
     */
    @Test
    public void toArray3(){
        String json = "[{'data':[{'name':'get'}]},{'data':[{'name':'set'}]}]";
        Map classMap = new HashMap();
        classMap.put("data", Person.class);

        Object[] objArr = JsonUtil.toArray(json, MyBean.class, classMap);
        for (int i = 0; i < objArr.length; i++){
            log.info(((MyBean) objArr[i]).getData().get(0).getClass() + " name = "
                            + ((Person) ((MyBean) objArr[i]).getData().get(0)).getName());
        }
        /*
         * print: class comm.test.Person name = get class comm.test.Person name = set
         */
    }

    /**
     * 把一个json数组串转换成存放普通类型元素的集合 void.
     */
    @Test
    public void toList1(){
        String json = "['get',1,true,null]";
        List list = JsonUtil.toList(json);
        for (int i = 0; i < list.size(); i++){
            log.info(list.get(i).getClass() + " " + list.get(i));
        }

        /*
         * print: class java.lang.String get class java.lang.Integer 1 class java.lang.Boolean true class net.sf.json.JSONNull null
         */
    }

    /**
     * 把一个json数组串转换成集合，且集合里存放的为实例Bean void.
     */
    @Test
    public void toList(){
        String json = "[{'name':'get'},{'name':'set'}]";
        List<Person> list = JsonUtil.toList(json, Person.class);

        log.info(JsonUtil.toJSON(list).toString(4, 4));
    }

    /**
     * 把一个json数组串转换成集合，且集合里的对象的属性含有另外实例Bean void.
     */
    @Test
    public void toList3(){
        String json = "[{'data':[{'name':'get'}]},{'data':[{'name':'set'}]}]";
        Map classMap = new HashMap();
        classMap.put("data", Person.class);
        List list = JsonUtil.toList(json, MyBean.class, classMap);
        for (int i = 0; i < list.size(); i++){
            log.info(((MyBean) list.get(i)).getData().get(0).getClass() + " name = "
                            + ((Person) ((MyBean) list.get(i)).getData().get(0)).getName());
        }
        /*
         * print: class comm.test.Person name = get class comm.test.Person name = set
         */
    }

    /**
     * 把json对象串转换成map对象 void.
     */
    @Test
    public void testGetMapFromJsonObjStr(){
        String json = "{'name':'get','int':1,'double':1.1,'null':null}";
        Map<String, Object> map = JsonUtil.toMap(json);
        for (Iterator<String> iter = map.keySet().iterator(); iter.hasNext();){
            Object key = iter.next();
            log.info(key + ":" + map.get(key).getClass().toString());
        }
        /*
         * print: class java.lang.Double class net.sf.json.JSONNull class java.lang.Integer class java.lang.String
         */
    }

    /**
     * 把json对象串转换成map对象，且map对象里存放的为其他实体Bean void.
     */
    @Test
    public void toMap(){
        String json = "{'data1':{'name':'get'},'data2':{'name':'set'}}";
        Map<String, Person> map = JsonUtil.toMap(json, Person.class);

        log.info(JsonUtil.toJSON(map).toString(4, 4));
    }

    /**
     * Name.
     */
    @Test
    public void name(){
        Map<String, Object> map = new HashMap<String, Object>();

        Map<String, Object> map1 = new HashMap<String, Object>();

        String[] aStrings = { "aaaa", "bbbb" };
        map1.put("b", aStrings);
        map1.put("bb", "2");
        map1.put("bbb", "3");

        map.put("a", map1);
        map.put("aa", map1);
        map.put("aaa", map1);
        log.info(JsonUtil.toJSON(map).toString(4, 4));
        //log.info(JsonUtil.format(map,"a.b"));
    }

    /**
     * To map1.
     */
    @Test
    public void toMap1(){

        log.info("status_deliveried".length() + "");

        List<Order> list = new ArrayList<Order>();

        Order a = new Order();
        a.setCode("137214341849121");

        a.setMemberID("325465");
        a.setMerchant_order_code("216888");
        Order a1 = new Order();
        a1.setCode("137214341888888");

        a1.setMemberID("3240088");
        a1.setMerchant_order_code("288888");

        list.add(a);
        list.add(a1);

        log.info(JsonUtil.format(list));

    }

    /**
     * 把json对象串转换成map对象，且map对象里 存放的其他实体Bean还含有另外实体Bean void.
     */
    @Test
    public void toMap3(){
        String json = "{'mybean':{'data':[{'name':'get'}]}}";
        Map classMap = new HashMap();
        classMap.put("data", Person.class);

        Map map = JsonUtil.toMap(json, MyBean.class, classMap);
        for (Iterator iter = map.keySet().iterator(); iter.hasNext();){
            String key = (String) iter.next();
            Object o = ((MyBean) map.get(key)).getData().get(0);
            log.info(o.getClass() + " name=" + ((Person) o).getName());
        }
        /*
         * print: class comm.test.Person name=get
         */
    }

    /**
     * 实体Bean转json串 void.
     */
    @Test
    public void testgetJsonStr1(){
        Person ps = new Person();
        ps.setDateAttr(new Date());
        ps.setName("get");
        MyBean myBean = new MyBean();
        List<Object> list = new ArrayList<Object>();
        list.add(ps);

        myBean.setData(list);
        // print: {"data":[{"dateAttr":"2009-09-12 07:24:54","name":"get"}]}
        log.info("" + JsonUtil.toJSON(myBean));
    }

    /**
     * list转json串 void.
     */
    @Test
    public void testgetJsonStr4(){
        Person ps = new Person();
        ps.setDateAttr(new Date());
        ps.setName("get");
        List<Person> list = new ArrayList<Person>();
        list.add(ps);

        // print: [{"dateAttr":"2009-09-12 07:22:49","name":"get"}]
        log.info("" + JsonUtil.toJSON(list));

        Set set = new LinkedHashSet();
        set.add(ps);

        // print: [{"dateAttr":"2009-09-12 07:22:16","name":"get"}]
        log.info("" + JsonUtil.toJSON(set));

        Person[] personArr = new Person[1];
        personArr[0] = ps;
        // print: [{"dateAttr":"2009-09-12 07:23:54","name":"get"}]
        log.info("" + JsonUtil.toJSON(personArr));

        Map map = new LinkedHashMap();
        map.put("person1", ps);

        // print: {"person1":{"dateAttr":"2009-09-12 07:24:27","name":"get"}}
        log.info("" + JsonUtil.toJSON(map));
    }
}
