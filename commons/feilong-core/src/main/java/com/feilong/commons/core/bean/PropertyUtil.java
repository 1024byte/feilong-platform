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

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class PropertyUtil.
 * 
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.8 2014-7-21 17:45:30
 * @see org.apache.commons.beanutils.PropertyUtils
 * @see com.feilong.commons.core.bean.BeanUtil
 */
public final class PropertyUtil{

	/** The Constant log. */
	private final static Logger	log	= LoggerFactory.getLogger(PropertyUtil.class);

	/** Don't let anyone instantiate this class. */
	private PropertyUtil(){}

	/**
	 * <p>
	 * 把Bean的属性值放入到一个Map里面.
	 * </p>
	 * 
	 * 这个方法返回一个Object中所有的可读属性，并将属性名/属性值放入一个Map中，<br>
	 * 另外还有一个名为class的属性，属性值是Object的类名，事实上class是java.lang.Object的一个属性
	 * 
	 * @param bean
	 *            Bean whose properties are to be extracted
	 * @return The set of properties for the bean
	 * @throws BeanUtilException
	 *             if IllegalAccessException | InvocationTargetException | NoSuchMethodException
	 * @see org.apache.commons.beanutils.BeanUtils#describe(Object)
	 * @see org.apache.commons.beanutils.PropertyUtils#describe(Object)
	 * @see com.feilong.commons.core.bean.BeanUtil#describe(Object)
	 */
	public static Map<String, Object> describe(Object bean) throws BeanUtilException{
		try{
			//Return the entire set of properties for which the specified bean provides a read method.
			Map<String, Object> map = PropertyUtils.describe(bean);
			return map;
		}catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e){
			log.error(e.getClass().getName(), e);
			throw new BeanUtilException(e);
		}
	}

	/**
	 * 使用 {@link PropertyUtils#setProperty(Object, String, Object)} 来设置属性值(<b>不会进行类型转换</b>).
	 * 
	 * <pre>
	 * 
	 * BeanUtils.setProperty(pt1, &quot;x&quot;, &quot;9&quot;); // 这里的9是String类型
	 * PropertyUtils.setProperty(pt1, &quot;x&quot;, 9); // 这里的是int类型
	 * // 这两个类BeanUtils和PropertyUtils,前者能自动将int类型转化，后者不能
	 * </pre>
	 * 
	 * 
	 * <pre>
	 * {@code
	 * getProperty和setProperty,它们都只有2个参数，第一个是JavaBean对象，第二个是要操作的属性名.
	 * Company c = new Company();
	 * c.setName("Simple");
	 *  
	 * 对于Simple类型，参数二直接是属性名即可
	 * //Simple
	 * System.out.println(BeanUtils.getProperty(c, "name"));
	 *  
	 * 对于Map类型，则需要以“属性名（key值）”的形式
	 * //Map
	 *     System.out.println(BeanUtils.getProperty(c, "address (A2)"));
	 *     HashMap am = new HashMap();
	 *     am.put("1","234-222-1222211");
	 *     am.put("2","021-086-1232323");
	 *     BeanUtils.setProperty(c,"telephone",am);
	 * System.out.println(BeanUtils.getProperty(c, "telephone (2)"));
	 *  
	 * 对于Indexed，则为“属性名[索引值]”，注意这里对于ArrayList和数组都可以用一样的方式进行操作.
	 * //index
	 *     System.out.println(BeanUtils.getProperty(c, "otherInfo[2]"));
	 *     BeanUtils.setProperty(c, "product[1]", "NOTES SERVER");
	 *     System.out.println(BeanUtils.getProperty(c, "product[1]"));
	 *  
	 * 当然这3种类也可以组合使用啦！
	 * //nest
	 *     System.out.println(BeanUtils.getProperty(c, "employee[1].name"));
	 * 
	 * }
	 * </pre>
	 * 
	 * @param bean
	 *            Bean whose property is to be modified
	 * @param name
	 *            Possibly indexed and/or nested name of the property to be modified
	 * @param value
	 *            Value to which this property is to be set
	 * @throws BeanUtilException
	 *             if IllegalAccessException | InvocationTargetException | NoSuchMethodException
	 * @see org.apache.commons.beanutils.BeanUtils#setProperty(Object, String, Object)
	 * @see org.apache.commons.beanutils.PropertyUtils#setProperty(Object, String, Object)
	 * @see com.feilong.commons.core.bean.BeanUtil#setProperty(Object, String, Object)
	 */
	public static void setProperty(Object bean,String name,Object value) throws BeanUtilException{
		try{
			//Set the value of the specified property of the specified bean, no matter which property reference format is used, with no type conversions.

			// PropertyUtils的功能类似于BeanUtils,但在底层不会对传递的数据做转换处理
			PropertyUtils.setProperty(bean, name, value);
		}catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e){
			log.error(e.getClass().getName(), e);
			throw new BeanUtilException(e);
		}
	}

	// [start] getProperty

	/**
	 * 使用 {@link PropertyUtils#getProperty(Object, String)} 类从对象中取得属性值.
	 * 
	 * <pre>
	 * {@code
	 * getProperty和setProperty,它们都只有2个参数，第一个是JavaBean对象，第二个是要操作的属性名.
	 * Company c = new Company();
	 * c.setName("Simple");
	 *  
	 * 对于Simple类型，参数二直接是属性名即可
	 * //Simple
	 * System.out.println(BeanUtils.getProperty(c, "name"));
	 *  
	 * 对于Map类型，则需要以“属性名（key值）”的形式
	 * //Map
	 *     System.out.println(BeanUtils.getProperty(c, "address (A2)"));
	 *     HashMap am = new HashMap();
	 *     am.put("1","234-222-1222211");
	 *     am.put("2","021-086-1232323");
	 *     BeanUtils.setProperty(c,"telephone",am);
	 * System.out.println(BeanUtils.getProperty(c, "telephone (2)"));
	 *  
	 * 对于Indexed，则为“属性名[索引值]”，注意这里对于ArrayList和数组都可以用一样的方式进行操作.
	 * //index
	 *     System.out.println(BeanUtils.getProperty(c, "otherInfo[2]"));
	 *     BeanUtils.setProperty(c, "product[1]", "NOTES SERVER");
	 *     System.out.println(BeanUtils.getProperty(c, "product[1]"));
	 *  
	 * 当然这3种类也可以组合使用啦！
	 * //nest
	 *     System.out.println(BeanUtils.getProperty(c, "employee[1].name"));
	 * 
	 * }
	 * </pre>
	 * 
	 * <h3>{@link BeanUtils#getProperty(Object, String)}&{@link PropertyUtils#getProperty(Object, String)}的区别</h3>
	 * 
	 * <blockquote>
	 * <p>
	 * {@link BeanUtils#getProperty(Object, String)} 会将结果转成String返回,<br>
	 * {@link PropertyUtils#getProperty(Object, String)} 结果是Object类型,不会做类型转换
	 * </p>
	 * </blockquote>
	 * 
	 * @param <T>
	 *            the generic type
	 * @param bean
	 *            Bean whose property is to be extracted
	 * @param name
	 *            Possibly indexed and/or nested name of the property to be extracted
	 * 
	 * @return 使用{@link PropertyUtils#getProperty(Object, String)} 从对象中取得属性值
	 * @throws BeanUtilException
	 *             if IllegalAccessException | InvocationTargetException | NoSuchMethodException
	 * @see com.feilong.commons.core.bean.BeanUtil#getProperty(Object, String)
	 * @see org.apache.commons.beanutils.BeanUtils#getProperty(Object, String)
	 * @see org.apache.commons.beanutils.PropertyUtils#getProperty(Object, String)
	 * @see org.apache.commons.beanutils.PropertyUtilsBean
	 */
	public static <T> T getProperty(Object bean,String name) throws BeanUtilException{
		//Return the value of the specified property of the specified bean, no matter which property reference format is used, with no type conversions.
		//For more details see PropertyUtilsBean.
		try{
			@SuppressWarnings("unchecked")
			T propertyValue = (T) PropertyUtils.getProperty(bean, name);
			return propertyValue;
		}catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e){
			log.error(e.getClass().getName(), e);
			throw new BeanUtilException(e);
		}
	}
	// [end]
}