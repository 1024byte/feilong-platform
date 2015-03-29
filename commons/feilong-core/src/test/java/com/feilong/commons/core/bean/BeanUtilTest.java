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
package com.feilong.commons.core.bean;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.beanutils.BasicDynaBean;
import org.apache.commons.beanutils.BasicDynaClass;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaProperty;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;
import org.apache.commons.lang.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.bean.command.Address;
import com.feilong.commons.core.bean.command.Customer;
import com.feilong.commons.core.bean.command.Member;
import com.feilong.commons.core.bean.command.MemberAddress;
import com.feilong.commons.core.bean.command.SalesOrder;
import com.feilong.commons.core.bean.command.SalesOrderDto;
import com.feilong.commons.core.date.DatePattern;
import com.feilong.commons.core.date.DateUtil;
import com.feilong.commons.core.tools.json.JsonUtil;
import com.feilong.test.User;

/**
 * The Class BeanUtilTest.
 *
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-5-15 上午10:45:34
 */
public class BeanUtilTest{

    /** The Constant log. */
    private static final Logger log = LoggerFactory.getLogger(BeanUtilTest.class);

    /** The sales order. */
    private SalesOrder          salesOrder;

    /**
     * Inits the.
     */
    @Before
    public void init(){
        salesOrder = new SalesOrder();
        salesOrder.setCreateTime(new Date());
        salesOrder.setCode("258415-002");
        salesOrder.setId(5L);
        salesOrder.setPrice(new BigDecimal(566));

        Member member = new Member();
        member.setCode("222222");
        long memberId = 888L;
        member.setId(memberId);
        member.setLoginName("feilong");

        Map<String, String> loveMap = new HashMap<>();
        loveMap.put("蜀国", "赵子龙");
        loveMap.put("魏国", "张文远");
        loveMap.put("吴国", "甘兴霸");
        member.setLoveMap(loveMap);

        MemberAddress memberAddress1 = new MemberAddress();
        memberAddress1.setAddress("上海市宝山区真大路333弄22号1503室");
        memberAddress1.setAddTime(DateUtil.string2Date("20140615", DatePattern.yyyyMMdd));
        memberAddress1.setId(1L);
        memberAddress1.setMemberId(memberId);

        MemberAddress memberAddress2 = new MemberAddress();
        memberAddress2.setAddress("上海市闸北区阳城路280弄25号802室(阳城贵都)");
        memberAddress2.setAddTime(DateUtil.string2Date("20101001", DatePattern.yyyyMMdd));
        memberAddress2.setId(1L);
        memberAddress2.setMemberId(memberId);

        MemberAddress[] memberAddresses = { memberAddress1, memberAddress2 };
        member.setMemberAddresses(memberAddresses);

        salesOrder.setMember(member);
    }

    /**
     * Demo normal java beans.
     *
     * @throws Exception
     *             the exception
     */
    @Test
    public void testDemoNormalJavaBeans() throws Exception{

        log.debug(StringUtils.center(" demoNormalJavaBeans ", 40, "="));

        // data setup  
        Address addr1 = new Address("CA1234", "xxx", "Los Angeles", "USA");
        Address addr2 = new Address("100000", "xxx", "Beijing", "China");
        Address[] addrs = new Address[2];
        addrs[0] = addr1;
        addrs[1] = addr2;
        Customer cust = new Customer(123, "John Smith", addrs);

        // accessing the city of first address  
        String cityPattern = "addresses[0].city";
        String name = (String) PropertyUtils.getSimpleProperty(cust, "name");
        String city = (String) PropertyUtils.getProperty(cust, cityPattern);
        Object[] rawOutput1 = new Object[] { "The city of customer ", name, "'s first address is ", city, "." };
        log.debug(StringUtils.join(rawOutput1));

        // setting the zipcode of customer's second address  
        String zipPattern = "addresses[1].zipCode";
        if (PropertyUtils.isWriteable(cust, zipPattern)){//PropertyUtils  
            log.debug("Setting zipcode ...");
            PropertyUtils.setProperty(cust, zipPattern, "200000");//PropertyUtils  
        }
        String zip = (String) PropertyUtils.getProperty(cust, zipPattern);//PropertyUtils  
        Object[] rawOutput2 = new Object[] { "The zipcode of customer ", name, "'s second address is now ", zip, "." };
        log.debug(StringUtils.join(rawOutput2));
    }

