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
package com.feilong.commons.core.date;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.enumeration.DatePattern;
import com.feilong.commons.core.tools.json.JsonUtil;

/**
 * The Class DateExtensionUtilTest.
 *
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.8 2014年7月31日 下午2:48:22
 * @since 1.0.8
 */
public class DateExtensionUtilTest extends BaseDateUtilTest{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(DateExtensionUtilTest.class);

	/**
	 * Test get interval for view long.
	 */
	@Test
	public void testGetIntervalForViewLong(){
		log.debug(DateExtensionUtil.getIntervalForView(25841));
		log.debug(DateExtensionUtil.getIntervalForView(0));
	}

	/**
	 * Test get chinese week.
	 */
	@Test
	public void testGetChineseWeek(){
		log.debug(DateExtensionUtil.getChineseWeek(0));
	}

	/**
	 * Test get interval day list.
	 */
	@Test
	public void testGetIntervalDayList(){
		List<Date> dates = DateExtensionUtil.getIntervalDayList(fromString, toString, DatePattern.commonWithTime);
		for (int i = 0; i < dates.size(); ++i){
			log.debug(DateUtil.date2String(dates.get(i), DatePattern.commonWithTime));
		}
	}

	/**
	 * Test get interval for view.
	 */
	@Test
	public void testGetIntervalForView(){
		Date now = DateUtil.string2Date("2011-05-19 11:31:25.456", DatePattern.commonWithTime);
		now = new Date();
		Date date = DateUtil.string2Date("2012-12-03 00:00:00", DatePattern.commonWithTime);
		log.debug(DateExtensionUtil.getIntervalForView(now, date));
		long intervalTime = DateUtil.getIntervalTime(now, date);
		log.debug(intervalTime + "");
	}

	/**
	 * 将传入的date转换为中国特色日期
	 * 
	 * <pre>
	 * 如果 现在是 2012-10-18 14:16:00
	 * 
	 * 给你个时间, 
	 * 2012-10-18 14:15:02,要你显示成   "58秒前"
	 * 2012-10-18 14:14:22,要你显示成   "1分钟前"
	 * 2012-10-18 13:55:00,要你显示成   "19分钟前"
	 * 2012-10-17 14:15:02,要你显示成   "昨天 14:15"
	 * 2012-10-16 14:15:02,要你显示成   "前天 14:15"
	 * 2012-10-15 14:15:02,要你显示成   "10-15 14:15"
	 * 2012-09-15 14:15:02,要你显示成   "09-15 14:15"
	 * 2011-09-15 14:15:02,要你显示成   "2011-09-15 14:15"
	 * 
	 * </pre>
	 * 
	 * .
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void testToHumanizationDateString(){

		log.debug(DateExtensionUtil.toHumanizationDateString(DateUtil.string2Date("2012-10-18 13:55:00", DatePattern.commonWithTime)));
		log.debug(DateExtensionUtil.toHumanizationDateString(DateUtil.string2Date("2012-10-18 14:14:22", DatePattern.commonWithTime)));
		log.debug(DateExtensionUtil.toHumanizationDateString(DateUtil.string2Date("2012-10-18 14:15:22", DatePattern.commonWithTime)));
		log.debug(DateExtensionUtil.toHumanizationDateString(DateUtil.string2Date("2012-10-17 14:15:02", DatePattern.commonWithTime)));
		log.debug(DateExtensionUtil.toHumanizationDateString(DateUtil.string2Date("2012-10-16 14:15:02", DatePattern.commonWithTime)));
		log.debug(DateExtensionUtil.toHumanizationDateString(DateUtil.string2Date("2012-10-15 14:15:02", DatePattern.commonWithTime)));
		log.debug(DateExtensionUtil.toHumanizationDateString(DateUtil.string2Date("2012-09-15 14:15:02", DatePattern.commonWithTime)));
		log.debug(DateExtensionUtil.toHumanizationDateString(DateUtil.string2Date("2011-09-15 14:15:02", DatePattern.commonWithTime)));
		log.debug(DateExtensionUtil.toHumanizationDateString(DateUtil.string2Date("2012-12-03 00:00:00", DatePattern.commonWithTime)));
	}

	/**
	 * TestDateUtilTest.
	 */
	@Test
	public void testDateUtilTest(){
		List<Date> dateList = new ArrayList<>();
		dateList.add(new Date());
		dateList.add(new Date());
		dateList.add(new Date());
		dateList.add(new Date());

		if (log.isDebugEnabled()){
			log.debug(JsonUtil.format(DateExtensionUtil.toStringList(dateList, DatePattern.commonWithMillisecond)));
		}

		
	}

	/**
	 * Test get extent yesterday.
	 */
	@Test
	public final void testGetExtentYesterday(){
		Date[] dates = DateExtensionUtil.getExtentYesterday();
		for (Date date : dates){
			logDate(date);
		}
	}

	/**
	 * Test get extent today.
	 */
	@Test
	public final void testGetExtentToday(){
		Date[] dates = DateExtensionUtil.getExtentToday();
		for (Date date : dates){
			log.debug(DateUtil.date2String(date, DatePattern.commonWithMillisecond));
		}
	}
}