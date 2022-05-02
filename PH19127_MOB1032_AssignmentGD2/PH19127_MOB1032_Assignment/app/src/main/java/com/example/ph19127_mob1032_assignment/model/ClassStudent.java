package com.example.ph19127_mob1032_assignment.model;

public class ClassStudent {
    private String maLop;
    private String tenLop;

    public ClassStudent(String classId, String className) {
        this.maLop = classId;
        this.tenLop = className;
    }

    public ClassStudent() {
    }

    public String getMaLop() {
        return maLop;
    }

    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }

    public String getTenLop() {
        return tenLop;
    }

    public void setTenLop(String tenLop) {
        this.tenLop = tenLop;
    }
}
