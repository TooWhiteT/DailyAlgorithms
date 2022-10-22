import java.util.*
import kotlin.collections.ArrayList

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

/**
 * 外观数列
 *
 * 给定一个正整数 n ，输出外观数列的第 n 项。
 *「外观数列」是一个整数序列，从数字 1 开始，序列中的每一项都是对前一项的描述。
 * 你可以将其视作是由递归公式定义的数字字符串序列：
 *  countAndSay(1) = "1"
 *  countAndSay(n) 是对 countAndSay(n-1) 的描述，然后转换成另一个数字字符串。
 * @see <a href="https://leetcode.cn/leetbook/read/top-interview-questions-easy/xnpvdm/">外观数列</a>
 */
fun countAndSay(n: Int): String {
    if (n == 1) return "1"
    val temp = countAndSay(n - 1)
    val res = StringBuilder()
    var local = temp[0]
    var count = 0
    for (i in temp) {
        if (i == local) {
            count++
        } else {
            res.append(count)
            res.append(local)
            count = 1
            local = i
        }
    }
    res.append(count)
    res.append(local)
    return res.toString()
}

/**
 * 最长公共前缀
 *
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * 如果不存在公共前缀，返回空字符串 ""。
 *
 * @see <a href="">最长公共前缀</a>
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

class ListNode(var `val`: Int) { // 链表
    var next: ListNode? = null
}

/**
 * 删除链表中的节点
 *
 * 有一个单链表的 head，我们想删除它其中的一个节点node。
 * 给你一个需要删除的节点node。你将无法访问第一个节点head。
 * 链表的所有值都是 唯一的，并且保证给定的节点node不是链表中的最后一个节点。
 * 删除给定的节点。注意，删除节点并不是指从内存中删除它。这里的意思是：
 *
 * 给定节点的值不应该存在于链表中。
 *
 * 链表中的节点数应该减少 1。
 *
 * node前面的所有值顺序相同。
 *
 * node后面的所有值顺序相同。
 *
 * @see <a href="https://leetcode.cn/leetbook/read/top-interview-questions-easy/xnarn7/">删除链表中的节点</a>
 */
fun deleteNode(node: ListNode?) {
    // 删除节点 将它指向的下一个节点替换掉它  影子弑主
    node?.`val` = node?.next?.`val`!!
    node.next = node.next?.next
}

/**
 * 删除链表的倒数第N个节点
 *
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 *
 * @see <a href="https://leetcode.cn/leetbook/read/top-interview-questions-easy/xn2925/">删除链表的倒数第N个节点</a>
 */
fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
    var pre = head
    val last = length(head) - n
    // 如果last等于0表示删除的是头结点
    if (last == 0) return head?.next
    // 这里首先要找到要删除链表的前一个结点
    for (i in 0 until (last - 1)) {
        pre = pre?.next
    }
    // 然后让前一个结点的 next 指向要删除节点的 next
    pre?.next = pre?.next?.next
    return head
}
// 获取链表长度
fun length(head: ListNode?): Int {
    var temp = head
    var len = 0
    while (temp != null) {
        len++
        temp = temp.next
    }
    return len
}

/**
 * 反转链表
 * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
 *
 * @see <a href="https://leetcode.cn/leetbook/read/top-interview-questions-easy/xnnhm6/">反转链表</a>
 */
fun reverseList(head: ListNode?): ListNode? { // 栈 先进后出特性
    val stack = Stack<ListNode>()
    var temp = head
    while (temp != null) {
        stack.push(temp)
        temp = temp.next
    }
    if (stack.isEmpty()) return null
    var node = stack.pop()
    val dump = node
    while (!stack.isEmpty()) {
        val pop = stack.pop()
        node.next = pop
        node = node.next
    }
    node.next = null
    return dump
}

// 递归法
fun reverseList2(head: ListNode?): ListNode? {
    if (head?.next == null) {
        return head
    }
    val newHead = reverseList2(head.next)
    head.next?.next = head
    head.next = null
    return newHead
}

/**
 * 合并两个有序链表
 *
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 *
 * @see <a href="https://leetcode.cn/leetbook/read/top-interview-questions-easy/xnnbp2/">合并两个有序链表</a>
 */
