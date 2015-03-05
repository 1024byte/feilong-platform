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
package com.feilong.commons.core.net;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.io.CharsetType;

/**
 * The Class URLConnectionUtilTest.
 *
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Sep 27, 2013 10:55:18 AM
 */
public class URLConnectionUtilTest{

    /** The Constant log. */
    private static final Logger log          = LoggerFactory.getLogger(URLConnectionUtilTest.class);

    /** The url string. */
    private String              urlString    = "http://www.nikestore.com.cn/?t=" + System.currentTimeMillis();

    /** The proxy address. */
    private String              proxyAddress = "10.8.12.205";

    /** The proxy port. */
    private int                 proxyPort    = 3128;

    /**
     * 获得 response body as string.
     */
    @Test
    public final void getResponseBodyAsString(){
        String templateFile = "http://10.8.25.80:6666/template.csv?sign=123456";

        HttpURLConnectionParam httpURLConnectionParam = new HttpURLConnectionParam();
        httpURLConnectionParam.setContentCharset(CharsetType.GBK);

        String responseBodyAsString = URLConnectionUtil.getResponseBodyAsString(templateFile, httpURLConnectionParam);
        log.info(responseBodyAsString);
    }
}
