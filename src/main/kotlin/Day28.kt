/**
 * 写字符串需要的行数
 *
 * 我们要把给定的字符串 S 从左到右写到每一行上，每一行的最大宽度为100个单位，如果我们在写某个字母的时候会使这行超过了100 个单位，那么我们应该把这个字母写到下一行。我们给定了一个数组widths，这个数组 widths[0] 代表 'a' 需要的单位，widths[1] 代表 'b' 需要的单位，...，widths[25] 代表 'z' 需要的单位。
 * 现在回答两个问题：至少多少行能放下S，以及最后一行使用的宽度是多少个单位？将你的答案作为长度为2的整数列表返回。
 *
 * @see <a href="https://leetcode.cn/problems/number-of-lines-to-write-string">写字符串需要的行数</a>
 */
fun numberOfLines(widths: IntArray, s: String): IntArray {
    val ans = IntArray(2)
    ans[0] = 1
    for (arr in s.toCharArray()) {
        ans[1] += widths[arr.hashCode() - 'a'.hashCode()]
        if (ans[1] > 100) {
            ans[1] = widths[arr.hashCode() - 'a'.hashCode()]
            ans[0]++
        }
    }
    return ans
}

/**
 * 唯一摩尔斯密码词
 *
 * 国际摩尔斯密码定义一种标准编码方式，将每个字母对应于一个由一系列点和短线组成的字符串，比如:
 * 'a' 对应 ".-" ，
 * 'b' 对应 "-..." ，
 * c' 对应 "-.-." ，以此类推。
 * 为了方便，所有 26 个英文字母的摩尔斯密码表如下：
 * [".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."]
 *
 * 给你一个字符串数组 words ，每个单词可以写成每个字母对应摩尔斯密码的组合。
 * 例如，"cab" 可以写成 "-.-..--..." ，(即 "-.-." + ".-" + "-..." 字符串的结合)。我们将这样一个连接过程称作 单词翻译 。
 * 对 words 中所有单词进行单词翻译，返回不同 单词翻译 的数量。
 *
 * @see <a href="https://leetcode.cn/problems/unique-morse-code-words/">唯一摩尔斯密码词</a>
 */
fun uniqueMorseRepresentations(words: Array<String>): Int {
    val map = arrayOf(
        ".-",
        "-...",
        "-.-.",
        "-..",
        ".",
        "..-.",
        "--.",
        "....",
        "..",
        ".---",
        "-.-",
        ".-..",
        "--",
        "-.",
        "---",
        ".--.",
        "--.-",
        ".-.",
        "...",
        "-",
        "..-",
        "...-",
        ".--",
        "-..-",
        "-.--",
        "--.."
    )
    val set = HashSet<String>()
    for (s in words) {
        val sb = StringBuilder()
        for (c in s.toCharArray()) {
            sb.append(map[c.hashCode() - 'a'.hashCode()])
        }
        set.add(sb.toString())
    }
    return set.size
}

/**
 * 旋转字符串
 *
 * 给定两个字符串, s和goal。如果在若干次旋转操作之后，s能变成goal，那么返回true。
 * s 的 旋转操作 就是将 s 最左边的字符移动到最右边。
 * 例如, 若 s = 'abcde'，在旋转一次之后结果就是'bcdea'。
 *
 * @see <a href="https://leetcode.cn/problems/rotate-string/">旋转字符串</a>
 */
fun rotateString(A: String, B: String): Boolean {
    return A.length == B.length && (A + A).contains(B)
}


/**
 * 二叉搜索树节点最小距离
 *
 * 给你一个二叉搜索树的根节点 root ，返回 树中任意两不同节点值之间的最小差值 。
 * 差值是一个正数，其数值等于两值之差的绝对值。
 *
 * @see <a href="https://leetcode.cn/problems/minimum-distance-between-bst-nodes/">二叉搜索树节点最小距离</a>
 */
private var pre_28 = -(1e6.toInt())
private var ans_28 = 0x3f3f3f3f
fun minDiffInBST(root: TreeNode?): Int {
    if (root != null) {
        minDiffInBST(root.left)
        ans_28 = Math.min(ans_28, root.`val` - pre_28)
        pre_28 = root.`val`
        minDiffInBST(root.right)
    }
    return ans_28
}

/**
 * 检查数组是否经排序和轮转得到
 *
 * 给你一个数组 nums 。nums 的源数组中，所有元素与 nums 相同，但按非递减顺序排列。
 * 如果 nums 能够由源数组轮转若干位置（包括 0 个位置）得到，则返回 true ；否则，返回 false 。
 * 源数组中可能存在 重复项 。
 * 注意：我们称数组 A 在轮转 x 个位置后得到长度相同的数组 B ，当它们满足 A[i] == B[(i+x) % A.length] ，其中 % 为取余运算。
 *
 * @see <a href="https://leetcode.cn/problems/check-if-array-is-sorted-and-rotated">检查数组是否经排序和轮转得到</a>
 */
fun check(nums: IntArray): Boolean {
    var count = 0
    for (i in 0 until nums.size) {
        if (nums[i] > nums[(i + 1) % nums.size]) {
            count++
        }
    }
    return count <= 1
}