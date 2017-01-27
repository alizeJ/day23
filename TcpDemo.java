import java.io.*;
import java.net.*;

class  TcpClient
{
	public static void main(String[] args) throws Exception
	{
		Socket s = new Socket("192.168.1.102",10004);

		OutputStream os = s.getOutputStream();
		
		os.write("�������ð�".getBytes());

		
		//��ȡ�������Ϣ����
		InputStream in = s.getInputStream();

		byte[] buf = new byte[1024];

		int len = in.read(buf);
		
		System.out.println(new String(buf,0,len));

		s.close();

	}
}

class TcpServer
{
	public static void main(String[] args)	throws Exception
	{
		ServerSocket ss = new ServerSocket(10004);

		Socket s = ss.accept();

		String ip = s.getInetAddress().getHostAddress();
		System.out.println(ip+"...������");
		
		InputStream in = s.getInputStream();

		byte[] buf = new byte[1024];

		int len =in.read(buf);

		System.out.println(new String(buf,0,len));

		OutputStream out = s.getOutputStream();

		out.write("���������յ���".getBytes());

		s.close();
		ss.close();


	}
}
