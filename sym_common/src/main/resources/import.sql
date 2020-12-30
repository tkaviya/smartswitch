#system settings
insert ignore into sym_config(config_id,is_enabled,config_name,config_value,config_description) values (1,1,'system_name','SmartSwitch','System Name');
insert ignore into sym_config(config_id,is_enabled,config_name,config_value,config_description) values (2,1,'system_user_id','0','AuthUser ID for the system account');
insert ignore into sym_config(config_id,is_enabled,config_name,config_value,config_description) values (3,1,'system_base_path','https://t3ratech.com/symbiosis','Base path of deployment');
insert ignore into sym_config(config_id,is_enabled,config_name,config_value,config_description) values (4,1,'config_update_duration','1440','How often switch re-reads configs from the database');
insert ignore into sym_config(config_id,is_enabled,config_name,config_value,config_description) values (5,1,'thread_core_pool_size','20','Number of initial threads available');
insert ignore into sym_config(config_id,is_enabled,config_name,config_value,config_description) values (6,1,'thread_max_pool_size','50','Maximum number threads available');
insert ignore into sym_config(config_id,is_enabled,config_name,config_value,config_description) values (7,1,'mutex_lock_wait_time','10000','Milliseconds to wait for mutex');
insert ignore into sym_config(config_id,is_enabled,config_name,config_value,config_description) values (8,1,'mutex_lock_wait_interval','1000','Milliseconds between checking mutex lock');
insert ignore into sym_config(config_id,is_enabled,config_name,config_value,config_description) values (9,1,'api_sync_timeout','20','Number of seconds before timing out a synchronous network communication');
insert ignore into sym_config(config_id,is_enabled,config_name,config_value,config_description) values (10,1,'api_async_timeout','90','Number of seconds before timing out an asynchronous network communication');
#sms settings
insert ignore into sym_config(config_id,is_enabled,config_name,config_value,config_description) values (20,1,'sms_enable','true','Are all SMSs enabled?');
insert ignore into sym_config(config_id,is_enabled,config_name,config_value,config_description) values (21,1,'sms_max_length','160','Maximum length of SMSs to allow');
#sms message settings
insert ignore into sym_config(config_id,is_enabled,config_name,config_value,config_description) values (25,1,'sms_mobile_registration','You have successfully registered a mobile account on the %sys_name% wallet platform with username %username%','SMS sent at successful registration');
insert ignore into sym_config(config_id,is_enabled,config_name,config_value,config_description) values (26,1,'sms_pos_registration','You have been registered for a POS account on the %sys_name% wallet platform. Username: %username%. Pin: %pin%. You can now load funds & start vending','SMS sent at successful registration');
insert ignore into sym_config(config_id,is_enabled,config_name,config_value,config_description) values (27,1,'sms_wallet_load','Your %sys_name% wallet has been loaded with %amount% received via %deposit_type%. New balance: %current_balance%','SMS sent after wallet is loaded');
insert ignore into sym_config(config_id,is_enabled,config_name,config_value,config_description) values (28,1,'sms_pinbased_voucher_purchase','You have successfully purchased %amount% %service_provider% %voucher_type% from %sys_name% for recipient %recipient%','SMS sent after purchasing a pinbased voucher');
insert ignore into sym_config(config_id,is_enabled,config_name,config_value,config_description) values (29,1,'sms_pinbased_voucher_received','%sys_name% %amount% %service_provider% %voucher_type%. Voucher Pin: %voucher_pin%. Serial: %voucher_serial%','SMS when you receive a pinbased voucher');
insert ignore into sym_config(config_id,is_enabled,config_name,config_value,config_description) values (30,1,'sms_pinless_voucher_purchase','You have successfully purchased %amount% %service_provider% %voucher_type% from %sys_name% for recipient %recipient%','SMS sent after purchasing a pinless voucher');
insert ignore into sym_config(config_id,is_enabled,config_name,config_value,config_description) values (31,1,'sms_pinless_voucher_received','%sys_name% %amount% %service_provider% %voucher_type% has been credited to your account by %sender%','SMS when you receive pinless recharge');
insert ignore into sym_config(config_id,is_enabled,config_name,config_value,config_description) values (32,1,'sms_wallet_tranfer_sent','%sys_name% wallet transfer successful. Amount: %amount%. New balance: %current_balance%. Recipient: %first_name% %last_name% (%recipient_msisdn%). Ref: %transfer_id%','SMS sent after sending a wallet transfer');
insert ignore into sym_config(config_id,is_enabled,config_name,config_value,config_description) values (33,1,'sms_wallet_tranfer_receieved','Received %sys_name% wallet transfer of %amount% from %first_name% %last_name% (%sender_msisdn%). New Balance: %current_balance%. Ref: %transfer_id%','SMS sent when you receive a wallet transfer');
insert ignore into sym_config(config_id,is_enabled,config_name,config_value,config_description) values (34,1,'sms_pin_reset','You have successfully changed your %sys_name% wallet pin to %pin% for username %username%','SMS sent when you reset your pin');
insert ignore into sym_config(config_id,is_enabled,config_name,config_value,config_description) values (35,1,'sms_password_reset','You have successfully changed your %sys_name% wallet password to %password% for username %username%','SMS sent when you reset your password');
#email settings
insert ignore into sym_config(config_id,is_enabled,config_name,config_value,config_description) values (51,1,'email_enable','true','Are all emails enabled?');
insert ignore into sym_config(config_id,is_enabled,config_name,config_value,config_description) values (52,1,'email_protocol','smtp','Email protocol');
insert ignore into sym_config(config_id,is_enabled,config_name,config_value,config_description) values (53,1,'email_host','localhost','SMTP host');
insert ignore into sym_config(config_id,is_enabled,config_name,config_value,config_description) values (54,1,'email_port','25','SMTP port');
insert ignore into sym_config(config_id,is_enabled,config_name,config_value,config_description) values (55,1,'email_username','t3ratech','SMTP username');
insert ignore into sym_config(config_id,is_enabled,config_name,config_value,config_description) values (56,1,'email_password','3mp0wER@dm1n','SMTP password');
insert ignore into sym_config(config_id,is_enabled,config_name,config_value,config_description) values (57,1,'email_smtp_auth','false','Authenticate on SMTP');
insert ignore into sym_config(config_id,is_enabled,config_name,config_value,config_description) values (58,1,'email_smtp_starttls_enable','false','Enable TLS Authentication');
insert ignore into sym_config(config_id,is_enabled,config_name,config_value,config_description) values (59,1,'email_smtp_debug','false','Show SMTP debug messages?');
insert ignore into sym_config(config_id,is_enabled,config_name,config_value,config_description) values (60,1,'email_from','no-reply@t3ratech.com','Sender "from address" used for system emails');
insert ignore into sym_config(config_id,is_enabled,config_name,config_value,config_description) values (61,1,'email_alert_to','tsungai@t3ratech.com','Where email is sent when there is a system alert');
#localization settings
insert ignore into sym_config(config_id,is_enabled,config_name,config_value,config_description) values (80,1,'default_country','Ghana','Default country for user registrations');
insert ignore into sym_config(config_id,is_enabled,config_name,config_value,config_description) values (81,1,'default_country_code','233','Default country code for phone numbers');
insert ignore into sym_config(config_id,is_enabled,config_name,config_value,config_description) values (82,1,'default_currency_symbol','GHS','Default currency for transactions');
insert ignore into sym_config(config_id,is_enabled,config_name,config_value,config_description) values (83,1,'default_language','English','Default language for user registrations');
#integration settings : VonageNexmo
insert ignore into sym_config(config_id,is_enabled,config_name,config_value,config_description) values (90,1,'vonage_api_key','361b5ca9','API Key for Vonage Nexmo');
insert ignore into sym_config(config_id,is_enabled,config_name,config_value,config_description) values (91,1,'vonage_api_secret','FmSxQXZmIIvM1aWl','Password for Vonage SMS API');
insert ignore into sym_config(config_id,is_enabled,config_name,config_value,config_description) select  92,1,'vonage_sms_from',sc.config_value,'From name on SMS' from sym_config sc where sc.config_name = 'system_name';
insert ignore into sym_config(config_id,is_enabled,config_name,config_value,config_description) values (93,1,'vonage_sms_max_retries','5','Number of times to retry when an SMS fails');
insert ignore into sym_config(config_id,is_enabled,config_name,config_value,config_description) values (94,1,'vonage_sms_retry_wait_time','10000','Amount of time (in milliseconds) to wait between retries if SMS fails');
#registration settings
# insert ignore into sym_config(config_id,is_enabled,config_name,config_value,config_description) values (100,1,'default_desktop_group','DESKTOP_AGENT','Default auth group for desktop user registrations');
insert ignore into sym_config(config_id,is_enabled,config_name,config_value,config_description) values (101,1,'default_web_auth_group','WEB_AGENT','Default auth group for web user registrations');
insert ignore into sym_config(config_id,is_enabled,config_name,config_value,config_description) values (102,1,'default_pos_machine_group','POS_AGENT','Default auth group for pos machine user registrations');
# insert ignore into sym_config(config_id,is_enabled,config_name,config_value,config_description) values (103,1,'default_pos_till_group','POS_TILL_AGENT','Default auth group for pos till user registrations');
# insert ignore into sym_config(config_id,is_enabled,config_name,config_value,config_description) values (104,1,'default_ussd_auth_group','USSD_USER','Default auth group for ussd user registrations');
insert ignore into sym_config(config_id,is_enabled,config_name,config_value,config_description) values (105,1,'default_smart_phone_auth_group','MOBILE_USER','Default auth group for mobile user registrations');
insert ignore into sym_config(config_id,is_enabled,config_name,config_value,config_description) values (106,1,'default_wallet_group','DEFAULT_WALLET_GROUP','Default wallet group for user registrations');
#company settings
insert ignore into sym_config(config_id,is_enabled,config_name,config_value,config_description) values (120,1,'domain_name','t3ratech.com','Domain name for where the system is hosted');
insert ignore into sym_config(config_id,is_enabled,config_name,config_value,config_description) values (121,1,'contact_address','Plot 87, Spintex Road, Accra, Ghana','Company physical address');
insert ignore into sym_config(config_id,is_enabled,config_name,config_value,config_description) values (122,1,'support_email','t3ratech@gmail.com','Company support email');
insert ignore into sym_config(config_id,is_enabled,config_name,config_value,config_description) values (123,1,'support_phone','+233242279182','Company support phone number');
insert ignore into sym_config(config_id,is_enabled,config_name,config_value,config_description) values (124,1,'full_company_name','T3raTech Solutions','Full company name');
insert ignore into sym_config(config_id,is_enabled,config_name,config_value,config_description) values (125,1,'copyright_year','2017','Company copyright year (shown at the bottom of emails)');
#voucher settings
insert ignore into sym_config(config_id,is_enabled,config_name,config_value,config_description) values (140,1,'default_voucher_expiry_days','365','Time before prepaid vouchers are considered expired');
insert ignore into sym_config(config_id,is_enabled,config_name,config_value,config_description) values (141,1,'low_stock_threshold','50','Threshold before sending out low stock email');
#security settings
insert ignore into sym_config(config_id,is_enabled,config_name,config_value,config_description) values (160,1,'max_password_tries','5','Maximum password tries before blocking an account');
#web settings
insert ignore into sym_config(config_id,is_enabled,config_name,config_value,config_description) values (181,1,'default_reporting_days','7','Number of days to show in history by default');
#pos settings
insert ignore into sym_config(config_id,is_enabled,config_name,config_value,config_description) values (200,1,'falcon_pos_binary_name','ettl-falcon.jar','Name of the Falcon POS binary file');
insert ignore into sym_config(config_id,is_enabled,config_name,config_value,config_description) values (201,1,'falcon_pos_binary_location','/var/www/html/ettl_pos/ettl-falcon.jar','Location of the Falcon POS binary file');
insert ignore into sym_config(config_id,is_enabled,config_name,config_value,config_description) values (202,1,'falcon_pos_binary_version','1.0.4','Version of the Falcon POS binary file');
#integration settings : GloSeamless
insert ignore into sym_config(config_id,is_enabled,config_name,config_value,config_description) values (300,1,'glo_service_request_timeout','15000','Milliseconds before a request is considered timed out');
insert ignore into sym_config(config_id,is_enabled,config_name,config_value,config_description) values (301,1,'glo_service_client_id','EMPOWER','Client Id for the Glo Integration');
insert ignore into sym_config(config_id,is_enabled,config_name,config_value,config_description) values (302,1,'glo_service_user_id','t3ratechtst','User Id for the Glo Integration');
insert ignore into sym_config(config_id,is_enabled,config_name,config_value,config_description) values (303,1,'glo_service_password','2suZ0Dcv5iTLOPbqOyOC0A==','Password for the Glo Integration');
insert ignore into sym_config(config_id,is_enabled,config_name,config_value,config_description) values (304,1,'glo_service_low_balance_threshold','100','Low threshold amount before the system sends warning email');
#integration settings : MTN
insert ignore into sym_config(config_id,is_enabled,config_name,config_value,config_description) values (315,1,'mtn_gh_unique_id_file','/opt/integration/mtn/unique_id.txt','Last unique ID sent to MobiFin Service');
#integration settings : MTN Mobifin
insert ignore into sym_config(config_id,is_enabled,config_name,config_value,config_description) values (320,1,'mobifin_gh_terminal_number','50361738','MobiFin unique terminal identifier for reseller');
insert ignore into sym_config(config_id,is_enabled,config_name,config_value,config_description) values (321,1,'mobifin_gh_encryption_key','2205505435344133','MobiFin encryption key');
insert ignore into sym_config(config_id,is_enabled,config_name,config_value,config_description) values (322,1,'mobifin_gh_transaction_key','1157699898','MobiFin Transaction Key');
insert ignore into sym_config(config_id,is_enabled,config_name,config_value,config_description) values (323,1,'mobifin_gh_hsm_url','http://hsm.mpay.com.gh/hsmrest/hsmrest','URL for encryption & decryption');
insert ignore into sym_config(config_id,is_enabled,config_name,config_value,config_description) values (324,1,'mobifin_gh_distributor_url','http://resadmin.mpay.com.gh/distributormobilerest/distributormobilerest','URL for Basic Business functionality like Session Generation and balance retrieval');
insert ignore into sym_config(config_id,is_enabled,config_name,config_value,config_description) values (325,1,'mobifin_gh_switch_url','http://tswitch.mpay.com.gh/productrest/productrest','URL for Transaction functionality like Product retrieval, Service Delivery (Topup, Bill Payment, Voucher Sale, Wallet)');
insert ignore into sym_config(config_id,is_enabled,config_name,config_value,config_description) values (331,1,'mobifin_gh_use_local_encryption','true','Set whether to use local encryption or MobiFin HSM API encryption');
insert ignore into sym_config(config_id,is_enabled,config_name,config_value,config_description) values (335,1,'mobifin_gh_cash_in_check_interval','30000','Amount of time (milliseconds) between checking whether cash in transaction has completed');
insert ignore into sym_config(config_id,is_enabled,config_name,config_value,config_description) values (336,1,'mobifin_gh_cash_in_check_count','5','Number of times to check whether cash in transaction has completed');
#integration settings : MTN Mobile Mobile
insert ignore into sym_config(config_id,is_enabled,config_name,config_value,config_description) values (350,1,'mtnmomo_gh_request_to_pay_url','https://sandbox.momodeveloper.mtn.com/collection/v1_0/requesttopay','URL to send requests for payment from MTN mobile money');
insert ignore into sym_config(config_id,is_enabled,config_name,config_value,config_description) values (351,1,'mtnmomo_gh_api_token_url','https://sandbox.momodeveloper.mtn.com/collection/token/','URL to create API user & key');
insert ignore into sym_config(config_id,is_enabled,config_name,config_value,config_description) values (352,1,'mtnmomo_gh_account_balance_url','https://sandbox.momodeveloper.mtn.com/collection/v1_0/account/balance','URL to check account balance');
insert ignore into sym_config(config_id,is_enabled,config_name,config_value,config_description) values (353,1,'mtnmomo_gh_target_environment','sandbox','Environment to connect to');
insert ignore into sym_config(config_id,is_enabled,config_name,config_value,config_description) values (355,1,'mtnmomo_gh_payer_message','Load %amount% into %sys_name% wallet from MTN mobile money wallet','Message received by payer when loading funds');
insert ignore into sym_config(config_id,is_enabled,config_name,config_value,config_description) values (356,1,'mtnmomo_gh_payee_note','Received %amount% from %sender% for %sys_name% wallet from MTN mobile money wallet','Message received by recipient when funds are loaded from MTN mobile money');
insert ignore into sym_config(config_id,is_enabled,config_name,config_value,config_description) values (367,1,'mtnmomo_gh_collections_key','eb9f41b082a84d35b5d5e9f76376632c','API Key for MTN MoMo collections');
insert ignore into sym_config(config_id,is_enabled,config_name,config_value,config_description) values (368,1,'mtnmomo_gh_api_user','a0073598-508b-4999-9b49-c4f61774a829','API User ID for MTN MoMo');
insert ignore into sym_config(config_id,is_enabled,config_name,config_value,config_description) values (369,1,'mtnmomo_gh_api_key','0118e1615ef842cb99d3fa9aee3e2951','API Key for MTN MoMo collections');
insert ignore into sym_config(config_id,is_enabled,config_name,config_value,config_description) values (370,1,'mtnmomo_gh_cash_in_check_interval','30000','Amount of time (milliseconds) between checking whether cash in transaction has completed');
insert ignore into sym_config(config_id,is_enabled,config_name,config_value,config_description) values (371,1,'mtnmomo_gh_cash_in_check_count','5','Number of times to check whether cash in transaction has completed');
#google openid settings
# insert ignore into sym_config(config_id,is_enabled,config_name,config_value,config_description) values (400,1,'google_openid_base_url','','');
# insert ignore into sym_config(config_id,is_enabled,config_name,config_value,config_description) values (401,1,'google_openid_client_id','','');
# insert ignore into sym_config(config_id,is_enabled,config_name,config_value,config_description) values (402,1,'google_openid_api_key','','');
# insert ignore into sym_config(config_id,is_enabled,config_name,config_value,config_description) values (403,1,'google_openid_redirect_url','http://localhost:8081/login/oauth2/code/google','');

