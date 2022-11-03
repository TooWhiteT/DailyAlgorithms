/**
 * 快乐数
 *
 * 编写一个算法来判断一个数 n 是不是快乐数。
 * 「快乐数」定义为：
 *
 *      对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和。
 *      然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。
 *      如果这个过程 结果为1，那么这个数就是快乐数。
 *
 * 如果 n 是 快乐数 就返回 true ；不是，则返回 false 。
 *
 * @see <a href="https://leetcode.cn/problems/happy-number">快乐数</a>
 */
fun isHappy(n: Int): Boolean {
    var fast = n
    var slow = n
    do {
        slow = squareSum(slow)
        fast = squareSum(fast)
        fast = squareSum(fast)
    } while (slow != fast)
    return fast == 1
}

fun squareSum(m: Int): Int {
    var m = m
    var squaresum = 0
    while (m != 0) {
        squaresum += m % 10 * (m % 10)
        m /= 10
    }
    return squaresum
}

/**
 * 移除链表元素
 *
 * 给你一个链表的头节点 head 和一个整数 val ，请你删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点
 *
 * @see <a href="https://leetcode.cn/problems/remove-linked-list-elements/">移除链表元素</a>
 */
fun removeElements(head: ListNode?, `val`: Int): ListNode? {
    val header = ListNode(-1)
    header.next = head
    var cur: ListNode? = header
    while (cur!!.next != null) {
        if (cur.next!!.`val` == `val`) {
            cur.next = cur.next!!.next
        } else {
            cur = cur.next
        }
    }
    return header.next
}

/**
 * 同构字符串
 *
 * 给定两个字符串s和t，判断它们是否是同构的。
 *
 * 如果 s 中的字符可以按某种映射关系替换得到 t，那么这两个字符串是同构的。
 * 每个出现的字符都应当映射到另一个字符，同时不改变字符的顺序。
 * 不同字符不能映射到同一个字符上，相同字符只能映射到同一个字符上，字符可以映射到自己本身。
 *
 * @see <a href="https://leetcode.cn/problems/isomorphic-strings">同构字符串</a>
 */
fun isIsomorphic(s: String, t: String): Boolean {
    if (s.length != t.length) return false
    val map = HashMap<Char, Char>()
    for (i in 0 until s.length) {
        if (!map.containsKey(s[i])) {
            if (map.containsValue(t[i])) {
                return false
            }
            map[s[i]] = t[i]
        } else {
            if (map[s[i]] !== t[i]) {
                return false
            }
        }
    }
    return true
}

/**
 * 存在重复元素 II
 *
 * 给你一个整数数组 nums 和一个整数 k ，判断数组中是否存在两个 不同的索引 i 和 j ，
 * 满足 nums`[i]` == nums`[j]` 且 abs(i - j) <= k 。如果存在，返回 true ；否则，返回 false 。
 *
 * @see <a href="https://leetcode.cn/problems/contains-duplicate-ii/">存在重复元素 II</a>
 */
fun containsNearbyDuplicate(nums: IntArray, k: Int): Boolean {
    val map: MutableMap<Int, Int> = HashMap()
    for (i in 0 until nums.size) {
        if (map.containsKey(nums[i])) {
            if (i - map[nums[i]]!! <= k) return true
        }
        // 覆盖之后的位置和下一个相同值肯定离得更近
        map[nums[i]] = i
    }
    return false
}

/**
 * 最大重复子字符串
 *
 * 给你一个字符串 sequence，如果字符串 word连续重复k次形成的字符串是sequence的一个子字符串，那么单词word 的 重复值为 k 。
 * 单词 word 的 最大重复值是单词word在sequence中最大的重复值。如果word不是sequence的子串，那么重复值k为 0 。
 * 给你一个字符串 sequence和 word，请你返回 最大重复值k 。
 *
 * @see <a href="https://leetcode.cn/problems/maximum-repeating-substring">最大重复子字符串</a>
 */
fun maxRepeating(sequence: String, word: String): Int {
    var count = 0
    var temp_word = word
    var temp = word
    while (sequence.contains(temp_word)) {
        temp_word += temp
        count++
    }
    return count
}