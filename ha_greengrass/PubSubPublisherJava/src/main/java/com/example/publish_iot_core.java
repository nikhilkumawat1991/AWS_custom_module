package com.example;

import software.amazon.awssdk.aws.greengrass.GreengrassCoreIPCClient;
import software.amazon.awssdk.aws.greengrass.model.*;
import software.amazon.awssdk.crt.mqtt.MqttClient;
import software.amazon.awssdk.eventstreamrpc.EventStreamRPCConnection;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class publish_iot_core {

        //public GreengrassCoreIPCClient ipcClient;
        QOS qos = QOS.AT_LEAST_ONCE;

        public void connect_ipc()
        {
            try (EventStreamRPCConnection eventStreamRPCConnection = IPCUtils.getEventStreamRpcConnection()) {
                // ipcClient = new GreengrassCoreIPCClient(eventStreamRPCConnection);
            } catch (InterruptedException e) {
                System.out.println("Publisher interrupted.");
            } catch (Exception e) {
                System.err.println("Exception occurred when using IPC.");
                e.printStackTrace();
            }
        }

        public void send_to_core(String message) throws ExecutionException, InterruptedException {
            System.out.println(message+" -----> to be sent to core...!!");
            String topic = "test/topic";
            try (EventStreamRPCConnection eventStreamRPCConnection = IPCUtils.getEventStreamRpcConnection())
            {
                GreengrassCoreIPCClient ipcClient = new GreengrassCoreIPCClient(eventStreamRPCConnection);
                PublishToIoTCoreRequest publishToIoTCoreRequest = new PublishToIoTCoreRequest();
                publishToIoTCoreRequest.setTopicName(topic);
                publishToIoTCoreRequest.setPayload(message.getBytes(StandardCharsets.UTF_8));
                publishToIoTCoreRequest.setQos(qos);
                ipcClient.publishToIoTCore(publishToIoTCoreRequest, Optional.empty());
                System.out.println("Successfully published to topic: " + topic);
            } catch (InterruptedException e) {
                System.out.println("Publisher interrupted.");
            } catch (Exception e) {
                System.err.println("Exception occurred when using IPC.");
                e.printStackTrace();
            }
        }
}
