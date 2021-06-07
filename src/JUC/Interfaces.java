package JUC;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Interfaces {
    public static void main(String[] args) {
//
// Function<String,String> function = new Function<String,String>() {
// @Override
// public String apply(String str) {
// return str;
// }
// };
        Function<String,String> function = (str)->{return str;};
        System.out.println(function.apply("asd"));
    }
}

class Demo02 {
    public static void main(String[] args) {
    // 判断字符串是否为空
    // Predicate<String> predicate = new Predicate<String>(){
    //// @Override
    //// public boolean test(String str) {
    //// return str.isEmpty();
    //// }
    //// };
        Predicate<String> predicate = (str)->{return str.isEmpty(); };
        System.out.println(predicate.test(""));
    }
}

class Demo03 {
    public static void main(String[] args) {
    // Consumer<String> consumer = new Consumer<String>() {
    // @Override
    // public void accept(String str) {
    // System.out.println(str);
    // }
    // };
        Consumer<String> consumer = (str)->{System.out.println(str);};
        consumer.accept("sdadasd");
    }
}

class Demo04 {
    public static void main(String[] args) {
    // Supplier supplier = new Supplier<Integer>() {
    // @Override
    // public Integer get() {
    // System.out.println("get()");
    // return 1024;
    // }
    // };
        Supplier supplier = ()->{ return 1024; };
        System.out.println(supplier.get());
    }
}