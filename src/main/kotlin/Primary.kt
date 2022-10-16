/**
 * 删除排序数组中的重复项
 *
 * 给你一个 升序排列 的数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。元素的 相对顺序 应该保持 一致 。
 * 由于在某些语言中不能改变数组的长度，所以必须将结果放在数组nums的第一部分。更规范地说，如果在删除重复项之后有 k 个元素，那么 nums 的前 k 个元素应该保存最终结果。
 * 将最终结果插入 nums 的前 k 个位置后返回 k 。
 * 不要使用额外的空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 * @see <a href="https://leetcode.cn/leetbook/read/top-interview-questions-easy/x2gy9m/">删除排序数组中的重复项</a>
 */
fun removeDuplicates(nums: IntArray): Int {
    var left = 0
    if (nums.size <= 1) return nums.size
    for (right in 1 until nums.size) {
        if (nums[right] != nums[left]) {
            nums[++left] = nums[right]
        }
    }
    return left + 1
}

/**
 * 买卖股票的最佳时机 II
 *
 * 给你一个整数数组 prices ，其中 `prices[i]` 表示某支股票第 i 天的价格。
 * 在每一天，你可以决定是否购买和/或出售股票。你在任何时候 `最多` 只能持有 `一股` 股票。你也可以先购买，然后在 同一天 出售。
 * 返回 你能获得的 `最大` 利润。
 * @see <a href="https://leetcode.cn/leetbook/read/top-interview-questions-easy/x2zsx1/">买卖股票的最佳时机 II</a>
 */
fun maxProfit(prices: IntArray): Int {
    if (prices.size < 2) return 0
    var profit = 0
    for (i in 1 until prices.size) { // 贪心算法
        val temp = prices[i] - prices[i-1]
        profit += Math.max(temp, 0)
    }
    return profit
}

/**
 * 旋转数组
 *
 * 给你一个数组，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。
 * @see <a href="https://leetcode.cn/leetbook/read/top-interview-questions-easy/x2skh7/">旋转数组</a>
 */
fun rotate(nums: IntArray, k: Int) {
    val temp = IntArray(nums.size)
    for (i in 0 until nums.size) {
        temp[i] = nums[i]
    }
    for (j in 0 until temp.size) {
        // (j + k) % nums.size 偏移后的下标
        nums[(j + k) % nums.size] = temp[j]
    }
}

// 空间复杂度O(1)的解法 翻转数组
fun rotate2(nums: IntArray, k: Int) {
    if (nums.size < 2 || k <= 0) return
    nums.reverse()
    nums.reverse(0, k)
    nums.reverse(k, nums.size)
}

/**
 * 存在重复元素
 *
 * 给你一个整数数组 nums 。如果任一值在数组中出现 至少两次 ，返回 true ；如果数组中每个元素互不相同，返回 false 。
 *
 * @see <a href="https://leetcode.cn/leetbook/read/top-interview-questions-easy/x248f5/">存在重复元素</a>
 */
fun containsDuplicate(nums: IntArray): Boolean {
    val temp = HashSet<Int>()
    for (i in nums) {
        if (!temp.add(i)) {
            return true
        }
    }
    return false
}

/**
 * 只出现一次的数字
 *
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 *
 * @see <a href="https://leetcode.cn/leetbook/read/top-interview-questions-easy/x21ib6/">只出现一次的数字</a>
 */
fun singleNumber(nums: IntArray): Int {
    val temp = HashSet<Int>()
    for (i in nums) {
        if (!temp.add(i)) { // Set特性 无法添加重复值
            temp.remove(i)
        }
    }
    return temp.first()
}

/**
 * 使用位运算符(异或)
 *
 * 使用异或运算，将所有值进行异或
 *
 * 异或运算，相异为真，相同为假，所以 a^a = 0 ; 0^a = a 相同为0 不同返回原值
 *
 * 因为异或运算 满足交换律 a^b^a = a^a^b = b 所以数组经过异或运算，单独的值就剩下了
 */
fun singleNumber2(nums: IntArray): Int {
    var temp: Int = 0;
    for (i in nums) {
        temp = temp xor i
    }
    return temp
}

/**
 * 两个数组的交集 II
 *
 * 给你两个整数数组 nums1 和 nums2 ，请你以数组形式返回两数组的交集。返回结果中每个元素出现的次数，应与元素在两个数组中都出现的次数一致（如果出现次数不一致，则考虑取较小值）。可以不考虑输出结果的顺序。
 * @see <a href="https://leetcode.cn/leetbook/read/top-interview-questions-easy/x2y0c2/">两个数组的交集 II</a>
 */
fun intersect(nums1: IntArray, nums2: IntArray): IntArray {
    nums1.sort()
    nums2.sort()
    var left = 0 // 左边数组下标
    var right = 0 // 右边数组下标
    val list = mutableListOf<Int>()
    while (left < nums1.size && right < nums2.size) {
        // 进行比较 小的下标向后移一位，相等就是交集元素
        if (nums1[left] < nums2[right]) {
            left++
        } else if (nums1[left] > nums2[right]) {
            right++
        } else {
            list.add(nums1[left])
            // 相等 下标同时往后移一位
            left++
            right++
        }
    }
    return list.toIntArray()
}

/**
 * 加一
 *
 * 给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。
 * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 *
 * @see <a href="https://leetcode.cn/leetbook/read/top-interview-questions-easy/x2cv1c/">加一</a>
 */
