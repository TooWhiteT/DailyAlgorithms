import java.util.*


/**
 * 二进制数转字符串
 *
 * 二进制数转字符串。给定一个介于0和1之间的实数（如0.72），类型为double，打印它的二进制表达式。如果该数字无法精确地用32位以内的二进制表示，则打印“ERROR”。
 *
 * @see <a href="https://leetcode.cn/problems/bianry-number-to-string-lcci">二进制数转字符串</a>
 */
fun printBin(num: Double): String {
    val sb = StringBuffer("0.")
    var i = 0
    var temp = num
    while (temp != 0.0 && i < 6){
        temp =  temp * 2
        if (temp >= 1.0){
            //取小数部分
            temp = temp - 1
            sb.append("1")
        }else {
            sb.append("0")
        }
        i++
    }
    //循环结束后num不为0 ，直接返回ERROR
    if (temp != 0.0){
        return "ERROR"
    }
    return sb.toString()
}

/**
 * 统计有序矩阵中的负数
 *
 * 给你一个 m * n 的矩阵 grid，矩阵中的元素无论是按行还是按列，都以非递增顺序排列。 请你统计并返回 grid 中 负数 的数目。
 *
 * @see <a href="https://leetcode.cn/problems/count-negative-numbers-in-a-sorted-matrix/">统计有序矩阵中的负数</a>
 */
fun countNegatives(grid: Array<IntArray>): Int {
    val row: Int = grid.size
    val col: Int = grid[0].size
    var res = 0
    for (i in 0 until row) {
        for (j in 0 until col) {
            if (grid[i][j] < 0) {
                res++
            }
        }
    }
    return res
}

/**
 * 根据数字二进制下 1 的数目排序
 *
 * 给你一个整数数组arr。请你将数组中的元素按照其二进制表示中数字 1 的数目升序排序。
 * 如果存在多个数字二进制中1的数目相同，则必须将它们按照数值大小升序排列。
 * 请你返回排序后的数组。
 *
 * @see <a href="https://leetcode.cn/problems/sort-integers-by-the-number-of-1-bits">根据数字二进制下 1 的数目排序</a>
 */
fun sortByBits(arr: IntArray): IntArray {
    val nums = arrayOfNulls<Int>(arr.size)
    for (i in 0 until arr.size) {
        nums[i] = arr[i]
    }
    Arrays.sort(nums) { o1: Int?, o2: Int? ->
        val bitCountA = Integer.bitCount(o1!!)
        val bitCountB = Integer.bitCount(o2!!)
        if (bitCountA == bitCountB) {
            o1!! - o2!!
        } else {
            bitCountA - bitCountB
        }
    }
    for (i in 0 until arr.size) {
        arr[i] = nums[i]!!
    }
    return arr
}

/**
 * 日期之间隔几天
 *
 * 请你编写一个程序来计算两个日期之间隔了多少天。
 * 日期以字符串形式给出，格式为 YYYY-MM-DD，如示例所示。
 *
 * @see <a href="https://leetcode.cn/problems/number-of-days-between-two-dates/">日期之间隔几天</a>
 */
fun daysBetweenDates(date1: String, date2: String): Int { // 自顶向下“金字塔”式编程
    return Math.abs(dateToDay(date1) - dateToDay(date2))
}
fun dateToDay(date: String): Int {
    val split = date.split("-".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
    var y = Integer.valueOf(split[0])
    var m = Integer.valueOf(split[1])
    val d = Integer.valueOf(split[2])
    if (m <= 2) {
        m += 10
        y--
    } else {
        m -= 2
    }
    return 365 * y + y / 4 - y / 100 + y / 400 + 30 * m + (3 * m - 1) / 5 + d
}

/**
 * 有多少小于当前数字的数字
 *
 * 给你一个数组nums，对于其中每个元素nums[i]，请你统计数组中比它小的所有数字的数目。
 * 换而言之，对于每个nums[i]你必须计算出有效的j的数量，其中 j 满足j != i 且 nums[j] < nums[i]。
 * 以数组形式返回答案。
 *
 * see <a href="https://leetcode.cn/problems/how-many-numbers-are-smaller-than-the-current-number">有多少小于当前数字的数字</a>
 */
fun smallerNumbersThanCurrent(nums: IntArray): IntArray {
    val map = HashMap<Int, Int>()
    val res = Arrays.copyOf(nums, nums.size)
    Arrays.sort(res)
    for (i in 0 until res.size) {
        if (!map.containsKey(res[i])) map[res[i]] = i
    }
    for (i in 0 until nums.size) {
        res[i] = map[nums[i]]!!
    }
    return res
}