fun mergeTwoLists(list1: ListNode?, list2: ListNode?): ListNode? { // 双指针法
    var l1 = list1
    var l2 = list2
    val head = ListNode(0)
    var temp = head
    // 比较一下哪个小就把哪个链表的头拿出来放到新的链表中，一直这样循环
    while (l1 != null && l2 != null) {
        if (l1.`val` <= l2.`val`) {
            temp.next = ListNode(l1.`val`)
            l1 = l1.next
        } else {
            temp.next = ListNode(l2.`val`)
            l2 = l2.next
        }
        // 向后移
        temp = temp.next!!
    }
    // 最后将不为空的加后面即可
    if (l1 == null) {
        temp.next = l2
    } else {
        temp.next = l1
    }
    return head.next
}

fun mergeTwoLists2(list1: ListNode?, list2: ListNode?): ListNode? {
    val newList = ListNode(-1)
    var dummy: ListNode? = newList
    var tempList1 = list1
    var tempList2 = list2
    while (tempList1 != null || tempList2 != null) {
        if (tempList1 == null) {
            dummy?.next = tempList2
            break
        } else if (tempList2 == null) {
            dummy?.next = tempList1
            break
        } else if (tempList1.`val` < tempList2.`val`) {
            dummy?.next = tempList1
            dummy = dummy?.next
            tempList1 = tempList1.next
        } else {
            dummy?.next = tempList2
            dummy = dummy?.next
            tempList2 = tempList2.next
        }
    }
    return newList.next
}

/**
 * 回文链表
 *
 * 给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。
 *
 * @see <a href="https://leetcode.cn/leetbook/read/top-interview-questions-easy/xnv1oc/">回文链表</a>
 */
fun isPalindrome(head: ListNode?): Boolean { // 栈方法
    if (head == null) return true
    var temp = head
    var temp_stack = head
    val stack = Stack<Int>()
    var length = 0
    // 入栈
    while (temp_stack != null) {
        stack.push(temp_stack.`val`)
        temp_stack = temp_stack.next
        length++
    }
    // 长度 除 2  只要对比一半
    length = length ushr 1
    while (length >= 0) {
        // 出栈 后半段 与 前半段进行比较
        if (temp?.`val` != stack.pop()) return false
        temp = temp?.next
        length--
    }
    return true
}

fun isPalindrome2(head: ListNode?): Boolean { // 快慢指针
    var fast = head // 快指针
    var slow = head // 慢指针
    while (fast?.next != null) {
        fast = fast?.next?.next
        slow = slow?.next
    }
    // 如果fast不为空，说明链表的长度是奇数个
    if (fast != null) {
        slow = slow?.next
    }
    slow = reverse(slow)
    fast = head
    while (slow != null) {
        // 然后比较，判断节点值是否相等
        if (fast?.`val` != slow.`val`) return false
        slow = slow.next
        fast = fast.next
    }
    return true
}

fun reverse(head: ListNode?): ListNode? {
    var perv: ListNode? = null
    var temp = head
    while (temp != null) {
        val next = temp.next
        temp.next = perv
        perv = temp
        temp = next
    }
    return perv
}

/**
 * 环形链表
 *
 * 给你一个链表的头节点 head ，判断链表中是否有环。
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。注意：pos 不作为参数进行传递。仅仅是为了标识链表的实际情况。
 * 如果链表中存在环，则返回 true 。 否则，返回 false 。
 *
 * @see <a href="https://leetcode.cn/leetbook/read/top-interview-questions-easy/xnwzei/">环形链表</a>
 */
fun hasCycle(head: ListNode?): Boolean { // 快慢指针
    // 假如有环，那么快慢指针最终都会走到环上 相遇
    if (head == null) return false
    var slow = head // 慢
    var fast = head // 快
    while (fast?.next != null) {
        slow = slow?.next // 慢 走一步
        fast = fast?.next?.next // 快 走两步
        // 如果相遇，说明有环，直接返回true
        if (fast == slow) return true
    }
    return false
}

fun hasCycle2(head: ListNode?): Boolean { // Set 集合特性 判断是否重复
    val set = HashSet<ListNode>()
    var temp = head
    while (temp != null) {
        if (set.contains(temp)) return true
        set.add(temp)
        temp = temp.next
    }
    return false
}

class TreeNode(var `val`: Int) { // 树
    var left: TreeNode? = null
    var right: TreeNode? = null
}

/**
 * 二叉树的最大深度
 *
 * 给定一个二叉树，找出其最大深度。
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * @see <a href="https://leetcode.cn/leetbook/read/top-interview-questions-easy/xnd69e/">二叉树的最大深度</a>
 */
fun maxDepth(root: TreeNode?): Int { // 递归
    if (root == null) return 0
    val left = maxDepth(root.left)
    val right = maxDepth(root.right)
    return 1 + (if (right >= left) right else left)
}

