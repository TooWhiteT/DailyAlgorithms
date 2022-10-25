public class Test {
    public static void main(String[] args) {
        String userName = "123456789asd";
        // 全为英文 返回true  否则false
        boolean result1 = userName.matches("[a-zA-Z]+");
//        boolean result2 = userName.matches("[0-9a-zA-Z]+");
        // 全为数字 返回true 否则false
        boolean result6 = userName.matches("[0-9]+");
        System.out.println(result1);
//        System.out.println(result2);
        System.out.println(result6);
        System.out.println(false && true);
        System.out.println(false || true);
    }
}
