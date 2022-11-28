/**
 * 最大三角形面积
 *
 * 给定包含多个点的集合，从其中取三个点组成三角形，返回能组成的最大三角形的面积。
 *
 * @see <a href="https://leetcode.cn/problems/largest-triangle-area/">最大三角形面积</a>
 */
fun largestTriangleArea(points: Array<IntArray>): Double {
    val n = points.size
    var res = 0.0
    for (i in 0 until n - 2) {
        val a = points[i]
        for (j in i + 1 until n - 1) {
            val b = points[j]
            for (k in j + 1 until n) {
                val c = points[k]
                val xAB = b[0] - a[0]
                val yAB = b[1] - a[1]
                val xAC = c[0] - a[0]
                val yAC = c[1] - a[1]
                if (Math.abs(xAB * yAC - xAC * yAB) / 2.0 > res) {
                    res = Math.abs(xAB * yAC - xAC * yAB) / 2.0
                }
            }
        }
    }
    return res
}

/**
 * 最常见的单词
 *
 * 给定一个段落 (paragraph) 和一个禁用单词列表 (banned)。返回出现次数最多，同时不在禁用列表中的单词。
 *
 * 题目保证至少有一个词不在禁用列表中，而且答案唯一。
 *
 * 禁用列表中的单词用小写字母表示，不含标点符号。段落中的单词不区分大小写。答案都是小写字母。
 *
 * @see <a href="https://leetcode.cn/problems/most-common-word/">最常见的单词</a>
 */
fun mostCommonWord(paragraph: String, banned: Array<String>): String {
    var paragraph_str = paragraph.toLowerCase()
    paragraph_str = paragraph_str.replace('!', ' ')
        .replace('?', ' ')
        .replace('\'', ' ')
        .replace(',', ' ')
        .replace(';', ' ')
        .replace('.', ' ').trim()

    val s = paragraph_str.split(" ")
    val map1 = HashMap<String, Int>()
    val map2 = HashMap<String, Int>()
    //被禁用的单词放入map1
    for (str in banned) {
        map1[str] = 1
    }
    //把没有禁用的单词放入map2
    for (str in s) {
        if (!map1.containsKey(str) && str != "") {
            map2[str] = map2.getOrDefault(str, 0) + 1
        }
    }
    //在map2找出出现最多的单词
    var max = -1
    var res = ""
    for (str in map2.keys) {
        if (map2[str]!! > max) {
            max = map2[str]!!
            res = str
        }
    }
    return res
}

/**
 * 字符的最短距离
 *
 * 给你一个字符串 s 和一个字符 c ，且 c 是 s 中出现过的字符。
 * 返回一个整数数组 answer ，其中 answer.length == s.length 且 answer[i] 是 s 中从下标 i 到离它 最近 的字符 c 的 距离 。
 * 两个下标 i 和 j 之间的 距离 为 abs(i - j) ，其中 abs 是绝对值函数。
 *
 * @see <a href="https://leetcode.cn/problems/shortest-distance-to-a-character">字符的最短距离</a>
 */
fun shortestToChar(s: String, c: Char): IntArray {
    val res = IntArray(s.length){ Int.MAX_VALUE }
    val n = s.length
    var left = s.length
    var right = -1
    for (i in 0 until n) {
        if (s[i] == c) left = i
        if (s[n - 1 - i] == c) right = n - 1 - i
        if (i >= left) res[i] = Math.min(res[i], i - left)
        if (n - 1 - i <= right) res[n - 1 - i] = Math.min(res[n - 1 -i], right - (n - 1 - i))
    }
    return res
}

/**
 * 山羊拉丁文
 *
 * 给你一个由若干单词组成的句子sentence ，单词间由空格分隔。每个单词仅由大写和小写英文字母组成。
 * 请你将句子转换为 “山羊拉丁文（Goat Latin）”（一种类似于 猪拉丁文 - Pig Latin 的虚构语言）。山羊拉丁文的规则如下：
 * 如果单词以元音开头（'a', 'e', 'i', 'o', 'u'），在单词后添加"ma"。
 * 例如，单词 "apple" 变为 "applema" 。
 * 如果单词以辅音字母开头（即，非元音字母），移除第一个字符并将它放到末尾，之后再添加"ma"。
 * 例如，单词 "goat" 变为 "oatgma" 。
 * 根据单词在句子中的索引，在单词最后添加与索引相同数量的字母'a'，索引从 1 开始。
 * 例如，在第一个单词后添加 "a" ，在第二个单词后添加 "aa" ，以此类推。
 * 返回将 sentence 转换为山羊拉丁文后的句子。
 *
 * @see <a href="https://leetcode.cn/problems/goat-latin/">山羊拉丁文</a>
 */
fun toGoatLatin(sentence: String): String {
    val strs = sentence.split(" ")
    val sb = StringBuilder()
    val vowel = "aeiouAEIOU"
    for (i in strs.indices) {
        sb.append(if (vowel.indexOf(strs[i][0]) == -1) strs[i].substring(1) + strs[i][0] + "ma" else strs[i] + "ma")
        for (j in 0 until i + 1) sb.append('a')
        sb.append(' ')
    }
    return sb.toString().trim { it <= ' ' }
}

/**
 * 最大平均值和的分组
 *
 * 给定数组 nums 和一个整数 k 。我们将给定的数组nums分成最多k个相邻的非空子数组 。
 * 分数 由每个子数组内的平均值的总和构成。
 * 注意我们必须使用 nums 数组中的每一个数进行分组，并且分数不一定需要是整数。
 * 返回我们所能得到的最大 分数 是多少。答案误差在10^-6内被视为是正确的。
 *
 * @see <a href="https://leetcode.cn/problems/largest-sum-of-averages/">最大平均值和的分组</a>
 */
fun largestSumOfAverages(A: IntArray, K: Int): Double {
    /**
    dp[i][k]表示前i个数构成k个子数组时的最大平均值, 那么对于所有 0 <= j <= i-1
    dp[i][k] = Math.max(dp[i][k], dp[j][k-1] + (A[j+1] + ... + A[i+1]) / (i-j))
     **/
    val dp = Array(A.size + 1) {
        DoubleArray(K + 1)
    }
    // 额外记录一个sum数组保存到前i个数的和, 便于计算(A[j+1] + ... + A[i+1]) / (i-j)
    val sum = DoubleArray(A.size + 1)

    for (i in 1..A.size) {
        sum[i] = sum[i - 1] + A.get(i - 1)
        dp[i][1] = sum[i] / i
    }
    for (i in 1..A.size) {
        for (k in 2..K) {
            for (j in 0 until i) {
                dp[i][k] = Math.max(dp[i][k], dp[j][k - 1] + (sum[i] - sum[j]) / (i - j))
            }
        }
    }

    return dp[A.size][K]
}