// 验证
fun main(args: Array<String>) {
    val arr = intArrayOf(0, 0, 1, 1, 1, 2, 2, 3, 3, 4)
    val size = removeDuplicates(arr)
    arr.copyOfRange(0, size).forEach {
        println(it)
    }
}