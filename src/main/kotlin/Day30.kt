/**
 * 生成交替二进制字符串的最少操作数
 *
 * 给你一个仅由字符 '0' 和 '1' 组成的字符串 s 。一步操作中，你可以将任一 '0' 变成 '1' ，或者将 '1' 变成 '0' 。
 * 交替字符串 定义为：如果字符串中不存在相邻两个字符相等的情况，那么该字符串就是交替字符串。例如，字符串 "010" 是交替字符串，而字符串 "0100" 不是。
 * 返回使 s 变成 交替字符串 所需的 最少 操作数。
 *
 * @see <a href="https://leetcode.cn/problems/minimum-changes-to-make-alternating-binary-string">生成交替二进制字符串的最少操作数</a>
 */
fun minOperations(s: String): Int {
    var cnt1 = 0
    var cnt2 = 0
    var x = '0'
    // 将序列变为 0101.. 或 1010..
    for (i in s) {
        if (i != x) {
            cnt1 += 1
        } else {
            cnt2 += 1
        }
        // 0 变 1，1 变 0
        x = (x.hashCode() xor 1).toChar()
    }
    return Math.min(cnt1, cnt2)
}

/**
 * 比较含退格的字符串
 *
 * 给定 s 和 t 两个字符串，当它们分别被输入到空白的文本编辑器后，如果两者相等，返回 true 。# 代表退格字符。
 * 注意：如果对空文本输入退格字符，文本继续为空。
 *
 * @see <a href="https://leetcode.cn/problems/backspace-string-compare">比较含退格的字符串</a>
 */
fun backspaceCompare(s: String, t: String): Boolean {
    val ssb = StringBuilder() // 模拟栈
    val tsb = StringBuilder() // 模拟栈
    // 分别处理两个 String
    for (c in s.toCharArray()) {
        if (c != '#') {
            ssb.append(c) // 模拟入栈
        } else if (ssb.length > 0) { // 栈非空才能弹栈
            ssb.deleteCharAt(ssb.length - 1) // 模拟弹栈
        }
    }
    for (c in t.toCharArray()) {
        if (c != '#') {
            tsb.append(c) // 模拟入栈
        } else if (tsb.length > 0) { // 栈非空才能弹栈
            tsb.deleteCharAt(tsb.length - 1) // 模拟弹栈
        }
    }
    return ssb.toString() == tsb.toString()
}

/**
 * 矩形重叠
 *
 * 矩形以列表 [x1, y1, x2, y2] 的形式表示，其中 (x1, y1) 为左下角的坐标，(x2, y2) 是右上角的坐标。矩形的上下边平行于 x 轴，左右边平行于 y 轴。
 * 如果相交的面积为 正 ，则称两矩形重叠。需要明确的是，只在角或边接触的两个矩形不构成重叠。
 * 给出两个矩形 rec1 和 rec2 。如果它们重叠，返回 true；否则，返回 false 。
 *
 * @see <a href="https://leetcode.cn/problems/rectangle-overlap">矩形重叠</a>
 */
fun isRectangleOverlap(rec1: IntArray, rec2: IntArray): Boolean {
    // 矩形如果不重叠，从x轴和y轴上看两个矩形就变成了两条线段，这两条线段肯定是不相交的，
    // 也就是说左边的矩形的最右边小于右边矩形的最左边，也就是rec1[2] < rec2[0] || rec2[2] < rec1[0]；
    // y轴同理，下面的矩形的最上边小于上面矩形的最下边，也就是rec1[3] < rec2[1] || rec2[3] < rec1[1]。
    // 因为题目要求重叠算相离，所以加上=，最后取反
    return !(rec1[2] <= rec2[0] ||
            rec2[2] <= rec1[0] ||
            rec1[3] <= rec2[1] ||
            rec2[3] <= rec1[1])
}

/**
 * 翻转图像
 *
 * 给定一个n x n的二进制矩阵image，先 水平 翻转图像，然后反转图像并返回结果。
 * 水平翻转图片就是将图片的每一行都进行翻转，即逆序。
 *
 * 例如，水平翻转[1,1,0]的结果是[0,1,1]。
 * 反转图片的意思是图片中的0全部被1替换，1全部被0替换。
 * 例如，反转[0,1,1]的结果是[1,0,0]。
 *
 * @see <a href="https://leetcode.cn/problems/flipping-an-image">翻转图像</a>
 */
fun flipAndInvertImage(A: Array<IntArray>): Array<IntArray> {
    val x: Int = A.size
    val y: Int = A[0].size
    val B = Array(x) {
        IntArray(y)
    }
    for (i in 0 until x) {
        for (j in 0 until y) {
            B[i][y - j - 1] = 1 - A[i][j] // 从后往前赋值，同时取反
        }
    }
    return B
}

/**
 * 较大分组的位置
 *
 * 在一个由小写字母构成的字符串 s 中，包含由一些连续的相同字符所构成的分组。
 * 例如，在字符串 s = "abbxxxxzyy" 中，就含有 "a", "bb", "xxxx", "z" 和 "yy" 这样的一些分组。
 * 分组可以用区间 [start, end] 表示，其中 start 和 end 分别表示该分组的起始和终止位置的下标。上例中的 "xxxx" 分组用区间表示为 [3,6] 。
 * 我们称所有包含大于或等于三个连续字符的分组为 较大分组 。
 * 找到每一个 较大分组 的区间，按起始位置下标递增顺序排序后，返回结果。
 *
 * @see <a href="https://leetcode.cn/problems/positions-of-large-groups">较大分组的位置</a>
 */
fun largeGroupPositions(s: String): List<List<Int>> {
    val temp = s + "A"
    val ans = ArrayList<List<Int>>()
    var begin = 0
    for (i in 1 until temp.length) {
        if (temp[i] != temp[i - 1]) {
            if (i - begin >= 3) {
                ans.add(listOf(begin, i-1))
            }
            begin = i
        }
    }
    return ans
}
