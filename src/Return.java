import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class Return {// This Class used for creating Response Message and print it to Web Browser
    Socket socket;

    public Return(Socket socket,int size) throws IOException {//Constructor takes 2 input.Socket  used for using same Socket Object since from beginning
        this.socket = socket;
        String response = "<HTML>\n" +
                "<HEAD>\n" +
                "<TITLE>I am 100 bytes long</TITLE>\n" +
                "</HEAD>\n" +
                "<BODY> a a a a a a a a </BODY>\n" +
                "</HTML>";//Here is a 100bytes long Response Message taken from Project's Proposal(Maybe some lack of Bytes occurs But i consider this message as reference)
        String modifiedResponse = "";//If size is 500 than it means we will return 5 times response string.If size value is invalid than it means we will send response with error message.This string used for this purpose
        if(size == 20988){//for request localhost:8080
            modifiedResponse = "";//Like main page
        }
        else if(size<100 ||size>20000 || size == 20897){ //If the requested file's size greater than 20.000 Bytes or less than 100 Bytes than Bad Request Message will returned
            modifiedResponse = "<h1>(400) BAD REQUEST </h1>";
        }
        else if(size == 20984){//If request includes any method other than GET
            modifiedResponse = "<h1>(501)NOT IMPLEMENTED</h1>";
        }
        else {//If request is valid than program modifying the modifiedResponse String
            size = size / 100;
            for (int i = 0; i < size; i++) {
                modifiedResponse += response;
            }
        }
        response = modifiedResponse;//assign it to response string now we have right form of message
        PrintWriter out = new PrintWriter(socket.getOutputStream());// Define writer into the Socket's outputstream
        out.println("HTTP/1.1 200 OK");//Giving Http ResponseLine
        out.println("Content-Type: text/html");//Content Type header
        out.println("Content-Length: " + response.length()); //Content length header
        out.println();
        out.println(response);//Our response message printed
        out.flush();
        out.close();
        socket.close();
    }


}
