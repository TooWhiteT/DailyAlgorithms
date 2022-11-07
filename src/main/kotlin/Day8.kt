
/**
 * 模糊坐标
 *
 * 我们有一些二维坐标，如"(1, 3)"或"(2, 0.5)"，然后我们移除所有逗号，小数点和空格，得到一个字符串S。返回所有可能的原始字符串到一个列表中。
 * 原始的坐标表示法不会存在多余的零，所以不会出现类似于"00", "0.0", "0.00", "1.0", "001", "00.01"或一些其他更小的数来表示坐标。
 * 此外，一个小数点前至少存在一个数，所以也不会出现“.1”形式的数字。
 * 最后返回的列表可以是任意顺序的。而且注意返回的两个数字中间（逗号之后）都有一个空格。
 *
 * @see <a href="https://leetcode.cn/problems/ambiguous-coordinates">模糊坐标</a>
 */
fun ambiguousCoordinates(s: String): List<String> {
    //1.先划分逗号
    val res = ArrayList<String>()
    val str = s.substring(1, s.length - 1)
    for (i in 1 until str.length) {
        val head = find(str.substring(0, i))
        val tail = find(str.substring(i))
        for (h in head) {
            for (t in tail) {
                res.add("($h, $t)")
            }
        }
    }
    return res
}

fun find(s: String): List<String> {
    val ans = ArrayList<String>()
    if (s.length == 1 || s[0] != '0') ans.add(s) //整数部分
    for (i in 1 until s.length) {
        val l = s.substring(0, i) //小数点前面的
        val r = s.substring(i) //小数点后面的
        if (l.length > 1 && l[0] == '0') continue
        if (r[r.length - 1] == '0') continue
        ans.add("$l.$r")
    }
    return ans
}

/**
 * 4的幂
 *
 * 给定一个整数，写一个函数来判断它是否是 4 的幂次方。如果是，返回 true ；否则，返回 false 。
 * 整数 n 是 4 的幂次方需满足：存在整数 x 使得 n == 4^x
 *
 * @see <a href="https://leetcode.cn/problems/power-of-four">4的幂</a>
 */
fun isPowerOfFour(n: Int): Boolean {
    return (n > 0) && ((n and (n - 1)) == 0) && (n % 3 == 1)
}

/**
 * 反转字符串中的元音字母
 *
 * 给你一个字符串 s ，仅反转字符串中的所有元音字母，并返回结果字符串。元音字母包括 'a'、'e'、'i'、'o'、'u'，且可能以大小写两种形式出现。
 *
 * @see <a href="https://leetcode.cn/problems/reverse-vowels-of-a-string/">反转字符串中的元音字母</a>
 */
fun reverseVowels(s: String): String {
    if (s.length <= 1) return s
    val map = charArrayOf('a','o','e','i','u', 'A', 'O', 'E', 'I', 'U')
    var left = 0
    var right = s.length - 1
    val strs = s.toCharArray()
    while (left < right) {
        while (left < right && s[left] !in map) {
            left++
        }
        while (left < right && s[right] !in map) {
            right--
        }
        if (s[left] != s[right]) {
            val temp = strs[left]
            strs[left] = strs[right]
            strs[right] = temp
        }
        left++
        right--
    }
    return String(strs)
}

/**
 * 两个数组的交集
 *
 * 给定两个数组 nums1 和 nums2 ，返回 它们的交集 。输出结果中的每个元素一定是 唯一 的。我们可以 不考虑输出结果的顺序 。
 *
 * @see <a href="https://leetcode.cn/problems/intersection-of-two-arrays/">两个数组的交集</a>
 */
fun intersection(nums1: IntArray, nums2: IntArray): IntArray {
    var count = 0
    // 比较两个数组谁小 交集看数组最小的长度  并都初始化为 -1  因为取值范围[0,1000]
    val result = IntArray(if (nums1.size < nums2.size) nums1.size else nums2.size){-1}
    val temp_min = if (nums1.size < nums2.size) nums1 else nums2
    val temp_max = if (nums1.size > nums2.size) nums1 else nums2

    for (i in temp_min) {
        // 遍历最小的 然后去大的判断是否存在 并且 我们的结果集中是否存在
        if (i in temp_max && i !in result) {
            result[count++] = i
        }
    }
    return result.copyOfRange(0, count)
}

/**
 * 有效的完全平方数
 *
 * 给定一个 正整数 num ，编写一个函数，如果 num 是一个完全平方数，则返回 true ，否则返回 false 。
 * 进阶：不要 使用任何内置的库函数，如 sqrt 。
 *
 * @see <a href="https://leetcode.cn/problems/valid-perfect-square">有效的完全平方数</a>
 */
fun isPerfectSquare(num: Int): Boolean {
    var temp = num
    var sum = 1
    while (temp > 0) {
        temp -= sum
        sum += 2
    }
    return temp == 0
}