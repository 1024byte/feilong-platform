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
package jdk.java.net;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

import com.feilong.commons.core.net.InetAddressUtil;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-1-12 上午09:44:38
 */
public class InetAddressTest{

	public static void main(String[] args){
		String ipString = "127.0.0.1";
		byte[] ips1 = ipString.getBytes();
		byte[] ips = new byte[] { (byte) 127, (byte) 0, 0, 1 };
		test();
	}

	public static void test1(){
		Enumeration<NetworkInterface> netInterfaces = null;
		try{
			netInterfaces = NetworkInterface.getNetworkInterfaces();
			while (netInterfaces.hasMoreElements()){
				NetworkInterface ni = netInterfaces.nextElement();
				System.out.println("DisplayName:" + ni.getDisplayName());
				System.out.println("Name:" + ni.getName());
				Enumeration<InetAddress> ips = ni.getInetAddresses();
				while (ips.hasMoreElements()){
					System.out.println("IP:" + ips.nextElement().getHostAddress());
				}
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	public static void test(){
		try{
			// 调用中止前的时间（以毫秒为单位）
			int timeOut = 3000;
			int retry = 4;
			// String hostString = "127.0.0.1";
			String hostString = "fe80::6965:c8b8:f570:842%11";
			InetAddress address = InetAddressUtil.getInetAddress(hostString);
			for (int i = 0; i < retry; i++){
				// 测试是否可以达到该地址。
				if (address.isReachable(timeOut)){
					System.out.println(i + " OK");
				}else{
					System.out.println(i + " LOSS");
				}
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