    /**
     * Demo dyna beans.
     *
     * @throws Exception
     *             the exception
     */
    @Test
    public void demoDynaBeans() throws Exception{

        log.debug(StringUtils.center(" demoDynaBeans ", 40, "="));

        // creating a DynaBean  
        DynaProperty[] dynaBeanProperties = new DynaProperty[] {//DynaProperty  
        new DynaProperty("name", String.class), new DynaProperty("inPrice", Double.class), new DynaProperty("outPrice", Double.class), };
        BasicDynaClass cargoClass = new BasicDynaClass("Cargo", BasicDynaBean.class, dynaBeanProperties); //BasicDynaClass  BasicDynaBean  
        DynaBean cargo = cargoClass.newInstance();//DynaBean  

        // accessing a DynaBean  
        cargo.set("name", "Instant Noodles");
        cargo.set("inPrice", new Double(21.3));
        cargo.set("outPrice", new Double(23.8));
        log.debug("name: " + cargo.get("name"));
        log.debug("inPrice: " + cargo.get("inPrice"));
        log.debug("outPrice: " + cargo.get("outPrice"));
    }

    /**
     * Copy property.
     */
    @Test
    public void copyProperty(){
        User a = new User();
        a.setId(5L);
        a.setMoney(new BigDecimal(500000));
        a.setDate(new Date());
        User b = new User();
        // DateConverter converter = new DateConverter(DatePattern.forToString, Locale.US);
        ConvertUtils.register(new DateLocaleConverter(Locale.US, DatePattern.forToString), Date.class);

        String[] strs = { "date", "money" };
        BeanUtil.copyProperties(b, a, strs);
        log.info(b.getDate() + "");
        log.info(b.getMoney() + "");
    }

    /**
     * Copy properties.
     */
    @Test
    public void copyProperties(){
        User a = new User();
        a.setId(5L);
        a.setDate(new Date());
        String[] nickName = { "feilong", "飞天奔月", "venusdrogon" };
        a.setNickName(nickName);

        User b = new User();

        String[] aStrings = { "date", "id", "nickName" };
        ConvertUtils.register(new DateLocaleConverter(Locale.US, DatePattern.forToString), Date.class);
        BeanUtil.copyProperties(b, a, aStrings);

        if (log.isDebugEnabled()){
            log.debug(JsonUtil.format(b));
        }

    }

    /**
     * Copy properties1.
     */
    @Test
    public void copyProperties1(){
        SalesOrderDto salesOrderDto = new SalesOrderDto();

        //ConvertUtils.register(new DateLocaleConverter(Locale.US, DatePattern.forToString), Date.class);
        BeanUtil.copyProperties(salesOrderDto, salesOrder);

        if (log.isDebugEnabled()){
            log.debug("salesOrderDto:{}", JsonUtil.format(salesOrderDto));
        }

    }

    /**
     * Describe.
     */
    @Test
    public void describe(){
        User a = new User();
        a.setId(5L);
        Date now = new Date();
        a.setDate(now);

        Map<String, String> map = BeanUtil.describe(a);

        log.info("map:{}", JsonUtil.format(map));
    }

    /**
     * Populate.
     */
    @Test
    public void populate(){
        User a = new User();
        a.setId(5L);
        Date now = new Date();
        a.setDate(now);
        // DateConverter converter = new DateConverter("yyyy");
        // ConvertUtils.register(converter, Date.class);
        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put("id", 8L);
        // properties.put("date", 2010);
        BeanUtil.populate(a, properties);
        log.info(JsonUtil.format(a));
    }

    /**
     * Clone bean.
     */
    @Test
    public void cloneBean(){
        SalesOrder salesOrder1 = BeanUtil.cloneBean(salesOrder);

        if (log.isDebugEnabled()){
            log.debug(JsonUtil.format(salesOrder1));
        }
    }
}