/**
 * 验证二叉搜索树
 *
 * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
 * 有效 二叉搜索树定义如下：
 *
 * 1.节点的左子树只包含 小于 当前节点的数。
 *
 * 2.节点的右子树只包含 大于 当前节点的数。
 *
 * 3.所有左子树和右子树自身必须也是二叉搜索树。
 *
 * @see <a href="https://leetcode.cn/leetbook/read/top-interview-questions-easy/xn08xg/">验证二叉搜索树</a>
 */
// 前一个结点，全局的
var prev: TreeNode? = null
fun isValidBST(root: TreeNode?): Boolean {
    if (root == null) return true
    // 访问左子树
    if (!isValidBST(root.left)) return false
    // 左树满足条件 判断右树
    // 访问当前节点：如果当前节点小于等于中序遍历的前一个节点直接返回false。
    if (prev != null && prev?.`val`!! >= root.`val`) return false
    prev = root
    // 访问右子树
    if (!isValidBST(root.right)) return false
    return true
}

/**
 * 对称二叉树
 *
 * 给你一个二叉树的根节点 root ， 检查它是否轴对称。
 *
 * @see <a href="https://leetcode.cn/leetbook/read/top-interview-questions-easy/xn7ihv/">对称二叉树</a>
 */
fun isSymmetric(root: TreeNode?): Boolean { // 递归
    if (root == null) return true
    // 从两个子节点开始判断
    return isSymmetricHelper(root.left, root.right)
}

fun isSymmetricHelper(left: TreeNode?, right: TreeNode?): Boolean {
    // 如果左右子节点都为空，说明当前节点是叶子节点，返回true
    if (left == null && right == null) return true
    // 如果当前节点只有一个子节点或者有两个子节点，但两个子节点的值不相同，直接返回false
    if (left == null || right == null || right.`val` != left.`val`) return false
    // 然后左子节点的左子节点和右子节点的右子节点比较，左子节点的右子节点和右子节点的左子节点比较
    return isSymmetricHelper(left.left, right.right) && isSymmetricHelper(left.right, right.left)
}

fun isSymmetric2(root: TreeNode?): Boolean { // 迭代
    val queue = LinkedList<TreeNode?>()
    // 将子树放入队列
    queue.offer(root?.left)
    queue.offer(root?.right)
    while (!queue.isEmpty()) {
        // 一次取两个 注意与上下文 offer的顺序
        val left = queue.poll()
        val right = queue.poll()

        if (left == null && right == null) continue
        // 如果左子树和右子树一个为空一个不为空，直接返回false
        if (left == null || right == null) return false
        // 左子树和右子树 值不同返回false
        if (left.`val` != right.`val`) return false

        // 把子节点加入到队列中，注意加入的顺序

        // 外翼
        queue.offer(left.left)
        queue.offer(right.right)
        // 内翼
        queue.offer(left.right)
        queue.offer(right.left)
    }
    return true
}

/**
 * 二叉树的层序遍历
 *
 * 给你二叉树的根节点 root ，返回其节点值的 层序遍历 。 （即逐层地，从左到右访问所有节点）。
 *
 * @see <a href="https://leetcode.cn/leetbook/read/top-interview-questions-easy/xnldjj/">二叉树的层序遍历</a>
 */
fun levelOrder(root: TreeNode?): List<List<Int>> { // BFS 广度搜索
    if (root == null) return listOf()
    val queue = LinkedList<TreeNode?>()
    val res = ArrayList<ArrayList<Int>>()
    // 根节点加入队列
    queue.add(root)
    // 遍历队列中的节点
    while (!queue.isEmpty()) {
        // 表示的是每层的结点数
        val level = queue.size
        // 每层的结点值
        val subList = ArrayList<Int>()
        for (i in 0 until level) {
            // 出列
            val node = queue.poll()
            subList.add(node?.`val`!!)
            // 左右子节点如果不为空就加入到队列中
            if (node.left != null) {
                queue.add(node.left)
            }
            if (node.right != null) {
                queue.add(node.right)
            }
        }
        // 把每层的结点值存储在res中
        res.add(subList)
    }
    return res
}

/**
 * 将有序数组转换为二叉搜索树
 *
 * 给你一个整数数组 nums ，其中元素已经按 升序 排列，请你将其转换为一棵 高度平衡 二叉搜索树。
 *
 * 高度平衡 二叉树是一棵满足「每个节点的左右两个子树的高度差的绝对值不超过 1 」的二叉树。
 *
 * @see <a href="https://leetcode.cn/leetbook/read/top-interview-questions-easy/xninbt/">将有序数组转换为二叉搜索树</a>
 */
