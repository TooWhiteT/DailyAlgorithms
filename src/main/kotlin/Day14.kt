import java.util.*


/**
 * 键盘行
 *
 * 给你一个字符串数组 words ，只返回可以使用在 美式键盘 同一行的字母打印出来的单词。键盘如下图所示。
 * 美式键盘 中：
 * 第一行由字符 "qwertyuiop" 组成。
 * 第二行由字符 "asdfghjkl" 组成。
 * 第三行由字符 "zxcvbnm" 组成。
 *
 * @see <a href="https://leetcode.cn/problems/keyboard-row/">键盘行</a>
 */
fun findWords(words: Array<String>): ArrayList<String> {
    val res = ArrayList<String>()
    val one_line = "qwertyuiop".toCharArray()
    val two_line = "asdfghjkl".toCharArray()
    val three_line = "zxcvbnm".toCharArray()
    for (word in words) {
        val length = word.length
        var line1_length = 0
        var line2_length = 0
        var line3_length = 0
        val temp = word.toLowerCase()
        for (c in temp) {
            when (c) {
                in one_line -> line1_length++
                in two_line -> line2_length++
                in three_line -> line3_length++
            }
        }
        if (line1_length == length || line2_length == length || line3_length == length) {
            res.add(word)
        }
    }
    return res
}

/**
 * 二叉搜索树中的众数
 *
 * 给你一个含重复值的二叉搜索树（BST）的根节点 root ，找出并返回 BST 中的所有 众数（即，出现频率最高的元素）。
 * 如果树中有不止一个众数，可以按 任意顺序 返回。
 * 假定 BST 满足如下定义：
 * 结点左子树中所含节点的值 小于等于 当前节点的值
 * 结点右子树中所含节点的值 大于等于 当前节点的值
 * 左子树和右子树都是二叉搜索树
 *
 * @see <a href="https://leetcode.cn/problems/find-mode-in-binary-search-tree/">二叉搜索树中的众数</a>
 */
var pre = 0
var curTimes = 0
var maxTimes = 0
val list = ArrayList<Int>()
fun findMode(root: TreeNode?): IntArray { // 中序遍历二叉搜索树等于遍历有序数组
    traversal(root)
    val ans = IntArray(list.size)
    for (i in 0 until list.size) {
        ans[i] = list[i]
    }
    return ans
}

//二叉搜索树中序遍历是递增顺序
fun traversal(root: TreeNode?) {
    if (root != null) {
        traversal(root.left)
        //判断当前值与上一个值的关系, 更新 curTimes 和 preVal
        if (pre === root.`val`) {
            curTimes++
        } else {
            pre = root.`val`
            curTimes = 1
        }
        //判断当前数量与最大数量的关系, 更新 list 和 maxTimes
        if (curTimes === maxTimes) {
            list.add(root.`val`)
        } else if (curTimes > maxTimes) {
            list.clear()
            list.add(root.`val`)
            maxTimes = curTimes
        }
        traversal(root.right)
    }
}

/**
 * 七进制数
 *
 * 给定一个整数 num，将其转化为 7 进制，并以字符串形式输出。
 *
 * @see <a href="https://leetcode.cn/problems/base-7/">七进制数</a>
 */
fun convertToBase7(num: Int): String = num.toString(7)

/**
 * 相对名次
 *
 * 给你一个长度为 n 的整数数组 score ，其中 score[i] 是第 i 位运动员在比赛中的得分。所有得分都 互不相同 。
 * 运动员将根据得分 决定名次 ，其中名次第 1 的运动员得分最高，名次第 2 的运动员得分第 2 高，依此类推。运动员的名次决定了他们的获奖情况：
 * 名次第 1 的运动员获金牌 "Gold Medal" 。
 * 名次第 2 的运动员获银牌 "Silver Medal" 。
 * 名次第 3 的运动员获铜牌 "Bronze Medal" 。
 * 从名次第 4 到第 n 的运动员，只能获得他们的名次编号（即，名次第 x 的运动员获得编号 "x"）。
 * 使用长度为 n 的数组 answer 返回获奖，其中 answer[i] 是第 i 位运动员的获奖情况。
 *
 * @see <a href="https://leetcode.cn/problems/relative-ranks">相对名次</a>
 */
fun findRelativeRanks(score: IntArray): Array<String> {
    //  创建一个HashMap将得分与其排名建立联系
    val len: Int = score.size
    // 不去改变原数组，创建一个新的数组进行排序
    val nums = IntArray(len)
    for (i in 0 until len) {
        nums[i] = score[i]
    }
    // 对数组进行排序
    Arrays.sort(nums)
    //  创建一个HashMap建立联系
    val map: MutableMap<Int, Int> = HashMap()
    for (i in 0 until len) {
        map[nums[i]] = len - i
    }

    // 创建一个String[] 数组来保存数据
    val str = Array<String>(len){""}
    // 根据score中的分数生成相应的奖励
    for (i in 0 until len) {
        val mc = map[score[i]]!!
        if (mc == 1) {
            str[i] = "Gold Medal"
        } else if (mc == 2) {
            str[i] = "Silver Medal"
        } else if (mc == 3) {
            str[i] = "Bronze Medal"
        } else {
            str[i] = mc.toString() + ""
        }
    }
    return str
}

/**
 * 自定义字符串排序
 *
 * 给定两个字符串 order 和 s 。order 的所有单词都是 唯一 的，并且以前按照一些自定义的顺序排序。
 * 对 s 的字符进行置换，使其与排序的order相匹配。
 * 更具体地说，如果在order中的字符 x 出现字符 y 之前，那么在排列后的字符串中， x也应该出现在 y 之前。
 * 返回 满足这个性质的 s 的任意排列。
 *
 * @see <a href="https://leetcode.cn/problems/custom-sort-string"></a>
 */
fun customSortString(order: String, s: String): String {
    val sb = StringBuilder()
    // 记录s中每个字符出现的次数
    val rec = IntArray(26)
    for (c in s.toCharArray()) {
        rec[c.hashCode() - 'a'.hashCode()]++
    }
    // 遍历order，如果order中的字符也在T中，则将该字符全部加入
    for (c in order.toCharArray()) {
        if (rec[c.hashCode() - 'a'.hashCode()] != 0) {
            for (i in 0 until rec[c.hashCode() - 'a'.hashCode()]) {
                sb.append(c)
            }
            rec[c.hashCode() - 'a'.hashCode()] = 0
        }
    }
    // 连接s剩下的在order中不存在的字符
    for (i in rec.indices) {
        if (rec[i] != 0) {
            for (j in 0 until rec[i]) {
                sb.append((i + 'a'.hashCode()).toChar())
            }
        }
    }
    return sb.toString()
}