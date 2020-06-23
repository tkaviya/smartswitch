# create system user, mainly for logging purposes
insert ignore into sym_company(company_id,company_name,address_line_1,address_line_2,address_city,address_country_id,phone1,phone2) values (0,'System','System','System','System',1,'0','0');
update sym_company set company_id = 0 where company_name = 'System';
alter table sym_company auto_increment = 1;
insert ignore into sym_user(first_name,last_name,username,email,msisdn,msisdn2,salt,user_status_id,country_id,language_id,password,password_tries,pin,pin_tries) values ('System','System','system','empowerttl@gmail.com','0','0','system',30,2,1,'system',0,'system',0);
update sym_user set sym_user_id = 0 where username = 'system';
alter table sym_user auto_increment = 1;
insert ignore into sym_auth_user (sym_user_id,channel_id,auth_group_id,device_id,registration_date,last_auth_date,last_login_date) SELECT su.sym_user_id,0,1,null,sysdate(),NULL,NULL FROM sym_user su WHERE su.username = 'system';
update sym_auth_user set auth_user_id = 0 where sym_user_id = 0;
alter table sym_auth_user auto_increment = 1;
insert ignore into sym_wallet (wallet_id, current_balance, company_id, wallet_admin_user_id, wallet_group_id) values (0, '0.00', 0, 0, 1);
update sym_wallet set wallet_id = 0 where wallet_admin_user_id = 0;
update sym_user set wallet_id = 0 where sym_user_id = 0;
alter table sym_wallet auto_increment = 1;

# create main super user
insert ignore into sym_company(company_id,company_name,address_line_1,address_line_2,address_city,address_country_id,phone1,phone2) values (1,'T3raTech','4 Janeen Close','Groombridge, Mt. Pleasant','Harare',2,'263785107830','27627938765');
insert ignore into sym_user(first_name,last_name,username,email,msisdn,msisdn2,salt,user_status_id,country_id,language_id,password,password_tries,pin,pin_tries) values ('Tsungai','Kaviya','admin','tsungai.kaviya@gmail.com','263785107830','27627938765','2X54xH6bq2hMC9x7',30,2,1,'57c5ab9d0bcf7648fd7195da3e087ea82fc750f88070dd8b70f4912235933',0,'b625884f7f5a7d524ea69d4039df95425227ef1cadaaed1b202e135c6ebed144',0);
insert ignore into sym_auth_user (sym_user_id,channel_id,auth_group_id,device_id,registration_date,last_auth_date,last_login_date) SELECT su.sym_user_id,2,1,null,sysdate(),NULL,NULL FROM sym_user su WHERE su.username = 'admin';
insert ignore into sym_auth_user (sym_user_id,channel_id,auth_group_id,device_id,registration_date,last_auth_date,last_login_date) SELECT su.sym_user_id,6,1,null,sysdate(),NULL,NULL FROM sym_user su WHERE su.username = 'admin';
insert ignore into sym_wallet (wallet_id, current_balance, company_id, wallet_admin_user_id, wallet_group_id) values (1, '10000.00', 1, 1, 1);
update sym_user set wallet_id = 1 where sym_user_id = 1;