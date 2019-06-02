package com.xinlan.client;

import com.xinlan.imserver.Policy;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class TimeClient {
    private static final String IP = "127.0.0.1";
    private static final int port = 8899;
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(IP , port);
        boolean isRun = true;
        while (isRun){
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(Policy.TIME_SERVER_ASK.getBytes());

            InputStream inputStream = socket.getInputStream();
            byte[] buf = new byte[1024];
            int len = inputStream.read(buf);
            String response = new String(buf , 0 , len);
            System.out.println(socket.getRemoteSocketAddress() +" received " + response);
        }// end while
    }
}
