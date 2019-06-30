package com.blueberrysolution.pinelib19.addone.datetime

import java.util.*

object DateTime{

    /*
    %te	 一个月中的某一天（1～31）	 2
    %tb	 指定语言环境的月份简称	 Feb（英文）、二月（中文）
    %tB	 指定语言环境的月份全称	 February（英文）、二月（中文）
    %tA	 指定语言环境的星期几全称	 Monday（英文）、星期一（中文）
    %ta	 指定语言环境的星期几简称	 Mon（英文）、星期一（中文）
    %tc	 包括全部日期和时间信息	 星期四 十一月 26 10:26:30 CST 2009
    %tY	 4位年份	 2009
    %tj	 一年中的第几天（001～366）	 085
    %tm	 月份	 03
    %td	 一个月中的第几天（01～31）	 08
    %ty	 2位年份	 09
    时间格式化转换符
    转换符	说　　明	示　　例
    %tH	 2位数字的24小时制的小时（00～23）	 14
    %tI	 2位数字的12小时制的小时（01～12）	 05
    %tk	 2位数字的24小时制的小时（1～23）	 5
    %tl	 2位数字的12小时制的小时（1～12）	 10
    %tM	 2位数字的分钟（00～59）	 05
    %tS	 2位数字的秒数（00～60）	 12
    %tL	 3位数字的毫秒数（000～999）	 920
    %tN	 9位数字的微秒数（000000000～999999999）	 062000000000
    %tp	 指定语言环境下上午或下午标记	 下午（中文）、pm（英文）
    %tz	 相对于GMT RFC 82格式的数字时区偏移量	 +0800
    %tZ	 时区缩写形式的字符串	 CST
    %ts	 1970-01-01 00:00:00至现在经过的秒数	 1206426646
    %tQ	 1970-01-01 00:00:00至现在经过的毫秒数	 1206426737453
    常见的日期时间组合转换符
    转换符	说　　明	示　　例
    %tF	 “年-月-日”格式（4位年份）	 2009-01-26
    %tD	 “月/日/年”格式（2位年份）	 03/25/09
    %tr	 “时：分：秒 PM（AM）”格式（12小时制）	 03:22:06 下午
    %tT	 “时：分：秒”格式（24小时制）	 15:23:50
    %tR	 “时：分”格式（24小时制）	 15:23
     */
    fun format(format: String, date: Date): String{
        val dt = String.format(format, date) // 星期
        return dt
    }

    fun getTodaysWeek(isEn: Boolean):String {
        var cal = Calendar.getInstance();
        var i = cal.get(Calendar.DAY_OF_WEEK);
        when (i) {
            1 ->
                return if (isEn)  "Sunday" else "星期日";
            2 ->
                return if (isEn)  "Monday" else "星期一";
            3 ->
                return if (isEn)  "Tuesday" else "星期二";
            4 ->
                return if (isEn)  "Wednesday" else "星期三";
            5 ->
                return if (isEn)  "Thursday" else "星期四";
            6 ->
                return if (isEn)  "Friday" else "星期五";
            7 ->
                return if (isEn)  "Saturday" else "星期六";
            else ->
                return "";
        }
    }
}