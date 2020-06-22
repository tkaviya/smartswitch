package net.symbiosis.core_lib.enumeration;

/***************************************************************************
 * Created:     3/5/2018                                                  *
 * Author:      Tsungai Kaviya                                             *
 * Contact:     kaviyat@payserv.co.zw                                      *
 **************************************************************************/

public class DBConfigVars {

    //SYSTEM SETTINGS
    public static final String CONFIG_SYSTEM_NAME = "system_name";
    public static final String CONFIG_SYSTEM_USER_ID = "system_user_id";
    public static final String CONFIG_BASE_PATH = "system_base_path";
    public static final String CONFIG_CONFIG_UPDATE_DURATION = "config_update_duration";
    public static final String CONFIG_THREAD_CORE_POOL_SIZE = "thread_core_pool_size";
    public static final String CONFIG_THREAD_MAX_POOL_SIZE = "thread_max_pool_size";
    public static final String CONFIG_MUTEX_LOCK_WAIT_TIME = "mutex_lock_wait_time";
    public static final String CONFIG_MUTEX_LOCK_WAIT_INTERVAL = "mutex_lock_wait_interval";
    public static final String CONFIG_API_SYNC_TIMEOUT = "api_sync_timeout";
    public static final String CONFIG_API_ASYNC_TIMEOUT = "api_async_timeout";

    //INTEGRATION SETTINGS : VONAGE NEXMO
    public static final String CONFIG_VONAGE_API_KEY = "vonage_api_key";
    public static final String CONFIG_VONAGE_API_SECRET = "vonage_api_secret";
    public static final String CONFIG_VONAGE_SMS_FROM = "vonage_sms_from";
    public static final String CONFIG_VONAGE_SMS_MAX_RETRIES = "vonage_sms_max_retries";
    public static final String CONFIG_VONAGE_SMS_RETRY_WAIT_TIME = "vonage_sms_retry_wait_time";

    //SMS SETTINGS
    public static final String CONFIG_SMS_API_URL = "sms_api_url";
    public static final String CONFIG_SMS_ENABLE = "sms_enable";
    public static final String CONFIG_SMS_MAX_LENGTH = "sms_max_length";

    //SMS MESSAGE SETTINGS
    public static final String CONFIG_SMS_MOBILE_REGISTRATION = "sms_mobile_registration";
    public static final String CONFIG_SMS_POS_REGISTRATION = "sms_pos_registration";
    public static final String CONFIG_SMS_WALLET_LOAD = "sms_wallet_load";
    public static final String CONFIG_SMS_PINBASED_VOUCHER_PURCHASE = "sms_pinbased_voucher_purchase";
    public static final String CONFIG_SMS_PINBASED_VOUCHER_RECEIVED = "sms_pinbased_voucher_received";
    public static final String CONFIG_SMS_PINLESS_VOUCHER_PURCHASE = "sms_pinless_voucher_purchase";
    public static final String CONFIG_SMS_PINLESS_VOUCHER_RECEIVED = "sms_pinless_voucher_received";
    public static final String CONFIG_SMS_WALLET_TRANSFER_SENT = "sms_wallet_tranfer_sent";
    public static final String CONFIG_SMS_WALLET_TRANSFER_RECEIVED = "sms_wallet_tranfer_receieved";
    public static final String CONFIG_SMS_PIN_RESET = "sms_pin_reset";
    public static final String CONFIG_SMS_PASSWORD_RESET = "sms_password_reset";

    //MAIL SETTINGS
    public static final String CONFIG_EMAIL_ENABLE = "email_enable";
    public static final String CONFIG_EMAIL_PROTOCOL = "email_protocol";
    public static final String CONFIG_EMAIL_HOST = "email_host";
    public static final String CONFIG_EMAIL_PORT = "email_port";
    public static final String CONFIG_EMAIL_USERNAME = "email_username";
    public static final String CONFIG_EMAIL_PASSWORD = "email_password";
    public static final String CONFIG_EMAIL_SMTP_AUTH = "email_smtp_auth";
    public static final String CONFIG_EMAIL_SMTP_STARTTLS_ENABLE = "email_smtp_starttls_enable";
    public static final String CONFIG_EMAIL_SMTP_DEBUG = "email_smtp_debug";
    public static final String CONFIG_EMAIL_FROM = "email_from";
    public static final String CONFIG_EMAIL_ALERT_TO = "email_alert_to";

    //LOCALIZATION SETTINGS
    public static final String CONFIG_DEFAULT_COUNTRY = "default_country";
    public static final String CONFIG_DEFAULT_COUNTRY_CODE = "default_country_code";
    public static final String CONFIG_DEFAULT_CURRENCY_SYMBOL = "default_currency_symbol";
    public static final String CONFIG_DEFAULT_LANGUAGE = "default_language";

    //OPENID SETTINGS
    public static final String CONFIG_GOOGLE_OPENID_BASE_URL = "google_openid_base_url";
    public static final String CONFIG_GOOGLE_OPENID_CLIENT_ID = "google_openid_client_id";
    public static final String CONFIG_GOOGLE_OPENID_API_KEY = "google_openid_api_key";
    public static final String CONFIG_GOOGLE_OPENID_REDIRECT_URL = "google_openid_redirect_url";

