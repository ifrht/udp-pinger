import java.net.*; //Network i�lemleri i�in Java'da kullan�lan k�t�phane

public class Client
{
	public static void main(String[] args) throws Exception
	{
		if (args.length < 3) //Program�n �al��abilmesi i�in 3 farkl� parametre girilmesi gerekiyor. Burada bunun kontrol� sa�land�.
		{
			System.out.println("Hata! Do�ru kullan�m a�a��daki gibidir:\njava Client <Hedef IP Adresi> <Hedef Port Numaras�> <Ping Atma Say�s�>"); 
			return;
		}
			String HedefIP = new String(args[0]); //Hedef IP adresi program�n ilk parametresidir.
			int HedefPort = Integer.parseInt(args[1]); //Port numaras� program�n ikinci parametresidir. args String oldu�u i�in int'e �evirildi.
			DatagramSocket clientSocket = new DatagramSocket(); // Socket olu�turuldu.
			InetAddress IPAdress = InetAddress.getByName(HedefIP); //Hedef IP adresi i�in InetAddress structure'� olu�turuldu
			String SendText = new String(); //G�nderilecek �rnek veri i�in String dizisi tan�mland�.
			SendText = "11111111111111111111111111111111"; //32 byte'�n hepsi 1 ile dolduruldu.
			byte [] sendData = new byte[32]; //Gelen veriyi tutmak i�in bir byte dizisi olu�turuldu.
			byte [] receiveData = new byte[32]; //G�nderilecek veri i�in byte dizisi olu�turuldu.
			sendData = SendText.getBytes(); //String verisi byte format�na �evrildi.
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAdress, HedefPort); //G�nderilecek UDP paketi olu�turuldu.
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length); //Sunucudan al�nacak UDP paketi olu�turuldu.
			for (int i=1;i <= Integer.parseInt(args[2]);i++) //args[2] parametresi ile ping i�leminin ka� defa yap�laca�� bilgisi kullan�c�dan al�nm��t�.
			{												 //Burdaki for d�ng�s� ile istenilen ping say�s� kadar i�lem tekrarlanacak.
			clientSocket.send(sendPacket); //Olu�turulan UDP paketi burada g�nderildi.
			long startTime = System.nanoTime(); //G�nderildikten hemen sonra nano saniye cinsinden zaman damgas� al�nd�.
			clientSocket.receive(receivePacket); //Sunucudan yan�t paketi al�nd�.
			long difference = System.nanoTime() - startTime; //�u anki zaman ile ba�lang�� zaman� aras�ndaki s�re �l��ld�.
			System.out.println("-------------------------------------------\nServer IP Adresi: " + receivePacket.getAddress().getHostAddress());
			System.out.println("Server Port Numaras�: " + receivePacket.getPort());					//
			System.out.println("Gelen Veri Boyutu: " + receivePacket.getLength() + " bytes");		//Elde edilen bilgiler istemci ekran�nda g�sterildi.
			System.out.println("Toplam Zaman: " + difference/1000000 + " ms");						//
			} 
			clientSocket.close(); //for d�ng�s� bittikten sonra socket kapat�ld�.
	}

}