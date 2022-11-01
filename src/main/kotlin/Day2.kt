import java.util.*


/**
 * 路径总和
 *
 * 给你二叉树的根节点 root 和一个表示目标和的整数 targetSum 。判断该树中是否存在 根节点到叶子节点 的路径，这条路径上所有节点值相加等于目标和 targetSum 。
 * 如果存在，返回 true ；否则，返回 false 。
 * 叶子节点 是指没有子节点的节点。
 *
 * @see <a href="https://leetcode.cn/problems/path-sum/">路径总和</a>
 */
fun hasPathSum(root: TreeNode?, targetSum: Int): Boolean {
    if (root == null) {
        return false
    }
    return if (root.left == null && root.right == null) {
        targetSum - root.`val` === 0
    } else {
        (hasPathSum(root.left, targetSum - root.`val`) || hasPathSum(root.right, targetSum - root.`val`))
    }
}

/**
 * 二叉树的前序遍历
 *
 * 给你二叉树的根节点 root ，返回它节点值的 前序 遍历。
 *
 * @see <a href="https://leetcode.cn/problems/binary-tree-preorder-traversal/">二叉树的前序遍历</a>
 */
fun preorderTraversal(root: TreeNode?): List<Int> {
    val res = ArrayList<Int>()
    if (root == null)  return res

    val stack = Stack<TreeNode?>()
    stack.push(root)
    while (!stack.isEmpty()) {
        val node = stack.pop()
        res.add(node!!.`val`)
        if (node!!.right != null) {
            stack.push(node.right)
        }
        if (node.left != null) {
            stack.push(node.left)
        }
    }
    return res
}

/**
 * 二叉树的后序遍历
 *
 * 给你一棵二叉树的根节点 root ，返回其节点值的 后序遍历 。
 *
 * @see <a href="https://leetcode.cn/problems/binary-tree-postorder-traversal/">二叉树的后序遍历</a>
 */
fun postorderTraversal(root: TreeNode?): List<Int> {
    var p = root
    var r: TreeNode? = null
    val ans = ArrayList<Int>()
    val s: Deque<TreeNode> = ArrayDeque()
    while (!s.isEmpty() || p != null) {
        if (p != null) {
            s.push(p)
            p = p.left
        } else {
            p = s.peek()
            if (p.right == null || p.right == r) {
                ans.add(p.`val`)
                r = p
                s.pop()
                p = null
            } else p = p.right
        }
    }
    return ans
}
/**
只要写出了下面这个模板
while( 栈非空 || p 非空) {
    if( p 非空) {

    } else {

    }
}
那么不仅能够慢慢想出后序遍历，前中序遍历也能够想出来！！
 */


/**
 * 相交链表
 *
 * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表不存在相交节点，返回 null 。
 *
 * @see <a href="https://leetcode.cn/problems/intersection-of-two-linked-lists/">相交链表</a>
 */
fun getIntersectionNode(headA:ListNode?, headB:ListNode?):ListNode? {
    /**
     * 定义两个指针, 第一轮让两个到达末尾的节点指向另一个链表的头部, 最后如果相遇则为交点(在第一轮移动中恰好抹除了长度差)
     * 两个指针等于移动了相同的距离, 有交点就返回, 无交点就是各走了两条指针的长度
     */
    if (headA == null || headB == null) return null
    var pA = headA
    var pB = headB
    // 在这里第一轮体现在pA和pB第一次到达尾部会移向另一链表的表头, 而第二轮体现在如果pA或pB相交就返回交点, 不相交最后就是null==null
    // 在这里第一轮体现在pA和pB第一次到达尾部会移向另一链表的表头, 而第二轮体现在如果pA或pB相交就返回交点, 不相交最后就是null==null
    while (pA != pB) {
        pA = if (pA == null) headB else pA.next
        pB = if (pB == null) headA else pB.next
    }
    return pA
}