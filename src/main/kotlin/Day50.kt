import java.util.*


/**
 * 寻找图中是否存在路径
 *
 * 有一个具有 n 个顶点的 双向 图，其中每个顶点标记从 0 到 n - 1（包含 0 和 n - 1）。图中的边用一个二维整数数组 edges 表示，其中 edges[i] = [ui, vi] 表示顶点 ui 和顶点 vi 之间的双向边。 每个顶点对由 最多一条 边连接，并且没有顶点存在与自身相连的边。
 * 请你确定是否存在从顶点 source 开始，到顶点 destination 结束的 有效路径 。
 * 给你数组 edges 和整数 n、source 和 destination，如果从 source 到 destination 存在 有效路径 ，则返回 true，否则返回 false 。
 *
 * @see <a href="https://leetcode.cn/problems/find-if-path-exists-in-graph">寻找图中是否存在路径</a>
 */
fun validPath(n: Int, edges: Array<IntArray>, source: Int, destination: Int): Boolean {
    val path = Array<MutableList<Int>>(n) {
        ArrayList<Int>()
    }
    val cameBefore = BooleanArray(n)
    for (i in 0 until edges.size) {
        path[edges[i][0]].add(edges[i][1])
        path[edges[i][1]].add(edges[i][0])
    }
    val q = LinkedList<Int>()
    q.add(source)
    cameBefore[source] = true
    while (q.size > 0) {
        val a = q.poll()
        if (a == destination) return true
        for (i in 0 until path[a].size) {
            val b = path[a][i]
            if (!cameBefore[b]) {
                cameBefore[b] = true
                q.add(b)
            }
        }
    }
    return false
}

/**
 * 解压缩编码列表
 *
 * 给你一个以行程长度编码压缩的整数列表nums。
 * 考虑每对相邻的两个元素 [freq, val] = [nums[2*i], nums[2*i+1]]（其中i >= 0），每一对都表示解压后子列表中有 freq个值为val的元素，你需要从左到右连接所有子列表以生成解压后的列表。
 * 请你返回解压后的列表。
 *
 * @see <a href="https://leetcode.cn/problems/decompress-run-length-encoded-list">解压缩编码列表</a>
 */
fun decompressRLElist(nums: IntArray): IntArray {
    var len = 0
    for (o in 0 until nums.size step 2) {
        len += nums[o]
    }
    val res = IntArray(len)
    var index = 0
    for (i in 0 until nums.size step 2) {
        while (nums[i] > 0) {
            res[index] = nums[i + 1]
            nums[i]--
            index++
        }
    }
    return res
}

/**
 * 将整数转换为两个无零整数的和
 *
 * 「无零整数」是十进制表示中 不含任何 0的正整数。
 * 给你一个整数n，请你返回一个 由两个整数组成的列表 [A, B]，满足：
 *      A 和 B都是无零整数
 *      A + B = n
 *
 *
 * 题目数据保证至少有一个有效的解决方案。
 * 如果存在多个有效解决方案，你可以返回其中任意一个。
 *
 * @see <a href="https://leetcode.cn/problems/convert-integer-to-the-sum-of-two-no-zero-integers">将整数转换为两个无零整数的和</a>
 */
fun getNoZeroIntegers(n: Int): IntArray {
    var i = 1
    var j = n - i
    while (true) {
        if (!i.toString().contains("0") && !j.toString().contains("0")) return intArrayOf(i, j)
        i += 1
        j = n - i
    }
}

/**
 * 6 和 9 组成的最大数字
 *
 * 给你一个仅由数字 6 和 9 组成的正整数 num。
 * 你最多只能翻转一位数字，将 6 变成 9，或者把 9 变成 6 。
 * 请返回你可以得到的最大数字。
 *
 * @see <a href="https://leetcode.cn/problems/maximum-69-number/">6 和 9 组成的最大数字</a>
 */
fun maximum69Number (num: Int): Int {
    return num.toString().replaceFirst("6","9").toInt()
}

/**
 * 数组序号转换
 * 
 * 给你一个整数数组arr ，请你将数组中的每个元素替换为它们排序后的序号。
 * 序号代表了一个元素有多大。序号编号的规则如下：
 *      序号从 1 开始编号。
 *      一个元素越大，那么序号越大。如果两个元素相等，那么它们的序号相同。
 *      每个数字的序号都应该尽可能地小。
 * 
 * @see <a href="https://leetcode.cn/problems/rank-transform-of-an-array">数组序号转换</a>
 */
fun arrayRankTransform(arr: IntArray): IntArray {
    // 先复制一份数据到新的数组，排序新数组
    val a = arr.clone()
    Arrays.sort(a)
    // 利用HashMap去重，赋值该数组的所求序号
    var temp = 0
    val map = HashMap<Int, Int>()
    for (i in 0 until arr.size) {
        if (!map.containsKey(a[i])) {
            temp++
            map[a[i]] = temp
        }
    }
    // 比对map中的元素实际位置，赋值
    for (i in 0 until arr.size) {
        a[i] = map[arr[i]]!!
    }
    return a
}