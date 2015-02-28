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

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.enumeration.CharsetType;
import com.feilong.commons.core.tools.json.JsonUtil;

/**
 * The Class URIUtilTest.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-4-5 下午4:11:17
 */
public class URIUtilTest{

	/** The Constant log. */
	private static final Logger	log		= LoggerFactory.getLogger(URIUtilTest.class);

	/** The result. */
	private String				result	= null;

	/**
	 * Test encode.
	 */
	@Test
	public void testEncode(){
		String value = "={}[]今天天气很不错今天天气很不错今天天气很不错今天天气很不错今天天气很不错";
		value = "http://xy2.cbg.163.com/cgi-bin/equipquery.py?server_name=风花雪月&query_order=selling_time DESC&search_page&areaid=2&server_id=63&act=search_browse&equip_type_ids&search_text=斩妖剑";
		value = "斩妖剑";
		value = "风花雪月";
		log.info(URIUtil.encode(value, CharsetType.UTF8));
		value = "景儿,么么哒";
		log.info(URIUtil.encode(value, CharsetType.UTF8));

		value = "白色/黑色/纹理浅麻灰";
		result = URIUtil.encode(value, CharsetType.UTF8);
		log.info(result);
		log.info(URIUtil.encode(value, CharsetType.GBK));

		result = URIUtil.encode("Lifestyle / Graphic,", CharsetType.UTF8);
		log.info(result);
	}

	/**
	 * Test get union url.
	 */
	@Test
	public void testGetUnionUrl(){
		result = URIUtil.getUnionUrl("E:\\test", "sanguo");
		log.info(result);
	}

	/**
	 * Test get union url2.
	 *
	 * @throws MalformedURLException
	 *             the malformed url exception
	 */
	@Test
	public void testGetUnionUrl2() throws MalformedURLException{
		URL url = new URL("http://www.exiaoshuo.com/jinyiyexing/");
		result = URIUtil.getUnionUrl(url, "/jinyiyexing/1173348/");
		log.info(result);
	}

	/**
	 * Decode.
	 */
	@Test
	public void decode(){
		//		result = URIUtil.decode("上海", CharsetType.GBK);
		//		log.info(result);
		//		result = URIUtil.decode("Lifestyle+%2F+Graphic,", CharsetType.UTF8);
		//		log.info(result);
		//		result = URIUtil.decode("%E6%88%91%E7%88%B1%E5%BC%A0%E5%85%88%E6%B3%BD%7E%7E%7E%40%E5%BC%A0%E5%85%88%E6%B3%BD", CharsetType.UTF8);
		//		log.info(result);
		result = URIUtil.decode(
						"%E9%87%91%E6%80%BB%EF%BC%8C%E4%BD%A0%E6%83%B3%E6%80%8E%E4%B9%88%E4%B9%88%EF%BC%8C%E5%B0%B1%E6%80%8E%E4%B9%88%E4%B9%88",
						CharsetType.UTF8);
		log.info(result);

	}

	/**
	 * Special char to hex string.
	 */
	@Test
	public void specialCharToHexString(){
		result = URIUtil.specialCharToHexString(" ");
		log.info(result);
	}

	/**
	 * Creates the.
	 */
	@Test
	public void create(){
		String url = "http://127.0.0.1/cmens/t-b-f-a-c-s-f-p-g-e-i-o.htm?a=1&a=2";
		// url = "/cmens/t-b-f-a-c-s-f-p400-600,0-200,200-400,600-up-gCold Gear-eBase Layer-i1-o.htm";

		//		String queryString = null;
		//		queryString = "'\"--></style></script><script>netsparker(0x0000E1)</script>=";
		// queryString = "'%22--%3E%3C/style%3E%3C/script%3E%3Cscript%3Enetsparker(0x0000E1)%3C/script%3E=";

		// url = url + "?" + queryString;
		log.info(url);
		// URIEditor uriEditor = new URIEditor();
		// uriEditor.setAsText(url);
		// log.info(URIEditor);
		// try{
		// URL url1 = new URL(url);
		// log.info(url1.toString());
		// }catch (MalformedURLException e){
		// log.error(e.getClass().getName(), e);
		// }
		URI uri = URIUtil.create(url, CharsetType.UTF8);
		log.info(uri.toString());
	}

