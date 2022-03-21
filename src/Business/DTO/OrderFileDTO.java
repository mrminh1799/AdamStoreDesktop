/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.DTO;

import java.util.Date;

/**
 *
 * @author tiennv
 */
public class OrderFileDTO {
    private int orderID;
    private String CustomerName;
    private String ProductName;
    private Date dateCreate;
    private double price;
    private int quantity;
    private String staffName;
    private double sumPrice;

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String CustomerName) {
        this.CustomerName = CustomerName;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public double getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(double sumPrice) {
        this.sumPrice = sumPrice;
    }

    public OrderFileDTO() {
    }

    public OrderFileDTO(int orderID, String CustomerName, String ProductName, Date dateCreate, double price, int quantity, String staffName, double sumPrice) {
        this.orderID = orderID;
        this.CustomerName = CustomerName;
        this.ProductName = ProductName;
        this.dateCreate = dateCreate;
        this.price = price;
        this.quantity = quantity;
        this.staffName = staffName;
        this.sumPrice = sumPrice;
    }
    
    
    
}
