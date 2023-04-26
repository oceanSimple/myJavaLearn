package socket;

import org.junit.Test;
import socket.util.RequestUtil;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class RequestUtilTest {
    @Test
    public void test01() throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        Socket socket = serverSocket.accept();
        RequestUtil requestUtil = new RequestUtil(socket.getInputStream());

        String requestMethod = requestUtil.getRequestMethod();
        String url = requestUtil.getUrl();
        HashMap<String, String> infoMap = requestUtil.getInfoMap();
        HashMap<String, String> paramMap = requestUtil.getParamMap();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("请求方式：" + requestMethod);
        stringBuilder.append("\n");
        stringBuilder.append("url：" + url);
        stringBuilder.append("\n");
        stringBuilder.append("-----------------------------------------\n");
        stringBuilder.append("请求行如下：\n");
        for (String key : infoMap.keySet()) {
            stringBuilder.append(key + ":" + infoMap.get(key));
            stringBuilder.append("\n");
        }
        stringBuilder.append("-----------------------------------------\n");
        stringBuilder.append("请求参数如下：\n");
        for (String key : paramMap.keySet()) {
            stringBuilder.append(key + ":" + paramMap.get(key));
            stringBuilder.append("\n");
        }
        stringBuilder.append("-----------------------------------------\n");
        System.out.println(stringBuilder);

        socket.close();
        serverSocket.close();
    }
}
