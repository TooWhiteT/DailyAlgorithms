import java.util.*


/**
 * 字符串转化后的各位数字之和
 *
 * 给你一个由小写字母组成的字符串 s ，以及一个整数 k 。
 * 首先，用字母在字母表中的位置替换该字母，将 s 转化 为一个整数（也就是，'a' 用 1 替换，'b' 用 2 替换，... 'z' 用 26 替换）。接着，将整数 转换 为其 各位数字之和 。共重复 转换 操作 k 次 。
 * 例如，如果 s = "zbax" 且 k = 2 ，那么执行下述步骤后得到的结果是整数 8 ：
 *      转化："zbax" ➝ "(26)(2)(1)(24)" ➝ "262124" ➝ 262124
 *      转换 #1：262124➝ 2 + 6 + 2 + 1 + 2 + 4➝ 17
 *      转换 #2：17 ➝ 1 + 7 ➝ 8
 *
 * 返回执行上述操作后得到的结果整数。
 *
 * @see <a href="https://leetcode.cn/problems/sum-of-digits-of-string-after-convert/">字符串转化后的各位数字之和</a>
 */
fun getLucky(s: String, k: Int): Int {
    var res = 0
    var temp = k
    //s转化数字，同步执行一次按位求和，k-1
    for (i in 0 until s.length) {
        var num = s[i] - 'a' + 1
        while (num > 0) {
            res += num % 10
            num /= 10
        }
    }
    --temp
    //循环k-1次按位求和
    while (temp > 0) {
        var tem = res
        res = 0
        while (tem > 0) {
            res += tem % 10
            tem /= 10
        }
        --temp
    }
    return res
}

/**
 * 最小绝对差
 *
 * 给你个整数数组arr，其中每个元素都 不相同。
 * 请你找到所有具有最小绝对差的元素对，并且按升序的顺序返回。
 *  每对元素对 [a,b] 如下：
 *  a ,b均为数组arr中的元素
 *  a < b
 *  b - a等于 arr 中任意两个元素的最小绝对差
 *
 * @see <a href="https://leetcode.cn/problems/minimum-absolute-difference">最小绝对差</a>
 */
fun minimumAbsDifference(arr: IntArray): MutableList<MutableList<Int>> {
    Arrays.sort(arr)
    val res = HashMap<Int, MutableList<MutableList<Int>>>()
    for (i in 1 until arr.size) {
        val orDefault: MutableList<MutableList<Int>> = res.getOrDefault(arr[i] - arr[i - 1], ArrayList())
        val ints = ArrayList<Int>()
        ints.add(arr[i - 1])
        ints.add(arr[i])
        orDefault.add(ints)
        res.put(arr[i] - arr[i - 1], orDefault)
    }
    var min = Int.MAX_VALUE
    res.map {
        min = Math.min(min, it.key)
    }
    return res[min]!!
}

/**
 * 独一无二的出现次数
 *
 * 给你一个整数数组 arr，请你帮忙统计数组中每个数的出现次数。
 * 如果每个数的出现次数都是独一无二的，就返回 true；否则返回 false。
 *
 * @see <a href="https://leetcode.cn/problems/unique-number-of-occurrences/">独一无二的出现次数</a>
 */
fun uniqueOccurrences(arr: IntArray): Boolean {
    val counter = HashMap<Int, Int>()
    for (elem in arr) {
        counter[elem] = counter.getOrDefault(elem, 0) + 1
    }
    return counter.size == HashSet(counter.values).size
}

/**
 * 玩筹码
 *
 * 有n个筹码。第 i 个筹码的位置是position[i]。
 * 我们需要把所有筹码移到同一个位置。在一步中，我们可以将第 i 个筹码的位置从position[i]改变为:
 *  position[i] + 2或position[i] - 2，此时cost = 0
 *  position[i] + 1或position[i] - 1，此时cost = 1
 *
 * 返回将所有筹码移动到同一位置上所需要的 最小代价 。
 *
 * @see <a href="https://leetcode.cn/problems/minimum-cost-to-move-chips-to-the-same-position">玩筹码</a>
 */
fun minCostToMoveChips(position: IntArray): Int {
    var odd = 0
    var even = 0
    for (i in 0 until position.size) {
        if (position[i] % 2 == 0) {
            even++
        } else if (position[i] % 2 != 0) {
            odd++
        }
    }
    return Math.min(even, odd)
}

/**
 * 分割平衡字符串
 *
 * 平衡字符串 中，'L' 和 'R' 字符的数量是相同的。
 * 给你一个平衡字符串s，请你将它分割成尽可能多的子字符串，并满足：
 *  每个子字符串都是平衡字符串。
 *  返回可以通过分割得到的平衡字符串的 最大数量 。
 *
 * @see <a href="https://leetcode.cn/problems/split-a-string-in-balanced-strings">分割平衡字符串</a>
 */
fun balancedStringSplit(s: String): Int {
    var num = 0
    var n = 0
    for (i in 0 until s.length) {
        if (s[i] == 'R') n++
        if (s[i] == 'L') n--
        if (n == 0) num++
    }
    return num
}