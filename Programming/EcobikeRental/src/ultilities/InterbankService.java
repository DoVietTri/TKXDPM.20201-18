package ultilities;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import application.Main;
import model.Card;

public class InterbankService {
	private static final String SECRETKEY = "BJrapO8Wdtw=";
	private static final String APPCODE = "CUgp9eRNgwU=";
	private static final String VERSION = "1.0.1";
	private static final String CARDCODE = null;
	private static final String OWNER = null;
	private static final String CVVCODE = null;
	private static final String DATEEXPIRED = null;
	
	public static String getMD5(String input) throws UnsupportedEncodingException
	{
		try { 
            // Static getInstance method is called with hashing MD5 
            MessageDigest md = MessageDigest.getInstance("MD5"); 
            
  
            // digest() method is called to calculate message digest 
            //  of an input digest() return array of byte 
            byte[] messageDigest = md.digest(input.getBytes("UTF-8")); 
  
            // Convert byte array into signum representation 
            BigInteger no = new BigInteger(1, messageDigest); 
  
            // Convert message digest into hex value 
            String hashtext = no.toString(16); 
            while (hashtext.length() < 32) { 
                hashtext = "0" + hashtext; 
            } 
            return hashtext; 
        }  
  
        // For specifying wrong message digest algorithms 
        catch (NoSuchAlgorithmException e) { 
            throw new RuntimeException(e); 
        } 
	}
	
	public static String jsonInforToHash (Card card, String command, int amount, String currentTime)
	{
		String result = "{"
		+ "\"secretKey\":\""  + SECRETKEY + "\""
		+ ",\"transaction\":{"
		+ 	"\"command\":\"" + command + "\""
		+	",\"cardCode\":\"" + card.getCardNumber() + "\""
		+ 	",\"owner\":\"" + card.getCardHolderName() + "\""
		+	",\"cvvCode\":\"" + card.getSecurityCode() + "\""
		+	",\"dateExpired\":\"" + card.getExpirationDate() + "\""
		+	",\"transactionContent\":\"" + card.getTransactionDescription() + "\""
		+ 	",\"amount\":" + amount + ""
		+ 	",\"createdAt\":\"" + currentTime + "\""
		+ 	"}}";
		
		return result;
	}
	
	//Test api
	public static String jsonResetBalance()
	{
		String result = "{"
				+	"\"cardCode\":\"" + CARDCODE + "\""
				+ 	",\"owner\":\"" + OWNER + "\""
				+ 	",\"cvvCode\":\"" + CVVCODE + "\""
				+ 	",\"dateExpired\":\"" + DATEEXPIRED + "\""
				+ "}";
		return result;
	}
	
	public static String processTransaction(Card card, String command, int amount) throws UnsupportedEncodingException
	{
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		String dateTimeNow = dtf.format(now);

		String result = null;
		String statusCode = null;
		String hashCode = getMD5(jsonInforToHash(card, command, amount, dateTimeNow));
		
		HttpPatch patch = new HttpPatch("https://ecopark-system-api.herokuapp.com/api/card/processTransaction");
		patch.setHeader("Content-Type", "application/json");
		patch.setHeader("Access", "application/json");
		StringBuilder entity = new StringBuilder();
		entity.append("{"
				+ "\"version\":\""  + VERSION + "\""
				+ ",\"transaction\":{"
				+ 	"\"command\":\"" + command + "\""
				+	",\"cardCode\":\"" + card.getCardNumber() + "\""
				+ 	",\"owner\":\"" + card.getCardHolderName() + "\""
				+	",\"cvvCode\":\"" + card.getSecurityCode() + "\""
				+	",\"dateExpired\":\"" + card.getExpirationDate() + "\""
				+	",\"transactionContent\":\"" + card.getTransactionDescription() + "\""
				+ 	",\"amount\":" + amount + ""
				+ 	",\"createdAt\":\"" + dateTimeNow + "\""
				+ 	"}"
				+	",\"appCode\":\""+ APPCODE +"\""
				+ 	",\"hashCode\":\"" + hashCode + "\""
				+ "}");
		try {
			patch.setEntity(new StringEntity(entity.toString()));
			CloseableHttpClient httpClient = HttpClients.createDefault();
			CloseableHttpResponse res = httpClient.execute(patch);
			result = EntityUtils.toString(res.getEntity());
			
			JSONObject json = new JSONObject(result);
			statusCode = json.getString("errorCode");
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return statusCode;
	}
	
	//String cardCode, String owner, String cvvCode, String dateExpired
	public static String resetBalance(Card card)
	{
		String result = null;
		String statusCode = null;
		
		HttpPatch patch = new HttpPatch("https://ecopark-system-api.herokuapp.com/api/card/reset-balance");
		patch.setHeader("Content-Type", "application/json");
		patch.setHeader("Accept", "application/json");
		
		StringBuilder entity = new StringBuilder();
		entity.append("{"
				+	"\"cardCode\":\"" + card.getCardNumber() + "\""
				+ 	",\"owner\":\"" + card.getCardHolderName() + "\""
				+ 	",\"cvvCode\":\"" + card.getSecurityCode() + "\""
				+ 	",\"dateExpired\":\"" + card.getExpirationDate() + "\""
				+ "}");
//		System.out.println("content: " + entity);
		
		try {
			patch.setEntity(new StringEntity(entity.toString()));
			CloseableHttpClient httpClient = HttpClients.createDefault();
			CloseableHttpResponse res = httpClient.execute(patch);
			result = EntityUtils.toString(res.getEntity());
			JSONObject json = new JSONObject(result);
			statusCode = json.getString("errorCode");
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return statusCode;
	}
	
//	public static void main(String args[]) {
//		Card card = new Card(123, "Group 18", "118609_group18_2020", "Group 18 thue xe", "1125", "390", "VTB");
//		String code;
//		try {
//			code = InterbankService.processTransaction(card, "pay", 10000);
//			System.out.print("Code: " + code);
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}
	
}