insert ignore into sym_country(country_id,name,is_enabled,iso_code_2,iso_code_3,dialing_code) values (1,'GHANA',1,'GH','GHA',233);
insert ignore into sym_country(country_id,name,is_enabled,iso_code_2,iso_code_3,dialing_code) values (2,'ZIMBABWE',0,'ZW','ZWE',263);
insert ignore into sym_country(country_id,name,is_enabled,iso_code_2,iso_code_3,dialing_code) values (3,'SOUTH AFRICA',0,'ZA','ZAF',27);

# system channel for events initiated by the system
insert ignore into sym_channel(channel_id,name,is_enabled,is_pin_based) values (0,'SYSTEM',1,0);
update sym_channel set channel_id = 0 where name = 'SYSTEM';

insert ignore into sym_channel(channel_id,name,is_enabled,is_pin_based) values (1,'API',1,0);
insert ignore into sym_channel(channel_id,name,is_enabled,is_pin_based) values (2,'DESKTOP',1,0);
insert ignore into sym_channel(channel_id,name,is_enabled,is_pin_based) values (3,'WEB',1,0);
insert ignore into sym_channel(channel_id,name,is_enabled,is_pin_based) values (4,'POS_MACHINE',1,1);
insert ignore into sym_channel(channel_id,name,is_enabled,is_pin_based) values (5,'POS_TILL',1,1);
insert ignore into sym_channel(channel_id,name,is_enabled,is_pin_based) values (6,'USSD',1,1);
insert ignore into sym_channel(channel_id,name,is_enabled,is_pin_based) values (7,'SMART_PHONE',1,1);

