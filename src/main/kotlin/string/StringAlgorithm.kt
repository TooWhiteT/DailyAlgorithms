package string

import java.util.*

/**
 * 最长公共前缀
 *
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * 如果不存在公共前缀，返回空字符串 ""。
 *
 * @see <a href="https://leetcode.cn/leetbook/read/array-and-string/ceda1/">最长公共前缀</a>
 */
fun longestCommonPrefix(strs: Array<String>): String {
    var pre = strs[0]
    for (i in 1 until strs.size) {
        while (strs[i].indexOf(pre) != 0) {
            pre = pre.substring(0, pre.length - 1)
        }
    }
    return pre
}

/**
 * 最长回文子串
 *
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 *
 * @see <a href="https://leetcode.cn/leetbook/read/array-and-string/conm7/">最长回文子串</a>
 */
fun longestPalindrome(s: String): String { // 中心回文
    // 用数组分别记录长度，起点，终点
    val res = IntArray(3)
    //每一次循环都将当前的坐标，作为回文的中心点，分为偶数和奇数中心，记录长度最长的。
    for (i in 0 until s.length) {
        var begin = i
        var end = i
        //奇数回文串，直接开始中心回文。
        while (begin - 1 >= 0 && end + 1 < s.length && s[begin - 1] == s[end + 1]) {
            end++
            begin--
            //比已知最长的长度长，则记录数据
            if (end - begin > res[0]){
                res[0] = end - begin
                res[1] = begin
                res[2] = end
            }
        }
        //偶数回文串，第一次先判断和下一个是否相等，然后中心回文
        begin = i
        end = i + 1
        while (begin >= 0 && end < s.length && s[begin] == s[end]) {
            if (end - begin > res[0]) {
                res[0] = end - begin
                res[1] = begin
                res[2] = end
            }
            end++
            begin--
        }
    }
    return s.substring(res[1], res[2]+1)
}

/**
 * 翻转字符串里的单词
 *
 * 给你一个字符串 s ，请你反转字符串中 单词 的顺序。
 * 单词 是由非空格字符组成的字符串。s 中使用至少一个空格将字符串中的 单词 分隔开。
 * 返回 单词 顺序颠倒且 单词 之间用单个空格连接的结果字符串。
 * 注意：输入字符串s中可能会存在前导空格、尾随空格或者单词间的多个空格。返回的结果字符串中，单词间应当仅用单个空格分隔，且不包含任何额外的空格。
 *
 * @see <a href="https://leetcode.cn/leetbook/read/array-and-string/crmp5/">翻转字符串里的单词</a>
 */
fun reverseWords(s: String): String {
    val stack = Stack<String>()
    val wordArray = s.trim().split(" ")
    for (w in wordArray) {
        if (!w.isEmpty()) {
            stack.add(w)
        }
    }
    val stringbuffer = StringBuffer()
    while (!stack.isEmpty()) {
        stringbuffer.append(stack.pop())
        if (!stack.isEmpty()) {
            stringbuffer.append(" ")
        }
    }
    return stringbuffer.toString()
}

/**
 * 实现 strStr()
 *
 * 给你两个字符串haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串的第一个匹配项的下标（下标从 0 开始）。
 * 如果needle 不是 haystack 的一部分，则返回-1 。
 *
 * @see <a href="https://leetcode.cn/leetbook/read/array-and-string/cm5e2/">实现 strStr()</a>
 */
fun strStr(haystack: String, needle: String): Int {
    if (needle.length == 0) {
        return 0
    }
    var left = 0
    var right = 0
    while (left < haystack.length && right < needle.length) {
        if (haystack[left] == needle[right]) {
            left++
            right++
        } else {
            // 如果不匹配，就回退，从第一次匹配的下一个开始，
            left = left - right + 1
            right = 0
        }
        if (right == needle.length) {
            return left - right
        }
    }
    return -1
}

/**
 * 反转字符串
 *
 * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 s 的形式给出。
 * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
 *
 * @see <a href="https://leetcode.cn/leetbook/read/array-and-string/cacxi/">反转字符串</a>
 */
fun reverseString(s: CharArray) {
    if (s.size == 1) {
        return
    }
    for (i in 0 until (s.size / 2)) {
        val temp = s[i]
        s[i] = s[s.size - 1 - i]
        s[s.size - 1 - i] = temp
    }
}

/**
 * 数组拆分 I
 *
 * 给定长度为2n的整数数组 nums ，你的任务是将这些数分成 n 对, 例如 (a1, b1), (a2, b2), ..., (an, bn) ，使得从 1 到 n 的 min(ai, bi) 总和最大。
 * 返回该 最大总和 。
 *
 * @see <a href="https://leetcode.cn/leetbook/read/array-and-string/c24he/">数组拆分 I</a>
 */
fun arrayPairSum(nums: IntArray): Int {
    // 两个最大的数排在一组则结果必然包含第二最大值, 同理去除最大值和第二最大值后,将剩下值中最大值和第二最大值排在一组...逐步最优—————>全局最优
    Arrays.sort(nums)
    var res = 0
    for (index in 0 until nums.size) {
        if (index % 2 == 0) {
            res += nums[index]
        }
    }
    return res
}

/**
 * 两数之和 II - 输入有序数组
 *
 * 给你一个下标从 1 开始的整数数组numbers ，该数组已按 非递减顺序排列，请你从数组中找出满足相加之和等于目标数target 的两个数。如果设这两个数分别是 numbers`[index1]` 和 numbers`[index2]` ，则 1 <= index1 < index2 <= numbers.length 。
 * 以长度为 2 的整数数组 [index1, index2] 的形式返回这两个整数的下标 index1 和 index2。
 * 你可以假设每个输入 只对应唯一的答案 ，而且你 不可以 重复使用相同的元素。
 * 你所设计的解决方案必须只使用常量级的额外空间。
 *
 * @see <a href="https://leetcode.cn/leetbook/read/array-and-string/cnkjg/">两数之和 II - 输入有序数组</a>
 */
fun twoSum(numbers: IntArray, target: Int): IntArray {
    //由于数组从小到大排列, 双指针分别指向首部和尾部;
    //首部尾部相加等于目标值，返回结果集
    //首部尾部相加小于目标值，首部后移变大
    //首部尾部相加大于目标值，尾部前移变小
    val res = intArrayOf(0, numbers.size - 1)
    var left = res[0]
    var right = res[1]
    while(left < right){
        if(numbers[left] + numbers[right] == target){
            res[0] = left+1
            res[1] = right+1
            break
        } else if (numbers[left] + numbers[right] < target){
            left++
        } else {
            right--
        }
    }
    return res
}
