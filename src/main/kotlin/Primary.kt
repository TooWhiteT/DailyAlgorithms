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