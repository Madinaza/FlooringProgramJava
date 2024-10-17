package model;

import java.math.BigDecimal;

public class Order {
    
    
    private int orderNumber;
    private String customerName;
    private String state;
    private BigDecimal taxRate;
    private String productType;
    private BigDecimal area;
    private BigDecimal costPerSquareFoot;
    private BigDecimal laborCostPerSquareFoot;
    private BigDecimal materialCost;
    private BigDecimal laborCost;
    private BigDecimal tax;
    private BigDecimal total;
   
    
    
    public Order(int orderNumber, String customerName, String state, BigDecimal taxRate, 
                 String productType, BigDecimal area, BigDecimal costPerSquareFoot, 
                 BigDecimal laborCostPerSquareFoot) {
        this.orderNumber = orderNumber;
        this.customerName = customerName;
        this.state = state;
        this.taxRate = taxRate;
        this.productType = productType;
        this.area = area;
        this.costPerSquareFoot = costPerSquareFoot;
        this.laborCostPerSquareFoot = laborCostPerSquareFoot;
        calculateCosts();
    }
    
    public void calculateCosts(){
     materialCost = area.multiply(costPerSquareFoot);
        laborCost = area.multiply(laborCostPerSquareFoot);
        tax = (materialCost.add(laborCost)).multiply(taxRate.divide(new BigDecimal("100")));
        total = materialCost.add(laborCost).add(tax);
        
    }
  
     
     
public int getOrderNumber() {
    return orderNumber;
}
     
  

public String getCustomerName() {
    return customerName;
}

public String getState() {
    return state;
}

public BigDecimal getTaxRate() {
    return taxRate;
}

public String getProductType() {
    return productType;
}

public BigDecimal getArea() {
    return area;
}

public BigDecimal getCostPerSquareFoot() {
    return costPerSquareFoot;
}

public BigDecimal getLaborCostPerSquareFoot() {
    return laborCostPerSquareFoot;
}

public BigDecimal getMaterialCost() {
    return materialCost;
}

public BigDecimal getLaborCost() {
    return laborCost;
}

public BigDecimal getTax() {
    return tax;
}

public BigDecimal getTotal() {
    return total;
}



public void setOrderNumber(int orderNumber) {
    this.orderNumber = orderNumber;
}

public void setCustomerName(String customerName) {
    this.customerName = customerName;
}

public void setState(String state) {
    this.state = state;
}

public void setTaxRate(BigDecimal taxRate) {
    this.taxRate = taxRate;
}

public void setProductType(String productType) {
    this.productType = productType;
}

public void setArea(BigDecimal area) {
    this.area = area;
    calculateCosts(); 
}

public void setCostPerSquareFoot(BigDecimal costPerSquareFoot) {
    this.costPerSquareFoot = costPerSquareFoot;
    calculateCosts(); 
}

public void setLaborCostPerSquareFoot(BigDecimal laborCostPerSquareFoot) {
    this.laborCostPerSquareFoot = laborCostPerSquareFoot;
    calculateCosts(); 
}


   

}
