import java.util.*


/**
 * 到达终点数字
 *
 * 在一根无限长的数轴上，你站在0的位置。终点在target的位置。
 * 你可以做一些数量的移动 numMoves :
 *
 *      每次你可以选择向左或向右移动。
 *      第 i 次移动（从 i == 1 开始，到 i == numMoves ），在选择的方向上走 i 步。
 *
 * 给定整数 target ，返回 到达目标所需的 最小 移动次数(即最小 numMoves )。
 *
 * @see <a href="https://leetcode.cn/problems/reach-a-number">到达终点数字</a>
 */
fun reachNumber(target: Int): Int {
    val t = Math.abs(target)
    var s = 0
    var dis = 0
    while (dis < t) {
        s++
        dis += s
    }
    val dt = dis - t
    return if (dt % 2 == 0) {
        s
    } else {
        if (s % 2 == 0) s + 1 else s + 2
    }
}

/**
 * 用队列实现栈
 *
 * 请你仅使用两个队列实现一个后入先出（LIFO）的栈，并支持普通栈的全部四种操作（push、top、pop 和 empty）。
 *
 * 实现 MyStack 类：
 *
 *      void push(int x) 将元素 x 压入栈顶。
 *      int pop() 移除并返回栈顶元素。
 *      int top() 返回栈顶元素。
 *      boolean empty() 如果栈是空的，返回 true ；否则，返回 false 。
 *
 * 注意：
 *      你只能使用队列的基本操作 —— 也就是 push to back、peek/pop from front、size 和 is empty 这些操作。
 *      你所使用的语言也许不支持队列。你可以使用 list （列表）或者 deque（双端队列）来模拟一个队列, 只要是标准的队列操作即可。
 * @see <a href="https://leetcode.cn/problems/implement-stack-using-queues/">用队列实现栈</a>
 */
class MyStack() {
    var queueIn = LinkedList<Int>()
    var queueOut = LinkedList<Int>()

    fun push(x: Int) {
        queueIn.offer(x)
        while (!queueOut.isEmpty()) {
            queueIn.offer(queueOut.poll())
        }
        val temp = queueIn
        queueIn = queueOut
        queueOut = temp
    }

    fun pop(): Int {
        return queueOut.poll()
    }

    fun top(): Int {
        return queueOut.peek()
    }

    fun empty(): Boolean {
        return queueOut.isEmpty()
    }

}

/**
 * 翻转二叉树
 *
 * 给你一棵二叉树的根节点 root ，翻转这棵二叉树，并返回其根节点。
 *
 * @see <a href="https://leetcode.cn/problems/invert-binary-tree/">翻转二叉树</a>
 */
fun invertTree(root: TreeNode?): TreeNode? {
    // 后序遍历-- 从下向上交换
//    if (root == null) return null
//    val leftNode = invertTree(root.left)
//    val rightNode = invertTree(root.right)
//    root.right = leftNode
//    root.left = rightNode
//    return root
    // 利用中序遍历
//    if (root == null) return null
//    invertTree(root.left) // 递归找到左节点
//
//    val rightNode = root.right // 保存右节点
//
//    root.right = root.left
//    root.left = rightNode
//    // 递归找到右节点 继续交换 : 因为此时左右节点已经交换了,所以此时的右节点为root.left
//    // 递归找到右节点 继续交换 : 因为此时左右节点已经交换了,所以此时的右节点为root.left
//    invertTree(root.left)

    // 先序遍历--从顶向下交换
    if (root == null) return null
    // 保存右子树
    // 保存右子树
    val rightTree = root.right
    // 交换左右子树的位置
    // 交换左右子树的位置
    root.right = invertTree(root.left)
    root.left = invertTree(rightTree)
    return root
}

/**
 * 2 的幂
 *
 * 给你一个整数 n，请你判断该整数是否是 2 的幂次方。如果是，返回 true ；否则，返回 false 。
 * 如果存在一个整数 x 使得 n == 2^x ，则认为 n 是 2 的幂次方。
 *
 * @see <a href="https://leetcode.cn/problems/power-of-two/">2 的幂</a>
 */
fun isPowerOfTwo(n: Int): Boolean {
    // 位运算符
    return (n > 0) && (n and -n) == n
}

/**
 * 汇总区间
 *
 * 给定一个 无重复元素 的有序 整数数组 nums 。
 * 返回 恰好覆盖数组中所有数字 的 最小有序 区间范围列表。也就是说，nums 的每个元素都恰好被某个区间范围所覆盖，并且不存在属于某个范围但不属于 nums 的数字 x 。
 *
 * 列表中的每个区间范围 [a,b] 应该按如下格式输出：
 *
 * "a->b" ，如果 a != b
 *
 * "a" ，如果 a == b
 *
 * @see <a href="https://leetcode.cn/problems/summary-ranges">汇总区间</a>
 */
fun summaryRanges(nums: IntArray): List<String> {
    val ans: MutableList<String> = ArrayList()
    var sb = StringBuilder()
    for (i in 0 until nums.size) {
        if (!(i + 1 < nums.size && nums[i] === nums[i + 1] - 1)) {
            if (sb.length > 0) sb.append("->")
            sb.append(nums[i])
            ans.add(sb.toString())
            sb = StringBuilder()
        } else {
            if (sb.length == 0) sb.append(nums[i])
        }
    }
    return ans
}