package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Port8080 {
    public static void main(String[] args) {
        ServerSocket server;
        Socket socket;
        try {
            // 创建一个监听8080的端口
            server = new ServerSocket(8080);
            // 等待请求
             socket = server.accept();
            //读取数据
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String s = "";
            while (!("".equals(s= reader.readLine()))) {
                System.out.println(s);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            socket.close();
            server.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
