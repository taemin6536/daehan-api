package com.nuri.mys.bems.domain.jwt.constant;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

/**
 * E-Coin에 사용되는 상수 지정
 */
public class Constant {

	// 시스템 에러
	public static final int SERROR_INVALID_AUTH = 777; // auth 이상 시스템 에러

	// 최고 관리자 id와 지갑키
	public static final String ADMIN_COMP_ID = "elmo"; // E/S admin wallet id
//	public static final String ADMIN_USER_ID = "admin"; // E/S admin wallet id
	public static final Integer ADMIN_WLT_KEY = 1; // E/S admin wallet key
	public static final Integer ADMIN_TRASH_WLT_KEY = 2; // E/S admin wallet key
	public static final String ADMIN_TRASH_WLT_ID = "trash"; // E/S admin wallet key
	
	public static enum Question {
		/**
		 * Account/Authentication
		 */
		QACNT("QACNT"),
		/**
		 * Buy ELMO
		 */
		QBUY("QBUY"), 
		/**
		 * Send/Receive
		 */
		QTXRX("QTXRX"), 
		/**
		 * Recharge
		 */
		QRCHG("QRCHG"), 
		/**
		 * Receipt
		 */
		QRECT("QRECT"), 
		/**
		 * VendingStation
		 */
		QVS("QVS");

		private String cd = "";

		Question(String cd) {
			this.cd=cd;
		}

		public String getCode(){
			return this.cd;
		}
	}
	
	public static enum WalletType {
		SMRT("SMRT"), 
		USSD("USSD"), 
		WIFI("WIFI");
		
		private String cd = "";

		WalletType(String cd) {
			this.cd=cd;
		}

		public String getCode(){
			return this.cd;
		}
	}
	
	public static enum Trade {
		/**
		 * remittance c2c
		 */
		RMTC_C2C_SEND("TRC2C"),
		/**
		 * remittance c2c
		 */
		RMTC_C2C_RECV("RRC2C"),
		/**
		 * remittance c2c
		 */
		RMTC_C2C_FEE("FRC2C"),
		/**
		 * remittance c2w
		 */
		RMTC_C2W_SEND("TRC2W"),
		/**
		 * remittance c2w
		 */
		RMTC_C2W_RECV("RRC2W"),
		/**
		 * remittance c2w
		 */
		RMTC_C2W_FEE("FRC2W"),
		/**
		 * remittance w2c
		 */
		RMTC_W2C_SEND("TRW2C"), 
		/**
		 * remittance w2c
		 */
		RMTC_W2C_RECV("RRW2C"), 
		/**
		 * remittance w2c
		 */
		RMTC_W2C_FEE("FRW2C"), 
		/**
		 * remittance w2w
		 */
		RMTC_W2W_SEND("TRW2W"), 
		/**
		 * remittance w2w
		 */
		RMTC_W2W_RECV("RRW2W"), 
		/**
		 * remittance w2w
		 */
		RMTC_W2W_FEE("FRW2W"),
		/**
		 * buy electricity
		 */
		RECHARGE_SEND("TPWR"), 
		/**
		 * buy electricity
		 */
		RECHARGE_RECV("RPWR"), 
		/**
		 * pay debt electricity
		 */
		PAYDEBT_SEND("TPAY"), 
		/**
		 * pay debt electricity
		 */
		PAYDEBT_RECV("RPAY"), 
		/**
		 * buy token
		 */
		BUY_TOKEN_SEND("TTKN"), 
		/**
		 * buy token
		 */
		BUY_TOKEN_RECV("RTKN"), 
		/**
		 * withdrawal(cash out)
		 */
		WTDR_SEND("TWTDR"), 
		/**
		 * withdrawal(cash out)
		 */
		WTDR_RECV("RWTDR"), 
		/**
		 * withdrawal(cash out)
		 */
		WTDR_FEE("FWTDR"), 
		/**
		 * close wallet(fee 0)
		 */
		CLS_WALET_SEND("TCLS"), 
		/**
		 * close wallet(fee 0)
		 */
		CLS_WALET_RECV("RCLS"), 
		/**
		 * ASSIGN ES
		 */
		ASSIGN_SEND("TASN"),
		/**
		 * ASSIGN ES
		 */
		ASSIGN_RECV("RASN"),
		
