package com.rw;

import java.util.HashMap;
import java.util.Random;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.rw.API.BusinessMgr;
import com.rw.API.ContactsMgr;
import com.rw.API.PartyMgr;
import com.rw.API.SecMgr;
import com.rw.API.UserMgr;
import com.rw.persistence.RWJApplication;
import com.rw.persistence.RWJBusComp;
import com.rw.persistence.RWObjectMgr;

public class DemoSetupBase {
	
	static final Logger log = Logger.getLogger("DemoSetup");

	public static final Random random = new Random();
	public static RWObjectMgr context = new RWObjectMgr();
	public static final RWJApplication app = new RWJApplication(context);

	public static JSONObject getSecMgrDefaults() throws JSONException{
		JSONObject data = new JSONObject();
		data.put("act", "login");
		data.put("login", "phil@referralwiretest.biz");
		data.put("password","123456");
		data.put("tenant",System.getProperty("PARAM3"));
		return data;
	}
	
	public static BasicDBObject getPartyRecord(String partyEmail) throws Exception{

    	
    	BasicDBObject retVal = null;
    	RWJBusComp partyBC = app.GetBusObject("Party").getBusComp("Party");
    	
    	BasicDBObject query = new BasicDBObject();
		query.put("emailAddress", partyEmail);
		int recs = partyBC.ExecQuery(query,null);
		
		if (recs > 0){
			retVal = partyBC.currentRecord;
		}
		return retVal;
		
    	
    }
	public static String getPartyId(String login) throws Exception{

    	
    	String retVal = "undefined";
    	RWJBusComp partyBC = app.GetBusObject("Party").getBusComp("Party");
    	
    	BasicDBObject query = new BasicDBObject();
		query.put("emailAddress", login);
		int recs = partyBC.ExecQuery(query,null);
		
		if (recs > 0){
			retVal = partyBC.currentRecord.getString("_id");
		}
		return retVal;
		
    	
  	}
	
    public static String getPartyAttribute(String partyEmail,String fieldName) throws Exception{

    	
    	String retVal = "undefined";
    	RWJBusComp partyBC = app.GetBusObject("Party").getBusComp("Party");
    	
    	BasicDBObject query = new BasicDBObject();
		query.put("emailAddress", partyEmail);
		int recs = partyBC.ExecQuery(query,null);
		
		if (recs > 0){
			retVal = partyBC.currentRecord.getString(fieldName);
		}
		return retVal;
		
    	
    }
    public static String getAdminUserId() throws JSONException{
    	SecMgr sm = new SecMgr();
		
		JSONObject data = new JSONObject();

		data.put("act", "login");
		data.put("login", "admin.user@referralwire.com");
		data.put("password","123456");
		data.put("tenant",System.getProperty("PARAM3"));

		
		try {
			data = sm.handleRequest(data);
		} catch (Exception e) {
			log.debug("DemoSetup Error: ", e);
		}
		

		String userId = data.get("data").toString();
		return userId;
    	
    }
    
    public static String getMemberId(String login) throws JSONException{
    	SecMgr sm = new SecMgr();
		
		JSONObject data = new JSONObject();

		data.put("act", "login");
		data.put("login", login);
		data.put("password","123456");
		data.put("tenant",System.getProperty("PARAM3"));

		try {
			data = sm.handleRequest(data);
		} catch (Exception e) {
			log.debug("DemoSetup Error: ", e);
		}
		

		String userId = data.get("data").toString();
		
		UserMgr um = new UserMgr(userId);
		data = new JSONObject();
		data.put("act", "read");
		data.put("userid", userId);
		
		try {
			data = um.handleRequest(data);
		} catch (Exception e) {
			log.debug("DemoSetup Error: ", e);
		}
		String dString = data.getString("data");
		JSONObject d2 = new JSONObject(dString);
		String dString2 = d2.getString("_id");
		JSONObject d3 = new JSONObject(dString2);
		String memberId = d3.getString("$oid");
		//String memberId = results.getJSONObject("data").getJSONObject("_id").getString("$oid");
		//JSONObject detail = new JSONObject(data.get("data"));
		//Object oid = detail.get("_id");
		//String memberId  = oid.getString("$oid");
		//String memberId = data.get("data").toString();
		
		return memberId;
		
		
    	
    }    
    
	
	    

