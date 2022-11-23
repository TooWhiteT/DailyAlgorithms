import java.util.*

/**
 * 盒子中小球的最大数量
 *
 * 你在一家生产小球的玩具厂工作，有 n 个小球，编号从 lowLimit 开始，到 highLimit 结束（包括 lowLimit 和 highLimit ，即 n == highLimit - lowLimit + 1）。
 * 另有无限数量的盒子，编号从 1 到 infinity 。
 * 你的工作是将每个小球放入盒子中，其中盒子的编号应当等于小球编号上每位数字的和。
 * 例如，编号 321 的小球应当放入编号 3 + 2 + 1 = 6 的盒子，而编号 10 的小球应当放入编号 1 + 0 = 1 的盒子。
 * 给你两个整数 lowLimit 和 highLimit ，返回放有最多小球的盒子中的小球数量。如果有多个盒子都满足放有最多小球，只需返回其中任一盒子的小球数量。
 *
 * @see <a href="https://leetcode.cn/problems/maximum-number-of-balls-in-a-box">盒子中小球的最大数量</a>
 */
fun countBalls(lowLimit: Int, highLimit: Int): Int {
    val map = HashMap<Int, Int>(16)
    var res = 0
    for (i in lowLimit .. highLimit) {
        var tmp = i / 10
        var value = i % 10
        while (tmp != 0) {
            value += tmp % 10
            tmp /= 10
        }
        val num = map.getOrDefault(value, 0) + 1
        map.put(value, num)
        res = Math.max(num, res)
    }
    return res
}

/**
 * 二叉搜索树中的搜索
 *
 * 给定二叉搜索树（BST）的根节点 root 和一个整数值 val。
 * 你需要在 BST 中找到节点值等于 val 的节点。 返回以该节点为根的子树。 如果节点不存在，则返回 null。
 *
 * @see <a href="https://leetcode.cn/problems/search-in-a-binary-search-tree">二叉搜索树中的搜索</a>
 */
fun searchBST(root: TreeNode?, `val`: Int): TreeNode? {
    if (root == null || root.`val` == `val`) return root
    return if (root.`val` > `val`) searchBST(root.left, `val`) else searchBST(root.right, `val`)
}

/**
 * 数据流中的第 K 大元素
 *
 * 设计一个找到数据流中第 k 大元素的类（class）。注意是排序后的第 k 大元素，不是第 k 个不同的元素。
 * 请实现 KthLargest 类：
 * 1. KthLargest(int k, int[] nums) 使用整数 k 和整数流 nums 初始化对象。
 * 2. int add(int val) 将 val 插入数据流 nums 后，返回当前数据流中第 k 大的元素。
 *
 * @see <a href="https://leetcode.cn/problems/kth-largest-element-in-a-stream">数据流中的第 K 大元素</a>
 */
class KthLargest(k: Int, nums: IntArray) {
    // 维护一个最小堆，堆的元素个数为常量 k，新加入一个元素和堆顶比较，如果比堆顶元素小，丢弃，否则删除堆顶元素，插入新元素。
    private var k: Int = 0
    private var q: PriorityQueue<Int>
    init {
        this.k = k
        q = PriorityQueue<Int>(k)
        for (i in nums) {
            add(i)
        }
    }

    fun add(`val`: Int): Int {
        if (q.size < k) {
            q.offer(`val`)
        } else if (q.peek() < `val`) {
            q.poll()
            q.offer(`val`)
        }
        return q.peek()
    }

}

/**
 * 二分查找
 *
 * 给定一个n个元素有序的（升序）整型数组nums 和一个目标值target，写一个函数搜索nums中的 target，如果目标值存在返回下标，否则返回 -1。
 *
 * @see <a href="https://leetcode.cn/problems/binary-search">二分查找</a>
 */
fun search(nums: IntArray, target: Int): Int {
    var left = 0
    var right: Int = nums.size
    while (left < right) {
        val mid = left + (right - left shr 1)
        if (nums[mid] === target)
            return mid
        else if (nums[mid] < target)
            left = mid + 1
        else if (nums[mid] > target)
            right = mid
    }
    return -1
}

/**
 * 设计哈希集合
 *
 * 不使用任何内建的哈希表库设计一个哈希集合（HashSet）。
 *
 * 实现 MyHashSet 类：
 * 1. void add(key) 向哈希集合中插入值 key 。
 * 2. bool contains(key) 返回哈希集合中是否存在这个值 key 。
 * 3. void remove(key) 将给定值 key 从哈希集合中删除。如果哈希集合中没有这个值，什么也不做。
 *
 * @see <a href="https://leetcode.cn/problems/design-hashset">设计哈希集合</a>
 */
class MyHashSet() {
    private val map = BooleanArray(1000005)

    fun add(key: Int) {
        map[key] = true
    }

    fun remove(key: Int) {
        map[key] = false
    }

    fun contains(key: Int): Boolean = map[key]

}