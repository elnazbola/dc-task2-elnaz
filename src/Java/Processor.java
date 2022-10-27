package Java;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;


/**
 * Processor of HTTP request.
 */
public class Processor
//        extends Thread
{
    private final Socket socket;
    private final HttpRequest request;

    public Processor(Socket socket, HttpRequest request) {
        this.socket = socket;
        this.request = request;
    }


    public void process() throws IOException {
        System.out.println("Got request:");
        System.out.println(request.toString());
        System.out.println(request.getRequestLine());
        System.out.flush();

        PrintWriter output = new PrintWriter(socket.getOutputStream());

        String[] requestSplit = request.getRequestLine().split(" ");

        if (requestSplit[1].equals("/create/itemid")){
            output.println("HTTP/1.1 200 OK");
            output.println("Content-Type: text/html; charset=utf-8");
            output.println();
            output.println("<html>");
            output.println("<head><title>Hello</title></head>");
            output.println("<body><p>Create</p></body>");
            output.println("</html>");
            output.flush();
            socket.close();
        } else if(requestSplit[1].equals("/delete/itemid")){
            output.println("HTTP/1.1 200 OK");
            output.println("Content-Type: text/html; charset=utf-8");
            output.println();
            output.println("<html>");
            output.println("<head><title>Hello</title></head>");
            output.println("<body><p>Delete</p></body>");
            output.println("</html>");
            output.flush();
            socket.close();
        } else if(requestSplit[1].equals("/exec/params")){
            output.println("HTTP/1.1 200 OK");
            output.println("Content-Type: text/html; charset=utf-8");
            output.println();
            output.println("<html>");
            output.println("<head><title>Hello</title></head>");
            output.println("<body><p>Execute</p></body>");
            output.println("</html>");
            output.flush();
            socket.close();
        } else if(isNum(requestSplit[1].substring(1))){
            output.println("HTTP/1.1 200 OK");
            output.println("Content-Type: text/html; charset=utf-8");
            output.println();
            output.println("<html>");
            output.println("<head><title>Hello</title></head>");
            output.print("<body><p>");
            int a, b, flag;

           
            output.print(
                    "Prime numbers from 1 and " + Integer.parseInt(requestSplit[1].substring(1))
                            + " : ");

           
            for (a = 1; a <= Integer.parseInt(requestSplit[1].substring(1)); a++) {

                
                if (a == 1 || a == 0)
                    continue;

                
                flag = 1;

                for (b = 2; b <= a / 2; ++b) {
                    if (a % b == 0) {
                        flag = 0;
                        break;
                    }
                }

               
                if (flag == 1)
                    output.print(a + " ");
            }
          
            output.println("</p></body>");
            output.println("</html>");
            output.flush();
            socket.close();
        } else {
          
            output.println("HTTP/1.1 200 OK");
            output.println("Content-Type: text/html; charset=utf-8");
            output.println();
            output.println("<html>");
            output.println("<head><title>Hello</title></head>");
            output.println("<body><p>Hello, world!</p></body>");
            output.println("</html>");
            output.flush();
            socket.close();
        }
        
    }
    public static boolean isNum(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int z = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

}