import java.util.Arrays

/**
 * 子序列宽度之和
 *
 * 一个序列的 宽度 定义为该序列中最大元素和最小元素的差值。
 * 给你一个整数数组 nums ，返回 nums 的所有非空 子序列 的 宽度之和 。由于答案可能非常大，请返回对 109 + 7 取余 后的结果。
 * 子序列 定义为从一个数组里删除一些（或者不删除）元素，但不改变剩下元素的顺序得到的数组。例如，[3,6,2,7] 就是数组 [0,3,1,6,2,2,7] 的一个子序列。
 *
 * @see <a href="https://leetcode.cn/problems/sum-of-subsequence-widths/">子序列宽度之和</a>
 */
fun sumSubseqWidths(nums: IntArray): Int {
    Arrays.sort(nums)
    val n = nums.size
    var ans: Long = 0
    var pow: Long = 1
    val mod = (1e9 + 7).toInt()
    for (i in 0 until nums.size) {
        ans = (ans + pow * (nums[i] - nums[n - 1 - i] + mod)) % mod
        pow = pow * 2 % mod
    }
    return ans.toInt()
}

/**
 * 范围求和 II
 *
 * 给你一个 m x n 的矩阵M，初始化时所有的 0 和一个操作数组 op ，其中 ops[i] = [ai, bi] 意味着当所有的 0 <= x < ai 和 0 <= y < bi 时， M[x][y] 应该加 1。
 * 在执行完所有操作后，计算并返回 矩阵中最大整数的个数。
 *
 * @see <a href="https://leetcode.cn/problems/range-addition-ii">范围求和 II</a>
 */
fun maxCount(m: Int, n: Int, ops: Array<IntArray>): Int {
    var i = m
    var j = n
    for (op in ops) {
        if (i > op[0]) i = op[0]
        if (j > op[1]) j = op[1]
    }
    return i * j
}

/**
 * 两个列表的最小索引总和
 *
 * 假设 Andy 和 Doris 想在晚餐时选择一家餐厅，并且他们都有一个表示最喜爱餐厅的列表，每个餐厅的名字用字符串表示。
 * 你需要帮助他们用最少的索引和找出他们共同喜爱的餐厅。 如果答案不止一个，则输出所有答案并且不考虑顺序。 你可以假设答案总是存在。
 *
 * @see <a href="https://leetcode.cn/problems/minimum-index-sum-of-two-lists">两个列表的最小索引总和</a>
 */
fun findRestaurant(list1: Array<String>, list2: Array<String>): List<String> {
    var min = Int.MAX_VALUE
    val ans = ArrayList<String>()
    for (i in 0 until list1.size) {
        for (j in 0 until list2.size) {
            if (list1[i].equals(list2[j])) {
                if (i + j == min) {
                    ans.add(list1[i])
                } else if (i + j < min) {
                    min = i + j
                    ans.clear()
                    ans.add(list1[i])
                }
            }
        }
    }
    return ans
}

/**
 * 种花问题
 *
 * 假设有一个很长的花坛，一部分地块种植了花，另一部分却没有。可是，花不能种植在相邻的地块上，它们会争夺水源，两者都会死去。
 * 给你一个整数数组 flowerbed 表示花坛，由若干 0 和 1 组成，其中 0 表示没种植花，1 表示种植了花。另有一个数 n ，能否在不打破种植规则的情况下种入 n 朵花？
 * 能则返回 true ，不能则返回 false。
 *
 * @see <a href="https://leetcode.cn/problems/can-place-flowers">种花问题</a>
 */
fun canPlaceFlowers(flowerbed: IntArray, n: Int): Boolean {
    var num = 0
    var count = 1
    for (i in 0 until flowerbed.size) {
        if (flowerbed[i] == 0) {
            count++
        } else {
            count = 0
        }
        if (count == 3) {
            num++
            count = 1
        }
    }
    if (count == 2) {
        num++
    }
    return n <= num
}

/**
 * 根据二叉树创建字符串
 *
 * 给你二叉树的根节点 root ，请你采用前序遍历的方式，将二叉树转化为一个由括号和整数组成的字符串，返回构造出的字符串。
 * 空节点使用一对空括号对 "()" 表示，转化后需要省略所有不影响字符串与原始二叉树之间的一对一映射关系的空括号对。
 *
 * @see <a href="https://leetcode.cn/problems/construct-string-from-binary-tree/">根据二叉树创建字符串</a>
 */
fun tree2str(root: TreeNode?): String {
    val sb = StringBuilder()
    doTree2str(root, sb)
    return sb.toString()
}

fun doTree2str(t: TreeNode?, sb: StringBuilder) {
    if (t != null) {
        sb.append(t.`val`)
        if (t.left != null || t.right != null) {
            sb.append('(')
            doTree2str(t.left, sb)
            sb.append(')')
            if (t.right != null) {
                sb.append('(')
                doTree2str(t.right, sb)
                sb.append(')')
            }
        }
    }
}