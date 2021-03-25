package com.cosre7.pms.handler;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class MemberListHandler implements Command {

  @Override
  public void service(DataInputStream in, DataOutputStream out) {
    System.out.println("[회원 목록]");

    out.writeUTF("member/selectall");
    out.writeInt(0);
    out.flush();

    String status = in.readUTF();
    int length = in.readInt();

    if (status.equals("error")) {
      System.out.println(in.readUTF());
      return;
    }

    for (int i = 0; i < length; i++) {
      String[] fields = in.readUTF().split(",");

      //번호, 이름, 전화, 가입일
      System.out.printf("[%d], %s (%s) %s\n",
          m.getNo(), 
          m.getName(), 
          m.getTel(), 
          m.getRegisteredDate());
    }
  }
}
