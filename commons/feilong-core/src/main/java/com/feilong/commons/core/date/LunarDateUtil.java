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

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.MessageConstants;

/**
 * 阴历(农历)日期.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2010-2-8 下午04:59:32
 * @see SolarDateUtil
 * @since 1.0.0
 */
@SuppressWarnings("deprecation")
public final class LunarDateUtil{

	/** The Constant log. */
	private final static Logger	log	= LoggerFactory.getLogger(LunarDateUtil.class);

	/**
	 * 获得中文星期.
	 * 
	 * @param week
	 *            星期 日从0开始 1 2 --6
	 * @return 如 星期一
	 */
	public static String getChineseWeek(int week){
		return MessageConstants.DATE_WEEK + DateDictionary.WEEK_CHINESES[week];
	}

	/**
	 * 农历转成阳历The lunar calendar is turned into the Solar calendar.
	 * 
	 * @param lunarYear
	 *            农历年份
	 * @param lunarMonth
	 *            农历月份
	 * @param lunarDay
	 *            农历日期
	 * @return 阳历
	 */
	public static String toSolar(int lunarYear,int lunarMonth,int lunarDay){
		int year;
		int month;
		int day;
		int offSetDays = LunarDateUtil.getLNewYearOffsetDays(lunarYear, lunarMonth, lunarDay)
						+ DateDictionary.SOLAR_AND_LUNAR_OFFSET_TABLE[lunarYear - 1901];
		int yearDays = DateUtil.isLeapYear(lunarYear) ? 366 : 365;
		if (offSetDays >= yearDays){
			year = lunarYear + 1;
			offSetDays -= yearDays;
		}else{
			year = lunarYear;
		}
		day = offSetDays + 1;
		for (month = 1; offSetDays >= 0; ++month){
			day = offSetDays + 1;
			offSetDays -= CalendarUtil.getMaxDayOfMonth(year, month);
		}
		month--;
		return "" + year + (month > 9 ? month + "" : "0" + month) + (day > 9 ? day + "" : "0" + day);
	}

	/**
	 * date 转成农历.
	 * 
	 * @param date
	 *            the date
	 * @return the lunar date string
	 */
	public static String getLunarDateString(Date date){
		int year = DateUtil.getYear(date);
		int month = DateUtil.getMonth(date);
		int day = DateUtil.getDayOfMonth(date);

		// *********************************
		int lundar = Integer.parseInt(SolarDateUtil.toLundar(year, month, day));
		int year_Lunar = lundar / 10000;
		int month_Lunar = lundar % 10000 / 100;
		int day_Lunar = lundar % 100;
		return _getLunarDateString(year_Lunar, month_Lunar, day_Lunar);
	}

	/**
	 * 获得某年某月农历最大的天数.
	 * 
	 * @param iYear
	 *            农历年
	 * @param iMonth
	 *            农历月
	 * @return 获得某年某月农历最大的天数
	 */
	public static int getLunarMonthMaxDays(int iYear,int iMonth){
		int iLeapMonth = _getLeapMonth(iYear);
		if ((iMonth > 12) && (iMonth - 12 != iLeapMonth) || (iMonth < 0)){
			log.error("Wrong month, ^_^ , i think you are want a -1, go to death!");
			return -1;
		}
		if (iMonth - 12 == iLeapMonth){
			if ((DateDictionary.LUNAR_MONTH_DAYS_TABLE[iYear - 1901] & (0x8000 >> iLeapMonth)) == 0){
				return 29;
			}
			return 30;
		}
		if ((iLeapMonth > 0) && (iMonth > iLeapMonth)){
			iMonth++;
		}
		if ((DateDictionary.LUNAR_MONTH_DAYS_TABLE[iYear - 1901] & (0x8000 >> (iMonth - 1))) == 0){
			return 29;
		}
		return 30;
	}