		/**
		 * PG CHARGE REQUEST
		 */
		PG_REQ("PGREQ"),
		/**
		 * PG CHARGE RESPONSE
		 */
		PG_RES("PGRES"),
		
		/**
		 * issue
		 */
		ISSUE("ISSUE") 
		;

		private String cd = "";

		Trade(String cd) {
			this.cd=cd;
		}

		public String getCode(){
			return this.cd;
		}
	}

	public static enum PhotoID {
		VOTER_ID("VOTER"), 
		NTNL_ID("NTNL"), 
		DRVR_LCNS("DRVR"), 
		NHIS_ID("NHIS"),
		SSNIT_ID("SSNIT"),
		PSPRT("PSPRT"),
		INCOPR_CERT("INCOPR");

		private String cd = "";

		PhotoID(String cd) {
			this.cd=cd;
		}

		public String getCode(){
			return this.cd;
		}
	}

	public static enum AddCert {
		REGIST_TENANCY_AGRE("TNCY"), 
		UTIL_BILL("UTIL"), 
		INCOME_TAX_CERT("INCOME"), 
		BANK_STAT("BANK"), 
		REF_LETTER("LETTER")
		;
		
		private String cd = "";

		AddCert(String cd) {
			this.cd=cd;
		}

		public String getCode(){
			return this.cd;
		}
	}

	public static enum Asset {
		
		TOKEN("TOKEN"), CASH("CASH");
		
		private String cd = "";

		Asset(String cd) {
			this.cd=cd;
		}

		public String getCode(){
			return this.cd;
		}
	}
	
	
	// Enum
	public static enum Status {
		ACTIVE("ACTV"), 
		BLOCKED("BLOCK"), 
		TERMINATED("TERM"), 
		CLOSED("CLOSE"), 
		STANDBY("STNBY");
		
		private String cd = "";

		Status(String cd) {
			this.cd=cd;
		}

		public String getCode(){
			return this.cd;
		}
	}

	public static enum Device {
		AND("AND"), 
		IOS("IOS"), 
		FTP("FTP");

		private String cd = "";

		Device(String cd) {
			this.cd=cd;
		}

		public String getCode(){
			return this.cd;
		}
	}

	public static enum KYC {
		/**
		 * MERCHANT > UNLIMITED
		 */
		MER("MER"), 
		/**
		 * KYC6 > MAX 50000
		 */
		KYC6("KYC6"), 
		/**
		 * KYC5 > MAX 20000
		 */
		KYC5("KYC5"), 
		/**
		 * KYC4 > MAX 3000
		 */
		KYC4("KYC4"), 
		/**
		 * KYC3 > UNUSED
		 */
		KYC3("KYC3"), 
		/**
		 * KYC2 > UNUSED
		 */
		KYC2("KYC2"), 
		/**
		 * KYC1 > UNUSED
		 */
		KYC1("KYC1");

		private String cd = "";

		KYC(String cd) {
			this.cd=cd;
		}

		public String getCode(){
			return this.cd;
		}
	}

	public static enum ComplainType {
		/**
		 * ProcessTime
		 */
		PROC_TIME("TIME"),
		/**
		 * IN/OUT TOKEN
		 */
		INOUT("INOUT"),
		/**
		 * CUSTOMER RESPONSE
		 */
		CUSTOMER("CUST"),
		/**
		 * ETC
		 */
		ETC("ETC");

		private String cd = "";

		ComplainType(String cd) {
			this.cd=cd;
		}

		public String getCode(){
			return this.cd;
		}
	}

	public static enum ComplainProc {
		/**
		 * Regist
		 */
		REGIST("RGST"),
		/**
		 * Confirm
		 */
		CONFIRM("CONF"),
		/**
		 * process
		 */
		PROC("PROC"),
		/**
		 * Complete
		 */
		COMPLETE("CPLT"),
		/**
		 * Return
		 */
		RETRURN("RTRN");

		private String cd = "";

		ComplainProc(String cd) {
			this.cd=cd;
		}

