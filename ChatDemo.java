import java.io.*;
import java.net.*;

class  Send implements Runnable
{
	private DatagramSocket ds;
	Send(DatagramSocket ds)
	{
		this.ds = ds;
	}
	public void run()
	{
		try
		{
			BufferedReader br = 
						new BufferedReader(new InputStreamReader(System.in));
			String line = null;
			while ((line=br.readLine())!=null)
			{
				byte[] buf = line.getBytes();
				DatagramPacket dp =
						new DatagramPacket(buf,buf.length,InetAddress.getByName("192.168.1.102"),10002);
				ds.send(dp);
				if ("886".equals(line))
					break;
			}


		}
		catch (Exception e)
		{
			throw new RuntimeException("发送失败！");
		}
	}
}

class Rece implements Runnable
{
	private DatagramSocket ds;
	Rece(DatagramSocket ds)
	{
		this.ds = ds;
	}
	public void run()
	{
		try
		{
			byte[] buf = new byte[1024];
			while (true)
			{
				
				DatagramPacket dp = new DatagramPacket(buf,buf.length);
				
				ds.receive(dp);

				String ip = dp.getAddress().getHostAddress();
				String data = new String(dp.getData(),0,dp.getLength());
				if("886".equals(data))
				{
					System.out.println(ip+"离开聊天室");
					break;
				}
				
				System.out.println(ip+".."+data);
			}

		}
		catch (Exception e)
		{
			throw new RuntimeException("接受失败！");
		}
	}
}

class ChatDemo
{
	public static void main(String[] args)throws Exception
	{
		DatagramSocket se = new DatagramSocket();
		DatagramSocket re = new DatagramSocket(10002);

		new Thread(new Send(se)).start();
		new Thread(new Rece(re)).start();
	}
}
