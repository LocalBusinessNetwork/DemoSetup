package com.rw;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.util.JSON;
import com.rw.API.ActivityStreamMgr;
import com.rw.API.AssocMemberMgr;
import com.rw.API.AssocMgr;
import com.rw.API.BusinessMgr;
import com.rw.API.ContactsMgr;
import com.rw.API.CriteriaMgr;
import com.rw.API.EventMgr;
import com.rw.API.GenericMgr;
import com.rw.API.OrgMgr;
import com.rw.API.PartnerMgr;
import com.rw.API.PartyMgr;
import com.rw.API.RWReqHandle;
import com.rw.API.RecommendationsMgr;
import com.rw.API.RfrlMgr;
import com.rw.API.RfrlOfferMgr;
import com.rw.API.SecMgr;
import com.rw.API.UserMgr;
import com.rw.persistence.RWJApplication;
import com.rw.persistence.RWJBusComp;
import com.rw.persistence.RWJBusObj;
import com.rw.persistence.RWObjectMgr;
import com.rw.persistence.RandomDataGenerator;
import com.rw.persistence.mongoMaster;
import com.rw.persistence.mongoRepo;
import com.rw.persistence.mongoStore;

/*
 * Usage DemoSetup
 * 
 * Following is the command line to run the demo setup for any specific tenant
 * 
 * For STN :  java -classpath ${RWHOME}/Web/target/web/WEB-INF/lib -jar -DPARAM3=STN -DTENANTPROXY=successfulthinkersnetwork.com ${RWHOME}/Web/target/web/WEB-INF/lib/DemoSetup-DROP2.jar
 * For LOWES : java -classpath ${RWHOME}/Web/target/web/WEB-INF/lib -jar -DPARAM3=LOWES -DTENANTPROXY=lowesforpros.com ${RWHOME}/Web/target/web/WEB-INF/lib/DemoSetup-DROP2.jar
 *
 */
public class App 
{
	
	static final Logger log = Logger.getLogger("DemoSetup");
	public static final Random random = new Random();
	public static RWObjectMgr context = new RWObjectMgr();
	public static final RWJApplication app = new RWJApplication(context);
	
    public static void main( String[] args ) throws Exception
    {
      	String rwhome = System.getenv("RWHOME");
      	System.out.println("RWHOME = " + rwhome);   	     	
      	System.setProperty("WebRoot",rwhome + "/web/src/main/webapp");
      	
		System.setProperty("JDBC_CONNECTION_STRING","localhost");
       	System.setProperty("EMAIL","local"); // Run demo setup always in non-email mode.
      	System.setProperty("VERSION","1.0"); // Run demo setup always in non-email mode.

       	Options options = new Options();
      	options.addOption("tenant", true, "Tenant ID");
      	options.addOption("connection", true, "DB server Connection");
		options.addOption("version", true, "App Version");
		
    	CommandLineParser parser = new BasicParser();

		CommandLine cmd = parser.parse( options, args);
    	
		if(cmd.hasOption("tenant")){ 
			System.setProperty("PARAM3", cmd.getOptionValue("tenant"));
		}
		
		String tenant = System.getProperty("PARAM3");
	   	if ( tenant == null ) {
	   		System.out.println("Tenant Not specified, exiting");
	   		return;
	   	}
	   	else 
			System.out.println("TENANT = " + tenant);  			

	   	context.setExecutionContextItem("tenant", tenant);
	    
		if(cmd.hasOption("version")) { 
			System.setProperty("VERSION", cmd.getOptionValue("version"));
		}

		if(cmd.hasOption("connection")){ 
			System.setProperty("JDBC_CONNECTION_STRING", cmd.getOptionValue("connection"));
		}

		System.out.println("JDBC_CONNECTION_STRING = " + System.getProperty("JDBC_CONNECTION_STRING"));  			
		 
		String demodata = cmd.getOptionValue("demodata");
		
		mongoStore m = new mongoStore(tenant);
		m.cleanUp();
		
    	Repobuilder.LoadAllMetadata(tenant); 
    	SeedData.LoadAllSeeddata(tenant);

    	String className = new String ( "com.rw." + tenant );
		Class cls = Class.forName(className); 
		Object obj = cls.newInstance(); 
		
    }
 }
