/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business.DTO;


public class ChartDTO  {
    private String month;
    private Integer sumPrice;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Integer getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(Integer sumPrice) {
        this.sumPrice = sumPrice;
    }

    public ChartDTO() {
    }

    public ChartDTO(String month, Integer sumPrice) {
        this.month = month;
        this.sumPrice = sumPrice;
    }
    
    
    
}
