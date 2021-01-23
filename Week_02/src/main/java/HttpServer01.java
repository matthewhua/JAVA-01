import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Matthew
 * @date 2021-01-24 6:57
 */
public class HttpServer01 {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8801);
        while (true){
            try {
                Socket accept = serverSocket.accept();
                service(accept);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private static void service(Socket socket)
    {
        try {
            Thread.sleep(20);
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
            printWriter.println("HTTP/1.1 200 OK");
            printWriter.println("Content-type: text/html; charset=utf-8");
            printWriter.println();
            printWriter.println("Hello matthew");
            printWriter.close();
            socket.close();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }
}
