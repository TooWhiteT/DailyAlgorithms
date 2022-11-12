import java.util.*
import kotlin.collections.HashMap

/**
 * 密钥格式化
 *
 * 给定一个许可密钥字符串 s，仅由字母、数字字符和破折号组成。字符串由 n 个破折号分成 n + 1 组。你也会得到一个整数 k 。
 * 我们想要重新格式化字符串s，使每一组包含 k 个字符，除了第一组，它可以比 k 短，但仍然必须包含至少一个字符。此外，两组之间必须插入破折号，并且应该将所有小写字母转换为大写字母。
 * 返回 重新格式化的许可密钥 。
 *
 * <a href="https://leetcode.cn/problems/license-key-formatting">密钥格式化</a>
 */
fun licenseKeyFormatting(s: String, k: Int): String {
    if (k <= 0) {
        return ""
    }

    val builder = StringBuilder()
    for (i in 0 until s.length) {
        if (s[i] != '-') {
            builder.append(s[i])
        }
    }

    var i: Int = builder.length - k
    while (i > 0) {
        builder.insert(i, '-')
        i -= k
    }

    return builder.toString().toUpperCase()
}

/**
 * 构造矩形
 *
 * 作为一位 web开发者， 懂得怎样去规划一个页面的尺寸是很重要的。 所以，现给定一个具体的矩形页面面积，你的任务是设计一个长度为 L 和宽度为 W 且满足以下要求的矩形的页面。要求：
 *
 * 你设计的矩形页面必须等于给定的目标面积。
 * 宽度 W不应大于长度 L ，换言之，要求 L >= W 。
 * 长度 L 和宽度 W之间的差距应当尽可能小。
 * 返回一个数组[L, W]，其中 L 和 W 是你按照顺序设计的网页的长度和宽度。
 *
 * @see <a href="https://leetcode.cn/problems/construct-the-rectangle">构造矩形</a>
 */
fun constructRectangle(area: Int): IntArray {
    var width = Math.sqrt(area.toDouble()).toInt()
    while (area % width != 0) {
        width--
    }
    return intArrayOf(area / width, width)
}

/**
 * 提莫攻击
 *
 * 在《英雄联盟》的世界中，有一个叫 “提莫” 的英雄。他的攻击可以让敌方英雄艾希（编者注：寒冰射手）进入中毒状态。
 * 当提莫攻击艾希，艾希的中毒状态正好持续 duration 秒。
 * 正式地讲，提莫在 t 发起发起攻击意味着艾希在时间区间 [t, t + duration - 1]（含 t 和 t + duration - 1）处于中毒状态。
 * 如果提莫在中毒影响结束 前 再次攻击，中毒状态计时器将会 重置 ，在新的攻击之后，中毒影响将会在 duration 秒后结束。
 * 给你一个 非递减 的整数数组 timeSeries ，其中 timeSeries[i] 表示提莫在 timeSeries[i] 秒时对艾希发起攻击，以及一个表示中毒持续时间的整数 duration 。
 * 返回艾希处于中毒状态的 总 秒数。
 *
 * @see <a href="https://leetcode.cn/problems/teemo-attacking">提莫攻击</a>
 */
fun findPoisonedDuration(timeSeries: IntArray, duration: Int): Int {
    var times = duration
    for (i in 1 until timeSeries.size) {
        if (timeSeries[i] - timeSeries[i - 1] >= duration) {
            times += duration
        } else {
            times += timeSeries[i] - timeSeries[i - 1]
        }
     }
    return times
}

/**
 * 下一个更大元素 I
 *
 * nums1中数字x的 下一个更大元素 是指x在nums2 中对应位置 右侧 的 第一个 比x大的元素。
 * 给你两个 没有重复元素 的数组nums1 和nums2 ，下标从 0 开始计数，其中 nums1 是 nums2 的子集。
 * 对于每个 0 <= i < nums1.length ，找出满足 nums1[i] == nums2[j] 的下标 j ，并且在 nums2 确定 nums2[j] 的 下一个更大元素 。如果不存在下一个更大元素，那么本次查询的答案是 -1 。
 * 返回一个长度为nums1.length 的数组 ans 作为答案，满足 ans[i] 是如上所述的 下一个更大元素 。
 *
 * @see <a href="https://leetcode.cn/problems/next-greater-element-i">下一个更大元素 I</a>
 */
fun nextGreaterElement(nums1: IntArray, nums2: IntArray): IntArray {
    val stack = Stack<Int>()
    val map = HashMap<Int, Int>()
    val result = IntArray(nums1.size)

    for (num in nums2) {
        while (!stack.isEmpty() && stack.peek() < num) {
            map.put(stack.pop(), num)
        }
        stack.push(num)
    }

    for (i in 0 until nums1.size) {
        result[i] = map.getOrDefault(nums1[i], -1)
    }

    return result
}

/**
 * 多米诺和托米诺平铺
 *
 * 有两种形状的瓷砖：一种是 2 x 1 的多米诺形，另一种是形如 "L" 的托米诺形。两种形状都可以旋转。
 *
 * 给定整数 n ，返回可以平铺 2 x n 的面板的方法的数量。返回对 10^9 + 7取模的值。
 * 平铺指的是每个正方形都必须有瓷砖覆盖。两个平铺不同，当且仅当面板上有四个方向上的相邻单元中的两个，使得恰好有一个平铺有一个瓷砖占据两个正方形。
 *
 * @see <a href="https://leetcode.cn/problems/domino-and-tromino-tiling/">多米诺和托米诺平铺</a>
 */
fun numTilings(n: Int): Int {
    // f(n) = 2 * f(n-1) + f(n-3)
    if (n == 1) return 1
    if (n == 2) return 2
    if (n == 3) return 5
    var n1 = 5
    var n2 = 2
    var n3 = 1
    var temp = 0
    for (i in 4 .. n) {
        temp = (2 * n1) % 1000000007 + n3 % 1000000007
        n3 = n2
        n2 = n1
        n1 = temp % 1000000007
    }
    return n1
}