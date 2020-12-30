	Jarvis
	This is the T3raTech Solutions Electronic Voucher Distribution Switch

	GIT: https://github.com/t3ratech/jarvis/

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
	cp /var/lib/jenkins/workspace/Jarvis/ettl_web_ui/build/libs/ettl_web_ui-1.0.0.war /opt/wildfly/standalone/deployments/admin.war
	cp /var/lib/jenkins/workspace/Jarvis/sym_api/build/libs/sym_api-1.0.0.war /opt/wildfly/standalone/deployments/api.war

Execute Shell:
	echo "Copying latest POS binary to download folder"
	cp -R /var/lib/jenkins/workspace/Jarvis/ettl_pos/dist/ettl-falcon.jar /var/www/html/ettl_pos/
	
Email Notification
	Recipients
		t3ratech@gmail.com, tsungai.kaviya@gmail.com, fiademo.dogble@newdawntek.com
		
		- Send e-mail for every unstable build	
 		- Send separate e-mails to individuals who broke the build