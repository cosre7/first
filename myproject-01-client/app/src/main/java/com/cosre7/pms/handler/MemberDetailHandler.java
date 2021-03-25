package com.cosre7.pms.handler;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import com.cosre7.pms.util.Prompt;

public class MemberDetailHandler implements Command {

  @Override
  public void service(DataInputStream in, DataOutputStream out) throws Exception {
    System.out.println("[회원 상세보기]");

    int no = Prompt.inputInt("번호 > ");

    out.writeUTF("member/select");
    out.writeInt(1);
    out.writeUTF(Integer.toString(no));
    out.flush();

    String status = in.readUTF();
    in.readInt();

    if (status.equals("error")) {
      System.out.println(in.readUTF());
      return;
    }

    String[] fields = in.readUTF().split(",");

    // 이름, 사진, 전화, 가입일
    System.out.printf("이름: %s\n", fields[1]);
    System.out.printf("사진: %s\n", fields[2]);
    System.out.printf("전화: %s\n", fields[3]);
    System.out.printf("가입일: %s\n", fields[4]);
  }
}
