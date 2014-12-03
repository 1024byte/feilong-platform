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
package com.feilong.commons.core.configure;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.text.MessageFormatUtil;
import com.feilong.commons.core.util.StringUtil;
import com.feilong.commons.core.util.Validator;

/**
 * ResourceBundle 工具类.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-11-11 上午10:24:25
 * @see MessageFormatUtil#format(String, Object...)
 * @see java.util.ResourceBundle
 * @since 1.0.0
 */
public final class ResourceBundleUtil implements BaseConfigure{

	/** The Constant log. */
	private final static Logger	log	= LoggerFactory.getLogger(ResourceBundleUtil.class);

	/**
	 * 获取Properties配置文件键值,按照typeClass 返回对应的类型.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param baseName
	 *            配置文件的包+类全名(不要尾缀),the base name of the resource bundle, a fully qualified class name
	 * @param key
	 *            the key
	 * @param typeClass
	 *            指明返回类型,<br>
	 *            如果是String.class,则返回的是String <br>
	 *            如果是Integer.class,则返回的是Integer
	 * @return the value
	 * @see #getValue(String, String)
	 * @see com.feilong.commons.core.util.StringUtil#toT(String, Class)
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getValue(String baseName,String key,Class<?> typeClass){
		String value = getValue(baseName, key);
		return (T) StringUtil.toT(value, typeClass);
	}

	/**
	 * 获取Properties配置文件键值,按照typeClass 返回对应的类型.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param resourceBundle
	 *            the resource bundle
	 * @param key
	 *            the key
	 * @param typeClass
	 *            指明返回类型,<br>
	 *            如果是String.class,则返回的是String <br>
	 *            如果是Integer.class,则返回的是Integer
	 * @return the value
	 * @see #getValue(ResourceBundle, String)
	 * @see com.feilong.commons.core.util.StringUtil#toT(String, Class)
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getValue(ResourceBundle resourceBundle,String key,Class<?> typeClass){
		String value = getValue(resourceBundle, key);
		return (T) StringUtil.toT(value, typeClass);
	}

	/**
	 * 获取Properties配置文件键值 ,采用java.util.ResourceBundle类的getBundle()方法来读取
	 *
	 * @param baseName
	 *            配置文件的包+类全名(不要尾缀),the base name of the resource bundle, a fully qualified class name
	 * @param key
	 *            Properties配置文件键名
	 * @return 该键的值
	 * @see #getResourceBundle(String)
	 * @see #getValue(ResourceBundle, String)
	 * @since 1.0
	 */
	public static String getValue(String baseName,String key){
		ResourceBundle resourceBundle = getResourceBundle(baseName);
		return getValue(resourceBundle, key);
	}

	/**
	 * 获取Properties配置文件键值 ,采用java.util.ResourceBundle类的getBundle()方法来读取
	 *
	 * @param baseName
	 *            配置文件的包+类全名(不要尾缀),the base name of the resource bundle, a fully qualified class name
	 * @param key
	 *            Properties配置文件键名
	 * @param locale
	 *            the locale
	 * @return 该键的值
	 * @see #getResourceBundle(String, Locale)
	 * @see #getValue(ResourceBundle, String)
	 * @since 1.0.5
	 */
	public static String getValue(String baseName,String key,Locale locale){
		ResourceBundle resourceBundle = getResourceBundle(baseName, locale);
		return getValue(resourceBundle, key);
	}

	/**
	 * 带参数的 配置文件<br>
	 * 格式如:name={0}.
	 * 
	 * @param baseName
	 *            配置文件的包+类全名(不要尾缀),the base name of the resource bundle, a fully qualified class name
	 * @param key
	 *            the key
	 * @param locale
	 *            the locale
	 * @param arguments
	 *            此处可以传递Object[]数组过来
	 * @return the value with arguments
	 * @see #getResourceBundle(String, Locale)
	 * @see #getValueWithArguments(ResourceBundle, String, Object...)
	 */
	public static String getValueWithArguments(String baseName,String key,Locale locale,Object...arguments){
		ResourceBundle resourceBundle = getResourceBundle(baseName, locale);
		return getValueWithArguments(resourceBundle, key, arguments);
	}

