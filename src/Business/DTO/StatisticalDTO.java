/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business.DTO;

/**
 *
 * @author Minh
 */

public class StatisticalDTO {
    String productName;
    Integer quantity;
    Integer  sumPrice;

    public StatisticalDTO() {
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(Integer sumPrice) {
        this.sumPrice = sumPrice;
    }

    public StatisticalDTO(String productName, Integer quantity, Integer sumPrice) {
        this.productName = productName;
        this.quantity = quantity;
        this.sumPrice = sumPrice;
    }
    
    
    
}