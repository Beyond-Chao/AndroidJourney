package com.example.javaknowledge;

public class MyClass {

    public static void main(String[] args) {

        Person p = new Person();
        int temp = 15;
        String[] fullname = new String[] {"Homer", "Simpson"};
        String bob = new String("Bob"); // "Bob";
        p.setFirstName(bob);
        System.out.println(bob);

        System.out.println(p.getFirstName());
        bob = "Alice";
        System.out.println(p.getFirstName());

        p.setName(fullname);
        System.out.println(p.getName());
        fullname[0] = "Beyond";

        System.out.println(p.getName());

        p.setAge(temp);
        System.out.println(p.getAge());
        temp = 200;
        System.out.println(p.getAge());

        String str = new String("ab");
        change(str);
        System.out.println(str);

        Personable personable = new Personable() {
            @Override
            public void runable() {

            }

            @Override
            public void talkable() {

            }
        };
    }

    public static void change(String x) {
        x = "cd";
    }
}


class Person {
    private int age;
    private String[] name;
    private String firstName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String[] name) {
        this.name = name;
    }

    public String getName() {
        return this.name[0] + " " + this.name[1];
    }
}

class Student implements Personable {

    @Override
    public void runable() {
        //
    }

    @Override
    public void talkable() {
        //
    }
}

class Teacher implements Hello {
    @Override
    public void greet(String who) {
        //
    }

    @Override
    public void runable() {

    }

    @Override
    public void talkable() {

    }
}

interface Personable {
    void runable();
    void talkable();
}

interface Hello extends Personable {
    void greet(String who);
}