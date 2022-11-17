import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashSet

/**
 * 匹配子序列的单词数
 *
 * 给定字符串 s 和字符串数组 words, 返回 words[i] 中是s的子序列的单词个数。
 * 字符串的 子序列 是从原始字符串中生成的新字符串，可以从中删去一些字符(可以是none)，而不改变其余字符的相对顺序。
 * 例如， “ace” 是 “abcde” 的子序列。
 *
 * @see <a href="https://leetcode.cn/problems/number-of-matching-subsequences">匹配子序列的单词数</a>
 */
fun numMatchingSubseq(s: String, words: Array<String>): Int {
    val c = s.toCharArray()
    val next = Array(c.size + 1){IntArray(26){-1}}
    val pos = IntArray(26){-1}
    for (i in (c.size - 1) downTo 0) {
        pos[c[i] - 'a'] = i
        for (j in 0 until 26) {
            next[i][j] = pos[j]
        }
    }
    var ans = words.size
    for (i in 0 until words.size) {
        var pre = 0
        val c1 = words[i].toCharArray()
        for (j in 0 until c1.size) {
            pre = next[pre][c1[j] - 'a']
            if (pre < 0) {
                ans--
                break
            }
            pre++
        }
    }
    return ans
}

/**
 * 最长和谐子序列
 *
 * 和谐数组是指一个数组里元素的最大值和最小值之间的差别 正好是 1 。
 * 现在，给你一个整数数组 nums ，请你在所有可能的子序列中找到最长的和谐子序列的长度。
 * 数组的子序列是一个由数组派生出来的序列，它可以通过删除一些元素或不删除元素、且不改变其余元素的顺序而得到。
 *
 * @see <a href="https://leetcode.cn/problems/longest-harmonious-subsequence">最长和谐子序列</a>
 */
fun findLHS(nums: IntArray): Int {
    Arrays.sort(nums)
    var begin = 0
    var res = 0
    for (end in 0 until nums.size) {
        while (nums[end] - nums[begin] > 1) {
            begin++
        }
        if (nums[end] - nums[begin] == 1) {
            res = Math.max(res, end - begin + 1)
        }
    }
    return res
}

/**
 * 分糖果
 *
 * Alice 有 n 枚糖，其中第 i 枚糖的类型为 candyType[i] 。Alice 注意到她的体重正在增长，所以前去拜访了一位医生。
 * 医生建议 Alice 要少摄入糖分，只吃掉她所有糖的 n / 2 即可（n 是一个偶数）。Alice 非常喜欢这些糖，她想要在遵循医生建议的情况下，尽可能吃到最多不同种类的糖。
 * 给你一个长度为 n 的整数数组 candyType ，返回： Alice 在仅吃掉 n / 2 枚糖的情况下，可以吃到糖的 最多 种类数。
 *
 * @see <a href="https://leetcode.cn/problems/distribute-candies/">分糖果</a>
 */
fun distributeCandies(candyType: IntArray): Int {
    val set = HashSet<Int>()
    for (c in candyType) {
        set.add(c)
    }
    return Math.min(set.size, candyType.size shr 1)
}

/**
 * N 叉树的前序遍历
 *
 * 给定一个 n 叉树的根节点 root，返回 其节点值的 前序遍历 。
 * n 叉树 在输入中按层序遍历进行序列化表示，每组子节点由空值 null 分隔。
 *
 * @see <a href="https://leetcode.cn/problems/n-ary-tree-preorder-traversal/">N 叉树的前序遍历</a>
 */
fun preorder(root: Node?): List<Int> {
    val res = ArrayList<Int>()
    if (root == null) {
        return res
    }
    var node: Node? = null
    val stack = Stack<Node>()
    stack.push(root)
    while (!stack.empty()) {
        node = stack.pop()
        res.add(node.`val`)
        for (i in node.children.size - 1 downTo 0) {
            stack.push(node.children[i])
        }
    }
    return res
}

/**
 * N 叉树的后序遍历
 *
 * 给定一个 n 叉树的根节点 root ，返回 其节点值的 后序遍历 。
 * n 叉树 在输入中按层序遍历进行序列化表示，每组子节点由空值 null 分隔。
 *
 * @see <a href="https://leetcode.cn/problems/n-ary-tree-postorder-traversal/">N 叉树的后序遍历</a>
 */
fun postorder(root: Node?): List<Int> {
    val list = ArrayList<Int>()
    dfs(root, list)
    return list
}

fun dfs(root: Node?, list: MutableList<Int>) {
    if (root == null) return
    for (child in root.children) {
        dfs(child, list)
    }
    list.add(root.`val`)
}
