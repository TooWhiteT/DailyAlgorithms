
import java.util.*


/**
 * 判断句子是否为全字母句
 *
 * 全字母句 指包含英语字母表中每个字母至少一次的句子。
 * 给你一个仅由小写英文字母组成的字符串 sentence ，请你判断 sentence 是否为 全字母句 。
 * 如果是，返回 true ；否则，返回 false 。
 *
 * @see <a href="https://leetcode.cn/problems/check-if-the-sentence-is-pangram">判断句子是否为全字母句</a>
 */
fun checkIfPangram(sentence: String): Boolean {
    val array = IntArray(26)
    for (c in sentence) {
        array[c - 'a']++
    }
    for (i in array) {
        if (i == 0) return false
    }
    return true
}

/**
 * 等价多米诺骨牌对的数量
 *
 * 给你一个由一些多米诺骨牌组成的列表 dominoes。
 * 如果其中某一张多米诺骨牌可以通过旋转 0 度或 180 度得到另一张多米诺骨牌，我们就认为这两张牌是等价的。
 * 形式上，dominoes[i] = [a, b] 和 dominoes[j] = [c, d] 等价的前提是 a==c 且 b==d，或是 a==d 且 b==c。
 * 在 0 <= i < j < dominoes.length 的前提下，找出满足 dominoes[i] 和 dominoes[j] 等价的骨牌对 (i, j) 的数量。
 *
 * @see <a href="https://leetcode.cn/problems/number-of-equivalent-domino-pairs">等价多米诺骨牌对的数量</a>
 */
fun numEquivDominoPairs(dominoes: Array<IntArray>): Int {
    var ans = 0
    val cp = IntArray(100)
    for (arr in dominoes) {
        Arrays.sort(arr)
        ans += cp[arr[0] * 10 + arr[1]]++
    }
    return ans
}

/**
 * 第 N 个泰波那契数
 *
 * 泰波那契序列 Tn 定义如下：
 * T0 = 0, T1 = 1, T2 = 1, 且在 n >= 0 的条件下 Tn+3 = Tn + Tn+1 + Tn+2
 * 给你整数 n，请返回第 n 个泰波那契数 Tn 的值。
 *
 * @see <a href="https://leetcode.cn/problems/n-th-tribonacci-number">第 N 个泰波那契数</a>
 */
fun tribonacci(n: Int): Int {
    val temp = intArrayOf(0,1,1)
    for (i in 3 .. n) {
        temp[i % 3] = temp[0] + temp[1] + temp[2]
    }
    return temp[n % 3]
}

/**
 * 一年中的第几天
 *
 * 给你一个字符串 date ，按 YYYY-MM-DD 格式表示一个 现行公元纪年法 日期。返回该日期是当年的第几天。
 *
 * @see <a href="https://leetcode.cn/problems/day-of-the-year/">一年中的第几天</a>
 */
fun dayOfYear(date: String): Int {
    val y = Integer.valueOf(date.substring(0, 4))
    val m = Integer.valueOf(date.substring(5, 7))
    val d = Integer.valueOf(date.substring(8, 10))
    var res = 0
    if (m == 1) {
        res = d
    } else if (m == 2) {
        res = 31 + d
    } else if (m == 3) {
        res = 31 + 28 + d
    } else if (m == 4) {
        res = 31 + 28 + 31 + d
    } else if (m == 5) {
        res = 31 + 28 + 31 + 30 + d
    } else if (m == 6) {
        res = 31 + 28 + 31 + 30 + 31 + d
    } else if (m == 7) {
        res = 31 + 28 + 31 + 30 + 31 + 30 + d
    } else if (m == 8) {
        res = 31 + 28 + 31 + 30 + 31 + 30 + 31 + d
    } else if (m == 9) {
        res = 31 + 28 + 31 + 30 + 31 + 30 + 31 + 31 + d
    } else if (m == 10) {
        res = 31 + 28 + 31 + 30 + 31 + 30 + 31 + 31 + 30 + d
    } else if (m == 11) {
        res = 31 + 28 + 31 + 30 + 31 + 30 + 31 + 31 + 30 + 31 + d
    } else if (m == 12) {
        res = 31 + 28 + 31 + 30 + 31 + 30 + 31 + 31 + 30 + 31 + 30 + d
    }
    if (m >= 3) {
        if (y % 400 == 0 || (y % 4 == 0 && y % 100 != 0)) {
            res += 1
        }
    }
    return res
}

/**
 * 拼写单词
 *
 * 给你一份『词汇表』（字符串数组） words 和一张『字母表』（字符串） chars。
 * 假如你可以用 chars 中的『字母』（字符）拼写出 words 中的某个『单词』（字符串），那么我们就认为你掌握了这个单词。
 * 注意：每次拼写（指拼写词汇表中的一个单词）时，chars 中的每个字母都只能用一次。
 * 返回词汇表 words 中你掌握的所有单词的 长度之和。
 *
 * @see <a href="https://leetcode.cn/problems/find-words-that-can-be-formed-by-characters">拼写单词</a>
 */
fun countCharacters(words: Array<String>, chars: String): Int {
    val hash = IntArray(26)
    for (ch in chars) {
        hash[ch - 'a'] += 1
    }
    val map = IntArray(26)
    var len = 0
    for (word in words) {
        Arrays.fill(map, 0)
        var flag = true
        for (ch in word) {
            map[ch - 'a']++
            if (map[ch - 'a'] > hash[ch - 'a']) flag = false
        }
        len += if (flag) word.length else 0
    }
    return len
}