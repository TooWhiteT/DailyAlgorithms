/**
 * 统计一致字符串的数目
 *
 * 给你一个由不同字符组成的字符串 allowed 和一个字符串数组 words。如果一个字符串的每一个字符都在 allowed 中，就称这个字符串是 一致字符串 。
 * 请你返回 words 数组中 一致字符串 的数目。
 *
 * @see <a href="https://leetcode.cn/problems/count-the-number-of-consistent-strings/">统计一致字符串的数目</a>
 */
fun countConsistentStrings(allowed: String, words: Array<String>): Int {
    var count = 0
    val arr = IntArray(26)
    for (i in 0 until allowed.length) {
        arr[allowed[i]-'a']++
    }
    for (w in words) {
        count++
        for (i in 0 until w.length) {
            if (arr[w[i]-'a'] == 0) {
                count--
                break
            }
        }
    }
    return count
}

/**
 * 判断子序列
 *
 * 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
 * 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
 * 进阶：
 * 如果有大量输入的 S，称作 S1, S2, ... , Sk 其中 k >= 10亿，你需要依次检查它们是否为 T 的子序列。在这种情况下，你会怎样改变代码？
 *
 * @see <a href="https://leetcode.cn/problems/is-subsequence/">判断子序列</a>
 */
fun isSubsequence(s: String, t: String): Boolean {
    var index = -1
    for (c in s) {
        index = t.indexOf(c, index + 1)
        if (index == -1) {
            return false
        }
    }
    return true
}

/**
 * 找不同
 *
 * 给定两个字符串 s 和 t ，它们只包含小写字母。
 * 字符串 t 由字符串 s 随机重排，然后在随机位置添加一个字母。
 * 请找出在 t 中被添加的字母。
 *
 * @see <a href="https://leetcode.cn/problems/find-the-difference/">找不同</a>
 */
fun findTheDifference(s: String, t: String): Char {
    var res = 0
    for (i in 0 until t.length) {
        res = res xor t[i].toInt() xor (if (i == s.length) 0 else s[i].toInt())
    }
    return res.toChar()
}

/**
 * 赎金信
 *
 * 给你两个字符串：ransomNote 和 magazine ，判断 ransomNote 能不能由 magazine 里面的字符构成。
 * 如果可以，返回 true ；否则返回 false 。
 * magazine 中的每个字符只能在 ransomNote 中使用一次。
 *
 * @see <a href="https://leetcode.cn/problems/ransom-note/">赎金信</a>
 */
fun canConstruct(ransomNote: String, magazine: String): Boolean {
    // 记录杂志字符串出现的次数
    val arr = IntArray(26)
    var temp: Int
    for (i in 0 until magazine.length) {
        temp = magazine[i] - 'a'
        arr[temp]++
    }
    for (i in 0 until ransomNote.length) {
        temp = ransomNote[i] - 'a'
        // 对于金信中的每一个字符都在数组中查找
        // 找到相应位减一，否则找不到返回false
        if (arr[temp] > 0) {
            arr[temp]--
        } else {
            return false
        }
    }
    return true
}

/**
 * 猜数字大小
 *
 * 猜数字游戏的规则如下：
 *
 * 每轮游戏，我都会从 1 到 n 随机选择一个数字。 请你猜选出的是哪个数字。
 * 如果你猜错了，我会告诉你，你猜测的数字比我选出的数字是大了还是小了。
 * 你可以通过调用一个预先定义好的接口 int guess(int num) 来获取猜测结果，返回值一共有 3 种可能的情况（-1，1 或 0）：
 *
 * -1：我选出的数字比你猜的数字小 pick < num
 * 1：我选出的数字比你猜的数字大 pick > num
 * 0：我选出的数字和你猜的数字一样。恭喜！你猜对了！pick == num
 *
 * 返回我选出的数字。
 *
 * @see <a href="https://leetcode.cn/problems/guess-number-higher-or-lower/">猜数字大小</a>
 */
open class GuessGame {
    open fun guess(n: Int): Int {
        return 0
    }
}
class Demo : GuessGame() {
    fun guessNumber(n: Int): Int {
        var l = 0
        var r = n
        while (true) {
            val num = l + ((r - l) shr 1)
            val res: Int = guess(num)
            if (res == 0) return num else if (res == 1) l = num + 1 else r = num
        }
    }
}
