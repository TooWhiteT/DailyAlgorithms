/**
 * 全局倒置与局部倒置
 *
 * 给你一个长度为 n 的整数数组 nums ，表示由范围 [0, n - 1] 内所有整数组成的一个排列。
 *
 * 全局倒置 的数目等于满足下述条件不同下标对 (i, j) 的数目：
 *
 * 0 <= i < j < n
 * nums[i] > nums[j]
 *
 * 局部倒置 的数目等于满足下述条件的下标 i 的数目：
 *
 * 0 <= i < n - 1
 * nums[i] > nums[i + 1]
 *
 * 当数组 nums 中 全局倒置 的数量等于 局部倒置 的数量时，返回 true ；否则，返回 false 。
 *
 * @see <a href="https://leetcode.cn/problems/global-and-local-inversions">全局倒置与局部倒置</a>
 */
fun isIdealPermutation(nums: IntArray): Boolean {
    var max = nums[0]
    for (i in 2 until nums.size) {
        if (nums[i] < max) {
            return false
        }
        max = Math.max(max, nums[i - 1])
    }
    return true
}

/**
 * 另一棵树的子树
 *
 * 给你两棵二叉树 root 和 subRoot 。检验 root 中是否包含和 subRoot 具有相同结构和节点值的子树。如果存在，返回 true ；否则，返回 false 。
 * 二叉树 tree 的一棵子树包括 tree 的某个节点和这个节点的所有后代节点。tree 也可以看做它自身的一棵子树。
 *
 * @see <a href="https://leetcode.cn/problems/subtree-of-another-tree">另一棵树的子树</a>
 */
fun isSubtree(root: TreeNode?, subRoot: TreeNode?): Boolean {
//    一个树是另一个树的子树 则:
//    要么这两个树相等
//    要么这个树是左树的子树
//    要么这个树hi右树的子树

    if (root == null){
        return false;
    }
    return isSameTree(root, subRoot) || isSubtree(root?.left, subRoot) || isSubtree(root?.right, subRoot)
}

/**
 * 重塑矩阵
 *
 * 在 MATLAB 中，有一个非常有用的函数 reshape ，它可以将一个 m x n 矩阵重塑为另一个大小不同（r x c）的新矩阵，但保留其原始数据。
 * 给你一个由二维数组 mat 表示的 m x n 矩阵，以及两个正整数 r 和 c ，分别表示想要的重构的矩阵的行数和列数。
 * 重构后的矩阵需要将原始矩阵的所有元素以相同的 行遍历顺序 填充。
 * 如果具有给定参数的 reshape 操作是可行且合理的，则输出新的重塑矩阵；否则，输出原始矩阵。
 *
 * @see <a href="https://leetcode.cn/problems/reshape-the-matrix">重塑矩阵</a>
 */
fun matrixReshape(mat: Array<IntArray>, r: Int, c: Int): Array<IntArray> {
    //矩阵的行和列
    val i: Int = mat.size
    val j: Int = mat[0].size
    //不可操作，返回原矩阵
    if (r == 0 || c == 0 || r == i && c == j || i * j != r * c) {
        return mat
    }

    val res = Array(r) { IntArray(c) }

    val temp = IntArray(r * c)
    var index = 0

    //不管目标矩阵是什么样的，先将原矩阵转换成数组，然后再转换成目标矩阵
    for (k in 0 until i) {
        for (p in 0 until j) {
            temp[index++] = mat[k][p]
        }
    }

    //数组转换成矩阵，容易找到下标的规律，而且可以减少循环的层数
    for (k in 0 until r) {
        for (p in 0 until c) {
            res[k][p] = temp[k * c + p]
        }
    }

    return res
}

/**
 * 二叉树的坡度
 *
 * 给你一个二叉树的根节点 root ，计算并返回 整个树 的坡度 。
 *
 * 一个树的 节点的坡度 定义即为，该节点左子树的节点之和和右子树节点之和的 差的绝对值 。如果没有左子树的话，左子树的节点之和为 0 ；没有右子树的话也是一样。空结点的坡度是 0 。
 * 整个树 的坡度就是其所有节点的坡度之和。
 *
 * @see <a href="https://leetcode.cn/problems/binary-tree-tilt">二叉树的坡度</a>
 */
fun findTilt(root: TreeNode?): Int {
    if(root == null) return 0
    return Math.abs(sum(root.left) - sum(root.right)) + findTilt(root.left) + findTilt(root.right)
}

fun sum(root: TreeNode?): Int {
    return if (root == null) 0 else root.`val` + sum(root.left) + sum(root.right)
}

/**
 * N 叉树的最大深度
 *
 * 给定一个 N 叉树，找到其最大深度。
 *
 * 最大深度是指从根节点到最远叶子节点的最长路径上的节点总数。
 *
 * N 叉树输入按层序遍历序列化表示，每组子节点由空值分隔（请参见示例）。
 *
 * @see <a href="https://leetcode.cn/problems/maximum-depth-of-n-ary-tree">N 叉树的最大深度</a>
 */
fun maxDepth(root: Node?): Int { // DFS
    if (root == null) return 0
    var depth = 0
    for (i in 0 until root.children.size) {
        depth = Math.max(depth, maxDepth(root.children[i]))
    }
    return depth + 1
}