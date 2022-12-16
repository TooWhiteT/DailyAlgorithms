/**
 * 构成特定和需要添加的最少元素
 *
 * 给你一个整数数组 nums ，和两个整数 limit 与 goal 。数组 nums 有一条重要属性：abs(nums[i]) <= limit 。
 * 返回使数组元素总和等于 goal 所需要向数组中添加的 最少元素数量 ，添加元素 不应改变 数组中 abs(nums[i]) <= limit 这一属性。
 * 注意，如果 x >= 0 ，那么 abs(x) 等于 x ；否则，等于 -x 。
 *
 * @see <a href="https://leetcode.cn/problems/minimum-elements-to-add-to-form-a-given-sum/">构成特定和需要添加的最少元素</a>
 */
fun minElements(nums: IntArray, limit: Int, goal: Int): Int {
    // sum / n 向上取整 == (sum + n - 1) / n 向下取整。
    var sum: Long = 0
    for (x in nums) {
        sum += x.toLong()
    }
    return ((Math.abs(sum - goal) + limit - 1) / limit).toInt()
}

/**
 * 缀点成线
 *
 * 给定一个数组coordinates，其中coordinates[i] = [x, y]，[x, y]表示横坐标为 x、纵坐标为 y的点。请你来判断，这些点是否在该坐标系中属于同一条直线上
 *
 * @see <a href="https://leetcode.cn/problems/check-if-it-is-a-straight-line/">缀点成线</a>
 */
fun checkStraightLine(coordinates: Array<IntArray>): Boolean {
    // 由斜率公式得
    // (y1-y0)/(x1-x0)=(yi-y0)/(xi-x0)
    // 防止除0，变换成相乘的形式
    // (y1-y0)*(xi-x0)==(x1-x0)*(yi-y0)
    for (i in 2 until coordinates.size) {
        if ((coordinates[1][1] - coordinates[0][1]) * (coordinates[i][0] - coordinates[0][0]) != (coordinates[i][1] - coordinates[0][1])*(coordinates[1][0] - coordinates[0][0]))
            return false
    }
    return true
}

/**
 * 奇数值单元格的数目
 *
 * 给你一个 m x n 的矩阵，最开始的时候，每个单元格中的值都是 0。
 * 另有一个二维索引数组indices，indices[i] = [ri, ci] 指向矩阵中的某个位置，其中 ri 和 ci 分别表示指定的行和列（从 0 开始编号）。
 * 对 indices[i] 所指向的每个位置，应同时执行下述增量操作：
 *      ri 行上的所有单元格，加 1 。
 *      ci 列上的所有单元格，加 1 。
 * 给你 m、n 和 indices 。请你在执行完所有indices指定的增量操作后，返回矩阵中 奇数值单元格 的数目。
 *
 * @see <a href="https://leetcode.cn/problems/cells-with-odd-values-in-a-matrix">奇数值单元格的数目</a>
 */
fun oddCells(m: Int, n: Int, indices: Array<IntArray>): Int {
    val row = IntArray(m)
    val col = IntArray(n)
    for (i in indices) {
        row[i[0]]++
        col[i[1]]++
    }
    var ans = 0
    for (i in 0 until m) {
        for (j in 0 until n) {
            val count = row[i] + col[j]
            if (count % 2 == 1) ans++
        }
    }
    return ans
}

/**
 * 二维网格迁移
 *
 * 给你一个 m 行 n列的二维网格grid和一个整数k。你需要将grid迁移k次
 * 每次「迁移」操作将会引发下述活动：
 *      位于 grid[i][j]的元素将会移动到grid[i][j + 1]。
 *      位于grid[i][n- 1] 的元素将会移动到grid[i + 1][0]。
 *      位于 grid[m- 1][n - 1]的元素将会移动到grid[0][0]。
 * 请你返回k 次迁移操作后最终得到的 二维网格。
 *
 * @see <a href="https://leetcode.cn/problems/shift-2d-grid">二维网格迁移</a>
 */
fun shiftGrid(grid: Array<IntArray>, k: Int): List<List<Int>> {
    val nums = IntArray(grid.size * grid[0].size)
    var k = k
    for (i in 0 until grid.size) {
        for (j in 0 until grid[0].size) {
            k %= nums.size
            nums[k++] = grid[i][j]
        }
    }
    k = 0
    val lists = ArrayList<List<Int>>(grid.size)
    for (i in 0 until grid.size) {
        val tempList = ArrayList<Int>(grid[0].size)
        for (j in 0 until grid[0].size) {
            tempList.add(nums[k++])
        }
        lists.add(tempList)
    }
    return lists
}

/**
 * 访问所有点的最小时间
 *
 * 平面上有n个点，点的位置用整数坐标表示 points[i] = [xi, yi] 。请你计算访问所有这些点需要的 最小时间（以秒为单位）。
 * 你需要按照下面的规则在平面上移动：
 *      每一秒内，你可以：
 *          沿水平方向移动一个单位长度，或者
 *          沿竖直方向移动一个单位长度，或者
 *          跨过对角线移动 sqrt(2) 个单位长度（可以看作在一秒内向水平和竖直方向各移动一个单位长度）。
 *      
 *      必须按照数组中出现的顺序来访问这些点。
 *      
 *      在访问某个点时，可以经过该点后面出现的点，但经过的那些点不算作有效访问。
 *
 * @see <a href="https://leetcode.cn/problems/minimum-time-visiting-all-points">访问所有点的最小时间</a>
 */
fun minTimeToVisitAllPoints(points: Array<IntArray>): Int {
    // 因为走斜线和走横竖一个值，所以直接取x or y值的最大值就行
    val num = points.size
    var result = 0
    for (i in 0 until num - 1) {
        result += Math.max(Math.abs(points[i][0] - points[i + 1][0]), Math.abs(points[i][1] - points[i + 1][1]))
    }
    return result
}