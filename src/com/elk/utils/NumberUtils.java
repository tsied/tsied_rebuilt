package com.elk.utils;

import java.util.Formatter;

public class NumberUtils {

	/**
	 * %.2f % 表示 小数点前任意位数 2 表示两位小数 格式后的结果为 f 表示浮点型
	 * 
	 * @param value
	 * @return
	 */
	public static String format4(double value) {

		return new Formatter().format("%.2f", value).toString();
	}

}
