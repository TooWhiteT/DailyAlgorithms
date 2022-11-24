/**
 * 设计哈希映射
 *
 * 不使用任何内建的哈希表库设计一个哈希映射（HashMap）。
 * 实现 MyHashMap 类：
 *
 * MyHashMap() 用空映射初始化对象
 *
 * void put(int key, int value) 向 HashMap 插入一个键值对 (key, value) 。如果 key 已经存在于映射中，则更新其对应的值 value 。
 *
 * int get(int key) 返回特定的 key 所映射的 value ；如果映射中不包含 key 的映射，返回 -1 。
 *
 * void remove(key) 如果映射中存在 key 的映射，则移除 key 和它所对应的 value 。
 *
 * @see <a href="https://leetcode.cn/problems/design-hashmap">设计哈希映射</a>
 */
class MyHashMap() { // 手写一个红黑树

    private val arr = IntArray(10000000){-1}

    fun put(key: Int, value: Int) {
        arr[key] = value
    }

    fun get(key: Int): Int {
        return arr[key]
    }

    fun remove(key: Int) {
        arr[key] = -1
    }

}

/**
 * 转换成小写字母
 *
 * 给你一个字符串 s ，将该字符串中的大写字母转换成相同的小写字母，返回新的字符串。
 *
 * @see <a href="https://leetcode.cn/problems/to-lower-case/">转换成小写字母</a>
 */
fun toLowerCase(s: String): String {
//    大写变小写、小写变大写 : 字符 ^= 32
//    大写变小写、小写变小写 : 字符 |= 32
//    小写变大写、大写变大写 : 字符 &= -33
    val temp = s.toCharArray()
    val rangeUp = 65 .. 90 // 大写字母区间
    val rangeLow = 97 .. 122 // 小写字母区间
    for (i in temp.indices) {
        if (temp[i].toInt() in rangeUp) {
            temp[i] = (temp[i].toInt() or 32).toChar()
        }
    }
    return String(temp)
}

/**
 * 1 比特与 2 比特字符
 *
 * 有两种特殊字符：
 *
 * 第一种字符可以用一比特 0 表示
 *
 * 第二种字符可以用两比特（10 或 11）表示
 *
 * 给你一个以 0 结尾的二进制数组 bits ，如果最后一个字符必须是一个一比特字符，则返回 true 。
 *
 * @see <a href="https://leetcode.cn/problems/1-bit-and-2-bit-characters">1 比特与 2 比特字符</a>
 */
fun isOneBitCharacter(bits: IntArray): Boolean {
    var start = 0
    while (start < bits.size - 1) {
        if (bits[start] == 0) {
            start++
        } else {
            start += 2
        }
    }
    return start == bits.size - 1
}

/**
 * 自除数
 *
 * 自除数是指可以被它包含的每一位数整除的数。
 *
 * 例如，128 是一个 自除数 ，因为 128 % 1 == 0，128 % 2 == 0，128 % 8 == 0。
 * 自除数 不允许包含 0 。
 * 给定两个整数 left 和 right ，返回一个列表，列表的元素是范围 [left, right] 内所有的 自除数 。
 *
 * @see <a href="https://leetcode.cn/problems/self-dividing-numbers">自除数</a>
 */
fun selfDividingNumbers(left: Int, right: Int): List<Int> {
    val res = ArrayList<Int> ()
    for (i in left..right) {
        if (getValue(i)) {
            res.add(i)
        }
    }
    return res
}

fun getValue(n: Int): Boolean {
    var n = n
    val res = n
    while (n > 0) {
        val temp = n % 10
        if (temp == 0) {
            return false
        }
        if (temp != 0 && res % temp != 0) {
            return false
        }
        n = n / 10
    }
    return true
}

/**
 * 区间子数组个数
 *
 * 给你一个整数数组 nums 和两个整数：left 及 right 。找出 nums 中连续、非空且其中最大元素在范围 [left, right] 内的子数组，并返回满足条件的子数组的个数。
 * 生成的测试用例保证结果符合 32-bit 整数范围。
 *
 * @see <a href="https://leetcode.cn/problems/number-of-subarrays-with-bounded-maximum">区间子数组个数</a>
 */
fun numSubarrayBoundedMax(nums: IntArray, left: Int, right: Int): Int {
    var j = -1
    var temp = 0
    var ans = 0
    for (i in 0 until nums.size) {
        if (nums[i] > right) {
            j = i
        }
        if (nums[i] >= left) {
            temp = i - j
        }
        ans += temp
    }
    return ans
}