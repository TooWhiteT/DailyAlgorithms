package string

import java.util.*

/**
 * 最长公共前缀
 *
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * 如果不存在公共前缀，返回空字符串 ""。
 *
 * @see <a href="https://leetcode.cn/leetbook/read/array-and-string/ceda1/">最长公共前缀</a>
 */
fun longestCommonPrefix(strs: Array<String>): String {
    var pre = strs[0]
    for (i in 1 until strs.size) {
        while (strs[i].indexOf(pre) != 0) {
            pre = pre.substring(0, pre.length - 1)
        }
    }
    return pre
}

/**
 * 最长回文子串
 *
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 *
 * @see <a href="https://leetcode.cn/leetbook/read/array-and-string/conm7/">最长回文子串</a>
 */
fun longestPalindrome(s: String): String { // 中心回文
    // 用数组分别记录长度，起点，终点
    val res = IntArray(3)
    //每一次循环都将当前的坐标，作为回文的中心点，分为偶数和奇数中心，记录长度最长的。
    for (i in 0 until s.length) {
        var begin = i
        var end = i
        //奇数回文串，直接开始中心回文。
        while (begin - 1 >= 0 && end + 1 < s.length && s[begin - 1] == s[end + 1]) {
            end++
            begin--
            //比已知最长的长度长，则记录数据
            if (end - begin > res[0]){
                res[0] = end - begin
                res[1] = begin
                res[2] = end
            }
        }
        //偶数回文串，第一次先判断和下一个是否相等，然后中心回文
        begin = i
        end = i + 1
        while (begin >= 0 && end < s.length && s[begin] == s[end]) {
            if (end - begin > res[0]) {
                res[0] = end - begin
                res[1] = begin
                res[2] = end
            }
            end++
            begin--
        }
    }
    return s.substring(res[1], res[2]+1)
}

/**
 * 翻转字符串里的单词
 *
 * 给你一个字符串 s ，请你反转字符串中 单词 的顺序。
 * 单词 是由非空格字符组成的字符串。s 中使用至少一个空格将字符串中的 单词 分隔开。
 * 返回 单词 顺序颠倒且 单词 之间用单个空格连接的结果字符串。
 * 注意：输入字符串s中可能会存在前导空格、尾随空格或者单词间的多个空格。返回的结果字符串中，单词间应当仅用单个空格分隔，且不包含任何额外的空格。
 *
 * @see <a href="https://leetcode.cn/leetbook/read/array-and-string/crmp5/">翻转字符串里的单词</a>
 */
fun reverseWords(s: String): String {
    val stack = Stack<String>()
    val wordArray = s.trim().split(" ")
    for (w in wordArray) {
        if (!w.isEmpty()) {
            stack.add(w)
        }
    }
    val stringbuffer = StringBuffer()
    while (!stack.isEmpty()) {
        stringbuffer.append(stack.pop())
        if (!stack.isEmpty()) {
            stringbuffer.append(" ")
        }
    }
    return stringbuffer.toString()
}

/**
 * 实现 strStr()
 *
 * 给你两个字符串haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串的第一个匹配项的下标（下标从 0 开始）。
 * 如果needle 不是 haystack 的一部分，则返回-1 。
 *
 * @see <a href="https://leetcode.cn/leetbook/read/array-and-string/cm5e2/">实现 strStr()</a>
 */
fun strStr(haystack: String, needle: String): Int {
    if (needle.length == 0) {
        return 0
    }
    var left = 0
    var right = 0
    while (left < haystack.length && right < needle.length) {
        if (haystack[left] == needle[right]) {
            left++
            right++
        } else {
            // 如果不匹配，就回退，从第一次匹配的下一个开始，
            left = left - right + 1
            right = 0
        }
        if (right == needle.length) {
            return left - right
        }
    }
    return -1
}

/**
 * 反转字符串
 *
 * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 s 的形式给出。
 * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
 *
 * @see <a href="https://leetcode.cn/leetbook/read/array-and-string/cacxi/">反转字符串</a>
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

/**
 * 数组拆分 I
 *
 * 给定长度为2n的整数数组 nums ，你的任务是将这些数分成 n 对, 例如 (a1, b1), (a2, b2), ..., (an, bn) ，使得从 1 到 n 的 min(ai, bi) 总和最大。
 * 返回该 最大总和 。
 *
 * @see <a href="https://leetcode.cn/leetbook/read/array-and-string/c24he/">数组拆分 I</a>
 */
