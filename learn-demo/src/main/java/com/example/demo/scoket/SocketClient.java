package com.example.demo.scoket;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author SongJiuHua.
 * @date Created on 2018/5/9.
 * @description
 */
public class SocketClient {

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++){
            try {
                Socket client = new Socket("localhost", 8080);
                System.out.println("远程主机地址：" + client.getRemoteSocketAddress());

                OutputStream out = client.getOutputStream();
                /** 客户端传输给服务器参数 */
                DataOutputStream dataOut = new DataOutputStream(out);
                dataOut.writeUTF("Tom 你妈喊你回家吃饭了。。。");

                InputStream in = client.getInputStream();
                DataInputStream dataIn = new DataInputStream(in);
                System.out.println("服务器响应： " + dataIn.readUTF());
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