insert ignore into sym_currency(currency_id,currency_name,iso_4217_code,iso_4217_num) values (1,'United States Dollar','USD','840');
insert ignore into sym_currency(currency_id,currency_name,iso_4217_code,iso_4217_num) values (2,'South African Rand','ZAR','710');
insert ignore into sym_currency(currency_id,currency_name,iso_4217_code,iso_4217_num) values (3,'Botswana Pula','BWP','072');
insert ignore into sym_currency(currency_id,currency_name,iso_4217_code,iso_4217_num) values (4,'Pound Sterling','GBP','826');
insert ignore into sym_currency(currency_id,currency_name,iso_4217_code,iso_4217_num) values (5,'Euro','EUR','978');
insert ignore into sym_currency(currency_id,currency_name,iso_4217_code,iso_4217_num) values (6,'Zambian Kwacha','ZMW','967');

insert ignore into sym_event_type(event_type_id,name,is_enabled) values (100, 'CONFIG_UPDATE',1);
insert ignore into sym_event_type(event_type_id,name,is_enabled) values (101, 'RESEND_SMS',1);
insert ignore into sym_event_type(event_type_id,name,is_enabled) values (1000,'USER_REGISTRATION',1);
insert ignore into sym_event_type(event_type_id,name,is_enabled) values (1001,'USER_ASSIGN_CHANNEL',1);
insert ignore into sym_event_type(event_type_id,name,is_enabled) values (1002,'USER_CREATE',1);
insert ignore into sym_event_type(event_type_id,name,is_enabled) values (1003,'USER_LOGIN',1);
insert ignore into sym_event_type(event_type_id,name,is_enabled) values (1004,'USER_LOGOUT',1);
insert ignore into sym_event_type(event_type_id,name,is_enabled) values (1005,'USER_UPDATE',1);
insert ignore into sym_event_type(event_type_id,name,is_enabled) values (1006,'USER_PASSWORD_UPDATE',1);
insert ignore into sym_event_type(event_type_id,name,is_enabled) values (1007,'USER_PASSWORD_RESET',1);
insert ignore into sym_event_type(event_type_id,name,is_enabled) values (1008,'USER_PIN_RESET',1);

