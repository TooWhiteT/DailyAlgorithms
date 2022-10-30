/**
 * 回文数
 *
 * 给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
 * 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数
 * 例如，121 是回文，而 123 不是。
 *
 * @see <a href="https://leetcode.cn/problems/palindrome-number">回文数</a>
 */
fun isPalindrome(x: Int): Boolean {
    if (x < 0) return false
    val str = x.toString()
    for (i in 0 until (str.length / 2)) {
        if (str[i] != str[str.length - 1 - i]) {
            return false
        }
    }
    return true
}

/**
 * 最后一个单词的长度
 *
 * 给你一个字符串 s，由若干单词组成，单词前后用一些空格字符隔开。返回字符串中 最后一个 单词的长度。
 * 单词 是指仅由字母组成、不包含任何空格字符的最大子字符串。
 *
 * @see <a href="https://leetcode.cn/problems/length-of-last-word/">最后一个单词的长度</a>
 */
fun lengthOfLastWord(s: String): Int {
    val temp = s.trim()
    val lastEmptyIndex = temp.lastIndexOf(" ")
    return temp.substring(lastEmptyIndex + 1).length
}

/**
 * 二进制求和
 *
 * 给你两个二进制字符串 a 和 b ，以二进制字符串的形式返回它们的和。
 *
 * @see <a href="https://leetcode.cn/problems/add-binary/">二进制求和</a>
 */
fun addBinary(a: String, b: String): String {
    val stb = StringBuilder()
    var i = a.length - 1
    var j = b.length - 1
    var c = 0 // 进位
    while (i >= 0 || j >= 0) {
        if (i >= 0) {
            c += a[i--] - '0'
        }
        if (j >= 0) {
            c += b[j--] - '0'
        }
        stb.append(c % 2)
        c = c ushr 1
    }
    val res = stb.reverse().toString()
    return if (c > 0) "1${res}" else res
}

/**
 * x 的平方根
 *
 * 给你一个非负整数 x ，计算并返回x的 算术平方根 。
 * 由于返回类型是整数，结果只保留 整数部分 ，小数部分将被 舍去 。
 * 注意：不允许使用任何内置指数函数和算符，例如 pow(x, 0.5) 或者 x ** 0.5 。
 *
 * @see <a href="https://leetcode.cn/problems/sqrtx">x 的平方根</a>
 */
fun mySqrt(x: Int): Int { // 二分查找
    var left = 1
    var right = x
    var mid = 0
    while (left <= right) {
        mid = left + (right - left) / 2
        val temp = x / mid
        if (temp > mid) {
            left = mid + 1
        } else if (temp < mid) {
            right = mid - 1
        } else if (temp == mid) {
            return mid
        }
    }
    return right
}

/**
 * 删除排序链表中的重复元素
 *
 * 给定一个已排序的链表的头 head ， 删除所有重复的元素，使每个元素只出现一次 。返回 已排序的链表 。
 *
 * @see <a href="https://leetcode.cn/problems/remove-duplicates-from-sorted-list/"></a>
 */
fun deleteDuplicates(head: ListNode?): ListNode? {
    var temp = head
    if (temp?.next == null) return temp
    temp.next = deleteDuplicates(temp.next)
    if (temp.`val` == temp.next?.`val`) temp = temp.next
    return temp
}