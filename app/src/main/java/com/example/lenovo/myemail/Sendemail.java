package com.example.lenovo.myemail;

/**
 * Created by lenovo on 2016/11/12.
 */

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Sendemail extends AppCompatActivity implements android.view.View.OnClickListener{

    Button out,send;
    private EditText email;
    private EditText subject;
    private EditText contend;
    private String mail;
    private String Subject;
    private String Contend;
    static OutputStream ou=null;
    static BufferedReader bff=null;

    static private final String NAME_BASE64="eWwxMzY2MTM4NDU0\r\n";//用户名 base64 编码 ,注意是@前面的部分的base64编码
    static private final String PASS_BASE64="bGFpbDEzMTQ=\r\n";//密码base64编码
    static private final String FROME="MAIL FROM: <yl1366138454@sina.com>\r\n";//用户名,注意格式


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.getpage);

        out = (Button)this.findViewById(R.id.btnClose);
        out.setOnClickListener(this);
        send = (Button)this.findViewById(R.id.btnSend);
        send.setOnClickListener(this);
        email = (EditText)this.findViewById(R.id.email);
        subject = (EditText)this.findViewById(R.id.subject);
        contend = (EditText)this.findViewById(R.id.contend);
    }
    @Override
    public void onClick(View view)
    {
        if (view== send)
        {
            mail=email.getText().toString().trim();
            Subject=subject.getText().toString();
            Contend=contend.getText().toString();
            if(mail!=null)
            sendMail(Subject,Contend+".");
            else
                Toast.makeText(this,"输入错误，邮箱名不能为空!",Toast.LENGTH_SHORT).show();
        }
        else if(view== out)
        {
            finish();
        }
    }
    public void sendMail(String Subject,String Content)
    {
        try
        {
            mail=email.getText().toString().trim();
            StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            Socket socket = new Socket();

            socket.connect(new InetSocketAddress("smtp.sina.com", 25), 3000);//连接服务器，这里用的是新浪邮箱
            bff = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            ou = socket.getOutputStream();


            ou.write("HELO smtp.sina.com\r\n".getBytes("UTF-8"));//发送问候消息

            ou.write("AUTH LOGIN\r\n".getBytes("UTF-8"));//发送登陆命令

            ou.write(NAME_BASE64.getBytes("UTF-8"));//发送用户名的base64编码

            ou.write(PASS_BASE64.getBytes("UTF-8"));//发送密码的base64编码
            ou.write(FROME.getBytes("UTF-8"));//发送用户名，应定要和前面发送的编码一致

            ou.write(("RCPT TO: <"+mail+">\r\n").getBytes("UTF-8"));//发送收件人地址

            ou.write("DATA\r\n".getBytes("UTF-8"));//发送数据命令

            ou.write(("From:yl1366138454@sina.com\r\n"//发件人，要和前面的一致
                    +"To:"+mail+"\r\n" //收件人，要和前面的一致
                    + "Subject:"+Subject+"\r\n\r\n").getBytes("UTF-8"));//邮件主题

            ou.write(("\r\t"+Content).getBytes("UTF-8"));//邮件正文内容

            ou.write("\r\n.\r\n".getBytes("UTF-8"));//结束标志

            ou.write("QUIT\r\n".getBytes("UTF-8"));//退出登录

            ou.close(); // 关闭Socket输出流
            bff.close(); // 关闭Socket输入流
            socket.close(); // 关闭Socket
        }
        catch (Exception e)
        {
            Toast.makeText(this,"Error. " + e,Toast.LENGTH_SHORT).show();
        }
        Toast.makeText(this,"邮件发送成功!",Toast.LENGTH_SHORT).show();
        email.setText("");
        subject.setText("");
        contend.setText("");
    }


}