insert ignore into sym_event_type(event_type_id,name,is_enabled) values (1100,'WALLET_LOAD',1);
insert ignore into sym_event_type(event_type_id,name,is_enabled) values (1101,'WALLET_CASHIN',1);
insert ignore into sym_event_type(event_type_id,name,is_enabled) values (1102,'WALLET_CASHOUT',1);
insert ignore into sym_event_type(event_type_id,name,is_enabled) values (1103,'WALLET_CASHIN_RESPONSE_CHECK',1);
insert ignore into sym_event_type(event_type_id,name,is_enabled) values (1110,'WALLET_UPDATE',1);
insert ignore into sym_event_type(event_type_id,name,is_enabled) values (1115,'WALLET_GET_CASHOUT_ACCOUNTS',1);
insert ignore into sym_event_type(event_type_id,name,is_enabled) values (1116,'WALLET_ADD_CASHOUT_ACCOUNT',1);
insert ignore into sym_event_type(event_type_id,name,is_enabled) values (1117,'WALLET_DISABLE_CASHOUT_ACCOUNT',1);
insert ignore into sym_event_type(event_type_id,name,is_enabled) values (1120,'WALLET_GROUP_CREATE',1);
insert ignore into sym_event_type(event_type_id,name,is_enabled) values (1121,'WALLET_GROUP_UPDATE',1);
insert ignore into sym_event_type(event_type_id,name,is_enabled) values (1122,'WALLET_GROUP_VOUCHER_DISCOUNT_UPDATE',1);
insert ignore into sym_event_type(event_type_id,name,is_enabled) values (1123,'WALLET_GROUP_TRANSFER_CHARGE_UPDATE',1);
insert ignore into sym_event_type(event_type_id,name,is_enabled) values (1130,'WALLET_SWIPE_IN',1);
insert ignore into sym_event_type(event_type_id,name,is_enabled) values (1140,'WALLET_TRANSFER',1);
insert ignore into sym_event_type(event_type_id,name,is_enabled) values (1150,'WALLET_HISTORY',1);

