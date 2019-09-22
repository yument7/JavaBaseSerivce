package com.leach.advance.thread.basis.communication;

/**
 * @author Administrator
 * @name 概述：多线程通信实例 wait() notify()实现
 * 生产-消费问题
 * @date 2019/3/21 22:14
 */
public class Depot{
    // 仓库容量
    private int capacity;
    // 仓库实际存储数量
    private int size;

    public Depot(int capacity){
        this.capacity = capacity;
        this.size = 0;
    }

    // 生产方法
    public synchronized void produce(int goal){
        try{
            // need 表示“想要生产的数量”(有可能生产量太多，需多此生产)
            int need = goal;
            while(need > 0){
                // 库存已满时，等待“消费者”消费产品。
                while(size >= capacity)
                    wait();
                // 获取“实际生产的数量”(即库存中新增的数量)
                // 如果“库存”+“想要生产的数量”>“总的容量”，则“实际增量”=“总的容量”-“当前容量”。(此时填满仓库)
                // 否则“实际增量”=“想要生产的数量”
                int inc = (size + need) > capacity ? (capacity - size) : need;
                size += inc;
                need -= inc;
                System.out.printf("%s produce(%3d) --> unproduct=%3d, hasproducted=%3d, size=%3d\n",
                        Thread.currentThread().getName(), goal, need, inc, size);
                // 通知“消费者”可以消费了。
                notifyAll();
            }
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }


    // 消费方法
    public synchronized void consume(int goal){
        try{
            // need 表示“客户要消费数量”(有可能消费量太大，库存不够，需多此消费)
            int need = goal;
            while(need > 0){
                // 库存为0时，等待“生产者”生产产品。
                while(size <= 0)
                    wait();
                // 获取“实际消费的数量”(即库存中实际减少的数量)
                // 如果“库存”<“客户要消费的数量”，则“实际消费量”=“库存”；
                // 否则，“实际消费量”=“客户要消费的数量”。
                int dec = (size < need) ? size : need;
                size -= dec;
                need -= dec;
                System.out.printf("%s consume(%3d) <-- unconsume=%3d, hasconsumed=%3d, size=%3d\n",
                        Thread.currentThread().getName(), goal, need, dec, size);
                notifyAll();
            }
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public String toString() {
        return "capacity:"+capacity+", actual size:"+size;
    }
}
