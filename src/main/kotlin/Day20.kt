import java.util.*

/**
 * 找到最高海拔
 *
 * 有一个自行车手打算进行一场公路骑行，这条路线总共由n + 1个不同海拔的点组成。自行车手从海拔为 0的点 0 开始骑行。
 * 给你一个长度为 n 的整数数组 gain ，其中 gain[i]是点 i 和点 i + 1 的 净海拔高度差（0 <= i < n）。请你返回 最高点的海拔 。
 *
 * @see <a href="https://leetcode.cn/problems/find-the-highest-altitude">找到最高海拔</a>
 */
fun largestAltitude(gain: IntArray): Int {
    var ans = 0
    var h = 0
    for (i in 0 until gain.size) {
        h += gain[i]
        ans = Math.max(ans, h)
    }
    return ans
}

/**
 * 根据二叉树创建字符串
 *
 * 给你二叉树的根节点 root ，请你采用前序遍历的方式，将二叉树转化为一个由括号和整数组成的字符串，返回构造出的字符串。
 * 空节点使用一对空括号对 "()" 表示，转化后需要省略所有不影响字符串与原始二叉树之间的一对一映射关系的空括号对。
 *
 * @see <a href="https://leetcode.cn/problems/construct-string-from-binary-tree">根据二叉树创建字符串</a>
 */
fun tree2strs(root: TreeNode?): String {
    val sb = StringBuilder()
    doTree2str(root, sb)
    return sb.toString()
}

/**
 * 合并二叉树
 *
 * 给你两棵二叉树： root1 和 root2 。
 * 想象一下，当你将其中一棵覆盖到另一棵之上时，两棵树上的一些节点将会重叠（而另一些不会）。你需要将这两棵树合并成一棵新二叉树。合并的规则是：如果两个节点重叠，那么将这两个节点的值相加作为合并后节点的新值；否则，不为 null 的节点将直接作为新二叉树的节点。
 * 返回合并后的二叉树。
 * 注意: 合并过程必须从两个树的根节点开始。
 *
 * @see <a href="https://leetcode.cn/problems/merge-two-binary-trees">合并二叉树</a>
 */
fun mergeTrees(t1: TreeNode?, t2: TreeNode?): TreeNode? {
    if (t1 == null) {
        return t2
    }
    if (t2 == null) {
        return t1
    }
    // 先合并根节点
    t1.`val` += t2.`val`
    // 再递归合并左右子树
    t1.left = mergeTrees(t1.left, t2.left)
    t1.right = mergeTrees(t1.right, t2.right)
    return t1
}

/**
 * 三个数的最大乘积
 *
 * 给你一个整型数组 nums ，在数组中找出由三个数组成的最大乘积，并输出这个乘积。
 *
 * @see <a href="https://leetcode.cn/problems/maximum-product-of-three-numbers/">三个数的最大乘积</a>
 */
fun maximumProduct(nums: IntArray): Int {
    Arrays.sort(nums)
    return Math.max(nums[nums.size-1] * nums[nums.size - 2] * nums[nums.size - 3], nums[0] * nums[1] * nums[nums.size - 1])
}

/**
 * 二叉树的层平均值
 *
 * 给定一个非空二叉树的根节点 root , 以数组的形式返回每一层节点的平均值。与实际答案相差 10-5 以内的答案可以被接受。
 *
 * @see <a href="https://leetcode.cn/problems/average-of-levels-in-binary-tree/">二叉树的层平均值</a>
 */
fun averageOfLevels(root: TreeNode?): DoubleArray {
    val res = ArrayList<Double>()
    if (root == null) return res.toDoubleArray()
    val list: Queue<TreeNode?> = LinkedList()
    list.add(root)
    while (list.size !== 0) {
        val len: Int = list.size
        var sum = 0.0
        for (i in 0 until len) {
            val node = list.poll()
            sum += node!!.`val`.toDouble()
            if (node!!.left != null) list.add(node!!.left)
            if (node!!.right != null) list.add(node!!.right)
        }
        res.add(sum / len)
    }
    return res.toDoubleArray()
}