insert ignore into sym_event_type (event_type_id,name,is_enabled) values (1200,'VOUCHER_IMPORT',1);
insert ignore into sym_event_type (event_type_id,name,is_enabled) values (1201,'VOUCHER_CREATE',1);
insert ignore into sym_event_type (event_type_id,name,is_enabled) values (1202,'VOUCHER_PURCHASE',1);
insert ignore into sym_event_type (event_type_id,name,is_enabled) values (1203,'VOUCHER_UPDATE',1);
insert ignore into sym_event_type (event_type_id,name,is_enabled) values (1204,'VOUCHER_TYPE_CREATE',1);
insert ignore into sym_event_type (event_type_id,name,is_enabled) values (1205,'VOUCHER_TYPE_UPDATE',1);
insert ignore into sym_event_type (event_type_id,name,is_enabled) values (1206,'VOUCHER_PURCHASE_QUERY',1);
insert ignore into sym_event_type (event_type_id,name,is_enabled) values (1220,'VOUCHER_PROVIDER_CREATE',1);
insert ignore into sym_event_type (event_type_id,name,is_enabled) values (1221,'VOUCHER_PROVIDER_UPDATE',1);
insert ignore into sym_event_type (event_type_id,name,is_enabled) values (1222,'VOUCHER_PROVIDER_BALANCE_LOAD',1);
insert ignore into sym_event_type (event_type_id,name,is_enabled) values (1223,'VOUCHER_PROVIDER_BALANCE_CHECK',1);
insert ignore into sym_event_type (event_type_id,name,is_enabled) values (1230,'SERVICE_PROVIDER_CREATE',1);
insert ignore into sym_event_type (event_type_id,name,is_enabled) values (1231,'SERVICE_PROVIDER_UPDATE',1);

insert ignore into sym_event_type(event_type_id,name,is_enabled) values (1300,'DEVICE_POS_MACHINE_UPDATE',1);
insert ignore into sym_event_type(event_type_id,name,is_enabled) values (1301,'DEVICE_PHONE_UPDATE',1);

insert ignore into sym_event_type(event_type_id,name,is_enabled) values (1400,'EMAIL_NOTIFICATION',1);
insert ignore into sym_event_type(event_type_id,name,is_enabled) values (1401,'SMS_NOTIFICATION',1);
insert ignore into sym_event_type(event_type_id,name,is_enabled) values (1402,'WHATSAPP_NOTIFICATION',1);
insert ignore into sym_event_type(event_type_id,name,is_enabled) values (1403,'FACEBOOK_NOTIFICATION',1);
insert ignore into sym_event_type(event_type_id,name,is_enabled) values (1410,'NOTIFICATION_HISTORY',1);

insert ignore into sym_financial_institution_type(institution_type_id,name,is_enabled) values (1,'BANK',1);
insert ignore into sym_financial_institution_type(institution_type_id,name,is_enabled) values (2,'MOBILE_WALLET',1);
insert ignore into sym_financial_institution_type(institution_type_id,name,is_enabled) values (3,'ONLINE_BANK',1);
insert ignore into sym_financial_institution_type(institution_type_id,name,is_enabled) values (4,'CRYPTO_CURRENCY',1);

insert ignore into sym_financial_institution(institution_id,institution_name,short_name,integration_id,institution_type_id,country_id,current_balance,service_id,product_id) values (1,'MTN Mobile Money','MTN MoMo','MTNMoMo',2,1,0.0,64,'MTNMOMOCashout');

insert ignore into sym_language(name,is_enabled) values ('ENGLISH',1);

insert ignore into sym_response_code(response_code_id,name,is_enabled,response_message) values(1, 'GENERAL_ERROR',1,'A general error occurred');
insert ignore into sym_response_code(response_code_id,name,is_enabled,response_message) values(-1,'CONFIGURATION_INVALID',1,'Specified configuration is not valid');
insert ignore into sym_response_code(response_code_id,name,is_enabled,response_message) values(15,'DATA_NOT_FOUND',1,'Data does not exist');
insert ignore into sym_response_code(response_code_id,name,is_enabled,response_message) values(16,'NOT_SUPPORTED',1,'Not supported');

insert ignore into sym_response_code(response_code_id,name,is_enabled,response_message) values(2,'INPUT_INCOMPLETE_REQUEST',1,'Incomplete request specified');
insert ignore into sym_response_code(response_code_id,name,is_enabled,response_message) values(3,'INPUT_INVALID_REQUEST',1,'Invalid request specified');
insert ignore into sym_response_code(response_code_id,name,is_enabled,response_message) values(4,'INPUT_INVALID_EMAIL',1,'Email provided was not valid');
insert ignore into sym_response_code(response_code_id,name,is_enabled,response_message) values(5,'INPUT_INVALID_MSISDN',1,'Phone number provided was not valid');
insert ignore into sym_response_code(response_code_id,name,is_enabled,response_message) values(6,'INPUT_INVALID_FIRST_NAME',1,'First name provided was not valid');
insert ignore into sym_response_code(response_code_id,name,is_enabled,response_message) values(7,'INPUT_INVALID_LAST_NAME',1,'Last name provided was not valid');
insert ignore into sym_response_code(response_code_id,name,is_enabled,response_message) values(8,'INPUT_INVALID_USERNAME',1,'Username provided was not valid');
insert ignore into sym_response_code(response_code_id,name,is_enabled,response_message) values(9,'INPUT_INVALID_PASSWORD',1,'Password provided was not valid');
insert ignore into sym_response_code(response_code_id,name,is_enabled,response_message) values(10,'INPUT_INVALID_NAME',1,'Name provided was not valid');
insert ignore into sym_response_code(response_code_id,name,is_enabled,response_message) values(11,'INPUT_INVALID_AMOUNT',1,'Invalid amount specified');
insert ignore into sym_response_code(response_code_id,name,is_enabled,response_message) values(12,'INPUT_INVALID_WALLET',1,'Invalid wallet specified');
insert ignore into sym_response_code(response_code_id,name,is_enabled,response_message) values(13,'INPUT_INVALID_CASHIER',1,'Invalid cashier name specified');

insert ignore into sym_response_code(response_code_id,name,is_enabled,response_message) values(20,'AUTH_INSUFFICIENT_PRIVILEGES',1,'Insufficient privileges for current operation');
insert ignore into sym_response_code(response_code_id,name,is_enabled,response_message) values(21,'AUTH_AUTHENTICATION_FAILED',1,'Authentication failed');
insert ignore into sym_response_code(response_code_id,name,is_enabled,response_message) values(22,'AUTH_INCORRECT_PASSWORD',1,'Password is incorrect');
insert ignore into sym_response_code(response_code_id,name,is_enabled,response_message) values(23,'AUTH_NON_EXISTENT',1,'Account does not exist');

