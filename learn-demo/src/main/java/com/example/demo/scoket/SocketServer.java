package com.example.demo.scoket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author SongJiuHua.
 * @date Created on 2018/5/9.
 * @description
 */
public class SocketServer implements Runnable{

    private ServerSocket serverSocket;

    public SocketServer(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
    }

    public static void main(String[] args) {
        try {
            Thread thread = new Thread(new SocketServer(8080));
            thread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true){
            try{
                System.out.println("等待远程连接，端口号为：" + serverSocket.getLocalPort() + "...");
                Socket server = serverSocket.accept();
                System.out.println("远程主机地址：" + server.getRemoteSocketAddress());

                DataInputStream in = new DataInputStream(server.getInputStream());
                System.out.println("服务端获取客户端传来的参数：" + in.readUTF());

                /** 服务器相应给客户端 */
                DataOutputStream out = new DataOutputStream(server.getOutputStream());
                out.writeUTF("知道了，马上回家吃饭");

                server.close();
            }catch (IOException e){
                e.printStackTrace();
                break;
            }
        }
    }
}
