/**
 * 字符串中第二大的数字
 *
 * 给你一个混合字符串 s ，请你返回 s 中 第二大 的数字，如果不存在第二大的数字，请你返回 -1 。
 * 混合字符串 由小写英文字母和数字组成。
 *
 * @see <a href="https://leetcode.cn/problems/second-largest-digit-in-a-string/">字符串中第二大的数字</a>
 */
fun secondHighest(s: String): Int {
    val chars = s.toCharArray()
    var mx1 = -1
    var mx2 = -1
    for (c in chars) {
        if (Character.isLetter(c)) continue
        val num = c.hashCode() - '0'.hashCode()
        if (num > mx1) {
            mx2 = mx1
            mx1 = num
        } else if (num < mx1 && num > mx2) {
            mx2 = num
        }
    }
    return mx2
}

/**
 * 按奇偶排序数组
 *
 * 给你一个整数数组 nums，将 nums 中的的所有偶数元素移动到数组的前面，后跟所有奇数元素。
 * 返回满足此条件的 任一数组 作为答案。
 *
 * @see <a href="https://leetcode.cn/problems/sort-array-by-parity/">按奇偶排序数组</a>
 */
fun sortArrayByParity(nums: IntArray): IntArray {
    val temp = IntArray(nums.size)
    var left = 0
    var right = nums.size - 1
    for (i in 0 until nums.size) {
        if (nums[i] % 2 == 0) {
            temp[left++] = nums[i]
        } else {
            temp[right--] = nums[i]
        }
    }
    return temp
}

/**
 * 最小差值 I
 *
 * 给你一个整数数组 nums，和一个整数 k 。
 * 在一个操作中，您可以选择 0 <= i < nums.length 的任何索引 i 。将 nums[i] 改为 nums[i] + x ，其中 x 是一个范围为 [-k, k] 的整数。对于每个索引 i ，最多 只能 应用 一次 此操作。
 * nums的分数是nums中最大和最小元素的差值。
 * 在对 nums 中的每个索引最多应用一次上述操作后，返回nums 的最低 分数 。
 *
 * @see <a href="https://leetcode.cn/problems/smallest-range-i">最小差值 I</a>
 */
fun smallestRangeI(nums: IntArray, k: Int): Int {
    val max = nums.max()!!.minus(k)
    val min = nums.min()!!.plus(k)
    val res = max - min
    return if (res >= 0) res else 0
}

/**
 * 仅仅反转字母
 *
 * 给你一个字符串 s ，根据下述规则反转字符串：
 *  所有非英文字母保留在原有位置。
 *  所有英文字母（小写或大写）位置反转。
 *  返回反转后的 s 。
 *
 * @see <a href="https://leetcode.cn/problems/reverse-only-letters/">仅仅反转字母</a>
 */
fun reverseOnlyLetters(s: String): String {
    val chars = s.toCharArray()
    var left = 0
    var right = chars.size - 1
    while (left < right) {
        if (!Character.isLetter(chars[left])) {
            left++
        }
        if (!Character.isLetter(chars[right])) {
            right--
        }
        if (Character.isLetter(chars[left]) &&
            Character.isLetter(chars[right])
        ) {
            val temp = chars[left]
            chars[left] = chars[right]
            chars[right] = temp
            left++
            right--
        }
    }
    return String(chars)
}

/**
 * 卡牌分组
 * 
 * 给定一副牌，每张牌上都写着一个整数。
 * 此时，你需要选定一个数字 X，使我们可以将整副牌按下述规则分成 1 组或更多组：
 *  每组都有X张牌。
 *  组内所有的牌上都写着相同的整数。
 *  仅当你可选的 X >= 2 时返回true。
 * 
 * @see <a href="https://leetcode.cn/problems/x-of-a-kind-in-a-deck-of-cards/">卡牌分组</a>
 */
fun hasGroupsSizeX(deck: IntArray): Boolean {
    // 计数
    val counter = IntArray(10000)
    for (num in deck) {
        counter[num]++
    }
    // 求gcd
    var x = 0
    for (cnt in counter) {
        if (cnt > 0) {
            x = gcd(x, cnt)
            if (x == 1) {
                return false
            }
        }
    }
    return x >= 2
}

// 辗转相除法
private fun gcd(a: Int, b: Int): Int {
    return if (b == 0) a else gcd(b, a % b)
}