package NetworkingClient;

import java.io.*; 
import java.net.*; 
  
public class UDPClient { 
	
    public static void main(String args[]) throws Exception  { 
    	//Read the user input from the console
    	BufferedReader inFromUser = 
    			new BufferedReader(new InputStreamReader(System.in)); 
  
    	//Create the client socket
    	DatagramSocket clientSocket = new DatagramSocket(); 
	  
    	//Get the ip-address from the host (DNS names can be used)
    	InetAddress IPAddress = InetAddress.getByName("192.168.0.101");
    	
    	byte[] sendData = new byte[1024]; //The data to be send by the client
    	byte[] receiveData = new byte[1024]; //The data to be received by the server
	  
    	String sentence = inFromUser.readLine(); //Read the user input
    	sendData = sentence.getBytes(); //Transform the user input to bytes      

    	//Create a new packet with address and its port
    	DatagramPacket sendPacket = 
    			new DatagramPacket(sendData, sendData.length, IPAddress, 1046); 
    	  
    	clientSocket.send(sendPacket); //Send the packet
    	  
    	//Pre-create the packet in which the response from the server will be located
    	DatagramPacket receivePacket = 
    			new DatagramPacket(receiveData, receiveData.length); 
    	  
    	clientSocket.receive(receivePacket); //Receive the packet
    	 
    	//Get the data out of the bytes packet
    	String modifiedSentence = 
    			new String(receivePacket.getData()); 
    	  
    	System.out.println("FROM SERVER:" + modifiedSentence); 
    	clientSocket.close(); //Close the connection with the server
    } 
} 