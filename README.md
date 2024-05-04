# TextCrypt
![Screenshot of the application](/screenshot/TextCrypt-UI.png)

## Requirements
 - Java 1.5 or greater should be installed.


Download TextCrypt from [here](https://github.com/sahilvinodMhatre/TextCrypt/releases/download/binary/TextCrypt.jar).

## Run using Docker

For Linux:

```
xhost +local:
sudo docker run -it --env="DISPLAY" --net=host -v textcrypt:/data  equin0x/textcrypt:1.0
```

For Windows:

- Install VcXsrv Windows X Server from [here](https://sourceforge.net/projects/vcxsrv/)
- While setting up the X server disable the access control 

```
# Make sure you change the {IP} in the below command with your IP where the X server is running, the localhost/127.0.0.1 will not work as an IP, for example - docker run -it -e DISPLAY=192.168.0.1:0 --net=host -v textcrypt:/data equin0x/textcrypt:1.0  
docker run -it -e DISPLAY={IP}:0 --net=host -v textcrypt:/data equin0x/textcrypt:1.0
```
