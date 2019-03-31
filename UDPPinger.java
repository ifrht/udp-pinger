import java.net.*; //Network iþlemleri için Java'da kullanýlan kütüphane
import java.sql.Timestamp; //Tarih bilgisini alabilmek için kullanýlan kütüphane

public class UDPPinger
{
	public static void main(String[] args) throws Exception
	{
		System.out.println("--------------------------\nComputer Networks 2 - HW 2");
		System.out.println("UDP Pinger Server Programý\n--------------------------");
		int ServerPort = 1234; //Sunucunun port numarasý tanýmlandý.
		int ClientPort; //Yanýt vermek için daha sonra kullanýlacak istemci port numarasý
		DatagramSocket serverSocket = new DatagramSocket(ServerPort); //Socket oluþturuldu.
		byte[] receiveDataPacket = new byte[32]; //Gelen veriyi tutmak için bir byte dizisi oluþturuldu.
		byte[] sendDataPacket = new byte[32];	 //Gönderilecek veri için byte dizisi oluþturuldu.
		String sendText = new String(); //Ping testi için kullanýlacak String deðiþkeni tanýmlandý.
		sendText = "11111111111111111111111111111111"; // Oluþturulan Stringin içerisi 1 ile dolduruldu.
		while (true) //Server kýsmý sürekli çalýþýr durumda olacak çýkmak için CTRL+C kullanýlabilir.
		{
			DatagramPacket receivePacket = new DatagramPacket(receiveDataPacket, receiveDataPacket.length); //UDP paketi oluþturuldu.
			serverSocket.receive(receivePacket); // UDP paketi alýnýyor.
			InetAddress IPAdress = receivePacket.getAddress(); //Gelen UDP paketinden istemci IP adresi elde edildi.
			ClientPort = receivePacket.getPort(); //Gelen UDP paketinden istemci port numarasý elde edildi.
			
			sendDataPacket = sendText.getBytes(); // Gönderilecek veri byte formatýna çevrildi.
			DatagramPacket sendPacket = new DatagramPacket(sendDataPacket, sendDataPacket.length, IPAdress, ClientPort); // Gönderilecek UDP paketi oluþturuldu.
			serverSocket.send(sendPacket);
			System.out.println("---------------------------------------------\nClient IP Adresi: " 
			+ receivePacket.getAddress().getHostAddress() 
			+ "\nÝstemci Port Numarasý: " + receivePacket.getPort()
			+ "\nAlýnan Veri Boyutu: " 
			+ receivePacket.getLength()); // Ýstemci adresi ve gelen veri boyutu server komut satýrýnda gösterildi.
			Timestamp timestamp = new Timestamp(System.currentTimeMillis()); //Tarih bilgisini almak için kullanýlan bir fonksiyon.
	        System.out.println("Tarih: " + timestamp);// Tarih bilgisi de server komut satýrýnda gösterildi.
	        											//Server kýsmý sürekli çalýþabilsin diye Socket kapatýlmadý. Program çalýþtýkça socket açýk kalacak.
		}
	}
}