package HeartBeatCheck;

import java.io.*;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

public class Client {

    public static void main(String[] args) {
        Socket socket;
        try {
            socket = new Socket("127.0.0.1",8888);
            doSentHeart ds =  new doSentHeart(socket);
            ds.Starts();
        } catch (IOException e) {
            System.err.println("无法连接到指定的服务端！");
            System.exit(-1);
        }

    }

}

class SentHeart extends TimerTask{
    public SentHeart() {
    }

    PrintStream ps;

    public SentHeart(Socket socket) throws IOException {
        OutputStream os = socket.getOutputStream();
        ps = new PrintStream(os);
    }

    @Override
    public void run() {
        String heart = "Still Alive";
        ps.println(heart);
        ps.flush();
    }
}

class doSentHeart{
    Socket socket;
    boolean shouldDown = false;
    boolean getStr = false;
    String getBack;
    boolean allShutDown = false;


    class getBackInf extends Thread{
        @Override
        public void run() {
            InputStream is;
            try {
                is = socket.getInputStream();
                Reader ris = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(ris);
                while (true){
                    if (allShutDown) break;
                    try {
                        if (!shouldDown && (getBack = br.readLine()) != null){
                            getStr = true;
                            System.out.println("收到服务端回复了！" + getBack);
                        }else {
                            break;
                        }
                    } catch (IOException e) {
                        System.err.println("失去服务器的链接！");
                        allShutDown = true;
                        shouldDown = true;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    class shutDownTask extends Thread{
        @Override
        public void run() {
            long lastReceiveTime = System.currentTimeMillis();
            long Delay = 3100;
            while (true){
                if (allShutDown) {
                    shouldDown = true;
                    break;
                }
                if (System.currentTimeMillis() - lastReceiveTime > Delay){
                    if (!getStr){
                        shouldDown = true;
                    }
                    lastReceiveTime = System.currentTimeMillis();
                }
                while (System.currentTimeMillis() - lastReceiveTime < Delay){
                    if (getStr){
                        getStr = false;
                        lastReceiveTime = System.currentTimeMillis();
                    }
                }

            }
        }
    }

    public doSentHeart(Socket socket) {
        this.socket = socket;
    }

    public doSentHeart() {
    }

    public void Starts(){
        Timer timeDo = new Timer();
        SentHeart sentHeart;
        try {
            long time = System.currentTimeMillis();
            sentHeart = new SentHeart(socket);
            timeDo.schedule(sentHeart,50,3000);
            getBackInf getBack = new getBackInf();
            shutDownTask sDT =  new shutDownTask();
            getBack.start();
            sDT.start();
            while (!shouldDown) {
                while (System.currentTimeMillis() - time > 1000) {
                    time = System.currentTimeMillis();
                    System.out.println("仍在运转。");
                }
            }
            allShutDown = true;
            Thread.sleep(100);
            timeDo.cancel();
            socket.shutdownInput();
            socket.shutdownOutput();
            socket.close();
            System.out.println("已经断开与服务端的链接。");
            System.exit(0);
        } catch (IOException | InterruptedException e) {
            System.err.println("无法连接到指定的服务端！");
            System.exit(-1);
        }
    }
}


