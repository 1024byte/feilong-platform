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
package com.feilong.tools.ant;

import java.io.File;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class DirectoryScannerUtilTest.
 *
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.9 2015年1月3日 下午9:06:03
 * @since 1.0.9
 */
public class DirectoryScannerUtilTest{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(DirectoryScannerUtilTest.class);

	/**
	 * 获得 included file list.
	 */
	@Test
	public void getIncludedFileList(){
		/** The folder. */
		String WORK_FOLDER = "E:\\Workspaces\\baozun\\BaozunSql\\train";

		String courseNickName = "0926SQL优化";

		String basedir = WORK_FOLDER + "\\" + courseNickName;
		String[] includes = { "ppt-contents*.png" };
		List<File> includedFileList = DirectoryScannerUtil.getIncludedFileList(basedir, includes);
	}
}
