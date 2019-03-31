import java.net.*; //Network iþlemleri için Java'da kullanýlan kütüphane

public class Client
{
	public static void main(String[] args) throws Exception
	{
		if (args.length < 3) //Programýn çalýþabilmesi için 3 farklý parametre girilmesi gerekiyor. Burada bunun kontrolü saðlandý.
		{
			System.out.println("Hata! Doðru kullaným aþaðýdaki gibidir:\njava Client <Hedef IP Adresi> <Hedef Port Numarasý> <Ping Atma Sayýsý>"); 
			return;
		}
			String HedefIP = new String(args[0]); //Hedef IP adresi programýn ilk parametresidir.
			int HedefPort = Integer.parseInt(args[1]); //Port numarasý programýn ikinci parametresidir. args String olduðu için int'e çevirildi.
			DatagramSocket clientSocket = new DatagramSocket(); // Socket oluþturuldu.
			InetAddress IPAdress = InetAddress.getByName(HedefIP); //Hedef IP adresi için InetAddress structure'ý oluþturuldu
			String SendText = new String(); //Gönderilecek örnek veri için String dizisi tanýmlandý.
			SendText = "11111111111111111111111111111111"; //32 byte'ýn hepsi 1 ile dolduruldu.
			byte [] sendData = new byte[32]; //Gelen veriyi tutmak için bir byte dizisi oluþturuldu.
			byte [] receiveData = new byte[32]; //Gönderilecek veri için byte dizisi oluþturuldu.
			sendData = SendText.getBytes(); //String verisi byte formatýna çevrildi.
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAdress, HedefPort); //Gönderilecek UDP paketi oluþturuldu.
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length); //Sunucudan alýnacak UDP paketi oluþturuldu.
			for (int i=1;i <= Integer.parseInt(args[2]);i++) //args[2] parametresi ile ping iþleminin kaç defa yapýlacaðý bilgisi kullanýcýdan alýnmýþtý.
			{												 //Burdaki for döngüsü ile istenilen ping sayýsý kadar iþlem tekrarlanacak.
			clientSocket.send(sendPacket); //Oluþturulan UDP paketi burada gönderildi.
			long startTime = System.nanoTime(); //Gönderildikten hemen sonra nano saniye cinsinden zaman damgasý alýndý.
			clientSocket.receive(receivePacket); //Sunucudan yanýt paketi alýndý.
			long difference = System.nanoTime() - startTime; //þu anki zaman ile baþlangýç zamaný arasýndaki süre ölçüldü.
			System.out.println("-------------------------------------------\nServer IP Adresi: " + receivePacket.getAddress().getHostAddress());
			System.out.println("Server Port Numarasý: " + receivePacket.getPort());					//
			System.out.println("Gelen Veri Boyutu: " + receivePacket.getLength() + " bytes");		//Elde edilen bilgiler istemci ekranýnda gösterildi.
			System.out.println("Toplam Zaman: " + difference/1000000 + " ms");						//
			} 
			clientSocket.close(); //for döngüsü bittikten sonra socket kapatýldý.
	}

}