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
package com.feilong.commons.core.util.comparator;

import java.util.Comparator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.bean.PropertyUtil;

/**
 * 属性比较器, 自动获取 <code>T</code>中的属性名字是 {@link #propertyName}的值, 进行比较.
 * <p>
 * 注意:propertyName取出来的值,需要实现 {@link Comparable}接口, 比如 {@link Integer}, {@link String}等类型
 * </p>
 *
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.1.2 2015年5月21日 上午11:02:42
 * @param <T>
 *            the generic type
 * @see "org.springframework.beans.support.PropertyComparator"
 * @see org.apache.commons.collections.comparators.BooleanComparator
 * @see org.apache.commons.collections.comparators.ReverseComparator
 * @since 1.1.2
 */
public class PropertyComparator<T> implements Comparator<T>{

    /** The Constant log. */
    private static final Logger log = LoggerFactory.getLogger(PropertyComparator.class);

    /** T对象中的属性名称. */
    private final String        propertyName;

    /*
     * (non-Javadoc)
     * 
     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
     */
    /**
     * The Constructor.
     *
     * @param propertyName
     *            T对象中的属性名称
     */
    public PropertyComparator(String propertyName){
        super();
        this.propertyName = propertyName;
        log.info("propertyName:[{}]", propertyName);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
     */
    @Override
    public int compare(T t1,T t2){
        Comparable<Comparable<?>> propertyValue1 = PropertyUtil.getProperty(t1, propertyName);
        Comparable<?> propertyValue2 = PropertyUtil.getProperty(t2, propertyName);

        int compareTo = propertyValue1.compareTo(propertyValue2);
        log.debug(
                        "propertyName:[{}],propertyValue1:[{}],propertyValue2:[{}],compareTo:[{}]",
                        propertyName,
                        propertyValue1,
                        propertyValue2,
                        compareTo);
        return compareTo;
    }
}
