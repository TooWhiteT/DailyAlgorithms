
import java.util.*


/**
 * 最大频率栈
 *
 * 设计一个类似堆栈的数据结构，将元素推入堆栈，并从堆栈中弹出出现频率最高的元素。
 *
 * 实现 FreqStack 类:
 *  FreqStack() 构造一个空的堆栈。
 *  void push(int val) 将一个整数 val 压入栈顶。
 *  int pop() 删除并返回堆栈中出现频率最高的元素。
 *  如果出现频率最高的元素不只一个，则移除并返回最接近栈顶的元素。
 *
 * @see <a href="https://leetcode.cn/problems/maximum-frequency-stack/">最大频率栈</a>
 */
class FreqStack() {

    private var index = 0
    private val map = HashMap<Int, Int>()
    // 优先队列，用index判断谁离栈顶近
    private val queue = PriorityQueue(object : Comparator<IntArray> {
        override fun compare(o1: IntArray, o2: IntArray): Int {
            if(o1[1] == o2[1]) {
                return o2[2] - o1[2]
            }
            return o2[1] - o1[1]
        }
    })

    fun push(x: Int) {
        val cnt = (map[x] ?: 0) + 1
        map.put(x, cnt)
        queue.add(intArrayOf(x, cnt, index++))
    }

    fun pop(): Int {
        val poll = queue.poll()
        map[poll[0]] = poll[1] - 1
        return poll[0]
    }

}

/**
 * 亲密字符串
 *
 * 给你两个字符串 s 和 goal ，只要我们可以通过交换 s 中的两个字母得到与 goal 相等的结果，就返回true；否则返回 false 。
 * 交换字母的定义是：取两个下标 i 和 j （下标从 0 开始）且满足 i != j ，接着交换 s[i] 和 s[j] 处的字符。
 * 例如，在 "abcd" 中交换下标 0 和下标 2 的元素可以生成 "cbad" 。
 *
 * @see <a href="https://leetcode.cn/problems/buddy-strings">亲密字符串</a>
 */
fun buddyStrings(s: String, goal: String): Boolean {
    val len1: Int = s.length
    val len2: Int = goal.length
    // 1. 字符串长度不相等, 直接返回false
    if (len1 != len2) return false

    val set: MutableSet<Char> = HashSet()
    for (i in 0 until len1) {
        set.add(s[i])
    }
    // 2. 字符串相等的时候, 只要有重复的元素就返回true
    if (s.equals(goal)) {
        return set.size != len1
    }

    val list: MutableList<Char> = ArrayList()
    for (i in 0 until len1) {
        if (s[i] !== goal[i]) {
            list.add(s[i])
            list.add(goal[i])
        }
        if (list.size > 4) return false
    }
    // 3. 字符串有不相等的两个地方, 需要查看它们交换后是否相等即可.
    if (list.size < 4) return false
    return if (list[0] == list[3] && list[1] == list[2]) true  else false
}

/**
 * 柠檬水找零
 *
 * 在柠檬水摊上，每一杯柠檬水的售价为 5 美元。顾客排队购买你的产品，（按账单 bills 支付的顺序）一次购买一杯。
 * 每位顾客只买一杯柠檬水，然后向你付 5 美元、10 美元或 20 美元。你必须给每个顾客正确找零，也就是说净交易是每位顾客向你支付 5 美元。
 * 注意，一开始你手头没有任何零钱。
 * 给你一个整数数组 bills ，其中 bills[i] 是第 i 位顾客付的账。如果你能给每位顾客正确找零，返回 true ，否则返回 false 。
 *
 * @see <a href="https://leetcode.cn/problems/lemonade-change">柠檬水找零</a>
 */
fun lemonadeChange(bills: IntArray): Boolean {
    var five = 0
    var ten = 0
    for (i in bills) {
        if (i == 5) {
            five++
        } else if (i == 10) {
            five--
            ten++
        } else if (ten > 0) {
            ten--
            five--
        } else {
            five -= 3
        }
        if (five < 0) return false
    }
    return true
}

/**
 * 转置矩阵
 *
 * 给你一个二维整数数组 matrix， 返回 matrix 的 转置矩阵 。
 * 矩阵的 转置 是指将矩阵的主对角线翻转，交换矩阵的行索引与列索引。
 *
 * @see <a href="https://leetcode.cn/problems/transpose-matrix/">转置矩阵</a>
 */
fun transpose(matrix: Array<IntArray>): Array<IntArray> {
    val res = Array(matrix[0].size){
        IntArray(matrix.size)
    }
    matrix.mapIndexed { i, value ->
        value.mapIndexed { j, v ->
            res[j][i] = v
        }
    }
    return res
}

