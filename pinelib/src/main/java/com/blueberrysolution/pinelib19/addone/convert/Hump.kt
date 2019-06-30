package com.blueberrysolution.pinelib19.addone.convert

import java.util.regex.Pattern

object Hump {
    private val linePattern = Pattern.compile("_(\\w)")
    /**下划线转驼峰 */
    fun lineToHump(str: String): String {
        var str = str
        str = str.toLowerCase()
        val matcher = linePattern.matcher(str)
        val sb = StringBuffer()
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase())
        }
        matcher.appendTail(sb)
        return sb.toString()
    }

    /**驼峰转下划线(简单写法，效率低于[.humpToLine2]) */
    fun humpToLine(str: String): String {
        return str.replace("[A-Z]".toRegex(), "_$0").toLowerCase()
    }

    private val humpPattern = Pattern.compile("[A-Z]")
    /**驼峰转下划线,效率比上面高 */
    fun humpToLine2(str: String): String {
        val matcher = humpPattern.matcher(str)
        val sb = StringBuffer()
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase())
        }
        matcher.appendTail(sb)
        return sb.toString()
    }
}