
import java.util.*


/**
 * 判断字符串的两半是否相似
 *
 * 给你一个偶数长度的字符串 s 。将其拆分成长度相同的两半，前一半为 a ，后一半为 b 。
 * 两个字符串 相似 的前提是它们都含有相同数目的元音（'a'，'e'，'i'，'o'，'u'，'A'，'E'，'I'，'O'，'U'）。注意，s 可能同时含有大写和小写字母。
 * 如果 a 和 b 相似，返回 true ；否则，返回 false 。
 *
 * @see <a href="https://leetcode.cn/problems/determine-if-string-halves-are-alike/">判断字符串的两半是否相似</a>
 */
fun halvesAreAlike(s: String): Boolean {
    var left = 0
    var right = s.length - 1
    val arr = intArrayOf(0, 0)
    val map = charArrayOf('a','e','i','o','u','A','E','I','O','U')
    for (index in 0 until  (s.length / 2)) {
        if (s[left++] in map) {
            arr[0] += 1
        }
        if (s[right--] in map) {
            arr[1] += 1
        }
    }
    return arr[0] == arr[1]
}

/**
 * 分发饼干
 *
 * 假设你是一位很棒的家长，想要给你的孩子们一些小饼干。但是，每个孩子最多只能给一块饼干。
 * 对每个孩子 i，都有一个胃口值g[i]，这是能让孩子们满足胃口的饼干的最小尺寸；并且每块饼干 j，都有一个尺寸 s[j]。
 * 如果 s[j]>= g[i]，我们可以将这个饼干 j 分配给孩子 i ，这个孩子会得到满足。你的目标是尽可能满足越多数量的孩子，并输出这个最大数值。
 *
 * @see <a href="https://leetcode.cn/problems/assign-cookies/">分发饼干</a>
 */
// 贪心的思想是，用尽量小的饼干去满足小需求的孩子，所以需要进行排序先
fun findContentChildren(g: IntArray, s: IntArray): Int {
    var child = 0
    var cookie = 0

    Arrays.sort(g) // 先将饼干 和 孩子所需大小都进行排序
    Arrays.sort(s)

    while (child < g.size && cookie < s.size) { // 当其中一个遍历就结束
        if (g[child] <= s[cookie]) { // 当用当前饼干可以满足当前孩子的需求，可以满足的孩子数量 + 1
            child++
        }
        cookie++ // 饼干只可以用一次，因为饼干如果小的话，就是无法满足被抛弃，满足的话就是被用了
    }
    return child
}

/**
 * 重复的子字符串
 *
 * 给定一个非空的字符串 s ，检查是否可以通过由它的一个子串重复多次构成。
 *
 * @see <a href="https://leetcode.cn/problems/repeated-substring-pattern/">重复的子字符串</a>
 */
fun repeatedSubstringPattern(s: String): Boolean = (s + s).substring(1, s.length * 2 - 1).indexOf(s) != -1

/**
 * 岛屿的周长
 *
 * 给定一个 row x col 的二维网格地图 grid ，其中：grid[i][j] = 1 表示陆地， grid[i][j] = 0 表示水域。
 * 网格中的格子 水平和垂直 方向相连（对角线方向不相连）。整个网格被水完全包围，但其中恰好有一个岛屿（或者说，一个或多个表示陆地的格子相连组成的岛屿）。
 * 岛屿中没有“湖”（“湖” 指水域在岛屿内部且不和岛屿周围的水相连）。格子是边长为 1 的正方形。网格为长方形，且宽度和高度均不超过 100 。计算这个岛屿的周长。
 *
 * @see <a href="https://leetcode.cn/problems/island-perimeter">岛屿的周长</a>
 */
fun islandPerimeter(grid: Array<IntArray>): Int {
    if (grid.size == 0) return 0
    var rsp = 0
    for (i in 0 until grid.size) {
        for (j in 0 until grid[i].size) {
            if (grid[i][j] == 1) {
                rsp += 4
                if (i > 0 && grid[i - 1][j] == 1) {
                    rsp -= 2
                }
                if (j > 0 && grid[i][j - 1] == 1) {
                    rsp -= 2
                }
            }
        }
    }
    return rsp
}

/**
 * 数字的补数
 *
 * 对整数的二进制表示取反（0 变 1 ，1 变 0）后，再转换为十进制表示，可以得到这个整数的补数。
 * 例如，整数 5 的二进制表示是 "101" ，取反后得到 "010" ，再转回十进制表示得到补数 2 。
 * 给你一个整数 num ，输出它的补数。
 *
 * @see <a href="https://leetcode.cn/problems/number-complement/">数字的补数</a>
 */
fun findComplement(num: Int): Int {
    // 掩码
    var n = 1
    while ((num and n) != num) {
        n = (n shl 1) + 1
    }
    return num xor n
}