/**
 * 所有子字符串美丽值之和
 *
 * 一个字符串的 美丽值 定义为：出现频率最高字符与出现频率最低字符的出现次数之差。
 *      比方说，"abaacc"的美丽值为3 - 1 = 2。
 * 给你一个字符串s，请你返回它所有子字符串的美丽值之和。
 *
 * @see <a href="https://leetcode.cn/problems/sum-of-beauty-of-all-substrings/">所有子字符串美丽值之和</a>
 */
fun beautySum(s: String): Int {
    val n = s.length
    var res = 0
    for (i in 0 until n) {
        val h = IntArray(26)
        for (j in i until n) {
            h[s[j] - 'a']++
            var max = 0
            var min = n
            for (k in 0..25) {
                if (h[k] != 0) {
                    max = Math.max(max, h[k])
                    min = Math.min(min, h[k])
                }
            }
            res += max - min
        }
    }
    return res
}

/**
 * 复写零
 *
 * 给你一个长度固定的整数数组arr ，请你将该数组中出现的每个零都复写一遍，并将其余的元素向右平移。
 * 注意：请不要在超过该数组长度的位置写入元素。请对输入的数组就地进行上述修改，不要从函数返回任何东西。
 *
 * @see <a href="https://leetcode.cn/problems/duplicate-zeros">复写零</a>
 */
fun duplicateZeros(arr: IntArray): Unit {
    val length = arr.size
    var i = 0
    while (i < length) {
        if (arr[i] == 0) {
            for (j in length - 1 downTo i + 1) {
                arr[j] = arr[j - 1]
            }
            i++
        }
        i++
    }
}

/**
 * IP 地址无效化
 *
 * 给你一个有效的 IPv4 地址 address，返回这个 IP 地址的无效化版本。
 * 所谓无效化 IP 地址，其实就是用 "[.]" 代替了每个 "."。
 *
 * @see <a href="https://leetcode.cn/problems/defanging-an-ip-address/">IP 地址无效化</a>
 */
fun defangIPaddr(address: String): String = address.replace(".","[.]")

/**
 * 数组的相对排序
 *
 * 给你两个数组，arr1 和arr2，arr2中的元素各不相同，arr2 中的每个元素都出现在arr1中。
 * 对 arr1中的元素进行排序，使 arr1 中项的相对顺序和arr2中的相对顺序相同。未在arr2中出现过的元素需要按照升序放在arr1的末尾。
 *
 * @see <a href="https://leetcode.cn/problems/relative-sort-array">数组的相对排序</a>
 */
fun relativeSortArray(arr1: IntArray, arr2: IntArray): IntArray {
    val nums = IntArray(1001)
    val res = IntArray(arr1.size)
    //遍历arr1,统计每个元素的数量
    for (i in arr1) {
        nums[i]++
    }
    //遍历arr2,处理arr2中出现的元素
    var index = 0
    for (i in arr2) {
        while (nums[i] > 0) {
            res[index++] = i
            nums[i]--
        }
    }
    //遍历nums,处理剩下arr2中未出现的元素
    for (i in nums.indices) {
        while (nums[i] > 0) {
            res[index++] = i
            nums[i]--
        }
    }
    return res
}

/**
 * 分糖果 II
 *
 * 排排坐，分糖果。
 * 我们买了一些糖果 candies，打算把它们分给排好队的 n = num_people 个小朋友。
 * 给第一个小朋友 1 颗糖果，第二个小朋友 2 颗，依此类推，直到给最后一个小朋友 n颗糖果。
 * 然后，我们再回到队伍的起点，给第一个小朋友 n+ 1 颗糖果，第二个小朋友 n+ 2 颗，依此类推，直到给最后一个小朋友 2 * n颗糖果。
 * 重复上述过程（每次都比上一次多给出一颗糖果，当到达队伍终点后再次从队伍起点开始），直到我们分完所有的糖果。注意，就算我们手中的剩下糖果数不够（不比前一次发出的糖果多），这些糖果也会全部发给当前的小朋友。
 * 返回一个长度为 num_people、元素之和为 candies 的数组，以表示糖果的最终分发情况（即 ans[i] 表示第 i 个小朋友分到的糖果数）。
 *
 * @see <a href="https://leetcode.cn/problems/distribute-candies-to-people">分糖果 II</a>
 */
fun distributeCandies(candies: Int, num_people: Int): IntArray {
    var candies = candies
    var curr_give = 0
    val res = IntArray(num_people)

    while (candies > 0) {
        res[curr_give % num_people] += Math.min(++curr_give, candies)
        candies -= curr_give
    }

    return res
}