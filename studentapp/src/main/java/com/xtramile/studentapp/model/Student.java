package com.xtramile.studentapp.model;

import java.time.LocalDate;
import java.time.Period;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "students") 
public class Student {
    // Variable
    @Id
    private Long nim;
  
    @Column(nullable = false) 
    private String namaDepan; 
  
    @Column(nullable = true) 
    private String namaBelakang; 
  
    @Column(nullable = false) 
    private LocalDate tanggalLahir; 

    @Column(nullable = false)
    private String jenisKelamin;

    private int usia;
    
    // Constructor
    public Student() {}

    public Student (Long nim, String namaDepan, String namaBelakang, LocalDate tanggalLahir, String jenisKelamin) {
        this.setNim(nim);
        this.setNamaDepan(namaDepan);
        this.setNamaBelakang(namaBelakang);
        this.setTanggalLahir(tanggalLahir);
        this.setJenisKelamin(jenisKelamin);
    }

    // Getter, Setter, and Method
    public void setNim(Long nim) {
        this.nim = nim;
    }

    public Long getNim() {
        return this.nim;
    }

    public void setNamaDepan(String namaDepan) {
        this.namaDepan = namaDepan;
    }

    public String getNamaDepan() {
        return this.namaDepan;
    }

    public void setNamaBelakang(String namaBelakang) {
        if (namaBelakang != null) {
            this.namaBelakang = namaBelakang;
        } else {
            this.namaBelakang = "";
        }
    }

    public String getNamaBelakang() {
        if (namaBelakang != null) {
            return this.namaBelakang;
        } else {
            return "";
        }
    }

    public String getNamaLengkap() {
        if (namaBelakang != null) {
            return this.namaDepan + " " + this.namaBelakang;
        } else {
            return this.namaDepan;
        }
    }

    public void setTanggalLahir(LocalDate tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public LocalDate getTanggalLahir() {
        return this.tanggalLahir;
    }

    public int getUsia() {
        LocalDate currentDate = LocalDate.now();
        return Period.between(this.tanggalLahir, currentDate).getYears();
    }

    public void setJenisKelamin(String jenisKelamin) {
        if (jenisKelamin.equalsIgnoreCase("laki-laki") || jenisKelamin.equalsIgnoreCase("perempuan")) {
            this.jenisKelamin = jenisKelamin.substring(0,1).toUpperCase() + jenisKelamin.substring(1).toLowerCase();
        }
    }

    public String getJenisKelamin() {
        return this.jenisKelamin;
    }

}
