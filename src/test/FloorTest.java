package test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import org.junit.Test;

import main.Floor;
public class FloorTest {



	@Test
	public void testReceiveRequest()
	{
		
		Thread floorThread = new Thread(new Runnable()
		{
		@Override
		public void run()
		{
			Floor f = new Floor();
			f.run();
		}
		
	});
		
		floorThread.start();
		byte[] request = new byte[] {0};
		try {
			DatagramPacket requestPckt = new DatagramPacket(request,request.length,
					InetAddress.getLocalHost(),45);
			DatagramSocket sendReq = new DatagramSocket();
			sendReq.send(requestPckt);
			sendReq.close();
		} catch (IOException e) {
		
			fail("Did not send ");
			e.printStackTrace();
		} 
		
		byte[] arrival = new byte[100];
		try {
			Thread.sleep(19500);
			DatagramSocket getArrival = new DatagramSocket(69);
			
			DatagramPacket pck = new DatagramPacket(arrival,arrival.length);
			getArrival.receive(pck);
			getArrival.close();
			
			
		} catch (InterruptedException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue((int)arrival[0] == 4);
		assertTrue((int)arrival[1] == 0);
		
		
	}
		
		
	

	
}
	


