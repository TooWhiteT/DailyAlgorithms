/**
 * 情感丰富的文字
 *
 * 有时候人们会用重复写一些字母来表示额外的感受，比如 "hello" -> "heeellooo", "hi" -> "hiii"。我们将相邻字母都相同的一串字符定义为相同字母组，例如："h", "eee", "ll", "ooo"。
 * 对于一个给定的字符串 S ，如果另一个单词能够通过将一些字母组扩张从而使其和 S 相同，我们将这个单词定义为可扩张的（stretchy）。扩张操作定义如下：选择一个字母组（包含字母c），然后往其中添加相同的字母c使其长度达到 3 或以上。
 * 例如，以"hello" 为例，我们可以对字母组"o" 扩张得到 "hellooo"，但是无法以同样的方法得到 "helloo" 因为字母组 "oo" 长度小于3。此外，我们可以进行另一种扩张 "ll" -> "lllll" 以获得"helllllooo"。如果S = "helllllooo"，那么查询词"hello" 是可扩张的，因为可以对它执行这两种扩张操作使得query = "hello" -> "hellooo" -> "helllllooo" = S。
 * 输入一组查询单词，输出其中可扩张的单词数量。
 *
 * <a href="https://leetcode.cn/problems/expressive-words">情感丰富的文字</a>
 */
fun expressiveWords(s: String, words: Array<String>): Int {
    var res = 0
    for (word in words) {
        if (check(s, word)) res++
    }
    return res
}

fun check(s: String, word: String): Boolean {
    var i = 0
    var j = 0
    while (i < s.length && j < word.length) {
        if (s[i] != word[j]) return false
        val c = s[i]
        var cnts = 0
        while (i < s.length && s[i] == c) {
            i++
            cnts++
        }
        var cntw = 0
        while (j < word.length && word[j] == c) {
            j++
            cntw++
        }
        if (cnts < cntw) return false
        if (cnts > cntw && cnts < 3) return false
    }
    return i == s.length && j == word.length
}

/**
 * 图像渲染
 *
 * 有一幅以m x n的二维整数数组表示的图画image，其中image[i][j]表示该图画的像素值大小。
 *
 * 你也被给予三个整数 sr , sc 和 newColor 。你应该从像素image[sr][sc] 开始对图像进行 上色填充 。
 *
 * 为了完成 上色工作 ，从初始像素开始，记录初始坐标的 上下左右四个方向上 像素值与初始坐标相同的相连像素点，接着再记录这四个方向上符合条件的像素点与他们对应 四个方向上 像素值与初始坐标相同的相连像素点，……，重复该过程。将所有有记录的像素点的颜色值改为 newColor。
 *
 * 最后返回 经过上色渲染后的图像。
 *
 * @see <a href="https://leetcode.cn/problems/flood-fill">图像渲染</a>
 */
fun floodFill(image: Array<IntArray>, sr: Int, sc: Int, color: Int): Array<IntArray> {
    return dfs(image, sr, sc, color, image[sr][sc])
}

fun dfs(image: Array<IntArray>, i: Int, j: Int, newColor: Int, num: Int): Array<IntArray> {
    if (!(i < 0 || i >= image.size || j < 0 || j >= image[0].size || image[i][j] == newColor || image[i][j] != num)) {
        val temp = image[i][j]
        image[i][j] = newColor
        dfs(image, i + 1, j, newColor, temp)
        dfs(image, i - 1, j, newColor, temp)
        dfs(image, i, j + 1, newColor, temp)
        dfs(image, i, j - 1, newColor, temp)
    }
    return image
}

/**
 * 寻找比目标字母大的最小字母
 *
 * 给你一个排序后的字符列表 letters ，列表中只包含小写英文字母。另给出一个目标字母target，请你寻找在这一有序列表里比目标字母大的最小字母。
 *
 * 在比较时，字母是依序循环出现的。举个例子：
 *
 * 如果目标字母 target = 'z' 并且字符列表为 letters = ['a', 'b']，则答案返回'a'
 *
 * @see <a href="https://leetcode.cn/problems/find-smallest-letter-greater-than-target">寻找比目标字母大的最小字母</a>
 */
fun nextGreatestLetter(letters: CharArray, target: Char): Char {
    val len = letters.size
    var l = 0
    var r = len - 1
    while (l <= r) {
        val mid = l + (r - l) / 2
        if (target < letters[mid]) {
            r = mid - 1
        } else {
            l = mid + 1
        }
    }
    return letters[l % len]
}

/**
 * 使用最小花费爬楼梯
 *
 * 给你一个整数数组 cost ，其中 cost[i] 是从楼梯第 i 个台阶向上爬需要支付的费用。一旦你支付此费用，即可选择向上爬一个或者两个台阶。
 * 你可以选择从下标为 0 或下标为 1 的台阶开始爬楼梯。
 * 请你计算并返回达到楼梯顶部的最低花费。
 *
 * @see <a href="https://leetcode.cn/problems/min-cost-climbing-stairs">使用最小花费爬楼梯</a>
 */
fun minCostClimbingStairs(cost: IntArray): Int {
    var dp0 = cost[0]
    var dp1 = cost[1]
    for (i in 2 until cost.size) {
        val dpi = Math.min(dp0, dp1) + cost[i]
        dp0 = dp1
        dp1 = dpi
    }
    return Math.min(dp0, dp1)
}

/**
 * 至少是其他数字两倍的最大数
 *
 * 给你一个整数数组 nums ，其中总是存在 唯一的 一个最大整数 。
 * 请你找出数组中的最大元素并检查它是否 至少是数组中每个其他数字的两倍 。如果是，则返回 最大元素的下标 ，否则返回 -1 。
 *
 * @see <a href="https://leetcode.cn/problems/largest-number-at-least-twice-of-others">至少是其他数字两倍的最大数</a>
 */
fun dominantIndex(nums: IntArray): Int {
    var max = 0
    var idx = 0
    var less = 1
    for (i in 0 until nums.size) {
        if (nums[i] > max) {
            less = max
            max = nums[i]
            idx = i
        } else if (nums[i] > less) {
            less = nums[i]
        }
    }
    return if (max >= (less * 2)) idx else -1
}