	public static void updateParty(String userId, String partyId, HashMap values) throws JSONException{
		
		//SecMgr sm = new SecMgr();
		
		JSONObject data = new JSONObject();
/*
		data.put("act", "login");
		data.put("login", "admin@referralwiretest.biz");
		data.put("password","123456");
		
		try {
			data = sm.handleRequest(data);
		} catch (Exception e) {
			log.debug("DemoSetup Error: ", e);
		}
*/		
		ContactsMgr cm = new ContactsMgr(userId);
		//cm.partyId = userId;
		//cm.userid = userId;
		data = new JSONObject();
		data.put("act", "update");
		data.put("userid", userId);
		data.put("skipEnrichment",true);
		data.put("id",partyId);
		
		Object[] fields = values.keySet().toArray();
		for (int i = 0; i < fields.length; i++ ) {
			String thisField = fields[i].toString();
			data.put(thisField, values.get(thisField));
		}
		try {
			cm.handleRequest(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.debug("DemoSetup Error: ", e);
		}
	}
	
	public static void updateMember(String userId, String partyId, HashMap values) throws JSONException{
		
		//SecMgr sm = new SecMgr();
		
		JSONObject data = new JSONObject();
/*
		data.put("act", "login");
		data.put("login", "admin@referralwiretest.biz");
		data.put("password","123456");
		
		try {
			data = sm.handleRequest(data);
		} catch (Exception e) {
			log.debug("DemoSetup Error: ", e);
		}
*/		
		PartyMgr pm = new PartyMgr(userId);
		//cm.partyId = userId;
		//cm.userid = userId;
		data = new JSONObject();
		data.put("act", "update");
		data.put("userid", userId);
		data.put("skipEnrichment",true);
		data.put("id",partyId);
		
		Object[] fields = values.keySet().toArray();
		for (int i = 0; i < fields.length; i++ ) {
			String thisField = fields[i].toString();
			data.put(thisField, values.get(thisField));
		}
		try {
			pm.handleRequest(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.debug("DemoSetup Error: ", e);
		}
	}
	
	public static void CreateBusiness(String mEmail,HashMap bValues,HashMap mCopyMap) throws Exception {
		JSONObject data = getSecMgrDefaults();		
		SecMgr sm = new SecMgr();
		try {
			data = sm.handleRequest(data);
		} catch (Exception e) {
			log.debug("DemoSetup Error: ", e);
		}
		String uId = data.get("data").toString();
		data = new JSONObject();

		data.put("act", "create");
		data.put("userid", uId);
		data.put("bo", "Business");
		data.put("bc", "Business");
		

		

		BasicDBObject member = getPartyRecord(mEmail);
		
		Object busvalKeys[] = bValues.keySet().toArray();
		
		for (int i = 0; i < busvalKeys.length; i++){
			String fName = (String)busvalKeys[i];
			String fVal = (String)bValues.get(fName);
			data.put(fName,fVal);
		}
		
		Object cmapKeys[] = mCopyMap.keySet().toArray();
		for (int i = 0; i < cmapKeys.length; i++){
			String copyToField = (String)cmapKeys[i];
			String copyFromField = (String)mCopyMap.get(copyToField);
			String copyValue = member.getString(copyFromField);
			data.put(copyToField,copyValue);
		}
		data.put("faceBookPage", "www.yahoo.com");
		data.put("linkedInPage", "www.yahoo.com");
		data.put("twitterPage", "www.yahoo.com");
		data.put("yelpPage", "www.yahoo.com");
		data.put("youtubePage", "www.yahoo.com");
		
		System.out.println(data.toString());
		BusinessMgr bm = new BusinessMgr(uId);
		
		try {
			data = bm.handleRequest(data);
		} catch (Exception e) {
			log.debug("DemoSetup Error: ", e);
		}
		
		
	}


}
