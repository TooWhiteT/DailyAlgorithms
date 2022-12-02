import java.util.*

/**
 * 移动所有球到每个盒子所需的最小操作数
 *
 * 有 n 个盒子。给你一个长度为 n 的二进制字符串 boxes ，其中 boxes[i] 的值为 '0' 表示第 i 个盒子是 空 的，而 boxes[i] 的值为 '1' 表示盒子里有 一个 小球。
 * 在一步操作中，你可以将 一个 小球从某个盒子移动到一个与之相邻的盒子中。第 i 个盒子和第 j 个盒子相邻需满足 abs(i - j) == 1 。
 * 注意，操作执行后，某些盒子中可能会存在不止一个小球。
 * 返回一个长度为 n 的数组 answer ，其中 answer[i] 是将所有小球移动到第 i 个盒子所需的 最小 操作数。
 * 每个 answer[i] 都需要根据盒子的 初始状态 进行计算。
 *
 * @see <a href="https://leetcode.cn/problems/minimum-number-of-operations-to-move-all-balls-to-each-box">移动所有球到每个盒子所需的最小操作数</a>
 */
fun minOperationsplus(boxes: String): IntArray {
    var left = 0
    var right = 0
    var left_n = 0
    var right_n = 0
    val chs = boxes.toCharArray()
    val res = IntArray(chs.size)
    for (i in 1 until chs.size) {
        if (chs[i] == '1') {
            right += i
            right_n++
        }
    }
    res[0] = right
    for (j in 1 until chs.size) {
        if (chs[j - 1] == '1') left_n++
        left += left_n
        right -= right_n
        res[j] =  left + right
        if (chs[j] == '1') right_n--
    }
    return res
}

/**
 * 公平的糖果交换
 *
 * 爱丽丝和鲍勃拥有不同总数量的糖果。给你两个数组 aliceSizes 和 bobSizes ，aliceSizes[i] 是爱丽丝拥有的第 i 盒糖果中的糖果数量，bobSizes[j] 是鲍勃拥有的第 j 盒糖果中的糖果数量。
 * 两人想要互相交换一盒糖果，这样在交换之后，他们就可以拥有相同总数量的糖果。
 * 一个人拥有的糖果总数量是他们每盒糖果数量的总和。
 * 返回一个整数数组 answer，其中 answer[0] 是爱丽丝必须交换的糖果盒中的糖果的数目，answer[1] 是鲍勃必须交换的糖果盒中的糖果的数目。
 * 如果存在多个答案，你可以返回其中 任何一个 。题目测试用例保证存在与输入对应的答案。
 *
 * @see <a href="https://leetcode.cn/problems/fair-candy-swap">公平的糖果交换</a>
 */
fun fairCandySwap(aliceSizes: IntArray, bobSizes: IntArray): IntArray {
    val ans = IntArray(2)
    var sumA = 0
    var sumB = 0
    for (i in aliceSizes) {
        sumA += i
    }
    for (j in bobSizes) {
        sumB += j
    }
    Arrays.sort(aliceSizes)
    Arrays.sort(bobSizes)
    val temp = sumA - (sumA + sumB) / 2
    var i = 0
    var j = 0
    while (i < aliceSizes.size && j < bobSizes.size) {
        if (aliceSizes[i] - bobSizes[j] == temp) {
            ans[0] = aliceSizes[i]
            ans[1] = bobSizes[j]
            break
        } else if (aliceSizes[i] - bobSizes[j] > temp) {
            j++
        } else if (aliceSizes[i] - bobSizes[j] < temp) {
            i++
        }
    }
    return ans
}

/**
 * 三维形体的表面积
 *
 * 给你一个 n * n 的网格 grid ，上面放置着一些 1 x 1 x 1 的正方体。每个值 v = grid[i][j] 表示 v 个正方体叠放在对应单元格 (i, j) 上。
 * 放置好正方体后，任何直接相邻的正方体都会互相粘在一起，形成一些不规则的三维形体。
 * 请你返回最终这些形体的总表面积。
 * 注意：每个形体的底面也需要计入表面积中。
 *
 * @see <a href="https://leetcode.cn/problems/surface-area-of-3d-shapes/">三维形体的表面积</a>
 */
fun surfaceArea(grid: Array<IntArray>): Int {
    var res = 0
    for (i in 0 until grid.size) {
        for (j in 0 until grid[i].size) {
            if (grid[i][j] != 0) {
                // 假设每个 v = grid[i][j] 都是独立的。
                res += grid[i][j] * 4 + 2
            }
            // 减去面贴在一起的情况
            if (i > 0) {
                res -= Math.min(grid[i - 1][j], grid[i][j]) * 2
            }
            if (j > 0) {
                res -= Math.min(grid[i][j - 1], grid[i][j]) * 2
            }
        }
    }
    return res
}

/**
 * 单调数列
 *
 * 如果数组是单调递增或单调递减的，那么它是 单调 的。
 * 如果对于所有 i <= j，nums[i] <= nums[j]，那么数组 nums 是单调递增的。 如果对于所有 i <= j，nums[i]> = nums[j]，那么数组 nums 是单调递减的。
 * 当给定的数组 nums 是单调数组时返回 true，否则返回 false。
 *
 * @see <a href="https://leetcode.cn/problems/monotonic-array">单调数列</a>
 */
fun isMonotonic(nums: IntArray): Boolean {
    val len = nums.size
    if (len == 1) return true
    var a = 0
    var b = 0
    for (i in 0 until len - 1) {
        if (nums[i] < nums[i + 1]) a = 1
        if (nums[i] > nums[i + 1]) b = 1
    }
    return a + b != 2
}

/**
 * 递增顺序搜索树
 *
 * 给你一棵二叉搜索树的 root ，请你 按中序遍历 将其重新排列为一棵递增顺序搜索树，使树中最左边的节点成为树的根节点，并且每个节点没有左子节点，只有一个右子节点。
 *
 * @see <a href="https://leetcode.cn/problems/increasing-order-search-tree">递增顺序搜索树</a>
 */
var prev33: TreeNode? = null
var r33: TreeNode? = null
fun increasingBST(root: TreeNode?): TreeNode? {
    helper(root)
    return r33
}

fun helper(root: TreeNode?) {
    if (root == null) return
    helper(root.left)
    if (prev33 == null) {
        r33 = root
    } else {
        prev?.right = root
    }
    prev33 = root
    root.left = null
    helper(root.right)
}