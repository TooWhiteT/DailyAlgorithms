package arraylistset

/**
 * 寻找数组的中心索引
 *
 * 给你一个整数数组 nums ，请计算数组的 中心下标 。
 * 数组 中心下标 是数组的一个下标，其左侧所有元素相加的和等于右侧所有元素相加的和。
 * 如果中心下标位于数组最左端，那么左侧数之和视为 0 ，因为在下标的左侧不存在元素。这一点对于中心下标位于数组最右端同样适用。
 * 如果数组有多个中心下标，应该返回 最靠近左边 的那一个。如果数组不存在中心下标，返回 -1 。
 *
 * @see <a href="https://leetcode.cn/leetbook/read/array-and-string/yf47s/">寻找数组的中心索引</a>
 */
fun pivotIndex(nums: IntArray): Int {
    var sum = 0
    // 求和
    for (i in nums) {
        sum += i
    }
    // 从左边开始减
    var left_temp = 0
    for (j in nums.indices) {
        sum -= nums[j]
        // 相减结果 与 左边相加 相等时 则返回下标
        if (sum == left_temp) {
            return j
        }
        left_temp += nums[j]
    }
    return -1
}

/**
 * 搜索插入位置
 *
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * 请必须使用时间复杂度为 O(log n) 的算法。
 *
 * @see <a href="https://leetcode.cn/leetbook/read/array-and-string/cxqdh/">搜索插入位置</a>
 */
fun searchInsert(nums: IntArray, target: Int): Int {
    var lo = 0 // 左边
    var hi = nums.size - 1 // 右边
    while (lo <= hi) {
        val mid = lo + (hi - lo) / 2 // 中间下标
        val minValue = nums[mid]
        if (minValue > target) { // 中位大于目标值
            hi = mid - 1 // 右边下标 移动到 中位下标 - 1
        } else if (minValue < target) { // 中位小于目标值
            lo = mid + 1 // 左边下标 移动到 中位下标 + 1
        } else {
            return mid // 如果找到就返回
        }
    }
    // 如果没找到就返回应该插入的位置
    return lo
}