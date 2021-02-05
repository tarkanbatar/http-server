import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class HttpServer {//This class is our main class for Http Server.
    ServerSocket serverSocket;//Global variable with ServerSocket type, we are going to use it in methods
    public static void main(String[] args) throws Exception {// This main method asks for Port number than creates instance of class and calls method with this instance(with port number)
        System.out.println("Enter Port: \n");
        Scanner sc = new Scanner(System.in);
        int portNo = sc.nextInt();
        HttpServer web_server = new HttpServer();
        web_server.runServer(portNo);

    }

    public void runServer(int port_Number) throws Exception{ //initializing serverSocket with given port number
        serverSocket = new ServerSocket(port_Number);
        listen();//This method listens request from client
    }

    public void listen(){//Method is listening till the end of program(quit) with while loop
        while(true){
            Socket socket = null;

            try{
                socket = serverSocket.accept();//socket starts accepting request
            }catch (IOException e){
                e.printStackTrace();
            }
            Connection connection = null;//Creating Connection object
            try{
                connection = new Connection(socket);
            }catch (Exception e){
                e.printStackTrace();
            }
            connection.start();//starting method start().Thread class has this method,we override it.
        }
    }
}
