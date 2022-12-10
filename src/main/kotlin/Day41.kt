import java.util.*


/**
 * 堆叠长方体的最大高度
 *
 * 给你 n 个长方体 cuboids ，其中第 i 个长方体的长宽高表示为 cuboids[i] = [widthi, lengthi, heighti]（下标从 0 开始）。请你从 cuboids 选出一个 子集 ，并将它们堆叠起来。
 * 如果 widthi <= widthj 且 lengthi <= lengthj 且 heighti <= heightj ，你就可以将长方体 i 堆叠在长方体 j 上。你可以通过旋转把长方体的长宽高重新排列，以将它放在另一个长方体上。
 * 返回 堆叠长方体cuboids 可以得到的 最大高度 。
 *
 * @see <a href="https://leetcode.cn/problems/maximum-height-by-stacking-cuboids">堆叠长方体的最大高度</a>
 */
fun maxHeight(cuboids: Array<IntArray>): Int {
    for (i in 0 until cuboids.size) {
        Arrays.sort(cuboids[i])
    }
    Arrays.sort(cuboids) { a,b ->
        if (a[0] == b[0])
            if (a[1] == b[1])
                b[2] - a[2]
            else
                b[1] - a[1]
         else
            b[0] - a[0]
    }
    val height = IntArray(cuboids.size)
    for (i in 0 until cuboids.size) {
        for (j in 0 until i) {
            if (cuboids[j][1] >= cuboids[i][1] && cuboids[j][2] >= cuboids[i][2]) {
                height[i] = Math.max(height[i], height[j])
            }
        }
        height[i] += cuboids[i][2]
    }
    var ans = 0
    for (i in 0 until cuboids.size) {
        ans = Math.max(ans, height[i])
    }
    return ans
}

/**
 * 除数博弈
 *
 * 爱丽丝和鲍勃一起玩游戏，他们轮流行动。爱丽丝先手开局。
 * 最初，黑板上有一个数字n。在每个玩家的回合，玩家需要执行以下操作：
 *      选出任一x，满足0 < x < n且n % x == 0。
 *      用 n - x替换黑板上的数字n 。
 *
 * 如果玩家无法执行这些操作，就会输掉游戏。
 * 只有在爱丽丝在游戏中取得胜利时才返回true。假设两个玩家都以最佳状态参与游戏。
 *
 * @see <a href="https://leetcode.cn/problems/divisor-game">除数博弈</a>
 */
fun divisorGame(n: Int): Boolean {
    // 如果N是奇数，因为奇数的所有因数都是奇数，因此 N 进行一次 N-x 的操作结果一定是偶数，
    // 所以如果 a 拿到了一个奇数，那么轮到 b 的时候，b拿到的肯定是偶数，
    // 这个时候 b 只要进行 -1， 还给 a 一个奇数，那么这样子b就会一直拿到偶数，到最后b一定会拿到最小偶数2，a就输了。
    // 所以如果游戏开始时Alice拿到N为奇数，那么她必输，也就是false。如果拿到N为偶数，她只用 -1，让bob 拿到奇数，最后bob必输，结果就是true。
    return (n and 1 == 0)
}

/**
 * 距离顺序排列矩阵单元格
 *
 * 给定四个整数 rows, cols , rCenter 和 cCenter 。有一个rows x cols的矩阵，你在单元格上的坐标是(rCenter, cCenter) 。
 * 返回矩阵中的所有单元格的坐标，并按与(rCenter, cCenter)的 距离 从最小到最大的顺序排。你可以按 任何 满足此条件的顺序返回答案。
 * 单元格(r1, c1) 和 (r2, c2) 之间的距离为|r1 - r2| + |c1 - c2|。
 *
 * @see <a href="https://leetcode.cn/problems/matrix-cells-in-distance-order">距离顺序排列矩阵单元格</a>
 */
fun allCellsDistOrder(rows: Int, cols: Int, rCenter: Int, cCenter: Int): Array<IntArray> {
    val res = Array(rows * cols) { IntArray(2) }
    var index = 0
    for (i in 0 until rows) for (j in 0 until cols) {
        val xy = intArrayOf(i, j)
        res[index++] = xy
    }
    Arrays.sort(res) { o1, o2 ->
        val dis1: Int = Math.abs(o1[0] - rCenter) + Math.abs(o1[1] - cCenter)
        val dis2: Int = Math.abs(o2[0] - rCenter) + Math.abs(o2[1] - cCenter)
        dis1 - dis2
    }
    return res
}

/**
 * 有效的回旋镖
 *
 * 给定一个数组points，其中points[i] = [xi, yi]表示 X-Y 平面上的一个点，如果这些点构成一个回旋镖则返回true。
 * 回旋镖定义为一组三个点，这些点各不相同且不在一条直线上。
 *
 * @see <a href="https://leetcode.cn/problems/valid-boomerang">有效的回旋镖</a>
 */
fun isBoomerang(p: Array<IntArray>): Boolean {
    // 已知三个顶点坐标来计算三角形面积是否为0： 面积公式S=1/2[(x1y2-x2y1)+(x2y3-x3y2)+(x3y1-x1y3)],去个常数项，提个公因子答案就来了。
    return (p[0][0] * (p[1][1] - p[2][1]) + p[1][0] * (p[2][1] - p[0][1]) + p[2][0] * (p[0][1] - p[1][1])) != 0;
}

/**
 * 最后一块石头的重量
 *
 * 有一堆石头，每块石头的重量都是正整数。
 * 每一回合，从中选出两块 最重的 石头，然后将它们一起粉碎。假设石头的重量分别为x 和y，且x <= y。那么粉碎的可能结果如下：
 *      如果x == y，那么两块石头都会被完全粉碎；
 *      如果x != y，那么重量为x的石头将会完全粉碎，而重量为y的石头新重量为y-x。
 *
 * 最后，最多只会剩下一块石头。返回此石头的重量。如果没有石头剩下，就返回 0。
 *
 * @see <a href="https://leetcode.cn/problems/last-stone-weight">最后一块石头的重量</a>
 */
fun lastStoneWeight(stones: IntArray): Int {
    if (stones.size == 2) {
        return Math.abs(stones[0] - stones[1])
    }
    if (stones.size == 1) {
        return stones[0]
    }
    Arrays.sort(stones)
    if (stones[stones.size - 3] == 0) {
        return stones[stones.size - 1] - stones[stones.size - 2]
    }
    stones[stones.size - 1] = stones[stones.size - 1] - stones[stones.size - 2]
    stones[stones.size - 2] = 0
    return lastStoneWeight(stones)
}