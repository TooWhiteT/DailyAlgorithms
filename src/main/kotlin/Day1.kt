import java.util.Stack

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