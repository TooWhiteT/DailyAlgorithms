
import java.util.*


/**
 * 最少操作使数组递增
 *
 * 给你一个整数数组nums（下标从 0 开始）。每一次操作中，你可以选择数组中一个元素，并将它增加1。
 *      比方说，如果nums = [1,2,3]，你可以选择增加nums[1]得到nums = [1,3,3]。
 * 请你返回使 nums严格递增的 最少操作次数。
 * 我们称数组nums是 严格递增的，当它满足对于所有的0 <= i < nums.length - 1都有nums[i] < nums[i+1]。一个长度为 1的数组是严格递增的一种特殊情况。
 *
 * @see <a href="https://leetcode.cn/problems/minimum-operations-to-make-the-array-increasing">最少操作使数组递增</a>
 */
fun minOperations(nums: IntArray): Int {
    var ans = 0
    for (i in 1 until nums.size) {
        ans += Math.max(0, nums[i - 1] + 1 - nums[i])
        nums[i] = Math.max(nums[i], nums[i - 1] + 1)
    }
    return ans
}

/**
 * 删除字符串中的所有相邻重复项
 *
 * 给出由小写字母组成的字符串S，重复项删除操作会选择两个相邻且相同的字母，并删除它们。
 * 在 S 上反复执行重复项删除操作，直到无法继续删除。
 * 在完成所有重复项删除操作后返回最终的字符串。答案保证唯一。
 *
 * @see <a href="https://leetcode.cn/problems/remove-all-adjacent-duplicates-in-string">删除字符串中的所有相邻重复项</a>
 */
fun removeDuplicates(s: String): String {
    var index = -1
    val chars = s.toCharArray()
    for (i in chars.indices) {
        if (index >= 0 && chars[index] == chars[i]) {
            index--
        } else {
            index++
            chars[index] = chars[i]
        }
    }
    return String(chars, 0, index + 1)
}

/**
 * 高度检查器
 *
 * 学校打算为全体学生拍一张年度纪念照。根据要求，学生需要按照 非递减 的高度顺序排成一行。
 * 排序后的高度情况用整数数组 expected 表示，其中 expected[i] 是预计排在这一行中第 i 位的学生的高度（下标从 0 开始）。
 * 给你一个整数数组 heights ，表示 当前学生站位 的高度情况。heights[i] 是这一行中第 i 位学生的高度（下标从 0 开始）。
 * 返回满足 heights[i] != expected[i] 的 下标数量 。
 *
 * @see <a href="https://leetcode.cn/problems/height-checker">高度检查器</a>
 */
fun heightChecker(heights: IntArray): Int {
    var count = 0
    val temp = heights.clone()
    Arrays.sort(temp)
    for (i in 0 until heights.size) {
        if (heights[i] != temp[i]) count++
    }
    return count
}

/**
 * 字符串的最大公因子
 *
 * 对于字符串s 和t，只有在s = t + ... + t（t 自身连接 1 次或多次）时，我们才认定“t 能除尽 s”。
 * 给定两个字符串str1和str2。返回 最长字符串x，要求满足x 能除尽 str1 且X 能除尽 str2 。
 *
 * @see <a href="https://leetcode.cn/problems/greatest-common-divisor-of-strings">字符串的最大公因子</a>
 */
fun gcdOfStrings(str1: String, str2: String): String {
    // 假设str1是N个x，str2是M个x，那么str1+str2肯定是等于str2+str1的。
    if (((str1 + str2) != (str2 + str1))) {
        return ""
    }
    // 辗转相除法求gcd。
    return str1.substring(0, gcd(str1.length, str2.length))
}

private fun gcd(a: Int, b: Int): Int {
    return if (b == 0) a else gcd(b, a % b)
}

/**
 * Bigram 分词
 *
 * 给出第一个词first 和第二个词second，考虑在某些文本text中可能以 "first second third" 形式出现的情况，其中second紧随first出现，third紧随second出现。
 * 对于每种这样的情况，将第三个词 "third" 添加到答案中，并返回答案。
 *
 * @see <a href="https://leetcode.cn/problems/occurrences-after-bigram">Bigram 分词</a>
 */
fun findOcurrences(text: String, first: String, second: String): Array<String> {
    val words = text.split(" ")
    val ans = ArrayList<String>()
    for (i in 0 until words.size - 2) {
        if (first == words[i] && second == words[i + 1]) {
            ans.add(words[i + 2])
        }
    }
    return ans.toArray(arrayOfNulls<String>(0))
}