package server;

import util.MsqReq;

import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
        ServerSocket serverSocket;
        Socket socketClient = null;
        int port = 54321;


        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server available: " + port);
        } catch (Exception e) {
            System.out.println("Erro: " + e);
            return;
        }
        //while(){
        try {
            System.out.println("Waiting connection");
            socketClient = serverSocket.accept();

            System.out.println("Accept: " + socketClient.getInetAddress().getHostAddress());
            /*Jogando info para thread e startando ela */
            ThreadCalc calc = new ThreadCalc(socketClient);
            calc.start();


        } catch (Exception e) {
            System.out.println("Erro while accepting connection: " + e);
        }
        //}

        try {
            System.out.println("Servidor fechado");
            //socketClient.close();
            //  serverSocket.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
