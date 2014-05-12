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
package com.feilong.framework.bind.exception;

// Exception又分为两类：一种是CheckedException，一种是UncheckedException。
// 
// 这两种Exception的区别主要是CheckedException需要用try...catch...显示的捕获，
// 而UncheckedException不需要捕获。 通常UncheckedException又叫做RuntimeException。
// 	
// 《effective java》指出：
//	对于可恢复的条件使用被检查的异常（CheckedException），
//	对于程序错误（言外之意不可恢复，大错已经酿成）使用运行时异常（RuntimeException）。
/**
 * 构建对象异常<br>
 * 对于程序错误（言外之意不可恢复，大错已经酿成）使用运行时异常
 * 
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.6 2014年5月8日 下午12:37:00
 * @since 1.0.6
 */
public class BuildCommandException extends RuntimeException{

	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= 7791021324380086044L;

	/**
	 * Instantiates a new http client util exception.
	 */
	public BuildCommandException(){
		super();
	}

	/**
	 * Instantiates a new http client util exception.
	 * 
	 * @param message
	 *            the message
	 */
	public BuildCommandException(String message){
		super(message);
	}

	/**
	 * Instantiates a new http client util exception.
	 * 
	 * @param cause
	 *            the cause
	 */
	public BuildCommandException(Throwable cause){
		super(cause);
	}

	/**
	 * Instantiates a new http client util exception.
	 * 
	 * @param message
	 *            the message
	 * @param cause
	 *            the cause
	 */
	public BuildCommandException(String message, Throwable cause){
		super(message, cause);
	}
}
