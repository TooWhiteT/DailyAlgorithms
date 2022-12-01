/**
 * 找到最近的有相同 X 或 Y 坐标的点
 *
 * 给你两个整数 x 和 y ，表示你在一个笛卡尔坐标系下的 (x, y) 处。同时，在同一个坐标系下给你一个数组 points ，其中 points[i] = [ai, bi] 表示在 (ai, bi) 处有一个点。当一个点与你所在的位置有相同的 x 坐标或者相同的 y 坐标时，我们称这个点是 有效的 。
 * 请返回距离你当前位置 曼哈顿距离 最近的 有效 点的下标（下标从 0 开始）。如果有多个最近的有效点，请返回下标 最小 的一个。如果没有有效点，请返回 -1 。
 * 两个点 (x1, y1) 和 (x2, y2) 之间的 曼哈顿距离 为 abs(x1 - x2) + abs(y1 - y2) 。
 *
 * @see <a href="https://leetcode.cn/problems/find-nearest-point-that-has-the-same-x-or-y-coordinate">找到最近的有相同 X 或 Y 坐标的点</a>
 */
fun nearestValidPoint(x: Int, y: Int, points: Array<IntArray>): Int {
    var min_index = -1
    var min_distince = Int.MAX_VALUE // 2147483647
    for (i in 0 until points.size) {
        // （当一个点与你所在的位置有相同的 x 坐标或者相同的 y 坐标时） && （当前 曼哈顿距离 小于记录的最小距离）
        if (
            (x == points[i][0] || y == points[i][1]) &&
            Math.abs(x - points[i][0]) + Math.abs(y - points[i][1]) < min_distince
        ) {
            // 更新最小坐标&&最小距离
            min_index = i
            min_distince = Math.abs(x - points[i][0]) + Math.abs(y - points[i][1])
        }
    }
    return min_index
}

/**
 * 叶子相似的树
 *
 * 请考虑一棵二叉树上所有的叶子，这些叶子的值按从左到右的顺序排列形成一个 叶值序列 。
 * 举个例子，如上图所示，给定一棵叶值序列为 (6, 7, 4, 9, 8) 的树。
 * 如果有两棵二叉树的叶值序列是相同，那么我们就认为它们是 叶相似 的。
 * 如果给定的两个根结点分别为 root1 和 root2 的树是叶相似的，则返回 true；否则返回 false 。
 *
 * @see <a href="https://leetcode.cn/problems/leaf-similar-trees/">叶子相似的树</a>
 */
fun leafSimilar(root1: TreeNode?, root2: TreeNode?): Boolean {
    val str1 = eachTree(root1, "")
    val str2 = eachTree(root2, "")
    return str1 == str2
}

fun eachTree(root: TreeNode?, str: String): String {
    if (root == null) return str
    var temp = str
    if (root.right == null && root.left == null) {
        temp += root.`val`
    }
    return eachTree(root.left, temp) + eachTree(root.right, temp)
}

/**
 * 链表的中间结点
 *
 * 给定一个头结点为 head 的非空单链表，返回链表的中间结点。
 * 如果有两个中间结点，则返回第二个中间结点。
 *
 * @see <a href="https://leetcode.cn/problems/middle-of-the-linked-list/">链表的中间结点</a>
 */
fun middleNode(head: ListNode?): ListNode? {
    var slow = head // 慢指针
    var fast = head // 快指针
    while (fast != null && fast.next != null) {
        fast = fast!!.next?.next
        slow = slow?.next
    }
    return slow
}

/**
 * 两句话中的不常见单词
 *
 * 句子 是一串由空格分隔的单词。每个 单词 仅由小写字母组成。
 * 如果某个单词在其中一个句子中恰好出现一次，在另一个句子中却 没有出现 ，那么这个单词就是 不常见的 。
 * 给你两个 句子 s1 和 s2 ，返回所有 不常用单词 的列表。返回列表中单词可以按 任意顺序 组织。
 *
 * @see <a href="https://leetcode.cn/problems/uncommon-words-from-two-sentences/"> 两句话中的不常见单词</a>
 */
fun uncommonFromSentences(s1: String, s2: String): Array<String> {
    // 1. 合并A，B放到一个字符串数组中
    val str_sum = "$s1 $s2".split(" ")
    // 2. 计算每个字符串出现的次数
    val map = HashMap<String, Int>()
    for (i in 0 until str_sum.size) {
        map.put(str_sum[i], map.getOrDefault(str_sum[i], 0) + 1)
    }
    // 3. 如果出现的次数为1，就是唯一的不常见单词
    val list = ArrayList<String>()
    for (key in map.keys) {
        if (map[key] == 1) {
            list.add(key)
        }
    }
    val res = Array<String>(list.size) { "" }
    return list.toArray(res)
}

/**
 * 三维形体投影面积
 *
 * 在 n x n 的网格 grid 中，我们放置了一些与 x，y，z 三轴对齐的 1 x 1 x 1 立方体。
 * 每个值 v = grid[i][j] 表示 v 个正方体叠放在单元格 (i, j) 上。
 * 现在，我们查看这些立方体在 xy 、yz 和 zx 平面上的投影。
 * 投影 就像影子，将 三维 形体映射到一个 二维 平面上。从顶部、前面和侧面看立方体时，我们会看到“影子”。
 * 返回 所有三个投影的总面积 。
 *
 * @see <a href="https://leetcode.cn/problems/projection-area-of-3d-shapes/">三维形体投影面积</a>
 */
fun projectionArea(grid: Array<IntArray>): Int {
    var area = 0
    for (i in 0 until grid.size) {
        var max_x = 0
        var max_y = 0
        for (j in 0 until grid.size) {
            if (grid[i][j] != 0) {
                area++
            }
            max_x = Math.max(grid[i][j], max_x)
            max_y = Math.max(grid[j][i], max_y)
        }
        area += max_x
        area += max_y
    }
    return area
}