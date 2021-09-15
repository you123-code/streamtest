package com.you;

import com.you.pojo.Person;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author youwei
 * @version 1.0
 * @date 2021/9/8 9:22
 */
public class StreamTest {
    @Test
     public void streamTest1(){
         List<Integer> list = Arrays.asList(7,6,9,3,8,2,1);
         //遍历输出符合条件的元素
         list.stream().filter(x -> x > 6).forEach(System.out::println);
         //匹配第一个
         Optional<Integer> findFirst = list.stream().filter(x -> x > 6).findFirst();
         //匹配任意（适用于并行流）
         Optional<Integer> findAny = list.parallelStream().filter(x -> x > 6).findAny();
         //是否包含符合特定的条件的元素
         boolean anyMatch = list.stream().anyMatch(x -> x < 6);
         System.out.println("匹配第一个值："+ findFirst.get());
         System.out.println("匹配任意一个值："+ findAny.get());
         System.out.println("是否存在大于6的值："+ anyMatch);
     }
     @Test
     public void streamTest2(){
        List<Integer> list = Arrays.asList(6,7,3,8,1,2,9);
         Stream<Integer> stream = list.stream();
         stream.filter(x -> x > 7).forEach(System.out::println);
     }
     @Test
    public  void streamTest3(){
         List<Person> personList = new ArrayList<Person>();
         personList.add(new Person("Tom",8900,20,"male","New York"));
         personList.add(new Person("Jack",7000,20,"male","New York"));
         personList.add(new Person("Lily",7900,20,"female","New York"));
         personList.add(new Person("Anni",6900,20,"female","New York"));
         personList.add(new Person("Owen",7900,20,"male","New York"));
         personList.add(new Person("Alisa",9000,20,"female","New York"));
         List<String> firstList = personList.stream().filter(x -> x.getSalary() > 8000).map(Person::getName)
                 .collect(Collectors.toList());
         System.out.println("薪资高于8000的员工姓名 ："+firstList);
     }
     @Test
    public void streamTest4(){
        List<String> list = Arrays.asList("a","bb","ccc");
            Optional<String> max = list.stream().max(Comparator.comparing(String::length));
            System.out.println("最长的字符串"+max.get());
     }
     @Test
    public void  streamTest5(){
        List<Integer> list = Arrays.asList(1,2,3,4,5);
        //自然排序
        Optional<Integer> max = list.stream().max(Integer::compareTo);
        //自定义排序
         Optional<Integer> max2 = list.stream().max(new Comparator<Integer>() {
             @Override
             public int compare(Integer o1, Integer o2) {
                        return o1.compareTo(o2);
             }
         });
         System.out.println("自然排序的最大值："+max.get());
         System.out.println("自定义排序的最大值："+max2.get());
    }
    @Test
    public void streamTest6(){
        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("Tom",8900,20,"male","New York"));
        personList.add(new Person("Jack",7000,20,"male","New York"));
        personList.add(new Person("Lily",7900,20,"female","New York"));
        personList.add(new Person("Anni",6900,20,"female","New York"));
        personList.add(new Person("Owen",7900,20,"male","New York"));
        personList.add(new Person("Alisa",9000,20,"female","New York"));
        Optional<Person> max = personList.stream().max(Comparator.comparingInt(Person::getSalary));
        //Optional<Integer> max = list.stream().max(Integer::compareTo);
        System.out.println(
                "员工工资最大值："+max.get().getSalary()
        );
    }
    @Test
    public void streamTest7(){
        List<Integer> list = Arrays.asList(7,4,8,9,2,1,0);
        long count =  list.stream().filter(x -> x > 6).count();
        System.out.println("list中大于6的个数"+count);
    }
    @Test
    public void streamTest8(){
        String[] strArr = {"aaa","bbb","ccc"};
        List<String> strList = Arrays.stream(strArr).map(String::toUpperCase).collect(Collectors.toList());

        List<Integer> intList = Arrays.asList(1,2,3,5,7,0);
        List<Integer> intListNew = intList.stream().map(x -> x+3).collect(Collectors.toList());
        System.out.println("每个元素大写"+strList);
        System.out.println("每个元素加3"+intListNew);
    }
    @Test
    public void streamTest9(){
        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("Tom",8900,20,"male","New York"));
        personList.add(new Person("Jack",7000,20,"male","New York"));
        personList.add(new Person("Lily",7900,20,"female","New York"));
        personList.add(new Person("Anni",6900,20,"female","New York"));
        personList.add(new Person("Owen",7900,20,"male","New York"));
        personList.add(new Person("Alisa",9000,20,"female","New York"));
        //不改变原来员工集合的方式
        List<Person> personListNew = personList.stream().map(person -> {
            Person personNew = new Person(person.getName(),0,0,null,null);
            personNew.setSalary(person.getSalary()+3000);
            return personNew;
        }).collect(Collectors.toList());
        System.out.println("改动前"+personList.get(0).getName()+"-->"+personList.get(0).getSalary());
        System.out.println("改动后"+personListNew.get(0).getName()+"-->"+personListNew.get(0).getSalary());

        //改变原来员工的集合方式
        List<Person> personListNew1 = personList.stream().map(person -> {
            person.setSalary(person.getSalary()+4000);
            return person;
        }).collect(Collectors.toList());
        System.out.println("改动后"+personListNew1.get(0).getName()+"-->"+personListNew1.get(0).getSalary());
    }
    @Test
    public void streamTest10(){
        List<String> list = Arrays.asList("1,2,3","4,5");
        List<String> listNew = list.stream().flatMap(s -> {
            //将每一个元素转换为stream
            String[] spit = s.split(",");
            Stream<String> s2 = Arrays.stream(spit);
            return s2;
        }).collect(Collectors.toList());
        System.out.println("改变前"+"-->"+list);
        System.out.println("改变后"+"-->"+listNew);
    }
    @Test
    public void streamTest11(){
        List<Integer>  list = Arrays.asList(1,2,3,5);
        //求和第一种方法
        Optional<Integer> sum = list.stream().reduce((x,y) -> x+y);
        //第二种求和方法
        Optional<Integer> sum1 = list.stream().reduce(Integer::sum);
        //第三种求和方式
        Integer sum2 = list.stream().reduce(0,Integer::sum);
        System.out.println("第一种 "+sum);
        System.out.println("第二种"+sum1);
        System.out.println("第三种"+sum2);
        //求乘积
        Optional<Integer> sum3 = list.stream().reduce((x,y) -> x * y);
        System.out.println("乘积"+sum3);
        //求最大值
        Optional<Integer> max = list.stream().reduce((x,y) -> x > y ? x : y);
        System.out.println("最大值"+max);
        //求最大值2
        Integer max1 = list.stream().reduce(1, Integer::max);
        System.out.println("最大值2"+" "+max1);
    }
    @Test
    public void streamTest12(){
        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("Tom",8900,20,"male","New York"));
        personList.add(new Person("Jack",7000,20,"male","New York"));
        personList.add(new Person("Lily",7900,20,"female","New York"));
        personList.add(new Person("Anni",6900,20,"female","New York"));
        personList.add(new Person("Owen",7900,20,"male","New York"));
        personList.add(new Person("Alisa",9000,20,"female","New York"));
        //求工资之和1
        Optional<Integer> sumSalary = personList.stream().map(Person::getSalary).reduce(Integer::sum);
        //求工资之和2
        Integer sumSalary1 = personList.stream().reduce(0,(sum,p) -> sum += p.getSalary(),(sum1,sum2) -> sum1+sum2);
        //求工资之和3
        Integer sumSalary2 = personList.stream().reduce(0,(sum,p) -> sum +=p.getSalary(), Integer::sum);
        //求最高工资1
        Integer maxSalary1 = personList.stream().reduce(0,(max,p) -> max > p.getSalary() ? max : p.getSalary(),(max1,max2) -> max1 > max2 ? max1 : max2);
        //求最高工资2
        Integer maxSalary2 = personList.stream().reduce(0,(max,p) -> max > p.getSalary() ? max : p.getSalary(),Integer::max);
        System.out.println("工资之和"+sumSalary.get()+","+sumSalary1+","+sumSalary2);
        System.out.println("最大值"+maxSalary1+","+sumSalary2);
    }
    @Test
    public  void streamTest13(){
        List<Integer> list = Arrays.asList(1,3,5,4,6,8);
        List<Integer> listNew = list.stream().filter(x -> x % 2 == 0).collect(Collectors.toList());
        Set<Integer> set = list.stream().filter(x -> x % 2 == 0).collect(Collectors.toSet());

        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("Tom",8900,20,"male","New York"));
        personList.add(new Person("Jack",7000,20,"male","New York"));
        personList.add(new Person("Lily",7900,20,"female","New York"));
        personList.add(new Person("Anni",6900,20,"female","New York"));
        personList.add(new Person("Owen",7900,20,"male","New York"));
        personList.add(new Person("Alisa",9000,20,"female","New York"));
        Map<?,Person> map = personList.stream().filter(p -> p.getSalary() > 8000)
                .collect(Collectors.toMap(Person::getName,p -> p));
        System.out.println("toList"+listNew);
        System.out.println("toSet"+set);
        System.out.println("toMap"+map);
    }
    @Test
    public void streamTest14(){
        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("Tom",8900,20,"male","New York"));
        personList.add(new Person("Jack",7000,20,"male","New York"));
        personList.add(new Person("Lily",7900,20,"female","New York"));
        // 求总数
        Long count = personList.stream().collect(Collectors.counting());
        //求平均工资
        Double average = personList.stream().collect(Collectors.averagingDouble(Person::getSalary));
        //求最高工资
        Optional<Integer> max = personList.stream().map(Person::getSalary).collect(Collectors.maxBy(Integer::compare));
        //求工资之和
        Integer sum = personList.stream().collect(Collectors.summingInt(Person::getSalary));
        //一次性统计所有信息
        DoubleSummaryStatistics collect = personList.stream().collect(Collectors.summarizingDouble(Person::getSalary));
        System.out.println("员工总数"+count);
        System.out.println("平均工资"+average);
        System.out.println("最高工资"+max);
        System.out.println("工资之和"+sum);
        System.out.println("所有信息"+collect);
    }
    @Test
    public void streamTest15(){
        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("Tom",8900,20,"male","New York"));
        personList.add(new Person("Jack",7000,20,"male","New York"));
        personList.add(new Person("Lily",7900,20,"female","New York"));
        personList.add(new Person("Anni",6900,20,"female","New York"));
        personList.add(new Person("Owen",7900,20,"male","New York"));
        personList.add(new Person("Alisa",9000,20,"female","New York"));
        //将员工薪资是否高于8000分组
        Map<Boolean,List<Person>> part = personList.stream().collect(Collectors.partitioningBy(x -> x.getSalary() > 8000));
        //按性别分组
        Map<String,List<Person>> group = personList.stream().collect(Collectors.groupingBy(Person::getSex));
        //按性别分组再按地区分组
        Map<String,Map<String,List<Person>>> group2 = personList.stream().collect(Collectors.groupingBy(Person::getSex,Collectors.groupingBy(Person::getArea)));
        //Map<String,Map<String,Map<String,List<Person>>>> group3 = personList.stream().collect(Collectors.groupingBy(Person::getSex,Collectors.groupingBy(Person::getArea,Collectors.groupingBy())));
        //Map<String,Map<String,Map<String,List<Person>>>> group4 = personList.stream().collect();
        System.out.println("将员工薪资是否高于8000分组"+part);
        System.out.println("按性别分组"+group);
        System.out.println("按性别分组再按地区分组"+group2);
    }
    @Test
    public void streamTest16(){
        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("Tom",8900,20,"male","New York"));
        personList.add(new Person("Jack",7000,20,"male","New York"));
        personList.add(new Person("Lily",7900,20,"female","New York"));
        personList.add(new Person("Anni",6900,20,"female","New York"));
        personList.add(new Person("Owen",7900,20,"male","New York"));
        personList.add(new Person("Alisa",9000,20,"female","New York"));
        String name2s = personList.stream().map(p -> p.getName()).collect(Collectors.joining(","));
        System.out.println("所有员工的姓名"+name2s);
        List<String> list = Arrays.asList("A","B","C");
        String name2 = list.stream().collect(Collectors.joining("-"));
        System.out.println("拼接"+name2);
    }
    @Test
    public void streamTest17(){
        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("Tom",8900,20,"male","New York"));
        personList.add(new Person("Jack",7000,20,"male","New York"));
        personList.add(new Person("Lily",7900,20,"female","New York"));
        personList.add(new Person("Anni",6900,20,"female","New York"));
        personList.add(new Person("Owen",7900,20,"male","New York"));
        personList.add(new Person("Alisa",9000,20,"female","New York"));
        //每个员工减去起增点后的薪资之和
        Integer sum = personList.stream().collect(Collectors.reducing(0,Person::getSalary,(i,j) -> (i + j - 5000)));
        System.out.println("员工扣税后的薪资总和"+sum);
        //stream的reduce
        Optional<Integer> sum1 = personList.stream().map(Person::getSalary).reduce(Integer::sum);
        System.out.println("员工薪资之和"+sum1);
    }
    @Test
    public void streamTest18(){
        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("Tom",8900,20,"male","New York"));
        personList.add(new Person("Jack",7000,20,"male","New York"));
        personList.add(new Person("Lily",7900,20,"female","New York"));
        personList.add(new Person("Anni",6900,20,"female","New York"));
        personList.add(new Person("Owen",7900,20,"male","New York"));
        personList.add(new Person("Alisa",9000,20,"female","New York"));
        //按工资升序排列
        List<String> newList = personList.stream().sorted(Comparator.comparing(Person::getSalary)).map(Person::getName).collect(Collectors.toList());
        // 按工资倒序排序
        List<String> newList1 = personList.stream().sorted(Comparator.comparing(Person::getSalary).reversed()).map(Person::getName).collect(Collectors.toList());
        // 先按工资再按年龄升序排序
        List<String> newList2 = personList.stream().sorted(Comparator.comparing(Person::getSalary).thenComparing(Person::getAge)).map(Person::getName).collect(Collectors.toList());
        // 先按工资再按年龄自定义排序（降序）
        List<String> newList3 = personList.stream().sorted((p1,p2)->{
            if(p1.getSalary() == p2.getSalary()){
                return p2.getAge() - p1.getAge();
            }else{
                return p2.getSalary() - p1.getSalary();
            }
        }).map(Person::getName).collect(Collectors.toList());
        System.out.println("按工资升序排列"+newList);
        System.out.println("按工资倒序排序"+newList1);
        System.out.println("先按工资再按年龄升序排序"+newList2);
        System.out.println("先按工资再按年龄自定义排序（降序"+newList3);
    }
    @Test
    public void streamTest19(){
        String[] arr1 = {"a","b","c","d"};
        String[] arr2 = {"e","f","g","h"};
        Stream<String> sre1 = Stream.of(arr1);
        Stream<String> sre2 = Stream.of(arr2);
        //concat:合并两个流
        List<String> newList = Stream.concat(sre1,sre2).distinct().collect(Collectors.toList());
        //limit:限制从流中获得n个数据
        List<Integer> collect = Stream.iterate(1,x -> x+2).limit(10).collect(Collectors.toList());
        //skip:跳过前n个数据
        List<Integer> collect1 = Stream.iterate(1,x -> x + 2).skip(1).limit(5).collect(Collectors.toList());
        System.out.println(""+newList);
        System.out.println(""+collect);
        System.out.println(""+collect1);

    }
}
