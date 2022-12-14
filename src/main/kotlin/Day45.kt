import java.util.*

/**
 * 检查边长度限制的路径是否存在
 *
 * 给你一个 n个点组成的无向图边集edgeList，其中edgeList[i] = [ui, vi, disi]表示点ui 和点vi之间有一条长度为disi的边。请注意，两个点之间可能有 超过一条边。
 * 给你一个查询数组queries，其中queries[j] = [pj, qj, limitj]，你的任务是对于每个查询queries[j]，判断是否存在从pj到qj的路径，且这条路径上的每一条边都 严格小于limitj。
 * 请你返回一个 布尔数组answer，其中answer.length == queries.length，当queries[j]的查询结果为true时，answer 第j个值为true，否则为false。
 *
 * @see <a href="https://leetcode.cn/problems/checking-existence-of-edge-length-limited-paths"></a>
 */
fun distanceLimitedPathsExist(n: Int, edgeList: Array<IntArray>, queries: Array<IntArray>): BooleanArray {
    val group = IntArray(n + 5)
    val m: Int = queries.size
    val o: Int = edgeList.size
    for (i in 0 until n) {
        group[i] = i
    }
    for (i in 0 until m) {
        queries[i] = intArrayOf(i, queries[i][0], queries[i][1], queries[i][2])
    }
    Arrays.sort(queries) { a, b ->
        a[3] - b[3]
    }
    Arrays.sort(edgeList) { a, b ->
        a[2] - b[2]
    }
    val ans = BooleanArray(m)
    var j = 0
    for (i in 0 until m) {
        while (j < o && edgeList[j][2] < queries[i][3]) {
            union(edgeList[j][0], edgeList[j][1], group)
            j++
        }
        ans[queries[i][0]] = find(group[queries[i][1]], group) == find(group[queries[i][2]], group)
    }
    return ans
}

fun union(a: Int, b: Int, group: IntArray) {
    var a = a
    var b = b
    a = find(a, group)
    b = find(b, group)
    group[b] = a
}

fun find(a: Int, group: IntArray): Int {
    if (a != group[a]) {
        group[a] = find(group[a], group)
    }
    return group[a]
}

/**
 * 质数排列
 *
 * 请你帮忙给从 1 到 n的数设计排列方案，使得所有的「质数」都应该被放在「质数索引」（索引从 1 开始）上；你需要返回可能的方案总数。
 * 让我们一起来回顾一下「质数」：质数一定是大于 1 的，并且不能用两个小于它的正整数的乘积来表示。
 * 由于答案可能会很大，所以请你返回答案 模 mod10^9 + 7之后的结果即可。
 *
 * @see <a href="https://leetcode.cn/problems/prime-arrangements">质数排列</a>
 */
fun numPrimeArrangements(n: Int): Int {
    var sum = 0
    if (n > 2) {
        sum = funSum(n)
    } else {
        return 1
    }
    var cur = 1L
    for (i in 2..sum) {
        cur = cur * i % (1000000000 + 7)
    }
    for (i in 2..n - sum) {
        cur = cur * i % (1000000000 + 7)
    }
    return cur.toInt()
}

fun funSum (n: Int): Int {
    var n = n
    n += 1
    var sum = 0
    val bool = BooleanArray(n + 1)
    for (i in 2 until n) {
        if (bool[i] == false) {
            sum++
            var j = 2
            while (j * i < n) {
                bool[j * i] = true
                j++
            }
        }
    }
    return sum
}

/**
 * 公交站间的距离
 *
 * 环形公交路线上有n个站，按次序从0到n - 1进行编号。我们已知每一对相邻公交站之间的距离，distance[i]表示编号为i的车站和编号为(i + 1) % n的车站之间的距离。
 * 环线上的公交车都可以按顺时针和逆时针的方向行驶。
 * 返回乘客从出发点start到目的地destination之间的最短距离。
 *
 * @see <a href="https://leetcode.cn/problems/distance-between-bus-stops">公交站间的距离</a>
 */
fun distanceBetweenBusStops(distance: IntArray, start: Int, destination: Int): Int {
    var d1 = 0
    var d2 = 0
    val l = Math.min(start, destination)
    val r = Math.max(start, destination)
    for (i in 0 until distance.size) {
        if (i >= l && i < r) {
            d1 += distance[i]
        } else {
            d2 += distance[i]
        }
    }
    return if (d1 < d2) d1 else d2
}

/**
 * 一周中的第几天
 *
 * 给你一个日期，请你设计一个算法来判断它是对应一周中的哪一天。
 * 输入为三个整数：day、month 和year，分别表示日、月、年。
 * 您返回的结果必须是这几个值中的一个{"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"}。
 *
 * @see <a href="https://leetcode.cn/problems/day-of-the-week">一周中的第几天</a>
 */
fun dayOfTheWeek(day: Int, month: Int, year: Int): String {
    // 基姆拉尔森计算公式
    val day = day
    var month = month
    var year = year

    if (month == 1 || month == 2) {
        year--
        month += 12
    }
    val week = (day + 2 * month + 3 * (month + 1) / 5 + year + year / 4 - year / 100 + year / 400) % 7
    val ss = arrayOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
    return ss[week]
}

/**
 * “气球” 的最大数量
 *
 * 给你一个字符串text，你需要使用 text 中的字母来拼凑尽可能多的单词"balloon"（气球）。
 * 字符串text 中的每个字母最多只能被使用一次。请你返回最多可以拼凑出多少个单词"balloon"。
 *
 * @see <a href="https://leetcode.cn/problems/maximum-number-of-balloons">“气球” 的最大数量</a>
 */
fun maxNumberOfBalloons(text: String): Int {
    val map = HashMap<Char, Int>()
    for (i in 0 until text.length) {
        val c = text[i]
        map.put(c, (map[c] ?: 0) + 1)
    }
    var res = 0
    val b = map['b'] ?: 0
    val a = map['a'] ?: 0
    val l = map['l'] ?: 0
    val o = map['o'] ?: 0
    val n = map['n'] ?: 0
    return Math.min(b, Math.min(a, Math.min(n, Math.min(l / 2, o / 2)))).also {
        res = it
    }
}