	/**
	 * 获取Properties配置文件键值 ,采用java.util.ResourceBundle类的getBundle()方法来读取
	 * 
	 * @param resourceBundle
	 *            配置文件的包+类全名(不要尾缀)
	 * @param key
	 *            Properties配置文件键名
	 * @return 该键的值<br>
	 *         如果配置文件中,
	 *         <ul>
	 *         <li>key不存在,log.warn 输出警告,然后返回null</li>
	 *         <li>key存在,但value是null 或者 empty,log.warn 输出警告,然后返回value</li>
	 *         </ul>
	 * @see java.util.ResourceBundle#getString(String)
	 */
	public static String getValue(ResourceBundle resourceBundle,String key){
		if (!resourceBundle.containsKey(key)){
			log.warn("resourceBundle don't containsKey:[{}]", key);
		}else{
			try{
				String value = resourceBundle.getString(key);
				if (Validator.isNullOrEmpty(value)){
					log.warn("resourceBundle has key:[{}],but value is null/empty", key);
				}
				return value;
			}catch (Exception e){
				log.error(e.getMessage());
			}
		}
		return null;
	}

	/**
	 * 带参数的 配置文件<br>
	 * 格式如:name={0}.
	 * 
	 * @param resourceBundle
	 *            the resource bundle
	 * @param key
	 *            如上面的 name
	 * @param arguments
	 *            此处可以传递Object[]数组过来
	 * @return 支持 arguments 为null,原样返回
	 * @see MessageFormatUtil
	 * @see MessageFormatUtil#format(String, Object...)
	 */
	public static String getValueWithArguments(ResourceBundle resourceBundle,String key,Object...arguments){
		String value = getValue(resourceBundle, key);
		if (Validator.isNullOrEmpty(value)){
			return null;
		}
		// 支持 arguments 为null,原样返回
		return MessageFormatUtil.format(value, arguments);
	}

	// *****************************************************************************
	/**
	 * 读取值,转成数组,<br>
	 * 默认调用 getArray(baseName, key, spliter, String.class) 形式
	 * 
	 * @param baseName
	 *            配置文件的包+类全名(不要尾缀),the base name of the resource bundle, a fully qualified class name
	 * @param key
	 *            the key
	 * @param spliter
	 *            分隔符
	 * @return 以value.split(spliter),如果 资源值不存在,返回null
	 * @see #getArray(ResourceBundle, String, String, Class)
	 */
	public static String[] getArray(String baseName,String key,String spliter){
		return getArray(baseName, key, spliter, String.class);
	}

	/**
	 * 读取值,转成数组,<br>
	 * 默认调用 getArray(resourceBundle, key, spliter, String.class) 形式
	 * 
	 * @param resourceBundle
	 *            the resource bundle
	 * @param key
	 *            the key
	 * @param spliter
	 *            分隔符
	 * @return 以value.split(spliter),如果 资源值不存在,返回null
	 * @see #getArray(ResourceBundle, String, String, Class)
	 */
	public static String[] getArray(ResourceBundle resourceBundle,String key,String spliter){
		return getArray(resourceBundle, key, spliter, String.class);
	}

	/**
	 * 读取值,转成数组.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param baseName
	 *            配置文件的包+类全名(不要尾缀),the base name of the resource bundle, a fully qualified class name
	 * @param key
	 *            the key
	 * @param spliter
	 *            分隔符
	 * @param typeClass
	 *            指明返回类型,<br>
	 *            如果是String.class,则返回的是String []数组<br>
	 *            如果是Integer.class,则返回的是Integer [] 数组
	 * @return 以value.split(spliter),如果 资源值不存在,返回null
	 * @see #getResourceBundle(String)
	 * @see #getArray(ResourceBundle, String, String, Class)
	 */
	@SuppressWarnings("unchecked")
	public static <T> T[] getArray(String baseName,String key,String spliter,Class<?> typeClass){
		ResourceBundle resourceBundle = getResourceBundle(baseName);
		return (T[]) getArray(resourceBundle, key, spliter, typeClass);
	}