insert ignore into sym_response_code(response_code_id,name,is_enabled,response_message) values(30,'ACC_ACTIVE',1,'Account is active');
insert ignore into sym_response_code(response_code_id,name,is_enabled,response_message) values(31,'ACC_INACTIVE',1,'Account is inactive');
insert ignore into sym_response_code(response_code_id,name,is_enabled,response_message) values(32,'ACC_SUSPENDED',1,'Account has been suspended');
insert ignore into sym_response_code(response_code_id,name,is_enabled,response_message) values(33,'ACC_CLOSED',1,'Account has been closed');
insert ignore into sym_response_code(response_code_id,name,is_enabled,response_message) values(34,'ACC_PASSWORD_TRIES_EXCEEDED',1,'Password tries exceeded');
insert ignore into sym_response_code(response_code_id,name,is_enabled,response_message) values(35,'ACC_PASSWORD_EXPIRED',1,'Password expired');

insert ignore into sym_response_code(response_code_id,name,is_enabled,response_message) values(40,'CONNECTION_FAILED',1,'Connection failed');
insert ignore into sym_response_code(response_code_id,name,is_enabled,response_message) values(41,'UNKNOWN_HOST',1,'Unknown host');
insert ignore into sym_response_code(response_code_id,name,is_enabled,response_message) values(42,'CONNECTION_REFUSED',1,'Connection Refused');
insert ignore into sym_response_code(response_code_id,name,is_enabled,response_message) values(43,'TIMEOUT',1,'Timeout elapsed before transaction completion');

insert ignore into sym_response_code(response_code_id,name,is_enabled,response_message) values(51,'INSUFFICIENT_FUNDS',1,'Insufficient funds');

insert ignore into sym_response_code(response_code_id,name,is_enabled,response_message) values(60,'INSUFFICIENT_STOCK',1,'Insufficient stock');

insert ignore into sym_response_code(response_code_id,name,is_enabled,response_message) values(80,'EXISTING_DATA_FOUND',1,'Existing data found');

insert ignore into sym_response_code(response_code_id,name,is_enabled,response_message) values(90,'PENDING',1,'Pending');

insert ignore into sym_response_code(response_code_id,name,is_enabled,response_message) values(351,'REGISTRATION_FAILED',1,'Registration Failed');
insert ignore into sym_response_code(response_code_id,name,is_enabled,response_message) values(352,'PREVIOUS_USERNAME_FOUND',1,'Username has been previously registered');
insert ignore into sym_response_code(response_code_id,name,is_enabled,response_message) values(353,'PREVIOUS_MSISDN_FOUND',1,'Mobile number has been previously registered');
insert ignore into sym_response_code(response_code_id,name,is_enabled,response_message) values(354,'PREVIOUS_EMAIL_FOUND',1,'Email has been previously registered');
insert ignore into sym_response_code(response_code_id,name,is_enabled,response_message) values(355,'PREVIOUS_REGISTRATION_FOUND',1,'Previous registration found');

insert ignore into sym_response_code(response_code_id,name,is_enabled,response_message) values(0, 'SUCCESS',1,'Successful');
update sym_response_code set response_code_id = 0 where name = 'SUCCESS';

