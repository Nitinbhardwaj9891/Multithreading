package com.java.monitor.lock.classlevel;

class MonitorLockDemo {
    private static int totalSeats;

    public static void initializeSeats(int seats) {
        totalSeats = seats;
    }

    public synchronized static void bookSeat(int seats) {
        if (seats <= totalSeats) {
            totalSeats -= seats;
            System.out.println(seats + " booked successfully");
        } else {
            System.out.println("Only " + totalSeats + " seats left, cannot book " + seats);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        MonitorLockDemo.initializeSeats(300);

        Thread t1 = new Thread(() -> MonitorLockDemo.bookSeat(150));
        Thread t2 = new Thread(() -> MonitorLockDemo.bookSeat(190));
        Thread t3 = new Thread(() -> MonitorLockDemo.bookSeat(10));
        Thread t4 = new Thread(() -> MonitorLockDemo.bookSeat(5));

        t1.start(); t2.start(); t3.start(); t4.start();
    }
}
