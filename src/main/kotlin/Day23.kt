/**
 * 棒球比赛
 *
 * 你现在是一场采用特殊赛制棒球比赛的记录员。这场比赛由若干回合组成，过去几回合的得分可能会影响以后几回合的得分。
 * 比赛开始时，记录是空白的。你会得到一个记录操作的字符串列表 ops，其中 ops[i] 是你需要记录的第 i 项操作，ops 遵循下述规则：
 *
 * 1. 整数 x - 表示本回合新获得分数 x
 * 2. "+" - 表示本回合新获得的得分是前两次得分的总和。题目数据保证记录此操作时前面总是存在两个有效的分数。
 * 3. "D" - 表示本回合新获得的得分是前一次得分的两倍。题目数据保证记录此操作时前面总是存在一个有效的分数。
 * 4. "C" - 表示前一次得分无效，将其从记录中移除。题目数据保证记录此操作时前面总是存在一个有效的分数。
 *
 * 请你返回记录中所有得分的总和。
 *
 * @see <a href="https://leetcode.cn/problems/baseball-game">棒球比赛</a>
 */
fun calPoints(operations: Array<String>): Int {
    val arr = IntArray(operations.size)
    var i = 0
    for (s in operations) {
        when (s) {
            "+" -> {
                arr[i] = arr[i - 1] + arr[i - 2]
                i++
            }
            "D" -> {
                arr[i] = 2 * arr[i - 1]
                i++
            }
            "C" -> {
                arr[i - 1] = 0
                i--
            }
            else -> {
                arr[i] = s.toInt()
                i++
            }
        }
    }
    var sum = 0
    for (j in 0 until arr.size) {
        sum += arr[j]
    }
    return sum
}

/**
 * 交替位二进制数
 *
 * 给定一个正整数，检查它的二进制表示是否总是 0、1 交替出现：换句话说，就是二进制表示中相邻两位的数字永不相同。
 *
 * @see <a href="https://leetcode.cn/problems/binary-number-with-alternating-bits/">交替位二进制数</a>
 */
fun hasAlternatingBits(n: Int): Boolean {
    // 如 010101 右移一位得到 001010
    // 二者异或之后得到011111  (这一步是关键,只有交替出现01，异或后才能得到结果0111111...11)
    // 为了判断 异或后的结果是否满足(0111111...11)类型的结果
    // 可以用如下方法，比如
    // 011111 加上1 为100000
    // 011111 与 100000按位相与 结果为000000 ， 也就是0;
    val m = n xor (n shr 1)
    return m and m + 1 == 0
}

/**
 * 计数二进制子串
 *
 * 给定一个字符串 s，统计并返回具有相同数量 0 和 1 的非空（连续）子字符串的数量，并且这些子字符串中的所有 0 和所有 1 都是成组连续的。
 * 重复出现（不同位置）的子串也要统计它们出现的次数。
 *
 * @see <a href="https://leetcode.cn/problems/count-binary-substrings">计数二进制子串</a>
 */
fun countBinarySubstrings(s: String): Int {
    var last = 0
    var res = 0
    var cur = 1
    for (i in 1 until s.length) {
        if (s[i] == s[i - 1]) {
            cur++
        } else {
            last = cur
            cur = 1
        }
        if (last >= cur) {
            res++
        }
    }
    return res
}

/**
 * 数组的度
 *
 * 给定一个非空且只包含非负数的整数数组 nums，数组的 度 的定义是指数组里任一元素出现频数的最大值。
 * 你的任务是在 nums 中找到与 nums 拥有相同大小的度的最短连续子数组，返回其长度。
 *
 * @see <a href="https://leetcode.cn/problems/degree-of-an-array/">数组的度</a>
 */
fun findShortestSubArray(nums: IntArray): Int {
    val map = HashMap<Int, IntArray>()
    var max = 0
    var res = 0
    for (i in 0 until nums.size) {
        if (!map.containsKey(nums[i])) {
            map[nums[i]] = intArrayOf(1, i)
        } else {
            map[nums[i]]!![0]++
        }
        val arr = map[nums[i]]
        val len = i - arr!![1] + 1
        if (max < arr!![0] || max == arr!![0] && res > len) {
            max = arr!![0]
            res = len
        }
    }
    return res
}

/**
 * 第 N 个神奇数字
 *
 * 一个正整数如果能被 a 或 b 整除，那么它是神奇的。
 * 给定三个整数 n , a , b ，返回第 n 个神奇的数字。因为答案可能很大，所以返回答案对 10^9 + 7 取模后的值。
 *
 * @see <a href="https://leetcode.cn/problems/nth-magical-number/">第 N 个神奇数字</a>
 */
fun nthMagicalNumber(n: Int, a: Int, b: Int): Int {
    // a、b有倍数关系，可直接O(1)内返回。
    if (a % b == 0) return nthMagicalNumber(n, b)
    if (b % a == 0) return nthMagicalNumber(n, a)

    //求a和b的最公倍数p
    var p = a
    var i = 1
    while (p * i % b != 0) i++
    p = p * i

    //二分查找，直到某个数之前恰好有n个神奇数字。
    var l: Long = 1
    var r = n.toLong() * Math.min(a, b)
    while (l <= r) {
        var m = l + (r - l) / 2
        // 求m之前的神奇数字的个数：a的倍数的数量，加上b的倍数的数量，再减去a和b的公倍数的数量
        if (m / a + m / b - m / p >= n) {
            r = --m
        } else {
            l = ++m
        }
    }
    return (l % 1000000007).toInt()
}

fun nthMagicalNumber(n: Int, a: Int): Int {
    return (n.toLong() * a % 1000000007).toInt()
}