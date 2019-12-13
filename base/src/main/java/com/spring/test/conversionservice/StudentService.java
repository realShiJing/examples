package com.spring.test.conversionservice;

/**
 * @Decription TODO
 * @Author yangsj
 * @Date 2019/12/12 11:03
 **/
public class StudentService {
    private Student student;

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "StudentService{" +
                "student=" + student +
                '}';
    }
}
