import java.util.*
import kotlin.collections.HashMap


/**
 * 细分图中的可到达节点
 *
 * 给你一个无向图（原始图），图中有 n 个节点，编号从 0 到 n - 1 。你决定将图中的每条边 细分 为一条节点链，每条边之间的新节点数各不相同。
 *
 * 图用由边组成的二维数组 edges 表示，其中edges[i] = [ui, vi, cnti] 表示原始图中节点 ui 和 vi 之间存在一条边，cnti 是将边 细分 后的新节点总数。注意，cnti == 0 表示边不可细分。
 * 要 细分 边 [ui, vi] ，需要将其替换为 (cnti + 1) 条新边，和 cnti 个新节点。新节点为 x1, x2, ..., xcnti ，新边为 [ui, x1], [x1, x2], [x2, x3], ..., [xcnti+1, xcnti], [xcnti, vi] 。
 *
 * 现在得到一个 新的细分图 ，请你计算从节点 0 出发，可以到达多少个节点？如果节点间距离是 maxMoves 或更少，则视为 可以到达 。
 * 给你原始图和 maxMoves ，返回 新的细分图中从节点 0 出发 可到达的节点数。
 *
 * @see <a href="https://leetcode.cn/problems/reachable-nodes-in-subdivided-graph">细分图中的可到达节点</a>
 */
fun reachableNodes(edges: Array<IntArray>, max: Int, n: Int): Int {
    val dis = IntArray(n) {Int.MAX_VALUE / 2}
    val visited = BooleanArray(n)
    dis[0] = 0
    // visited[0] = true;
    val graph: Array<HashMap<Int, Int>> = Array<HashMap<Int, Int>>(n){ HashMap() }

    for (e in edges) {
        val a = e[0]
        val b = e[1]
        val c = e[2]
        graph[a][b] = c + 1
        graph[b][a] = c + 1
    }

    for (i in 0 until n) {
        var min = Int.MAX_VALUE
        var p = -1
        for (j in 0 until n) {
            if (!visited[j] && dis[j] < min) {
                min = dis[j]
                p = j
            }
        }
        visited[p] = true
        for (q in graph[p].keys) {
            dis[q] = Math.min(dis[q], dis[p] + graph[p][q]!!)
        }
    }

    var ans = 0
    for (i in 0 until n) {
        if (dis[i] <= max) ans++
    }

    for (e in edges) {
        val a = e[0]
        val b = e[1]
        val c = e[2]
        var ca = 0
        var cb = 0
        if (dis[a] < max) {
            ca = max - dis[a]
        }
        if (dis[b] < max) {
            cb = max - dis[b]
        }
        ans += Math.min(c, ca + cb)
    }
    return ans
}

/**
 * 宝石与石头
 *
 * 给你一个字符串 jewels 代表石头中宝石的类型，另有一个字符串 stones 代表你拥有的石头。stones 中每个字符代表了一种你拥有的石头的类型，你想知道你拥有的石头中有多少是宝石。
 * 字母区分大小写，因此 "a" 和 "A" 是不同类型的石头。
 *
 * @see <a href="https://leetcode.cn/problems/jewels-and-stones">宝石与石头</a>
 */
fun numJewelsInStones(jewels: String, stones: String): Int {
    val map = jewels.toCharArray()
    var res = 0
    for (c in stones) {
        if (c in map) {
            res++
        }
    }
    return res
}

/**
 * 托普利茨矩阵
 *
 * 给你一个 m x n 的矩阵 matrix 。如果这个矩阵是托普利茨矩阵，返回 true ；否则，返回 false 。
 * 如果矩阵上每一条由左上到右下的对角线上的元素都相同，那么这个矩阵是 托普利茨矩阵 。
 *
 * @see <a href="https://leetcode.cn/problems/toeplitz-matrix">托普利茨矩阵</a>
 */
fun isToeplitzMatrix(matrix: Array<IntArray>): Boolean {
    for (r in 0 until matrix.size) {
        for (c in 0 until matrix[0].size) {
            if (r > 0 && c > 0 && matrix[r - 1][c - 1] !== matrix[r][c]) return false
        }
    }
    return true
}

/**
 * 二进制表示中质数个计算置位
 *
 * 给你两个整数left和right ，在闭区间 [left, right] 范围内，统计并返回 计算置位位数为质数 的整数个数。
 * 计算置位位数 就是二进制表示中 1 的个数。
 * 例如， 21的二进制表示10101有 3 个计算置位。
 *
 * @see <a href="https://leetcode.cn/problems/prime-number-of-set-bits-in-binary-representation">二进制表示中质数个计算置位</a>
 */
fun countPrimeSetBits(left: Int, right: Int): Int {
    // 665772的二进制表示是0000 0000 0000 1010 0010 1000 1010 1100，所有的 1 都在质数位置
    var res = 0
    for (i in left..right) {
        res += 665772 shr Integer.bitCount(i) and 1
    }
    return res
}

/**
 * 最短补全词
 *
 * 给你一个字符串 licensePlate 和一个字符串数组 words ，请你找出 words 中的 最短补全词 。
 * 补全词 是一个包含 licensePlate 中所有字母的单词。忽略 licensePlate 中的 数字和空格 。不区分大小写。如果某个字母在 licensePlate 中出现不止一次，那么该字母在补全词中的出现次数应当一致或者更多。
 * 例如：licensePlate = "aBc 12c"，那么它的补全词应当包含字母 'a'、'b' （忽略大写）和两个 'c' 。可能的 补全词 有 "abccdef"、"caaacab" 以及 "cbca" 。
 * 请返回 words 中的 最短补全词 。题目数据保证一定存在一个最短补全词。当有多个单词都符合最短补全词的匹配条件时取 words 中 第一个 出现的那个。
 *
 * @see <a href="https://leetcode.cn/problems/shortest-completing-word">最短补全词</a>
 */
fun shortestCompletingWord(licensePlate: String, words: Array<String>): String {
    Arrays.sort(words) { a: String, b: String -> a.length - b.length }
    val arr = IntArray(26)
    var sum = 0
    for (ch in licensePlate.toLowerCase().toCharArray()) {
        if (Character.isLowerCase(ch)) {
            arr[ch.hashCode() - 'a'.hashCode()]++
            sum++
        }
    }
    for (s in words) {
        val arr_ = Arrays.copyOf(arr, arr.size)
        var sum_ = 0
        val chs = s.toCharArray()
        for (ch in chs) {
            if (arr_[ch.hashCode() - 'a'.hashCode()] > 0) {
                arr_[ch.hashCode() - 'a'.hashCode()]--
                sum_++
            }
            if (sum_ == sum) return s
        }
    }
    return ""
}