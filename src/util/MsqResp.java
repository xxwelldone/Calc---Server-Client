package util;

import java.io.Serializable;

public class MsqResp implements Serializable {
    private Status status;
    private double numberReturn;

    public MsqResp(Status status, double numberReturn) {
        this.status = status;
        this.numberReturn = numberReturn;
    }

    public Status getStatus() {
        return status;
    }

    public double getNumberReturn() {
        return numberReturn;
    }
}
