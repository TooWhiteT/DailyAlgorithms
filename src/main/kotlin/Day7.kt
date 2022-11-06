/**
 * 设计 Goal 解析器
 *
 * 请你设计一个可以解释字符串 command 的 Goal 解析器 。command 由 "G"、"()" 和/或 "(al)" 按某种顺序组成。Goal 解析器会将 "G" 解释为字符串 "G"、"()" 解释为字符串 "o" ，"(al)" 解释为字符串 "al" 。然后，按原顺序将经解释得到的字符串连接成一个字符串。
 * 给你字符串 command ，返回 Goal 解析器 对 command 的解释结果。
 *
 * @see <a href="https://leetcode.cn/problems/goal-parser-interpretation/">设计 Goal 解析器</a>
 */
fun interpret(command: String): String {
    val sb = StringBuilder()
    var i = 0
    while (i < command.length) {
        val ch = command[i]
        if (ch == 'G')  {
            sb.append(ch)
        } else if (ch == '(') {
            if (command[i+1] == ')') {
                sb.append("o")
                i++
            } else {
                sb.append("al")
                i += 3
            }
        }
        i++
    }
    return sb.toString()
}

/**
 * 单词规律
 *
 * 给定一种规律 pattern 和一个字符串 s ，判断 s 是否遵循相同的规律。
 * 这里的遵循指完全匹配，例如，pattern 里的每个字母和字符串 s 中的每个非空单词之间存在着双向连接的对应规律
 *
 * @see <a href="https://leetcode.cn/problems/word-pattern/">单词规律</a>
 */
fun wordPattern(pattern: String, s: String): Boolean {
    if (pattern == null || s == null) return false
    val strs = s.split(" ")
    if (pattern.length != strs.size) return false
    val map: HashMap<Char, String> = HashMap()

    for (i in 0 until pattern.length) {
        val tmp: Char = pattern[i]
        // key已经在
        if (map.containsKey(tmp)) {
            // 不对应就失败
            if (!map[tmp].equals(strs[i])) return false
        } else {
            // 两个value的值一样 a-dog b-dog->false
            if (map.containsValue(strs[i])) return false else map[tmp] = strs[i]  // 添加k-v值
        }
    }
    return true
}

/**
 * Nim 游戏
 *
 * 你和你的朋友，两个人一起玩 Nim 游戏：
 *
 * 桌子上有一堆石头.
 * 你们轮流进行自己的回合，你作为先手。
 * 每一回合，轮到的人拿掉 1 - 3 块石头。
 * 拿掉最后一块石头的人就是获胜者。
 *
 * 假设你们每一步都是最优解。请编写一个函数，来判断你是否可以在给定石头数量为 n 的情况下赢得游戏。如果可以赢，返回 true；否则，返回 false 。
 *
 * @see <a href="https://leetcode.cn/problems/nim-game/">Nim 游戏</a>
 */
fun canWinNim(n: Int): Boolean = n % 4 != 0 // 巴什博奕

/**
 * 区域和检索 - 数组不可变
 *
 * 给定一个整数数组 nums，处理以下类型的多个查询:
 * 计算索引left和right（包含 left 和 right）之间的 nums 元素的 和 ，其中left <= right
 *
 * 实现 NumArray 类：
 * NumArray(int[] nums) 使用数组 nums 初始化对象
 * int sumRange(int i, int j) 返回数组 nums中索引left和right之间的元素的 总和 ，包含left和right两点（也就是nums[left] + nums[left + 1] + ... + nums[right])
 *
 * @see <a href="https://leetcode.cn/problems/range-sum-query-immutable">区域和检索 - 数组不可变</a>
 */
class NumArray(nums: IntArray) {

    private lateinit var sums: IntArray

    init {
        sums = IntArray(nums.size)
        if (nums.isNotEmpty()) {
            sums[0] = nums[0]
            for (i in 1 until nums.size) {
                sums[i] += sums[i - 1] + nums[i]
            }
        }
    }

    fun sumRange(left: Int, right: Int): Int {
        if (left == 0) {
            return sums[right]
        } else {
            return sums[right] - sums[left - 1]
        }
    }

}

/**
 * 比特位计数
 *
 * 给你一个整数 n ，对于 0 <= i <= n 中的每个 i ，计算其二进制表示中 1 的个数 ，返回一个长度为 n + 1 的数组 ans 作为答案。
 *
 * @see <a href="https://leetcode.cn/problems/counting-bits/">比特位计数</a>
 */
fun countBits(n: Int): IntArray {
    // i & (i - 1)可以去掉i最右边的一个1（如果有），因此 i & (i - 1）是比 i 小的，而且i & (i - 1)的1的个数已经在前面算过了，所以i的1的个数就是 i & (i - 1)的1的个数加上1
//    for (i in 1 .. n) { // 注意要从1开始，0不满足
//        res[i] = res[i & (i - 1)] + 1
//    }
    // i >> 1会把最低位去掉，因此i >> 1 也是比i小的，同样也是在前面的数组里算过。当 i 的最低位是0，则 i 中1的个数和i >> 1中1的个数相同；当i的最低位是1，i 中1的个数是 i >> 1中1的个数再加1
    val res = IntArray(n + 1)
    for (i in 0..n) {
        res[i] = res[i shr 1] + (i and 1) // 注意 i&1 需要加括号
    }
    return res
}