# GloSeamless response code
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('GloSeamless',1,1,'Pending');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('GloSeamless',2,1,'Report Not Ready');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('GloSeamless',3,0,'Voucher Delivered');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('GloSeamless',4,0,'Voucher Sold');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('GloSeamless',10,1,'Error occurred with voucher provider');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('GloSeamless',11,11,'Rejected Amount');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('GloSeamless',12,1,'Rejected Payment');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('GloSeamless',13,1,'Rejected Topup');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('GloSeamless',20,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('GloSeamless',21,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('GloSeamless',22,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('GloSeamless',23,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('GloSeamless',29,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('GloSeamless',30,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('GloSeamless',31,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('GloSeamless',32,5,'Invalid Glo phone number specified');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('GloSeamless',33,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('GloSeamless',34,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('GloSeamless',35,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('GloSeamless',36,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('GloSeamless',37,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('GloSeamless',38,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('GloSeamless',39,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('GloSeamless',40,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('GloSeamless',41,16,'Invalid product');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('GloSeamless',42,2,'Invalid receiver account type');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('GloSeamless',43,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('GloSeamless',44,1,'Invalid topup account type');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('GloSeamless',45,5,'Receiver account not found');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('GloSeamless',46,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('GloSeamless',47,1,'Topup account not found');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('GloSeamless',48,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('GloSeamless',49,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('GloSeamless',50,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('GloSeamless',51,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('GloSeamless',52,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('GloSeamless',53,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('GloSeamless',54,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('GloSeamless',55,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('GloSeamless',56,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('GloSeamless',57,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('GloSeamless',60,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('GloSeamless',61,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('GloSeamless',62,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('GloSeamless',63,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('GloSeamless',90,1,'Error occurred with voucher provider');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('GloSeamless',91,16,'Unsupported operation');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('GloSeamless',92,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('GloSeamless',93,1,'Voucher provider system busy');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('GloSeamless',94,1,'Voucher provider service unavailable');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('GloSeamless',95,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('GloSeamless',96,1,'Voucher provider unavailable');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('GloSeamless',97,60,'Product out of stock');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('GloSeamless',98,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('GloSeamless',100,1,'Cancelled');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('GloSeamless',101,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('GloSeamless',102,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('GloSeamless',103,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('GloSeamless',104,1,'Service unavailable. Try again later');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('GloSeamless',105,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('GloSeamless',106,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('GloSeamless',107,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('GloSeamless',108,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('GloSeamless',109,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('GloSeamless',901,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('GloSeamless',530,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('GloSeamless',931,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('GloSeamless',933,15,'Transaction not found');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('GloSeamless',2016,80,'Transaction already completed');

#MPay MobiFin Response Codes
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',041,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',042,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',043,3,'Invalid request specified');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',044,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',045,5,'Phone number provided was not valid');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',046,80,'Duplicate transaction');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',047,11,'Denomination not supported by this provider');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',049,43,'Timeout elapsed before transaction completion');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',091,40,'Connection Failed');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',092,43,'Timeout elapsed before transaction completion');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',099,90,'Transaction response pending');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',100,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',101,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',102,43,'Invalid request specified');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',103,80,'Duplicate transaction');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',104,90,'Transaction processing');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',105,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',106,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',107,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',108,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',109,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',201,3,'Product is not supported');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',202,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',203,11,'Invalid amount specified');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',204,3,'Product is inactive');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',205,3,'Product is inactive');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',206,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',207,3,'Product not available');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',208,80,'Duplicate transaction');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',209,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',210,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',211,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',212,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',213,3,'Product is unavailable');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',214,3,'Product is inactive');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',215,3,'Product is inactive');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',216,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',251,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',252,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',253,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',254,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',255,11,'Invalid amount specified');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',256,3,'Product is inactive');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',257,3,'Product is inactive');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',258,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',258,80,'Duplicate transaction');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',260,3,'Product is not available');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',261,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',262,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',263,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',264,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',265,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',266,3,'Product is not available');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',267,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',268,11,'Quantity is Invalid');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',301,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',302,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',303,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',304,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',305,11,'Invalid amount specified');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',306,3,'Product is inactive');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',307,3,'Product is inactive');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',308,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',309,80,'Duplicate transaction');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',310,3,'Product is not available');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',311,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',312,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',313,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',314,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',315,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',316,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',317,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',601,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',602,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',603,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',604,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',605,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',606,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',607,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',608,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',609,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',610,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',611,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',612,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',613,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',614,11,'Invalid amount specified');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',615,3,'Product is inactive');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',616,3,'Product is inactive');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',617,90,'Transaction processing');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',620,80,'Duplicate transaction');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',621,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',622,3,'Product is not available');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',623,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',624,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',625,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',801,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',802,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',803,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',804,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',805,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',806,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',807,11,'Invalid amount specified');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',808,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',809,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',810,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',811,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',812,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',813,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',814,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',815,3,'Product is not available');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',816,3,'Product is not available');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',817,90,'Transaction Processing');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',818,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',819,80,'Duplicate transaction');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',820,80,'Duplicate transaction');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',821,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',822,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',823,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',824,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MobiFin',825,3,'Product is not available');
# MTN MoMo Response Codes
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MTNMoMo',302,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MTNMoMo',400,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MTNMoMo',401,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MTNMoMo',404,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MTNMoMo',409,80,'Duplicate Transaction');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MTNMoMo',500,1,'A general error occurred');
insert ignore into sym_response_mapping(system_id,response_code_id,mapped_response_code_id,mapped_response_message) values ('MTNMoMo',503,1,'A general error occurred');

insert ignore into sym_auth_group(auth_group_id, name, is_enabled) values (1, 'SUPER_USER',1);
insert ignore into sym_auth_group(auth_group_id, name, is_enabled) values (2, 'WEB_ADMIN',1);
insert ignore into sym_auth_group(auth_group_id, name, is_enabled) values (3, 'WEB_CLERK',1);
insert ignore into sym_auth_group(auth_group_id, name, is_enabled) values (4, 'WEB_AGENT',1);
insert ignore into sym_auth_group(auth_group_id, name, is_enabled) values (5, 'POS_AGENT',1);
insert ignore into sym_auth_group(auth_group_id, name, is_enabled) values (6, 'POS_ADMIN',1);
insert ignore into sym_auth_group(auth_group_id, name, is_enabled) values (7, 'MOBILE_USER',1);
insert ignore into sym_auth_group(auth_group_id, name, is_enabled) values (8, 'MOBILE_ADMIN',1);

# insert ignore into sym_role(name, is_enabled) values ('ROLE_NOTIFICATION_SEND', 1);
# insert ignore into sym_role(name, is_enabled) values ('ROLE_NOTIFICATION_HISTORY', 1);

insert ignore into sym_role(name, is_enabled) values ('ROLE_WEB_MANAGE_EVD', 1);
insert ignore into sym_role(name, is_enabled) values ('ROLE_WEB_MANAGE_STOCK', 1);
insert ignore into sym_role(name, is_enabled) values ('ROLE_WEB_MANAGE_PROVIDERS', 1);
insert ignore into sym_role(name, is_enabled) values ('ROLE_WEB_MANAGE_VOUCHERS', 1);
insert ignore into sym_role(name, is_enabled) values ('ROLE_WEB_MANAGE_SYSTEM', 1);
insert ignore into sym_role(name, is_enabled) values ('ROLE_WEB_MANAGE_WALLETS', 1);
insert ignore into sym_role(name, is_enabled) values ('ROLE_WEB_MANAGE_USERS', 1);
insert ignore into sym_role(name, is_enabled) values ('ROLE_WEB_ADVANCED_MANAGE_USERS', 1);
insert ignore into sym_role(name, is_enabled) values ('ROLE_WEB_VIEW_REPORTS', 1);
insert ignore into sym_role(name, is_enabled) values ('ROLE_WEB_VIEW_SYSTEM_REPORTS', 1);
insert ignore into sym_role(name, is_enabled) values ('ROLE_WEB_VIEW_PAYMENT_REPORTS', 1);
insert ignore into sym_role(name, is_enabled) values ('ROLE_WEB_VIEW_TRANSACTION_REPORTS', 1);
insert ignore into sym_role(name, is_enabled) values ('ROLE_WEB_VIEW_SMS_REPORTS', 1);
insert ignore into sym_role(name, is_enabled) values ('ROLE_WEB_VIEW_AUTHENTICATION_REPORTS', 1);
insert ignore into sym_role(name, is_enabled) values ('ROLE_WEB_VIEW_SINGLE_REPORTS', 1);
insert ignore into sym_role(name, is_enabled) values ('ROLE_WEB_VIEW_SINGLE_AUTHENTICATION_REPORTS', 1);
insert ignore into sym_role(name, is_enabled) values ('ROLE_WEB_VIEW_SINGLE_PAYMENT_REPORTS', 1);
insert ignore into sym_role(name, is_enabled) values ('ROLE_WEB_VIEW_SINGLE_TRANSACTION_REPORTS', 1);

insert ignore into sym_role(name, is_enabled) values ('ROLE_WEB_MANAGE_SETTINGS', 1);

insert ignore into sym_role(name, is_enabled) values ('ROLE_POS_MANAGE_SETTINGS', 1);

insert ignore into sym_role(name, is_enabled) values ('ROLE_MOBILE_MANAGE_SETTINGS', 1);

/* Insert all roles for SUPER_USER */
insert ignore into sym_auth_group_role(auth_group_id,role_id,name,is_enabled) select sg.auth_group_id,sr.role_id,CONCAT(sg.name,'_',sr.name),1 from sym_auth_group sg,sym_role sr where sg.name = 'SUPER_USER' and sr.name LIKE '%';

/* Insert roles for COMPANY_SYSTEM_ADMIN */
insert ignore into sym_auth_group_role(auth_group_id,role_id,name,is_enabled) select sg.auth_group_id,sr.role_id,CONCAT(sg.name,'_',sr.name),1 from sym_auth_group sg,sym_role sr where sg.name = 'WEB_ADMIN' and sr.name LIKE 'ROLE_WEB_%';

/* Insert roles for COMPANY_SYSTEM_CLERK */
insert ignore into sym_auth_group_role(auth_group_id,role_id,name,is_enabled) select sg.auth_group_id,sr.role_id,CONCAT(sg.name,'_',sr.name),1 from sym_auth_group sg,sym_role sr where sg.name = 'WEB_CLERK' and (sr.name IN ('ROLE_WEB_MANAGE_EVD', 'ROLE_WEB_MANAGE_STOCK', 'ROLE_WEB_MANAGE_WALLETS', 'ROLE_WEB_MANAGE_USERS', 'ROLE_WEB_MANAGE_SETTINGS') or sr.name LIKE 'ROLE_WEB_VIEW_%');

/* Insert roles for COMPANY_SYSTEM_AGENT */
insert ignore into sym_auth_group_role(auth_group_id,role_id,name,is_enabled) select sg.auth_group_id,sr.role_id,CONCAT(sg.name,'_',sr.name),1 from sym_auth_group sg,sym_role sr where sg.name = 'WEB_AGENT' and (sr.name LIKE 'ROLE_WEB_VIEW_SINGLE_%' or sr.name = 'ROLE_WEB_MANAGE_SETTINGS');

/* Insert roles for POS_ADMIN */
insert ignore into sym_auth_group_role(auth_group_id,role_id,name,is_enabled) select sg.auth_group_id,sr.role_id,CONCAT(sg.name,'_',sr.name),1 from sym_auth_group sg,sym_role sr where sg.name = 'POS_ADMIN' and sr.name LIKE ('ROLE_POS_%');

/* Insert roles for MOBILE_ADMIN */
insert ignore into sym_auth_group_role(auth_group_id,role_id,name,is_enabled) select sg.auth_group_id,sr.role_id,CONCAT(sg.name,'_',sr.name),1 from sym_auth_group sg,sym_role sr where sg.name = 'MOBILE_ADMIN' and sr.name LIKE ('ROLE_MOBILE_%');

insert ignore into sym_voucher_type(voucher_type_id,is_enabled,name) values (1, 1, 'AIRTIME');
insert ignore into sym_voucher_type(voucher_type_id,is_enabled,name) values (2, 0, 'INTERNET');
insert ignore into sym_voucher_type(voucher_type_id,is_enabled,name) values (3, 0, 'SATELLITE_TV');
insert ignore into sym_voucher_type(voucher_type_id,is_enabled,name) values (4, 1, 'SMS_BUNDLE');
insert ignore into sym_voucher_type(voucher_type_id,is_enabled,name) values (5, 1, 'DATA_BUNDLE');
insert ignore into sym_voucher_type(voucher_type_id,is_enabled,name) values (6, 0, 'ELECTRICITY');
insert ignore into sym_voucher_type(voucher_type_id,is_enabled,name) values (7, 0, 'DIGITAL_TV');

insert ignore into sym_voucher_status(voucher_status_id,is_enabled,name) values (1, 1, 'NEW');
insert ignore into sym_voucher_status(voucher_status_id,is_enabled,name) values (2, 1, 'SOLD');
insert ignore into sym_voucher_status(voucher_status_id,is_enabled,name) values (3, 1, 'EXPIRED');

insert ignore into sym_distribution_channel(distribution_channel_id,is_enabled,name) values (1, 1, 'RECEIPT');
insert ignore into sym_distribution_channel(distribution_channel_id,is_enabled,name) values (2, 1, 'SMS');
insert ignore into sym_distribution_channel(distribution_channel_id,is_enabled,name) values (3, 1, 'NONE');

insert ignore into sym_deposit_type(deposit_type_id,is_enabled,name) values (1, 1, 'CASH');
insert ignore into sym_deposit_type(deposit_type_id,is_enabled,name) values (2, 1, 'BANK_DEPOSIT');
insert ignore into sym_deposit_type(deposit_type_id,is_enabled,name) values (3, 1, 'CREDIT_CARD');
insert ignore into sym_deposit_type(deposit_type_id,is_enabled,name) values (4, 1, 'DEBIT_CARD');
insert ignore into sym_deposit_type(deposit_type_id,is_enabled,name) values (5, 1, 'BANK_TRANSFER');
insert ignore into sym_deposit_type(deposit_type_id,is_enabled,name) values (6, 1, 'MOBILE_MONEY');

insert ignore into sym_notification_type(notification_type_id,is_enabled,name) values (1, 1, 'EMAIL');
insert ignore into sym_notification_type(notification_type_id,is_enabled,name) values (2, 1, 'SMS');
insert ignore into sym_notification_type(notification_type_id,is_enabled,name) values (3, 0, 'WHATSAPP');
insert ignore into sym_notification_type(notification_type_id,is_enabled,name) values (4, 0, 'FACEBOOK');

insert ignore into sym_notification_status(notification_status_id,is_enabled,name) values (1, 1, 'SENT');
insert ignore into sym_notification_status(notification_status_id,is_enabled,name) values (2, 1, 'FAILED');
insert ignore into sym_notification_status(notification_status_id,is_enabled,name) values (3, 1, 'QUEUED');
insert ignore into sym_notification_status(notification_status_id,is_enabled,name) values (4, 1, 'SENDING');
insert ignore into sym_notification_status(notification_status_id,is_enabled,name) values (5, 1, 'DELIVERED');
insert ignore into sym_notification_status(notification_status_id,is_enabled,name) values (6, 1, 'UNDELIVERED');
