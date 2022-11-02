/**
 * Excel表列名称
 *
 * 给你一个整数 columnNumber ，返回它在 Excel 表中相对应的列名称。
 *
 * @see <a href="https://leetcode.cn/problems/excel-sheet-column-title/">Excel表列名称</a>
 */
fun convertToTitle(columnNumber: Int): String { // ASCII码 A - Z  0 - 25
    var n = columnNumber
    if (n <= 0) return ""
    val sb = StringBuilder()
    while (n > 0) {
        n--
        sb.append(((n % 26) + 'A'.toInt()).toChar())
        n /= 26
    }
    return sb.reverse().toString()
}

/**
 * Excel 表列序号
 *
 * 给你一个字符串 columnTitle ，表示 Excel 表格中的列名称。返回 该列名称对应的列序号 。
 *
 * @see <a href="https://leetcode.cn/problems/excel-sheet-column-number/">Excel 表列序号</a>
 */
fun titleToNumber(columnTitle: String): Int {
    var count = 0
    val strs = columnTitle.toCharArray()
    for (i in 0 until strs.size) {
        count = count * 26 + (strs[i] - 'A' + 1)
    }
    return count
}

/**
 * 多数元素
 *
 * 给定一个大小为 n 的数组 nums ，返回其中的多数元素。多数元素是指在数组中出现次数 大于⌊ n/2 ⌋的元素。
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 *
 * @see <a href="https://leetcode.cn/problems/majority-element/">多数元素</a>
 */
fun majorityElement(nums: IntArray): Int {
    // 摩尔投票法,先假设第一个数过半数并设cnt=1；
    // 遍历后面的数如果相同则cnt+1，不同则减一，
    // 当cnt为0时则更换新的数字为候选数（成立前提：有出现次数大于n/2的数存在）
    var count = 1
    var maj = nums[0]
    for (i in 1 until nums.size) {
        if (maj == nums[i]) {
            count++
        } else {
            count--
            if (count == 0) {
                maj = nums[i + 1]
            }
        }
    }
    return maj
}

/**
 * 字符串相加
 *
 * 给定两个字符串形式的非负整数 num1 和 num2，计算它们的和并同样以字符串形式返回。
 * 你不能使用任何內建的用于处理大整数的库（比如 BigInteger），也不能直接将输入的字符串转换为整数形式。
 *
 * @see <a href="https://leetcode.cn/problems/add-strings/">字符串相加</a>
 */
fun addStrings(num1: String, num2: String): String {
    val sb = StringBuilder()
    var carry = 0
    var i = num1.length - 1
    var j = num2.length - 1
    while (i >= 0 || j >= 0 || carry != 0) {
        if (i >= 0) carry += num1[i--] - '0'
        if (j >= 0) carry += num2[j--] - '0'
        sb.append(carry % 10)
        carry /= 10
    }
    return sb.reverse().toString()
}