	/**
	 * Gets the encoded url by value map.
	 * 
	 */
	@Test
	public void testGetEncodedUrlByValueMap(){
		String beforeUrl = "www.baidu.com";
		Map<String, String> keyAndValueMap = new HashMap<String, String>();
		keyAndValueMap.put("a", "aaaa");
		String charsetType = CharsetType.UTF8;
		log.info(URIUtil.getEncodedUrlByValueMap(beforeUrl, keyAndValueMap, charsetType));
		log.info(URIUtil.getEncodedUrlByValueMap(beforeUrl, null, charsetType));
		log.info(URIUtil.getEncodedUrlByValueMap(beforeUrl, null, null));
	}

	/**
	 * Gets the encoded url by array map.
	 * 
	 */
	@Test
	public void testGetEncodedUrlByArrayMap(){
		String beforeUrl = "www.baidu.com";
		Map<String, String[]> keyAndArrayMap = new HashMap<String, String[]>();
		keyAndArrayMap.put("a", new String[] { "aaaa", "bbbb" });
		String charsetType = CharsetType.UTF8;
		log.info(URIUtil.getEncodedUrlByArrayMap(beforeUrl, keyAndArrayMap, charsetType));
		log.info(URIUtil.getEncodedUrlByArrayMap(beforeUrl, null, charsetType));
		log.info(URIUtil.getEncodedUrlByArrayMap(beforeUrl, null, null));
		log.info(URIUtil.getEncodedUrlByArrayMap(null, keyAndArrayMap, null));
	}

	/**
	 * Combine query string.
	 */
	@Test
	public void combineQueryString(){
		//		String beforeUrl = "www.baidu.com";
		Map<String, String[]> keyAndArrayMap = new HashMap<String, String[]>();
		keyAndArrayMap.put("a", new String[] { "aaaa", "bbbb" });
		String charsetType = CharsetType.UTF8;
		log.info(URIUtil.combineQueryString(keyAndArrayMap, charsetType));
		log.info(URIUtil.combineQueryString(null, charsetType));
		log.info(URIUtil.combineQueryString(null, null));
		log.info(URIUtil.combineQueryString(keyAndArrayMap, null));
	}

	/**
	 * Parses the query to value map.
	 */
	@Test
	public void parseQueryToValueMap(){
		log.info(JsonUtil.format(URIUtil.parseQueryToValueMap("a=1&b=2&a=3", CharsetType.UTF8)));
		log.info(JsonUtil.format(URIUtil.parseQueryToValueMap("a=", CharsetType.UTF8)));
		log.info(JsonUtil.format(URIUtil.parseQueryToValueMap("a=1&", CharsetType.UTF8)));
		log.info(JsonUtil.format(URIUtil.parseQueryToValueMap("", CharsetType.UTF8)));

	}

	/**
	 * Parses the query to value map.
	 */
	@Test
	public void parseQueryToValueMap12(){
		log.info(JsonUtil.format(URIUtil
						.parseQueryToValueMap(
										"subject=%E4%B8%8A%E6%B5%B7%E5%AE%9D%E5%B0%8A%E7%94%B5%E5%95%86&sign_type=MD5&notify_url=http%3A%2F%2Fwww.gymboshop.com%2Fpay%2FdoNotify%2F1.htm&out_trade_no=2014072210034383&return_url=http%3A%2F%2Fwww.gymboshop.com%2Fpay%2FdoReturn%2F1.htm&sign=a6e7dfc7b6dd54a5cd5e8ca91302f934&_input_charset=UTF-8&it_b_pay=50m&total_fee=171.00&error_notify_url=http%3A%2F%2Fwww.gymboshop.com%2Fpay%2FnotifyError.htm%3Ftype%3D1&service=create_direct_pay_by_user&paymethod=directPay&partner=2088511258288082&anti_phishing_key=KP3FUWbOTF63CIcXqg%3D%3D&seller_email=pay%40gymboree.com.cn&payment_type=1",
										CharsetType.UTF8)));
	}

	/**
	 * Parses the query to value map1.
	 */
	@Test
	public void parseQueryToValueMap1(){
		log.info(JsonUtil.format(URIUtil.parseQueryToArrayMap("a=1&b=2&a", CharsetType.UTF8)));
	}
}
