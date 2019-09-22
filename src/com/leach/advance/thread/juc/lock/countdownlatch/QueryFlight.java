package com.leach.advance.thread.juc.lock.countdownlatch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author Administrator
 * @name 概述：以查询航班为例，
 * APP -> 查询 从上海到北京的航班 -> (东方航空，南方航空，海南航空，...) -> 所有航空查询完成之后，再统一返回查询结果。
 * 到每个航空公司查询余票都是独立的，是并发执行的，等价于从不同航空公司给APP提供的查询接口进行查询。
 * @date 2019/9/13 11:37
 */
public class QueryFlight {

    private static List<String> aviationCompany = Arrays.asList("东方航空", "南方航空", "海南航空");

    private static CountDownLatch count = new CountDownLatch(aviationCompany.size());

    public List<Ticket> service(String source, String dest) {
        ArrayList<Ticket> flightResult = new ArrayList<>(aviationCompany.size());

        Thread[] threads = new Thread[aviationCompany.size()];
        for (int i = 0; i < threads.length; i++) {
            String name = aviationCompany.get(i);
            threads[i] = new Thread(() -> {
                System.out.printf("从%s查询%s到%s的航班\n", name, source, dest);
                Ticket ticket = getTicketsService(name);
                flightResult.add(ticket);
                count.countDown(); // 查询完成一次，计数减一
            });

            threads[i].start();
        }

        return flightResult;

    }


    // 模拟各航空公司余票查询接口
    public Ticket getTicketsService(String name) {
        int number = new Random().nextInt(10);
        try {
            TimeUnit.SECONDS.sleep(number);
            return new Ticket(name, number);
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
    }

    public static void main(String[] args) throws InterruptedException{
        QueryFlight qf = new QueryFlight();
        List<Ticket> flight = qf.service("上海", "北京");
        count.await();
        System.out.println("====================查询结果如下=========================");
        flight.forEach(System.out::println);
    }

}

class Ticket {

    private String name;

    private int number;

    public Ticket(String name, int number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "name='" + name + '\'' +
                ", number=" + number +
                '}';
    }
}
