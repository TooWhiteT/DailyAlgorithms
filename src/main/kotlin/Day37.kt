import java.util.*


/**
 * 字符串中不同整数的数目
 *
 * 给你一个字符串 word ，该字符串由数字和小写英文字母组成。
 * 请你用空格替换每个不是数字的字符。例如，"a123bc34d8ef34" 将会变成 " 123 34 8 34" 。
 * 注意，剩下的这些整数为（相邻彼此至少有一个空格隔开）："123"、"34"、"8" 和 "34" 。
 * 返回对 word 完成替换后形成的 不同 整数的数目。
 * 只有当两个整数的 不含前导零 的十进制表示不同， 才认为这两个整数也不同。
 *
 * @see <a href="https://leetcode.cn/problems/number-of-different-integers-in-a-string">字符串中不同整数的数目</a>
 */
fun numDifferentIntegers(word: String): Int {
    val set = HashSet<String>()
    var i = 0
    while (i < word.length) {
        val c = word[i]
        if (c.hashCode() < 58) {
            var j = i
            while (j < word.length && word[j].hashCode() < 58) {
                j++
            }
            while (i < j && word[i].hashCode() == 48) {
                i++
            }
            set.add(word.substring(i, j))
            i = j
        }
        i++
    }
    return set.size
}

/**
 * 验证外星语词典
 *
 * 某种外星语也使用英文小写字母，但可能顺序 order 不同。字母表的顺序（order）是一些小写字母的排列。
 * 给定一组用外星语书写的单词 words，以及其字母表的顺序 order，只有当给定的单词在这种外星语中按字典序排列时，返回 true；否则，返回 false。
 *
 * @see <a href="https://leetcode.cn/problems/verifying-an-alien-dictionary">验证外星语词典</a>
 */
fun isAlienSorted(words: Array<String>, order: String): Boolean {
    for (i in 0 until words.size - 1) {
        val n: Int = words[i].length
        val m: Int = words[i + 1].length
        val min = if (n < m) n else m
        var j = 0
        while (j < min) {
            // 判断是否满足第 1，2 种情况
            if (order.indexOf(words[i][j]) > order.indexOf(words[i + 1][j])) {
                return false
            }
            if (order.indexOf(words[i][j]) < order.indexOf(words[i + 1][j])) {
                break
            }
            j++
        }

        // 判断是否满足第 3 种情况
        if (j == min && n > m) {
            return false
        }
    }

    return true
}

/**
 * 在长度 2N 的数组中找出重复 N 次的元素
 *
 * 给你一个整数数组 nums ，该数组具有以下属性：
 *  nums.length == 2 * n.
 *  nums 包含 n + 1 个 不同的 元素
 *  nums 中恰有一个元素重复 n 次
 * 找出并返回重复了 n 次的那个元素。
 *
 * @see <a href="https://leetcode.cn/problems/n-repeated-element-in-size-2n-array">在长度 2N 的数组中找出重复 N 次的元素</a>
 */
fun repeatedNTimes(nums: IntArray): Int {
    val r = java.util.Random()
    val len: Int = nums.size
    var a: Int
    var b: Int
    while (true) {
        a = r.nextInt(len)
        b = r.nextInt(len)
        if (a != b && nums[a] == nums[b]) {
            return nums[a]
        }
    }
}

/**
 * 单值二叉树
 *
 * 如果二叉树每个节点都具有相同的值，那么该二叉树就是单值二叉树。
 * 只有给定的树是单值二叉树时，才返回 true；否则返回 false。
 *
 * @see <a href="https://leetcode.cn/problems/univalued-binary-tree/">单值二叉树</a>
 */
fun isUnivalTree(root: TreeNode?): Boolean {
    if (root == null) {
        return true
    }
    if (root.left != null && root.left!!.`val` != root.`val`) {
        return false
    }
    return if (root.right != null && root.right!!.`val` != root.`val`) {
        false
    } else {
        isUnivalTree(root.right) && isUnivalTree(root.left)
    }
}

/**
 * 三角形的最大周长
 *
 * 给定由一些正数（代表长度）组成的数组 nums ，返回 由其中三个长度组成的、面积不为零的三角形的最大周长 。如果不能形成任何面积不为零的三角形，返回 0。
 *
 * @see <a href="https://leetcode.cn/problems/largest-perimeter-triangle/">三角形的最大周长</a>
 */
fun largestPerimeter(nums: IntArray): Int {
    Arrays.sort(nums)
    for (i in nums.size - 1 downTo 2) {
        val a = nums[i]
        val b = nums[i - 1]
        val c = nums[i - 2]
        if (a < b + c) {
            return a + b + c
        }
    }
    return 0
}