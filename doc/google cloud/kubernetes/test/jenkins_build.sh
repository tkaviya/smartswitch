	sym_server
	This is the Empower Telecommunications and Technology Limited Wallet, Electronic Voucher Distribution & Transaction Switch

	GIT: https://github.com/empowerttl/symbiosis/

	- GitHub hook trigger for GITScm polling
	- Poll SCM
	Schedule: H/5 * * * *

Invoke Gradle
	Switches: -x test
	Tasks: 
		clean
		build
	
	
Execute Shell:
	echo "Copying Front End and API to Wildfly deployment folder"
	cp /var/lib/jenkins/workspace/sym_server/sym_web_ui/build/libs/sym_web_ui-1.0.0.war /opt/wildfly/standalone/deployments/symbiosis.war
	sudo systemctl restart wildfly
	
Email Notification
	Recipients
		empowerttl@gmail.com, tsungai.kaviya@gmail.com, fiademo.dogble@newdawntek.com, paulm@axilir.com
		
		- Send e-mail for every unstable build	
 		- Send separate e-mails to individuals who broke the build
		
		
------------------------------------------------------------------------------------------------------------------------------------------------------------

	sym_mobile
	This is the Empower Telecommunications and Technology Limited Wallet, Electronic Voucher Distribution & Transaction Switch Mobile Application

	GIT: https://github.com/tkaviya/sym_mobile

	- GitHub hook trigger for GITScm polling
	- Poll SCM
	Schedule: H/5 * * * *

Execute Shell:
	npm install
	npm audit fix
	export JAVA_HOME=/usr/lib/jvm/java-1.8.0-openjdk-1.8.0.242.b08-0.el8_1.x86_64/
	export PATH="$PATH:$JAVA_HOME/bin:/opt/android-sdk/:/opt/android-sdk/tools/bin:/opt/android-sdk/platform-tools"

	ionic cordova build android --no-interactive --confirm --aot --minifyjs --minifycss --optimizejs --verbose -- -- --minSdkVersion=19
	cp /var/lib/jenkins/workspace/sym_mobile/platforms/android/app/build/outputs/apk/debug/app-debug.apk /var/www/html/symbiosis/ETTLVision.apk

	#ionic cordova build android --no-interactive --confirm --prod --aot --minifyjs --minifycss --optimizejs --release --verbose -- -- --minSdkVersion=19
	#cp /var/lib/jenkins/workspace/sym_mobile/platforms/android/app/build/outputs/apk/release/app-release-unsigned.apk /var/www/html/symbiosis/ETTLVision.apk
	
Email Notification
	Recipients
		empowerttl@gmail.com, tsungai.kaviya@gmail.com, fiademo.dogble@newdawntek.com, paulm@axilir.com
		
		- Send e-mail for every unstable build	
 		- Send separate e-mails to individuals who broke the build