#!/bin/bash
echo "pls select the option you want to do"

echo "0. connect aliyun ssh"
echo "1. ./gradlew clean build"
echo "2. docker build --build-arg JAR_FILE=build/libs/*.jar -t ssevening/gs-spring-boot-docker ."
echo "3. docker tag   8392d1854b81 ssevening/gs-spring-boot-docker:0.8.1"
echo "4. docker push ssevening/gs-spring-boot-docker"
echo "5. nohup docker run -p 80:80 -p 443:443 -t ssevening/gs-spring-boot-docker /bin/bash &"
echo "6. mysql -urootofdeals -pHello1103 -hrm-rj99m0gh0h79uhl56yo.mysql.rds.aliyuncs.com"
echo "6. scp root@47.251.8.226:nohup.out ./"




read x

if [ $x = "0" ]; then
	echo $x
	ssh root@47.251.8.226	
elif [ $x = "1" ]; then
	echo $x
	./gradlew clean build
elif [ $x = "2" ]; then	
	echo $x
	docker build --build-arg JAR_FILE=build/libs/*.jar -t ssevening/gs-spring-boot-docker .
elif [ $x = "3" ]; then	
	echo $x
	echo "docker tag   8392d1854b81 ssevening/gs-spring-boot-docker:0.8.1"
elif [ $x = "4" ]; then		
	echo $x
	docker push ssevening/gs-spring-boot-docker
elif [ $x = "5" ]; then		
	echo $x
	echo "nohup docker run -p 80:80 -p 443:443 -t ssevening/gs-spring-boot-docker /bin/bash &"
elif [ $x = "7" ]; then		
	echo $x
	echo "scp root@47.251.8.226:nohup.out ./"	
elif [ $x = "6" ]; then		
	echo $x
	mysql -urootofdeals -pHello1103 -hrm-rj99m0gh0h79uhl56yo.mysql.rds.aliyuncs.com
fi
