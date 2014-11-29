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

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.bean.BeanUtilException;
import com.feilong.commons.core.enumeration.HttpMethodType;

/**
 * The Class EnumUtilTest.
 * 
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.6 2014年5月12日 下午11:00:42
 * @since 1.0.6
 */
public class EnumUtilTest{

	/** The Constant log. */
	@SuppressWarnings("unused")
	private static final Logger	log	= LoggerFactory.getLogger(EnumUtilTest.class);

	/**
	 * Test method for {@link com.feilong.commons.core.lang.EnumUtil#getEnumByPropertyValue(Class, String, Object)}.
	 * 
	 * @throws IllegalArgumentException
	 *             the illegal argument exception
	 * @throws NoSuchFieldException
	 *             the no such field exception
	 */
	@Test
	public final void testGetEnum() throws IllegalArgumentException,NoSuchFieldException{
		assertEquals(HttpMethodType.GET, EnumUtil.getEnumByPropertyValueIgnoreCase(HttpMethodType.class, "method", "get"));
	}

	/**
	 * Test get http method type.
	 *
	 * @throws IllegalArgumentException
	 *             the illegal argument exception
	 * @throws NoSuchFieldException
	 *             the no such field exception
	 */
	@Test
	public final void testGetHttpMethodType() throws IllegalArgumentException,NoSuchFieldException{
		assertEquals(HttpMethodType.POST, EnumUtil.getEnumByPropertyValueIgnoreCase(HttpMethodType.class, "method", "post"));
		assertEquals(HttpMethodType.POST, EnumUtil.getEnumByPropertyValueIgnoreCase(HttpMethodType.class, "method", "pOst"));
		assertEquals(HttpMethodType.POST, EnumUtil.getEnumByPropertyValueIgnoreCase(HttpMethodType.class, "method", "POST"));
		assertEquals(HttpMethodType.POST, EnumUtil.getEnumByPropertyValueIgnoreCase(HttpMethodType.class, "method", "posT"));
		assertEquals(HttpMethodType.GET, EnumUtil.getEnumByPropertyValueIgnoreCase(HttpMethodType.class, "method", "get"));
		assertEquals(HttpMethodType.GET, EnumUtil.getEnumByPropertyValueIgnoreCase(HttpMethodType.class, "method", "gEt"));
		assertEquals(HttpMethodType.GET, EnumUtil.getEnumByPropertyValueIgnoreCase(HttpMethodType.class, "method", "geT"));
		assertEquals(HttpMethodType.GET, EnumUtil.getEnumByPropertyValueIgnoreCase(HttpMethodType.class, "method", "GET"));

	}

	/**
	 * Test get http method type1.
	 * 
	 * @throws IllegalArgumentException
	 *             the illegal argument exception
	 * @throws NoSuchFieldException
	 *             the no such field exception
	 */
	@Test(expected = NoSuchFieldException.class)
	public final void testGetHttpMethodType1() throws IllegalArgumentException,NoSuchFieldException{
		assertEquals(null, EnumUtil.getEnumByPropertyValueIgnoreCase(HttpMethodType.class, "method", "post111"));
	}

	/**
	 * Test get http method type2.
	 * 
	 * @throws IllegalArgumentException
	 *             the illegal argument exception
	 * @throws NoSuchFieldException
	 *             the no such field exception
	 */
	@Test(expected = NoSuchFieldException.class)
	public final void testGetHttpMethodType2() throws IllegalArgumentException,NoSuchFieldException{
		assertEquals(null, EnumUtil.getEnumByPropertyValueIgnoreCase(HttpMethodType.class, "method", ""));
	}

	/**
	 * Test get http method type3.
	 * 
	 * @throws IllegalArgumentException
	 *             the illegal argument exception
	 * @throws NoSuchFieldException
	 *             the no such field exception
	 */
	@Test(expected = NoSuchFieldException.class)
	public final void testGetHttpMethodType3() throws IllegalArgumentException,NoSuchFieldException{
		assertEquals(null, EnumUtil.getEnumByPropertyValueIgnoreCase(HttpMethodType.class, "method", null));
	}

	/**
	 * Test get http method type4.
	 *
	 * @throws IllegalArgumentException
	 *             the illegal argument exception
	 * @throws NoSuchFieldException
	 *             the no such field exception
	 * @throws BeanUtilException
	 *             the bean util exception
	 */
	@Test(expected = BeanUtilException.class)
	public final void testGetHttpMethodType4() throws IllegalArgumentException,NoSuchFieldException,BeanUtilException{
		assertEquals(null, EnumUtil.getEnumByPropertyValueIgnoreCase(HttpMethodType.class, "method2222", null));
	}
}
