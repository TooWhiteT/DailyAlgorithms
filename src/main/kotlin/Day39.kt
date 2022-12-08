import java.util.*
import javax.swing.text.html.HTML.Attribute.N





/**
 * 判断国际象棋棋盘中一个格子的颜色
 *
 * 给你一个坐标 coordinates ，它是一个字符串，表示国际象棋棋盘中一个格子的坐标。
 * 如果所给格子的颜色是白色，请你返回 true，如果是黑色，请返回 false 。
 * 给定坐标一定代表国际象棋棋盘上一个存在的格子。坐标第一个字符是字母，第二个字符是数字。
 *
 * @see <a href="https://leetcode.cn/problems/determine-color-of-a-chessboard-square">判断国际象棋棋盘中一个格子的颜色</a>
 */
fun squareIsWhite(coordinates: String): Boolean {
    // 模拟棋盘 0 白 1 黑
    val start1 = mapOf<Char, Int>(
        '1' to 1,
        '2' to 0,
        '3' to 1,
        '4' to 0,
        '5' to 1,
        '6' to 0,
        '7' to 1,
        '8' to 0
    )
    val start0 = mapOf<Char, Int>(
        '1' to 0,
        '2' to 1,
        '3' to 0,
        '4' to 1,
        '5' to 0,
        '6' to 1,
        '7' to 0,
        '8' to 1
    )
    val map = mapOf<Char, Map<Char, Int>>(
        'a' to start1,
        'b' to start0,
        'c' to start1,
        'd' to start0,
        'e' to start1,
        'f' to start0,
        'g' to start1,
        'h' to start0
    )
    val (x, y) = coordinates.toCharArray()
    return map[x]?.get(y) == 0
}

/**
 * 可以被一步捕获的棋子数
 *
 * 在一个 8 x 8 的棋盘上，有一个白色的车（Rook），用字符 'R' 表示。棋盘上还可能存在空方块，白色的象（Bishop）以及黑色的卒（pawn），分别用字符 '.'，'B' 和 'p' 表示。不难看出，大写字符表示的是白棋，小写字符表示的是黑棋。
 * 车按国际象棋中的规则移动。东，西，南，北四个基本方向任选其一，然后一直向选定的方向移动，直到满足下列四个条件之一：
 *      棋手选择主动停下来。
 *      棋子因到达棋盘的边缘而停下。
 *      棋子移动到某一方格来捕获位于该方格上敌方（黑色）的卒，停在该方格内。
 *      车不能进入/越过已经放有其他友方棋子（白色的象）的方格，停在友方棋子前。
 *
 * 你现在可以控制车移动一次，请你统计有多少敌方的卒处于你的捕获范围内（即，可以被一步捕获的棋子数）。
 *
 * @see <a href="https://leetcode.cn/problems/available-captures-for-rook/">可以被一步捕获的棋子数</a>
 */
fun numRookCaptures(board: Array<CharArray>): Int {
    // 定义上下左右四个方向
    val dx = intArrayOf(-1, 1, 0, 0)
    val dy = intArrayOf(0, 0, -1, 1)

    for (i in 0..7) {
        for (j in 0..7) {
            // 找到白车所在的位置
            if (board[i][j] == 'R') {
                // 分别判断白车的上、下、左、右四个方向
                var res = 0
                for (k in 0..3) {
                    var x = i
                    var y = j
                    while (true) {
                        x += dx[k]
                        y += dy[k]
                        if (x < 0 || x >= 8 || y < 0 || y >= 8 || board[x][y] == 'B') {
                            break
                        }
                        if (board[x][y] == 'p') {
                            res++
                            break
                        }
                    }
                }
                return res
            }
        }
    }
    return 0
}

/**
 * 查找共用字符
 *
 * 给你一个字符串数组 words ，请你找出所有在 words 的每个字符串中都出现的共用字符（ 包括重复字符），并以数组形式返回。你可以按 任意顺序 返回答案。
 *
 * @see <a href="https://leetcode.cn/problems/find-common-characters">查找共用字符</a>
 */
fun commonChars(words: Array<String>): List<String> {
    val list = ArrayList<String>()
    val res = IntArray(26)
    for (c in words[0].toCharArray()) {
        res[c.hashCode() - 'a'.hashCode()]++
    }
    for (i in 1 until words.size) {
        val temp = IntArray(26)
        for (c in words[i].toCharArray()) {
            temp[c.hashCode() - 'a'.hashCode()]++
        }
        for (j in 0..25) {
            res[j] = Math.min(res[j], temp[j])
        }
    }
    for (i in res.indices) {
        if (res[i] > 0) {
            for (j in 0 until res[i]) {
                list.add(('a'.hashCode() + i).toChar().toString() + "")
            }
        }
    }
    return list
}

/**
 * K 次取反后最大化的数组和
 *
 * 给你一个整数数组 nums 和一个整数 k ，按以下方法修改该数组：
 *      选择某个下标 i 并将 nums[i] 替换为 -nums[i] 。
 *
 * 重复这个过程恰好 k 次。可以多次选择同一个下标 i 。
 * 以这种方式修改数组后，返回数组 可能的最大和 。
 *
 * @see <a href="https://leetcode.cn/problems/maximize-sum-of-array-after-k-negations">K 次取反后最大化的数组和</a>
 */
fun largestSumAfterKNegations(nums: IntArray, k: Int): Int {
    var i = 0
    var k = k
    // 1. 先来个排序, 把可能存在的负数排到数组前面
    Arrays.sort(nums)
    // 2. 将负数尽可能多的翻转为正数, 注意边界
    while (k > 0 && i < nums.size && nums[i] < 0) {
        nums[i] = -nums[i]
        k--
        i++
    }
    // 3. 如果翻转次数已经用完, 返回数组和
    if (k == 0) {
        return nums.sum()
    }
    // 4. 如果k != 0, 说明数组只剩下非负整数, 再来个排序
    Arrays.sort(nums)
    // 5. 判断剩余翻转次数是否为奇数, 如果是奇数则将第一个元素翻转(最小正数变负数, 对最终结果影响最"小")
    if (nums[0] != 0 && k % 2 != 0) {
        nums[0] = -nums[0]
    }
    // 6. 返回最终的数组和
    return nums.sum()
}

/**
 * 十进制整数的反码
 *
 * 每个非负整数N都有其二进制表示。例如，5可以被表示为二进制"101"，11 可以用二进制"1011"表示，依此类推。注意，除N = 0外，任何二进制表示中都不含前导零。
 * 二进制的反码表示是将每个1改为0且每个0变为1。例如，二进制数"101"的二进制反码为"010"。
 * 给你一个十进制数N，请你返回其二进制表示的反码所对应的十进制整数。
 *
 * @see <a href="https://leetcode.cn/problems/complement-of-base-10-integer">十进制整数的反码</a>
 */
fun bitwiseComplement(n: Int): Int {
    var num = 1
    while (num < n) {
        num = (num shl 1) + 1
    }
    return n xor num
}