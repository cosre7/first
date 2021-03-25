package com.cosre7.pms.handler;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.sql.Date;
import com.cosre7.pms.domain.Member;
import com.cosre7.pms.util.Prompt;

public class MemberAddHandler implements Command {

  @Override
  public void service(DataInputStream in, DataOutputStream out) throws Exception {
    System.out.println("[회원 등록]");

    Member m = new Member();
    m.setName(Prompt.inputString("이름 > "));
    m.setPassword(Prompt.inputString("암호 > "));
    m.setPhoto(Prompt.inputString("사진 > "));
    m.setTel(Prompt.inputString("전화 > "));
    m.setRegisteredDate(new Date(System.currentTimeMillis()));

    out.writeUTF("member/insert");
    out.writeInt(1);
    out.writeUTF(String.format("%s,%s,%s,%s,%s", 
        m.getName(), m.getPassword(), m.getPhoto(), m.getTel(), m.getRegisteredDate()));
    out.flush();

    String status = in.readUTF();
    in.readInt();

    if (status.equals("error")) {
      System.out.println(in.readUTF());
      return;
    }

    System.out.println("회원을 등록하였습니다.");
  }
}
