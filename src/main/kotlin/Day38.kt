import java.util.*


/**
 * 通过最少操作次数使数组的和相等
 *
 * 给你两个长度可能不等的整数数组 nums1 和 nums2 。两个数组中的所有值都在 1 到 6 之间（包含 1 和 6）。
 * 每次操作中，你可以选择 任意 数组中的任意一个整数，将它变成 1 到 6 之间 任意 的值（包含 1 和 6）。
 * 请你返回使 nums1 中所有数的和与 nums2 中所有数的和相等的最少操作次数。如果无法使两个数组的和相等，请返回 -1 。
 *
 * @see <a href="https://leetcode.cn/problems/equal-sum-arrays-with-minimum-number-of-operations">通过最少操作次数使数组的和相等</a>
 */
fun minOperations(nums1: IntArray, nums2: IntArray): Int {
    val n = nums1.size
    val m = nums2.size
    if (n * 6 < m || n > m * 6) {
        return -1
    }
    val sum1 = nums1.sum()
    val sum2 = nums2.sum()
    if (sum1 == sum2) {
        return 0
    }
    val cnt = IntArray(7)
    if (sum1 > sum2) {
        for (t in nums1) {
            cnt[t - 1]++
        }
        for (t in nums2) {
            cnt[6 - t]++
        }
    } else {
        for (t in nums1) {
            cnt[6 - t]++
        }
        for (t in nums2) {
            cnt[t - 1]++
        }
    }
    var dif = Math.abs(sum1 - sum2)
    var ans = 0
    for (i in 6 downTo 1) {
        if (cnt[i] * i >= dif) {
            ans += dif / i
            dif -= dif / i * i
            break
        } else {
            ans += cnt[i]
            dif -= cnt[i] * i
        }
    }
    ans += if (dif == 0) 0 else 1
    return ans
}

/**
 * 有序数组的平方
 *
 * 给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。
 *
 * @see <a href="https://leetcode.cn/problems/squares-of-a-sorted-array/">有序数组的平方</a>
 */
fun sortedSquares(nums: IntArray): IntArray {
    var right = nums.size - 1
    var left = 0
    val res = IntArray(nums.size)
    var index = res.size - 1
    while (left <= right) {
        if (nums[left] * nums[left] > nums[right] * nums[right]) {
            res[index--] = nums[left] * nums[left]
            ++left
        } else {
            res[index--] = nums[right] * nums[right]
            --right
        }
    }
    return res
}

/**
 * 数组形式的整数加法
 *
 * 整数的 数组形式  num 是按照从左到右的顺序表示其数字的数组。
 *      例如，对于 num = 1321 ，数组形式是 [1,3,2,1] 。
 *
 * 给定 num ，整数的 数组形式 ，和整数 k ，返回 整数 num + k 的 数组形式 。
 *
 * @see <a href="https://leetcode.cn/problems/add-to-array-form-of-integer">数组形式的整数加法</a>
 */
fun addToArrayForm(num: IntArray, k: Int): List<Int> {
    val len = num.size
    var lastNum = k
    val ret = LinkedList<Int>()
    var i = len - 1
    while (i >= 0 || lastNum > 0) {
        if (i >= 0) {
            lastNum += num[i]
        }
        ret.addFirst(lastNum % 10)
        lastNum /= 10
        i--
    }
    return ret
}

/**
 * 二叉树的堂兄弟节点
 *
 * 在二叉树中，根节点位于深度 0 处，每个深度为 k 的节点的子节点位于深度 k+1 处。
 * 如果二叉树的两个节点深度相同，但 父节点不同 ，则它们是一对堂兄弟节点。
 * 我们给出了具有唯一值的二叉树的根节点 root ，以及树中两个不同节点的值 x 和 y 。
 * 只有与值 x 和 y 对应的节点是堂兄弟节点时，才返回 true 。否则，返回 false。
 *
 * @see <a href="https://leetcode.cn/problems/cousins-in-binary-tree/">二叉树的堂兄弟节点</a>
 */
fun isCousins(root: TreeNode?, x: Int, y: Int): Boolean {
    val deep = IntArray(102)
    val dad = IntArray(102)
    dfs(root!!.left, root, deep, dad, 1)
    dfs(root.right, root, deep, dad, 1)
    return deep[x] == deep[y] && dad[x] != dad[y]
}

fun dfs(node: TreeNode?, node2: TreeNode, deep: IntArray, parent: IntArray, index: Int) {
    if (node == null) return
    parent[node.`val`] = node2.`val`
    deep[node.`val`] = index
    dfs(node.left, node, deep, parent, index + 1)
    dfs(node.right, node, deep, parent, index + 1)
}

/**
 * 找到小镇的法官
 *
 * 小镇里有 n 个人，按从 1 到 n 的顺序编号。传言称，这些人中有一个暗地里是小镇法官。
 * 如果小镇法官真的存在，那么：
 *      小镇法官不会信任任何人。
 *      每个人（除了小镇法官）都信任这位小镇法官。
 *      只有一个人同时满足属性 1 和属性 2 。
 *
 * 给你一个数组 trust ，其中 trust[i] = [ai, bi] 表示编号为 ai 的人信任编号为 bi 的人。
 * 如果小镇法官存在并且可以确定他的身份，请返回该法官的编号；否则，返回 -1 。
 *
 * @see <a href="https://leetcode.cn/problems/find-the-town-judge">找到小镇的法官</a>
 */
fun findJudge(n: Int, trust: Array<IntArray>): Int {
    val people = Array(n) {
        IntArray(
            2
        )
    }
    for (person in trust) {
        val o = person[0]
        val i = person[1]
        people[o - 1][0]++
        people[i - 1][1]++
    }
    for (i in 0 until n) {
        if (people[i][0] == 0 && people[i][1] == n - 1) return i + 1
    }
    return -1
}