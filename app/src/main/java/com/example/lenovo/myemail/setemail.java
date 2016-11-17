package com.example.lenovo.myemail;

/**
 * Created by lenovo on 2016/11/15.
 */

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;

public class setemail extends AppCompatActivity implements android.view.View.OnClickListener{

    Button setout,setsend;
    private EditText setemail;
    private EditText setsubject;
    private EditText setcontend;
    private EditText settime;
    private EditText setnum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setpage);

        setout = (Button)this.findViewById(R.id.setClose);
        setout.setOnClickListener(this);
        setsend = (Button)this.findViewById(R.id.setSend);
        setsend.setOnClickListener(this);
        setemail = (EditText)this.findViewById(R.id.setemail);
        setsubject = (EditText)this.findViewById(R.id.setsubject);
        setcontend = (EditText)this.findViewById(R.id.setcontend);
        settime= (EditText)this.findViewById(R.id.settime);
        setnum = (EditText)this.findViewById(R.id.num);
    }
    @Override
    public void onClick(View view)
    {
        if (view== setsend)
        {
            setMail();
        }
        else if(view== setout)
        {
            finish();
        }
    }
    public void setMail()
    {
        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Socket client = null;
        String protocol = "pop3";//使用pop3协议
        boolean isSSL = true;//使用SSL加密
        String host = "pop.sina.com";//QQ邮箱的pop3服务器
        int port = 995;//端口
        String username = "yl1366138454@sina.com";//用户名
        String password = "lail1314";//密码

	       /*
	       *Properties是一个属性对象，用来创建Session对象
	       */
        Properties props = new Properties();
        props.put("mail.pop3.ssl.enable", isSSL);
        props.put("mail.pop3.host", host);
        props.put("mail.pop3.port", port);
	       /*
	       *Session类定义了一个基本的邮件对话。
	       */
        Session session = Session.getInstance(props);

	       /*
	        * Store类实现特定邮件协议上的读、写、监视、查找等操作。
	        * 通过Store类可以访问Folder类。
	        * Folder类用于分级组织邮件，并提供照Message格式访问email的能力。
	        */
        Store store = null;
        Folder folder = null;
        try {
            store = session.getStore(protocol);
            store.connect(username, password);

            folder = store.getFolder("INBOX");
            folder.open(Folder.READ_ONLY);//在这一步，收件箱所有邮件将被下载到本地

            int size = folder.getMessageCount();//获取邮件数目
            if(size==0)
            {
                folder.close(true);
                store.close();
            }
            else
            {
            Message message = folder.getMessage(size);//取得最新的那个邮件

            //解析邮件内容
            String from = message.getFrom()[0].toString();
            String subject = message.getSubject();
            Date date = message.getSentDate();
            SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss EEEE");
            String da = sDateFormat.format(date);
            String result=from.substring(from.indexOf("<"),from.indexOf(">")+1);
            setnum.setText(String.valueOf(size));
            setemail.setText(result);
            setsubject.setText(subject);
            settime.setText(da);
            setcontend.setText("此服务暂时不能使用，请见谅！");
            }
        } catch (Exception e) {
            Toast.makeText(this,"Error. " + e,Toast.LENGTH_SHORT).show();
        } finally {
            try {
                if (folder != null)
                {
                    folder.close(false);
                }
                if (store != null) {
                    store.close();
                }
            } catch (Exception e) {
                Toast.makeText(this,"Error. " + e,Toast.LENGTH_SHORT).show();
            }
        }
    }


}






