/**
 * 数组的均值分割
 *
 * 给定你一个整数数组nums
 * 我们要将nums数组中的每个元素移动到A数组 或者B数组中，使得A数组和B数组不为空，并且average(A) == average(B)。
 * 如果可以完成则返回true， 否则返回 false。
 * 注意：对于数组arr, average(arr)是arr的所有元素除以arr长度的和。
 *
 * @see <a href="https://leetcode.cn/problems/split-array-with-same-average/">数组的均值分割</a>
 */
fun splitArraySameAverage(nums: IntArray): Boolean {
    var sum = 0
    val n: Int = nums.size
    for (i in 0 until n) {
        sum += nums[i]
    }
    for (i in 0 until n) {
        nums[i] = nums[i] * n - sum
    }
    val n1 = n shr 1
    val n2 = n - n1
    val max1 = 1 shl n1
    val max2 = 1 shl n2
    val map: MutableMap<Int, Int> = HashMap()
    //首先把所有的第一个集合的可能的和收集起来：
    for (i in 0 until max1) {
        val a = findSum(i, nums, 0)
        map[a] = map.getOrDefault(a, 0) + 1
    }
    //之后验证第二个集合，先不要验证全部不拿或者全部都拿的情况
    for (i in 1 until max2 - 1) {
        val a = findSum(i, nums, n1)
        if (a == 0 || map.containsKey(-a)) {
            return true
        }
    }
    //全不拿（0）的情况已经在上边得到了验证，剩下的只需要验证全都拿的情况：
    val a1 = findSum(max1 - 1, nums, 0)
    val a2 = findSum(max2 - 1, nums, n1)
    return a1 + a2 != 0 && map.containsKey(-a2) || a1 + a2 == 0 && map[-a2]!! > 1
}

fun findSum(mask: Int, nums: IntArray, start: Int): Int {
    var temp = mask
    var ans = 0
    var i = 0
    while (temp > 0) {
        if (temp and 1 == 1) {
            ans += nums[start + i]
        }
        i++
        temp = temp shr 1
    }
    return ans
}

/**
 * 完美数
 *
 * 对于一个正整数，如果它和除了它自身以外的所有 正因子 之和相等，我们称它为 「完美数」。
 * 给定一个整数n，如果是完美数，返回 true；否则返回 false。
 *
 * @see <a href="https://leetcode.cn/problems/perfect-number">完美数</a>
 */
fun checkPerfectNumber(num: Int): Boolean {
    val arr = intArrayOf(6, 28, 496, 8128, 33550336)
    return num in arr
}

/**
 * 斐波那契数
 *
 * 斐波那契数 （通常用 F(n) 表示）形成的序列称为 斐波那契数列 。该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。
 *
 * @see <a href="https://leetcode.cn/problems/fibonacci-number/">斐波那契数</a>
 */
fun fib(n: Int): Int {
    if (n < 2) return n
    var a = 0
    var b = 1
    var c = 0
    for (i in 1 until n) {
        c = a + b
        a = b
        b = c
    }
    return c
}

/**
 * 检测大写字母
 *
 * 我们定义，在以下情况时，单词的大写用法是正确的：
 *
 * 全部字母都是大写，比如 "USA" 。
 * 单词中所有字母都不是大写，比如 "leetcode" 。
 * 如果单词不只含有一个字母，只有首字母大写，比如"Google" 。
 * 给你一个字符串 word 。如果大写用法正确，返回 true ；否则，返回 false 。
 *
 * @see <a href="https://leetcode.cn/problems/detect-capital">检测大写字母</a>
 */
fun detectCapitalUse(word: String): Boolean {
    return (word.toUpperCase().equals(word) || word.substring(1).toLowerCase().equals(word.substring(1)))
}

/**
 * 最长特殊序列 Ⅰ
 *
 * 给你两个字符串a和b，请返回 这两个字符串中 最长的特殊序列的长度。如果不存在，则返回 -1。
 *
 * 「最长特殊序列」定义如下：该序列为某字符串独有的最长子序列（即不能是其他字符串的子序列）。
 * 字符串 s 的子序列是在从s中删除任意数量的字符后可以获得的字符串。
 * 例如，"abc" 是 "aebdc" 的子序列，因为删除 "aebdc" 中斜体加粗的字符可以得到 "abc" 。 "aebdc" 的子序列还包括 "aebdc" 、 "aeb" 和 "" (空字符串)。
 *
 * @see <a href="https://leetcode.cn/problems/longest-uncommon-subsequence-i">最长特殊序列 Ⅰ</a>
 */
fun findLUSlength(a: String, b: String): Int {
    if (a.equals(b)) {
        return -1
    } else {
        return Math.max(a.length, b.length)
    }
}
