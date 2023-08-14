package com.nia;

import com.nia.client.Client;
import com.nia.dao.loader.ConfigLoader;

public class ClientLauncher {
    public static void main(String[] args) {
        try {
            final int PORT = ConfigLoader.getInt("port");
            final String HOST = ConfigLoader.getString("host");

            Client client = new Client(HOST, PORT);

            client.start();
        } catch (NullPointerException | NumberFormatException e) {
            System.out.println("配置参数错误!请检查!");
        }
    }
}
