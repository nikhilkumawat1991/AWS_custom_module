package com.example;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;

public class mqttsubscriber
{
    public MqttClient mqttClient;
    public  MqttClient connect_to_broker()  throws MqttException
    {
        try {
            mqttClient = new MqttClient("tcp://localhost:1883", "HelloWorldSub");
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            connOpts.setUserName("nikhil");
            connOpts.setPassword("nikhil".toCharArray());
            connOpts.setAutomaticReconnect(true);
            mqttClient.setCallback(new SimpleMqttCallBack());
            mqttClient.connect();
            System.out.println("Connected..!!");
            mqttClient.subscribe("test");
            System.out.println("Subscribed to test topic..!!");

        }
        catch (Exception e)
        {
            System.out.println("exception while connecting to mqtt broker: "+ e);
            System.exit(0);
        }
        return mqttClient;
    }
}

