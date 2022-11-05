
import java.util.*


/**
 * 解析布尔表达式
 *
 * 给你一个以字符串形式表述的 布尔表达式（boolean） expression，返回该式的运算结果。
 *
 * 有效的表达式需遵循以下约定：
 *
 * "t"，运算结果为 True
 * "f"，运算结果为 False
 * "!(expr)"，运算过程为对内部表达式 expr 进行逻辑 非的运算（NOT）
 * "&(expr1,expr2,...)"，运算过程为对 2 个或以上内部表达式 expr1, expr2, ... 进行逻辑 与的运算（AND）
 * "|(expr1,expr2,...)"，运算过程为对 2 个或以上内部表达式 expr1, expr2, ... 进行逻辑 或的运算（OR）
 *
 * @see <a href="https://leetcode.cn/problems/parsing-a-boolean-expression">解析布尔表达式</a>
 */
fun parseBoolExpr(expression: String): Boolean {
    val op = Stack<Char>()
    var temp = ""
    for (i in 0 until expression.length) {
        val c = expression[i]
        if (c == ')') {
            while (op.peek() != '(') {
                temp = op.pop().toString() + temp
            }
            op.pop()
            val operation = op.pop()
            op.push(isBool(temp, operation))
            temp = ""
        } else if (c != ',') {
            op.push(c)
        }
    }
    return if (op.peek() == 't') true else false
}

fun isBool(s: String, op: Char): Char {
    if (op == '!') {
        return if (s == "f") {
            't'
        } else {
            'f'
        }
    } else if (op == '|') {
        return if (s.indexOf('t') != -1) {
            't'
        } else {
            'f'
        }
    } else if (op == '&') {
        return if (s.indexOf('f') != -1) {
            'f'
        } else {
            't'
        }
    }
    return 'f'
}

/**
 * 用栈实现队列
 *
 * 请你仅使用两个栈实现先入先出队列。队列应当支持一般队列支持的所有操作（push、pop、peek、empty）：
 *
 * 实现 MyQueue 类：
 *
 * void push(int x) 将元素 x 推到队列的末尾
 * int pop() 从队列的开头移除并返回元素
 * int peek() 返回队列开头的元素
 * boolean empty() 如果队列为空，返回 true ；否则，返回 false
 *
 * @see <a href="https://leetcode.cn/problems/implement-queue-using-stacks">用栈实现队列</a>
 */
class MyQueue() {
    val queueIn = Stack<Int>()
    val queueOut = Stack<Int>()

    fun push(x: Int) {
        queueIn.push(x)
    }

    fun pop(): Int {
        // 如果queueOut栈为空，则将queueIn栈全部弹出并压入queueOut栈中，然后queueOut.pop()
        if (queueOut.isEmpty()) {
            while (!queueIn.isEmpty()) {
                queueOut.push(queueIn.pop())
            }
        }
        return queueOut.pop()
    }

    fun peek(): Int {
        if (queueOut.isEmpty()) {
            while (!queueIn.isEmpty()) {
                queueOut.push(queueIn.pop())
            }
        }
        return queueOut.peek()
    }

    fun empty(): Boolean = queueIn.isEmpty() && queueOut.isEmpty()

}

/**
 * 二叉树的所有路径
 *
 * 给你一个二叉树的根节点 root ，按 任意顺序 ，返回所有从根节点到叶子节点的路径。
 * 叶子节点 是指没有子节点的节点。
 *
 * @see <a href="https://leetcode.cn/problems/binary-tree-paths/">二叉树的所有路径</a>
 */
fun binaryTreePaths(root: TreeNode?): List<String> {
    val ret: MutableList<String> = ArrayList()
    if (root == null) return ret
    solve(root, "", ret)
    return ret
}

fun solve(root: TreeNode?, cur: String, ret: MutableList<String>) {
    var cur = cur
    if (root == null) return
    cur += root.`val`
    if (root.left == null && root.right == null) {
        ret.add(cur)
    } else {
        solve(root.left, "$cur->", ret)
        solve(root.right, "$cur->", ret)
    }
}

/**
 * 各位相加
 *
 * 给定一个非负整数 num，反复将各个位上的数字相加，直到结果为一位数。返回这个结果。
 *
 * @see <a href="https://leetcode.cn/problems/add-digits/">各位相加</a>
 */
fun addDigits(num: Int): Int {
    return ((num - 1) % 9) + 1
}

/**
 * 丑数
 *
 * 丑数 就是只包含质因数 2、3 和 5 的正整数。
 * 给你一个整数 n ，请你判断 n 是否为 丑数 。如果是，返回 true ；否则，返回 false 。
 *
 * @see <a href="https://leetcode.cn/problems/ugly-number/">丑数</a>
 */
fun isUgly(n: Int): Boolean {
    if (n < 1) return false
    var temp = n
    while (temp % 5 == 0) {
        temp /= 5
    }
    while (temp % 3 == 0) {
        temp /= 3
    }
    while (temp % 2 == 0) {
        temp = temp shr 1
    }
    return temp == 1
}