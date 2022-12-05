
import java.util.*


/**
 * 从仓库到码头运输箱子
 *
 * 你有一辆货运卡车，你需要用这一辆车把一些箱子从仓库运送到码头。这辆卡车每次运输有箱子数目的限制和 总重量的限制。
 * 给你一个箱子数组boxes和三个整数 portsCount, maxBoxes和maxWeight，其中boxes[i] = [portsi, weighti]。
 *  portsi表示第i个箱子需要送达的码头，weightsi是第i个箱子的重量。
 *  portsCount是码头的数目。
 *  maxBoxes 和maxWeight分别是卡车每趟运输箱子数目和重量的限制。
 * 箱子需要按照 数组顺序运输，同时每次运输需要遵循以下步骤：
 *  卡车从boxes队列中按顺序取出若干个箱子，但不能违反maxBoxes 和maxWeight限制。
 *  对于在卡车上的箱子，我们需要 按顺序处理它们，卡车会通过 一趟行程将最前面的箱子送到目的地码头并卸货。如果卡车已经在对应的码头，那么不需要 额外行程，箱子也会立马被卸货。
 *  卡车上所有箱子都被卸货后，卡车需要 一趟行程回到仓库，从箱子队列里再取出一些箱子。
 *  卡车在将所有箱子运输并卸货后，最后必须回到仓库。
 * 请你返回将所有箱子送到相应码头的最少行程次数。
 *
 * @see <a href="https://leetcode.cn/problems/delivering-boxes-from-storage-to-ports">从仓库到码头运输箱子</a>
 */
fun boxDelivering(boxes: Array<IntArray>, portsCount: Int, maxBoxes: Int, maxWeight: Int): Int {
    val n: Int = boxes.size
    val preSum1 = LongArray(n + 1)
    val preSum2 = IntArray(n + 5)
    val ans = IntArray(n + 1)
    for (i in 1..n) {
        preSum1[i] = preSum1[i - 1] + boxes[i - 1][1]
        preSum2[i] = preSum2[i - 1]
        if (i == 1 || i > 1 && boxes[i - 1][0] !== boxes[i - 2][0]) {
            preSum2[i]++
        }
    }
    val q = ArrayDeque<Int>()
    q.addLast(0)
    for (i in 1..n) {
        while (true) {
            val a = q.first
            if (a < i - maxBoxes || maxWeight < preSum1[i] - preSum1[a]) {
                q.removeFirst()
            } else {
                break
            }
        }
        val b = q.first
        ans[i] = ans[b] + preSum2[i] - preSum2[b + 1] + 2
        while (q.size > 0) {
            val a = q.last
            if (ans[a] - preSum2[a + 1] >= ans[i] - preSum2[i + 1]) {
                q.removeLast()
            } else {
                break
            }
        }
        q.addLast(i)
    }
    return ans[n]
}

/**
 * 二叉搜索树的范围和
 *
 * 给定二叉搜索树的根结点 root，返回值位于范围 [low, high] 之间的所有结点的值的和。
 *
 * @see <a href="https://leetcode.cn/problems/range-sum-of-bst/">二叉搜索树的范围和</a>
 */
fun rangeSumBST(root: TreeNode?, low: Int, high: Int): Int {
    if (root == null) return 0
    if (root.`val` >= low && root.`val` <= high) {
        return root.`val` + rangeSumBST(root.left, low, high) + rangeSumBST(root.right, low, high)
    } else if (root.`val` < low) {
        return rangeSumBST(root.right, low, high)
    } else {
        return rangeSumBST(root.left, low, high)
    }
}

/**
 * 有效的山脉数组
 *
 * 给定一个整数数组 arr，如果它是有效的山脉数组就返回true，否则返回 false。
 * 让我们回顾一下，如果 arr满足下述条件，那么它是一个山脉数组：
 *  arr.length >= 3
 *  在0 < i< arr.length - 1条件下，存在i使得：
 *      arr[0] < arr[1] < ... arr[i-1] < arr[i]
 *      arr[i] > arr[i+1] > ... > arr[arr.length - 1]
 *
 * @see <a href="https://leetcode.cn/problems/valid-mountain-array">有效的山脉数组</a>
 */
fun validMountainArray(arr: IntArray): Boolean {
    if (arr.size < 3) return false
    var left = 0
    var right = arr.size - 1
    while (left < arr.size - 2 && arr[left] < arr[left + 1]) {
        left++
    }
    while (right > 1 && arr[right] < arr[right - 1]) {
        right--
    }
    return left == right
}

/**
 * 增减字符串匹配
 *
 * 由范围 [0,n] 内所有整数组成的 n + 1 个整数的排列序列可以表示为长度为 n 的字符串 s ，其中:
 *  如果perm[i] < perm[i + 1]，那么s[i] == 'I'
 *  如果perm[i] > perm[i + 1]，那么 s[i] == 'D'
 *
 * 给定一个字符串 s ，重构排列perm 并返回它。如果有多个有效排列perm，则返回其中 任何一个 。
 *
 * @see <a href="https://leetcode.cn/problems/di-string-match">增减字符串匹配</a>
 */
fun diStringMatch(s: String): IntArray {
    var numi = 0
    var numd = s.length
    val res = ArrayList<Int>()
    for (i in 0 until s.length) {
        if (s[i] == 'I') {
            res.add(numi++)
        } else if (s[i] == 'D') {
            res.add(numd--)
        }
    }
    res.add(numd)
    return res.toIntArray()
}

/**
 * 删列造序
 *
 * 给你由 n 个小写字母字符串组成的数组 strs，其中每个字符串长度相等。
 * 这些字符串可以每个一行，排成一个网格。例如，strs = ["abc", "bce", "cae"] 可以排列为:
 *
 * abc
 * bce
 * cae
 *
 * 你需要找出并删除 不是按字典序升序排列的 列。在上面的例子（下标从 0 开始）中，列 0（'a', 'b', 'c'）和列 2（'c', 'e', 'e'）都是按升序排列的，而列 1（'b', 'c', 'a'）不是，所以要删除列 1 。
 * 返回你需要删除的列数。
 *
 * @see <a href="https://leetcode.cn/problems/delete-columns-to-make-sorted">删列造序</a>
 */
fun minDeletionSize(strs: Array<String>): Int {
    var ans = 0
    var n = strs.size
    for (i in 0 until strs[0].length) {
        for (j in 1 until n) {
            if (strs[j][i] < strs[j - 1][i]) {
                ans++
                break
            }
        }
    }
    return ans
}

