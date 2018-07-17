package com.example.demo.thread;

/**
 * @author SongJiuHua.
 * @date Created on 2018/5/9.
 * @description 守护线程：当JVM中没有用户线程时，守护线程会立即退出（无论是否执行完），只要有一个用户线程还在执行，守护线程就不会退出
 */
public class DeamonThreadTest {

    public static void main(String[] args) {
        //创建一个用户线程
        Thread userThread=new Thread(){
            @Override
            public void run(){
                for(int i=1;i<=5;i++){
                    try {
                        Thread.sleep(200);
                        System.out.println("用户线程第"+i+"次运行.....");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("用户线程退出.....");
            }
        };
        //创建一个用户模拟守护线程的线程
        Thread daemonThread= new Thread(() -> {
            while (true){
                System.out.println(".....守护线程.....");
            }
        });
        //让daemonThread成为守护线程
        daemonThread.setDaemon(true);//这里必须在启动前设置，如果不设置，默认人是用户线程
        userThread.start();
        daemonThread.start();
    }

}
