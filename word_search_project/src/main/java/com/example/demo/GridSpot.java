package com.example.demo;
public class GridSpot {
    private String btnText = "-";

    public void setBtnText(char btnText) {
         this.btnText = String.valueOf(btnText);
    }

    public GridSpot(){
        btnText = "-";
    }

    public String getBtnText() {
        return btnText;
    }
}
