package com.example.demo.dao.model;

import java.time.LocalDateTime;

public class Order {
    private String medicineName;
    private String userName;
    private int id;
    private int userId;
    private int medicineId;
    private LocalDateTime orderDate;
    private int quantity;
    private int dosage;


    public Order(int id, int userId, int medicineId, LocalDateTime orderDate, int quantity, int dosage, String userName, String medicineName) {
        this.id = id;
        this.userId = userId;
        this.medicineId = medicineId;
        this.orderDate = orderDate;
        this.quantity = quantity;
        this.dosage = dosage;
        this.medicineName = medicineName;
        this.userName = userName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(int medicineId) {
        this.medicineId = medicineId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getDosage() {
        return dosage;
    }

    public void setDosage(int dosage) {
        this.dosage = dosage;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }
}
