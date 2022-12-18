/**
 * 得到连续 K 个 1 的最少相邻交换次数
 *
 * 给你一个整数数组nums和一个整数k。nums 仅包含0和1。每一次移动，你可以选择 相邻两个数字并将它们交换。
 * 请你返回使nums中包含k个 连续1的 最少交换次数。
 *
 * @see <a href="https://leetcode.cn/problems/minimum-adjacent-swaps-for-k-consecutive-ones">得到连续 K 个 1 的最少相邻交换次数</a>
 */
fun minMoves(nums: IntArray, k: Int): Int {
    val n = nums.size
    var l = 0
    var idx = 0
    var res = Int.MAX_VALUE
    var cnt = 0
    var t = 1
    val mid = k shr 1
    val pos = IntArray(n + 1)
    for (i in 0 until n)
        if (nums[i] == 1)
            pos[idx++] = i

    while (t < k)
        cnt += (pos[l + t] - pos[l + t - 1] - 1) * Math.min(t, k - t++) //计算第一个窗口大小

    while (l + k <= idx) {
        res = Math.min(res, cnt)
        cnt -= pos[l + mid] - pos[l]
        cnt += pos[l + k] - pos[l + k - mid]
        l++
    }
    return res
}

/**
 * 统计位数为偶数的数字
 *
 * 给你一个整数数组 nums，请你返回其中位数为 偶数 的数字的个数。
 *
 * @see <a href="https://leetcode.cn/problems/find-numbers-with-even-number-of-digits/">统计位数为偶数的数字</a>
 */
fun findNumbers(nums: IntArray): Int {
    var count = 0
    for (i in 0 until nums.size) {
        if ((nums[i] in 10..99) || (nums[i] in 1000..9999) || nums[i] == 100000) count++
    }
    return count
}

/**
 * 将每个元素替换为右侧最大元素
 *
 * 给你一个数组 arr ，请你将每个元素用它右边最大的元素替换，如果是最后一个元素，用 -1 替换。
 * 完成所有替换操作后，请你返回这个数组。
 *
 * @see <a href="https://leetcode.cn/problems/replace-elements-with-greatest-element-on-right-side/">将每个元素替换为右侧最大元素</a>
 */
fun replaceElements(arr: IntArray): IntArray {
    var max = -1
    for (i in arr.size - 1 downTo 0) {
        val temp = arr[i]
        arr[i] = max
        if (temp > max) {
            max = temp
        }
    }
    return arr
}

/**
 * 和为零的 N 个不同整数
 *
 * 给你一个整数 n，请你返回 任意 一个由 n 个 各不相同 的整数组成的数组，并且这 n 个数相加和为 0 。
 *
 * @see <a href="https://leetcode.cn/problems/find-n-unique-integers-sum-up-to-zero/">和为零的 N 个不同整数</a>
 */
fun sumZero(n: Int): IntArray {
    val arr = IntArray(n)
    if (n == 1) {
        return IntArray(1)
    }
    for (i in 0 until n / 2) {
        arr[i] = i + 1
        arr[n - i - 1] = -(i + 1)
    }
    if (n % 2 != 0) {
        arr[n / 2] = 0
    }
    return arr
}

/**
 * 解码字母到整数映射
 *
 * 给你一个字符串s，它由数字（'0' - '9'）和'#'组成。我们希望按下述规则将s映射为一些小写英文字符：
 *      字符（'a' - 'i'）分别用（'1' -'9'）表示。
 *      字符（'j' - 'z'）分别用（'10#'-'26#'）表示。
 *
 * 返回映射之后形成的新字符串。
 * 题目数据保证映射始终唯一。
 *
 * @see <a href="https://leetcode.cn/problems/decrypt-string-from-alphabet-to-integer-mapping">解码字母到整数映射</a>
 */
fun freqAlphabets(s: String): String {
    val builder = StringBuilder()
    val chars = s.toCharArray()
    var i = chars.size - 1
    while (i in 0 .. chars.size - 1) {
        if (chars[i] == '#') {
            builder.append((chars[--i] - 48 + (chars[--i] - 48).toInt() * 10 + 96).toChar())
        } else {
            builder.append((chars[i] + 48).toChar())
        }
        i--
    }
    return builder.reverse().toString()
}
