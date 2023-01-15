import java.util.*


/**
 * 极大极小游戏
 *
 * 给你一个下标从 0 开始的整数数组 nums ，其长度是 2 的幂。
 *
 * 对 nums 执行下述算法：
 *
 *  设 n 等于 nums 的长度，如果 n == 1 ，终止 算法过程。否则，创建 一个新的整数数组 newNums ，新数组长度为 n / 2 ，下标从 0 开始。
 *  对于满足 0 <= i < n / 2 的每个 偶数 下标 i ，将 newNums[i] 赋值 为 min(nums[2 * i], nums[2 * i + 1]) 。
 *  对于满足 0 <= i < n / 2 的每个 奇数 下标 i ，将 newNums[i] 赋值 为 max(nums[2 * i], nums[2 * i + 1]) 。
 *  用 newNums 替换 nums 。
 *  从步骤 1 开始 重复 整个过程。
 *
 * 执行算法后，返回 nums 中剩下的那个数字。
 *
 * @see <a href="https://leetcode.cn/problems/min-max-game">极大极小游戏</a>
 */
fun minMaxGame(nums: IntArray): Int {
    if (nums.size == 1) return nums[0]
    val newNums = IntArray(nums.size / 2)
    for (i in 0 until (nums.size / 2)) {
        if ((i and 1) == 0) {
            newNums[i] = Math.min(nums[2 * i], nums[2 * i + 1])
        } else {
            newNums[i] = Math.max(nums[2 * i], nums[2 * i + 1])
        }
    }
    return minMaxGame(newNums)
}

/**
 * 删除回文子序列
 *
 * 给你一个字符串s，它仅由字母'a' 和 'b'组成。每一次删除操作都可以从 s 中删除一个回文 子序列。
 *
 * 返回删除给定字符串中所有字符（字符串为空）的最小删除次数。
 *
 * 「子序列」定义：如果一个字符串可以通过删除原字符串某些字符而不改变原字符顺序得到，那么这个字符串就是原字符串的一个子序列。
 *
 * 「回文」定义：如果一个字符串向后和向前读是一致的，那么这个字符串就是一个回文。
 *
 * @see <a href="https://leetcode.cn/problems/remove-palindromic-subsequences">删除回文子序列</a>
 */
fun removePalindromeSub(s: String): Int {
    var i = 0
    var j = s.length - 1
    while (i < j && s[i] == s[j]) {
        i++
        j--
    }
    return if (i < j) 2 else 1
}

/**
 * 矩阵中战斗力最弱的 K 行
 *
 * 给你一个大小为m* n的矩阵mat，矩阵由若干军人和平民组成，分别用 1 和 0 表示。
 *
 * 请你返回矩阵中战斗力最弱的k行的索引，按从最弱到最强排序。
 *
 * 如果第i行的军人数量少于第j行，或者两行军人数量相同但 i 小于 j，那么我们认为第 i 行的战斗力比第 j 行弱。
 *
 * 军人 总是 排在一行中的靠前位置，也就是说 1 总是出现在 0 之前。
 *
 * @see <a href="https://leetcode.cn/problems/the-k-weakest-rows-in-a-matrix">矩阵中战斗力最弱的 K 行</a>
 */
fun kWeakestRows(mat: Array<IntArray>, k: Int): IntArray {
    val n = mat.size
    val arr = IntArray(n)
    val ids = IntArray(n)
    for (i in 0 until n) {
        val row = mat[i]
        // 二分查找，找到第一个0
        var l = 0
        var r = row.size - 1
        while (l <= r) {
            var m = (l + r) / 2
            if (row[m] == 0) r = --m else l = ++m
        }
        arr[i] = l
        ids[i] = i
    }

    //排序，需要一个稳定排序算法，姑且写个冒泡排序得了
    for (i in 0 until n) {
        for (j in 0 until n - 1 - i) {
            if (arr[j] > arr[j + 1]) {
                var temp = arr[j + 1]
                arr[j + 1] = arr[j]
                arr[j] = temp
                temp = ids[j + 1]
                ids[j + 1] = ids[j]
                ids[j] = temp
            }
        }
    }

    return Arrays.copyOfRange(ids, 0, k)
}

/**
 * 将数字变成 0 的操作次数
 *
 * 给你一个非负整数 num ，请你返回将它变成 0 所需要的步数。 如果当前数字是偶数，你需要把它除以 2 ；否则，减去 1 。
 *
 * @see <a href="https://leetcode.cn/problems/number-of-steps-to-reduce-a-number-to-zero/submissions/">将数字变成 0 的操作次数</a>
 */
var step = 0
fun numberOfSteps(num: Int): Int {
    if (num == 0) {
        return step
    }
    step++
    if (num % 2 == 0) {
        return numberOfSteps(num / 2)
    } else {
        return numberOfSteps(num - 1)
    }
}

/**
 * 检查整数及其两倍数是否存在
 * 
 * 给你一个整数数组arr，请你检查是否存在两个整数N 和 M，满足N是M的两倍（即，N = 2 * M）。
 *
 * 更正式地，检查是否存在两个下标i 和 j 满足：
 *
 *      i != j
 *      0 <= i, j < arr.length
 *      arr[i] == 2 * arr[j]
 * 
 * @see <a href="https://leetcode.cn/problems/check-if-n-and-its-double-exist/">检查整数及其两倍数是否存在</a>
 */
fun checkIfExist(arr: IntArray): Boolean {
    val set: MutableSet<Int> = HashSet()
    for (i in arr) {
        if (set.contains(i)) {
            return true
        }
        if (i % 2 == 0) {
            set.add(i / 2)
        }
        set.add(i * 2)
    }
    return false
}