fun sortedArrayToBST(nums: IntArray): TreeNode? {
    if (nums.size == 0) return null
    return sortedArrayToBST(nums, 0, nums.size - 1)
}

// 使用递归的方式，每次取数组中间的值比如m作为当前节点，m前面的值作为他左子树的结点值，m后面的值作为他右子树的节点值
fun sortedArrayToBST(nums: IntArray, start: Int, end: Int): TreeNode? {
    if (start > end) return null
    val mid = (start + end) ushr 1
    val node = TreeNode(nums[mid])
    node.left = sortedArrayToBST(nums, start, mid - 1)
    node.right = sortedArrayToBST(nums, mid + 1, end)
    return node
}

/**
 * 合并两个有序数组
 *
 * 给你两个按 非递减顺序 排列的整数数组 nums1 和 nums2，另有两个整数 m 和 n ，分别表示 nums1 和 nums2 中的元素数目。
 * 请你 合并 nums2 到 nums1 中，使合并后的数组同样按 非递减顺序 排列。
 * 注意：最终，合并后数组不应由函数返回，而是存储在数组 nums1 中。为了应对这种情况，nums1 的初始长度为 m + n，其中前 m 个元素表示应合并的元素，后 n 个元素为 0 ，应忽略。nums2 的长度为 n 。
 *
 * @see <a href="https://leetcode.cn/leetbook/read/top-interview-questions-easy/xnumcr/">合并两个有序数组</a>
 */
fun merge(nums1: IntArray, m: Int, nums2: IntArray, n: Int) { // 归并排序
    val temp = IntArray(m + n)
    var temp_index = 0
    var left = 0
    var right = 0

    while (left < m && right < n) {
        if (nums1[left] <= nums2[right]) {
            temp[temp_index++] = nums1[left++]
        } else {
            temp[temp_index++] = nums2[right++]
        }
    }
    for (left in left until m) {
        temp[temp_index++] = nums1[left]
    }
    for (right in right until n) {
        temp[temp_index++] = nums2[right]
    }
    for (i in 0 until temp.size) {
        nums1[i] = temp[i]
    }
}

fun merge2(nums1: IntArray, m: Int, nums2: IntArray, n: Int) {
    // m = [1,2,3,0,0,0]
    // n = [2,5,6]
    var left = m - 1
    var right = n - 1
    var end = m + n - 1
    while (right >= 0) {
        // 从大往小开始 归并
        nums1[end--] = if (left >= 0 && nums1[left] > nums2[right]) nums1[left--] else nums2[right--]
    }
}

/**
 * 第一个错误的版本
 *
 * 你是产品经理，目前正在带领一个团队开发新的产品。不幸的是，你的产品的最新版本没有通过质量检测。由于每个版本都是基于之前的版本开发的，所以错误的版本之后的所有版本都是错的。
 * 假设你有 n 个版本 [1, 2, ..., n]，你想找出导致之后所有版本出错的第一个错误的版本。
 * 你可以通过调用bool isBadVersion(version)接口来判断版本号 version 是否在单元测试中出错。实现一个函数来查找第一个错误的版本。你应该尽量减少对调用 API 的次数。
 *
 * @see <a href="https://leetcode.cn/leetbook/read/top-interview-questions-easy/xnto1s/">第一个错误的版本</a>
 */
fun isBadVersion(version: Int) : Boolean { // 过编译 无实际意义
    return false
}
fun firstBadVersion(n: Int): Int { // 这题玩尬的
    var start = 0
    var end = n
    while (start < end) {
        val mid = start + (end - start) / 2
        if (!isBadVersion(mid)) {
            start = mid + 1
        } else {
            end = mid
        }
    }
    return start
}

/**
 * 爬楼梯
 *
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *
 * @see <a href="https://leetcode.cn/leetbook/read/top-interview-questions-easy/xn854d/">爬楼梯</a>
 */
fun climbStairs(n: Int): Int { // 找规律
    if (n <= 1) return 1
    val dp = IntArray(n + 1)
    dp[1] = 1
    dp[2] = 2
    for (i in 3 .. n) {
        dp[i] = dp[i-1] + dp[i-2]
    }
    return dp[n]
}

