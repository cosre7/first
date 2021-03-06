package com.cosre7.pms;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import com.cosre7.pms.util.Prompt;

public class ClientApp {

  String serverAddress;
  int port;

  public static void main(String[] args) {
    ClientApp app = new ClientApp("localhost", 8888);
    app.execute();
  }

  public ClientApp(String serverAddress, int port) {
    this.serverAddress = serverAddress;
    this.port = port;
  }

  private void execute() {

    try (Socket socket = new Socket(this.serverAddress, this.port);
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        DataInputStream in = new DataInputStream(socket.getInputStream())) {

      while (true) {
        String message = Prompt.inputString("명령> ");

        // 1) 명령어
        out.writeUTF(message);

        // 2) 데이터 개수
        out.writeInt(3);

        // 3) 데이터
        out.writeUTF("aaaa");
        out.writeUTF("bbbb");
        out.writeUTF("cccc");

        out.flush();

        // 1) 작업 결과 
        String response = in.readUTF();

        // 2) 데이터 개수
        int length = in.readInt();

        // 3) 데이터 
        ArrayList<String> data = null;
        if (length > 0) {
          data = new ArrayList<>();
          for (int i = 0; i < length; i++) {
            data.add(in.readUTF());
          }
        }

        System.out.println("------------------------------------");
        System.out.printf("작업 결과: %s\n", response);
        System.out.printf("데이터 개수: %d\n", length);
        if (data != null) {
          System.out.println("데이터:");
          for (String str : data) {
            System.out.println(str);
          }
        }

        if (message.equals("quit")) {
          break;
        }
      }

      Prompt.close();

    } catch (Exception e) {
      System.out.println("서버와 통신 하는 중에 오류 발생!");
    }
  }
}