fun arrayPairSum(nums: IntArray): Int {
    // 两个最大的数排在一组则结果必然包含第二最大值, 同理去除最大值和第二最大值后,将剩下值中最大值和第二最大值排在一组...逐步最优—————>全局最优
    Arrays.sort(nums)
    var res = 0
    for (index in 0 until nums.size) {
        if (index % 2 == 0) {
            res += nums[index]
        }
    }
    return res
}

/**
 * 两数之和 II - 输入有序数组
 *
 * 给你一个下标从 1 开始的整数数组numbers ，该数组已按 非递减顺序排列，请你从数组中找出满足相加之和等于目标数target 的两个数。如果设这两个数分别是 numbers`[index1]` 和 numbers`[index2]` ，则 1 <= index1 < index2 <= numbers.length 。
 * 以长度为 2 的整数数组 [index1, index2] 的形式返回这两个整数的下标 index1 和 index2。
 * 你可以假设每个输入 只对应唯一的答案 ，而且你 不可以 重复使用相同的元素。
 * 你所设计的解决方案必须只使用常量级的额外空间。
 *
 * @see <a href="https://leetcode.cn/leetbook/read/array-and-string/cnkjg/">两数之和 II - 输入有序数组</a>
 */
fun twoSum(numbers: IntArray, target: Int): IntArray {
    //由于数组从小到大排列, 双指针分别指向首部和尾部;
    //首部尾部相加等于目标值，返回结果集
    //首部尾部相加小于目标值，首部后移变大
    //首部尾部相加大于目标值，尾部前移变小
    val res = intArrayOf(0, numbers.size - 1)
    var left = res[0]
    var right = res[1]
    while(left < right){
        if(numbers[left] + numbers[right] == target){
            res[0] = left+1
            res[1] = right+1
            break
        } else if (numbers[left] + numbers[right] < target){
            left++
        } else {
            right--
        }
    }
    return res
}

/**
 * 移除元素
 *
 * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于val的元素，并返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
 * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
 *
 * @see <a href="https://leetcode.cn/leetbook/read/array-and-string/cwuyj/">移除元素</a>
 */
fun removeElement(nums: IntArray, `val`: Int): Int { // 快慢指针
    var slow = 0
    for (fast in 0 until nums.size) {
        if (nums[fast] == `val`) {
            continue
        } else {
            nums[slow++] = nums[fast]
        }
    }
    return slow
}

/**
 * 最大连续1的个数
 *
 * 给定一个二进制数组 nums ， 计算其中最大连续 1 的个数。
 *
 * @see <a href="https://leetcode.cn/leetbook/read/array-and-string/cd71t/">最大连续1的个数</a>
 */
fun findMaxConsecutiveOnes(nums: IntArray): Int { // 秀一波 双指针 但没必要
    var left = 0
    var right = nums.size - 1
    var left_sum = 0
    var right_sum = 0
    var max = 0
    while (left < nums.size) {
        if (nums[left] == 1) {
            left_sum += 1
        } else {
            left_sum = 0
        }

        if (nums[right] == 1) {
            right_sum += 1
        } else {
            right_sum = 0
        }
        max = Math.max(max, Math.max(left_sum, right_sum))
        left++
        right--
    }
    return max
}

fun findMaxConsecutiveOnes2(nums: IntArray): Int { // 贪心算法
    var max = 0
    var temp = 0
    for (i in nums) {
        if (i == 1) {
            temp += 1
        } else {
            temp = 0
        }
        max = Math.max(max, temp)
    }
    return max
}

/**
 * 长度最小的子数组
 *
 * 给定一个含有n个正整数的数组和一个正整数 target 。
 * 找出该数组中满足其和 ≥ target 的长度最小的 连续子数组[numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
 *
 * @see <a href="https://leetcode.cn/leetbook/read/array-and-string/c0w4r/">长度最小的子数组</a>
 */
fun minSubArrayLen(target: Int, nums: IntArray): Int {
    // 遍历
    var min = Int.MAX_VALUE
    for (i in 0 until nums.size) {
        var sum = 0
        for (j in i until nums.size) {
            sum += nums[j]
            if (sum >= target) {
                min = Math.min(min, j - i + 1)
                break
            }
        }
    }
    return if (min == Int.MAX_VALUE) 0 else min
}

fun minSubArrayLen2(target: Int, nums: IntArray): Int { // 滑动窗口
    var min = Int.MAX_VALUE
    var left = 0
    var right = 0
    var sum = 0
    while (right < nums.size) {
        sum += nums[right]
        while (sum >= target) {
            min = Math.min(min, right - left + 1)
            sum -= nums[left]
            left++
        }
        right++
    }
    return if (min == Int.MAX_VALUE) 0 else min
}

