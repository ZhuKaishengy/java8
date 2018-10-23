package com.percent.coreinterface;

import com.percent.Predict;
import org.junit.Test;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * java8内置4大核心函数式接口
 *
 * Consumer<T> ：消费型接口
 * void accept(T t);
 * Supplier<T> ：供给型接口
 * T get();
 * Function<T,R> ：函数型接口
 * R apply(T t)
 * Predicate<T> ：断言型接口
 * boolean test(T t);
 *
 * @author: kaisheng.zhu
 * @Date: 2018-8-5 14:26
 * @Description:
 */
public class CoreInterfaceTest<T,R> {

    /**
     * Consumer：消费型接口
     * 特点，有入参无返回值，处理结果无法返回，只能是处理过程中改变原有参数，这个参数的改变无法赋给内部类之外的变量
     * 适合处理**集合，对象**，这种改变重新作用于输入值的
     * @param consumer consumer
     * @param t t
     */
    private void consume (T t ,Consumer<T> consumer) {
        consumer.accept(t);
    }

    /**
     * Supplier:供给型接口
     * 特点：没有入参，有返回值，用于初始化一些数据，初始化方式不依赖外部参数
     * @param num
     * @param supplier
     * @return
     */
    public List<Integer> supply (Integer num, Supplier<Integer> supplier) {

        List<Integer> list = new ArrayList<>(num);
        for (int i = 0; i < num; i++) {
            Integer val = supplier.get();
            list.add(val);
        }
        return list;
    }

    /**
     * Function:函数型接口
     * 特点：有入参，有返回值，及其灵活
     * @param t
     * @param function
     * @return
     */
    public R func (T t, Function<T,R> function) {
        return function.apply(t);
    }

    /**
     * Predict:断言型接口
     * 特点：有入参，返回值为boolean
     * @param predict
     * @return
     */
    public List<String> predict (List<String> strs, Predict<String> predict) {
        ArrayList<String> list = new ArrayList<>();
        for (String str : strs) {
            if (predict.match(str)) {
                list.add(str);
            }
        }
        return list;
    }

    /**
     * 使用consumer消费string，不合适，截取的值无法传递回来
     */
    @Test
    public void testConsume1() {

        String word = "abcdef";
        CoreInterfaceTest<String,?> method = new CoreInterfaceTest<>();
        method.consume(word, (str) -> {
            str = str.substring(0,3);
            System.out.println(str);
        });
        System.out.println(word);
    }

    /**
     * 可以使用supplier或者function
     */
    @Test
    public void testConsume1Again() {
        String word = "abcdef";
        String str1 = this.substrSupplier(() -> word.substring(0, 1));
        String str2 = this.substrFunc(word, str -> str.substring(0, 1));
        System.out.println(str1);
        System.out.println(str2);
    }

    /**
     * supplier
     */
    private String substrSupplier(Supplier<String> supplier){
        return supplier.get();
    }

    /**
     * function
     */
    private String substrFunc(String source, Function<String,String> function){
        return function.apply(source);
    }

    /**
     * 使用consumer消费对象，修改对象的属性，传入的值也发生变化
     */
    @Test
    public void testConsume2 (){

        Person p = new Person().setId("1001").setName("zks");
        CoreInterfaceTest<Person,?> method = new CoreInterfaceTest<>();
        method.consume(p, (person) -> person.setName("haha"));
        System.out.println(p);
    }

    /**
     * 初始化集合
     */
    private List<String> strs = new ArrayList<>(4);
    {
        strs.add("zks");
        strs.add("sjx");
        strs.add("rl");
        strs.add("haha");
    }

    /**
     * 使用consumer消费集合
     * 使用{@code list.removeIf(str -> Objects.equals("haha",str))}相当于使用iterator遍历，不可以使用增强for和foreach遍历
     */
    @Test
    public void testConsume3(){

        CoreInterfaceTest<List<String>,?> method = new CoreInterfaceTest<>();
        method.consume(strs,list -> {
            list.removeIf(str -> Objects.equals("haha",str));
        } );
        System.out.println(strs);
    }

    /**
     * 集合的操作一般使用stream api
     */
    @Test
    public void testStream(){
        List<String> result = strs.stream().filter(name -> !Objects.equals(name, "sjx"))
                .collect(Collectors.toList());
        System.out.println(result);
    }

    @Test
    public void test1(){
        // 集合使用正常初始化修改没有问题
        strs.removeIf(str -> Objects.equals(str,"haha"));
        System.out.println(strs);

        // 使用Arrays工具类不可修改，@throws UnsupportedOperationException
        List<String> list = Arrays.asList("a","b","c");
        list.removeIf(str -> Objects.equals(str,"a"));
        System.out.println(list);
    }

    @Test
    public void testSupply(){

        List<Integer> list = this.supply(20, () -> new Random().nextInt(10));
        System.out.println(list);
    }

    /**
     * 截取字符串
     */
    @Test
    public void testHandler1(){

        String str = "abcdef";
        CoreInterfaceTest<String, String> method = new CoreInterfaceTest<>();
        String result = method.func(str, tmp -> tmp.substring(0, 4));
        System.out.println(result);
    }

    /**
     * 创建集合
     */
    @Test
    public void testHandler2(){
        CoreInterfaceTest<Integer, List<Integer>> method = new CoreInterfaceTest<>();
        int num = 5;
        List<Integer> result = method.func(num, count -> {
            List<Integer> list = new ArrayList<>(count);
            for (int i = 0; i < count; i++) {
                list.add(i);
            }
            return list;
        });
        System.out.println(result);
    }

    @Test
    public void testPredict(){
        List<String> predict = this.predict(strs, str -> str.length() > 3);
        System.out.println(predict);
    }
}
