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
package com.feilong.commons.core.awt.toolkit;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class ClipboardUtilTest.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-5-23 下午02:43:35
 * @since 1.0
 */
public class ClipboardUtilTest{

    /** The Constant log. */
    private static final Logger log = LoggerFactory.getLogger(ClipboardUtilTest.class);

    /**
     * {@link com.feilong.commons.core.awt.toolkit.ClipboardUtil#setClipboardContent(java.lang.String)} 的测试方法。
     *
     * @throws UnsupportedFlavorException
     *             the unsupported flavor exception
     * @throws IOException
     *             the IO exception
     */
    @Test
    public final void testSetClipboardContents() throws UnsupportedFlavorException,IOException{
        log.info(ClipboardUtil.getClipboardContent());
    }

    /**
     * Test.
     */
    @Test
    public void test(){
        String aString = "L,M,S,XL,XS,XXL";
        StringBuilder stringBuilder = new StringBuilder();
        String[] strings = aString.split(",");
        stringBuilder.append("<enum type=\"string\">");
        for (String string : strings){
            stringBuilder.append("<item>" + string + "</item>");
        }
        stringBuilder.append("</enum>");
        ClipboardUtil.setClipboardContent(stringBuilder.toString());
        log.info(stringBuilder.toString());
    }
}