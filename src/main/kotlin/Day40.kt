import java.util.*
import kotlin.collections.ArrayList

/**
 * 判断一个数字是否可以表示成三的幂的和
 *
 * 给你一个整数n，如果你可以将n表示成若干个不同的三的幂之和，请你返回true，否则请返回 false。
 * 对于一个整数 y，如果存在整数 x满足 y == 3x，我们称这个整数 y是三的幂。
 *
 * @see <a href="https://leetcode.cn/problems/check-if-number-is-a-sum-of-powers-of-three/">判断一个数字是否可以表示成三的幂的和</a>
 */
fun checkPowersOfThree(n: Int): Boolean {
    // 如果一个数字可以表示成三的幂的和，那么这个数字转换为三进制后，数字应该只有0和1
    var n = n
    while (n > 0) {
        if (n % 3 == 2) {
            return false
        }
        n /= 3
    }
    return true
}

/**
 * 将数组分成和相等的三个部分
 *
 * 给你一个整数数组 arr，只有可以将其划分为三个和相等的 非空 部分时才返回true，否则返回 false。
 * 形式上，如果可以找出索引i + 1 < j且满足(arr[0] + arr[1] + ... + arr[i] == arr[i + 1] + arr[i + 2] + ... + arr[j - 1] == arr[j] + arr[j + 1] + ... + arr[arr.length - 1])就可以将数组三等分。
 *
 * @see <a href="https://leetcode.cn/problems/partition-array-into-three-parts-with-equal-sum/">将数组分成和相等的三个部分</a>
 */
fun canThreePartsEqualSum(arr: IntArray): Boolean {
    var sum = 0
    for (num in arr) {
        sum += num
    }
    // 数组A的和如果不能被3整除返回false
    if (sum % 3 != 0) {
        return false
    }
    // 遍历数组累加，每累加到目标值cnt加1，表示又找到1段,
    // 找到2段后就返回true（i只能到数组A的倒数第二个元素，保证了有第3段）
    sum /= 3
    var curSum = 0
    var cnt = 0
    for (i in 0 until arr.size - 1) {
        curSum += arr[i]
        if (curSum == sum) {
            cnt++
            if (cnt == 2) {
                return true
            }
            curSum = 0
        }
    }
    return false
}

/**
 * 可被 5 整除的二进制前缀
 *
 * 给定一个二进制数组 nums (索引从0开始)。
 * 我们将xi定义为其二进制表示形式为子数组nums[0..i](从最高有效位到最低有效位)。
 * 例如，如果 nums =[1,0,1] ，那么x0= 1,x1= 2, 和x2= 5。
 * 返回布尔值列表answer，只有当xi可以被 5整除时，答案answer[i] 为true，否则为 false。
 *
 * @see <a href="https://leetcode.cn/problems/binary-prefix-divisible-by-5">可被 5 整除的二进制前缀</a>
 */
fun prefixesDivBy5(nums: IntArray): List<Boolean> {
    val ans = ArrayList<Boolean>()
    var num = 0
    for (i in 0 until nums.size) {
        num = num shl 1
        num += nums[i]
        num %= 10
        ans.add(num % 5 == 0)
    }
    return ans
}

/**
 * 删除最外层的括号
 *
 * 有效括号字符串为空 ""、"(" + A + ")"或A + B ，其中A 和B都是有效的括号字符串，+代表字符串的连接。
 *      例如，""，"()"，"(())()"和"(()(()))"都是有效的括号字符串。
 *
 * 如果有效字符串 s 非空，且不存在将其拆分为 s = A + B的方法，我们称其为原语（primitive），其中A 和B都是非空有效括号字符串。
 * 给出一个非空有效字符串 s，考虑将其进行原语化分解，使得：s = P_1 + P_2 + ... + P_k，其中P_i是有效括号字符串原语。
 * 对 s 进行原语化分解，删除分解中每个原语字符串的最外层括号，返回 s 。
 *
 * @see <a href="https://leetcode.cn/problems/remove-outermost-parentheses">删除最外层的括号</a>
 */
fun removeOuterParentheses(s: String): String {
    var res = ""
    val mystack = Stack<Char>()
    for (i in 0 until s.length) {
        if (s[i] == ')') {
            mystack.pop()
        }
        if (!mystack.empty()) {
            res += s[i]
        }
        if (s[i] == '(') {
            mystack.push('(')
        }
    }
    return res
}

/**
 * 从根到叶的二进制数之和
 *
 * 给出一棵二叉树，其上每个结点的值都是0或1。每一条从根到叶的路径都代表一个从最高有效位开始的二进制数。
 *      例如，如果路径为0 -> 1 -> 1 -> 0 -> 1，那么它表示二进制数01101，也就是13。
 *
 * 对树上的每一片叶子，我们都要找出从根到该叶子的路径所表示的数字。
 * 返回这些数字之和。题目数据保证答案是一个 32 位 整数。
 *
 * @see <a href="https://leetcode.cn/problems/sum-of-root-to-leaf-binary-numbers">从根到叶的二进制数之和</a>
 */
fun sumRootToLeaf(root: TreeNode?): Int {
    if (root == null) return 0
    val mod = root.`val` % 1000000007
    if (root.left == null && root.right == null) return mod
    if (root.left != null) root.left!!.`val` += mod shl 1
    if (root.right != null) root.right!!.`val` += mod shl 1

    return (sumRootToLeaf(root.left) + sumRootToLeaf(root.right)) % 1000000007
}