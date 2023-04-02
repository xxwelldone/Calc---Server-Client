package server;

import util.MsqReq;
import util.MsqResp;
import util.Status;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ThreadCalc extends Thread {
    private Socket client;
    ObjectInputStream in; /* recebe objeto*/
    ObjectOutputStream out; /* envia objeto*/
    MsqResp response;
    MsqReq request;

    public ThreadCalc(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        try {
            /*Deserialization */
            in = new ObjectInputStream(client.getInputStream());
            out = new ObjectOutputStream(client.getOutputStream());

            request = (MsqReq) in.readObject(); //Casting object to msqReq

            System.out.println("Accepted: " + request.getNumber1() + "," + request.getNumber2());

            char oper = request.getOper();
            double number1 = request.getNumber1();

            double number2 = request.getNumber2();
            double result;
            switch (oper) {
                case '+':
                    result = number1 + number2;
                    response = new MsqResp(Status.SUCCESS, result);
                    break;
                case '*':
                    result = number1 * number2;
                    response = new MsqResp(Status.SUCCESS, result);
                    break;
                case '-':
                    result = number1 - number2;
                    response = new MsqResp(Status.SUCCESS, result);
                    break;
                case '/':
                    if (request.getNumber2() == 0) {
                        response = new MsqResp(Status.NOT_POSSIBLE, 0);
                    } else {
                        result = number1 / number2;
                        response = new MsqResp(Status.SUCCESS, result);

                    }

                    break;

            }
            out.writeObject(response);
        } catch (Exception e) {
            System.out.println("Erro no run da Thread: " + e);
        }

    }
}
