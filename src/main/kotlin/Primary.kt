import java.util.*
import javax.xml.stream.events.Characters
import kotlin.collections.HashMap
import kotlin.collections.HashSet

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

/**
 * 字符串中的第一个唯一字符
 *
 * 给定一个字符串 s ，找到 它的第一个不重复的字符，并返回它的索引 。如果不存在，则返回 -1
 *
 * @see <a href="https://leetcode.cn/leetbook/read/top-interview-questions-easy/xn5z8r/">字符串中的第一个唯一字符</a>
 */
fun firstUniqChar(s: String): Int {
    for (i in 0 until s.length) {
        if (s.indexOf(s[i]) == s.lastIndexOf(s[i])) {
            return i
        }
    }
    return -1
}

/**
 * 有效的字母异位词
 *
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 * 注意：若 s 和 t 中每个字符出现的次数都相同，则称 s 和 t 互为字母异位词。
 *
 * @see <a href="https://leetcode.cn/leetbook/read/top-interview-questions-easy/xn96us/">有效的字母异位词</a>
 */
fun isAnagram(s: String, t: String): Boolean { // 排序 比较内容  遍历s map存 遍历s减数量 都为0 返回true
    if (s.length != t.length) {
        return false
    }
    val s_arr = s.toCharArray()
    val t_arr = t.toCharArray()
    s_arr.sort()
    t_arr.sort()
    return s_arr.contentEquals(t_arr)
}

/**
 * 验证回文串
 *
 * 如果在将所有大写字符转换为小写字符、并移除所有非字母数字字符之后，短语正着读和反着读都一样。则可以认为该短语是一个 回文串 。
 * 字母和数字都属于字母数字字符。
 * 给你一个字符串 s，如果它是 回文串 ，返回 true ；否则，返回 false 。
 *
 * @see <a href="https://leetcode.cn/leetbook/read/top-interview-questions-easy/xne8id/">验证回文串</a>
 */
fun isPalindrome(s: String): Boolean { // 双指针法
    if (s.trim().isEmpty()) {
        return true
    }
    val temp = s.toLowerCase()
    var left = 0
    var right = s.length - 1
    while (left < right) {
        while (left < right && !Character.isLetterOrDigit(temp[left])) {
            left++
        }
        while (left < right && !Character.isLetterOrDigit(temp[right])) {
            right--
        }
        if (temp[left] != temp[right]) return false
        left++
        right--
    }
    return true
}

/**
 * 字符串转换整数 (atoi)
 *
 * 请你来实现一个 myAtoi(string s) 函数，使其能将字符串转换成一个 32 位有符号整数（类似 C/C++ 中的 atoi 函数）。
 *
 * 函数 myAtoi(string s) 的算法如下：
 *
 * 1.读入字符串并丢弃无用的前导空格
 *
 * 2.检查下一个字符（假设还未到字符末尾）为正还是负号，读取该字符（如果有）。 确定最终结果是负数还是正数。 如果两者都不存在，则假定结果为正。
 *
 * 3.读入下一个字符，直到到达下一个非数字字符或到达输入的结尾。字符串的其余部分将被忽略。
 *
 * 4.将前面步骤读入的这些数字转换为整数（即，"123" -> 123， "0032" -> 32）。如果没有读入数字，则整数为 0 。必要时更改符号（从步骤 2 开始）。
 *
 * 5.如果整数数超过 32 位有符号整数范围 [−2^31, 2^31− 1] ，需要截断这个整数，使其保持在这个范围内。具体来说，小于 −2^31 的整数应该被固定为 −2^31 ，大于 2^31− 1 的整数应该被固定为 2^31− 1 。
 *
 * 6.返回整数作为最终结果。
 *
 * 注意：
 * 本题中的空白字符只包括空格字符 ' ' 。
 * 除前导空格或数字后的其余字符串外，请勿忽略 任何其他字符。
 *
 * @see <a href="https://leetcode.cn/leetbook/read/top-interview-questions-easy/xnoilh/">字符串转换整数 (atoi)</a>
 */
