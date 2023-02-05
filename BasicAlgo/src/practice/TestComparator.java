package practice;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class TestComparator {
    public static void main(String[] args) {


    }
}

class StudentComparator implements Comparator<Student> {
    @Override
    public int compare(Student s1, Student s2) {
        return s1.age == s2.age ? s1.id - s2.id : s1.age - s2.age;
    }
}

class Student {
    public int id;
    public int age;
    public String name;

    public Student(int id, int age, String name) {
        this.id = id;
        this.age = age;
        this.name = name;
    }
}
