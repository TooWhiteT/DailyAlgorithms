import java.util.Queue

/**
 * 最接近目标价格的甜点成本
 *
 * 你打算做甜点，现在需要购买配料。目前共有 n 种冰激凌基料和 m 种配料可供选购。而制作甜点需要遵循以下几条规则：
 *  必须选择 一种 冰激凌基料。
 *  可以添加 一种或多种 配料，也可以不添加任何配料。
 *  每种类型的配料 最多两份 。
 * 给你以下三个输入：
 *  baseCosts ，一个长度为 n 的整数数组，其中每个 baseCosts[i] 表示第 i 种冰激凌基料的价格。\
 *  toppingCosts，一个长度为 m 的整数数组，其中每个 toppingCosts[i] 表示 一份 第 i 种冰激凌配料的价格。
 *  target ，一个整数，表示你制作甜点的目标价格。
 * 你希望自己做的甜点总成本尽可能接近目标价格 target 。
 * 返回最接近 target 的甜点成本。如果有多种方案，返回 成本相对较低 的一种。
 *
 * @see <a href="https://leetcode.cn/problems/closest-dessert-cost">最接近目标价格的甜点成本</a>
 */
//初始化一个变量表示答案，取一个和答案无关的值就可
private var best: Int = 1e9.toInt()
private var target = 0

fun closestCost(baseCosts: IntArray, toppingCosts: IntArray, t: Int): Int {
    target = t
    for (i in 0 until baseCosts.size) {
        dfs(toppingCosts, 0, baseCosts[i])
    }
    return best
}

//暴搜，数据量很小，枚举每个选择就可以
private fun dfs(arr: IntArray, idx: Int, total: Int) {
    val sign: Int = Math.abs(best - target) - Math.abs(total - target)
    if (sign > 0 || sign == 0 && total < best) {
        best = total
    }

    //剪支以下，如果当前的成本已经大于total，没有必要再往后就行选择
    //或者已经遍历完，结束
    if (total >= target || idx == arr.size) {
        return
    }

    //每个配料可以选0，1，2份
    for (k in 0..2) {
        dfs(arr, idx + 1, total + arr[idx] * k)
    }
}

/**
 * 按奇偶排序数组 II
 *
 * 给定一个非负整数数组 nums，nums 中一半整数是 奇数 ，一半整数是 偶数 。
 * 对数组进行排序，以便当 nums[i] 为奇数时，i 也是 奇数 ；当 nums[i] 为偶数时， i 也是 偶数 。
 * 你可以返回 任何满足上述条件的数组作为答案 。
 *
 * @see <a href="https://leetcode.cn/problems/sort-array-by-parity-ii">按奇偶排序数组 II</a>
 */
fun sortArrayByParityII(nums: IntArray): IntArray {
    val res = IntArray(nums.size)
    var left = 1
    var right = 0
    for (i in 0 until nums.size) {
        if (nums[i] % 2 == 0) {
            res[right] = nums[i]
            right += 2
        } else {
            res[left] = nums[i]
            left += 2
        }
    }
    return res
}

/**
 * 长按键入
 *
 * 你的朋友正在使用键盘输入他的名字name。偶尔，在键入字符c时，按键可能会被长按，而字符可能被输入 1 次或多次。
 * 你将会检查键盘输入的字符typed。如果它对应的可能是你的朋友的名字（其中一些字符可能被长按），那么就返回True。
 *
 * @see <a href="https://leetcode.cn/problems/long-pressed-name">长按键入</a>
 */
fun isLongPressedName(name: String, typed: String): Boolean {
    val m = name.length
    val n = typed.length
    val dp = Array(m + 1) { BooleanArray(n + 1) }
    dp[0][0] = true
    for (i in 1..m) {
        for (j in 1..n) {
            if (name[i - 1] == typed[j - 1]) {
                dp[i][j] = dp[i - 1][j - 1] || dp[i][j - 1]
            } else {
                dp[i][j] = false
            }
        }
    }
    return dp[m][n]
}

/**
 * 独特的电子邮件地址
 *
 * 每个 有效电子邮件地址 都由一个 本地名 和一个 域名 组成，以 '@' 符号分隔。除小写字母之外，电子邮件地址还可以含有一个或多个 '.' 或 '+' 。
 * 例如，在 alice@leetcode.com中， alice 是 本地名 ，而 leetcode.com 是 域名 。
 *
 * 如果在电子邮件地址的 本地名 部分中的某些字符之间添加句点（'.'），则发往那里的邮件将会转发到本地名中没有点的同一地址。请注意，此规则 不适用于域名 。
 * 例如，"alice.z@leetcode.com” 和 “alicez@leetcode.com” 会转发到同一电子邮件地址。
 * 如果在 本地名 中添加加号（'+'），则会忽略第一个加号后面的所有内容。这允许过滤某些电子邮件。同样，此规则 不适用于域名 。
 * 例如 m.y+name@email.com 将转发到 my@email.com。
 * 可以同时使用这两个规则。
 * 给你一个字符串数组 emails，我们会向每个 emails[i] 发送一封电子邮件。返回实际收到邮件的不同地址数目。
 *
 * @see <a href="https://leetcode.cn/problems/unique-email-addresses">独特的电子邮件地址</a>
 */
fun numUniqueEmails(emails: Array<String>): Int {
    val set = HashSet<String> ()
    for (s in emails) {
        val email = s.split("@".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        email[0] = email[0].replace("\\.".toRegex(), "")
        email[0] = email[0].split("\\+".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[0]
        set.add(email[0] + "@" + email[1])
    }
    return set.size
}

/**
 * 最近的请求次数
 *
 * 写一个 RecentCounter 类来计算特定时间范围内最近的请求。
 * 请你实现 RecentCounter 类：
 *  RecentCounter() 初始化计数器，请求数为 0 。
 *  int ping(int t) 在时间 t 添加一个新请求，其中 t 表示以毫秒为单位的某个时间，并返回过去 3000 毫秒内发生的所有请求数（包括新请求）。确切地说，返回在 [t-3000, t] 内发生的请求数。
 *
 * 保证 每次对 ping 的调用都使用比之前更大的 t 值。
 *
 * @see <a href="https://leetcode.cn/problems/number-of-recent-calls">最近的请求次数</a>
 */
class RecentCounter() {
    private val records = IntArray(10010)
    var head: Int = 0
    var tail: Int = 0

    fun ping(t: Int): Int {
        records[tail++] = t
        while (head < tail && records[head] < t - 3000) {
            head++
        }
        return tail.minus(head)
    }

}