/**
 * 杨辉三角
 *
 * 给定一个非负整数 numRows，生成「杨辉三角」的前 numRows 行。
 * 在「杨辉三角」中，每个数是它左上方和右上方的数的和。
 *
 * @see <a href="https://leetcode.cn/leetbook/read/array-and-string/cuj3m/">杨辉三角</a>
 */
fun generate(numRows: Int): List<List<Int>> {
    val res = ArrayList<ArrayList<Int>>()
    val row = ArrayList<Int>()
    for (i in 0 until numRows) {
        row.add(0, 1)
        for (j in 1 until (row.size - 1)) {
            row.set(j, row[j] + row[j + 1])
        }
        res.add(ArrayList(row))
    }
    return res
}

/**
 * 杨辉三角 II
 *
 * 给定一个非负索引 rowIndex，返回「杨辉三角」的第 rowIndex 行。
 * 在「杨辉三角」中，每个数是它左上方和右上方的数的和。
 *
 * @see <a href="https://leetcode.cn/leetbook/read/array-and-string/ctyt1/">杨辉三角 II</a>
 */
fun getRow(rowIndex: Int): List<Int> {
    val array = IntArray(rowIndex + 1)
    for (row in 0 .. rowIndex) {
        for (i in row downTo 0) {
            if (i == 0 || i == row) {
                array[i] = 1
            } else {
                array[i] = array[i - 1] + array[i]
            }
        }
    }
    return array.map { it }
}

/**
 * 反转字符串中的单词 III
 *
 * 给定一个字符串 s ，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
 *
 * @see <a href="https://leetcode.cn/leetbook/read/array-and-string/c8su7/">反转字符串中的单词 III</a>
 */
fun reverseWords3(s: String): String { // 栈
    val list = ArrayList<Stack<Char>>()
    var stack = Stack<Char>()
    for (c in s.indices) {
        if (s[c] == ' ') {
            list.add(stack)
            stack = Stack<Char>()
        } else if (c == s.length - 1) {
            stack.push(s[c])
            list.add(stack)
        } else {
            stack.push(s[c])
        }
    }
    var index = 0
    val chars = CharArray(s.length)
    for (temp in list) {
        while (!temp.isEmpty()) {
            chars[index++] = temp.pop()
        }
        if (index == s.length) {
            break
        }
        chars[index++] = ' '
    }
    return String(chars)
}

fun reverseWords3s(s: String): String { // 局部翻转
    val chars = s.toCharArray()
    val len = s.length
    var j = 0
    for (i in 0 until len) {
        val ch = chars[i]
        if (ch == ' ' || i == len - 1) {
            var m = j
            var n = if (ch == ' ') i - 1 else i
            while (m <= n) {
                val temp = chars[m]
                chars[m++] = chars[n]
                chars[n--] = temp
            }
            j = i + 1
        }
    }
    return String(chars)
}

/**
 * 寻找旋转排序数组中的最小值
 *
 * 已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。例如，原数组 nums = [0,1,2,4,5,6,7] 在变化后可能得到：
 *
 * 若旋转 4 次，则可以得到 [4,5,6,7,0,1,2]
 *
 * 若旋转 7 次，则可以得到 [0,1,2,4,5,6,7]
 *
 * 注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。
 * 给你一个元素值 互不相同 的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的 最小元素 。
 *
 * 你必须设计一个时间复杂度为O(log n) 的算法解决此问题。
 *
 * @see <a href="https://leetcode.cn/leetbook/read/array-and-string/c3ki5/">寻找旋转排序数组中的最小值</a>
 */
fun findMin(nums: IntArray): Int {
    if (nums.size == 1) return nums[0]
    var left = 0
    var right = nums.size - 1
    var mid = (left + right) shr 1
    while (left < right) {
        if (nums[mid] > nums[right]) {
            left = mid + 1
        } else if (nums[mid] < nums[right]) {
            right = mid
        }
        mid = (left + right) shr 1
    }
    return nums[left]
}

/**
 * 删除排序数组中的重复项
 *
 * 给你一个 升序排列 的数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。元素的 相对顺序 应该保持 一致 。
 *
 * 由于在某些语言中不能改变数组的长度，所以必须将结果放在数组nums的第一部分。更规范地说，如果在删除重复项之后有 k 个元素，那么nums的前 k 个元素应该保存最终结果。
 * 将最终结果插入nums的前 k 个位置后返回 k 。
 * 不要使用额外的空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 *
 * @see <a href="https://leetcode.cn/leetbook/read/array-and-string/cq376/">删除排序数组中的重复项</a>
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
 * 移动零
 *
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
 *
 * @see <a href="https://leetcode.cn/leetbook/read/array-and-string/c6u02/"></a>
 */
fun moveZeroes(nums: IntArray): Unit {
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