import java.util.*

/**
 * 二叉树的中序遍历
 *
 * 给定一个二叉树的根节点 root ，返回 它的 中序 遍历 。
 *
 * @see <a href="https://leetcode.cn/problems/binary-tree-inorder-traversal/">二叉树的中序遍历</a>
 */
fun inorderTraversal(root: TreeNode?): List<Int> {
    val list = ArrayList<Int>()
    val stack = Stack<TreeNode>()
    var cur = root
    while (cur != null || !stack.isEmpty()) {
        if (cur != null) {
            stack.push(cur)
            cur = cur.left
        } else {
            cur = stack.pop()
            list.add(cur.`val`)
            cur = cur.right
        }
    }
    return list
}

/**
 * 相同的树
 *
 * 给你两棵二叉树的根节点 p 和 q ，编写一个函数来检验这两棵树是否相同。
 * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
 *
 * @see <a href="https://leetcode.cn/problems/same-tree/">相同的树</a>
 */
fun isSameTree(p: TreeNode?, q: TreeNode?): Boolean {
    if( p== null && q == null) return true
    if (p != null && q != null && p.`val` == q.`val`) {
        return (isSameTree(p.left, q.left) && isSameTree(p.right, q.right))
    } else {
        return false
    }
}

/**
 * 平衡二叉树
 *
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 *
 * @see <a href="https://leetcode.cn/problems/balanced-binary-tree/">平衡二叉树</a>
 */
fun isBalanced(root: TreeNode?): Boolean {
    return height(root) >= 0
}

fun height(root: TreeNode?): Int {
    if (root == null) return 0
    val lh = height(root.left)
    val rh = height(root.right)
    return if (lh >= 0 && rh >= 0 && Math.abs(lh - rh) <= 1) Math.max(lh, rh) + 1 else -1
}

/**
 * 二叉树的最小深度
 *
 * 给定一个二叉树，找出其最小深度。
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 * 说明：叶子节点是指没有子节点的节点。
 *
 * @see <a href="https://leetcode.cn/problems/minimum-depth-of-binary-tree/">二叉树的最小深度</a>
 */
fun minDepth(root: TreeNode?): Int {
    if (root == null) {
        return 0
    }
    // null节点不参与比较
    if (root.left == null && root.right != null) {
        return 1 + minDepth(root.right)
    }
    // null节点不参与比较
    if (root.right == null && root.left != null) {
        return 1 + minDepth(root.left)
    }

    return 1 + Math.min(minDepth(root.left), minDepth(root.right))
}