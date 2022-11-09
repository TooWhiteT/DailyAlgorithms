import java.util.*


/**
 * 最大加号标志
 *
 * 在一个 n x n 的矩阵 grid 中，除了在数组mines中给出的元素为0，其他每个元素都为1。mines[i] = [xi, yi]表示grid[xi][yi] == 0
 * 返回 grid 中包含1的最大的 轴对齐 加号标志的阶数 。如果未找到加号标志，则返回 0 。
 * 一个k阶由1组成的 “轴对称”加号标志 具有中心网格grid[r][c] == 1，以及4个从中心向上、向下、向左、向右延伸，长度为k-1，由1组成的臂。
 * 注意，只有加号标志的所有网格要求为 1 ，别的网格可能为 0 也可能为 1 。
 *
 * @see <a href="https://leetcode.cn/problems/largest-plus-sign">最大加号标志</a>
 */
fun orderOfLargestPlusSign(n: Int, mines: Array<IntArray>): Int {
    var res = 0
    val graph = Array(n){IntArray(n)}
    for (i in 0 until mines.size) {
        graph[mines[i][0]][mines[i][1]] = 1
    }
    for (i in 0 until n) {
        for (j in 0 until n) {
            var num = 0
            for (k in 0 until n) {
                if (i + res >= n || i - res < 0 || j + res >= n || j - res < 0) {
                    break
                }
                if (graph[i+res][j] == 1 || graph[i-res][j] == 1 || graph[i][j+res] == 1 || graph[i][j-res] == 1) {
                    break
                }
                if (i + k >= n || i - k < 0 || j + k >= n || j - k < 0) {
                    break
                }
                if (graph[i + k][j] == 1 || graph[i - k][j] == 1 || graph[i][j + k] == 1 || graph[i][j - k] == 1) {
                    break
                }
                num++
            }
            res = Math.max(res, num)
        }
    }
    return res
}

/**
 * 二进制手表
 *
 * 二进制手表顶部有 4 个 LED 代表 小时（0-11），底部的 6 个 LED 代表 分钟（0-59）。每个 LED 代表一个 0 或 1，最低位在右侧。
 *
 * @see <a href="https://leetcode.cn/problems/binary-watch/">二进制手表</a>
 */
fun readBinaryWatch(turnedOn: Int): List<String> {
    val res: MutableList<String> = LinkedList()
    //直接遍历  0:00 -> 12:00   每个时间有多少1
    //直接遍历  0:00 -> 12:00   每个时间有多少1
    for (i in 0..11) {
        for (j in 0..59) {
            if (count1(i) + count1(j) == turnedOn) res.add(i.toString() + ":" + if (j < 10) "0$j" else j)
        }
    }
    return res
}

fun count1(n: Int): Int { // 计算二进制中1的个数
    var n = n
    var res = 0
    while (n != 0) {
        n = n and n - 1
        res++
    }
    return res
}

/**
 * 左叶子之和
 *
 * 给定二叉树的根节点 root ，返回所有左叶子之和。
 *
 * @see <a href="https://leetcode.cn/problems/sum-of-left-leaves/">左叶子之和</a>
 */
fun sumOfLeftLeaves(root: TreeNode?): Int {
    if (root == null) return 0
    var res = 0
    // 判断节点是否是左叶子节点，如果是则将它的和累计起来
    if (root.left != null && root?.left?.left == null && root?.left?.right == null) {
        res += root.left!!.`val`
    }
    return sumOfLeftLeaves(root.left) + sumOfLeftLeaves(root.right) + res
}

/**
 * 数字转换为十六进制数
 *
 * 给定一个整数，编写一个算法将这个数转换为十六进制数。对于负整数，我们通常使用补码运算方法。
 * 注意:
 *
 * 十六进制中所有字母(a-f)都必须是小写。
 * 十六进制字符串中不能包含多余的前导零。如果要转化的数为0，那么以单个字符'0'来表示；对于其他情况，十六进制字符串中的第一个字符将不会是0字符。
 * 给定的数确保在32位有符号整数范围内。
 * 不能使用任何由库提供的将数字直接转换或格式化为十六进制的方法。
 *
 * @see <a href="https://leetcode.cn/problems/convert-a-number-to-hexadecimal/">数字转换为十六进制数</a>
 */
fun toHex(num: Int): String {
    if (num == 0) return "0"
    var temp = num
    val chars = charArrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f')
    val sb = StringBuilder()
    while (sb.length < 8 && temp != 0) {
        sb.append(chars[temp and 0xf])
        temp = temp shr 4
    }
    return sb.reverse().toString()
}


/**
 * 最长回文串
 *
 * 给定一个包含大写字母和小写字母的字符串s，返回通过这些字母构造成的 最长的回文串。
 * 在构造过程中，请注意 区分大小写 。比如"Aa"不能当做一个回文字符串。
 *
 * @see <a href="https://leetcode.cn/problems/longest-palindrome">最长回文串</a>
 */
fun longestPalindrome(s: String): Int {
    val cnt = IntArray(58)
    for (c in s.toCharArray()) {
        cnt[c.hashCode() - 'A'.hashCode()] += 1
    }

    var ans = 0
    for (x in cnt) {
        // 字符出现的次数最多用偶数次。
        ans += x - (x and 1)
    }
    // 如果最终的长度小于原字符串的长度，说明里面某个字符出现了奇数次，那么那个字符可以放在回文串的中间，所以额外再加一。
    return if (ans < s.length) ans + 1 else ans
}