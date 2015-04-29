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
package com.feilong.commons.core.util.predicate;

import java.io.Serializable;

import org.apache.commons.collections.Predicate;

import com.feilong.commons.core.bean.PropertyUtil;
import com.feilong.commons.core.lang.ObjectUtil;

/**
 * 调用 {@link com.feilong.commons.core.bean.PropertyUtil#getProperty(Object, String)} 匹配属性值.
 *
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.1.2 2015年4月27日 下午1:52:29
 * 
 * @since 1.1.2
 */
public class ObjectPropertyEqualsPredicate implements Predicate,Serializable{

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 288232184048495608L;

    /** The property name. */
    private final String      propertyName;

    /** The value. */
    private final Object      value;

    /**
     * The Constructor.
     *
     * @param propertyName
     *            the property name
     * @param value
     *            the value
     */
    public ObjectPropertyEqualsPredicate(String propertyName, Object value){
        this.propertyName = propertyName;
        this.value = value;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.commons.collections.Predicate#evaluate(java.lang.Object)
     */
    @Override
    public boolean evaluate(Object object){
        Object property = PropertyUtil.getProperty(object, propertyName);
        return ObjectUtil.equals(property, value, true);
    }
}