fun myAtoi(s: String): Int {
    // 去掉空格
    val temp = s.trim()
    // 如果为空，直接返回0
    if (temp.length == 0)
        return 0;
    var index = 0 // 遍历字符串中字符的位置
    var res = 0 // 最终结果
    var sign = 1 // 符号，1是正数，-1是负数，默认为正数
    var length = temp.length
    // 判断符号
    if (temp[index] == '-' || temp[index] == '+') {
        sign = if (temp[index++] == '-')  -1 else 1
    }
    for (i in index until length) {
        // 取出字符串中字符，然后转化为数字
        val digit = temp[i] - '0'
        // 按照题中的要求，读入下一个字符，直到到达下一个非数字字符或到达输入的结尾。
        // 字符串的其余部分将被忽略。如果读取了非数字，后面的都要忽略
        if (digit < 0 || digit > 9) {
            break
        }
        // 越界处理
        if (res > Int.MAX_VALUE / 10 || (res == Int.MAX_VALUE / 10 && digit > Int.MAX_VALUE % 10)) {
            return if (sign == 1) Int.MAX_VALUE else Int.MIN_VALUE
        } else {
            res = res * 10 + digit
        }
    }
    return sign * res // 进行正负赋值
}

/**
 * 实现 strStr()
 *
 * 给你两个字符串haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串的第一个匹配项的下标（下标从 0 开始）。如果needle 不是 haystack 的一部分，则返回 -1 。
 *
 * @see <a href="https://leetcode.cn/leetbook/read/top-interview-questions-easy/xnr003/">实现 strStr()</a>
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

private fun getNext(p: String, next: IntArray) {
    val len = p.length
    var i = 0
    var j = -1
    next[0] = -1 // 这个默认的，
    /**
     * 匹配的时候是当前字符的前一个和前面的匹配，所以最后一个是不参与匹配的，可以看strStr方法的注释，
     */
    while (i < len - 1) {
        if (j == -1 || p[i] == p[j]) {
            /**
             * 如果j不等于-1，指定的字符相等，那么i和j要往后移一位，这点很好理解，如果j为-1的时候，i往后移移位，j置为0
             * 重新开始匹配。next[i]是匹配成功的数量
             */
            i++
            j++
            next[i] = j
        } else {
            /**
             * 关键是这里不好理解，为什么匹配失败要让next[j]等于j，要记住这里的next[j]是指匹配成功的数量，有可能为0，也有可能是其他数.比如
             * 字符串ABCABXYABCABATDM,对应的next数组为{-1	0	0	0	1	2	0	0	1	2	3	4	5	1	0	0	}
             */
            j = next[j]
        }
    }
}

fun strStrKMP(haystack: String, needle: String): Int {
    if (needle.length == 0) return 0
    var i = 0
    var j = 0
    /**
     * 数组next表示pattern指定的下标前具有相同的字符串数量，语言组织能力不好，可能不是太好理解，我举个例子吧
     * ，比如ABCABA，数组next[0]是-1，这个是固定的，因为第一个A前面是没有字符的，next[1]是0，因为B的前面就一个A，没有
     * 重复的，所以是0,同理next[2]也是,next[3]也是0,而next[4]是1，因为next[4]所指向的是第二个B，第二个B前面有一个A和
     * 第一个A相同，所以是1,next[5]是2，因为next[5]所指向的是最后一个Ａ，因为前面的Ａ对比成功，并且Ｂ也对比成功，所以是２，
     * 也就是ＡＢ两个字符串匹配成功,再举个例子，比如WABCABA，数组除了第一个为-1，其他的都是为0，因为只有第一个匹配成功之后
     * 才能匹配后面的，虽然后面的AB和前面的AB匹配成功，但是后面AB的前面是C和前面AB的前面一个W不匹配，所以后面的匹配都是0.
     * 要记住只有指定字符前面的字符和第一个字符匹配成功的时候才能往后匹配，否则后面的永远都是先和第一个匹配。
     */
    val next = IntArray(needle.length)
    getNext(needle, next)
    while (i < haystack.length && j < needle.length) {
        if (j == -1 || haystack[i] == needle[j]) {
            /**
             * 这里j等于-1的时候也只有在下面next数组赋值的时候才会出现，并且只有在数组next[0]的时候才会等于-1，
            其他时候是没有的，这一点要谨记，待会下面求next数组的时候就会用到。这里可以这样来理解，如果j不等于-1，
            并且下标i和j所指向的字符相等，那么i和j分别往后移一位继续比较，这个很好理解，那么如果j==-1的时候，就表示
            就表示前面没有匹配成功的，同时i往后移一位，j置为0（j==-1的时候，j++为0），再从0开始比较。
             */
            i++
            j++
        } else {
            /**
             * i = i - j + 1;
            j = 0;
            返回到指定的位置，不是返回到匹配失败的下一个位置，这里都好理解，重点是求数组next。
            这里只要j等于0，在next[j]赋值的之后，j就会等于-1；因为next[0]等于-1
             */
            j = next[j] // j回到指定位置
        }
        if (j == needle.length) return i - j
    }
    return -1
}
