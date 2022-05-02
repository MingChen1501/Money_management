package com.example.ph19127_mob1032_assignment.model;

public class Student {
    private String tenSV;
    private String Lop;
    private String ngaySinh;
    private String id;

    public Student() {
    }

    public Student(String tenSV, String lop, String ngaySinh) {
        this.tenSV = tenSV;
        Lop = lop;
        this.ngaySinh = ngaySinh;
    }

    public Student(String tenSV, String lop, String ngaySinh, String id) {
        this.tenSV = tenSV;
        Lop = lop;
        this.ngaySinh = ngaySinh;
        this.id = id;
    }

    public String getTenSV() {
        return tenSV;
    }

    public void setTenSV(String tenSV) {
        this.tenSV = tenSV;
    }

    public String getLop() {
        return Lop;
    }

    public void setLop(String lop) {
        Lop = lop;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (tenSV != null ? !tenSV.equals(student.tenSV) : student.tenSV != null) return false;
        if (Lop != null ? !Lop.equals(student.Lop) : student.Lop != null) return false;
        if (ngaySinh != null ? !ngaySinh.equals(student.ngaySinh) : student.ngaySinh != null)
            return false;
        return id != null ? id.equals(student.id) : student.id == null;
    }

    @Override
    public String toString() {
        return "Student{" +
                "tenSV='" + tenSV + '\'' +
                ", Lop='" + Lop + '\'' +
                ", ngaySinh='" + ngaySinh + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        int result = tenSV != null ? tenSV.hashCode() : 0;
        result = 31 * result + (Lop != null ? Lop.hashCode() : 0);
        result = 31 * result + (ngaySinh != null ? ngaySinh.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }
}