    //REGISTRATION SETTINGS
    public static final String CONFIG_DEFAULT_WEB_AUTH_GROUP = "default_web_auth_group";
    public static final String CONFIG_DEFAULT_POS_MACHINE_AUTH_GROUP = "default_web_auth_group";
    public static final String CONFIG_DEFAULT_SMART_PHONE_AUTH_GROUP = "default_smart_phone_auth_group";
    public static final String CONFIG_DEFAULT_WALLET_GROUP = "default_wallet_group";

    //COMPANY SETTINGS
    public static final String CONFIG_DOMAIN_NAME = "domain_name";
    public static final String CONFIG_CONTACT_ADDRESS = "contact_address";
    public static final String CONFIG_SUPPORT_EMAIL = "support_email";
    public static final String CONFIG_SUPPORT_PHONE = "support_phone";
    public static final String CONFIG_FULL_COMPANY_NAME = "full_company_name";
    public static final String CONFIG_COPYRIGHT_YEAR = "copyright_year";

    //VOUCHER SETTINGS
    public static final String CONFIG_DEFAULT_VOUCHER_EXPIRY_DAYS = "default_voucher_expiry_days";
    public static final String CONFIG_LOW_STOCK_THRESHHOLD = "low_stock_threshold";

    //SECURITY SETTINGS
    public static final String CONFIG_MAX_PASSWORD_TRIES = "max_password_tries";

    //WEB SETTINGS
    public static final String CONFIG_DEFAULT_REPORTING_DAYS = "default_reporting_days";

    //POS SETTINGS
    public static final String CONFIG_FALCON_POS_BINARY_NAME = "falcon_pos_binary_name";
    public static final String CONFIG_FALCON_POS_BINARY_LOCATION = "falcon_pos_binary_location";
    public static final String CONFIG_FALCON_POS_BINARY_VERSION = "falcon_pos_binary_version";

    //INTEGRATION SETTINGS : GLO SEAMLESS
    public static final String CONFIG_GLO_SERVICE_REQUEST_TIMEOUT = "glo_service_request_timeout";
    public static final String CONFIG_GLO_SERVICE_CLIENT_ID = "glo_service_client_id";
    public static final String CONFIG_GLO_SERVICE_USER_ID = "glo_service_user_id";
    public static final String CONFIG_GLO_SERVICE_PASSWORD = "glo_service_password";
    public static final String CONFIG_GLO_SERVICE_LOW_BALANCE_THRESHOLD = "glo_service_low_balance_threshold";

    //INTEGRATION SETTINGS : MTN GHANA

    public static final String CONFIG_MTN_GH_UNIQUE_ID_FILE = "mtn_gh_unique_id_file";

    //INTEGRATION SETTINGS : MTN MOBIFIN
    public static final String CONFIG_MOBIFIN_GH_TERMINAL_NUMBER = "mobifin_gh_terminal_number";
    public static final String CONFIG_MOBIFIN_GH_ENCRYPTION_KEY = "mobifin_gh_encryption_key";
    public static final String CONFIG_MOBIFIN_GH_TRANSACTION_KEY = "mobifin_gh_transaction_key";
    public static final String CONFIG_MOBIFIN_GH_HSM_URL = "mobifin_gh_hsm_url";
    public static final String CONFIG_MOBIFIN_GH_DISTRIBUTOR_URL = "mobifin_gh_distributor_url";
    public static final String CONFIG_MOBIFIN_GH_SWITCH_URL = "mobifin_gh_switch_url";
    public static final String CONFIG_MOBIFIN_GH_USE_LOCAL_ENCRYPTION = "mobifin_gh_use_local_encryption";
    public static final String CONFIG_MOBIFIN_GH_CASH_IN_CHECK_INTERVAL = "mobifin_gh_cash_in_check_interval";
    public static final String CONFIG_MOBIFIN_GH_CASH_IN_CHECK_COUNT = "mobifin_gh_cash_in_check_count";

    //INTEGRATION SETTINGS : MTN MOBILE MONEY
    public static final String CONFIG_MTNMOMO_GH_REQUEST_TO_PAY_URL = "mtnmomo_gh_request_to_pay_url";
    public static final String CONFIG_MTNMOMO_GH_API_TOKEN_URL = "mtnmomo_gh_api_token_url";
    public static final String CONFIG_MTNMOMO_GH_ACCOUNT_BALANCE_URL = "mtnmomo_gh_account_balance_url";
    public static final String CONFIG_MTNMOMO_GH_TARGET_ENVIRONMENT = "mtnmomo_gh_target_environment";
    public static final String CONFIG_MTNMOMO_GH_PAYER_MESSAGE = "mtnmomo_gh_payer_message";
    public static final String CONFIG_MTNMOMO_GH_PAYEE_NOTE = "mtnmomo_gh_payee_note";
    public static final String CONFIG_MTNMOMO_COLLECTIONS_KEY = "mtnmomo_gh_collections_key";
    public static final String CONFIG_MTNMOMO_API_USER = "mtnmomo_gh_api_user";
    public static final String CONFIG_MTNMOMO_API_KEY = "mtnmomo_gh_api_key";
    public static final String CONFIG_MTNMOMO_GH_CASH_IN_CHECK_INTERVAL = "mtnmomo_gh_cash_in_check_interval";
    public static final String CONFIG_MTNMOMO_GH_CASH_IN_CHECK_COUNT = "mtnmomo_gh_cash_in_check_count";

}
