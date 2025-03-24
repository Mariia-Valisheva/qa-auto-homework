public class HomeworkJava {
    public static void main(String[] args) {
        int one = 1;
        int two = 22;
        int three = 34;
        int four = 3;
        double dd = 21.1;

        int x = Integer.MAX_VALUE;
        int y = Integer.MAX_VALUE;

        System.out.println(two + one - four);
        System.out.println(three/four);
        System.out.println(four * two);

        System.out.println(dd * four);
        System.out.println(three/dd);
        System.out.println(three-two/dd);

        System.out.println(one > dd);
        System.out.println(dd < three & dd > one);
        System.out.println(one == two);
        System.out.println(two >= four || two <= four);

//Переполнение
        System.out.println(x * y);
        System.out.println(x + one);
    }
}
