package com.example;

import software.amazon.awssdk.crt.CRT;
import software.amazon.awssdk.crt.CrtResource;
import software.amazon.awssdk.aws.greengrass.GreengrassCoreIPCClient;
import software.amazon.awssdk.aws.greengrass.model.*;

import software.amazon.awssdk.eventstreamrpc.EventStreamRPCConnection;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import com.example.IPCUtils;
import org.eclipse.paho.client.mqttv3.*;

class App {

    private static MqttClient client;

    public static void main(String[] args) throws MqttException {
        String message = "Hello from the pub/sub publisher (Java).";
        String topic = "test/topic";

        //connect and subscribe to MQTT broker
        mqttsubscriber mqtsub = new mqttsubscriber();
        client = mqtsub.connect_to_broker();

        //create IPC connection
        //publish_iot_core pic = new publish_iot_core();
        //pic.connect_ipc();

        }
    }

