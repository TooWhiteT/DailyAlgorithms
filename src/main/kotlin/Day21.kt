/**
 * 香槟塔
 *
 * 我们把玻璃杯摆成金字塔的形状，其中第一层有 1 个玻璃杯， 第二层有 2 个，依次类推到第 100 层，每个玻璃杯 (250ml) 将盛有香槟。
 * 从顶层的第一个玻璃杯开始倾倒一些香槟，当顶层的杯子满了，任何溢出的香槟都会立刻等流量的流向左右两侧的玻璃杯。当左右两边的杯子也满了，就会等流量的流向它们左右两边的杯子，依次类推。（当最底层的玻璃杯满了，香槟会流到地板上）
 * 例如，在倾倒一杯香槟后，最顶层的玻璃杯满了。倾倒了两杯香槟后，第二层的两个玻璃杯各自盛放一半的香槟。在倒三杯香槟后，第二层的香槟满了 - 此时总共有三个满的玻璃杯。在倒第四杯后，第三层中间的玻璃杯盛放了一半的香槟，他两边的玻璃杯各自盛放了四分之一的香槟
 *
 * @see <a href="https://leetcode.cn/problems/champagne-tower">香槟塔</a>
 */
fun champagneTower(poured: Int, query_row: Int, query_glass: Int): Double {
    var count = doubleArrayOf(poured.toDouble())
    for (i in 1..query_row) {
        val arr = DoubleArray(i + 1)
        arr[0] = Math.max(0.0, count[0] - 1) / 2
        arr[i] = Math.max(0.0, count[i - 1] - 1) / 2
        for (j in 1 until i) {
            arr[j] = (Math.max(0.0, count[j - 1] - 1) + Math.max(0.0, count[j] - 1)) / 2
        }
        count = arr
    }
    return Math.min(1.0, count[query_glass])
}

/**
 * 子数组最大平均数 I
 *
 * 给你一个由 n 个元素组成的整数数组 nums 和一个整数 k 。
 * 请你找出平均数最大且 长度为 k 的连续子数组，并输出该最大平均数。
 * 任何误差小于 10-5 的答案都将被视为正确答案。
 *
 * @see <a href="https://leetcode.cn/problems/maximum-average-subarray-i">子数组最大平均数 I</a>
 */
fun findMaxAverage(nums: IntArray, k: Int): Double {
    var sumMax = 0
    for (i in 0 until k) {
        sumMax += nums[i]
    }
    var sum = sumMax
    for (i in 1..nums.size - k) {
        sum -= nums[i - 1]
        sum += nums[i + k - 1]
        sumMax = Math.max(sum, sumMax)
    }
    return sumMax.toDouble() / k
}

/**
 * 错误的集合
 *
 * 集合 s 包含从 1 到 n 的整数。不幸的是，因为数据错误，导致集合里面某一个数字复制了成了集合里面的另外一个数字的值，导致集合 丢失了一个数字 并且 有一个数字重复 。
 * 给定一个数组 nums 代表了集合 S 发生错误后的结果。
 * 请你找出重复出现的整数，再找到丢失的整数，将它们以数组的形式返回。
 *
 * @see <a href="https://leetcode.cn/problems/set-mismatch"></a>
 */
fun findErrorNums(nums: IntArray): IntArray {
    val resule = IntArray(2)
    val temp = IntArray(nums.size + 1)
    for (num in nums) {
        temp[num]++
    }
    for (i in 1 until temp.size) {
        if (temp[i] == 1) continue
        if (temp[i] == 2) resule[0] = i else resule[1] = i
    }
    return resule
}

/**
 * 两数之和 IV - 输入二叉搜索树
 *
 * 给定一个二叉搜索树 root 和一个目标结果 k，如果二叉搜索树中存在两个元素且它们的和等于给定的目标结果，则返回 true。
 *
 * @see <a href="https://leetcode.cn/problems/two-sum-iv-input-is-a-bst/">两数之和 IV - 输入二叉搜索树</a>
 */
val set = HashSet<Int>()
fun findTarget(root: TreeNode?, k: Int): Boolean {
    if (set.contains(k - root!!.`val`)) return true else set.add(root.`val`)
    if (root.left != null && findTarget(root.left, k)) return true
    return if (root.right != null && findTarget(root.right, k)) true else false
}

/**
 * 机器人能否返回原点
 *
 * 在二维平面上，有一个机器人从原点 (0, 0) 开始。给出它的移动顺序，判断这个机器人在完成移动后是否在(0, 0) 处结束。
 * 移动顺序由字符串moves表示。字符 move[i] 表示其第 i 次移动。机器人的有效动作有 R（右），L（左），U（上）和 D（下）。
 * 如果机器人在完成所有动作后返回原点，则返回 true。否则，返回 false。
 * 注意：机器人“面朝”的方向无关紧要。 “R” 将始终使机器人向右移动一次，“L” 将始终向左移动等。此外，假设每次移动机器人的移动幅度相同。
 *
 * @see <a href="https://leetcode.cn/problems/robot-return-to-origin">机器人能否返回原点</a>
 */
fun judgeCircle(moves: String): Boolean {
    val cnt = IntArray(26)
    for (c in moves.toCharArray()) cnt[c.hashCode() - 'A'.hashCode() ]++
    return cnt['L'.hashCode()  - 'A'.hashCode() ] == cnt['R'.hashCode()  - 'A'.hashCode() ] && cnt['U'.hashCode()  - 'A'.hashCode() ] == cnt['D'.hashCode()  - 'A'.hashCode() ]
}
