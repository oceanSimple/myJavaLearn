package socket.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * @描述 对HTTP请求的报文分析，可以获取请求方式、请求路径、请求行、请求参数等信息
 * @创建时间 2023/04/10
 * @author ocean
 * @version 1.0
 */
public class RequestUtil {
    private InputStream input;//从socket传来的输入流
    private String requestMethod;//请求方式
    private String url;//请求路径
    private HashMap<String,String> infoMap;//存储请求行信息
    private HashMap<String,String> paramMap;//存储请求参数

    /**
     * 含参构造函数
     * @param in socket.getInputStream()
     */
    public RequestUtil(InputStream in) {
        this.input = in;
        this.infoMap = new HashMap<>();
        this.paramMap = new HashMap<>();

        //使用bufferedReader
        BufferedReader is = new BufferedReader(new InputStreamReader(input));

        //读取请求头，获取请求方式与url
        //未处理的url为split1[1]
        String line = "";
        try {
            line = is.readLine();
            String[] split1 = line.split(" ");
            this.requestMethod = split1[0];
            this.url = split1[1];
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //获取其他参数，保存在map中
        while (true) {
            try {
                if (!("".equals(line = is.readLine()))) {
                    //样例：Host: localhost:8080
                    int index = line.indexOf(":");
                    this.infoMap.put(line.substring(0,index),line.substring(index+2));
                } else {
                    break;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        //获取请求参数，分为post与get方式
        String paramsLine = "";
        if (this.requestMethod.equalsIgnoreCase("get")) {
            int index = this.url.indexOf("?");
            if (index != -1) {
                paramsLine = url.substring(index + 1);
                url = url.substring(0,index);
            }
        } else if (this.requestMethod.equalsIgnoreCase("post")) {
            try {
                paramsLine = is.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        //处理paramsLine
        String[] paramsSplit = paramsLine.split("&");
        for (int i = 0; i < paramsSplit.length; i++) {
            int index = paramsSplit[i].indexOf("=");
            paramMap.put(paramsSplit[i].substring(0,index),paramsSplit[i].substring(index+1));
        }
    }

    /**
     * 获取请求方式
     */
    public String getRequestMethod() {
        return requestMethod;
    }

    /**
     * 获取请求路径
     */
    public String getUrl() {
        return url;
    }

    /**
     * 获取储存请求行信息的map
     */
    public HashMap<String, String> getInfoMap() {
        return infoMap;
    }

    /**
     * 通过key获取请求行的value
     */
    public String getInfoByKey(String key) {
        return this.infoMap.get(key);
    }

    /**
     * 获取储存请求参数的map
     */
    public HashMap<String, String> getParamMap() {
        return paramMap;
    }

    /**
     * 通过name获取请求参数的value
     */
    public String getParamValueByName(String key) {
        return this.paramMap.get(key);
    }
}
