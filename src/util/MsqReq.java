package util;

import java.io.Serializable;

public class MsqReq implements Serializable {
    private char oper;
    private double number1, number2;

    public MsqReq(char oper, double number1, double number2) {
        this.oper = oper;
        this.number1 = number1;
        this.number2 = number2;
    }

    public char getOper() {
        return oper;
    }

    public double getNumber1() {
        return number1;
    }

    public double getNumber2() {
        return number2;
    }
}
