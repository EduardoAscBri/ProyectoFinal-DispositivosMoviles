package com.example.proyectofinal;

import java.time.LocalDateTime;


public class Transactions{
    
    
    private double Amount;
    private String Concept;
    private LocalDateTime Date;
    private double LatLocation;
    private double LonLocation;
    private int IsAutoCharge;

    public double getAmount() {
        return Amount;
    }

    public String getConcept() {
        return Concept;
    }

    public LocalDateTime getDate() {
        return Date;
    }

    public double getLatLocation() {
        return LatLocation;
    }

    public double getLonLocation() {
        return LonLocation;
    }

    public int getIsAutoCharge() {
        return IsAutoCharge;
    }

    public void setAmount(double Amount) {
        this.Amount = Amount;
    }

    public void setConcept(String Concept) {
        this.Concept = Concept;
    }

    public void setDate(LocalDateTime Date) {
        this.Date = Date;
    }

    public void setLatLocation(double LatLocation) {
        this.LatLocation = LatLocation;
    }

    public void setLonLocation(double LonLocation) {
        this.LonLocation = LonLocation;
    }

    public void setIsAutoCharge(int IsAutoCharge) {
        this.IsAutoCharge = IsAutoCharge;
    }
    
    
    
    
    
    
    
    


}