fun plusOne(digits: IntArray): IntArray {
    // 看完下面的扩展思路 回头看这个就是耻辱
    var i = digits.size - 1
    while (i in digits.size - 1 downTo 0) {
        if (digits[i] + 1 == 10) {
            digits[i] = 0
            if (i == 0) {
                var src = IntArray(digits.size +1)
                System.arraycopy(src, 1, digits,0, digits.size)
                src[0] = 1
                return src
            }
            i--
            continue
        } else {
            digits[i] += 1
            i = -1 // 结束
        }
    }
    return digits
}

// 扩展思路
fun plusOne2(digits: IntArray): IntArray {
    for (i in digits.size - 1 downTo 0) {
        if (digits[i] != 9) {
            digits[i] += 1
            return digits
        }
        digits[i] = 0
    }
    val res = IntArray(digits.size + 1)
    res[0] = 1
    return res
}

/**
 * 移动零
 *
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * 请注意，必须在不复制数组的情况下原地对数组进行操作。
 *
 * @see <a href="https://leetcode.cn/leetbook/read/top-interview-questions-easy/x2ba4i/">移动零</a>
 */
fun moveZeroes(nums: IntArray) { // [0,1,0,3,12]
    var index = 0
    for (i in 0 until nums.size) {
        if (nums[i] != 0) {
            val temp = nums[index]
            nums[index] = nums[i]
            nums[i] = temp
            index++
        }
    }
}

/**
 * 两数之和
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target 的那两个整数，并返回它们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 * 你可以按任意顺序返回答案。
 *
 * @see <a href="https://leetcode.cn/leetbook/read/top-interview-questions-easy/x2jrse/">两数之和</a>
 */
fun twoSum(nums: IntArray, target: Int): IntArray {
    val map = HashMap<Int, Int>() // 数字 to 下标
    map[nums[0]] = 0
    for (i in 1 until nums.size) {
        val key = target - nums[i]
        if (map.containsKey(key)) {
            return intArrayOf(map[key]!!, i)
        } else {
            map[nums[i]] = i
        }
    }
    return intArrayOf()
}

/**
 * 有效的数独
 *
 * 请你判断一个9 x 9 的数独是否有效。只需要 根据以下规则 ，验证已经填入的数字是否有效即可。
 * 数字1-9在每一行只能出现一次。
 * 数字1-9在每一列只能出现一次。
 * 数字1-9在每一个以粗实线分隔的3x3宫内只能出现一次。
 *
 * @see <a href="https://leetcode.cn/leetbook/read/top-interview-questions-easy/x2f9gg/">有效的数独</a>
 */
fun isValidSudoku(board: Array<CharArray>): Boolean {
    val line = IntArray(9)
    val column = IntArray(9)
    val cell = IntArray(9)
    var shift = 0
    for (i in board.indices) {
        for (j in board[i].indices) {
            if (board[i][j] == '.') continue
            shift = 1 shl (board[i][j] - '0')
            val k = (i /3) * 3 + j / 3
            if ((column[i] and shift) > 0
                || (line[j] and shift) > 0
                || (cell[k] and shift) > 0) return false

            column[i] = column[i] or shift
            line[j] = line[j] or shift
            cell[k] = cell[k] or shift
        }
    }
    return true
}

/**
 * 旋转图像
 *
 * 给定一个 n×n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
 * 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
 *
 * @see <a href="https://leetcode.cn/leetbook/read/top-interview-questions-easy/xnhhkv/">旋转图像</a>
 */
fun rotate(matrix: Array<IntArray>) { // 直接交换
    for (i in 0 until (matrix.size / 2)) { // 不同size需要旋转的层数
        for (j in i until (matrix.size - i - 1)) {
            val temp = matrix[i][j]
            val m = matrix.size - j - 1
            val n = matrix.size - i - 1
            matrix[i][j] = matrix[m][i]
            matrix[m][i] = matrix[n][m]
            matrix[n][m] = matrix[j][n]
            matrix[j][n] = temp
        }
    }
}

/**
 * 反转字符串
 *
 * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 s 的形式给出。
 * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
 * @see <a href="https://leetcode.cn/leetbook/read/top-interview-questions-easy/xnhbqj/">反转字符串</a>
 */
fun reverseString(s: CharArray) {
    if (s.size == 1) {
        return
    }
    for (i in 0 until (s.size / 2)) {
        val temp = s[i]
        s[i] = s[s.size - 1 - i]
        s[s.size - 1 - i] = temp
    }
}

// 双指针法
fun reverseString2(s: CharArray) {
    var left = 0 // 左边指针
    var right = s.size - 1 // 右边指针
    while (left < right) {
        val temp = s[left]
        s[left] = s[right]
        s[right] = temp
        left++
        right--
    }
}

/**
 * 整数反转
 *
 * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
 * 如果反转后整数超过 32 位的有符号整数的范围[−2^31, 2^31− 1] ，就返回 0。
 * 假设环境不允许存储 64 位整数（有符号或无符号）。
 *
 * @see <a href=“https://leetcode.cn/leetbook/read/top-interview-questions-easy/xnx13t/”>整数反转</a>
 */
fun reverse(x: Int): Int {
    var res: Long = 0
    var temp = x
    while (temp != 0) {
        res = res * 10 + x % 10
        temp /= 10
    }
    return if (res in Int.MIN_VALUE..Int.MAX_VALUE) res.toInt() else 0
}