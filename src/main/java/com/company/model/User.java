package com.company.model;

public class User {
    private String fullName;
    private int age;
    private int salary;
    private String email;
    private String job;

    public User() {}

    public User(String fullName, int age, int salary, String email, String job) {
        this.fullName = fullName;
        this.age = age;
        this.salary = salary;
        this.email = email;
        this.job = job;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    @Override
    public String toString() {
        return "User{" +
                "fullName='" + fullName + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                ", email='" + email + '\'' +
                ", job='" + job + '\'' +
                '}';
    }
}
