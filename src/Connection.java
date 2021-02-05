import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Connection extends Thread{//this class extends Thread class and override one of it's method

    Socket socket;//Connection class has this 3 variables as default.
    PrintWriter printWriter;//Going to initialize them HTML İÇİN
    BufferedReader bufferedReader;

    Connection(Socket socket1) throws Exception{//Constructor takes Socket type variable.We need to do that because of using same socket.
        socket = socket1;//I send socket to Connection constructor and assign constructor's socket to incoming socket object
        InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());//initializing reader and writer for reading requests and at the end responsing some text with writer
        bufferedReader = new BufferedReader(inputStreamReader);
        printWriter = new PrintWriter(socket.getOutputStream());
    }
    public void run(){//Override method from Thread Class,Creating different threads for different request.(Method for multithreading)
        try {
            String requestFrame = "";
            while (bufferedReader.ready() || requestFrame.length() == 0) {//Reading request and assign it to requestFrame string
                requestFrame = requestFrame + (char)bufferedReader.read();
            }
            String lines[] = requestFrame.split("\n");//Splitting requestFrame into the lines (i need to consider first line)
            String request = lines[0].split(" ")[1];//take first line and assign it to request
            request = request.substring(1,request.length());// Finally i have the part after localhost/ and i will use it to define returning html file's size
            int size;
                try {
                    size = Integer.parseInt(request);//parse string into integer and assign it to size
                } catch (Exception e) {//This catch for catching exception which gonna be occur when program trying to cast string into int when string is not castable.Such as "asd" toInt
                    if(request.equals("")){//Its more like a homepage.If the request it localhost:_portNumber_ than HttpServer do nothing
                        size = 20988;//Size values used for define which type HttpStatus Code will be returned
                    }
                    else if((lines[0].substring(0,4).equals("GET"))){
                        size = 20984;// if not get request
                    }
                    else {
                        size = 20897;//trying to parse string to int
                    }

                }
            Return response = new Return(socket, size);//Constructing Return Class Object with size value and Socket Object
                                                        //If size value is valid for project instructions than we will create response to incoming request with Return Class
        }catch (Exception e){
        }
    }
}
