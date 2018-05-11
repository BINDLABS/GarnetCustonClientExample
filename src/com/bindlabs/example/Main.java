package com.bindlabs.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.bindlabs.blockchain.node.Main_Client;
import com.bindlabs.blockchain.obj.Node;
import com.bindlabs.blockchain.obj.OnLoadListener;

public class Main {

	static OnLoadListener ll = new OnLoadListener() {//client node listener > working on this
        @Override
        public void OnBalanceLoadListener(String balance) {
      		 System.out.println("my balance : "+balance+"coin");
        }

        @Override
        public void OnReceiveListener(double amount) {
     		 System.out.println("Received : "+amount+"coin");
        }

        @Override
        public void OnSuccessfullySendListener(double amount,String balance) {
          	 System.out.println("sent successfully");
        }
    };
    
	public static void main(String[] args) {
		ArrayList<Node> nodes = new ArrayList<Node>();
		nodes.add(new Node("127.0.0.1",8146));//node server
		Main_Client mc = new Main_Client("chaindata/client","clientkey",nodes,ll);//datapath, keypath, nodes, listener
		
		String input="";
        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
        while(true) {
            try {
				input = keyboard.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
            if(input.equals("exit")){
                System.exit(0);
            }else if(input.startsWith("send")) {
           	 String amount = input.split(":")[2];
           	 String wallet = input.split(":")[1];
           	 mc.send(wallet,amount);
            }else if(input.startsWith("balance")) {
           	 if(input.contains(":")) {
               	 String wallet = input.split(":")[1];
              	System.out.println(wallet+" balance : "+ mc.getBalance(wallet)+"coin");
           	 }else
             	System.out.println("my balance : "+ mc.getBalance()+"coin");
            }else if(input.equals("last")) {
            	System.out.println("latest block : "+mc.getLastBlock().toString());
            }else if(input.equals("wallet")) {
            	System.out.println("wallet : "+ (mc.getWalletAddress()));
            }else{
            }
        }

	}
	
	 

}
