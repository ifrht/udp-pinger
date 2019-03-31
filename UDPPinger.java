import java.net.*; //Network i�lemleri i�in Java'da kullan�lan k�t�phane
import java.sql.Timestamp; //Tarih bilgisini alabilmek i�in kullan�lan k�t�phane

public class UDPPinger
{
	public static void main(String[] args) throws Exception
	{
		System.out.println("--------------------------\nComputer Networks 2 - HW 2");
		System.out.println("UDP Pinger Server Program�\n--------------------------");
		int ServerPort = 1234; //Sunucunun port numaras� tan�mland�.
		int ClientPort; //Yan�t vermek i�in daha sonra kullan�lacak istemci port numaras�
		DatagramSocket serverSocket = new DatagramSocket(ServerPort); //Socket olu�turuldu.
		byte[] receiveDataPacket = new byte[32]; //Gelen veriyi tutmak i�in bir byte dizisi olu�turuldu.
		byte[] sendDataPacket = new byte[32];	 //G�nderilecek veri i�in byte dizisi olu�turuldu.
		String sendText = new String(); //Ping testi i�in kullan�lacak String de�i�keni tan�mland�.
		sendText = "11111111111111111111111111111111"; // Olu�turulan Stringin i�erisi 1 ile dolduruldu.
		while (true) //Server k�sm� s�rekli �al���r durumda olacak ��kmak i�in CTRL+C kullan�labilir.
		{
			DatagramPacket receivePacket = new DatagramPacket(receiveDataPacket, receiveDataPacket.length); //UDP paketi olu�turuldu.
			serverSocket.receive(receivePacket); // UDP paketi al�n�yor.
			InetAddress IPAdress = receivePacket.getAddress(); //Gelen UDP paketinden istemci IP adresi elde edildi.
			ClientPort = receivePacket.getPort(); //Gelen UDP paketinden istemci port numaras� elde edildi.
			
			sendDataPacket = sendText.getBytes(); // G�nderilecek veri byte format�na �evrildi.
			DatagramPacket sendPacket = new DatagramPacket(sendDataPacket, sendDataPacket.length, IPAdress, ClientPort); // G�nderilecek UDP paketi olu�turuldu.
			serverSocket.send(sendPacket);
			System.out.println("---------------------------------------------\nClient IP Adresi: " 
			+ receivePacket.getAddress().getHostAddress() 
			+ "\n�stemci Port Numaras�: " + receivePacket.getPort()
			+ "\nAl�nan Veri Boyutu: " 
			+ receivePacket.getLength()); // �stemci adresi ve gelen veri boyutu server komut sat�r�nda g�sterildi.
			Timestamp timestamp = new Timestamp(System.currentTimeMillis()); //Tarih bilgisini almak i�in kullan�lan bir fonksiyon.
	        System.out.println("Tarih: " + timestamp);// Tarih bilgisi de server komut sat�r�nda g�sterildi.
	        											//Server k�sm� s�rekli �al��abilsin diye Socket kapat�lmad�. Program �al��t�k�a socket a��k kalacak.
		}
	}
}