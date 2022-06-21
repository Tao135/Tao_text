package com.czt;

import java.util.Scanner;

public class test {

    private static final int bus_max = 20;              //汽车最大容量
    private static int num = 0;                         //初始化客车人数
    private static int onBus = 0;                       //上车人数
    private static int downBus = 0;                     //下车人数
    static Scanner sc = new Scanner(System.in);         //设置输入的变量

    //检查方法，检查输入的值，是否为退出系统的值
    public static void check(){
        if (onBus == -1 || downBus == -1) {
            System.out.println("汽车已到站,进程停止");
            System.out.println("全部乘客下车");
            System.out.println("下车的乘客人数为：" + num + "人");
            System.exit(1);               //退出系统
        }

    }

    //司机开车
    public static void start_dr(){
        System.out.println("司机开车！");
        System.out.println("售票员开始售票！");

    }

    //司机停车
    public static void stop_dr(){
        System.out.println("司机停车！");
        down_spy();

    }


    //上车售票员的信号量
    public static void on_spy(){
        System.out.println("前门售票员打开前车门，让乘客上车！");

        //超载人数
        int cz;

        //到站时，上车的人数
        System.out.println("请输入上车人数:");
        onBus = sc.nextInt();
        check();

        //不能输入无效数值
        while (onBus < -1){
            System.out.println("输入的上车人数不能小于 -1 ！！！");
            System.out.println("--------------------");
            System.out.println("请重新输入上车人数:");
            onBus = sc.nextInt();
            check();
        }

        //现在车上人数
        num += onBus - downBus;

        System.out.println("关闭前车门，并检查车上人数是否超载。");
        System.out.println("---------------------");

        //当车上人数没有超载时
        if (num <= bus_max){
            System.out.println("现在车上人数为:" + num + "人，未超载");
            System.out.println("准备开车！");
            System.out.println("------------------");

        }
        //当车上人数超载时
        else {
            //超载人数
            cz = num - bus_max;

            //因为超载，所以实际上车人和实际下车人需要更正
            downBus += cz;           //下车人数等于原下车人加上超载的人数
            onBus -= cz;             //上车人数等于原上车人减去超载的人数

            System.out.println("超载了:" + cz + "人。");
            System.out.println("请后上车的 " + cz + "人，等下一班车！");

            //车上的人数等于最大的人数
            num = bus_max;

            System.out.println("打开后车门，让" + cz + "人下车。");
            System.out.println("关闭后车门，汽车已坐满，准备发车!");
            System.out.println("------------------");

        }
    }

    //下车售票员的信号量
    public static void down_spy(){
        System.out.println("后门售票员打开车门，让下车乘客下车。");
        System.out.println("请输入下车人数:");
        downBus = sc.nextInt();
        check();

        //当下车人数大于车上所拥有的人数时
        while (downBus > num ){
            System.out.println("输入的下车人数大于现车上所拥有的人数！");
            System.out.println("--------------------");
            System.out.println("请重新输入下车人数:");
            downBus = sc.nextInt();
            check();
        }

        //不能输入无效数值
        while ( downBus < -1){
            System.out.println("输入的下车人数不能小于 -1 ！！！");
            System.out.println("--------------------");
            System.out.println("请重新输入下车人数:");
            downBus = sc.nextInt();
            check();
        }

        //当下车人数能成功操作时，执行上车售票员操作
        System.out.println("关闭后车门！");

    }

    public static void main(String[] args){
        System.out.println("设定汽车最大容量为:" + bus_max);
        System.out.println("上车数或下车数为-1 ，则结束程序");
        System.out.println("--------------------");

        //汽车未到站时，一直执行
        while (true){
            //前门售票员检测
            on_spy();

            //司机开车
            start_dr();

            System.out.println("-------------------");
            System.out.println("这一站上车人数为:" + onBus + "，下车人数为：" + downBus);
            System.out.println("--------------------");
            System.out.println("--------------------");
            System.out.println("--------------------");

            //司机停车
            stop_dr();
        }

    }
}
