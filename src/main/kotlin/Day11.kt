import java.util.Arrays
import java.util.LinkedList

/**
 * 获取所有钥匙的最短路径
 *
 * 给定一个二维网格 grid ，其中：
 *
 * '.' 代表一个空房间
 * '#' 代表一堵
 * '@' 是起点
 * 小写字母代表钥匙
 * 大写字母代表锁
 *
 * 我们从起点开始出发，一次移动是指向四个基本方向之一行走一个单位空间。我们不能在网格外面行走，也无法穿过一堵墙。
 * 如果途经一个钥匙，我们就把它捡起来。除非我们手里有对应的钥匙，否则无法通过锁。
 * 假设 k 为 钥匙/锁 的个数，且满足1 <= k <= 6，字母表中的前 k个字母在网格中都有自己对应的一个小写和一个大写字母。
 * 换言之，每个锁有唯一对应的钥匙，每个钥匙也有唯一对应的锁。另外，代表钥匙和锁的字母互为大小写并按字母顺序排列。
 * 返回获取所有钥匙所需要的移动的最少次数。如果无法获取所有钥匙，返回-1。
 *
 * @see <a href="https://leetcode.cn/problems/shortest-path-to-get-all-keys/">获取所有钥匙的最短路径</a>
 */
fun shortestPathAllKeys(grid: Array<String>): Int { // BFS
    val move = arrayOf(intArrayOf(1,0), intArrayOf(-1,0), intArrayOf(0,1), intArrayOf(0,-1))
    var m = grid.size
    var n = grid[0].length
    var startX = -1
    var startY = -1
    var count = 0
    // 先遍历网格找到起点坐标以及字母个数
    for (i in 0 until m) {
        for (j in 0 until n) {
            val c = grid[i][j]
            if (c == '@') {
                startX = i
                startY = j
            } else if (c >= 'a' && c < 'g') {
                count = Math.max(count, c - 'a' + 1)
            }
        }
    }
    var max = 1 shl count
    val step = Array(m){ Array(n) {IntArray(max)} }
    for (i in 0 until m) {
        for (j in 0 until n) {
            Arrays.fill(step[i][j], 1e9.toInt())
        }
    }
    step[startX][startY][0] = 0
    val q = LinkedList<IntArray>()
    q.add(intArrayOf(startX, startY, 0, 0)) // 坐标 钥匙情况 步数
    while (q.size > 0) {
        val a = q.poll()
        for (i in 0 until 4) {
            val x = a[0] + move[i][0]
            val y = a[1] + move[i][1]
            if (x < 0 || x == m || y < 0 || y == n) continue
            val c = grid[x][y]
            if (c == '#') continue
            if (c == '.' || c == '@') {
                if (step[x][y][a[2]] > a[3] + 1) {
                    step[x][y][a[2]] = a[3] + 1
                    q.add(intArrayOf(x, y, a[2], a[3] + 1))
                }
            } else if (c.isUpperCase()) {
                val bool1 = (a[2] and (1 shl (c - 'A'))) == 0
                val bool2 = step[x][y][a[2]] <= a[3] + 1
                if ( bool1 || bool2) continue
                step[x][y][a[2]] = a[3] + 1
                q.add(intArrayOf(x, y, a[2], a[3] + 1))
            } else {
                val bool = step[x][y][a[2] or (1 shl (c - 'a'))] <= a[3] + 1
                if (bool) continue
                if (max - 1 == (a[2] or (1 shl (c - 'a')))) {
                    return a[3] + 1
                }
                step[x][y][a[2] or (1 shl (c - 'a'))] = a[3] + 1
                q.add(intArrayOf(x, y, a[2] or (1 shl (c- 'a')), a[3] + 1))
            }
        }
    }
    return -1
}

/**
 * 第三大的数
 *
 * 给你一个非空数组，返回此数组中 第三大的数 。如果不存在，则返回数组中最大的数。
 *
 * @see <a href="https://leetcode.cn/problems/third-maximum-number/">第三大的数</a>
 */
fun thirdMax(nums: IntArray): Int {
    if (nums.size == 1) return nums[0]
    if (nums.size == 2) return Math.max(nums[0], nums[1])
    var max1 = Int.MIN_VALUE
    var max2 = Int.MIN_VALUE
    var max3 = Int.MIN_VALUE
    var f = true
    var flag = 0
    for (i in nums) {
        if (i == Int.MIN_VALUE && f) {
            flag++
            f = false
        }

        if (i > max1) {
            flag++
            max3 = max2
            max2 = max1
            max1 = i
        } else if (i > max2 && i < max1) {
            flag++
            max3 = max2
            max2 = i
        } else if (i > max3 && i < max2) {
            flag++
            max3 = i
        }
    }

    if (flag >= 3) {
        return max3
    } else {
        return max1
    }
}

/**
 * 字符串中的单词数
 *
 * 统计字符串中的单词个数，这里的单词指的是连续的不是空格的字符。
 * 请注意，你可以假定字符串里不包括任何不可打印的字符。
 *
 * @see <a href="https://leetcode.cn/problems/number-of-segments-in-a-string/">字符串中的单词数</a>
 */
fun countSegments(s: String): Int {
    val strs = s.split(" ")
    var count = 0
    for (str in strs) {
        if (!str.equals("")) {
            count++
        }
    }
    return count
}

/**
 * 排列硬币
 *
 * 你总共有n枚硬币，并计划将它们按阶梯状排列。对于一个由 k 行组成的阶梯，其第 i 行必须正好有 i 枚硬币。阶梯的最后一行 可能 是不完整的。
 * 给你一个数字n ，计算并返回可形成 完整阶梯行 的总行数。
 *
 * @see <a href="https://leetcode.cn/problems/arranging-coins/">排列硬币</a>
 */
fun arrangeCoins(n: Int): Int {
    var count = 0
    var temp = n
    var next_layer = 1
    while (temp >= next_layer) {
        temp -= next_layer
        next_layer++
        count++
    }
    return count
}

/**
 * 找到所有数组中消失的数字
 *
 * 给你一个含 n 个整数的数组 nums ，其中 nums[i] 在区间 [1, n] 内。请你找出所有在 [1, n] 范围内但没有出现在 nums 中的数字，并以数组的形式返回结果。
 *
 * @see <a href="https://leetcode.cn/problems/find-all-numbers-disappeared-in-an-array/">找到所有数组中消失的数字</a>
 */
fun findDisappearedNumbers(nums: IntArray): List<Int> {
    val list = ArrayList<Int>()
    val map = HashMap<Int, Int>()
    for (i in nums) {
        map.put(i, i)
    }
    for (i in 1..nums.size) {
        if (!map.containsKey(i)) {
            list.add(i)
        }
    }
    return list
}