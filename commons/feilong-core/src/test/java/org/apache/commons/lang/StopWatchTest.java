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
package org.apache.commons.lang;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.8 2014年7月25日 下午6:14:14
 * @since 1.0.8
 */
public class StopWatchTest{

	private static final Logger	log	= LoggerFactory.getLogger(StopWatchTest.class);

	/**
	 * TestMapUtilTest.
	 */
	@Test
	public void testMapUtilTest5(){
		StopWatch sw = new StopWatch();
		sw.start();
		for (Iterator iterator = DateUtils.iterator(new Date(), DateUtils.RANGE_WEEK_CENTER); iterator.hasNext();){
			Calendar cal = (Calendar) iterator.next();
			System.out.println(DateFormatUtils.format(cal.getTime(), "yy-MM-dd HH:mm"));
		}
		sw.stop();
		System.out.println("秒表计时:" + sw.getTime());

		//assertEquals(expected, actual);
	}
}
