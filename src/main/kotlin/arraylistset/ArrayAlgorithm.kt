package arraylistset

import java.util.*
import kotlin.Comparator

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

/**
 * 合并区间
 *
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals`[i]` = [starti, endi] 。
 * 请你合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。
 *
 * @see <a href="https://leetcode.cn/leetbook/read/array-and-string/c5tv3/">合并区间</a>
 */
fun merge(intervals: Array<IntArray>): Array<IntArray> {
    if (intervals.size == 0) return intervals
    Arrays.sort(intervals, object : Comparator<IntArray> {
        override fun compare(o1: IntArray?, o2: IntArray?): Int {
            return if (o1 == o2) (o1?.get(1)!! - o2?.get(1)!!) else (o1?.get(0)!! - o2?.get(0)!!)
        }
    }) // 按每行的第0列升序排序

    val intVector = Vector<IntArray>() // 由于我们事先不知道数组大小，所以用Vector类实现动态数组
    var temp = intervals[0] // 定义一个Int类型数组用于作比较，默认值为第一组二维数组的值
    for (i in 1 until intervals.size) {
        if (temp[1] >= intervals[i][0]) { // 如果第一个数组的右端点大于等于下一个数组的左端点，做说明两个数组有所交集
            temp[1] = Math.max(temp[1], intervals[i][1]) // int类型数组的右端点等于两个数组中右端点大的那个值
        } else {
            intVector.add(temp) // 把int类型一维数组ints添加到我们创建的vector类里面
            temp = intervals[i] // 给一维数组重新赋值
        }
    }
    intVector.add(temp) // 把最后一个区间添加到Vector里面
    return intVector.toArray(Array<IntArray>(intVector.size){ IntArray(2) }) // 把vector转换成二维数组返回
}

/**
 * 旋转矩阵
 *
 * 给你一幅由 N × N 矩阵表示的图像，其中每个像素的大小为 4 字节。请你设计一种算法，将图像旋转 90 度。
 * 不占用额外内存空间能否做到？
 *
 * @see <a href="https://leetcode.cn/leetbook/read/array-and-string/clpgd/">旋转矩阵</a>
 */
fun rotate(matrix: Array<IntArray>): Unit {
    for (i in 0 until matrix.size/2) {
        for (j in i until matrix.size - (i+1)) {
            // 相邻交换一次
            matrix[i][j] = matrix[i][j] xor matrix[j][matrix.size-1-i]
            matrix[j][matrix.size-1-i] = matrix[i][j] xor matrix[j][matrix.size-1-i]
            matrix[i][j] = matrix[i][j] xor matrix[j][matrix.size-1-i]
            // 对角交换一次
            matrix[i][j] = matrix[i][j] xor matrix[matrix.size-1-i][matrix.size-1-j]
            matrix[matrix.size-1-i][matrix.size-1-j] = matrix[i][j] xor matrix[matrix.size-1-i][matrix.size-1-j]
            matrix[i][j] = matrix[i][j] xor matrix[matrix.size-1-i][matrix.size-1-j]
            // 相邻交换一次
            matrix[i][j] = matrix[i][j] xor matrix[matrix.size-1-j][i]
            matrix[matrix.size-1-j][i] = matrix[i][j] xor matrix[matrix.size-1-j][i]
            matrix[i][j] = matrix[i][j] xor matrix[matrix.size-1-j][i]
        }
    }
}

/**
 * 零矩阵
 *
 * 编写一种算法，若M × N矩阵中某个元素为0，则将其所在的行与列清零。
 *
 * @see <a href="https://leetcode.cn/leetbook/read/array-and-string/ciekh/">零矩阵</a>
 */
fun setZeroes(matrix: Array<IntArray>) {
    val row = BooleanArray(matrix.size)
    val column = BooleanArray(matrix[0].size)

    // 标记是否有0
    for (i in 0 until matrix.size) {
        for (j in 0 until matrix[0].size) {
            if (matrix[i][j] == 0) {
                row[i] = true
                column[j] = true
            }
        }
    }
    // 将标记置为0
    for (i in 0 until matrix.size) {
        for (j in 0 until matrix[0].size) {
            if (row[i] || column[j]) {
                matrix[i][j] = 0
            }
        }
    }
}

/**
 * 对角线遍历
 *
 * 给你一个大小为 m x n 的矩阵 mat ，请以对角线遍历的顺序，用一个数组返回这个矩阵中的所有元素。
 *
 * @see <a href="https://leetcode.cn/leetbook/read/array-and-string/cuxq3/">对角线遍历</a>
 */
fun findDiagonalOrder(mat: Array<IntArray>): IntArray { // 太难了
    if (mat.size == 0) {
        return intArrayOf(0)
    }
    val m = mat.size
    val n = mat[0].size
    //存放数组
    val ans = IntArray(m * n)
    //对角线方向次数
    var count = n + m - 1
    //定义初始化 行标记，列标记，存放数组索引
    var row = 0
    var col = 0
    var index = 0
    //开始对角线循环
    for (i in 0 until count) {
        // 判断对角线方向（因题目初始从右上（即i=0）开始）：偶数右上，奇数左下
        if (i % 2 == 0) {
            // 右上操作
            while (row >= 0 && col < n) {
                // 将矩阵数存入存放数组
                ans[index] = mat[row][col]
                // 索引后移
                index++
                // 右上规律：行减一，列加一
                row--
                col++
            }
            // 判断是否为越界情况：不越界正常行加一，越界行加二，列减一
            //（此处不理解的拿张草稿纸将循环中row和col的值遍历写一下对照矩阵图就明白了)
            if (col < n) {
                row++
            } else {
                row += 2
                col--
            }
        } else { //左下操作：按规律与右上相反即可
            while (row < m && col >= 0) {
                ans[index] = mat[row][col];
                index++
                row++
                col--
            }
            if (row < m) {
                col++

            } else {
                row--
                col += 2
            }
        }
    }
    // 返回存放数组
    return ans
}