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
    return 0
}