	/**
	 * 读取值,转成数组.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param resourceBundle
	 *            the resource bundle
	 * @param key
	 *            the key
	 * @param spliter
	 *            分隔符
	 * @param typeClass
	 *            指明返回类型,<br>
	 *            如果是String.class,则返回的是String []数组<br>
	 *            如果是Integer.class,则返回的是Integer [] 数组
	 * @return 以value.split(spliter),如果 资源值不存在,返回null
	 * @see #getValue(ResourceBundle, String)
	 * @see com.feilong.commons.core.util.StringUtil#splitToTArray(String, String, Class)
	 */
	@SuppressWarnings("unchecked")
	public static <T> T[] getArray(ResourceBundle resourceBundle,String key,String spliter,Class<?> typeClass){
		String value = getValue(resourceBundle, key);
		return (T[]) StringUtil.splitToTArray(value, spliter, typeClass);
	}

	// **************************************************************************
	/**
	 * Read prefix as map(HashMap).
	 * 
	 * @param baseName
	 *            配置文件的包+类全名(不要尾缀),the base name of the resource bundle, a fully qualified class name
	 * @param prefix
	 *            前缀
	 * @param spliter
	 *            the spliter
	 * @param locale
	 *            the locale
	 * @return 如果 baseName 没有key value,则返回null,否则,解析所有的key和value转成HashMap
	 * @see #readAllPropertiesToMap(String, Locale)
	 */
	public static Map<String, String> readPrefixAsMap(String baseName,String prefix,String spliter,Locale locale){
		Map<String, String> propertyMap = readAllPropertiesToMap(baseName, locale);
		if (Validator.isNotNullOrEmpty(propertyMap)){
			Map<String, String> result = new HashMap<String, String>();
			for (Map.Entry<String, String> entry : propertyMap.entrySet()){
				String key = entry.getKey();
				String value = entry.getValue();
				// 以 prefix 开头
				if (key.startsWith(prefix)){
					// 分隔
					String[] values = key.split(spliter);
					if (values.length >= 2){
						result.put(values[1], value);
					}
				}
			}
			return result;
		}
		return null;
	}

	/**
	 * 读取配置文件,将k/v 统统转成map(HashMap).
	 * 
	 * @param baseName
	 *            配置文件的包+类全名(不要尾缀),the base name of the resource bundle, a fully qualified class name
	 * @param locale
	 *            the locale 支持国际化
	 * @return 如果 baseName 没有key value,则返回null,否则,解析所有的key和value转成HashMap
	 * @see #getResourceBundle(String, Locale)
	 * @see java.util.ResourceBundle#getKeys()
	 * @see org.apache.commons.collections.MapUtils#toMap(ResourceBundle)
	 */
	public static Map<String, String> readAllPropertiesToMap(String baseName,Locale locale){
		ResourceBundle resourceBundle = getResourceBundle(baseName, locale);
		Enumeration<String> enumeration = resourceBundle.getKeys();
		if (Validator.isNotNullOrEmpty(enumeration)){
			Map<String, String> propertyMap = new HashMap<String, String>();
			while (enumeration.hasMoreElements()){
				String key = enumeration.nextElement();
				String value = resourceBundle.getString(key);
				propertyMap.put(key, value);
			}
			return propertyMap;
		}
		return null;
	}

	/**
	 * 获得ResourceBundle.
	 * 
	 * @param baseName
	 *            the base name of the resource bundle, a fully qualified class name
	 * @return the resource bundle
	 * @see java.util.Locale#getDefault()
	 * @see #getResourceBundle(String, Locale)
	 */
	public static ResourceBundle getResourceBundle(String baseName){
		// Locale enLoc = new Locale("en", "US"); // 表示美国地区
		return getResourceBundle(baseName, Locale.getDefault());
	}

	/**
	 * 获得ResourceBundle.
	 * 
	 * @param baseName
	 *            配置文件的包+类全名(不要尾缀),the base name of the resource bundle, a fully qualified class name
	 * @param locale
	 *            the locale for which a resource bundle is desired
	 * @return the resource bundle,may be null
	 * @see java.util.ResourceBundle#getBundle(String, Locale)
	 */
	public static ResourceBundle getResourceBundle(String baseName,Locale locale){
		if (Validator.isNullOrEmpty(baseName)){
			throw new IllegalArgumentException("baseName can't be null/empty!");
		}
		if (Validator.isNullOrEmpty(locale)){
			throw new IllegalArgumentException("locale can't be null/empty!");
		}
		ResourceBundle resourceBundle = ResourceBundle.getBundle(baseName, locale);
		if (null == resourceBundle){
			log.warn("resourceBundle is null,baseName:{},locale:{}", resourceBundle, baseName, locale);
		}
		return resourceBundle;
	}
}
