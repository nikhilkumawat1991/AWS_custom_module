# Home Assistant

Home Assistant is free and open-source home automation software designed to be the central control system for smart home devices with focus on local control and privacy

## Pre-requisites
Make sure that VHC toolkit is installed on your system and Veea hubs are configured. If not, refer to VHC toolkit installation guide.


## Build

Use the following command to build the image 
 
```bash
vhc build --target vhc05 --unsigned      #for VHC05 hub
```

## Deployment
Once the image has been built, you can deploy it to Veea hub

```bash
$ vhc hub image --create bin/home_assistant_rasp_05-armhf\:2.0.6.tar 
Creating image push for file [bin/home_assistant_rasp_05-armhf:2.0.6.tar] on C05BCBA0C0A000004038:9000 (https://180.0.0.1:9000/images/push)...
 384.94 MB / 384.94 MB [===========================================================================================================================================================================] 100.00%
{"image_id": "61ce10b6248f8a55c010111d422be6d270fffaf72e6b4ee6ab7fef44eeaa2b45"}
```

* **Delete an image**

Use the following command to delete an image:

```bash
    $ vhc hub image --delete 61ce10b6248f8a55c010111d422be6d270fffaf72e6b4ee6ab7fef44eeaa2b45 
    
    Deleting image 61ce10b6248f8a55c010111d422be6d270fffaf72e6b4ee6ab7fef44eeaa2b45 on <HUB_ADDRESS>:9000(https://<IP_ADDRESS>:9000/images/61ce10b6248f8a55c010111d422be6d270fffaf72e6b4ee6ab7fef44eeaa2b45)...
```


* **Creating container from image**

Use the following command to create a container:

```bash
    $ vhc hub image --create-container 61ce10b6248f8a55c010111d422be6d270fffaf72e6b4ee6ab7fef44eeaa2b45 
    
    Creating container from image 61ce10b6248f8a55c010111d422be6d270fffaf72e6b4ee6ab7fef44eeaa2b45 on C05BCB00C0A000001127:9000(https://<IP_ADDRESS>:9000/images/61ce10b6248f8a55c010111d422be6d270fffaf72e6b4ee6ab7fef44eeaa2b45/create_container)...
    351 B / 351 B [=====================================================================================] 100.00%
    {
      "container_id": "c4a2da5042b95f85cbfca8eb136ad9b390cafe380352606a6654d2dc2c99274a",
      "detached": true
    }  
```

    You will get a container-ID after creating container. Use that ID to start/stop/attach the container.

* **Start container**

Use the following command to start the container:

```bash
    $ vhc hub container --start c4a2da5042b95f85cbfca8eb136ad9b390cafe380352606a6654d2dc2c99274a
    Starting container c4a2da5042b95f85cbfca8eb136ad9b390cafe380352606a6654d2dc2c99274a C05BCB00C0A000001127:9000(https://<IP_ADDRESS>:9000/containers/c4a2da5042b95f85cbfca8eb136ad9b390cafe380352606a6654d2dc2c99274a/start)...
    Success
```


* **Stop container**

Use the following command to stop the container:

```bash
    $ vhc hub container --stop c4a2da5042b95f85cbfca8eb136ad9b390cafe380352606a6654d2dc2c99274a
    Stopping container c4a2da5042b95f85cbfca8eb136ad9b390cafe380352606a6654d2dc2c99274a C05BCB00C0A000001127:9000 (https://<IP_ADDRESS>:9000/containers/c4a2da5042b95f85cbfca8eb136ad9b390cafe380352606a6654d2dc2c99274a/stop)...
    Success
```

* **Attach container**

Use the following command to attach to the container:

```bash
    $ vhc hub container --attach c4a2da5042b95f85cbfca8eb136ad9b390cafe380352606a6654d2dc2c99274a "/bin/sh -il"
     Attaching to stdin/stdout/stderr on C05BCB00C0A000001127:9001...
     Attaching to container c4a2da5042b95f85cbfca8eb136ad9b390cafe380352606a6654d2dc2c99274a C05BCB00C0A000001127:9000 (https://<IP_ADDRESS>:9000/containers/c4a2da5042b95f85cbfca8eb136ad9b390cafe380352606a6654d2dc2c99274a/attach)...
     Success
```

* **Delete Container **

Use the following command to delete the container:

```bash
    $ vhc hub container --delete c4a2da5042b95f85cbfca8eb136ad9b390cafe380352606a6654d2dc2c99274a

    Deleting container c4a2da5042b95f85cbfca8eb136ad9b390cafe380352606a6654d2dc2c99274a on <HUB_ADDRESS>:9000(https://<IP_ADDRESS>:9000/containers/c4a2da5042b95f85cbfca8eb136ad9b390cafe380352606a6654d2dc2c99274a)...
```


## Access Home Assistant UI
Home assistant provides a UI for controlling devices.


**Note**: Generally for the first time Home assistant takes 10-15 minutes to start. But thats only one time. 


* Access the Home assistant UI using ```"<IP-address of VeeaHub>:9070"```.


**Add ZHA Integration**

* Once Home Assistant UI starts, user can add ZHA integration by navigating to **configuration** > **Integration** > **Add Integration** > **zha**

     ![zha_name](.//images/zha_name.png)
  
* Select the default usb path which will come prepopulated on veeahub: 
 
     ![usb_path](.//images/usb_path.png)


* After selecting a success message will be shown as below:

    ![success](.//images/success.png)

    
* After that an intergration tile will be shown on dashboard. To add a device click on **configure** > **Add device**. Make sure devices are in pairing mode. 
 
   ![configure](.//images/configure.png)


* Once user clicks on **Add device** ZHA will start discovering the devices and will pair them. And user can see the device on the UI:

    ![details](.//images/details.png)
  