/**
 * 二进制间距
 *
 * 给定一个正整数 n，找到并返回 n 的二进制表示中两个 相邻 1 之间的 最长距离 。如果不存在两个相邻的 1，返回 0 。
 * 如果只有 0 将两个 1 分隔开（可能不存在 0 ），则认为这两个 1 彼此 相邻 。两个 1 之间的距离是它们的二进制表示中位置的绝对差。例如，"1001" 中的两个 1 的距离为 3 。
 *
 * @see <a href="https://leetcode.cn/problems/binary-gap">二进制间距</a>
 */
fun binaryGap(n: Int): Int {
    val s = n.toString(2)
    var pre = -1
    var max = 0
    for (i in 0 until s.length) {
        if (s[i] == '1') {
            if (pre == -1) {
                pre = i
            } else {
                max = Math.max(i - pre, max)
                pre = i
            }
        }
    }
    return max
}

/**
 * 检查两个字符串数组是否相等
 *
 * 给你两个字符串数组 word1 和 word2 。如果两个数组表示的字符串相同，返回 true ；否则，返回 false 。
 * 数组表示的字符串是由数组中的所有元素 按顺序 连接形成的字符串。
 *
 * @see <a href="https://leetcode.cn/problems/check-if-two-string-arrays-are-equivalent">检查两个字符串数组是否相等</a>
 */
fun arrayStringsAreEqual(word1: Array<String>, word2: Array<String>): Boolean {
    return Arrays.toString(word1).replace(", ","") == Arrays.toString(word2).replace(", ","")
}

/**
 * 网络信号最好的坐标
 *
 * 给你一个数组 towers和一个整数 radius 。
 * 数组towers中包含一些网络信号塔，其中 towers[i] = [xi, yi, qi]表示第i个网络信号塔的坐标是(xi, yi)且信号强度参数为qi。所有坐标都是在 X-Y 坐标系内的整数坐标。两个坐标之间的距离用 欧几里得距离计算。
 * 整数radius表示一个塔 能到达的 最远距离。如果一个坐标跟塔的距离在 radius以内，那么该塔的信号可以到达该坐标。在这个范围以外信号会很微弱，所以 radius以外的距离该塔是 不能到达的。
 * 如果第 i个塔能到达 (x, y)，那么该塔在此处的信号为⌊qi / (1 + d)⌋，其中d是塔跟此坐标的距离。一个坐标的 信号强度 是所有 能到达该坐标的塔的信号强度之和。
 * 请你返回数组 [cx, cy] ，表示 信号强度 最大的 整数 坐标点(cx, cy) 。如果有多个坐标网络信号一样大，请你返回字典序最小的 非负 坐标。
 * 注意：
 * 坐标(x1, y1)字典序比另一个坐标(x2, y2) 小，需满足以下条件之一：
 *  要么x1 < x2，
 *  要么x1 == x2 且y1 < y2。
 *  ⌊val⌋表示小于等于val的最大整数（向下取整函数）。
 *
 * @see <a href="https://leetcode.cn/problems/coordinate-with-maximum-network-quality/">网络信号最好的坐标</a>
 */
fun bestCoordinate(towers: Array<IntArray>, radius: Int): IntArray {
    var xmin = 0
    var xmax = 0
    var ymin = 0
    var ymax = 0
    for (t in towers) {
        xmin = Math.min(xmin, t[0])
        xmax = Math.max(xmax, t[0])
        ymin = Math.min(ymin, t[1])
        ymax = Math.max(ymax, t[1])
    }

    val r2 = radius * radius
    var strongest = Int.MIN_VALUE
    var strongestX = Int.MIN_VALUE
    var strongestY = Int.MIN_VALUE
    for (x in xmin - radius + 1 until xmax + radius) {
        if (x < 0) continue
        for (y in ymin - radius + 1 until ymax + radius) {
            if (y < 0) continue
            var quality = 0
            for (t in towers) {
                val dis2 = (t[0] - x) * (t[0] - x) + (t[1] - y) * (t[1] - y)
                if (dis2 <= r2) quality += Math.floor(t[2] / (1 + Math.sqrt(dis2.toDouble()))).toInt()
            }
            // if(x == 34 && y == 22)System.out.printf("%s, %s, %s, %s\n", x, y, quality, strongest);
            if (quality > strongest) {
                strongest = quality
                strongestX = x
                strongestY = y
            }
            // 因为我们在迭代的时候，x和y都是递增的，所以quality = strongest的情况，已经被自动处理好了。
        }
    }
    return intArrayOf(strongestX, strongestY)
}
