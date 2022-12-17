/**
 * 通过连接另一个数组的子数组得到一个数组
 *
 * 给你一个长度为 n的二维整数数组groups，同时给你一个整数数组nums。
 * 你是否可以从 nums中选出 n个 不相交 的子数组，使得第 i个子数组与 groups[i]（下标从 0开始）完全相同，且如果i > 0，那么第(i-1)个子数组在 nums中出现的位置在第 i个子数组前面。（也就是说，这些子数组在 nums中出现的顺序需要与 groups 顺序相同）
 * 如果你可以找出这样的 n 个子数组，请你返回true ，否则返回false。
 * 如果不存在下标为 k的元素 nums[k]属于不止一个子数组，就称这些子数组是 不相交 的。子数组指的是原数组中连续元素组成的一个序列。
 *
 * @see <a href="https://leetcode.cn/problems/form-array-by-concatenating-subarrays-of-another-array">通过连接另一个数组的子数组得到一个数组</a>
 */
fun canChoose(groups: Array<IntArray>, nums: IntArray): Boolean {

}

/**
 * 找出井字棋的获胜者
 *
 * A 和B在一个3x3的网格上玩井字棋。
 * 井字棋游戏的规则如下：
 *  玩家轮流将棋子放在空方格 (" ") 上。
 *  第一个玩家 A 总是用"X" 作为棋子，而第二个玩家 B 总是用 "O" 作为棋子。
 *  "X" 和 "O" 只能放在空方格中，而不能放在已经被占用的方格上。
 *  只要有 3 个相同的（非空）棋子排成一条直线（行、列、对角线）时，游戏结束。
 *  如果所有方块都放满棋子（不为空），游戏也会结束。
 *  游戏结束后，棋子无法再进行任何移动。
 *
 * 给你一个数组 moves，其中每个元素是大小为 2 的另一个数组（元素分别对应网格的行和列），它按照 A 和 B 的行动顺序（先 A 后 B）记录了两人各自的棋子位置。
 * 如果游戏存在获胜者（A 或 B），就返回该游戏的获胜者；如果游戏以平局结束，则返回 "Draw"；如果仍会有行动（游戏未结束），则返回 "Pending"。
 * 你可以假设moves都 有效（遵循井字棋规则），网格最初是空的，A 将先行动。
 *
 * @see <a href="https://leetcode.cn/problems/find-winner-on-a-tic-tac-toe-game">找出井字棋的获胜者</a>
 */
fun tictactoe(moves: Array<IntArray>): String {
    // 同一行
    var rowCounter = 0
    // 同一列
    var colCounter = 0
    // 主对角线
    var mainCounter = 0
    // 副对角线
    var subCounter = 0
    val key = (moves.size - 1) % 2
    val last = moves[moves.size - 1]
    val he = last[0] + last[1]
    val cha = last[0] - last[1]
    for (i in 0 until moves.size) {
        if (i % 2 == key) {
            val move = moves[i]
            if (move[0] == last[0]) {
                rowCounter++
            }
            if (move[1] == last[1]) {
                colCounter++
            }
            if (move[0] + move[1] == he) {
                subCounter++
            }
            if (move[0] - move[1] == cha) {
                mainCounter++
            }
        }
    }
    if ((rowCounter >= 3) || (colCounter >= 3) || (mainCounter >= 3) || (subCounter >= 3)) {
        return if (key == 0) "A" else "B"
    }
    // 格子未填满并且未分出胜负的情况
    return if (moves.size < 9) { "Pending" } else "Draw"
}

/**
 * 整数的各位积和之差
 *
 * 给你一个整数 n，请你帮忙计算并返回该整数「各位数字之积」与「各位数字之和」的差。
 *
 * @see <a href="https://leetcode.cn/problems/subtract-the-product-and-sum-of-digits-of-an-integer/">整数的各位积和之差</a>
 */
fun subtractProductAndSum(n: Int): Int {
    var muti = 1
    var sum = 0
    var n = n
    while (n !== 0) {
        muti *= n % 10
        sum += n % 10
        n /= 10
    }
    return muti - sum
}

/**
 * 有序数组中出现次数超过25%的元素
 *
 * 给你一个非递减的 有序 整数数组，已知这个数组中恰好有一个整数，它的出现次数超过数组元素总数的 25%。
 * 请你找到并返回这个整数
 *
 * @see <a href="https://leetcode.cn/problems/element-appearing-more-than-25-in-sorted-array/">有序数组中出现次数超过25%的元素</a>
 */
fun findSpecialInteger(arr: IntArray): Int {
    val len = arr.size / 4
    for (i in 0 until arr.size - len) {
        if (arr[i] == arr[i + len]) return arr[i]
    }
    return arr[0]
}

/**
 * 二进制链表转整数
 *
 * 给你一个单链表的引用结点head。链表中每个结点的值不是 0 就是 1。已知此链表是一个整数数字的二进制表示形式。
 * 请你返回该链表所表示数字的 十进制值 。
 *
 * @see <a href="https://leetcode.cn/problems/convert-binary-number-in-a-linked-list-to-integer">二进制链表转整数</a>
 */
fun getDecimalValue(head: ListNode?): Int {
    var head = head
    if (head == null) return 0
    var ans = 0
    while (head != null) {
        ans = (ans shl 1) + head.`val`
        head = head.next
    }
    return ans
}