		public String getCode(){
			return this.cd;
		}
	}
	
	public static enum Notice {
		/**
		 * CUST
		 */
		NTC_CS("NTCS"),
		/**
		 * EStation
		 */
		NTC_ES("NTES"),
		/**
		 * VStation
		 */
		NTC_VS("NTVS");

		private String cd = "";

		Notice(String cd) {
			this.cd=cd;
		}

		public String getCode(){
			return this.cd;
		}
	}
	
	// Limit
	public static final int LIMIT_USER_ID_MIN = 5; // USER ID 최소자리
	public static final int LIMIT_USER_ID_MAX = 32; // USER ID 최대자리
	public static final int LIMIT_PASSWD_MIN = 5; // 비밀번호 최소자리
	public static final int LIMIT_PASSWD_MAX = 16; // 비밀번호 최대자리

	public static enum Role {
		/**
		 * User
		 */
		NORMAL_USER("NormalUser", 1), 
		/**
		 * Admin
		 */
		ADMIN("Administrator", 2), 
		/**
		 * VSAdmin
		 */
		VS_ADMIN("VSAdmin", 4), 
		/**
		 * VSOper
		 */
		VS_OPER("VSCasher", 5), 
		/**
		 * ESOper
		 */
		ES_OPER("ESOper", 6), 
		/**
		 * ESAdmin
		 */
		ES_ADMIN("ESAdmin", 7); 

		private String nm;
		private int key;

		Role(String nm,int key) {
			this.nm=nm;
			this.key=key;
		}

		public String getName(){
			return this.nm;
		}
		
		public int getKey(){
			return this.key;
		}
	}
	
	// Message
	public static final String PUBLIC_OK = "OK";

	// Enum
	public static enum Sex {
		MALE('M'), FEMALE('F');
		
		private char cd;
		
		Sex(char cd){
			this.cd = cd;
		}
		
		public char getCode(){
			return this.cd;
		}
	}

	public static enum OrderSource {
		/**
		 * Mobile
		 */
		MOB("MOB"),
		/**
		 * USSD
		 */
		USSD("USSD"),
		/**
		 * EStation
		 */
		ES("ES"),
		/**
		 * VStation
		 */
		VS("VS");
		
		private String cd;
		
		OrderSource(String cd){
			this.cd = cd;
		}
		
		public String getCode(){
			return this.cd;
		}
	}
	
	public static enum UserType {
		/**
		 * E/Station
		 */
		CES("CES"),
		/**
		 * V/Station
		 */
		CVS("CVS"),
		/**
		 * Merchant
		 */
		MCT("MCT"),
		/**
		 * Admin
		 */
		ADM("ADM"),
		/**
		 * Customer
		 */
		CST("CST"),
		/**
		 * ECG
		 */
		ECG("ECG"),
		/**
		 * ES Admin
		 */
		ESA("ESA"),
		/**
		 * ES Oper
		 */
		ESO("ESO"),
		/**
		 * V/S Admin
		 */
		VSA("VSA"),
		/**
		 * V/S Oper
		 */
		VSO("VSO");
		private String cd;
		
		UserType(String cd){
			this.cd = cd;
		}
		
		public String getCode(){
			return this.cd;
		}
		
	}
	
	// Regex
	public static final Pattern REGEX_EMAIL_ADDRESS = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

	private static final DateTimeFormatter DTF4FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
	private static final DateTimeFormatter DTF4YYYYMMDDHHMMSS = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
	private static final DateTimeFormatter DTF4YYMMDDHHMMSS = DateTimeFormatter.ofPattern("YYMMddHHmmss");

	public static String getUTCTime4yyyyMMddHHmmss() {
		return Instant.now().atOffset(ZoneOffset.UTC).format(DTF4YYYYMMDDHHMMSS);
	}

	public static String getUTCTime4BC() {
		return Instant.now().atOffset(ZoneOffset.UTC).format(DTF4FORMAT);
	}

	public static String getUTCHexTime4YYMMddHHmmss() {
		return Long.toHexString(Long.parseLong(Instant.now().atOffset(ZoneOffset.UTC).format(DTF4YYMMDDHHMMSS))).toUpperCase();
	}
}