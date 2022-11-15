/**
 * 卡车上的最大单元数
 *
 * 请你将一些箱子装在 一辆卡车 上。给你一个二维数组 boxTypes ，其中 boxTypes[i] = [numberOfBoxesi, numberOfUnitsPerBoxi] ：
 * numberOfBoxesi 是类型 i 的箱子的数量。
 * numberOfUnitsPerBoxi 是类型 i 每个箱子可以装载的单元数量。
 * 整数 truckSize 表示卡车上可以装载 箱子 的 最大数量 。只要箱子数量不超过 truckSize ，你就可以选择任意箱子装到卡车上。
 * 返回卡车可以装载单元 的 最大 总数。
 *
 * @see <a href="https://leetcode.cn/problems/maximum-units-on-a-truck">卡车上的最大单元数</a>
 */
fun maximumUnits(boxTypes: Array<IntArray>, truckSize: Int): Int {
    var count = 0
    var temp = truckSize
    val ans = IntArray(1001)
    for (box in boxTypes) {
        ans[box[1]] += box[0]
    }
    for (i in (ans.size - 1) downTo 1) {
        if (ans[i] != 0) {
            if (temp > ans[i]) {
                count += ans[i] * i
                temp -= ans[i]
            } else {
                return count + temp * i
            }
        }
    }
    return count
}

/**
 * 学生出勤记录 I
 *
 * 给你一个字符串 s 表示一个学生的出勤记录，其中的每个字符用来标记当天的出勤情况（缺勤、迟到、到场）。记录中只含下面三种字符：
 *
 * 'A'：Absent，缺勤
 * 'L'：Late，迟到
 * 'P'：Present，到场
 *
 * 如果学生能够 同时 满足下面两个条件，则可以获得出勤奖励：
 *
 * 按 总出勤 计，学生缺勤（'A'）严格 少于两天。
 * 学生 不会 存在 连续 3 天或 连续 3 天以上的迟到（'L'）记录。
 *
 * 如果学生可以获得出勤奖励，返回 true ；否则，返回 false 。
 *
 * @see <a href="https://leetcode.cn/problems/student-attendance-record-i">学生出勤记录 I</a>
 */
fun checkRecord(s: String): Boolean {
    return s.indexOf("A") == s.lastIndexOf("A") && s.indexOf("LLL") == -1
}

/**
 * 二叉树的直径
 *
 * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。
 *
 * @see <a href="https://leetcode.cn/problems/diameter-of-binary-tree/">二叉树的直径</a>
 */
var max = 0
fun diameterOfBinaryTree(root: TreeNode?): Int {
    if (root == null) {
        return 0;
    }
    dfs(root)
    return max
}

fun dfs(root: TreeNode?): Int {
    if (root!!.left == null && root!!.right == null) {
        return 0
    }
    val leftSize = if (root!!.left == null) 0 else dfs(root!!.left) + 1
    val rightSize = if (root!!.right == null) 0 else dfs(root!!.right) + 1
    max = Math.max(max, leftSize + rightSize)
    return Math.max(leftSize, rightSize)
}

/**
 * 反转字符串 II
 *
 * 给定一个字符串 s 和一个整数 k，从字符串开头算起，每计数至 2k 个字符，就反转这 2k 字符中的前 k 个字符。
 * 如果剩余字符少于 k 个，则将剩余字符全部反转。
 * 如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。
 *
 * @see <a href="https://leetcode.cn/problems/reverse-string-ii">反转字符串 II</a>
 */
fun reverseStr(s: String, k: Int): String {
    val res = StringBuffer()
    var start = 0
    while (start < s.length) {
        val temp = StringBuffer()
        val firstK = if (start + k > s.length) s.length else start + k
        val secondK = if (start + (2 * k) > s.length) s.length else start + (2 * k)

        temp.append(s.substring(start, firstK))
        res.append(temp.reverse())

        if (firstK < secondK) {
            res.append(s.substring(firstK, secondK))
        }
        start += (2 * k)
    }
    return res.toString()
}

/**
 * 二叉搜索树的最小绝对差
 *
 * 给你一个二叉搜索树的根节点 root ，返回 树中任意两不同节点值之间的最小差值 。
 * 差值是一个正数，其数值等于两值之差的绝对值。
 *
 * @see <a href="https://leetcode.cn/problems/minimum-absolute-difference-in-bst/">二叉搜索树的最小绝对差</a>
 */
var min = Int.MAX_VALUE
var left = Int.MAX_VALUE
fun getMinimumDifference(root: TreeNode?): Int {
    if (root == null) return 0
    getMinimumDifference(root.left)
    val a = Math.abs(left - root.`val`)
    min = if (a > min) min else a
    left = root.`val`
    getMinimumDifference(root.right)
    return min
}