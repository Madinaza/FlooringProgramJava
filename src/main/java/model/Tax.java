package model;

import java.math.BigDecimal;


public class Tax {
    private String StateAbbreviation;
    private String StateName;
    private BigDecimal TaxRate;
    
    public Tax( String StateAbbreviation,String StateName,BigDecimal TaxRate){
        this.StateAbbreviation=StateAbbreviation;
        this.StateName=StateName;
        this.TaxRate=TaxRate;
        
    }
    
    public String getStateAbbrevlation(){
        return StateAbbreviation;
    }
    public String getStateName(){
        return StateName;
    }
    
    public BigDecimal getTaxRate(){
        return TaxRate;
    }
    
    public void setStateAbbreylation(String StateAbbreviation){
        this.StateAbbreviation=StateAbbreviation;
    }
    
    public void setStateName(String StateName){
        this.StateName=StateName;
    }
    
    public void setTaxRate(BigDecimal TexRate){
        this.TaxRate=TaxRate;
    }
        
    
    
    
}
