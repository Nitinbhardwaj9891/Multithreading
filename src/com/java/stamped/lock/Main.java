package com.java.stamped.lock;

import java.util.Scanner;
import java.util.concurrent.locks.StampedLock;

class BankAccount{
    private final StampedLock stampedLock;
    private double balance;

    public BankAccount(StampedLock stampedLock, double balance){
        this.stampedLock = stampedLock;
        this.balance = balance;
    }
    public double getBalance(){
        long stamp = stampedLock.tryOptimisticRead();
        double currentBalance = this.balance;
        if(!stampedLock.validate(stamp)){
            try {
                stamp = stampedLock.readLock();
                currentBalance = this.balance;
            }
            finally {
                stampedLock.unlockRead(stamp);
            }

        }
        return currentBalance;
    }

    public double deposit(double amount){
        long stamp = stampedLock.writeLock();
        try{

            if(amount>=1){
                balance += amount;
                System.out.println(amount+" is deposited");
            }
            else{
                System.out.println("Invalid amount");
            }
        }
        finally {
            stampedLock.unlockWrite(stamp);
        }
        return balance;
    }

    public double withdraw(double amount){
        long stamp = stampedLock.writeLock();
        try{

            if(balance>=amount){
                balance-=amount;
                System.out.println(amount+" is withdrawn");
            }
            else{
                System.out.println("Not enough funds");
            }
        }
        finally {
            stampedLock.unlockWrite(stamp);
        }
        return balance;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter deposit amount: ");
        double deposit = sc.nextDouble();

        System.out.print("Enter withdrawal amount: ");
        double withdraw = sc.nextDouble();

        System.out.print("Enter initial balance: ");
        double currentBalance = sc.nextDouble();

        StampedLock lock = new StampedLock();
        BankAccount bankAccount = new BankAccount(lock, currentBalance);

        Thread t2 = new Thread(() -> bankAccount.deposit(deposit), "t2");
        Thread t3 = new Thread(() -> bankAccount.withdraw(withdraw), "t3");


        t2.start();
        t3.start();

        try {
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        Thread t1 = new Thread(() -> {
            double fund = bankAccount.getBalance();
            System.out.println(fund + " is total fund");
        }, "t1");

        t1.start();

        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Final Balance: " + bankAccount.getBalance());
    }
}