fun climbStairs2(n: Int): Int {
    if (n <= 2) return n
    var first = 1
    var second = 2
    var sum = 0
    for (i in n downTo 3) {
        sum = first + second
        first = second
        second = sum
    }
    return sum
}

/**
 * 买卖股票的最佳时机
 *
 * 给定一个数组 prices ，它的第 i 个元素prices`[i]` 表示一支给定股票第 i 天的价格。
 * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
 * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
 *
 * @see <a href="https://leetcode.cn/leetbook/read/top-interview-questions-easy/xn8fsh/">买卖股票的最佳时机</a>
 */
fun maxProfit2(prices: IntArray): Int {
    if (prices.size == 0) return 0
    var maxProfit = 0 // 记录最大利润
    var min = prices[0] // 记录最小值
    for (i in 1 until prices.size) {
        min = Math.min(min, prices[i])
        maxProfit = Math.max(maxProfit, prices[i] - min)
    }
    return maxProfit
}

/**
 * 最大子序和
 *
 * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * 子数组 是数组中的一个连续部分。
 *
 * @see <a href="https://leetcode.cn/leetbook/read/top-interview-questions-easy/xn3cg3/">最大子序和</a>
 */
fun maxSubArray(nums: IntArray): Int {
    var cur = nums[0]
    var max = cur
    for (i in 1 until nums.size) {
        cur = Math.max(cur, 0) + nums[i]
        max = Math.max(cur, max)
    }
    return max
}

/**
 * 打家劫舍
 *
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 *
 * @see <a href="https://leetcode.cn/leetbook/read/top-interview-questions-easy/xnq4km/">打家劫舍</a>
 */
fun rob(nums: IntArray): Int {
    if (nums.size == 0) return 0
    var dp0 = 0 // 第1家没偷
    var dp1 = nums[0] // 第1家偷了

    for (i in 1 until nums.size) {
        // 防止dp0被修改之后对下面运算造成影响，这里
        // 使用一个临时变量temp先把结果存起来，计算完
        // 之后再赋值给dp0.
        val temp = Math.max(dp0, dp1)
        dp1 = dp0 + nums[i]
        dp0 = temp
    }
    // 最后取最大值即可
    return Math.max(dp0, dp1)
}

/**
 * 打乱数组
 *
 * 给你一个整数数组 nums ，设计算法来打乱一个没有重复元素的数组。打乱后，数组的所有排列应该是 等可能 的。
 * 实现 Solution class:
 *
 * 1. Solution(int[] nums) 使用整数数组 nums 初始化对象
 * 2. int[] reset() 重设数组到它的初始状态并返回
 * 3. int[] shuffle() 返回数组随机打乱后的结果
 *
 * @see <a href="https://leetcode.cn/leetbook/read/top-interview-questions-easy/xn6gq1/">打乱数组</a>
 */
class Solution(val nums: IntArray) {
    val temp = nums
    fun reset(): IntArray {
        return temp
    }

    fun shuffle(): IntArray {
        val clone = nums.clone()
        for (i in 0 until nums.size) {
            val j = Random().nextInt(i + 1)
            if (i != j) {
                clone[i] = clone[i] xor clone[j]
                clone[j] = clone[j] xor clone[i]
                clone[i] = clone[i] xor clone[j]
            }
        }
        return clone
    }
}

/**
 * 最小栈
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 *
 * 实现 MinStack 类:
 *
 * MinStack() 初始化堆栈对象。
 *
 * void push(int val) 将元素val推入堆栈。
 *
 * void pop() 删除堆栈顶部的元素。
 *
 * int top() 获取堆栈顶部的元素。
 *
 * int getMin() 获取堆栈中的最小元素。
 *
 * @see <a href="https://leetcode.cn/leetbook/read/top-interview-questions-easy/xnkq37/"></a>
 */

data class StackNode(val `val`: Int, val min: Int)
class MinStack() {
    val stack = Stack<StackNode>()
    fun push(`val`: Int) {
        if (stack.isEmpty()) {
            stack.push(StackNode(`val`, `val`))
        } else {
            stack.push(StackNode(`val`, Math.min(`val`, getMin())))
        }
    }

    fun pop() {
        if (stack.isEmpty()) {
            throw IllegalStateException("栈为空")
        }
        stack.pop()
    }

    fun top(): Int {
        if (stack.isEmpty()) {
            throw IllegalStateException("栈为空")
        }
        return stack.peek().`val`
    }

    fun getMin(): Int {
        if (stack.isEmpty()) {
            throw IllegalStateException("栈为空")
        }
        return stack.peek().min
    }
}