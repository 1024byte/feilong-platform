/**
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
/**
 * This product currently only contains code developed by authors
 * of specific components, as identified by the source code files.
 *
 * Since product implements StAX API, it has dependencies to StAX API
 * classes.
 *
 * For additional credits (generally to people who reported problems)
 * see CREDITS file.
 */
package com.feilong.commons.core.configure;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.enumeration.CharsetType;
import com.feilong.commons.core.io.IOUtil;

/**
 * The Class PropertiesUtilTest.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-5-19 下午03:41:14
 * @since 1.0
 */
public class PropertiesUtilTest{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(PropertiesUtilTest.class);

	/**
	 * Gets the properties value.
	 * 
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test
	public void testGetPropertiesValue() throws IOException{
		String propertiesPath = "I:/Ebook/book.properties";
		InputStream inputStream = IOUtil.getFileInputStream(propertiesPath);
		Properties properties = PropertiesUtil.getProperties(inputStream);
		String key = "锦衣夜行";
		@SuppressWarnings("unused")
		String value = properties.getProperty(key);
		try{
			for (Object iterable_element : properties.keySet()){
				log.info(new String(iterable_element.toString().getBytes(CharsetType.ISO_8859_1), CharsetType.GBK));
			}
		}catch (UnsupportedEncodingException e1){
			e1.printStackTrace();
		}
		// = PropertiesUtil.getPropertiesValue(FeiLongPropertiesUtilTest.class, propertiesPath, "锦衣夜行");
		try{
			inputStream.close();
		}catch (IOException e){
			log.error(e.getClass().getName(), e);
		}
	}

	// @formatter:off

//	public static boolean write(String fileName){
//		// 建立Properties对象
//		Properties properties = new Properties();
//		// 将信息方入Properties对象
//		properties.put("a.b.c", "金鑫");
//		properties.put("aaa", "ppp");
//		// 将信息包存在a.ini文件中
//		try{
//			properties.store(new FileOutputStream(fileName), null);
//		}catch (FileNotFoundException e){
//			log.error(e.getClass().getName(), e);
//		}catch (IOException e){
//			log.error(e.getClass().getName(), e);
//		}
//		return true;
//	}
//
//	public static void read(String fileName){
//		Properties properties = new Properties();
//		// 可以从a.ini中通过Properties.get方法读取配置信息
//		try{
//			properties.load(new FileInputStream(fileName));
//		}catch (FileNotFoundException e){
//			log.error(e.getClass().getName(), e);
//		}catch (IOException e){
//			log.error(e.getClass().getName(), e);
//		}
//		log.debug(properties.get("a.b.c"));
//		log.debug(properties.get("aaa"));
//	}
//
//	/**
//	 * 更新（或插入）一对properties信息(主键及其键值) 如果该主键已经存在，更新该主键的值； 如果该主键不存在，则插件一对键值.
//	 * 
//	 * @param keyname
//	 *            键名
//	 * @param keyvalue
//	 *            键值
//	 */
//	public static void writeProperties(String keyname,String keyvalue){
//		try{
//			// 调用 Hashtable 的方法 put，使用 getProperty 方法提供并行性.
//			// 强制要求为属性的键和值使用字符串.返回值是 Hashtable 调用 put 的结果.
//			OutputStream fos = new FileOutputStream(profilepath);
//			props.setProperty(keyname, keyvalue);
//			// 以适合使用 load 方法加载到 Properties 表中的格式，
//			// 将此 Properties 表中的属性列表（键和元素对）写入输出流
//			props.store(fos, "Update '" + keyname + "' value");
//		}catch (IOException e){
//			System.err.println("属性文件更新错误");
//		}
//	}
//
//	/**
//	 * 更新properties文件的键值对 如果该主键已经存在，更新该主键的值； 如果该主键不存在，则插件一对键值.
//	 * 
//	 * @param keyname
//	 *            键名
//	 * @param keyvalue
//	 *            键值
//	 */
//	public void updateProperties(String keyname,String keyvalue){
//		try{
//			props.load(new FileInputStream(profilepath));
//			// 调用 Hashtable 的方法 put，使用 getProperty 方法提供并行性.
//			// 强制要求为属性的键和值使用字符串.返回值是 Hashtable 调用 put 的结果.
//			OutputStream fos = new FileOutputStream(profilepath);
//			props.setProperty(keyname, keyvalue);
//			// 以适合使用 load 方法加载到 Properties 表中的格式，
//			// 将此 Properties 表中的属性列表（键和元素对）写入输出流
//			props.store(fos, "Update '" + keyname + "' value");
//		}catch (IOException e){
//			System.err.println("属性文件更新错误");
//		}
//	}

	// @formatter:on
}
