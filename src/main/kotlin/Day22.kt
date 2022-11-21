/**
 * 分汤
 *
 * 有 A 和 B 两种类型的汤。一开始每种类型的汤有n毫升。有四种分配操作：
 *
 *  1. 提供 100ml 的 汤A 和 0ml 的 汤B 。
 *  2. 提供 75ml 的 汤A 和 25ml 的 汤B 。
 *  3. 提供 50ml 的 汤A 和 50ml 的 汤B 。
 *  4. 提供 25ml 的 汤A 和 75ml 的 汤B 。
 *
 * 当我们把汤分配给某人之后，汤就没有了。每个回合，我们将从四种概率同为 0.25 的操作中进行分配选择。如果汤的剩余量不足以完成某次操作，我们将尽可能分配。当两种类型的汤都分配完时，停止操作。
 *
 * 注意 不存在先分配 100 ml 汤B 的操作。
 * 需要返回的值：汤A 先分配完的概率 + 汤A 和 汤B同时分配完的概率 / 2。返回值在正确答案10^-5的范围内将被认为是正确的。
 *
 * @see <a href="https://leetcode.cn/problems/soup-servings">分汤</a>
 */
fun soupServings(n: Int): Double {
    if (n >= 4800) return 1.0
    val n: Int = Math.ceil(n / 25.0).toInt()
    val dp = Array(n + 1){ DoubleArray( n + 1) }
    dp[0][0] = 0.5
    for (i in 1 until n + 1) {
        dp[i][0] = 0.0
        dp[0][i] = 1.0
    }
    for (i in 1 until n + 1) {
        val a1 = if (i - 4 > 0) i - 4 else 0
        val a2 = if (i - 3 > 0) i - 3 else 0
        val a3 = if (i - 2 > 0) i - 2 else 0
        val a4 = if (i - 1 > 0) i - 1 else 0

        for (j in 1 until n + 1) {
            val b1 = j
            val b2 = if (j - 1 > 0) j - 1 else 0
            val b3 = if (j - 2 > 0) j - 2 else 0
            val b4 = if (j - 3 > 0) j - 3 else 0
            dp[i][j] = 0.25 * (dp[a1][b1] + dp[a2][b2] + dp[a3][b3] + dp[a4][b4])
        }
    }
    return dp[n][n]
}

/**
 * 验证回文串 II
 *
 * 给你一个字符串 s，最多 可以从中删除一个字符。
 * 请你判断 s 是否能成为回文字符串：如果能，返回 true ；否则，返回 false 。
 *
 * @see <a href="https://leetcode.cn/problems/valid-palindrome-ii/">验证回文串 II</a>
 */
fun validPalindrome(s: String): Boolean = valid(s, 0, s.length - 1, false)

fun valid(s: String, i: Int, j: Int, isDeep: Boolean): Boolean {
    var i = i
    var j = j
    while (i < j) {
        val a = s[i]
        val b = s[j]
        if (a != b) {
           if (isDeep) return false
            return valid(s, i, j - 1, true) || valid(s, i + 1, j, true)
        }
        i++
        j--
    }
    return true
}

/**
 * 最长连续递增序列
 *
 * 给定一个未经排序的整数数组，找到最长且 连续递增的子序列，并返回该序列的长度。
 * 连续递增的子序列 可以由两个下标 l 和 r（l < r）确定，如果对于每个 l <= i < r，都有 nums[i] < nums[i + 1] ，那么子序列 [nums[l], nums[l + 1], ..., nums[r - 1], nums[r]] 就是连续递增子序列。
 *
 * @see <a href="https://leetcode.cn/problems/longest-continuous-increasing-subsequence">最长连续递增序列</a>
 */
fun findLengthOfLCIS(nums: IntArray): Int {
    if (nums.size < 1) {
        return 0
    }
    var d = 0
    var max = 1
    for (i in 1 until nums.size) {
        if (nums[i] > nums[i - 1]) {
            max = Math.max(i - d + 1, max)
        } else {
            d = i
        }
    }
    return max
}

/**
 * 二叉树中第二小的节点
 *
 * 给定一个非空特殊的二叉树，每个节点都是正数，并且每个节点的子节点数量只能为 2 或 0。
 * 如果一个节点有两个子节点的话，那么该节点的值等于两个子节点中较小的一个。
 * 更正式地说，即 root.val = min(root.left.val, root.right.val) 总成立。
 * 给出这样的一个二叉树，你需要输出所有节点中的第二小的值 。如果第二小的值不存在的话，输出 -1 。
 *
 * @see <a href="https://leetcode.cn/problems/second-minimum-node-in-a-binary-tree"> 二叉树中第二小的节点</a>
 */
fun findSecondMinimumValue(root: TreeNode?): Int {
    return dfs(root, root!!.`val`)
}

fun dfs(root: TreeNode?, `val`: Int): Int {
    if (root == null) {
        return -1
    }
    if (root.`val` > `val`) {
        return root.`val`
    }
    val l: Int = dfs(root.left, `val`)
    val r: Int = dfs(root.right, `val`)
    if (l > `val` && r > `val`) {
        return Math.min(l, r)
    }
    return Math.max(l, r)
}

/**
 * 图片平滑器
 *
 * 图像平滑器 是大小为 3 x 3 的过滤器，用于对图像的每个单元格平滑处理，平滑处理后单元格的值为该单元格的平均灰度。
 * 每个单元格的 平均灰度 定义为：该单元格自身及其周围的 8 个单元格的平均值，结果需向下取整。（即，需要计算蓝色平滑器中 9 个单元格的平均值）。
 * 如果一个单元格周围存在单元格缺失的情况，则计算平均灰度时不考虑缺失的单元格（即，需要计算红色平滑器中 4 个单元格的平均值）。
 *
 * @see <a href="https://leetcode.cn/problems/image-smoother">图片平滑器</a>
 */
fun imageSmoother(img: Array<IntArray>): Array<IntArray> {
    val l1 = img.size
    val l2 = img[0].size
    val r = Array(l1){IntArray(l2)}
    for (i in 0 until l1) {
        for (j in 0 until l2) {
            var sum = img[i][j]
            var num = 1
            if ( i > 0 && j > 0) {
                sum += img[i - 1][j - 1]
                num++
            }
            if (i > 0) {
                sum += img[i - 1][j]
                num++
            }
            if (i > 0 && j < l2 - 1) {
                sum += img[i - 1][j + 1]
                num++
            }
            if (j > 0) {
                sum += img[i][j - 1]
                num++
            }
            if (j < l2 - 1) {
                sum += img[i][j + 1]
                num++
            }
            if (i < l1 - 1 && j > 0) {
                sum += img[i + 1][j - 1]
                num++
            }
            if (i < l1 - 1) {
                sum += img[i + 1][j]
                num++
            }
            if (i < l1 - 1 && j < l2 - 1) {
                sum += img[i + 1][j + 1]
                num++
            }
            r[i][j] = sum / num
        }
    }
    return r
}