package HeartBeatCheck;

/**
 *
 *   20级  杨浩  2020211855
 *
 *   用一个String值来代表心跳，首先客户端发送给服务端，然后服务端回复一个对应的心跳。
 *   如果在3000ms内服务端没有收到客户端的心跳或者客户端没有收到服务端的心跳，那么都会断开socket连接
 *   目前只是模拟了一下心跳……
 *   启动了多个线程分别执行  接收心跳并回复心跳  计时
 *   Client端使用Timer来重复执行发送心跳的任务，启动了多个线程用于  接受心跳  发送心跳  计时
 *
 *   （代码写的很杂乱，学长手下留情qwq）
 *
 */

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        System.out.println("---服务端---");
        ServerSocket serverSocket = new ServerSocket(8888);
        while(true){
            Socket socket = serverSocket.accept();
            System.out.println(socket.getRemoteSocketAddress() + "已经连接。");
            new SeverReaderThread(socket).start();
        }
    }
}

class SeverReaderThread extends Thread{
    private Socket socket;
    boolean close = true;

    public SeverReaderThread() {
    }

    public SeverReaderThread (Socket socket){
        this.socket = socket;
    }
    static class CountTime extends SeverReaderThread{
        @Override
        public void run() {
            long lastReceiveTime = System.currentTimeMillis();
            long Delay = 3000;
            lo:
            while (true){
                while (System.currentTimeMillis() - lastReceiveTime < Delay){
                    if (super.getStr){
                        lastReceiveTime = System.currentTimeMillis();
                        super.getStr = false;
                    }
                }
                while (System.currentTimeMillis() - lastReceiveTime > Delay){
                    if (!super.getStr){
                        super.shouldDown = true;
                        break lo;
                    }
                }
            }
        }
    }
    boolean shouldDown = false;
    String hearts = "nothing";
    boolean getStr = false;
    @Override
    public void run() {
        try{
            String backHeart = "Get";
            InputStream is = socket.getInputStream();
            Reader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            OutputStream os = socket.getOutputStream();
            PrintStream ps = new PrintStream(os);
            CountTime ct = new CountTime();
            ct.start();
            new StopSend().start(); // 用来计时主动断开连接的代码
            close = false;
            while(!shouldDown && (hearts = br.readLine())!=null){
                getStr = true;
                System.out.println(socket.getRemoteSocketAddress()+hearts);
                ps.println(backHeart);
                ps.flush();
            }
            socket.shutdownInput();
            socket.shutdownOutput();
            socket.close();
            System.out.println("已经与客户端" + socket.getRemoteSocketAddress() + "断开连接" );
        }catch (Exception e){
            close = true;
            System.out.println("已经与客户端" + socket.getRemoteSocketAddress() + "断开连接" );
        }
    }

    class StopSend extends Thread{
        @Override
        public void run() {
            long lastTime = System.currentTimeMillis();
            while (true){
                if (System.currentTimeMillis() - lastTime > 10000){
                    shouldDown = true;
                    break;
                }
            }
        }
    }
}