	// *************************************************************************
	/**
	 * 获得农历字符串.
	 * 
	 * @param year_Lunar
	 *            农历年
	 * @param month_Lunar
	 *            农历月
	 * @param day_Lunar
	 *            农历日
	 * @return 阳历年月日获得对应的阴历
	 */
	public static String _getLunarDateString(int year_Lunar,int month_Lunar,int day_Lunar){
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(convertYearToChineseYear(year_Lunar));
		stringBuilder.append("(" + _getChineseGanZhi(year_Lunar) + ")");
		stringBuilder.append("年");
		// *******************月*****************************************
		if (month_Lunar > 12){
			month_Lunar -= 12;
			stringBuilder.append("闰");
		}
		if (month_Lunar == 12){
			stringBuilder.append("腊月");
		}else if (month_Lunar == 11){
			// 农历月份的别称
			//
			// 一月：正月、端月、征月、开岁、华岁、早春、孟春、新正；
			// 二月：命月、如月、丽月、杏月、酣香、仲春；
			// 三月：蚕月、桃月、桐月、季春、晓春、鸢时、桃良、樱笋时；
			// 四月：余月、阴月、梅月、清和月、初夏、孟夏、正阳、朱明；
			// 五月：皋月、榴月、蒲月、仲夏、郁蒸、天中；
			// 六月：且月、焦月、荷月、暑月、伏月、精阳、季夏；
			// 七月：相月、兰月、凉月、瓜月、巧月、孟秋、初秋、早秋；
			// 八月：壮月、桂月、仲秋、中秋、正秋、仲商；
			// 九月：玄月、菊月、青女月、季秋、穷秋、抄秋；
			// 十月：阴月、良月、正阴月、小阳春、初冬、开冬、孟冬；
			// 十一月：幸月、畅月、仲冬；
			// 十二月：涂月、蜡月、腊月、季冬、暮冬、残冬、末冬、嘉平月.
			stringBuilder.append("十一月");// 冬月
		}else if (month_Lunar == 1){
			stringBuilder.append("正月");
		}else{
			stringBuilder.append(DateDictionary.CHINSES_NUMBERS[month_Lunar] + "月");
		}
		// **************day*************************************************
		if (day_Lunar > 29){
			stringBuilder.append("三十");
		}else if (day_Lunar > 20){
			stringBuilder.append("二十" + DateDictionary.CHINSES_NUMBERS[day_Lunar % 20]);
		}else if (day_Lunar == 20){
			stringBuilder.append("二十");
		}else if (day_Lunar > 10){
			stringBuilder.append("十" + DateDictionary.CHINSES_NUMBERS[day_Lunar % 10]);
		}else{
			stringBuilder.append("初" + DateDictionary.CHINSES_NUMBERS[day_Lunar]);
		}
		return stringBuilder.toString();
	}

	/**
	 * 年份转成中文 {@code 2010--->二零一零} .
	 * 
	 * @param year
	 *            年份 2010
	 * @return 年份转成中文
	 */
	public static String convertYearToChineseYear(int year){
		char[] cs = String.valueOf(year).toCharArray();
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < cs.length; ++i){
			Object iObject = cs[i];
			int index = Integer.parseInt(iObject.toString());
			stringBuilder.append(DateDictionary.CHINSES_NUMBERS[index]);
		}
		return stringBuilder.toString();
	}

	/**
	 * 获得闰月.
	 * 
	 * @param year
	 *            the year
	 * @return the int
	 */
	public static int _getLeapMonth(int year){
		char iMonth = DateDictionary.LUNAR_LEAP_MONTH_TABLE[(year - 1901) / 2];
		if (year % 2 == 0){
			return (iMonth & 0x0f);
		}
		return (iMonth & 0xf0) >> 4;
	}

	/**
	 * 根据年份获得干支记法.
	 * 
	 * @param year
	 *            年份
	 * @return 干支
	 */
	private static String _getChineseGanZhi(int year){
		int temp = Math.abs(year - 1924);
		return DateDictionary.HEAVENLY_STEMS[temp % 10] + DateDictionary.EARTHLY_BRANCHES[temp % 12];
	}

	/**
	 * Gets the l new year offset days.
	 * 
	 * @param iYear
	 *            the i year
	 * @param iMonth
	 *            the i month
	 * @param iDay
	 *            the i day
	 * @return the l new year offset days
	 */
	private static int getLNewYearOffsetDays(int iYear,int iMonth,int iDay){
		int iOffsetDays = 0;
		int iLeapMonth = _getLeapMonth(iYear);
		if ((iLeapMonth > 0) && (iLeapMonth == iMonth - 12)){
			iMonth = iLeapMonth;
			iOffsetDays += getLunarMonthMaxDays(iYear, iMonth);
		}
		for (int i = 1; i < iMonth; ++i){
			iOffsetDays += getLunarMonthMaxDays(iYear, i);
			if (i == iLeapMonth){
				iOffsetDays += getLunarMonthMaxDays(iYear, iLeapMonth + 12);
			}
		}
		iOffsetDays += iDay - 1;
		return iOffsetDays;
	}
}