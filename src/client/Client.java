package client;

import util.MsqReq;
import util.MsqResp;
import util.Status;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


public class Client {
    public static void main(String[] args) {
        Socket socket;
        Scanner sc = new Scanner(System.in);
        String ip = "127.0.0.1";
        int port = 54321;
        ObjectOutputStream out;
        ObjectInputStream in;

        try {
            /*Solicitação de conexão com o ip e porta informados*/
            socket = new Socket(ip, port);
            System.out.println("Connected");
        } catch (Exception e) {
            System.out.println("Erro: " + e);
            return;
        }
        try {

            /*serializa e envia para outro lado*/
            out = new ObjectOutputStream(socket.getOutputStream()); /* envia para fora*/
            in = new ObjectInputStream(socket.getInputStream()); /* recebe para dentro*/

            System.out.println("Type in operation: (+,/,*,-)");
            char oper = sc.nextLine().charAt(0);
            System.out.println("Type first number: ");
            double number1 = sc.nextDouble();
            System.out.println("Type first number: ");
            double number2 = sc.nextDouble();

            /* pega mensagem*/
            MsqReq request = new MsqReq(oper, number1, number2);
            out.writeObject(request);/* recebe os dados e encapsula para enviar pro outro lado*/

            MsqResp response = (MsqResp) in.readObject();

            if(response.getStatus()== Status.SUCCESS){
                System.out.println(response.getNumberReturn());
            } else if(response.getStatus() == Status.NOT_POSSIBLE){
                System.out.println("It's not possible to divide by zero");
            } else {
                System.out.println("Invalid operator");
            }



        } catch (Exception e) {
            System.out.println("Erro: " + e);

        }
        try {
            System.out.println("Conexão fechada");
            // socket.close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
