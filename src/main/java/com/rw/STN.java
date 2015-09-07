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
import com.rw.API.RecommendationsMgr;
import com.rw.API.RfrlMgr;
import com.rw.API.RfrlOfferMgr;
import com.rw.API.SecMgr;
import com.rw.API.TenantMgr;
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
 * Usage DemoSetpup -connection dbserver -tenant tenantSuffix -version versionnumber
 */
public class STN extends DemoSetupBase
{
	
	static final Logger log = Logger.getLogger("STNDemoSetup");
	
    public STN() throws Exception
    {
    	setupTenant("1.0", "STN", "rw.jsp");
    	
    	// Repobuilder.LoadMetadataFile("STN", "Tenant Specific Metadata over rides");
    	
    	CreateAUser("NONE", "admin", "user", "referralwire.com",new HashMap());
    	
    	String pRoot = "dataVault/";
    	
      	String AssocId = CreatePublicAssoc("A");
      	
      	HashMap<String,ArrayList> lov = new HashMap();
      	
      	lov.put("CC_Location_1",new ArrayList(Arrays.asList("10 mile radius - local","1 Residence Location")));
      	lov.put("CC_Location_2",new ArrayList(Arrays.asList("50 mile radius - metro","1 Residence Location")));
      	lov.put("CC_Household_adult_age_3",new ArrayList(Arrays.asList("Adult age: 20-30","2 Household Adult Age")));
      	lov.put("CC_Household_adult_age_4",new ArrayList(Arrays.asList("Adult age: 30-40","2 Household Adult Age")));
      	lov.put("CC_Household_adult_age_5",new ArrayList(Arrays.asList("Adult age: 40-50","2 Household Adult Age")));
      	lov.put("CC_Household_adult_age_6",new ArrayList(Arrays.asList("Adult age: 50-60","2 Household Adult Age")));
      	lov.put("CC_Household_adult_age_7",new ArrayList(Arrays.asList("Adult age: 60-70","2 Household Adult Age")));
      	lov.put("CC_Household_adult_age_8",new ArrayList(Arrays.asList("Adult age: 70-80","2 Household Adult Age")));
      	lov.put("CC_Household_adult_age_9",new ArrayList(Arrays.asList("Adult age: 80+","2 Household Adult Age")));
      	lov.put("CC_Household_composition_10",new ArrayList(Arrays.asList("Child near graduation","3 Household Composition")));
      	lov.put("CC_Household_composition_11",new ArrayList(Arrays.asList("Generations - adult and child","3 Household Composition")));
      	lov.put("CC_Household_composition_12",new ArrayList(Arrays.asList("Generations - adult only","3 Household Composition")));
      	lov.put("CC_Household_composition_13",new ArrayList(Arrays.asList("Generations - adult, child, and parent","3 Household Composition")));
      	lov.put("CC_Household_composition_14",new ArrayList(Arrays.asList("Marital status - married","3 Household Composition")));
      	lov.put("CC_Household_composition_15",new ArrayList(Arrays.asList("Marital status - single","3 Household Composition")));
      	lov.put("CC_Household_composition_16",new ArrayList(Arrays.asList("Single parent","3 Household Composition")));
      	lov.put("CC_Household_composition_17",new ArrayList(Arrays.asList("Elderly parent","3 Household Composition")));
      	lov.put("CC_Household_composition_18",new ArrayList(Arrays.asList("Has pet","3 Household Composition")));
      	lov.put("CC_Household_composition_19",new ArrayList(Arrays.asList("New teen driver","3 Household Composition")));
      	lov.put("CC_Estimated_Income_20",new ArrayList(Arrays.asList("Estimated income - $50 to $100k","4 Estimated Income")));
      	lov.put("CC_Estimated_Income_21",new ArrayList(Arrays.asList("Estimated income - $100 to $150k","4 Estimated Income")));
      	lov.put("CC_Estimated_Income_22",new ArrayList(Arrays.asList("Estimated income - $150 to $200k","4 Estimated Income")));
      	lov.put("CC_Estimated_Income_23",new ArrayList(Arrays.asList("Estimated income - $200 to $300k","4 Estimated Income")));
      	lov.put("CC_Estimated_Income_24",new ArrayList(Arrays.asList("Estimated income - $300k+","4 Estimated Income")));
      	lov.put("CC_Real_Estate_Ownership_25",new ArrayList(Arrays.asList("Owns home","5 Real Estate: Ownership")));
      	lov.put("CC_Real_Estate_Ownership_26",new ArrayList(Arrays.asList("Rents home","5 Real Estate: Ownership")));
      	lov.put("CC_Real_Estate_Value_27",new ArrayList(Arrays.asList("Home market value less than $200k","6 Real Estate: Value")));
      	lov.put("CC_Real_Estate_Value_28",new ArrayList(Arrays.asList("Home market value $200k - 400k","6 Real Estate: Value")));
      	lov.put("CC_Real_Estate_Value_29",new ArrayList(Arrays.asList("Home market value $400k - 600k","6 Real Estate: Value")));
      	lov.put("CC_Real_Estate_Value_30",new ArrayList(Arrays.asList("Home market value $600k - 800k","6 Real Estate: Value")));
      	lov.put("CC_Real_Estate_Value_31",new ArrayList(Arrays.asList("Home market value $800k - 1m","6 Real Estate: Value")));
      	lov.put("CC_Real_Estate_Value_32",new ArrayList(Arrays.asList("Home market value $1m - 1.2m","6 Real Estate: Value")));
      	lov.put("CC_Real_Estate_Value_33",new ArrayList(Arrays.asList("Home market value $1.2m - 1.5m","6 Real Estate: Value")));
      	lov.put("CC_Real_Estate_Value_34",new ArrayList(Arrays.asList("Home market value $1.5m+","6 Real Estate: Value")));
      	lov.put("CC_Real_Estate_Year_built_35",new ArrayList(Arrays.asList("Home built: before 1900","7 Real Estate: Year built")));
      	lov.put("CC_Real_Estate_Year_built_36",new ArrayList(Arrays.asList("Home built: 1900 – 1940","7 Real Estate: Year built")));
      	lov.put("CC_Real_Estate_Year_built_37",new ArrayList(Arrays.asList("Home built: 1941 – 1960","7 Real Estate: Year built")));
      	lov.put("CC_Real_Estate_Year_built_38",new ArrayList(Arrays.asList("Home built: 1961 – 1980","7 Real Estate: Year built")));
      	lov.put("CC_Real_Estate_Year_built_39",new ArrayList(Arrays.asList("Home built: 1981 – 2000","7 Real Estate: Year built")));
      	lov.put("CC_Real_Estate_Year_built_40",new ArrayList(Arrays.asList("Home built: after 2000","7 Real Estate: Year built")));
      	lov.put("CC_Vehicle_41",new ArrayList(Arrays.asList("Owns 2 vehicles","8 Vehicle")));
      	lov.put("CC_Vehicle_42",new ArrayList(Arrays.asList("Owns 3+ vehicles","8 Vehicle")));
      	lov.put("CC_Vehicle_43",new ArrayList(Arrays.asList("Owns new vehicle","8 Vehicle")));
      	lov.put("CC_Donor_Capacity_44",new ArrayList(Arrays.asList("Donor capacity: $0 to $300","9 Donor Capacity")));
      	lov.put("CC_Donor_Capacity_45",new ArrayList(Arrays.asList("Donor capacity: $301 to $750","9 Donor Capacity")));
      	lov.put("CC_Donor_Capacity_46",new ArrayList(Arrays.asList("Donor capacity: $751 to $10,000","9 Donor Capacity")));
      	lov.put("CC_Donor_Capacity_47",new ArrayList(Arrays.asList("Donor capacity: $10,001 plus ","9 Donor Capacity")));
      	lov.put("CE_Household_event_48",new ArrayList(Arrays.asList("Expected parent","10 Consumer Event")));
      	lov.put("CE_Household_event_49",new ArrayList(Arrays.asList("New parent","10 Consumer Event")));
      	lov.put("CE_Household_event_50",new ArrayList(Arrays.asList("New teen driver","10 Consumer Event")));
      	lov.put("CE_Household_event_51",new ArrayList(Arrays.asList("Newlywed","10 Consumer Event")));
      	lov.put("CE_Household_event_52",new ArrayList(Arrays.asList("Recent college graduate in HH","10 Consumer Event")));
      	lov.put("CE_Household_event_53",new ArrayList(Arrays.asList("Recent divorce","10 Consumer Event")));
      	lov.put("CE_Household_event_54",new ArrayList(Arrays.asList("Recent empty nester","10 Consumer Event")));
      	lov.put("CE_Household_event_55",new ArrayList(Arrays.asList("Recently moved","10 Consumer Event")));
      	lov.put("CE_Household_event_56",new ArrayList(Arrays.asList("Deceased parent","10 Consumer Event")));
      	lov.put("CE_Household_event_57",new ArrayList(Arrays.asList("Formal occasion","10 Consumer Event")));
      	lov.put("CE_Household_event_58",new ArrayList(Arrays.asList("New child","10 Consumer Event")));
      	lov.put("CE_Household_event_59",new ArrayList(Arrays.asList("New pet","10 Consumer Event")));
      	lov.put("CE_Household_event_60",new ArrayList(Arrays.asList("Intends personal milestone celebration","10 Consumer Event")));
      	lov.put("CE_Household_event_61",new ArrayList(Arrays.asList("Planned move","10 Consumer Event")));
      	lov.put("CE_Household_event_62",new ArrayList(Arrays.asList("Planning family event","10 Consumer Event")));
      	lov.put("CE_Household_event_63",new ArrayList(Arrays.asList("Intends wedding","10 Consumer Event")));
      	lov.put("CE_Household_event_64",new ArrayList(Arrays.asList("Recent injury","10 Consumer Event")));
      	lov.put("CE_Property_event_65",new ArrayList(Arrays.asList("Has recently experienced a property loss","10 Consumer Event")));
      	lov.put("CE_Property_event_66",new ArrayList(Arrays.asList("Looking to diversify assets from recent sale or settlement","10 Consumer Event")));
      	lov.put("CE_Property_event_67",new ArrayList(Arrays.asList("Planning a home renovation","10 Consumer Event")));
      	lov.put("CE_Property_event_68",new ArrayList(Arrays.asList("Planning to purchase a home","10 Consumer Event")));
      	lov.put("CE_Property_event_69",new ArrayList(Arrays.asList("Restructuring household finances","10 Consumer Event")));
      	lov.put("CE_Property_event_70",new ArrayList(Arrays.asList("Recent home buyer","10 Consumer Event")));
      	lov.put("CE_Property_event_71",new ArrayList(Arrays.asList("Selling house","10 Consumer Event")));
      	lov.put("CE_Property_event_72",new ArrayList(Arrays.asList("Settlement or asset sale","10 Consumer Event")));
      	lov.put("CE_Property_event_73",new ArrayList(Arrays.asList("Recent property casualty","10 Consumer Event")));
      	lov.put("CC_Vehicle_74",new ArrayList(Arrays.asList("Intends purchase vehicle","10 Consumer Event")));
      	lov.put("CE_Interests_75",new ArrayList(Arrays.asList("Ailment orthepedic","11 Consumer Interests")));
      	lov.put("CE_Interests_76",new ArrayList(Arrays.asList("Arts supporter","11 Consumer Interests")));
      	lov.put("CE_Interests_77",new ArrayList(Arrays.asList("Cat owner","11 Consumer Interests")));
      	lov.put("CE_Interests_78",new ArrayList(Arrays.asList("Dieting weight loss","11 Consumer Interests")));
      	lov.put("CE_Interests_79",new ArrayList(Arrays.asList("Dog owner","11 Consumer Interests")));
      	lov.put("CE_Interests_80",new ArrayList(Arrays.asList("Exercise health","11 Consumer Interests")));
      	lov.put("CE_Interests_81",new ArrayList(Arrays.asList("Travel cruises","11 Consumer Interests")));
      	lov.put("CE_Interests_82",new ArrayList(Arrays.asList("Travel domestic","11 Consumer Interests")));
      	lov.put("CE_Interests_83",new ArrayList(Arrays.asList("Travel any","11 Consumer Interests")));
      	lov.put("CE_Interests_84",new ArrayList(Arrays.asList("Travel international","11 Consumer Interests")));
      	lov.put("CE_Interests_85",new ArrayList(Arrays.asList("Alimony","11 Consumer Interests")));
      	lov.put("CE_Interests_86",new ArrayList(Arrays.asList("Allergies","11 Consumer Interests")));
      	lov.put("CE_Interests_87",new ArrayList(Arrays.asList("Back pain","11 Consumer Interests")));
      	lov.put("CE_Interests_88",new ArrayList(Arrays.asList("Care-giving services","11 Consumer Interests")));
      	lov.put("CE_Interests_89",new ArrayList(Arrays.asList("College admissions","11 Consumer Interests")));
      	lov.put("CE_Interests_90",new ArrayList(Arrays.asList("Cosmetic treatments","11 Consumer Interests")));
      	lov.put("CE_Interests_91",new ArrayList(Arrays.asList("Dental services","11 Consumer Interests")));
      	lov.put("CE_Interests_92",new ArrayList(Arrays.asList("Diabetes","11 Consumer Interests")));
      	lov.put("CE_Interests_93",new ArrayList(Arrays.asList("Energy efficiency","11 Consumer Interests")));
      	lov.put("CE_Interests_94",new ArrayList(Arrays.asList("Investigative or security services","11 Consumer Interests")));
      	lov.put("CE_Interests_95",new ArrayList(Arrays.asList("Legal services","11 Consumer Interests")));
      	lov.put("CE_Interests_96",new ArrayList(Arrays.asList("Family counseling","11 Consumer Interests")));
      	lov.put("CE_Interests_97",new ArrayList(Arrays.asList("Pet training","11 Consumer Interests")));
      	lov.put("CE_Interests_98",new ArrayList(Arrays.asList("Preventitive health","11 Consumer Interests")));
      	lov.put("CE_Interests_99",new ArrayList(Arrays.asList("Student academics","11 Consumer Interests")));
      	lov.put("CE_Interests_100",new ArrayList(Arrays.asList("Student athletics","11 Consumer Interests")));
      	lov.put("CE_Interests_101",new ArrayList(Arrays.asList("Vehicle repair","11 Consumer Interests")));
      	lov.put("CE_Interests_102",new ArrayList(Arrays.asList("Accounting services","11 Consumer Interests")));
      	lov.put("CE_Interests_103",new ArrayList(Arrays.asList("Financial planning","11 Consumer Interests")));
      	lov.put("CE_Interests_104",new ArrayList(Arrays.asList("College financing","11 Consumer Interests")));
      	lov.put("CE_Interests_105",new ArrayList(Arrays.asList("Money making opportunities","11 Consumer Interests")));
      	lov.put("CC_Real_Estate_Ownership_106",new ArrayList(Arrays.asList("Interested in investment properties","11 Consumer Interests")));
      	lov.put("BC_Location_107",new ArrayList(Arrays.asList("10 mile radius - local","12 Business Location")));
      	lov.put("BC_Location_108",new ArrayList(Arrays.asList("50 mile radius - metro","12 Business Location")));
      	lov.put("BC_Occupation_109",new ArrayList(Arrays.asList("Occupation - business owner","13 Occupation: Role")));
      	lov.put("BC_Occupation_110",new ArrayList(Arrays.asList("Occupation - is upper management","13 Occupation: Role")));
      	lov.put("BC_Occupation_111",new ArrayList(Arrays.asList("Small office or home office","13 Occupation: Role")));
      	lov.put("BC_Occupation_112",new ArrayList(Arrays.asList("Working woman","13 Occupation: Role")));
      	lov.put("BC_Industry_113",new ArrayList(Arrays.asList("Accountancy","14 Occupation: Industry")));
      	lov.put("BC_Industry_114",new ArrayList(Arrays.asList("Automotive &amp; aerospace","14 Occupation: Industry")));
      	lov.put("BC_Industry_115",new ArrayList(Arrays.asList("Banking &amp; finance","14 Occupation: Industry")));
      	lov.put("BC_Industry_116",new ArrayList(Arrays.asList("Business consultancy","14 Occupation: Industry")));
      	lov.put("BC_Industry_117",new ArrayList(Arrays.asList("Communications","14 Occupation: Industry")));
      	lov.put("BC_Industry_118",new ArrayList(Arrays.asList("Construction","14 Occupation: Industry")));
      	lov.put("BC_Industry_119",new ArrayList(Arrays.asList("Document management","14 Occupation: Industry")));
      	lov.put("BC_Industry_120",new ArrayList(Arrays.asList("Education","14 Occupation: Industry")));
      	lov.put("BC_Industry_121",new ArrayList(Arrays.asList("Fashion &amp; retail","14 Occupation: Industry")));
      	lov.put("BC_Industry_122",new ArrayList(Arrays.asList("Government - national &amp; local","14 Occupation: Industry")));
      	lov.put("BC_Industry_123",new ArrayList(Arrays.asList("Human resources/training","14 Occupation: Industry")));
      	lov.put("BC_Industry_124",new ArrayList(Arrays.asList("Industrial engineering","14 Occupation: Industry")));
      	lov.put("BC_Industry_125",new ArrayList(Arrays.asList("Insurance","14 Occupation: Industry")));
      	lov.put("BC_Industry_126",new ArrayList(Arrays.asList("Technology services/software","14 Occupation: Industry")));
      	lov.put("BC_Industry_127",new ArrayList(Arrays.asList("Legal","14 Occupation: Industry")));
      	lov.put("BC_Industry_128",new ArrayList(Arrays.asList("Libraries","14 Occupation: Industry")));
      	lov.put("BC_Industry_129",new ArrayList(Arrays.asList("Manufacturing","14 Occupation: Industry")));
      	lov.put("BC_Industry_130",new ArrayList(Arrays.asList("Media/Press/Publishing","14 Occupation: Industry")));
      	lov.put("BC_Industry_131",new ArrayList(Arrays.asList("Medical/Healthcare","14 Occupation: Industry")));
      	lov.put("BC_Industry_132",new ArrayList(Arrays.asList("Military/Defence","14 Occupation: Industry")));
      	lov.put("BC_Industry_133",new ArrayList(Arrays.asList("Transport/Logistics","14 Occupation: Industry")));
      	lov.put("BC_Company_Size_134",new ArrayList(Arrays.asList("1 employee","15 Business Size")));
      	lov.put("BC_Company_Size_135",new ArrayList(Arrays.asList("2-10 employees","15 Business Size")));
      	lov.put("BC_Company_Size_136",new ArrayList(Arrays.asList("11-50 employees","15 Business Size")));
      	lov.put("BC_Company_Size_137",new ArrayList(Arrays.asList("51-200 employees","15 Business Size")));
      	lov.put("BC_Company_Size_138",new ArrayList(Arrays.asList("201-500 employees","15 Business Size")));
      	lov.put("BC_Company_Size_139",new ArrayList(Arrays.asList("501-1000 employees","15 Business Size")));
      	lov.put("BC_Company_Size_140",new ArrayList(Arrays.asList("1001-5000 employees","15 Business Size")));
      	lov.put("BC_Company_Size_141",new ArrayList(Arrays.asList("5001-10,000 employees","15 Business Size")));
      	lov.put("BC_Company_Size_142",new ArrayList(Arrays.asList("10,000+ employees","15 Business Size")));
      	lov.put("BE_Business_event_143",new ArrayList(Arrays.asList("Competitive challenges","16 Business Event")));
      	lov.put("BE_Business_event_144",new ArrayList(Arrays.asList("Financing or restructuring","16 Business Event")));
      	lov.put("BE_Business_event_145",new ArrayList(Arrays.asList("Technology upgrade","16 Business Event")));
      	lov.put("BE_Business_event_146",new ArrayList(Arrays.asList("New business initiative","16 Business Event")));
      	lov.put("BE_Business_event_147",new ArrayList(Arrays.asList("New job","16 Business Event")));
      	lov.put("BE_Business_event_148",new ArrayList(Arrays.asList("Planning business event","16 Business Event")));
      	lov.put("BE_Business_event_149",new ArrayList(Arrays.asList("Intends asset transaction","16 Business Event")));
      	lov.put("BE_Business_event_150",new ArrayList(Arrays.asList("Intends business formation","16 Business Event")));
      	lov.put("BE_Business_event_151",new ArrayList(Arrays.asList("Intends capacity expansion ","16 Business Event")));
      	lov.put("BE_Business_event_152",new ArrayList(Arrays.asList("Intends real estate development","16 Business Event")));
      	lov.put("BE_Business_event_153",new ArrayList(Arrays.asList("Handling staff turnover","16 Business Event")));
      	lov.put("BE_Interests_154",new ArrayList(Arrays.asList("Accounting services","17 Business Interests")));
      	lov.put("BE_Interests_155",new ArrayList(Arrays.asList("Affordable care act","17 Business Interests")));
      	lov.put("BE_Interests_156",new ArrayList(Arrays.asList("Career opportunities","17 Business Interests")));
      	lov.put("BE_Interests_157",new ArrayList(Arrays.asList("Cash management","17 Business Interests")));
      	lov.put("BE_Interests_158",new ArrayList(Arrays.asList("International trade","17 Business Interests")));
      	lov.put("BE_Interests_159",new ArrayList(Arrays.asList("Legal services","17 Business Interests")));
      	lov.put("BE_Interests_160",new ArrayList(Arrays.asList("Management support","17 Business Interests")));
      	lov.put("BE_Interests_161",new ArrayList(Arrays.asList("Payroll and hr support","17 Business Interests")));
      	lov.put("BE_Interests_162",new ArrayList(Arrays.asList("Property management","17 Business Interests")));
      	lov.put("BE_Interests_163",new ArrayList(Arrays.asList("Sales and marketing support","17 Business Interests")));
      	
      	HashMap bruce = new HashMap();

      	bruce.put("personalTitle","MR");
      	bruce.put("business","Simplified Computing");
      	bruce.put("profession","Information Technology Consulting");
      	bruce.put("jobTitle","President");
      	bruce.put("streetAddress1_work","2400 Camino Ramon");
      	bruce.put("streetAddress2","");
      	bruce.put("cityAddress_work","San Ramon");
      	bruce.put("stateAddress_work","CA");
      	bruce.put("postalCodeAddress_work","95583");
      	bruce.put("webPage","www.simplifiedc.com");
      	bruce.put("question1","Home or Office?");
      	bruce.put("question2","What service is the prospect interested in?");
      	bruce.put("question3","Mac or PC? Both?");
      	bruce.put("quote1_title","100% More Productive");
      	bruce.put("quote1_body","Bruce helped us make sure all of our email is synchronized and our computers are on the network. We're 100% more productive now than we were before.");
      	bruce.put("quote1_source","Shelly Walsh - COO, Industrial Glass");
      	bruce.put("quote2_title","IT Peace of Mind");
      	bruce.put("quote2_body","The first time someone tried to hack into our system we knew we need professional help. Bruce put safeguards in place that has prevented more problems and has really given us IT peace of mind.");
      	bruce.put("quote2_source","Ray Billingsly");
      	
      	bruce.put("photoUrl",pRoot+"bruce_p.jpg");
      	bruce.put("profilePhotoUrl",pRoot+"bruce_t.jpg");
      	bruce.put("bio","Bruce Campden started Simplified Computing to help everyday computer and small businesses users make the most of their personal technology hardware.\n\nWhether it's a slow computer, viruses, spyware, or upgrading your machine, the Digital Handyman can help with your PC");
      	bruce.put("logo","dataVault/bruce_l.jpg");
      	bruce.put("targetCustomers","Small business who want technology to increase their productivity - not distract them\nSeniors looking for a helping hand to get up and running with the web\nAnyone who's read -- or experienced -- the horror stories about computer viruses -- and doesn't want it to happen to them. ");
      	bruce.put("accreditations","Member: Walnut Creek Rotary Club");
      	bruce.put("specialties","Computer Maintenance, Upgrades, and Repair\nAdvice and Instruction\nInternet Connectivity and Networking\nSafe Internet");
      	bruce.put("webPage","www.simplifiedc.com");
      	bruce.put("image_url_1",pRoot+"bruce_gallery1.jpg");
      	bruce.put("image_caption_1","Home networking provides high speed internet access throughout the house");
      	bruce.put("image_url_2",pRoot+"bruce_gallery2.jpg");
      	bruce.put("image_caption_2","We enable business clients to outsource IT");
      	bruce.put("image_url_3",pRoot+"bruce_gallery3.jpg");
      	bruce.put("image_caption_3","Computers are powerful, but many risks need to be managed");
      	/*
      	bruce.put("joinedMonth","May");
      	bruce.put("joinedDate","16");
      	bruce.put("joinedYear","2012");
      	*/
      	
      	HashMap janet = new HashMap();
      	

      	janet.put("personalTitle","MISS");
      	janet.put("business","Digital Marketing");
      	janet.put("profession","Social Media Marketing");
      	janet.put("jobTitle","Owner");
      	janet.put("streetAddress1_work","");
      	janet.put("streetAddress2","");
      	janet.put("cityAddress_work","");
      	janet.put("stateAddress_work","CA");
      	janet.put("postalCodeAddress_work","94526");
      	janet.put("webPage","www.digitalmarketingllp.com");
      	janet.put("question1","What's the name of the business?");
      	janet.put("question2","Where is the business located");
      	janet.put("question3","What's the role of contact?");
      	janet.put("quote1_title","Totally modernized our marketing");
      	janet.put("quote1_body","We knew our customers were increasingly looking to the web for information, but didn't know where to begin. Janet totally modernized our marketing efforts.  She helped us work out a methodical and cost effective way of getting our message out.");
      	janet.put("quote1_source","David Wellerbach - Owner, Wellerbach Plumbing");
      	janet.put("quote2_title","Sales are up 15%");
      	janet.put("quote2_body","Janet helped us really take control of our image on the web and acquire good customers that previously didn't know us -- or know how good we are.  Our sales have gone up 15% over the last 6 months and I believe our work with Janet is the biggest reason why.");
      	janet.put("quote2_source","Anne Wang - Realtor, Diablo Properties");

      	janet.put("photoUrl",pRoot + "janet_p.jpg");
      	janet.put("profilePhotoUrl",pRoot + "janet_t.jpg");
      	janet.put("bio","The Marketing Paradigm has shifted. Nearly all consumers (97%) now use online media when researching products or services. The misconception is that only young people use these technologies when in fact, nearly all of the buying public now uses them.\n \nMarketing has also shifted from a one-way broadcast to a multi-point conversation. In the past, communications were “broadcast” exclusively through mass marketing channels like radio, TV and newspapers. However with social much of the communication is controlled by the prospects through services like Facebook and Twitter, which have more than 700 million members combined. Additionally, YouTube, gets 300 million visitors a month.\n \nA former Senior Director of Digital Media at Chevron, Janet is a knowledgeable expert who can help you plan and execute online and mobile marketing strategies with zero extra effort on your part. ");
      	janet.put("logo",pRoot + "janet_l.jpg");
      	janet.put("targetCustomers","Small businesses serving local consumers\nBusiness trying to understand how to take control over their digital reputation");
      	janet.put("featuredIn","<u>Danville Weekly News</u>, \"Local Businesses turn to the Web\" 11/20/12\n<u>Walnut Creek Bulletin</u> \"Local Online Contest Creates Stir Among Shoe Shoppers\" 7/15/2012");
      	janet.put("accreditations","Winner of 2008 Webby Award for most creative banner add\nBachelors in Digital Media, Stanford University\nMember of Digital Media Association of Northern California");
      	janet.put("specialties","Search engine optimization\nDigital ad design\nVideo shooting, editing and syndication");
      	janet.put("faceBookPage","www.facebook.com/digitalmarketing");
      	janet.put("googlePlusPage","www.googleplus.com/digitalmarketing");
      	janet.put("webPage","www.digitalmarketing.com");
      	janet.put("question1","What's the name of the business?");
      	janet.put("question2","Where is the business located");
      	janet.put("question3","What's the role of contact?");
      	janet.put("image_url_1",pRoot + "janet_gallery1.jpg");
      	janet.put("image_caption_1","Most visited web sites");
      	janet.put("image_url_2",pRoot + "janet_gallery2.png");
      	janet.put("image_caption_2","Marketing = Digital Marketing");
      	janet.put("image_url_3",pRoot + "janet_gallery3.png");
      	janet.put("image_caption_3","A few of my customers");
      	/*
      	janet.put("joinedMonth","Feb");
      	janet.put("joinedDate","22");
      	janet.put("joinedYear","2012");
      	*/
      	
      	HashMap jeff = new HashMap();
      	jeff.put("personalTitle","MR");
      	jeff.put("business","Stanley Consulting");
      	jeff.put("profession","Business Consultant");
      	jeff.put("jobTitle","Managing Partner");
      	jeff.put("streetAddress1_work","25 Estrella Place");
      	jeff.put("streetAddress2","");
      	jeff.put("cityAddress_work","Danville");
      	jeff.put("stateAddress_work","CA");
      	jeff.put("postalCodeAddress_work","94526");
      	jeff.put("webPage","www.Stanleyconsulting.com");
      	jeff.put("question1","What's the name of the company");
      	jeff.put("question2","What made you think of me?");
      	jeff.put("question3","");
      	jeff.put("quote1_title","Experience managing difficult transitions");
      	jeff.put("quote1_body","Jeff brings a wealth of experience to helping his clients manage difficult transitions.  He's the reason that several of his clients are not only still here but thriving more than ever.");
      	jeff.put("quote1_source","Bill Davis, Chairman of the Board, Sleep Train");
      	jeff.put("quote2_title","A Rich Problem Solving Playbook");
      	jeff.put("quote2_body","Jeff brings a rich problem solving playbook to a wide range of management challenges. He knows what's around the corner because he's been there.");
      	jeff.put("quote2_source","Carter Graham, Vice President, Lourds Ice Cream");
      	
      	jeff.put("photoUrl",pRoot+"jeff_p.jpg");
      	jeff.put("profilePhotoUrl",pRoot+"jeff_t.jpg");
      	jeff.put("LNProfile","");
      	jeff.put("LNProfileId","");
      	jeff.put("FaceBookId","1073842608");
      	jeff.put("bio","At Stanley Consulting, we provide management consulting services to both large and medium sized businesses. Our consultants combine extensive corporate and consulting experience. The result for our clients: access to best practice expertise; practical, action-oriented solutions; and a consultative, customized approach");
      	jeff.put("logo",pRoot+"jeff_l.jpg");
      	jeff.put("targetCustomers","Family businesses preparing for a transition\nFortunate 1000 companies looking for better talent development tools");
      	jeff.put("featuredIn","Business Insider: June 2011 - \"Where have all the good men gone?\"\nFortune.com: August 2012 - \"The number one risk to your business\"");
      	jeff.put("accreditations","Member of 2012 Excellence in Management Consulting Honor Roll\nMBA UC Berkeley");
      	jeff.put("specialties","Succession planning and top talent development\nLeadership development and executive coaching\nOrganization design and change");
      	jeff.put("faceBookPage","www.facebook/Stanleyconsulting.com");
      	jeff.put("webPage","www.Stanleyconsulting.com");
      	jeff.put("question1","What's the name of the company");
      	jeff.put("question2","What made you think of me?");
      	jeff.put("image_url_1",pRoot+"jeff_gallery1.jpg");
      	jeff.put("image_caption_1","Buy our book on Amazon!");
      	jeff.put("image_url_2",pRoot+"jeff_gallery2.jpg");
      	jeff.put("image_caption_2","We've been cited by numerous publications");
      	jeff.put("image_url_3",pRoot+"jeff_gallery3.png");
      	jeff.put("image_caption_3","Price Waterhouse Survey on Succession");
      	jeff.put("speakerTravelMiles","40");
      	/*
      	jeff.put("joinedMonth","Nov");
      	jeff.put("joinedDate","4");
      	jeff.put("joinedYear","2011");
      	*/
      	 
      	
      	HashMap jenny = new HashMap();
      	

      	jenny.put("personalTitle","MISS");
      	jenny.put("business","The Law Office of Jenny Lerner");
      	//jenny.put("profession","Attorney Corporate Law");
      	jenny.put("profession","Accountant");
      	jenny.put("jobTitle","Managing Partner");
      	jenny.put("streetAddress1_work","1415 Oakland Blvd");
      	jenny.put("streetAddress2","Suite 102");
      	jenny.put("cityAddress_work","Walnut Creek");
      	jenny.put("stateAddress_work","CA");
      	jenny.put("postalCodeAddress_work","94596");
      	jenny.put("webPage","");
      	jenny.put("question1","Business or Consumer?");
      	jenny.put("question2","What's the primary legal question?");
      	jenny.put("question3","Is the prospect working with any other legal resources?");
      	jenny.put("quote1_title","Savvy advice you can afford");
      	jenny.put("quote1_body","Jenny helped us settle our outstanding contract issues quickly and at a much lower cost than expected based on our conversations with other attourneys.");
      	jenny.put("quote1_source","Carl Blakenship, Danville City Council");
      	jenny.put("quote2_title","Advice you can trust");
      	jenny.put("quote2_body","Jenny was both extremely knowledgeable and very attentive to helping us put a structure in place for helping safeguard our future");
      	jenny.put("quote2_source","Karen Deutch, Alamo Resident");
      	jenny.put("powerpartner1","Payroll administration");
      	jenny.put("powerpartner2", "Accounting services");
      	jenny.put("powerpartner3","Commerical banking");
      	
      	jenny.put("accreditations","JD/MBA UC Berkeley\nMember, State Bar of California");

      	
      	jenny.put("photoUrl",pRoot + "jenny_p.jpg");
      	jenny.put("profilePhotoUrl",pRoot + "jenny_t.jpg");
      	jenny.put("LNProfile","");
      	jenny.put("LNProfileId","");
      	// jenny.put("FaceBookId","1231536324");

      	jenny.put("bio","Jenny is a Walnut Creek business attorney who assists small- to mid-sized businesses primarily in the arts, entertainment and technology industries with services such as: business planning and entity selection, crowdfunding planning, asset protection, contracts, regulatory compliance, minority- and women-owned business certification, commercial litigation, employment law, and trade secret and idea protection.");
      	jenny.put("logo",pRoot + "jenny_l.jpg");
      	jenny.put("targetCustomers","Small businesses and startups\nArts, entertainment and technology industry\nMinority owned businesses");
      	jenny.put("featuredIn","<u>Who's who in Law Today</u>, December 2009\n<u>Contra Consta Times</u> March 2011 \"Copyright Law for Digital Artists\"");
      	jenny.put("specialties","<b>Business Formation</b> S-Corporation, C-Corporation and LLP Formation\n<b>Intellectual Property</b> Copyright protection, trademark registration and patent filing\n<b>Contracts</b>Consultant, employee, distributor and supplier contracts");
      	jenny.put("tags","attorney incorporation startup");
      	jenny.put("parentId","");
      	jenny.put("question1","Year founded?");
      	jenny.put("question2","Who are the investors?");
      	jenny.put("question3","Number of employees?");
      	jenny.put("image_url_1",pRoot + "jenny_gallery1.jpg");
      	jenny.put("image_caption_1","East Bay Up and Comer");
      	jenny.put("image_url_2",pRoot + "jenny_gallery2.png");
      	jenny.put("image_caption_2","A few of my clients");
      	jenny.put("image_url_3",pRoot + "jenny_gallery3.png");
      	jenny.put("image_caption_3","Speaking at the Concerned Citizens Forum 2012");
      	/*jenny.put("joinedMonth","Sep");
      	jenny.put("joinedDate","31");
      	jenny.put("joinedYear","2012");*/
      	jenny.put("isSpeaker", "true");
      	jenny.put("speakerPhone", "925-436-9001");
      	jenny.put("speakerEmail", "jenny@newhorizons.net");
      	jenny.put("speakerShortDescription","The Joy of Stress: Humor as a Coping Mechanism");
      	jenny.put("speakerBio","Jenny is an acclaimed speaker, author and international stress management and humor consultant who has evoked wit and irreverent humor on her audience for over 30 years. Using humor to reframe a stressful situation, Jenny captures a new perspective on the difficult parts of life. Her teaching style, credibility and incontestable humor are integral parts to her compelling presence");
      	jenny.put("speakingTopics","<b>The Power of Humor and Optimism</b> When we add the ingredient of humor, our ability to handle life's inevitable ups and downs becomes more accessible.\n <b>The WOW Factor (Wisdom, Openness, and Wonder as a benchmark for living well) </b> We've all met enthusiastic, energetic people that leave us wondering what they could possibly be \"on\" to be the way they are.");
      	jenny.put("speakerTravelMiles", "50");
      	
      	
      	
      	
      	
      	HashMap lori = new HashMap();
      	
/*
      	lori.put("personalTitle","MISS");
      	lori.put("business","Eldercare Support");
      	lori.put("profession","Eldercare management");
      	lori.put("jobTitle","Owner");
      	lori.put("streetAddress1","1808 Tice Valley Boulevard");
      	lori.put("streetAddress2","");
      	lori.put("cityAddress","Walnut Creek");
      	lori.put("stateAddress","CA");
      	lori.put("postalCodeAddress","94595");
      	lori.put("webPage","www.eldercaresprt.com");
      	lori.put("question1","Where does the elderly individual live?");
      	lori.put("question2","What's the relationship of the contact to the elderly individual?");
      	lori.put("question3","What's the elderly individual's primary source of income?");
      	lori.put("question4","Does the elderly individual own their a home?");
      	lori.put("quote1_title","Lifesaver!");
      	lori.put("quote1_body","We were worried about Mom but didn't know what to do.  Lori educated us on the options and helpd us find a care solution that worked for everyone.  She's a real lifesaver!");
      	lori.put("quote1_source","Mabel Ferrero, Walnut Creek");
      	lori.put("quote2_title","One stop shop");
      	lori.put("quote2_body","We knew we had to find a better arrangement for Dad.  We start by trying to do all the research ourselves.  Within 10 minutes of talking to Lori we realized she had answers to virtually all of our questions - and a few more we should have been asking. She's a one stop shop.");
      	lori.put("quote2_source","George Campden, Alamo");
      	
      	lori.put("photoUrl",pRoot+"lori_p.jpg");
      	lori.put("profilePhotoUrl",pRoot+"lori_t.jpg");
      	lori.put("bio","Eldercare Support LLC provides personalized geriatric care services that offer a unique, comprehensive, and flexible approach in meeting the needs of elders, older adults, and their families who live in Contra Costa and Alameda counties.\n\nA professional geriatric care manager, Lori Meyers leads the Eldercare Support consulting team.  She offers assistance with planning for future health care needs by finding professional and reputable, geriatric care services, home care services, independent living and assisted living programs and counseling/family mediations.");
      	lori.put("logo",pRoot+"lori_l.jpg");
      	lori.put("targetCustomers","Families that are realizing that Mom or Dad can no longer safely take care of themselves\nFamilies with questions and concerns about the bewildering array of options and pressures from caring for an elderly parent. ");
      	lori.put("featuredIn","Studingers Directory of Top Eldercare Resources in 2008, 2009, 2010, 2011, and 2012\n");
      	lori.put("accreditations","<b>Member:</b> National Association of Professional Geriatric Managers\n<b>Member:</b> AARP Certified Eldercare Resources\n<b>Member:</b> Better Business Bureau, Walnut Creek");
      	lori.put("specialties","<b>Assessment:</b> We determine the physical and emotional needs of the individual\n<b>Care Plans:</b> Based on our assessment we'll compile a complete plan for ensuring the individuals needs are met \n<b>Education:</b> We help set family member expectations and let them know what's worked best for other families. \n<b>Implementation:</b> We ensure the plan is carried out in a way that achieves the desired outcomes.\n<b>Advocacy:</b> Last but not least we monitor the progress of the individual and help advocate on their behalf to other 3rd party services that participate in their care.");
      	lori.put("webPage","www.eldercaresprt.com");
      	lori.put("image_url_1",pRoot+"lori_gallery1.jpg");
      	lori.put("image_caption_1","Family counseling session in progress");
      	lori.put("image_url_2",pRoot+"lori_gallery2.png");
      	lori.put("image_caption_2","Services we offer");
      	lori.put("image_url_3",pRoot+"lori_gallery3.png");
      	lori.put("image_caption_3","Certified by NAPGCM");
  */    	
      	
      	HashMap melissa = new HashMap();
      	

      	melissa.put("personalTitle","MISS");
      	melissa.put("business","Walnut Creek Spa");
      	melissa.put("profession","Massage Therapist");
      	melissa.put("jobTitle","Owner");
      	melissa.put("streetAddress1_work","180 Griffith Lane");
      	melissa.put("streetAddress2","");
      	melissa.put("cityAddress_work","Brentwood");
      	melissa.put("stateAddress_work","CA");
      	melissa.put("postalCodeAddress_work","94513");
      	melissa.put("webPage","");
      	melissa.put("question1","What services is the contact interested in?");
      	melissa.put("question2","What's the occasion?");
      	melissa.put("question3","What made you think of me?");
      	melissa.put("quote1_title","Body and Mind Healer");
      	melissa.put("quote1_body","Melissa is more than just a mesage therapist, she's really a body and mind healer.");
      	melissa.put("quote1_source","Claire Davis");
      	melissa.put("quote2_title","Helped me calibrate my internal compass");
      	melissa.put("quote2_body","A few years ago I went through a huge upheaval with my marriage and my career.  Melissa really help me calibrate my internal company so I could navigate through what would otherwise been a really scary process.");
      	melissa.put("quote2_source","Kerri Michaels, Brentwood");
      	
      	melissa.put("photoUrl",pRoot + "melissa_p.jpg");
      	melissa.put("profilePhotoUrl",pRoot + "melissa_t.jpg");
      	melissa.put("bio","Whether yearning for change or coping with unwanted change thrust upon you, we all need support to get through life's transitions. Melissa Carleton at the Walnut Creek Spa has found her own calling by providing physical, emotional and spiritual therapy to clients in the midst of change.  She is a multi-disciplined renaissance healer who understands how each part of our being is connected and provides guidance and treatment to the whole person.  She is a licensed massage therapist and certified palm reader. ");
      	melissa.put("logo",pRoot + "melissa_l.jpg");
      	melissa.put("targetCustomers","Individuals coping with a high level of ongoing stress\nIndividuals coping with sudden, disorienting change");
      	melissa.put("accreditations","Associates Degree of Massage Therapy from Antioch Wellness Institute\nContra Costa County Licensed Massage Therapist\nCertification of Accomplishment - Spenser Institute of Palmistry");
      	melissa.put("specialties","Clinical Deep Tissue Massage\nDeep Work Massage\nEsalen Massage\nSpiritual and Palm Readings");
      	melissa.put("image_url_1",pRoot + "melissa_gallery1.jpg");
      	melissa.put("image_caption_1","I help clients balanced their mind, body and spirit");
      	melissa.put("image_url_2",pRoot + "melissa_gallery2.jpg");
      	melissa.put("image_caption_2","I provide a relaxed, safe environment for my treatments");
      	melissa.put("image_url_3",pRoot + "melissa_gallery3.jpg");
      	melissa.put("image_caption_3","The palm is a reflection of the larger self");
      	/*melissa.put("joinedMonth","Jul");
      	melissa.put("joinedDate","20");
      	melissa.put("joinedYear","2013");*/
      	
      	
      	
      	HashMap peter = new HashMap();
      	
      	peter.put("personalTitle","DOCTOR");
      	peter.put("business","Center for Spinal Health");
      	peter.put("profession","Chiropractor");
      	peter.put("jobTitle","Practice Lead");
      	peter.put("streetAddress1_work","3075 Citrus Circle");
      	peter.put("streetAddress2","Suite 175");
      	peter.put("cityAddress_work","Walnut Creek");
      	peter.put("stateAddress_work","CA");
      	peter.put("postalCodeAddress_work","94598");
      	peter.put("webPage","www.csh.com");
      	peter.put("question1","What are the primary symptoms?");
      	peter.put("question2","Has there been a recent injury");
      	peter.put("question3","");
      	peter.put("quote1_title","A major improvement to my quality of life");
      	peter.put("quote1_body","Peter not only helped me heal from recent back injury, but he also helped me identify and change lifestyle factors that were really putting me at risk.");
      	peter.put("quote1_source","Ben Steven, San Ramon");
      	peter.put("quote2_title","Peter practices the state of the art");
      	peter.put("quote2_body","As a neural health specialist I really admire Peter's extraordinary commjtment to continuing education and ensuring his clients have the benefit of recent progress in the field.");
      	peter.put("quote2_source","Dr. Larry Donner, Neurologist, San Ramon Regional Medical Center");
      	
      	peter.put("photoUrl",pRoot + "peter_p.jpg");
      	peter.put("profilePhotoUrl",pRoot + "peter_t.jpg");
      	peter.put("bio","Dr. Peter McDermott specializes in treating patients who suffer from chronic lower back and neck pain, headaches, repetitive stress disorders, work injuries and whiplash. He provides patients excellent chiropractic care, physiotherapy methodologies, exercise therapy, as well as professional ergonomic advice.");
      	peter.put("logo",pRoot + "peter_l.jpg");
      	peter.put("targetCustomers","Any one suffering from chronic neck or back pain\nPatients looking for alternatives to drugs for pain management");
      	peter.put("accreditations","Peter holds a doctorate in chiropractic medicine from UC Davis\nHe is a member of the California Association of Chiropractors");
      	peter.put("specialties","Reduction or elimination of pain through natural, non-invasive, and drug-free treatments\nRecovery of Function\nPreventive Program and Maintenance Visits \nManagement of chronic lower back and neck pain");
      	peter.put("faceBookPage","www.facebook.com/petermcdermott");
      	peter.put("image_url_1",pRoot + "peter_gallery1.jpg");
      	peter.put("image_caption_1","Common causes of back pain");
      	peter.put("image_url_2",pRoot + "peter_gallery2.jpg");
      	peter.put("image_caption_2","Our offices");
      	peter.put("image_url_3",pRoot + "peter_gallery3.png");
      	peter.put("image_caption_3","Peter's certification from Kinesio");
      	/*
      	peter.put("joinedMonth","Jan");
      	peter.put("joinedDate","3");
      	peter.put("joinedYear","2013");
      	*/
      	
      	
      	

      	
      	HashMap phil = new HashMap();

      	phil.put("personalTitle","MR");
      	phil.put("business","Remington Accounting");
      	phil.put("profession","Accountant");
      	phil.put("jobTitle","Certified Public Accountant");
      	phil.put("streetAddress1_work","1910 Olympic Blvd");
      	phil.put("streetAddress2","");
      	phil.put("cityAddress_work","Walnut Creek");
      	phil.put("stateAddress_work","CA");
      	phil.put("postalCodeAddress_work","94596");
      	phil.put("webPage","www.remingtoncpa.com");
      	phil.put("question1","Is the prospect interested in personal or commercial accounting services?");
      	phil.put("question2","If commercial, how large is the company?");
      	phil.put("question3","What did you tell the prospect about me?");
      	phil.put("quote1_title","Part of our own company");
      	phil.put("quote1_body","We view Phil and his organization as an integral part our extended team at Sunrise Mortuary.  His tax advice has really strengthened our business");
      	phil.put("quote1_source","Dave Stephens -- Owner, Sunrise Mortuary");
      	phil.put("quote2_title","More than tax advice");
      	phil.put("quote2_body","Phil provides more than just tax advice = he's helped us structure the assets of the company to limit our liability and maximize our flexibility");
      	phil.put("quote2_source","Bill Howitzer ");
      	
      	phil.put("photoUrl",pRoot + "phil_p.jpg");
      	phil.put("profilePhotoUrl",pRoot + "phil_t.jpg");
      	phil.put("bio","<b>Individuals and business do not plan to fail, they fail to plan</b>\nPhillip Winthrop will tell you what the numbers mean and invite discussions on improving your financial health.\n\nData is not information.phil.put(The professionals of Remington Accounting weave a business picture from bits of financial data.phil.put(We share that picture with our clients in a clear, straight-forward, and honest way. ");
      	phil.put("logo",pRoot + "phil_l.jpg");
      	phil.put("targetCustomers","Individuals and small businesses looking for strategic advice on how minimize taxes\nIndividuals with high value, illiquid assets that could present an large tax burden for their heirs");
      	phil.put("specialties","Tax Planning and Return Preparation\nAudit, Accounting and Assurance Services\nBusiness Roundtables");
      	phil.put("webPage","www.remingtoncpa.com");
      	phil.put("image_url_1",pRoot + "phil_gallery1.png");
      	phil.put("image_caption_1","How you can save $50K on taxes");
      	phil.put("image_url_2",pRoot + "phil_gallery2.png");
      	phil.put("image_caption_2","Without being auditted");
      	phil.put("image_url_3",pRoot + "phil_gallery3.png");
      	phil.put("image_caption_3","Changes to the tax law that <u>will</u> affect you this year");
      	/*
      	phil.put("joinedMonth","Oct");
      	phil.put("joinedDate","14");
      	phil.put("joinedYear","2012");
      	*/
      	
      	
      	HashMap susan = new HashMap();
      	susan.put("personalTitle", "MISS");
      	susan.put("business", "Freeman Landscaping");
      	susan.put("profession", "Landscape Design");
      	susan.put("jobTitle","Owner");
      	susan.put("streetAddress1_work", "");
      	susan.put("streetAddress2", "");
      	susan.put("cityAddress_work", "San Ramon");
      	susan.put("stateAddress_work", "CA");
      	susan.put("postalCodeAddress_work","94582");
      	susan.put("webPage","www.franklandscape.com");
      	susan.put("question1","What’s the occasion?");
      	susan.put("question2","Residential or commercial?");
      	susan.put("question3","How large is the property?");
      	susan.put("quote1_title","Stunning");
      	susan.put("quote1_body","We knew we wanted to renovate our back yard, but really weren't sure how to make the pieces fit.  Susan and her team introduced us to new possibilities that completely opened our eyes.  The end result was <b>stunning</b>.");
      	susan.put("quote1_source","Dave and Elisha Thomas - Walnut Creek");
      	susan.put("quote2_title","Elegance with economy");
      	susan.put("quote2_body","Susan transformed our outdoor spaces on a shoestring.  She combines elegance with economy.");
      	susan.put("quote2_source","Sheila Barnes - San Ramon");
      	
      	susan.put("bio","Freeman landscaping is a garden design group serving the San Francisco Bay Area. From concept to completion, we blend art, architecture, stone, wood and water to provide balance, beauty and superior craftsmanship to each new landscape environment.");
      	susan.put("photoUrl",pRoot+"susan_p.jpg");
      	susan.put("profilePhotoUrl",pRoot+"susan_t.jpg");
      	susan.put("logo",pRoot+"susan_l.png");
      	susan.put("targetCustomers","Anyone who's ready to reinvigorate their outdoor living space\nCivic and municipal organization seeking to make a statement about their community");
      	susan.put("featuredIn","Diablo Magazine \"Best of the Best\" September 2010\nBerkeley Ecology Digest");
      	susan.put("accreditations","Certified by American Landscaping Association\nMember: San Ramon Chamber of Commerce\nMember: San Ramon Rotary");
      	susan.put("specialties","Residental Gardens Design\nPublic Plazas Design\nOngoing Maintenance");
      	susan.put("faceBookPage","");
      	susan.put("googlePlusPage","www.google.com");
      	susan.put("twitterPage","");
      	susan.put("webPage","www.Freemanlandscaping.com");
      	susan.put("image_url_1",pRoot+"susan_gallery1.jpg");
      	susan.put("image_caption_1","Urban Elegance");
      	susan.put("image_url_2",pRoot+"susan_gallery2.jpg");
      	susan.put("image_caption_2","St. Mary's Library Walk");
      	susan.put("image_url_3",pRoot+"susan_gallery3.jpg");
      	susan.put("image_caption_3","Palace Grounds 1840 Stutgartt Alamo");
      	/*
      	susan.put("joinedMonth","Mar");
      	susan.put("joinedDate","19");
      	susan.put("joinedYear","2013");
      	*/


      	
      	HashMap valeria = new HashMap();
      	

      	valeria.put("personalTitle","MISS");
      	valeria.put("business","First USA Bank");
      	valeria.put("profession","Banker");
      	valeria.put("jobTitle","Director");
      	valeria.put("streetAddress1_work","1442 North Main Street");
      	valeria.put("streetAddress2","");
      	valeria.put("cityAddress_work","Walnut Creek");
      	valeria.put("stateAddress_work","CA");
      	valeria.put("postalCodeAddress_work","94596");
      	valeria.put("webPage","www.fusa.com");
      	valeria.put("question1","Does the contact current use another local bank");
      	valeria.put("question2","What set of services is the prospect looking for");
      	valeria.put("question3","What made you think of me?");
      	valeria.put("quote1_title","Made it easy");
      	valeria.put("quote1_body","When I first moved to the bay area, get my bank accounts set up was just another chore.  Valeria made it really easy to get everything in order quickly");
      	valeria.put("quote1_source","Charles Dougherty, Walnut Creek");
      	valeria.put("quote2_title","Someone I Trust");
      	valeria.put("quote2_body","For me working with Valeria has meant working with someone I really trust instead of a faceless corporation. I feel like a customer not an account number.");
      	valeria.put("quote2_source","Susan Wilkes, Lafayette");
      	
      	valeria.put("photoUrl",pRoot+"valeria_p.jpg");
      	valeria.put("profilePhotoUrl",pRoot+"valeria_t.jpg");
      	valeria.put("bio","Valeria joined the First USA in April of 2007 as a Personal Banker and has worked in the banking industry since 2002.valeria.put(She prides herself on providing a high degree of personal service to newcomers and first time homeowners.\n\nIn her role as Director of Retail Banking she acts as concierge who not only helps set up new accounts for her customers, but also advises them on how to best leverage other services within the First USA family. ");
      	valeria.put("logo",pRoot+"valeria_l.jpg");
      	valeria.put("targetCustomers","Newcomers getting established in the East Bay.valeria.put(\nNew homeowners\nEstablished locals discouraged by the impersonal service of the big banks.");
      	valeria.put("featuredIn","Walnut Creek Chamber of Commerce Honor Roll");
      	valeria.put("accreditations","First USA's Main Street Branch Employee of Year Award 2011\nAssociate Degree of Finance, Diablo Valley Community College 2010");
      	valeria.put("faceBookPage","www.facebook.com/fusa");
      	valeria.put("webPage","www.fusa.com");
      	valeria.put("image_url_1",pRoot+"valeria_gallery1.jpg");
      	valeria.put("image_caption_1","Mortgage Rates are at Historical Lows!");
      	valeria.put("image_url_2",pRoot+"valeria_gallery2.png");
      	valeria.put("image_caption_2","At First USA Customer Satisfaction is our Highest Priority");
      	valeria.put("image_url_3",pRoot+"valeria_gallery3.png");
      	valeria.put("image_caption_3","Valleria is your Personal Banker");
      	valeria.put("isSpeaker","true");
      	valeria.put("speakerTravelMiles","30");
      	/*valeria.put("joinedMonth","Aug");
      	valeria.put("joinedDate","11");
      	valeria.put("joinedYear","2012");*/
      	valeria.put("speakerBio","Extensive administrative and planning experience having worked primarily in the C-suite corporate setting. Actively involved in philanthropic efforts for the last 10 years, serving both the community and academic institutions. Effectively utilizes social media for event planning and promotion, while fostering and growing relationships within the business community.");
      	valeria.put("speakerShortDescription","Guiding the future of education");
      	valeria.put("speakingTopics","Fundraising \nEducation\nE-learning");
      	valeria.put("speakerPhone","510-434-7890");
      	valeria.put("speakerEmail","vsantana@learningtree.org");
      	
      	
      	AssocId = CreatePublicAssoc("A");
      	
      	CreateAUser(AssocId, "Susan", "Freeman", "referralwiretest.biz",susan);
      	CreateAUser(AssocId, "Jeff", "Stanley", "stanleyconsulting.com",jeff);
      	CreateAUser(AssocId, "Jenny", "Lerner", "referralwiretest.biz",jenny);
      	CreateAUser(AssocId, "Peter", "McDermott", "csh.com",peter);
      	CreateAUser(AssocId, "Janet", "Periman", "digitalmarketingllp.com",janet);
      	CreateAUser(AssocId, "Lori", "Meyers", "referralwiretest.biz",lori);
      	CreateAUser(AssocId, "Valeria", "Santana", "fusa.com",valeria);
      	String philId = CreateAUser(AssocId, "Phillip", "Winthrop", "referralwiretest.biz",phil);
      	CreateAUser(AssocId, "Bruce", "Campden", "simplifiedc.com",bruce);
      	CreateAUser(AssocId, "Melissa", "Carleton", "wcspa.com",melissa);
      	
      	System.out.println("Created Users");
      	
      	HashMap memberNames = new HashMap();
      	memberNames.put("susan@referralwiretest.biz","Susan Freeman");
      	memberNames.put("jeff.stanley@stanleyconsulting.com", "Jeff Stanley");
      	memberNames.put("jenny@referralwiretest.biz", "Jenny Lerner");
      	memberNames.put("peter.mcdermott@csh.com", "Peter McDermott");
      	memberNames.put("janet.periman@digitalmarketingllp.com", "Janet Periman");
      	memberNames.put("lori@referralwiretest.biz","Lori Meyers");
      	memberNames.put("valeria.santana@fusa.com","Valeria Santana");
      	memberNames.put("phil@referralwiretest.biz","Phillip Winthrop");
      	memberNames.put("bruce.campden@simplifiedc.com","Bruce Campden");
      	memberNames.put("melissa.carleton@wcspa.com","Melissa Carleton");
      	

      	createPartner("lori@referralwiretest.biz", "phil@referralwiretest.biz");
      	createPartner("phil@referralwiretest.biz", "lori@referralwiretest.biz");
      	

      	
    	/*
    	createPartner("susan@referralwiretest.biz", "jeff.stanley@stanleyconsulting.com");
    	createPartner("susan@referralwiretest.biz", "jenny@referralwiretest.biz");
    	createPartner("susan@referralwiretest.biz", "peter.mcdermott@csh.com");
    	createPartner("susan@referralwiretest.biz", "janet.periman@digitalmarketingllp.com");
    	createPartner("susan@referralwiretest.biz", "lori@referralwiretest.biz");
    	createPartner("susan@referralwiretest.biz", "valeria.santana@fusa.com");
    	createPartner("susan@referralwiretest.biz", "phil@referralwiretest.biz");
    	createPartner("susan@referralwiretest.biz", "bruce.campden@simplifiedc.com");
    	createPartner("susan@referralwiretest.biz", "melissa.carleton@wcspa.com");
    	createPartner("jeff.stanley@stanleyconsulting.com", "susan@referralwiretest.biz");
    	createPartner("jeff.stanley@stanleyconsulting.com", "jenny@referralwiretest.biz");
    	createPartner("jeff.stanley@stanleyconsulting.com", "peter.mcdermott@csh.com");
    	createPartner("jeff.stanley@stanleyconsulting.com", "janet.periman@digitalmarketingllp.com");
    	createPartner("jeff.stanley@stanleyconsulting.com", "lori@referralwiretest.biz");
    	createPartner("jeff.stanley@stanleyconsulting.com", "valeria.santana@fusa.com");
    	createPartner("jeff.stanley@stanleyconsulting.com", "phil@referralwiretest.biz");
    	createPartner("jeff.stanley@stanleyconsulting.com", "bruce.campden@simplifiedc.com");
    	createPartner("jeff.stanley@stanleyconsulting.com", "melissa.carleton@wcspa.com");
    	createPartner("jenny@referralwiretest.biz", "susan@referralwiretest.biz");
    	createPartner("jenny@referralwiretest.biz", "jeff.stanley@stanleyconsulting.com");
    	createPartner("jenny@referralwiretest.biz", "peter.mcdermott@csh.com");
    	createPartner("jenny@referralwiretest.biz", "janet.periman@digitalmarketingllp.com");
    	createPartner("jenny@referralwiretest.biz", "lori@referralwiretest.biz");
    	createPartner("jenny@referralwiretest.biz", "valeria.santana@fusa.com");
    	createPartner("jenny@referralwiretest.biz", "phil@referralwiretest.biz");
    	createPartner("jenny@referralwiretest.biz", "bruce.campden@simplifiedc.com");
    	createPartner("jenny@referralwiretest.biz", "melissa.carleton@wcspa.com");
    	createPartner("peter.mcdermott@csh.com", "susan@referralwiretest.biz");
    	createPartner("peter.mcdermott@csh.com", "jeff.stanley@stanleyconsulting.com");
    	createPartner("peter.mcdermott@csh.com", "jenny@referralwiretest.biz");
    	createPartner("peter.mcdermott@csh.com", "janet.periman@digitalmarketingllp.com");
    	createPartner("peter.mcdermott@csh.com", "lori@referralwiretest.biz");
    	createPartner("peter.mcdermott@csh.com", "valeria.santana@fusa.com");
    	createPartner("peter.mcdermott@csh.com", "phil@referralwiretest.biz");
    	createPartner("peter.mcdermott@csh.com", "bruce.campden@simplifiedc.com");
    	createPartner("peter.mcdermott@csh.com", "melissa.carleton@wcspa.com");
    	createPartner("janet.periman@digitalmarketingllp.com", "susan@referralwiretest.biz");
    	createPartner("janet.periman@digitalmarketingllp.com", "jeff.stanley@stanleyconsulting.com");
    	createPartner("janet.periman@digitalmarketingllp.com", "jenny@referralwiretest.biz");
    	createPartner("janet.periman@digitalmarketingllp.com", "peter.mcdermott@csh.com");
    	createPartner("janet.periman@digitalmarketingllp.com", "lori@referralwiretest.biz");
    	createPartner("janet.periman@digitalmarketingllp.com", "valeria.santana@fusa.com");
    	createPartner("janet.periman@digitalmarketingllp.com", "phil@referralwiretest.biz");
    	createPartner("janet.periman@digitalmarketingllp.com", "bruce.campden@simplifiedc.com");
    	createPartner("janet.periman@digitalmarketingllp.com", "melissa.carleton@wcspa.com");
    	createPartner("lori@referralwiretest.biz", "susan@referralwiretest.biz");
    	createPartner("lori@referralwiretest.biz", "jeff.stanley@stanleyconsulting.com");
    	createPartner("lori@referralwiretest.biz", "jenny@referralwiretest.biz");
    	createPartner("lori@referralwiretest.biz", "peter.mcdermott@csh.com");
    	createPartner("lori@referralwiretest.biz", "janet.periman@digitalmarketingllp.com");
    	createPartner("lori@referralwiretest.biz", "valeria.santana@fusa.com");
    	createPartner("lori@referralwiretest.biz", "phil@referralwiretest.biz");
    	createPartner("lori@referralwiretest.biz", "bruce.campden@simplifiedc.com");
    	createPartner("lori@referralwiretest.biz", "melissa.carleton@wcspa.com");
    	createPartner("valeria.santana@fusa.com", "susan@referralwiretest.biz");
    	createPartner("valeria.santana@fusa.com", "jeff.stanley@stanleyconsulting.com");
    	createPartner("valeria.santana@fusa.com", "jenny@referralwiretest.biz");
    	createPartner("valeria.santana@fusa.com", "peter.mcdermott@csh.com");
    	createPartner("valeria.santana@fusa.com", "janet.periman@digitalmarketingllp.com");
    	createPartner("valeria.santana@fusa.com", "lori@referralwiretest.biz");
    	createPartner("valeria.santana@fusa.com", "phil@referralwiretest.biz");
    	createPartner("valeria.santana@fusa.com", "bruce.campden@simplifiedc.com");
    	createPartner("valeria.santana@fusa.com", "melissa.carleton@wcspa.com");
    	createPartner("phil@referralwiretest.biz", "susan@referralwiretest.biz");
    	createPartner("phil@referralwiretest.biz", "jeff.stanley@stanleyconsulting.com");
    	createPartner("phil@referralwiretest.biz", "jenny@referralwiretest.biz");
    	createPartner("phil@referralwiretest.biz", "peter.mcdermott@csh.com");
    	createPartner("phil@referralwiretest.biz", "janet.periman@digitalmarketingllp.com");
    	createPartner("phil@referralwiretest.biz", "lori@referralwiretest.biz");
    	createPartner("phil@referralwiretest.biz", "valeria.santana@fusa.com");
    	createPartner("phil@referralwiretest.biz", "bruce.campden@simplifiedc.com");
    	createPartner("phil@referralwiretest.biz", "melissa.carleton@wcspa.com");
    	createPartner("bruce.campden@simplifiedc.com", "susan@referralwiretest.biz");
    	createPartner("bruce.campden@simplifiedc.com", "jeff.stanley@stanleyconsulting.com");
    	createPartner("bruce.campden@simplifiedc.com", "jenny@referralwiretest.biz");
    	createPartner("bruce.campden@simplifiedc.com", "peter.mcdermott@csh.com");
    	createPartner("bruce.campden@simplifiedc.com", "janet.periman@digitalmarketingllp.com");
    	createPartner("bruce.campden@simplifiedc.com", "lori@referralwiretest.biz");
    	createPartner("bruce.campden@simplifiedc.com", "valeria.santana@fusa.com");
    	createPartner("bruce.campden@simplifiedc.com", "phil@referralwiretest.biz");
    	createPartner("bruce.campden@simplifiedc.com", "melissa.carleton@wcspa.com");
    	createPartner("melissa.carleton@wcspa.com", "susan@referralwiretest.biz");
    	createPartner("melissa.carleton@wcspa.com", "jeff.stanley@stanleyconsulting.com");
    	createPartner("melissa.carleton@wcspa.com", "jenny@referralwiretest.biz");
    	createPartner("melissa.carleton@wcspa.com", "peter.mcdermott@csh.com");
    	createPartner("melissa.carleton@wcspa.com", "janet.periman@digitalmarketingllp.com");
    	createPartner("melissa.carleton@wcspa.com", "lori@referralwiretest.biz");
    	createPartner("melissa.carleton@wcspa.com", "valeria.santana@fusa.com");
    	createPartner("melissa.carleton@wcspa.com", "phil@referralwiretest.biz");
    	createPartner("melissa.carleton@wcspa.com", "bruce.campden@simplifiedc.com");
		*/
      	
      	
      	HashMap stockton = new HashMap();
    	HashMap vacaville = new HashMap();
    	HashMap danville = new HashMap();
    	HashMap sanramon = new HashMap();
    	
    	stockton.put("photoUrl", pRoot + "stockton_photo.jpeg");
    	stockton.put("businessName", "Stockton Thinkers");
    	stockton.put("streetAddress1_work", "2866 W March Ln");
    	stockton.put("dateFounded", "1312651915341");
    	stockton.put("cityAddress_work", "Stockton");
    	stockton.put("stateAddress_work", "CA");
    	stockton.put("postalCodeAddress_work", "95207");
    	stockton.put("meetingDayOfWeek", "3");
    	stockton.put("meetingHour", "1357052400000");//7 a.m. PST or 3 p.m. GMT
    	stockton.put("establishmentName", "Black Bear Diner");
    	stockton.put("logoUrl", pRoot + "stockton_establishmentlogo.jpeg");
    	stockton.put("greeting", "Surround yourself with motivated business men and women dedicated to helping move your business forward. This unique networking concept is open to all professions and requires no membership fees or dues. Join us for lunch, become enriched by our dynamic speakers and support the local Stockton economy.\n\nThe Successful Thinkers Network's mission is to help you develop relationships that will enable you to find solutions to your business challenges.");
    	stockton.put("faceBookPage", "www.facebook.com");
    	stockton.put("ambassador_fullName", "Phil Winthrop");
    	//stockton.put("latitude_work", "37.983987");
    	//stockton.put("longitude_work","-121.34668");
    	
    	String stocktonAmbassador = "phil@referralwiretest.biz";
    	ArrayList stocktonMembers = new ArrayList();
    	stocktonMembers.add("janet.periman@digitalmarketingllp.com");
    	stocktonMembers.add("peter.mcdermott@csh.com");
    	//stocktonMembers.add("phil@referralwiretest.biz");
    	stocktonMembers.add("susan@referralwiretest.biz");
    	
    	vacaville.put("photoUrl", pRoot + "vacaville_photo.jpeg");
    	vacaville.put("businessName", "Vacaville Successful Thinkers on Fire");
    	vacaville.put("streetAddress1_work", "505 Davis Street");
    	vacaville.put("cityAddress_work", "Vacaville");
    	vacaville.put("stateAddress_work", "CA");
    	vacaville.put("dateFounded", "1322651915341"); //7 a.m. PST or 3 p.m. GMT
    	vacaville.put("postalCodeAddress_work", "95688");
    	vacaville.put("meetingDayOfWeek", "5");
    	vacaville.put("meetingHour", "1357052400000");
    	vacaville.put("establishmentName", "Country Waffles");
    	vacaville.put("logoUrl", pRoot + "vacaville_establishmentlogo.gif");
    	vacaville.put("greeting", "ST – Vacaville is a high impact meeting for small businesses and entrepreneurs who want to grow their businesses. Each week over breakfast we invite top business speakers to share their success tips to help the small business entrepreneurs move their businesses forward. After our speakers we encourage business owners to meet with each other in one on one’s or fireside chats to learn about each other and get to know each other on a more personal level. ");
    	vacaville.put("faceBookPage", "www.facebook.com");
    	vacaville.put("ambassador_fullName", "Jeff Stanley");
    	//vacaville.put("latitude_work", "38.353503");
    	//vacaville.put("longitude_work","-121.985839");

    	String vacavilleAmbassador = "jeff.stanley@stanleyconsulting.com";
    	
    	ArrayList vacavilleMembers = new ArrayList();
    	vacavilleMembers.add("jeff.stanley@stanleyconsulting.com");
    	vacavilleMembers.add("bruce.campden@simplifiedc.com");
    	
    	
    	CreatePublicOrg(stockton,stocktonAmbassador,stocktonMembers);
    	CreatePublicOrg(vacaville,vacavilleAmbassador,vacavilleMembers);
    	System.out.println("Created Chapters");
    	
    	ArrayList Invitees = new ArrayList<String[]>();
    	
    	Invitees.add("susan@referralwiretest.biz:admin.user@referralwire.com");//phil invited by admin
    	Invitees.add("jeff.stanley@stanleyconsulting.com:susan@referralwiretest.biz");//valeria invited by phil
    	Invitees.add("jenny@referralwiretest.biz:susan@referralwiretest.biz");   	
    	Invitees.add("peter.mcdermott@csh.com:jeff.stanley@stanleyconsulting.com");
    	Invitees.add("janet.periman@digitalmarketingllp.com:valeria.santana@fusa.com");
    	Invitees.add("lori@referralwiretest.biz:valeria.santana@fusa.com");
    	Invitees.add("valeria.santana@fusa.com:phil@referralwiretest.biz");
    	Invitees.add("phil@referralwiretest.biz:susan@referralwiretest.biz");
    	Invitees.add("bruce.campden@simplifiedc.com:valeria.santana@fusa.com");
    	Invitees.add("melissa.carleton@wcspa.com:phil@referralwiretest.biz");
    	
    	SetInvitees(Invitees);
    	System.out.println("Set Invitees");
      	
      	createInvitation("phil@referralwiretest.biz", "janet.periman@digitalmarketingllp.com", "member", "ACCEPTED", memberNames);
      	createP2Preferral("phil@referralwiretest.biz", "bruce.campden@simplifiedc.com", "janet.periman@digitalmarketingllp.com","member","member","ACCEPTED","",memberNames);
      	createP2Preferral("valeria.santana@fusa.com", "phil@referralwiretest.biz", "jenny@referralwiretest.biz","member","member","ACCEPTED","ACCEPTED",memberNames);
      	createP2Preferral("bruce.campden@simplifiedc.com", "jeff.stanley@stanleyconsulting.com", "janet.periman@digitalmarketingllp.com","member","member","ACCEPTED","ACCEPTED",memberNames);
      	createInvitation("peter.mcdermott@csh.com", "bruce.campden@simplifiedc.com", "member", "ACCEPTED", memberNames);
      	createInvitation("valeria.santana@fusa.com", "janet.periman@digitalmarketingllp.com", "member", "ACCEPTED", memberNames);
      	createP2Preferral("jeff.stanley@stanleyconsulting.com", "phil@referralwiretest.biz", "susan@referralwiretest.biz","member","member","WAITING","phil@referralwiretest.biz",memberNames);
      	createInvitation("susan@referralwiretest.biz", "peter.mcdermott@csh.com", "member", "ACCEPTED", memberNames);
      	createInvitation("susan@referralwiretest.biz", "valeria.santana@fusa.com", "member", "ACCEPTED", memberNames);
      	createInvitation("jenny@referralwiretest.biz", "peter.mcdermott@csh.com", "member", "ACCEPTED", memberNames);
      	createInvitation("janet.periman@digitalmarketingllp.com", "jenny@referralwiretest.biz", "member", "UNREAD", memberNames);
      	createInvitation("peter.mcdermott@csh.com", "phil@referralwiretest.biz", "member", "UNREAD", memberNames);
      	createP2Preferral("valeria.santana@fusa.com", "jenny@referralwiretest.biz", "melissa.carleton@wcspa.com","member","member","WAITING","jenny@referralwiretest.biz",memberNames);
      	createInvitation("susan@referralwiretest.biz", "janet.periman@digitalmarketingllp.com", "member", "UNREAD", memberNames);
      	createInvitation("jenny@referralwiretest.biz", "jeff.stanley@stanleyconsulting.com", "member", "UNREAD", memberNames);

      	System.out.println("Created Invitations and P2P Referrals");

      	
    	HashMap susan_jeff = new HashMap();
    	HashMap susan_valeria = new HashMap();
    	HashMap susan_melissa = new HashMap();
    	HashMap jeff_jenny = new HashMap();
    	HashMap jeff_janet = new HashMap();
    	HashMap jeff_phillip = new HashMap();
    	HashMap jenny_peter = new HashMap();
    	HashMap jenny_jeff = new HashMap();
    	HashMap jenny_janet = new HashMap();
    	HashMap jenny_phillip = new HashMap();
    	HashMap peter_susan = new HashMap();
    	HashMap peter_melissa = new HashMap();
    	HashMap peter_bruce = new HashMap();
    	HashMap janet_valeria = new HashMap();
    	HashMap janet_phillip = new HashMap();
    	HashMap janet_bruce = new HashMap();
    	HashMap janet_jeff = new HashMap();
    	HashMap valeria_phillip = new HashMap();
    	HashMap valeria_bruce = new HashMap();
    	HashMap valeria_janet = new HashMap();
    	HashMap valeria_jenny = new HashMap();
    	HashMap phillip_Valeria = new HashMap();
    	HashMap phillip_jenny = new HashMap();
    	HashMap phillip_jeff = new HashMap();
    	HashMap bruce_phillip = new HashMap();
    	HashMap bruce_janet = new HashMap();
    	HashMap bruce_peter = new HashMap();
    	HashMap bruce_jeff = new HashMap();
    	
    	
    	susan_jeff.put("firstName","Frank");
    	susan_jeff.put("comments","The Barrymore development company is looking to landscape some of their facilities in San Ramon");
    	susan_jeff.put("answer1","They’re preparing the facility for a sale");
    	susan_jeff.put("answer2","Commercial");
    	susan_jeff.put("answer3","2 acres");
    	susan_jeff.put("answer4","");
    	susan_jeff.put("status","CONFIRMED");
    	susan_jeff.put("source_comments","Hey Jeff - I just wanted to let you know that I had a really good phone call with Frank. We're getting together next week for lunch.");
    	susan_jeff.put("CFB_Answer2", "No");

    	susan_valeria.put("firstName","Karen");
    	susan_valeria.put("comments","Karen and her husband just bought a fixer upper in Lafayette and expressed some interest in working on the yard.");
    	susan_valeria.put("answer1","Newly purchased house.");
    	susan_valeria.put("answer2","Residential");
    	susan_valeria.put("answer3",".25 acres");
    	susan_valeria.put("answer4","");
    	susan_valeria.put("status","NOSALE");
    	susan_valeria.put("noSaleReason","NOTINMARKET");
    	susan_valeria.put("source_comments","Hey Val -- sounds like Karen's going to hold off on any major renovations for the next year");

    	susan_melissa.put("firstName","Sheila");
    	susan_melissa.put("comments","My client wants to improve the feng shui of her home");
    	susan_melissa.put("answer1","Spiritual cleansing");
    	susan_melissa.put("answer2","Residential");
    	susan_melissa.put("answer3",".15 acres");
    	susan_melissa.put("answer4","");
    	susan_melissa.put("status","UNREAD");

    	jeff_jenny.put("firstName","Greg");
    	jeff_jenny.put("comments","Greg is trying to decide whether and how to sell his business");
    	jeff_jenny.put("answer1","Coventry Electrical Systems");
    	jeff_jenny.put("answer2","Greg’s is ready to retire - I figured this would be right up your alley. ");
    	jeff_jenny.put("answer3","");
    	jeff_jenny.put("answer4","");
    	jeff_jenny.put("status","UNREAD");
    	jeff_jenny.put("status","NOSALE");
    	jeff_jenny.put("noSaleReason","DUPLICATE");
    	jeff_jenny.put("source_comments","Thanks much Jenny -- I talked to Greg about this last year.");

    	jeff_janet.put("firstName","Howard");
    	jeff_janet.put("comments","Howard has a jewelry store that’s had a tough year.  I’m helping them update their marketing, but I believe they could us some management guidance as well. ");
    	jeff_janet.put("answer1","Finer Things Jewelry");
    	jeff_janet.put("answer2","I know you have expertise in team building.");
    	jeff_janet.put("answer3","");
    	jeff_janet.put("answer4","");
    	jeff_janet.put("status","ACCEPTED");

    	jeff_phillip.put("firstName","Carl");
    	jeff_phillip.put("comments","Carl’s Dad left him a business, but he has little experience with management");
    	jeff_phillip.put("answer1","Draper Leasing");
    	jeff_phillip.put("answer2","The company is very profitable, but needs to develop its bench to stay that way.");
    	jeff_phillip.put("answer3","");
    	jeff_phillip.put("answer4","");
    	jeff_phillip.put("status","CONFIRMED");
    	jeff_phillip.put("source_comments","Hey Jeff - I just wanted to let you know that I had a really good phone call with Carl. We're getting together next week for lunch.");
    	jeff_phillip.put("CFB_Answer1", "Yes");

    	jenny_peter.put("firstName","Cathy");
    	jenny_peter.put("comments","My sister in law may have to declare bankruptcy");
    	jenny_peter.put("answer1","Consumer");
    	jenny_peter.put("answer2","What the best way to get relief from creditors");
    	jenny_peter.put("answer3","No");
    	jenny_peter.put("answer4","");
    	jenny_peter.put("status","UNREAD");

    	jenny_jeff.put("firstName","Brian");
    	jenny_jeff.put("comments","I've advised Brian and his team to convert from an Limited Partnership to an SCorp.");
    	jenny_jeff.put("answer1","Business");
    	jenny_jeff.put("answer2","What are the legal pro’s and con’s to the SCorp structure versus LLP");
    	jenny_jeff.put("answer3","No");
    	jenny_jeff.put("answer4","");
    	jenny_jeff.put("status","ACCEPTED");

    	jenny_janet.put("firstName","Gary");
    	jenny_janet.put("comments","Gary has a number of copyright questions he’s looking for help with");
    	jenny_janet.put("answer1","Business");
    	jenny_janet.put("answer2","Which parts of Burnham Finishing’s brand are copyrightable");
    	jenny_janet.put("answer3","Yes");
    	jenny_janet.put("answer4","");
    	jenny_janet.put("status","CONFIRMED");
    	jenny_janet.put("source_comments","Thanks Janet, I had a brief chat with Gary yesterday");
    	jenny_janet.put("CFB_Answer2", "No");

    	jenny_phillip.put("firstName","Susan");
    	jenny_phillip.put("comments","I have a couple who has been contacted by the IRS for tax evasion. I working with them to compile a response.  They'll likely need some additional help. ");
    	jenny_phillip.put("answer1","Consumer");
    	jenny_phillip.put("answer2","Citizenship status for tax liability purposes");
    	jenny_phillip.put("answer3","No");
    	jenny_phillip.put("answer4","");
    	jenny_phillip.put("status","UNREAD");

    	peter_susan.put("firstName","Steve");
    	peter_susan.put("comments","Steve is an acquaintance who hurt his back on the job");
    	peter_susan.put("answer1","Lower back stiffness");
    	peter_susan.put("answer2","Yes, about 3 weeks ago");
    	peter_susan.put("answer3","");
    	peter_susan.put("answer4","");
    	peter_susan.put("status","CONFIRMED");
    	peter_susan.put("source_comments","Thank Susan, he's scheduled for a free appointment next Tuesday");
    	peter_susan.put("CFB_Answer1", "Yes");

    	peter_melissa.put("firstName","Carol");
    	peter_melissa.put("comments","Carol is one of my massage therapy clients.  She appears to have some alignment issues in her neck.");
    	peter_melissa.put("answer1","Shooting pain in her arms");
    	peter_melissa.put("answer2","No");
    	peter_melissa.put("answer3","");
    	peter_melissa.put("answer4","");
    	peter_melissa.put("status","ACCEPTED");

    	peter_bruce.put("firstName","Betty");
    	peter_bruce.put("comments","Betty seems to have some carpal tunnel syndrome issues");
    	peter_bruce.put("answer1","Persistence soreness in her back");
    	peter_bruce.put("answer2","No");
    	peter_bruce.put("answer3","");
    	peter_bruce.put("answer4","");
    	peter_bruce.put("status","CONFIRMED");
    	peter_bruce.put("source_comments","Hey Bruce, I just talked to Betty");
    	peter_bruce.put("CFB_Answer1", "Yes");

    	janet_valeria.put("firstName","Stan");
    	janet_valeria.put("comments","Stan has an automotive services business.  He's looking for a way to get some more traffic");
    	janet_valeria.put("answer1","A1 Honda Service");
    	janet_valeria.put("answer2","Walnut Creek");
    	janet_valeria.put("answer3","Owner");
    	janet_valeria.put("answer4","");
    	janet_valeria.put("status","UNREAD");

    	janet_phillip.put("firstName","Cindy");
    	janet_phillip.put("comments","Cindy has been using the yellow pages for years, but has seen a slow down");
    	janet_phillip.put("answer1","Burnhardts Upholstery");
    	janet_phillip.put("answer2","Concord");
    	janet_phillip.put("answer3","General Manager");
    	janet_phillip.put("answer4","");
    	janet_phillip.put("status","ACCEPTED");
    	

    	janet_bruce.put("firstName","Ming");
    	janet_bruce.put("comments","Ming’s restaurant has had some mixed reviews on Yelp.  He’d like to take more control of his online image.");
    	janet_bruce.put("answer1","China Grove Restaurant");
    	janet_bruce.put("answer2","Brentwood");
    	janet_bruce.put("answer3","General Manager");
    	janet_bruce.put("answer4","");
    	janet_bruce.put("status","UNREAD");

    	janet_jeff.put("firstName","Luke");
    	janet_jeff.put("comments","Luke the head of Marketing.  He’s trying to do more Search Optimization and fewer Google Ads");
    	janet_jeff.put("answer1","UCS Bank");
    	janet_jeff.put("answer2","Walnut Creek");
    	janet_jeff.put("answer3","VP of Marketing");
    	janet_jeff.put("answer4","");
    	janet_jeff.put("status","CONVERTED");
    	janet_jeff.put("source_comments","Thanks Jeff -- Luke's hired my firm to manage their next marketing campaign");
    	janet_jeff.put("serviceProvided","Online Marketing Outsourcing");
    	janet_jeff.put("leadRevenue","2500");

    	valeria_phillip.put("firstName","Mary");
    	valeria_phillip.put("comments","I’ve advised Mary to move some of her assets into a sweep account");
    	valeria_phillip.put("answer1","Yes, BofA");
    	valeria_phillip.put("answer2","Mary’s 75 and needs someone to walk her through it");
    	valeria_phillip.put("answer3","");
    	valeria_phillip.put("answer4","");
    	valeria_phillip.put("status","NOSALE");
    	valeria_phillip.put("noSaleReason","NOTAFIT");
    	valeria_phillip.put("source_comments","Thanks Phil!  I'm now focusing exclusively on portfolios with $100K or more, so I'm not the right rousrce for Mary, but I've referred her to someone I think would be perfect.  Thanks again!");
    	

    	valeria_bruce.put("firstName","Gordon");
    	valeria_bruce.put("comments","Gordon’s an east coast transplant that needs to get set up.  I told him you’d give him a good package. ");
    	valeria_bruce.put("answer1","No");
    	valeria_bruce.put("answer2","Checking, Saving, Money Market");
    	valeria_bruce.put("answer3","He’s a busy guy and needs some hand-holding");
    	valeria_bruce.put("answer4","");
    	valeria_bruce.put("source_comment", "Hey Bruce, Gordon came in to see me today. Thanks for the introduction!");
    	valeria_bruce.put("status","CONFIRMED");

    	valeria_janet.put("firstName","Ray");
    	valeria_janet.put("comments","Ray needs a new account set up for handling ecommerce");
    	valeria_janet.put("answer1","Yes, Wells");
    	valeria_janet.put("answer2","Merchant Account");
    	valeria_janet.put("answer3","I know First USA has some new eCommerce-friendly merchant account features");
    	valeria_janet.put("answer4","");
    	valeria_janet.put("status","UNREAD");

    	valeria_jenny.put("firstName","Bob");
    	valeria_jenny.put("comments","Bob is interested in a reverse mortgage");
    	valeria_jenny.put("answer1","");
    	valeria_jenny.put("answer2","Reverse Mortgage");
    	valeria_jenny.put("answer3","The presentation on Reverse Mortgages you have two months ago");
    	valeria_jenny.put("answer4","");
    	valeria_jenny.put("status","ACCEPTED");

    	phillip_Valeria.put("firstName","George");
    	phillip_Valeria.put("comments","George is looking for help on his taxes this year.");
    	phillip_Valeria.put("answer1","Personal");
    	phillip_Valeria.put("answer2","NA");
    	phillip_Valeria.put("answer3","You’re an experienced CPA who used to work for the IRS");
    	phillip_Valeria.put("answer4","");
    	phillip_Valeria.put("status","UNREAD");
    	

    	phillip_jenny.put("firstName","Rachel");
    	phillip_jenny.put("comments","I set Rachel up with an estate trust, but I've recommended she talk to you about the tax implications of funding it. ");
    	phillip_jenny.put("answer1","Personal");
    	phillip_jenny.put("answer2","NA");
    	phillip_jenny.put("answer3","You’re an expert in tax planning");
    	phillip_jenny.put("answer4","");
    	phillip_jenny.put("status","UNREAD");

    	phillip_jeff.put("firstName","Frank");
    	phillip_jeff.put("comments","The Barrymore development company will likely face some signicant capital gains this year. They need some guidance on how to spread out their liability");
    	phillip_jeff.put("answer1","Business");
    	phillip_jeff.put("answer2","$40M Annual Revenue");
    	phillip_jeff.put("answer3","You’re a well rounded expert in tax planning");
    	phillip_jeff.put("answer4","");
    	phillip_jeff.put("status","ACCEPTED");

    	bruce_phillip.put("firstName","Michael");
    	bruce_phillip.put("comments","Michael’s still tracking all his accounts on paper.  He needs some help to modernize");
    	bruce_phillip.put("answer1","Office");
    	bruce_phillip.put("answer2","Basic desktop productivity and small network setup");
    	bruce_phillip.put("answer3","Either :-)");
    	bruce_phillip.put("answer4","");
    	bruce_phillip.put("status","CONVERTED");
    	bruce_phillip.put("source_comments","Thanks Phil! Michael's put our firm on retainer to help them update and manage their systems");
    	bruce_phillip.put("serviceProvided","IT Consulting Retainer");
    	bruce_phillip.put("leadRevenue","3000");
    	

    	bruce_janet.put("firstName","Cheryl");
    	bruce_janet.put("comments","Cheryl and her team need help synchronizing their phones and tablets with their business email");
    	bruce_janet.put("answer1","Office");
    	bruce_janet.put("answer2","Exchange setup");
    	bruce_janet.put("answer3","PC");
    	bruce_janet.put("answer4","");
    	bruce_janet.put("status","UNREAD");

    	bruce_peter.put("firstName","Kerri");
    	bruce_peter.put("comments","Kerri’s has problems with viruses on her home PC");
    	bruce_peter.put("answer1","Home");
    	bruce_peter.put("answer2","Virus Removal");
    	bruce_peter.put("answer3","PC");
    	bruce_peter.put("answer4","");
    	bruce_peter.put("status","CONFIRMED");
    	bruce_peter.put("source_comments","Cheers Bruce - I've scheduled a call w/ her tomorrow.");
    	bruce_peter.put("CFB_Answer1", "Yes");

    	bruce_jeff.put("firstName","Ryan");
    	bruce_jeff.put("comments","Ryan’s company needs to outsource IT services");
    	bruce_jeff.put("answer1","");
    	bruce_jeff.put("answer2","Full service IT outsourcing");
    	bruce_jeff.put("answer3","Mac");
    	bruce_jeff.put("answer4","");
    	bruce_jeff.put("status","ACCEPTED");

    	CreateReferral("jeff.stanley@stanleyconsulting.com", "susan@referralwiretest.biz", susan_jeff,2,susan);
    	CreateReferral("valeria.santana@fusa.com", "susan@referralwiretest.biz", susan_valeria,3,susan);
    	CreateReferral("melissa.carleton@wcspa.com", "susan@referralwiretest.biz", susan_melissa,2,susan);
    	CreateReferral("jenny@referralwiretest.biz", "jeff.stanley@stanleyconsulting.com", jeff_jenny,4,jeff);
    	CreateReferral("janet.periman@digitalmarketingllp.com", "jeff.stanley@stanleyconsulting.com", jeff_janet,1,jeff);
    	CreateReferral("phil@referralwiretest.biz", "jeff.stanley@stanleyconsulting.com", jeff_phillip,5,jeff);
    	CreateReferral("peter.mcdermott@csh.com", "jenny@referralwiretest.biz", jenny_peter,4,jenny);
    	CreateReferral("jeff.stanley@stanleyconsulting.com", "jenny@referralwiretest.biz", jenny_jeff,6,jenny);
    	CreateReferral("janet.periman@digitalmarketingllp.com", "jenny@referralwiretest.biz", jenny_janet,2,jenny);
    	CreateReferral("phil@referralwiretest.biz", "jenny@referralwiretest.biz", jenny_phillip,3,jenny);
    	CreateReferral("susan@referralwiretest.biz", "peter.mcdermott@csh.com", peter_susan,2,peter);
    	CreateReferral("melissa.carleton@wcspa.com", "peter.mcdermott@csh.com", peter_melissa,6,peter);
    	CreateReferral("bruce.campden@simplifiedc.com", "peter.mcdermott@csh.com", peter_bruce,1,peter);
    	CreateReferral("valeria.santana@fusa.com", "janet.periman@digitalmarketingllp.com", janet_valeria,4,janet);
    	CreateReferral("phil@referralwiretest.biz", "janet.periman@digitalmarketingllp.com", janet_phillip,3,janet);
    	CreateReferral("bruce.campden@simplifiedc.com", "janet.periman@digitalmarketingllp.com", janet_bruce,5,janet);
    	CreateReferral("jeff.stanley@stanleyconsulting.com", "janet.periman@digitalmarketingllp.com", janet_jeff,3,janet);
    	CreateReferral("phil@referralwiretest.biz", "valeria.santana@fusa.com", valeria_phillip,9,valeria);
    	CreateReferral("bruce.campden@simplifiedc.com", "valeria.santana@fusa.com", valeria_bruce,4,valeria);
    	CreateReferral("janet.periman@digitalmarketingllp.com", "valeria.santana@fusa.com", valeria_janet,1,valeria);
    	CreateReferral("jenny@referralwiretest.biz", "valeria.santana@fusa.com", valeria_jenny,2,valeria);
    	CreateReferral("valeria.santana@fusa.com", "phil@referralwiretest.biz", phillip_Valeria,5,phil);
    	CreateReferral("jenny@referralwiretest.biz", "phil@referralwiretest.biz", phillip_jenny,7,phil);
    	CreateReferral("jeff.stanley@stanleyconsulting.com", "phil@referralwiretest.biz", phillip_jeff,5,phil);
    	CreateReferral("phil@referralwiretest.biz", "bruce.campden@simplifiedc.com", bruce_phillip,7,bruce);
    	CreateReferral("janet.periman@digitalmarketingllp.com", "bruce.campden@simplifiedc.com", bruce_janet,3,bruce);
    	CreateReferral("peter.mcdermott@csh.com", "bruce.campden@simplifiedc.com", bruce_peter,2,bruce);
    	CreateReferral("jeff.stanley@stanleyconsulting.com", "bruce.campden@simplifiedc.com", bruce_jeff,7,bruce);
    	
    	System.out.println("Created Prospect Referrals");
    	
      	HashMap raja = new HashMap(); //Contact for Phil
      	
		raja.put("userid", philId);
		raja.put("firstName", "Raja");
		raja.put("lastName", "Singh");
		raja.put("jobTitle", "Founder and CEO");
		raja.put("business", "Red House Studios");
		raja.put("mobilePhone", "(925) 938-6900");
		raja.put("emailAddress", "raja@referralwiretest.biz");
		raja.put("streetAddress1", "1667 Botelho Dr.");
		raja.put("cityAddress", "Walnut Creek");
		raja.put("postalCodeAddress_work", "CA");
		raja.put("postalCodeAddress", "94596");
      	
		log.trace("philid  = " + philId);
		createUserForPartner(raja);
		
      	HashMap joe = new HashMap(); //Contact for Phil
      	
      	joe.put("userid", philId);
      	joe.put("firstName", "Joe");
      	joe.put("lastName", "Davidson");
      	joe.put("jobTitle", "Director of Sales");
      	joe.put("business", "Digits Media");
      	joe.put("mobilePhone", "(925) 938-6412");
      	joe.put("emailAddress", "joe@referralwiretest.biz");
      	joe.put("streetAddress1", "201 Main St.");
      	joe.put("cityAddress", "Walnut Creek");
      	joe.put("postalCodeAddress_work", "CA");
      	joe.put("postalCodeAddress", "94596");
      	
		log.trace("philid  = " + philId);
		createUserForPartner(joe);
    	
    	
    	
    	/*
       	RunReferralFlow("john.green@yahoo.com", "adam.sandler@yahoo.com");
       	RunReferralFlow("john.green@yahoo.com", "richard.gere@yahoo.com");
       	RunReferralFlow("john.green@yahoo.com", "nicole.kidman@yahoo.com");

       	RunAssociationFlow(AssocId, "adam.sandler@yahoo.com");
       	RunAssociationFlow(AssocId, "richard.gere@yahoo.com");
       	RunAssociationFlow(AssocId, "nicole.kidman@yahoo.com");
       	RunAssociationFlow(AssocId, "john.green@yahoo.com");
      	
       	AssocId = CreatePublicAssoc("B");
       	RunAssociationFlow(AssocId, "adam.sandler@yahoo.com");
       	RunAssociationFlow(AssocId, "richard.gere@yahoo.com");
       	RunAssociationFlow(AssocId, "nicole.kidman@yahoo.com");
       	RunAssociationFlow(AssocId, "john.green@yahoo.com");

       	AssocId = CreatePublicAssoc("C");
       	RunAssociationFlow(AssocId, "adam.sandler@yahoo.com");
       	RunAssociationFlow(AssocId, "richard.gere@yahoo.com");
       	RunAssociationFlow(AssocId, "nicole.kidman@yahoo.com");
       	RunAssociationFlow(AssocId, "john.green@yahoo.com");  	
       	*/
       	
    	mongoStore s = new mongoStore();
    	//db.rwReferral.update( {subject: "past" }, { $set: { rw_created_on:new ISODate("2012-01-10") } }, { multi: true } )
		BasicDBObject find = new BasicDBObject();
    	find.put("subject","past");

    	BasicDBObject update = new BasicDBObject();
    	long period = 40160;//40160 minutes in the past ~ 4 weeks ago
    	Date past = new Date(System.currentTimeMillis()  -  period * 60000);
    	
    	BasicDBObject rwc =  new BasicDBObject();
    	rwc.put("rw_created_on", past);
    	update.put("$set",rwc);
    	
    	//update.put("rw_created_on", past);
    	s.getColl("rwReferral").updateMulti(find, update);
    	
    	
    	HashMap prospectRC = new HashMap(); //prospect recommendation category
    	ArrayList prospectRP = new ArrayList(); //prospect recommendation pre-reqs
    	ArrayList prospectMyRP = new ArrayList(); //MY prospect recommendation pre-reqs
    	HashMap partnerRC = new HashMap(); //partner recommendation category
    	ArrayList partnerRP = new ArrayList(); //power partner recommendation pre-reqs
    	HashMap resourceRC = new HashMap(); //resource recommendation category
    	ArrayList resourceRP = new ArrayList(); //service provider recommendation pre-reqs
    	ArrayList resourceQ = new ArrayList(); //service provider recommendation qualifications
    	HashMap exclusiveCriteria = new HashMap();
    	ArrayList nonExclusiveCriteria = new ArrayList();
    	HashMap dependentCriteria = new HashMap();


    	prospectRC.put("RR_1",new Integer(5));
    	prospectRC.put("RR_2",new Integer(8));
    	prospectRC.put("RR_3",new Integer(10));
    	prospectRC.put("RR_4",new Integer(4));
    	prospectRC.put("RR_5",new Integer(10));
    	prospectRC.put("RR_6",new Integer(4));
    	partnerRC.put("RR_7",new Integer(1));
    	partnerRC.put("RR_8",new Integer(5));
    	partnerRC.put("RR_13",new Integer(5));
    	partnerRC.put("RR_9",new Integer(2));
    	partnerRC.put("RR_10",new Integer(5));
    	resourceRC.put("RR_1",new Integer(5));
    	resourceRC.put("RR_2",new Integer(8));
    	resourceRC.put("RR_12",new Integer(10));
    	resourceRC.put("RR_4",new Integer(4));
    	resourceRC.put("RR_5",new Integer(10));
    	resourceRC.put("RR_6",new Integer(4));
    	
    	prospectRP.add("PR_1");
    	prospectRP.add("PR_2");
    	prospectRP.add("PR_3");
    	
    	partnerRP.add("PR_1");
    	partnerRP.add("PR_2");
    	partnerRP.add("PR_5");
    	
    	resourceRP.add("PR_4");
    	resourceRP.add("PR_2");
    	
    	resourceQ.add("RR_7");
    	resourceQ.add("RR_11");
    	/*
    	CreateRecommendations(prospectRC,prospectRP,null,"CUST_FOR_PART");
    	CreateRecommendations(partnerRC,partnerRP,null,"PART_FOR_PART");
    	CreateRecommendations(resourceRC,resourceRP,resourceQ,"PART_FOR_CUST");
    	*/
    	prospectMyRP.add("PR_1");
    	prospectMyRP.add("PR_3");
    	
    	/*CreateMyRecommendations(prospectRC,prospectMyRP,null,"CUST_FOR_USER");
    	
    	nonExclusiveCriteria = addNonEx("CC_Location_1",nonExclusiveCriteria);
    	dependentCriteria = addDependent("CC_Location_1","CC_Location_2",dependentCriteria);
    	exclusiveCriteria = addExclusive("2 Household Adult Age","CC_Household_adult_age_3",exclusiveCriteria);
    	exclusiveCriteria = addExclusive("2 Household Adult Age","CC_Household_adult_age_4",exclusiveCriteria);
    	exclusiveCriteria = addExclusive("2 Household Adult Age","CC_Household_adult_age_5",exclusiveCriteria);
    	exclusiveCriteria = addExclusive("2 Household Adult Age","CC_Household_adult_age_6",exclusiveCriteria);
    	exclusiveCriteria = addExclusive("2 Household Adult Age","CC_Household_adult_age_7",exclusiveCriteria);
    	exclusiveCriteria = addExclusive("2 Household Adult Age","CC_Household_adult_age_8",exclusiveCriteria);
    	exclusiveCriteria = addExclusive("2 Household Adult Age","CC_Household_adult_age_9",exclusiveCriteria);
    	exclusiveCriteria = addExclusive("3 Household Composition","CC_Household_composition_10",exclusiveCriteria);
    	nonExclusiveCriteria = addNonEx("CC_Household_composition_11",nonExclusiveCriteria);
    	exclusiveCriteria = addExclusive("3 Household Composition","CC_Household_composition_12",exclusiveCriteria);
    	exclusiveCriteria = addExclusive("3 Household Composition","CC_Household_composition_13",exclusiveCriteria);
    	exclusiveCriteria = addExclusive("3 Household Composition","CC_Household_composition_14",exclusiveCriteria);
    	exclusiveCriteria = addExclusive("3 Household Composition","CC_Household_composition_15",exclusiveCriteria);
    	dependentCriteria = addDependent("CC_Household_composition_15","CC_Household_composition_16",dependentCriteria);
    	nonExclusiveCriteria = addNonEx("CC_Household_composition_17",nonExclusiveCriteria);
    	nonExclusiveCriteria = addNonEx("CC_Household_composition_18",nonExclusiveCriteria);
    	nonExclusiveCriteria = addNonEx("CC_Household_composition_19",nonExclusiveCriteria);
    	nonExclusiveCriteria = addNonEx("CC_Estimated_Income_20",nonExclusiveCriteria);
    	exclusiveCriteria = addExclusive("4 Estimated Income","CC_Estimated_Income_21",exclusiveCriteria);
    	exclusiveCriteria = addExclusive("4 Estimated Income","CC_Estimated_Income_22",exclusiveCriteria);
    	exclusiveCriteria = addExclusive("4 Estimated Income","CC_Estimated_Income_23",exclusiveCriteria);
    	exclusiveCriteria = addExclusive("4 Estimated Income","CC_Estimated_Income_24",exclusiveCriteria);
    	exclusiveCriteria = addExclusive("5 Real Estate: Ownership","CC_Real_Estate_Ownership_25",exclusiveCriteria);
    	exclusiveCriteria = addExclusive("5 Real Estate: Ownership","CC_Real_Estate_Ownership_26",exclusiveCriteria);
    	exclusiveCriteria = addExclusive("6 Real Estate: Value","CC_Real_Estate_Value_27",exclusiveCriteria);
    	exclusiveCriteria = addExclusive("6 Real Estate: Value","CC_Real_Estate_Value_28",exclusiveCriteria);
    	exclusiveCriteria = addExclusive("6 Real Estate: Value","CC_Real_Estate_Value_29",exclusiveCriteria);
    	exclusiveCriteria = addExclusive("6 Real Estate: Value","CC_Real_Estate_Value_30",exclusiveCriteria);
    	exclusiveCriteria = addExclusive("6 Real Estate: Value","CC_Real_Estate_Value_31",exclusiveCriteria);
    	exclusiveCriteria = addExclusive("6 Real Estate: Value","CC_Real_Estate_Value_32",exclusiveCriteria);
    	exclusiveCriteria = addExclusive("6 Real Estate: Value","CC_Real_Estate_Value_33",exclusiveCriteria);
    	exclusiveCriteria = addExclusive("6 Real Estate: Value","CC_Real_Estate_Value_34",exclusiveCriteria);
    	exclusiveCriteria = addExclusive("7 Real Estate: Year built","CC_Real_Estate_Year_built_35",exclusiveCriteria);
    	exclusiveCriteria = addExclusive("7 Real Estate: Year built","CC_Real_Estate_Year_built_36",exclusiveCriteria);
    	exclusiveCriteria = addExclusive("7 Real Estate: Year built","CC_Real_Estate_Year_built_37",exclusiveCriteria);
    	exclusiveCriteria = addExclusive("7 Real Estate: Year built","CC_Real_Estate_Year_built_38",exclusiveCriteria);
    	exclusiveCriteria = addExclusive("7 Real Estate: Year built","CC_Real_Estate_Year_built_39",exclusiveCriteria);
    	exclusiveCriteria = addExclusive("7 Real Estate: Year built","CC_Real_Estate_Year_built_40",exclusiveCriteria);
    	exclusiveCriteria = addExclusive("8 Vehicle","CC_Vehicle_41",exclusiveCriteria);
    	exclusiveCriteria = addExclusive("8 Vehicle","CC_Vehicle_42",exclusiveCriteria);
    	exclusiveCriteria = addExclusive("8 Vehicle","CC_Vehicle_43",exclusiveCriteria);
    	exclusiveCriteria = addExclusive("9 Donor Capacity","CC_Donor_Capacity_44",exclusiveCriteria);
    	exclusiveCriteria = addExclusive("9 Donor Capacity","CC_Donor_Capacity_45",exclusiveCriteria);
    	exclusiveCriteria = addExclusive("9 Donor Capacity","CC_Donor_Capacity_46",exclusiveCriteria);
    	exclusiveCriteria = addExclusive("9 Donor Capacity","CC_Donor_Capacity_47",exclusiveCriteria);
    	exclusiveCriteria = addExclusive("10 Consumer Event","CE_Household_event_48",exclusiveCriteria);
    	nonExclusiveCriteria = addNonEx("CE_Household_event_49",nonExclusiveCriteria);
    	nonExclusiveCriteria = addNonEx("CE_Household_event_50",nonExclusiveCriteria);
    	dependentCriteria = addDependent("CC_Household_composition_14","CE_Household_event_51",dependentCriteria);
    	nonExclusiveCriteria = addNonEx("CE_Household_event_52",nonExclusiveCriteria);
    	dependentCriteria = addDependent("CC_Household_composition_16","CE_Household_event_53",dependentCriteria);
    	dependentCriteria = addDependent("CC_Household_composition_12","CE_Household_event_54",dependentCriteria);
    	nonExclusiveCriteria = addNonEx("CE_Household_event_55",nonExclusiveCriteria);
    	nonExclusiveCriteria = addNonEx("CE_Household_event_56",nonExclusiveCriteria);
    	nonExclusiveCriteria = addNonEx("CE_Household_event_57",nonExclusiveCriteria);
    	nonExclusiveCriteria = addNonEx("CE_Household_event_58",nonExclusiveCriteria);
    	nonExclusiveCriteria = addNonEx("CE_Household_event_59",nonExclusiveCriteria);
    	nonExclusiveCriteria = addNonEx("CE_Household_event_60",nonExclusiveCriteria);
    	nonExclusiveCriteria = addNonEx("CE_Household_event_61",nonExclusiveCriteria);
    	nonExclusiveCriteria = addNonEx("CE_Household_event_62",nonExclusiveCriteria);
    	nonExclusiveCriteria = addNonEx("CE_Household_event_63",nonExclusiveCriteria);
    	nonExclusiveCriteria = addNonEx("CE_Household_event_64",nonExclusiveCriteria);
    	nonExclusiveCriteria = addNonEx("CE_Property_event_65",nonExclusiveCriteria);
    	nonExclusiveCriteria = addNonEx("CE_Property_event_66",nonExclusiveCriteria);
    	nonExclusiveCriteria = addNonEx("CE_Property_event_67",nonExclusiveCriteria);
    	nonExclusiveCriteria = addNonEx("CE_Property_event_68",nonExclusiveCriteria);
    	nonExclusiveCriteria = addNonEx("CE_Property_event_69",nonExclusiveCriteria);
    	nonExclusiveCriteria = addNonEx("CE_Property_event_70",nonExclusiveCriteria);
    	nonExclusiveCriteria = addNonEx("CE_Property_event_71",nonExclusiveCriteria);
    	nonExclusiveCriteria = addNonEx("CE_Property_event_72",nonExclusiveCriteria);
    	nonExclusiveCriteria = addNonEx("CE_Property_event_73",nonExclusiveCriteria);
    	nonExclusiveCriteria = addNonEx("CC_Vehicle_74",nonExclusiveCriteria);
    	nonExclusiveCriteria = addNonEx("CE_Interests_75",nonExclusiveCriteria);
    	nonExclusiveCriteria = addNonEx("CE_Interests_76",nonExclusiveCriteria);
    	nonExclusiveCriteria = addNonEx("CE_Interests_77",nonExclusiveCriteria);
    	nonExclusiveCriteria = addNonEx("CE_Interests_78",nonExclusiveCriteria);
    	nonExclusiveCriteria = addNonEx("CE_Interests_79",nonExclusiveCriteria);
    	nonExclusiveCriteria = addNonEx("CE_Interests_80",nonExclusiveCriteria);
    	nonExclusiveCriteria = addNonEx("CE_Interests_81",nonExclusiveCriteria);
    	nonExclusiveCriteria = addNonEx("CE_Interests_82",nonExclusiveCriteria);
    	nonExclusiveCriteria = addNonEx("CE_Interests_83",nonExclusiveCriteria);
    	nonExclusiveCriteria = addNonEx("CE_Interests_84",nonExclusiveCriteria);
    	nonExclusiveCriteria = addNonEx("CE_Interests_85",nonExclusiveCriteria);
    	nonExclusiveCriteria = addNonEx("CE_Interests_86",nonExclusiveCriteria);
    	nonExclusiveCriteria = addNonEx("CE_Interests_87",nonExclusiveCriteria);
    	nonExclusiveCriteria = addNonEx("CE_Interests_88",nonExclusiveCriteria);
    	nonExclusiveCriteria = addNonEx("CE_Interests_89",nonExclusiveCriteria);
    	nonExclusiveCriteria = addNonEx("CE_Interests_90",nonExclusiveCriteria);
    	nonExclusiveCriteria = addNonEx("CE_Interests_91",nonExclusiveCriteria);
    	nonExclusiveCriteria = addNonEx("CE_Interests_92",nonExclusiveCriteria);
    	nonExclusiveCriteria = addNonEx("CE_Interests_93",nonExclusiveCriteria);
    	nonExclusiveCriteria = addNonEx("CE_Interests_94",nonExclusiveCriteria);
    	nonExclusiveCriteria = addNonEx("CE_Interests_95",nonExclusiveCriteria);
    	nonExclusiveCriteria = addNonEx("CE_Interests_96",nonExclusiveCriteria);
    	nonExclusiveCriteria = addNonEx("CE_Interests_97",nonExclusiveCriteria);
    	nonExclusiveCriteria = addNonEx("CE_Interests_98",nonExclusiveCriteria);
    	nonExclusiveCriteria = addNonEx("CE_Interests_99",nonExclusiveCriteria);
    	nonExclusiveCriteria = addNonEx("CE_Interests_100",nonExclusiveCriteria);
    	nonExclusiveCriteria = addNonEx("CE_Interests_101",nonExclusiveCriteria);
    	nonExclusiveCriteria = addNonEx("CE_Interests_102",nonExclusiveCriteria);
    	nonExclusiveCriteria = addNonEx("CE_Interests_103",nonExclusiveCriteria);
    	nonExclusiveCriteria = addNonEx("CE_Interests_104",nonExclusiveCriteria);
    	nonExclusiveCriteria = addNonEx("CE_Interests_105",nonExclusiveCriteria);
    	nonExclusiveCriteria = addNonEx("CC_Real_Estate_Ownership_106",nonExclusiveCriteria);
    	nonExclusiveCriteria = addNonEx("BC_Location_107",nonExclusiveCriteria);
    	dependentCriteria = addDependent("BC_Location_107","BC_Location_108",dependentCriteria);
    	exclusiveCriteria = addExclusive("13 Occupation: Role","BC_Occupation_109",exclusiveCriteria);
    	exclusiveCriteria = addExclusive("13 Occupation: Role","BC_Occupation_110",exclusiveCriteria);
    	exclusiveCriteria = addExclusive("13 Occupation: Role","BC_Occupation_111",exclusiveCriteria);
    	exclusiveCriteria = addExclusive("13 Occupation: Role","BC_Occupation_112",exclusiveCriteria);
    	exclusiveCriteria = addExclusive("14 Occupation: Industry","BC_Industry_113",exclusiveCriteria);
    	exclusiveCriteria = addExclusive("14 Occupation: Industry","BC_Industry_114",exclusiveCriteria);
    	exclusiveCriteria = addExclusive("14 Occupation: Industry","BC_Industry_115",exclusiveCriteria);
    	exclusiveCriteria = addExclusive("14 Occupation: Industry","BC_Industry_116",exclusiveCriteria);
    	exclusiveCriteria = addExclusive("14 Occupation: Industry","BC_Industry_117",exclusiveCriteria);
    	exclusiveCriteria = addExclusive("14 Occupation: Industry","BC_Industry_118",exclusiveCriteria);
    	exclusiveCriteria = addExclusive("14 Occupation: Industry","BC_Industry_119",exclusiveCriteria);
    	exclusiveCriteria = addExclusive("14 Occupation: Industry","BC_Industry_120",exclusiveCriteria);
    	exclusiveCriteria = addExclusive("14 Occupation: Industry","BC_Industry_121",exclusiveCriteria);
    	exclusiveCriteria = addExclusive("14 Occupation: Industry","BC_Industry_122",exclusiveCriteria);
    	exclusiveCriteria = addExclusive("14 Occupation: Industry","BC_Industry_123",exclusiveCriteria);
    	exclusiveCriteria = addExclusive("14 Occupation: Industry","BC_Industry_124",exclusiveCriteria);
    	exclusiveCriteria = addExclusive("14 Occupation: Industry","BC_Industry_125",exclusiveCriteria);
    	exclusiveCriteria = addExclusive("14 Occupation: Industry","BC_Industry_126",exclusiveCriteria);
    	exclusiveCriteria = addExclusive("14 Occupation: Industry","BC_Industry_127",exclusiveCriteria);
    	exclusiveCriteria = addExclusive("14 Occupation: Industry","BC_Industry_128",exclusiveCriteria);
    	exclusiveCriteria = addExclusive("14 Occupation: Industry","BC_Industry_129",exclusiveCriteria);
    	exclusiveCriteria = addExclusive("14 Occupation: Industry","BC_Industry_130",exclusiveCriteria);
    	exclusiveCriteria = addExclusive("14 Occupation: Industry","BC_Industry_131",exclusiveCriteria);
    	exclusiveCriteria = addExclusive("14 Occupation: Industry","BC_Industry_132",exclusiveCriteria);
    	exclusiveCriteria = addExclusive("14 Occupation: Industry","BC_Industry_133",exclusiveCriteria);
    	exclusiveCriteria = addExclusive("15 Business Size","BC_Company_Size_134",exclusiveCriteria);
    	exclusiveCriteria = addExclusive("15 Business Size","BC_Company_Size_135",exclusiveCriteria);
    	exclusiveCriteria = addExclusive("15 Business Size","BC_Company_Size_136",exclusiveCriteria);
    	exclusiveCriteria = addExclusive("15 Business Size","BC_Company_Size_137",exclusiveCriteria);
    	exclusiveCriteria = addExclusive("15 Business Size","BC_Company_Size_138",exclusiveCriteria);
    	exclusiveCriteria = addExclusive("15 Business Size","BC_Company_Size_139",exclusiveCriteria);
    	exclusiveCriteria = addExclusive("15 Business Size","BC_Company_Size_140",exclusiveCriteria);
    	exclusiveCriteria = addExclusive("15 Business Size","BC_Company_Size_141",exclusiveCriteria);
    	exclusiveCriteria = addExclusive("15 Business Size","BC_Company_Size_142",exclusiveCriteria);
    	nonExclusiveCriteria = addNonEx("BE_Business_event_143",nonExclusiveCriteria);
    	nonExclusiveCriteria = addNonEx("BE_Business_event_144",nonExclusiveCriteria);
    	nonExclusiveCriteria = addNonEx("BE_Business_event_145",nonExclusiveCriteria);
    	nonExclusiveCriteria = addNonEx("BE_Business_event_146",nonExclusiveCriteria);
    	nonExclusiveCriteria = addNonEx("BE_Business_event_147",nonExclusiveCriteria);
    	nonExclusiveCriteria = addNonEx("BE_Business_event_148",nonExclusiveCriteria);
    	nonExclusiveCriteria = addNonEx("BE_Business_event_149",nonExclusiveCriteria);
    	nonExclusiveCriteria = addNonEx("BE_Business_event_150",nonExclusiveCriteria);
    	nonExclusiveCriteria = addNonEx("BE_Business_event_151",nonExclusiveCriteria);
    	nonExclusiveCriteria = addNonEx("BE_Business_event_152",nonExclusiveCriteria);
    	nonExclusiveCriteria = addNonEx("BE_Business_event_153",nonExclusiveCriteria);
    	nonExclusiveCriteria = addNonEx("BE_Interests_154",nonExclusiveCriteria);
    	nonExclusiveCriteria = addNonEx("BE_Interests_155",nonExclusiveCriteria);
    	nonExclusiveCriteria = addNonEx("BE_Interests_156",nonExclusiveCriteria);
    	nonExclusiveCriteria = addNonEx("BE_Interests_157",nonExclusiveCriteria);
    	nonExclusiveCriteria = addNonEx("BE_Interests_158",nonExclusiveCriteria);
    	nonExclusiveCriteria = addNonEx("BE_Interests_159",nonExclusiveCriteria);
    	nonExclusiveCriteria = addNonEx("BE_Interests_160",nonExclusiveCriteria);
    	nonExclusiveCriteria = addNonEx("BE_Interests_161",nonExclusiveCriteria);
    	nonExclusiveCriteria = addNonEx("BE_Interests_162",nonExclusiveCriteria);
    	nonExclusiveCriteria = addNonEx("BE_Interests_163",nonExclusiveCriteria);
    	
    	enrichParties (exclusiveCriteria, nonExclusiveCriteria,dependentCriteria,lov);
    	*/
    	
    	
    	HashMap copymap = new HashMap();
    	copymap.put("logo","logo");
    	copymap.put("businessName","business");
    	copymap.put("leader_fullName","fullName");
    	copymap.put("streetAddress1_work","streetAddress1_work");
    	copymap.put("cityAddress_work","cityAddress_work");
    	copymap.put("stateAddress_work","stateAddress_work");
    	copymap.put("postalCodeAddress_work","postalCodeAddress_work");
    	copymap.put("about","bio");
    	copymap.put("leaderId","_id");
    	copymap.put("services","specialties");
    	copymap.put("associations","accreditations");
    	copymap.put("emailAddress","emailAddress");
    	copymap.put("workPhone","workPhone");
    	copymap.put("webPage","webPage");
    	copymap.put("faceBookPage","www.yahoo.com");
    	copymap.put("meetupPage","www.yahoo.com");
    	copymap.put("linkedInPage","www.yahoo.com");
    	copymap.put("twitterPage","www.yahoo.com");
    	
    	
    	HashMap jennylernerbiz = new HashMap();
    	HashMap brucecampdenbiz = new HashMap();
    	jennylernerbiz.put("category","Legal Services");
    	jennylernerbiz.put("yearFounded","2007");
    	brucecampdenbiz.put("category","Technology Services");
    	brucecampdenbiz.put("yearFounded","Technology Services");
    	
    	
    	createBanner();
    	CreateOrgMeetings("Stockton Thinkers");
    	CreateOrgMeetings("Vacaville Successful Thinkers on Fire");
    	System.out.println("Created Meetings");
    	
    	CreateBusiness("jenny@referralwiretest.biz",jennylernerbiz,copymap);
    	CreateBusiness("bruce.campden@simplifiedc.com",brucecampdenbiz,copymap);
    	
    	
    	
    	String template = "<html><head><title>Welcome to STN Connect</title></head><body style='font-family:arial;color:#466BB'><div style='background-color:#466BB0; height:50px;padding:5px;'><img src='http://successfulthinkersnetwork.com/images/logo.png'><div><h3>Greetings!</h3><p>You're cordially invited to the next [ChapterName] Chapter Meeting.  The meeting is next [Meeting Day], [Meeting Month] [Meeting Date] at [Time of Day].  Our speaker will be [Speaker Full Name].</p><p>[Ambassador's personal Message]</p><p>To enjoy all the privileges and benefit of STN membership click the link below to register. [invitation link]</p><p>We look forward to seeing you next [Meeting Day]!</p><p>Sincerely,</p><p>[Ambassador Full Name]<br>Ambassador,<br>[Chapter Name]</p></body></html>";
    	addEmailTemplate("Guest Chapter Meeting Invite","Invitation to Non Members for Chapter Meeting","GUEST_CHAPTER_MTG_INVITE",template);
    	
    	// Create Indexes
    	BasicDBObject index = new BasicDBObject();
    	
    	
    	index.put("GeoLocation", "2dsphere");
    	s.getColl("rwParty").ensureIndex(index);
    	
    	index = new BasicDBObject();
    	
    	index.put("GeoWorkLocation", "2d");
    	s.getColl("rwParty").ensureIndex(index);
    	
    	index.put("GeoWorkLocation", "2d");
    	s.getColl("rwEvent").ensureIndex(index);

    	System.out.println("Set indexes");
    	
    	/*
    	index = new BasicDBObject();
    	index.put("recipients", "1");
    	s.getColl("rwActivityStream").ensureIndex(index);
    	
    	index = new BasicDBObject();
    	index.put("login", "1");
    	s.getColl("rwSecurity").ensureIndex(index);

    	index = new BasicDBObject();
    	index.put("partytype", "1");
    	s.getColl("rwParty").ensureIndex(index);

    	index = new BasicDBObject();
    	index.put("emailAddress", "1");
    	s.getColl("rwPartner").ensureIndex(index);
		
		*/
    	System.out.println("DONE!");
    }
  
    public static void RunAssociationFlow (String AssocId, String partner1) throws JSONException {
 
		SecMgr sm = new SecMgr();
		
		JSONObject data = new JSONObject();

		data.put("act", "login");
		data.put("login", partner1);
		data.put("password","123456");
		data.put("tenant",System.getProperty("PARAM3"));

		try {
			data = sm.handleRequest(data);
		} catch (Exception e) {
			log.debug("DemoSetup Error: ", e);
		}

		String fromId = data.get("data").toString();

		AssocMemberMgr amm = new AssocMemberMgr(fromId);
		
		data = new JSONObject();
		data.put("act", "create");
		data.put("userid", fromId);
		data.put("id", AssocId);
		
		try {
			data = amm.handleRequest(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.debug("DemoSetup Error: ", e);
		}
		
    }
    
    public static void CreateRecommendations (HashMap categories, ArrayList prereqs,ArrayList qualifications,String type) throws Exception{
    	
    	JSONObject data = new JSONObject();

    	
    	RecommendationsMgr rm = new RecommendationsMgr();
		
    	RWJBusComp partyBC = app.GetBusObject("Party").getBusComp("Party");
    	
    	BasicDBObject query = new BasicDBObject();
		query.put("partytype", "PARTNER");
		int recs = partyBC.ExecQuery(query,null);
    	
    	BasicDBList members = partyBC.recordSet;
    	
    	for (int i=0;i< members.size();i++){ //for each member
    		BasicDBObject thisMember = (BasicDBObject)members.get(i);
    		String memberId = thisMember.getString("credId");
    		//String memberPartyId = thisMember.getString("_id");
    		
    		RWJBusComp partnerBC = app.GetBusObject("Partner").getBusComp("Partner"); //get a list of partners (and contacts)
    		query  = new BasicDBObject();
    		query.put("userId", memberId);
    		
    		int partRecs = partnerBC.ExecQuery(query,null);
    		BasicDBList mPartners = partnerBC.recordSet;
    		
    		for (int j = 0; j < partRecs; j++){  //evaluate recommendations for each of the members partners/contacts 
    											 // this loop walks through the list of partners
    			
    			BasicDBObject thisPartner = (BasicDBObject)mPartners.get(j);
    			int criteriaSpec = random.nextInt(8) + 1;
    			
    			
				Object catArray[] = categories.keySet().toArray(); //let's make up some values for each category of criteria
				HashMap hSpec = new HashMap(); //
				int totalSpecified = 0; //incremented over the loop
				for (int d = 0;d < catArray.length;d++){
					String thisCategory = (String)catArray[d];
					int MaxVal = (Integer)categories.get(thisCategory);
					
					int specified = (type.equals("PART_FOR_PART"))?MaxVal:random.nextInt(MaxVal);
					totalSpecified += specified;
					hSpec.put(thisCategory,new Integer(specified));
				}
    			
    			
    			
    			for (int k = 0; k < partRecs; k++){  //for a given partner i.e., thisPartner, this loop walks through every other partner
    												//belonging to the member.  i.e., we have two loops: j & k going through the same list
    				if (k != j){ //k != j:  don't campare a partner to himself/herself
    					BasicDBObject otherPartner = (BasicDBObject)mPartners.get(k);
    					
    					String partnerId = thisPartner.getString("_id");
    					String memberPartyId = thisPartner.getString("partnerId");
    					String contactId = otherPartner.getString("_id");
    					String contactPartyId = otherPartner.getString("partnerId");
    			    	
    					data = new JSONObject();
    					data.put("act", "create");
    					data.put("userId", memberId);
    					data.put("partnerId", partnerId);
    					data.put("contactId", contactId);
    					data.put("partnerPartyId",memberPartyId);
    					data.put("contactPartyId",contactPartyId);
    					data.put("type",type);
    					data.put("status", "UNREAD");
    					//data.put("firstName", RandomDataGenerator.getFirstName());
    					

    					TreeMap tCats = new TreeMap(); //here
    					HashMap hMet = new HashMap(); //here

    					int totalMet = 0; //here
    					for (int m = 0;m < catArray.length;m++){ //this loop genates random values for how many criteria in each cateogory
    															 //the partner has specified -- and of those how many we're met by the contact
    						
    						String thisCategory = (String)catArray[m];
    						int lSpecified = (Integer)hSpec.get(thisCategory);
    						int met = (lSpecified > 0)?random.nextInt(lSpecified):0;
    						
    						totalMet += met;
    						if (lSpecified > 0){
    							String sortedCatName = new Integer(met).toString() + "_" + thisCategory;
    							tCats.put(sortedCatName, thisCategory);
    							hMet.put(thisCategory, new Integer(met));
    						}
    						
    					}
    					
    					Object metArray[] = tCats.keySet().toArray();
    					int last = metArray.length - 1;
    					int fieldSeq = 1;
    					for (int m = last;m >= 0;m--){ //go through met criteria categories in descending order, so the one with highest % met 
    												   // is set to "cat1Name", "cat1CriteriaSpec", "cat1CriteriaMet"; the next highest
    												  // is set to "cat2Name", etc.
    						String fieldSeqStr = new Integer(fieldSeq).toString();
    						String thisTCatName = (String)tCats.get(metArray[m]);
    						
    						Integer thisCatMet = (Integer)hMet.get(thisTCatName);
    						Integer thisCatSpec = (Integer)hSpec.get(thisTCatName);
    						String catFieldName = "cat"+fieldSeqStr+"Name";
    						String specFieldName = "cat"+fieldSeqStr+"CriteriaSpec";
    						String metFieldName = "cat"+fieldSeqStr+"CriteriaMet";
    						
    						data.put(catFieldName,thisTCatName);
    						data.put(specFieldName,thisCatSpec.intValue());
    						data.put(metFieldName,thisCatMet.intValue());
    						
    						fieldSeq ++;
    					}
    					

    					if (qualifications != null){
    					
		    					
		    					fieldSeq = 1;
		    					for (int m = 0;m < qualifications.size();m++){ //this loop genates random values for how many criteria in each cateogory
									 //the partner has specified -- and of those how many we're met by the contact
		
									String thisQual = (String)qualifications.get(m);
									double qScore = random.nextDouble();
									
									String fieldSeqStr = new Integer(fieldSeq).toString();
									String qName = (String)qualifications.get(m); 
									String qFieldName = "partnerQual"+fieldSeqStr;
									String qFieldScore = "partnerQual"+fieldSeqStr+qScore;
									data.put(qFieldName,qName);
									data.put(qFieldScore,qScore);
									
									fieldSeq++;
									
								}

    					}
				
    					for (int m = 0;m < prereqs.size();m++){//html template & schema assume a max of 3
    						
    						String pVal = (String)prereqs.get(m);
    						String fName = "prereq" + (m + 1);
    						data.put(fName, pVal);
    					}
    					
    					if (qualifications != null){
	    					for (int m = 0;m < qualifications.size();m++){//html template & schema assume a max of 3
	    						
	    						String pVal = (String)qualifications.get(m);
	    						String fName = "partnerQual" + (m + 1);
	    						data.put(fName, pVal);
	    					}
    					}
    					
    					Double score = new Double(0);
    					if (totalSpecified > 0){
    						score = new Double(totalMet)/new Double(totalSpecified);
    					}
    					data.put("totalCriteriaSpec",totalSpecified);
    					data.put("totalCriteriaMet",totalMet);
    					data.put("score", score);
    					
    					try {
    						data = rm.handleRequest(data);
    					} catch (Exception e) {
    						log.debug("DemoSetup Error: ", e);
    					}
    					
    				}
    				
    			}
    			
    		}
    		
    		
    	}
    	//System.out.println("CreateRecommendations done");
    	
    	
    }
    
    
    public static void CreateMyRecommendations (HashMap categories, ArrayList prereqs,ArrayList qualifications,String type) throws Exception{
    	
    	JSONObject data = new JSONObject();

    	
    	RecommendationsMgr rm = new RecommendationsMgr();
		
    	RWJBusComp partyBC = app.GetBusObject("Party").getBusComp("Party");
    	
    	BasicDBObject query = new BasicDBObject();
		query.put("partytype", "PARTNER");
		int recs = partyBC.ExecQuery(query,null);
    	
    	BasicDBList members = partyBC.recordSet;
    	
    	for (int i=0;i< members.size();i++){ //for each member
    		BasicDBObject thisMember = (BasicDBObject)members.get(i);
    		String memberId = thisMember.getString("credId");
    		String memberPartyId = thisMember.getString("_id");
    		
    		RWJBusComp partnerBC = app.GetBusObject("Partner").getBusComp("Partner"); //get a list of partners (and contacts)
    		query  = new BasicDBObject();
    		query.put("userId", memberId);
    		
    		int partRecs = partnerBC.ExecQuery(query,null);
    		BasicDBList mPartners = partnerBC.recordSet;
    		
    		Object catArray[] = categories.keySet().toArray(); //let's make up some values for each category of criteria
			HashMap hSpec = getSpecifiedCategories(memberId,categories,type);
			
			/*
			for (int d = 0;d < catArray.length;d++){
				String thisCategory = (String)catArray[d];
				int MaxVal = (Integer)categories.get(thisCategory);
				
				int specified = (type.equals("PART_FOR_PART"))?MaxVal:random.nextInt(MaxVal);
				totalSpecified += specified;
				hSpec.put(thisCategory,new Integer(specified));
			}
			*/
			int totalSpecified = (Integer)hSpec.get("totalSpecified");
			hSpec.remove("totalSpecified");
    		
    		for (int j = 0; j < partRecs; j++){  //compare the member to each of his/her partner(contact) to see how strong the fit is
    			
    			BasicDBObject thisPartner = (BasicDBObject)mPartners.get(j);
    			//int criteriaSpec = random.nextInt(8) + 1;
    			
    			
    			
    			//for (int k = 0; k < partRecs; k++){
    				
    			//	if (k != j){
    			
						
    					String contactId = thisPartner.getString("_id");
    					String contactPartyId = thisPartner.getString("partnerId");
    			    	
    					data = new JSONObject();
    					data.put("act", "create");
    					data.put("userId", memberId);
    					data.put("memberId", memberId);
    					//data.put("partnerId", partnerId);
    					data.put("contactId", contactId);
    					data.put("partnerPartyId",memberPartyId);
    					data.put("contactPartyId",contactPartyId);
    					data.put("type",type);
    					data.put("status", "UNREAD");
    					//data.put("firstName", RandomDataGenerator.getFirstName());
    					

    					TreeMap tCats = new TreeMap(); //here
    					HashMap hMet = new HashMap(); //here

    					int totalMet = 0; //here
    					for (int m = 0;m < catArray.length;m++){ //this loop genates random values for how many criteria in each cateogory
    															 //the partner has specified -- and of those how many we're met by the contact
    						
    						String thisCategory = (String)catArray[m];
    						int lSpecified = (Integer)hSpec.get(thisCategory);
    						int met = (lSpecified > 0)?random.nextInt(lSpecified):0;
    						
    						totalMet += met;
    						if (lSpecified > 0){
    							String sortedCatName = new Integer(met).toString() + "_" + thisCategory;
    							tCats.put(sortedCatName, thisCategory);
    							hMet.put(thisCategory, new Integer(met));
    						}
    						
    					}
    					
    					Object metArray[] = tCats.keySet().toArray();
    					int last = metArray.length - 1;
    					int fieldSeq = 1;
    					for (int m = last;m >= 0;m--){ //go through met criteria categories in descending order, so the one with highest % met 
    												   // is set to "cat1Name", "cat1CriteriaSpec", "cat1CriteriaMet"; the next highest
    												  // is set to "cat2Name", etc.
    						String fieldSeqStr = new Integer(fieldSeq).toString();
    						String thisTCatName = (String)tCats.get(metArray[m]);
    						
    						Integer thisCatMet = (Integer)hMet.get(thisTCatName);
    						Integer thisCatSpec = (Integer)hSpec.get(thisTCatName);
    						String catFieldName = "cat"+fieldSeqStr+"Name";
    						String specFieldName = "cat"+fieldSeqStr+"CriteriaSpec";
    						String metFieldName = "cat"+fieldSeqStr+"CriteriaMet";
    						
    						data.put(catFieldName,thisTCatName);
    						data.put(specFieldName,thisCatSpec.intValue());
    						data.put(metFieldName,thisCatMet.intValue());
    						
    						fieldSeq ++;
    					}
    					

    					if (qualifications != null){
    					
		    					
		    					fieldSeq = 1;
		    					for (int m = 0;m < qualifications.size();m++){ //this loop genates random values for how many criteria in each cateogory
									 //the partner has specified -- and of those how many we're met by the contact
		
									String thisQual = (String)qualifications.get(m);
									double qScore = random.nextDouble();
									
									String fieldSeqStr = new Integer(fieldSeq).toString();
									String qName = (String)qualifications.get(m); 
									String qFieldName = "partnerQual"+fieldSeqStr;
									String qFieldScore = "partnerQual"+fieldSeqStr+qScore;
									data.put(qFieldName,qName);
									data.put(qFieldScore,qScore);
									
									fieldSeq++;
									
								}

    					}
				
    					for (int m = 0;m < prereqs.size();m++){//html template & schema assume a max of 3
    						
    						String pVal = (String)prereqs.get(m);
    						String fName = "prereq" + (m + 1);
    						data.put(fName, pVal);
    					}
    					
    					if (qualifications != null){
	    					for (int m = 0;m < qualifications.size();m++){//html template & schema assume a max of 3
	    						
	    						String pVal = (String)qualifications.get(m);
	    						String fName = "partnerQual" + (m + 1);
	    						data.put(fName, pVal);
	    					}
    					}
    					
    					Double score = new Double(0);
    					if (totalSpecified > 0){
    						score = new Double(totalMet)/new Double(totalSpecified);
    					}
    					data.put("totalCriteriaSpec",totalSpecified);
    					data.put("totalCriteriaMet",totalMet);
    					data.put("score", score);
    					
    					try {
    						data = rm.handleRequest(data);
    					} catch (Exception e) {
    						log.debug("DemoSetup Error: ", e);
    					}
    					
    				//}
    				
    			//}
    			
    		}
    		
    		
    	}
    	System.out.println("CreateMyRecommendations done");
    	
    	
    }
    
    public static HashMap getSpecifiedCategories(String memberId,HashMap categories,String type){
    	Object catArray[] = categories.keySet().toArray(); //let's make up some values for each category of criteria
    	int totalSpecified = 0;
    	HashMap hSpec = new HashMap();
    	for (int d = 0;d < catArray.length;d++){
			String thisCategory = (String)catArray[d];
			int MaxVal = (Integer)categories.get(thisCategory);
			
			int specified = (type.equals("PART_FOR_PART"))?MaxVal:random.nextInt(MaxVal);
			totalSpecified += specified;
			hSpec.put(thisCategory,new Integer(specified));
		}
    	hSpec.put("totalSpecified", totalSpecified);
    	return hSpec;
    }
    
    public static void RunReferralFlow (String partner1, String partner2) throws JSONException {
    	
    	createPartner(partner1, partner2);
    	createPartner(partner2, partner1);
//       	CreateReferral(partner1, partner2,createOffer(partner2));
      	//CreateReferral(partner1, partner2,createOffer(partner2));
      	//CreateReferral(partner1, partner2,createOffer(partner2));
      	//CreateReferral(partner1, partner2,createOffer(partner2));
//      	CreateReferral(partner2, partner1,createOffer(partner1));
      	//CreateReferral(partner2, partner1,createOffer(partner1));
      	//CreateReferral(partner2, partner1,createOffer(partner1));
      	//CreateReferral(partner2, partner1,createOffer(partner1));
      	//CreateReferral(partner2, partner1,createOffer(partner1));

    }

    public static void RunPrimaryFlow (String AssocId, String firstName, String lastName, String domain,HashMap details) throws JSONException {
  
    	String userid = CreateAUser(AssocId, firstName, lastName, domain,details);
    	/*
     	createOffer(userid);
      	createOffer(userid);
      	createOffer(userid);
      	createOffer(userid);
      	createOffer(userid);
      	createOffer(userid);
      	createOffer(userid);
      	createOffer(userid);
      	createOffer(userid);
    	*/	
    	
       	/* createActivityStream(userid);
     	createActivityStream(userid);
     	createActivityStream(userid);
     	createActivityStream(userid);
     	createActivityStream(userid);
     	createActivityStream(userid);
     	createActivityStream(userid);
     	createActivityStream(userid);
     	createActivityStream(userid);
    	*/
    }
    
    public static String getToContactId(String fromId,String contactLogin,HashMap memberNames) throws JSONException{
    	//fetch the contact that 1) belongs to the fromId and 2) has the toLogin email
    	//if none exists, create it
    	//return the row id of the record
    	String name = (String)memberNames.get(contactLogin);
    	String firstName = name.split(" ")[0];
    	String lastName = name.split(" ")[1];
    	ContactsMgr cm = new ContactsMgr(fromId);
		
		JSONObject data = new JSONObject();
		data.put("act", "create");
		
		data.put("userid", fromId);
		//data.put("firstName", RandomDataGenerator.getFirstName());
		data.put("firstName", firstName);
		data.put("lastName", lastName);
		/*
		data.put("jobTitle", RandomDataGenerator.getTitle());
		data.put("workPhone",RandomDataGenerator.getPhoneNumber(true));
		data.put("mobilePhone",RandomDataGenerator.getPhoneNumber(true));
		data.put("business", RandomDataGenerator.getBusinessName());
		data.put("postalCodeAddress","94526");
		*/
		data.put("emailAddress",contactLogin);
		data.put("skipEnrichment", true);

		
		try {
			data = cm.handleRequest(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.debug("DemoSetup Error: ", e);
		}

		BasicDBObject contactObj = (BasicDBObject) JSON.parse(data.get("data").toString()); 
		ObjectId idObj = (ObjectId) contactObj.get("_id");
		
		String contactId = idObj.toString();

    	return contactId;
    	
    }
    
    public static void createInvitation(String fromLogin, String toLogin, String beginState, String status, HashMap memberNames) throws JSONException{
    	log.trace("INVITE: " + fromLogin + ":" + toLogin);
    	
    	SecMgr sm = new SecMgr();
		
		JSONObject data = new JSONObject();

		data.put("act", "login");
		data.put("login", fromLogin);
		data.put("password","123456");
		data.put("tenant",System.getProperty("PARAM3"));

		
		try {
			data = sm.handleRequest(data);
		} catch (Exception e) {
			log.debug("DemoSetup Error: ", e);
		}
	
		String userId = data.get("data").toString();
    	
    	String fromId = getMemberId(fromLogin);
    	String toId = null;
    	if (beginState.equals("contact")){// the toLogin is just an email address of a contact belonging to fromLogin
			toId = getToContactId(fromId,toLogin,memberNames);
		} else { //assume toBeginState == "member"; the toLogin is already a member
			toId = getMemberId(toLogin);
		}
    	
		data = new JSONObject();

		data.put("act", "create");
		data.put("userid", userId);
		data.put("fromId", fromId);
		data.put("referralType", "PART_INVITE");
		data.put("demosetup", true);

		data.put("toId",toId);
		data.put("comments", "I'd like to invite you to become part of my partner network.");

		
		data.put("status",status);
		data.put("toStatus",status);
		if (status.equals("ACCEPTED")){
	    	createPartner(fromLogin, toLogin);
	    	createPartner(toLogin, fromLogin);
		} 
		
		//data.put("status","UNREAD");
		

		
		
		RfrlMgr rfm = new RfrlMgr(userId);
		
		try {
			data = rfm.handleRequest(data);
		} catch (Exception e) {
			log.debug("DemoSetup Error: ", e);
		}
		
		BasicDBObject RfrlObj = (BasicDBObject) JSON.parse(data.get("data").toString()); 
		ObjectId idObj = (ObjectId) RfrlObj.get("_id");
		
		String rfrlId = idObj.toString();
    	
    	
    }
    
    public static void createP2Preferral(String fromLogin, String toLogin, String toLogin2,String toBeginState,String toBeginState2,String status, String waitingForLogin, HashMap memberNames) throws JSONException{
        
    	String fromId = getMemberId(fromLogin);
		String toId = null;
		String toId2 = null;
		
		if (toBeginState.equals("contact")){// the toLogin is just an email address of a contact belonging to fromLogin
			toId = getToContactId(fromId,toLogin,memberNames);
		} else { //assume toBeginState == "member"; the toLogin is already a member
			toId = getMemberId(toLogin);
	    	createPartner(toLogin, fromLogin);
	    	createPartner(fromLogin, toLogin);
		}
		
		if (toBeginState2.equals("contact")){
			toId2 = getToContactId(fromId,toLogin2,memberNames);
		} else {
			toId2 = getMemberId(toLogin2);
	    	createPartner(toLogin2, fromLogin);
	    	createPartner(fromLogin, toLogin2);
		}
		
		
		SecMgr sm = new SecMgr();
		
		JSONObject data = new JSONObject();

		data.put("act", "login");
		data.put("login", fromLogin);
		data.put("password","123456");
		data.put("tenant",System.getProperty("PARAM3"));

		try {
			data = sm.handleRequest(data);
		} catch (Exception e) {
			log.debug("DemoSetup Error: ", e);
		}

		String userId = data.get("data").toString();
		
		//data = new JSONObject();
		
		data = new JSONObject();

		data.put("act", "create");
		data.put("userid", userId);
		data.put("fromId",fromId);
		data.put("toId",toId);
		data.put("toId2",toId2);
		data.put("status",status);
		
		data.put("demosetup", true);
		data.put("referralType","PART_FOR_PART");
		data.put("comments", "I believe the two of you would be good power partners. I encourage you to connect.");
		
		if (status.equals("ACCEPTED")){
	    	createPartner(toLogin, toLogin2);
	    	createPartner(toLogin2, toLogin);
		} 
		
		if (status.equals("WAITING")){
			if (waitingForLogin.equals(toLogin)){
				data.put("waitingForId", toId);				
				createPartner(fromLogin, toLogin2);
				createPartner(toLogin2, fromLogin);
			}
			if (waitingForLogin.equals(toLogin2)){
				data.put("waitingForId", toId2);
				createPartner(fromLogin, toLogin2);
				createPartner(toLogin2, fromLogin);
			}
		}
		

		//data.put("status","UNREAD");
		
				
		RfrlMgr rfm = new RfrlMgr(userId);
		
		try {
			data = rfm.handleRequest(data);
		} catch (Exception e) {
			log.debug("DemoSetup Error: ", e);
		}
		
		BasicDBObject RfrlObj = (BasicDBObject) JSON.parse(data.get("data").toString()); 
		ObjectId idObj = (ObjectId) RfrlObj.get("_id");
		
		String rfrlId = idObj.toString();
    	
    	
    	
    	
    }
    
    public static String CreatePublicOrg(HashMap chapterAttribs, String ambassadorLogin, ArrayList members) throws Exception {
		SecMgr sm = new SecMgr();
		
		JSONObject data = new JSONObject();

		data.put("act", "login");
		data.put("login", "admin.user@referralwire.com");
		data.put("password","123456");
		data.put("tenant",System.getProperty("PARAM3"));

		
		JSONObject retVal = null;
		try {
			retVal = sm.handleRequest(data);
		} catch (Exception e) {
			log.debug("DemoSetup Error: ", e);
		}
		
		String id = retVal.get("data").toString();
		String ambassadorId = getPartyId(ambassadorLogin);

		data = new JSONObject();

		data.put("act", "create");
		data.put("userid",id);
		data.put("partytype","BUSINESS");
		data.put("ambassadorId", ambassadorId);
		
		
	    Iterator it = chapterAttribs.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pairs = (Map.Entry)it.next();
	        //System.out.println(pairs.getKey() + " = " + pairs.getValue());
	        if (pairs.getKey().equals("dateFounded") || pairs.getKey().equals("meetingHour")){
	        	String strVal = (String)pairs.getValue();
	        	Long longval = Long.valueOf(strVal);
	        	System.out.println("dateString " + strVal);
	        	data.put((String)pairs.getKey(), new Date(longval.longValue()));	
	        } else {
	        	data.put(pairs.getKey().toString(),pairs.getValue());
	        }
	        it.remove(); // avoids a ConcurrentModificationException
	    }


		OrgMgr om = new OrgMgr(id);
		
		try {
			data = om.handleRequest(data);
		} catch (Exception e) {
			log.debug("DemoSetup Error: ", e);
		}
		
		BasicDBObject OrgObj = (BasicDBObject) JSON.parse(data.get("data").toString()); 
		ObjectId idObj = (ObjectId) OrgObj.get("_id");
		
		String orgId = idObj.toString();
		
		
		Iterator mit = members.iterator();
	    while (mit.hasNext()) {
	        String memberLogin = (String)mit.next();
	        String partyId = getPartyId(memberLogin);
	        HashMap updateVals = new HashMap();
	        updateVals.put("OrgId", orgId);
	       
	        updateParty(id, partyId, updateVals);
	        mit.remove(); // avoids a ConcurrentModificationException
	    }
		
		return orgId;

    }
    
    public static void CreateOrgMeetings(String orgName) throws Exception{
    	
    	String agenda = "We're really pleased to welcome ZSpeaker to join us this week.  Our speaker will be updating us to some the exciting ways that communities all over the state are raising additional funds for their schools and some of the exciting academic improvements that have resulted. \n\n We'll also be discussing plans our next after hours mixer. Volunteers welcome! See you there.";
    	
    	RWJBusComp partyBC = app.GetBusObject("Party").getBusComp("Party");
    	
    	BasicDBObject query = new BasicDBObject();
		query.put("isSpeaker", "true");
		int speakerRecs = partyBC.ExecQuery(query,null);
		BasicDBList speakerList = partyBC.recordSet;
    	

    	RWJBusComp chapterBC = app.GetBusObject("Organization").getBusComp("Organization");
    	query = new BasicDBObject();
		query.put("businessName", orgName);
		int recs = chapterBC.ExecQuery(query,null);
		//System.out.println("orgName = " + orgName + " recs " + recs);
		
		String OrgId = chapterBC.currentRecord.getString("_id");
		String locationName = chapterBC.currentRecord.getString("establishmentName");
		String locationImageUrl = chapterBC.currentRecord.getString("logoUrl");

		String locationStreet = chapterBC.currentRecord.getString("streetAddress1_work");
		String locationCity = chapterBC.currentRecord.getString("cityAddress_work");
		String locationState = chapterBC.currentRecord.getString("stateAddress_work");
		String locationZip = chapterBC.currentRecord.getString("postalCodeAddress_work");

		
		String meetingDayOfWeek = chapterBC.currentRecord.getString("meetingDayOfWeek");
		Long meetingHour = ((Date)chapterBC.currentRecord.get("meetingHour")).getTime();

		SecMgr sm = new SecMgr();
		
		JSONObject data = new JSONObject();

		data.put("act", "login");
		data.put("login", "admin.user@referralwire.com");
		data.put("password","123456");
		data.put("tenant",System.getProperty("PARAM3"));

		
		JSONObject userObj = null;

		try {
			userObj = sm.handleRequest(data);
		} catch (Exception e) {
			log.debug("DemoSetup Error: ", e);
		}
	
		String id = userObj.get("data").toString();

		EventMgr em = new EventMgr(id);
		
		ArrayList pastMeetings = getMeetingDateTimes(meetingDayOfWeek,meetingHour,8);
		String[] weekArray = {"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"}; 
		
		for (int i=0;i<pastMeetings.size();i++){
			
	
			//calculate the date vals
			Date thisMeetingDate = (Date)pastMeetings.get(i);
			
			String datetime = Long.toString(thisMeetingDate.getTime());
			String date = Integer.toString(thisMeetingDate.getDate());
			String day = weekArray[thisMeetingDate.getDay()];
			
			String month = Integer.toString(thisMeetingDate.getMonth() + 1);
			
			//final DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	        final Calendar c = Calendar.getInstance();
	        c.setTime(thisMeetingDate);
            String year = Integer.toString(c.get(Calendar.YEAR));
            
			year = year.substring(2);
			String title = orgName;

			//get the speaker id
			
			int speakerIndex = random.nextInt(speakerList.size());
			BasicDBObject speakerRecord = (BasicDBObject)speakerList.get(speakerIndex);
			String Speaker1_Id = speakerRecord.getString("_id");
			String speaker1_fullName = speakerRecord.getString("fullName");
			String speaker1_photoUrl = speakerRecord.getString("photoUrl");
			String speaker1_business = speakerRecord.getString("speakerShortDescription");
			String speaker1_bio = speakerRecord.getString("speakerBio");
			agenda = agenda.replaceAll("ZSpeaker", speaker1_fullName);
			int[] rating = {2,3,4,5};
			Integer speakerRating = null;
			if (thisMeetingDate.before(new Date())){
				speakerRating = rating[random.nextInt(4)];
			}
			
			data = new JSONObject();
			

			data.put("act", "create");
			data.put("userid",id);
			if (i > 0){
				data.put("demosetup", "true");
			} // attendee status will be varied randomly if true;
			
			data.put("title", title);
			data.put("agenda", agenda);
			data.put("datetime", thisMeetingDate);
			//data.put("date", date);
			data.put("day", day);
			data.put("OrgId", OrgId);
			data.put("Speaker1_Id", Speaker1_Id);
			data.put("speaker1_fullName", speaker1_fullName);
			data.put("speaker1_photoUrl", speaker1_photoUrl);
			data.put("speaker1_bio", speaker1_bio);
			data.put("speaker1_business",speaker1_business);
			data.put("speakerRating",speakerRating);
			/*
			data.put("locationName", locationName);
			data.put("locationImageUrl", locationImageUrl);
			data.put("locationStreet", locationStreet);
			data.put("locationCity", locationCity);
			data.put("locationState", locationState);
			data.put("locationZip", locationZip);
			*/
			
			try {
				data = em.handleRequest(data);
			} catch (Exception e) {
				log.debug("DemoSetup Error: ", e);
			}
	
		}	
		
    	
    }
    
    public static ArrayList getMeetingDateTimes(String meetingDayOfWeek,Long meetingHour, int weeksBack){
    	
    	ArrayList pastMeetings = new ArrayList();
    	int meetingDayOfWeekInt = Integer.parseInt(meetingDayOfWeek);
    	long daysSinceLastMeeting = 0;
    	Date meetingHourDate = new Date(meetingHour);
    	int hours = meetingHourDate.getHours();
    	int min = meetingHourDate.getMinutes();
    	
    	Date today = new Date();
    	Date meetingThisWeek = new Date();
    	Date lastMeetingDate = null;
    	long offsetDays = 0;
    	if (today.getDay() > meetingDayOfWeekInt){
    			
    		//meeting already happened this week. Find the same day next week
    		offsetDays = today.getDay() - meetingDayOfWeekInt;
    		meetingThisWeek = new Date(today.getTime()-(offsetDays * 24 * 60 * 60 * 1000));
    		lastMeetingDate = new Date(meetingThisWeek.getTime()+(7 * 24 * 60 * 60 * 1000));
    		
    		
    	} else {
    			
    		//meeting hasn't yet happened this week -- compute how many days forward
    		offsetDays = meetingDayOfWeekInt-today.getDay();
    		meetingThisWeek = new Date(today.getTime() + (offsetDays * 24 * 60 * 60 * 1000));
    		lastMeetingDate = meetingThisWeek;
    		
    	}
    	
		lastMeetingDate.setHours(hours);
		lastMeetingDate.setMinutes(min);
    	pastMeetings.add(lastMeetingDate);
    	
		
    	
		for (long i = 1; i < weeksBack; i++){
			
			long weekPrior = i * 7 * 24 * 60 * 60 * 1000;
			Date previousMeetingDate = new Date(lastMeetingDate.getTime() - weekPrior);
			pastMeetings.add(previousMeetingDate);
		}
    	return pastMeetings;
    }

    public static String CreatePublicAssoc(String suffix) throws JSONException {
		SecMgr sm = new SecMgr();
		
		JSONObject data = new JSONObject();

		data.put("act", "login");
		data.put("login", "admin.user@referralwire.com");
		data.put("password","123456");
		data.put("tenant",System.getProperty("PARAM3"));

		JSONObject retVal = null;
		try {
			retVal = sm.handleRequest(data);
		} catch (Exception e) {
			log.debug("DemoSetup Error: ", e);
		}
		
		String id = retVal.get("data").toString();

		data = new JSONObject();

		data.put("act", "create");
		data.put("userid",id);
		
		data.put("partytype","ASSOCIATION");
		data.put("logoUrl",RandomDataGenerator.getlogoURL());
		data.put("businessName",RandomDataGenerator.getAssoc()+ suffix);
		data.put("segment",RandomDataGenerator.getBusinessType());
		data.put("workPhone",RandomDataGenerator.getPhoneNumber(true));
		data.put("faxPhone",RandomDataGenerator.getPhoneNumber(true));
		data.put("emailAddress",RandomDataGenerator.getFirstName() + "@yahoo.com");
		data.put("streetAddress1",RandomDataGenerator.getStreetAddress());
		data.put("cityAddress",RandomDataGenerator.getCity());
		data.put("stateAddress",RandomDataGenerator.getState());
		data.put("postalCodeAddress",RandomDataGenerator.getZip());

		AssocMgr om = new AssocMgr(id);
		
		try {
			data = om.handleRequest(data);
		} catch (Exception e) {
			log.debug("DemoSetup Error: ", e);
		}

		BasicDBObject Obj = (BasicDBObject) JSON.parse(data.get("data").toString()); 
		ObjectId idObj = (ObjectId) Obj.get("_id");
		
		String AssocId = idObj.toString();
		
		return AssocId;

    }

    public static void CreateReferral(String fromLogin, String toLogin, HashMap refDetails,int reps,HashMap toPreferences) throws JSONException {
    	reps = 0;
    	for (int i=0;i<=reps;i++){
    	
	    	//String OfferId = createOffer(fromLogin);
	    	
			SecMgr sm = new SecMgr();
			
			JSONObject data = new JSONObject();
	
			data.put("act", "login");
			data.put("login", fromLogin);
			data.put("password","123456");
			data.put("tenant",System.getProperty("PARAM3"));

			try {
				data = sm.handleRequest(data);
			} catch (Exception e) {
				log.debug("DemoSetup Error: ", e);
			}
			
	
			String fromId = data.get("data").toString();
	
			ContactsMgr cm = new ContactsMgr(fromId);
			
			data = new JSONObject();
			data.put("act", "create");
			
			data.put("userid", fromId);
			//data.put("firstName", RandomDataGenerator.getFirstName());
			data.put("firstName", refDetails.get("firstName"));
			data.put("lastName", RandomDataGenerator.getSurname());
			data.put("jobTitle", RandomDataGenerator.getTitle());
			data.put("workPhone",RandomDataGenerator.getPhoneNumber(true));
			data.put("mobilePhone",RandomDataGenerator.getPhoneNumber(true));
			data.put("business", RandomDataGenerator.getBusinessName());
			data.put("postalCodeAddress","94526");
			data.put("emailAddress",data.get("firstName") + "@yahoo.com");
			data.put("skipEnrichment", true);
			
			HashMap address = RandomDataGenerator.getRealAddress();
					
		    Iterator it = address.keySet().iterator();
		    while (it.hasNext()) {
		    	String thisKey = (String)it.next();
		    	String thisVal = (String)address.get(thisKey);
		    	//System.out.println(thisKey + " " + thisVal);
		    	
		        data.put(thisKey,thisVal);
		        //it.remove(); // avoids a ConcurrentModificationException
		    }		
		    

			
			
			
			
			try {
				data = cm.handleRequest(data);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				log.debug("DemoSetup Error: ", e);
			}
	
			BasicDBObject contactObj = (BasicDBObject) JSON.parse(data.get("data").toString()); 
			ObjectId idObj = (ObjectId) contactObj.get("_id");
			
			String contactId = idObj.toString();
	
			data = new JSONObject();
			
			data.put("act", "login");
			data.put("login", toLogin);
			data.put("password","123456");
			data.put("tenant",System.getProperty("PARAM3"));

			try {
				data = sm.handleRequest(data);
			} catch (Exception e) {
				log.debug("DemoSetup Error: ", e);
			}
			
			String toId = data.get("data").toString();
	
		
			data = new JSONObject();
			
			data.put("act", "PartyId");
			data.put("userid", toId);
			
			try {
				data = sm.handleRequest(data);
			} catch (Exception e) {
				log.debug("DemoSetup Error: ", e);
			}
			
			String toId2 = data.get("id").toString();
			
			data = new JSONObject();
	
			data.put("act", "create");
			data.put("userid", fromId);
	
			data.put("toId",toId2);
			data.put("contactId",contactId);
//			data.put("ref_offerId",OfferId);
			String subj = (i>0)?"past":"present";
			data.put("subject",subj);
	
			//data.put("status","UNREAD");
			data.put("has_budget","true");
			data.put("can_contact","true");
			data.put("decision_maker","true");
			data.put("referralType","CUST_FOR_PART");
			//data.put("source_comments",RandomDataGenerator.getTagLine());
			//data.put("comments",RandomDataGenerator.getMessage());
			if (toPreferences.containsKey("question1")){
					data.put("question1", (String)toPreferences.get("question1"));
			}
			if (toPreferences.containsKey("question2")){
				data.put("question2", (String)toPreferences.get("question2"));
			}
			if (toPreferences.containsKey("question3")){
				data.put("question3", (String)toPreferences.get("question3"));
			}
			if (toPreferences.containsKey("question4")){
				data.put("question4", (String)toPreferences.get("question4"));
			}
			
		    it = refDetails.keySet().iterator();
		    while (it.hasNext()) {
		    	String thisKey = (String)it.next();
		    	String thisVal = (String)refDetails.get(thisKey);
		    	//System.out.println(thisKey + " " + thisVal);
		    	
		        data.put(thisKey,thisVal);
		        //it.remove(); // avoids a ConcurrentModificationException
		    }	
			
			RfrlMgr rfm = new RfrlMgr(fromId);
			
			try {
				data = rfm.handleRequest(data);
			} catch (Exception e) {
				log.debug("DemoSetup Error: ", e);
			}
			
			BasicDBObject RfrlObj = (BasicDBObject) JSON.parse(data.get("data").toString()); 
			idObj = (ObjectId) RfrlObj.get("_id");
			
			String rfrlId = idObj.toString();
    	}
    	
    	RunReferralFlow (fromLogin, toLogin);

     }
    
    public static String createOffer(String login) throws JSONException {

		JSONObject data = new JSONObject();
		
		SecMgr sm = new SecMgr();

		data.put("act", "login");
		data.put("login", login);
		data.put("password","123456");
		data.put("tenant",System.getProperty("PARAM3"));

		try {
			data = sm.handleRequest(data);
		} catch (Exception e) {
			log.debug("DemoSetup Error: ", e);
		}
		
		String toId = data.get("data").toString();

		
		data = new JSONObject();

		data.put("act", "create");
		data.put("userid", toId);
	
		data.put("title",RandomDataGenerator.getTagLine());
		data.put("details",RandomDataGenerator.getMessage());
		data.put("value", RandomDataGenerator.random.nextInt(100));
	
		RfrlOfferMgr rm = new RfrlOfferMgr(toId);
	
		try {
			data = rm.handleRequest(data);
		} catch (Exception e) {
			log.debug("DemoSetup Error: ", e);
		}

		BasicDBObject Obj = (BasicDBObject) JSON.parse(data.get("data").toString()); 
		ObjectId idObj = (ObjectId) Obj.get("_id");
		
		String Id = idObj.toString();
		
		return Id;

    	
    }
    
    public static String createBanner() throws JSONException {

		JSONObject data = new JSONObject();
		
		SecMgr sm = new SecMgr();

		data.put("act", "login");
		data.put("login", "phil@referralwiretest.biz");
		data.put("password","123456");
		data.put("tenant",System.getProperty("PARAM3"));

		try {
			data = sm.handleRequest(data);
		} catch (Exception e) {
			log.debug("DemoSetup Error: ", e);
		}
		
		String toId = data.get("data").toString();

		
		data = new JSONObject();

		data.put("act", "create");
		data.put("userid", toId);
		data.put("bo", "BannerMessages");
		data.put("bc", "BannerMessages");

		data.put("picture","dataVault/JimBellaceraSmall.jpg");
		data.put("headline","Greetings Thinkers!");
		data.put("body","If you can dream it, then you can achieve it. You will get all you want in life if you help enough other people get what they want.\n\n If you can dream it, then you can achieve it. You will get all you want in life if you help enough other people get what they want.");
		data.put("signature", "Zig Ziglar");
	
		GenericMgr gm = new GenericMgr(toId);
	
		try {
			data = gm.handleRequest(data);
		} catch (Exception e) {
			log.debug("DemoSetup Error: ", e);
		}

		BasicDBObject Obj = (BasicDBObject) JSON.parse(data.get("data").toString()); 
		ObjectId idObj = (ObjectId) Obj.get("_id");
		
		String Id = idObj.toString();
		
		return Id;

    	
    }
    
    
    public static void createActivityStream(String login) throws JSONException {
		SecMgr sm = new SecMgr();
		
		JSONObject data = new JSONObject();

		data.put("act", "login");
		data.put("login", login);
		data.put("password","123456");
		data.put("tenant",System.getProperty("PARAM3"));

		JSONObject retVal = null;
		try {
			retVal = sm.handleRequest(data);
		} catch (Exception e) {
			log.debug("DemoSetup Error: ", e);
		}
		
		String id = retVal.get("data").toString();

		for ( int i = 0; i < 5; i++) {
			data = new JSONObject();

			data.put("act", "create");
			data.put("userid",id);
		
			data.put("type","MESSAGE");
			data.put("message",RandomDataGenerator.getMessage());
			data.put("photo",RandomDataGenerator.getPhotoURL());
		
			ActivityStreamMgr am = new ActivityStreamMgr(id);
		
			try {
				retVal = am.handleRequest(data);
			} catch (Exception e) {
				log.debug("DemoSetup Error: ", e);
			}
		}
    }
    
    public static void createPartner(String login, String partner) throws JSONException {
    	
 		SecMgr sm = new SecMgr();
 		
 		JSONObject data = new JSONObject();
 		data.put("act", "login");
 		data.put("login", login);
 		data.put("password","123456");
		data.put("tenant",System.getProperty("PARAM3"));

 		try {
 			data = sm.handleRequest(data);
 		} catch (Exception e) {
 			// TODO Auto-generated catch block
 			log.debug("DemoSetup Error: ", e);
 		}
 		
 		String id = data.get("data").toString();
 		
 		
  		
 		
 		data = new JSONObject();

 		data.put("act", "login");
 		data.put("login", partner);
 		data.put("password","123456");
		data.put("tenant",System.getProperty("PARAM3"));

 		try {
 			data = sm.handleRequest(data);
 		} catch (Exception e) {
 			// TODO Auto-generated catch block
 			log.debug("DemoSetup Error: ", e);
 		}

 		String partnerId = data.get("data").toString();
 		data = new JSONObject();

		
 		
 		data.put("act", "read");
 		data.put("userid",partnerId);
 		
 		UserMgr um = new UserMgr(partnerId);
 		
 		try {
 			data = um.handleRequest(data);
 			log.trace(data.toString());
 		} catch (Exception e) {
 			log.debug("DemoSetup Error: ", e);
 		}
 		
 		BasicDBObject partnerObj = (BasicDBObject) JSON.parse(data.get("data").toString()); 
 		ObjectId idObj = (ObjectId) partnerObj.get("_id");
 		
 		String partnerPartyId = idObj.toString();
 		
 		data.put("act", "create");
 		data.put("userid",id);
 		data.put("partnerId",partnerPartyId);
 		
 		//data.put("firstName",partnerObj.get("firstName"));
 		//data.put("lastName",partnerObj.get("lastName"));
 		data.put("fullName",partnerObj.get("fullName"));
 		//data.put("emailAddress",partnerObj.get("emailAddress"));
 		//data.put("workPhone",partnerObj.get("workPhone"));
 		//data.put("mobilePhone",partnerObj.get("mobilePhone"));
		//data.put("jobTitle",partnerObj.get("jobTitle"));
		//data.put("business",partnerObj.get("business"));
		  		
 		PartnerMgr pm = new PartnerMgr(id);
 		try {
 			data = pm.handleRequest(data);
 		} catch (Exception e) {
 			// TODO Auto-generated catch block
 			log.debug("DemoSetup Error: ", e);
 		}
     }
    
	public static String CreateAUser(String AssocId, String firstName, String lastName, String domain, HashMap ov) throws JSONException {
		  
		// create john.green account
		SecMgr sm = new SecMgr();
	
		JSONObject data = new JSONObject();
	    
		String loginid = firstName.toLowerCase() + "." + lastName.toLowerCase() + "@" + domain;
		
		if (lastName.equals("Winthrop")){loginid = "phil@referralwiretest.biz";};
		if (lastName.equals("Meyers")){loginid = "lori@referralwiretest.biz";};
		if (lastName.equals("Freeman")){loginid = "susan@referralwiretest.biz";};
		if (lastName.equals("Lerner")){loginid = "jenny@referralwiretest.biz";};
		
		data.put("act", "create");
		data.put("login", loginid );
		data.put("password","123456");
		data.put("passwordHint", "123456") ;
		data.put("type", "PARTNER") ;
		data.put("tenant", System.getProperty("PARAM3")) ;
		data.put("firstName",firstName);
		data.put("lastName",lastName);
		//data.put("postalCodeAddress", "94526");
		data.put("Eula", "Yes") ;
		 

		try {
			sm.handleRequest(data);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.debug("DemoSetup Error: ", e);
		}
		
		data = new JSONObject();
		
		data.put("act", "login");
		data.put("login", loginid);
		data.put("password","123456");
		data.put("tenant",System.getProperty("PARAM3"));
		
		try {
			data = sm.handleRequest(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.debug("DemoSetup Error: ", e);
		}
		
		String id = data.get("data").toString();
		data = new JSONObject();

		if (loginid.equals("phil@referralwiretest.biz") ) {
	 		data = new JSONObject();
			data.put("act", "addRole");
	 		data.put("userid",id);
	 		data.put("login", loginid);
	 		data.put("role","CHAPTER_HEAD");
	 	
	 		try {
	 			data = sm.handleRequest(data);
	 		} catch (Exception e) {
	 			// TODO Auto-generated catch block
	 			log.debug("DemoSetup Error: ", e);
	 		}
	 		data = new JSONObject();
 		}

		if (loginid.equals("admin.user@referralwire.com") ) {
	 		data = new JSONObject();
			data.put("act", "addRole");
	 		data.put("userid",id);
	 		data.put("login", loginid);
	 		data.put("role","ADMINISTRATOR");
	 	
	 		try {
	 			data = sm.handleRequest(data);
	 		} catch (Exception e) {
	 			// TODO Auto-generated catch block
	 			log.debug("DemoSetup Error: ", e);
	 		}
	 		data = new JSONObject();
 		}
		
		data.put("act", "read");
		data.put("userid",id);
		
		UserMgr um = new UserMgr(id);
		
		try {
			data = um.handleRequest(data);
		} catch (Exception e) {
			log.debug("DemoSetup Error: ", e);
		}
		
		BasicDBObject partnerObj = (BasicDBObject) JSON.parse(data.get("data").toString()); 
		ObjectId idObj = (ObjectId) partnerObj.get("_id");
		
		String PartyId = idObj.toString();

		data = new JSONObject();

		data.put("act", "update");
		data.put("userid",id);
		data.put("id", PartyId);	
		/*
		data.put("gender","MALE");
		data.put("jobTitle",RandomDataGenerator.getTitle());
		data.put("segment",RandomDataGenerator.getBusinessType());
		data.put("jobTitle",RandomDataGenerator.getTitle());
		data.put("workPhone",RandomDataGenerator.getPhoneNumber(true));
		data.put("mobilePhone",RandomDataGenerator.getPhoneNumber(true));
		data.put("faxPhone",RandomDataGenerator.getPhoneNumber(true));
		data.put("emailAddress", loginid);
		data.put("streetAddress1",RandomDataGenerator.getStreetAddress());
		data.put("cityAddress",RandomDataGenerator.getCity());
		data.put("stateAddress",RandomDataGenerator.getState());
		data.put("postalCodeAddress",RandomDataGenerator.getZip());
		data.put("photoUrl",RandomDataGenerator.getPhotoURL());
		data.put("OrgId", CreatePublicOrg(loginid));
		if ( !AssocId.equals("NONE"))
			data.put("primaryAssocId",AssocId);
		data.put("profession","FINANCIAL");
		data.put("question1","Are you a Home owner ?");
		data.put("question2","Did you sell your house recently ?");
		data.put("question3","Did you change your job recently ?");
		data.put("question4","Who is your current Insurance provider ?");
		data.put("paypalId","jillm_1345698199_biz@gmail.com");
		*/
	    Iterator it = ov.keySet().iterator();
	    while (it.hasNext()) {
	    	String thisKey = (String)it.next();
	    	String thisVal = (String)ov.get(thisKey);
	    	log.trace(thisKey + " " + thisVal);
	    	
	        data.put(thisKey,thisVal);
	        //it.remove(); // avoids a ConcurrentModificationException
	    }
		data.put("workPhone",RandomDataGenerator.getPhoneNumber(true));
		data.put("mobilePhone",RandomDataGenerator.getPhoneNumber(true));
		data.put("faxPhone",RandomDataGenerator.getPhoneNumber(true));
		
		try {
			JSONObject userData = um.handleRequest(data);
		} catch (Exception e) {
			log.debug("DemoSetup Error: ", e);
		}

		
				
		
		
		ContactsMgr cm = new ContactsMgr(id);
		
		for (int i = 0; i < 5; i++ ) {
			data = new JSONObject();
			data.put("act", "create");
		
			data.put("userid", id);
			data.put("firstName", RandomDataGenerator.getFirstName());
			data.put("lastName", RandomDataGenerator.getSurname());
			data.put("jobTitle", RandomDataGenerator.getTitle());
			data.put("business", RandomDataGenerator.getBusinessName());
			data.put("postalCodeAddress", "94526");
			data.put("skipEnrichment",true);
			
		
		
			try {
				cm.handleRequest(data);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				log.debug("DemoSetup Error: ", e);
			}
		}
		
		
		
		//return loginid;
		return id;
	}
	
		
	public static void createUserForPartner(HashMap inputs) throws JSONException {
			
	ContactsMgr cm = new ContactsMgr((String)inputs.get("userid"));
	
		JSONObject data = new JSONObject();
		data.put("act", "create");
	
		data.put("userid", (String)inputs.get("userid"));
		data.put("firstName", (String)inputs.get("firstName"));
		data.put("lastName", (String)inputs.get("lastName"));
		data.put("jobTitle", (String)inputs.get("jobTitle"));
		data.put("business", (String)inputs.get("business"));
		data.put("mobilePhone", (String)inputs.get("mobilePhone"));
		data.put("emailAddress", (String)inputs.get("emailAddress"));
		data.put("streetAddress1", (String)inputs.get("streetAddress1"));
		data.put("cityAddress", (String)inputs.get("cityAddress"));
		data.put("stateAddress", (String)inputs.get("stateAddress"));
		data.put("postalCodeAddress", "94596");
		data.put("skipEnrichment",true);

		try {
			cm.handleRequest(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.debug("DemoSetup Error: ", e);
		}
	}
	
	public static void setupTenant(String ver, String name, String homepage) throws Exception {
		
		TenantMgr sm = new TenantMgr();
		
		JSONObject data = new JSONObject();
		data.put("act", "create");
		
		data.put("loginDisplayName", "Successful Thinkers Network");
		data.put("tagLine","Face To Face Interactions...Enhanced by Technology");
		data.put("splashImage","/STN/Images/loginPage.jpg");
		data.put("pageTitle","STNConnect");
		data.put("ver", ver);
		data.put("tenant", name);
		data.put("homepage",homepage);
		data.put("domainName","successfulthinkersnetwork.com");
		//data.put("domainName","amazonaws.com");
		//data.put("domainName","referralwiretest.biz");
		//data.put("domainName","whitelabelsite.biz");
		//data.put("domainName","localhost");
		//data.put("rootUrl","http://members.successfulthinkersnetwork.com");
		// data.put("rootUrl","http://localhost:8080");
		// url for development server
		data.put("rootUrl","http://ec2-23-22-176-243.compute-1.amazonaws.com:8080");
		data.put("tenantLogo","http://members.successfulthinkersnetwork.com/STN/Images/logo.png");
		data.put("SupportEmailAddress","support@successfulthinkersnetwork.com");
		data.put("AdminEmailAddress","admin@successfulthinkersnetwork.com");
		data.put("invitationsEmailAddress","invitations@successfulthinkersnetwork.com");
		data.put("memberEmailAddress","member@successfulthinkersnetwork.com");
		data.put("IPhoneAppLocation","/STN/Templates/Other/iphoneWebNote.html");
		data.put("AndroidAppLocation","/STN/Templates/Other/androidWebNote.html");
		data.put("IPhoneUpgradeAppPage","/STN/Templates/Other/iphoneWebNote.html");
		data.put("AndroidUpgradeAppPage","/STN/Templates/Other/androidWebNote.html");
		data.put("WebAppLocation","/rw.jsp");
		data.put("MobileAppName","na");
		data.put("MobileAppLocation","/m.jsp");
		data.put("MobileAppVer","V1");
		
		// Facebook App Ids - Use one of these based on where the deployment is going to be
		// data.put("FaceBookAppId","295573790540551"); // FB key for referralwiretest.biz domain
		// data.put("FaceBookAppId","388261677962904"); // FB key for localhost domain
		data.put("FaceBookAppId","242425209253707"); // FB key for aws development server
		// data.put("FaceBookAppId","170309439830860");  // FB key for sucessfulthinkersnetwork.com domain
		// LinkedIn App Ids - Use one of these based on where the deployment is going to be
		data.put("LinkedInAppId","75qpirkfeswa97");  // localhost, referralwiretest.biz, referralwire.com	
		// data.put("LinkedInAppId","zvzfcy2anepn");  // sucessfulthinkersnetwork.com	
		data.put("SFDCAppId","6556957529713189146");  // localhost, referralwiretest.biz, referralwire.com	
		data.put("publicProfilesBaseUrl","profiles");  // localhost, referralwiretest.biz, referralwire.com	
		data.put("GoogleAnalyticsPropertyCode","UA-40902707-2");  // localhost, referralwiretest.biz, referralwire.com	
		// data.put("GoogleAnalyticsServiceAccountEmail","639969735980-9hbadoqa88vks7a37fhsaj0bi8kfrs0f@developer.gserviceaccount.com");  // localhost, referralwiretest.biz, referralwire.com	
	// 	data.put("GoogleAnalyticsServiceAccountPrivateKey","stnmembersitekey");  // localhost, referralwiretest.biz, referralwire.com	
		data.put("GoogleAnalyticsServiceAccountEmail","734052415290-i6brg4dsbrq8o84dgfs37ugvikq0dtoc@developer.gserviceaccount.com");  // localhost, referralwiretest.biz, referralwire.com	
		data.put("GoogleAnalyticsServiceAccountPrivateKey","key");  // localhost, referralwiretest.biz, referralwire.com	

		data.put("GoogleAnalyticsServiceAccountGaId","86971733");  // localhost, referralwiretest.biz, referralwire.com	

		data.put("_demoSetup_","true");
		
		try {
			sm.handleRequest(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.debug("DemoSetup Error: ", e);
		}

	}
	
	public static HashMap addDependent(String validIf,String value,HashMap dependentCriteria){
		ArrayList potentialVals = null;
		
		if (dependentCriteria.containsKey(validIf)){
			potentialVals = (ArrayList)dependentCriteria.get(validIf);
		} else {
			potentialVals = new ArrayList();
		}
		potentialVals.add(value);
		dependentCriteria.put(validIf, potentialVals);
		
		return dependentCriteria;
	}
	
	public static ArrayList addNonEx(String value, ArrayList nonExclusiveCriteria){
		nonExclusiveCriteria.add(value);
		return nonExclusiveCriteria;
	}
	
	public static HashMap addExclusive(String cat,String val,HashMap exclusiveCriteria){
		return addDependent(cat,val,exclusiveCriteria); // coincidentally these two are functions build lists with symmetrical structures
		
		
	}
	
	public static void SetInvitees(ArrayList Invitees) throws Exception{
		
		String userId = getAdminUserId();
		Iterator<Object> it = Invitees.iterator();
		while(it.hasNext())
		{
		    String invitedBy = (String)it.next();
		    String[] invitedByAry = invitedBy.split(":");
		    String invited = invitedByAry[0];
		    String By = invitedByAry[1];
		    String inviteeId = getMemberId(invited);
		    String invitorId = getMemberId(By);
		    String invitorName = getPartyAttribute(By,"fullName");
		    String invitorOrgId = getPartyAttribute(By,"OrgId");
		    
		    HashMap invitorNameHash = new HashMap();
		    invitorNameHash.put("invitedBy_FullName", invitorName);
		    HashMap invitorIdHash = new HashMap();
		    invitorIdHash.put("invitedBy_Id", invitorId);
		    //System.out.println("userId = " + userId + "; inviteeId = " + inviteeId + "; invitorName = " + invitorName);
		    HashMap invitorOrgIdHash = new HashMap();
		    
		    updateMember(userId,inviteeId, invitorNameHash);
		    updateMember(userId,inviteeId, invitorIdHash);
		    
		    if (invitorOrgId != null && !invitorOrgId.equals("")){
			    invitorOrgIdHash.put("invitedBy_OrgId", invitorOrgId);
			    updateMember(userId,inviteeId, invitorOrgIdHash);
		    }
		    
		    
		}
		
	}
	
	public static void enrichParties (HashMap exclusiveCriteria, ArrayList nonExclusiveCriteria,HashMap dependentCriteria,HashMap lov) throws Exception{
    
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
		

		String adminId = data.get("data").toString();
		
		
		
    	data = new JSONObject();
    	JSONArray dataList = new JSONArray();
    	//String statusVals[] = {"NO_MATCH","ONE_MATCH","MULT_MATCHES","CONFIRMED_MATCH","CONFIRMED_MATCH","CONFIRMED_MATCH"};
    	//String statusVals[] = {"NO_MATCH","MULT_MATCHES","CONFIRMED_MATCH"};
    	String statusVals[] = {"CONFIRMED_MATCH"};
    	
    	CriteriaMgr cm = new CriteriaMgr(adminId);
		
    	RWJBusComp partyBC = app.GetBusObject("Party").getBusComp("Party");
    	
    	BasicDBObject query = new BasicDBObject();
		//query.put("partytype", "PARTNER");
		int recs = partyBC.ExecQuery(query,null);
    	
    	BasicDBList parties = partyBC.recordSet;
    	
    	//for (int i=0;i< 1;i++){ //for each member
		String ages[] = {"26","29","33","35","38","41","44","46","49","50","52","54","57","59","61","64","65","68"};
		String cities[] = {"Oakland","Corte Madera","Salinas","Walnut Creek","Sacramento"};
		
    	for (int i=0;i< parties.size();i++){ //for each member
    		BasicDBObject thisParty = (BasicDBObject)parties.get(i);
    		//String memberId = thisMember.getString("credId");
    		String partyId = thisParty.getString("_id");
    		int whichStatus = random.nextInt(statusVals.length);
    		String status = statusVals[whichStatus];

			String firstName = (thisParty.containsField("firstName"))?thisParty.getString("firstName"):"Bill";
			String lastName = (thisParty.containsField("lastName"))?thisParty.getString("lastName"):"Stevens";
			int whichAge = random.nextInt(ages.length);
			int whichCity = random.nextInt(cities.length);
			String age = ages[whichAge];
			String city = (thisParty.containsField("cityAddress"))?thisParty.getString("cityAddress"):cities[whichCity]; 

    		String profileName = firstName + " " + lastName + "; " + "age: " + age + "; city: " + city;
    		HashMap values = new HashMap();
    		values.put("enrichmentStatus", status);
    		
    		if (status.equals("MULT_MATCHES")){
    			String searchKey = new ObjectId().toString();
    			values.put("searchKey", searchKey);
    			associateAlternateParties(thisParty,adminId,searchKey);
    		}
    		
    		if (!status.equals("NO_MATCH")){
    			values.put("profileName", profileName);
    		}
    		
    		//System.out.println("updateParty");
    		updateParty(adminId, partyId, values);
    		
    		if (status.equals("CONFIRMED_MATCH")){
    			associateProspectValues(cm,adminId,partyId,exclusiveCriteria, nonExclusiveCriteria,dependentCriteria,lov);
    			
    		}
    	}
    		
    		
    
    	System.out.println("Enrich done");
    	
    	
    }
	
	public static void associateAlternateParties(BasicDBObject thisMember,String userId,String searchKey) throws JSONException{
		
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
				int copies = random.nextInt(4);
				String ages[] = {"26","29","33","35","38","41","44","46","49","50","52","54","57","59","61","64","65","68"};
				String cities[] = {"Oakland","Corte Madera","Salinas","Walnut Creek","Sacramento"};
				String areaCodes[] = {"510","425","408","925","916"};//these go with the cities
				String streets[] = {"Elm St.","Main St.","Duboce Ave","Grant Blvd.","4th Ave","Burley Dr.","Howard Ct.","Oak St"};
				
				ContactsMgr cm = new ContactsMgr(userId);
				//cm.partyId = userId;
				//cm.userid = userId;
				for (int i = 0; i < copies; i++){
					String firstName = (thisMember.containsField("firstName"))?thisMember.getString("firstName"):"Bill";
					String lastName = (thisMember.containsField("lastName"))?thisMember.getString("lastName"):"Stevens";
					String partytype = thisMember.getString("partytype");
					int whichAge = random.nextInt(ages.length);
					int whichCity = random.nextInt(cities.length);
					String age = ages[whichAge];
					String city = cities[whichCity];
					String areaCode = "("+areaCodes[whichCity] + ")";
					String phone = random.nextInt(799)+200 + "-" + random.nextInt(8999)+1000;
					phone = areaCode + " " + phone;
					String street = streets[random.nextInt(streets.length)];
					street = random.nextInt(1250) + " " + street;
					String profileName = firstName + " " + lastName + "; " + "age: " + age + "; city: " + city;
					
					
					data = new JSONObject();
					data.put("act", "create");
					data.put("userid", userId);
					data.put("firstName", firstName);
					data.put("lastName", lastName);
					data.put("partytype", partytype);
					data.put("emailAddress",firstName + "." + lastName + i);
					data.put("workPhone", phone);
					data.put("cityStreet",street);
					data.put("cityAddress", city);
					data.put("age", age);
					data.put("profileName",profileName);
					data.put("searchKey", searchKey);
					data.put("partytype", "SYNDICATED");
					data.put("skipEnrichment",true);
					
					try {
						cm.handleRequest(data);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						log.debug("DemoSetup Error: ", e);
					}
				}
		
		
	}
	
	public static void associateProspectValues(CriteriaMgr cm, String memberId, String partyId,HashMap exclusiveCriteria, ArrayList nonExclusiveCriteria,HashMap dependentCriteria,HashMap lov) throws JSONException{
		JSONArray dataList = new JSONArray();

		JSONObject data = new JSONObject();
		data.put("act", "associate");
		data.put("userId", memberId);
		data.put("partyId", partyId);
		
		HashMap selectedItems = new HashMap();
		//add some mutually exclusive descriptors.  These are descriptors such as Income Level for which a party should only have one value.  E.g., a party can have an income of either 50-100K or 100-150K but not both
		HashMap exc = (HashMap) exclusiveCriteria.clone();
		int numSelected = random.nextInt(exc.size());
		
		for (int i = 0; i < numSelected; i++){
			String thisExcCat = exc.keySet().toArray()[random.nextInt(exc.size())].toString();
			ArrayList listOfPossibleVals = (ArrayList)exc.get(thisExcCat);
			String gVal = (String)listOfPossibleVals.get(random.nextInt(listOfPossibleVals.size()));
			exc.remove(thisExcCat); //remove the category so it's not used more than once
			selectedItems.put(gVal, gVal);
		}
		
		//add some non-exclusive descriptors.  E.g., the user can be interested in both travel and pets
		ArrayList nonExc = (ArrayList) nonExclusiveCriteria.clone();
		
		numSelected = random.nextInt(nonExc.size()/2);
		
		
		for (int i = 0; i < numSelected; i++){
			String gVal = nonExc.get(random.nextInt(nonExc.size())).toString();
			nonExc.remove(gVal); //remove the category so it's not used more than once
			selectedItems.put(gVal, gVal);
		}
		
		Object dKeys[] = dependentCriteria.keySet().toArray();
		
		for (int i = 0; i < dKeys.length; i++){
			String thisDepCat = (String)dKeys[i];
			if (selectedItems.containsKey(thisDepCat)){
				ArrayList possibleVals = (ArrayList)dependentCriteria.get(thisDepCat);
				
				for (int j = 0; j < possibleVals.size(); j++){
					Float addDependentCat = random.nextFloat();
					if (addDependentCat > .5){
						String gVal = (String)possibleVals.get(j);
						selectedItems.put(gVal, gVal);
					}
				}
			}
		}
		
		Object selectedKeys[] = selectedItems.keySet().toArray();
		
		for (int i = 0; i < selectedKeys.length; i++){
			String gVal = (String)selectedKeys[i];
			JSONObject record = new JSONObject();
			record.put("GlobalVal",gVal);
			record.put("partyRelation","DESCRIPTOR");
			ArrayList LOV = (ArrayList)lov.get(gVal);
			String displayName = (String)LOV.get(0);
			String category = (String)LOV.get(1);
			record.put("name", displayName);
			record.put("category", category);
			dataList.put(record);
		}
		
		data.put("data", dataList);
		

		
		try {
			data = cm.handleRequest(data);
		} catch (Exception e) {
			log.debug("DemoSetup Error: ", e);
		}
	}
	
	public static void associateDefaultTargetValues(CriteriaMgr cm, String memberId, String partyId,HashMap exclusiveCriteria, ArrayList nonExclusiveCriteria,HashMap dependentCriteria,HashMap lov) throws JSONException{
		JSONArray dataList = new JSONArray();

		JSONObject data = new JSONObject();
		data.put("act", "associate");
		data.put("userId", memberId);
		data.put("partyId", partyId);
		
		HashMap selectedItems = new HashMap();
		//add some mutually exclusive descriptors.  These are descriptors such as Income Level for which a party should only have one value.  E.g., a party can have an income of either 50-100K or 100-150K but not both
		HashMap exc = (HashMap) exclusiveCriteria.clone();
		int numSelected = random.nextInt(exc.size());
		
		for (int i = 0; i < numSelected; i++){
			String thisExcCat = exc.keySet().toArray()[random.nextInt(exc.size())].toString();
			ArrayList listOfPossibleVals = (ArrayList)exc.get(thisExcCat);
			String gVal = (String)listOfPossibleVals.get(random.nextInt(listOfPossibleVals.size()));
			exc.remove(thisExcCat); //remove the category so it's not used more than once
			selectedItems.put(gVal, gVal);
		}
		
		//add some non-exclusive descriptors.  E.g., the user can be interested in both travel and pets
		ArrayList nonExc = (ArrayList) nonExclusiveCriteria.clone();
		
		numSelected = random.nextInt(nonExc.size()/2);
		
		
		for (int i = 0; i < numSelected; i++){
			String gVal = nonExc.get(random.nextInt(nonExc.size())).toString();
			nonExc.remove(gVal); //remove the category so it's not used more than once
			selectedItems.put(gVal, gVal);
		}
		
		Object dKeys[] = dependentCriteria.keySet().toArray();
		
		for (int i = 0; i < dKeys.length; i++){
			String thisDepCat = (String)dKeys[i];
			if (selectedItems.containsKey(thisDepCat)){
				ArrayList possibleVals = (ArrayList)dependentCriteria.get(thisDepCat);
				
				for (int j = 0; j < possibleVals.size(); j++){
					Float addDependentCat = random.nextFloat();
					if (addDependentCat > .5){
						String gVal = (String)possibleVals.get(j);
						selectedItems.put(gVal, gVal);
					}
				}
			}
		}
		
		Object selectedKeys[] = selectedItems.keySet().toArray();
		
		for (int i = 0; i < selectedKeys.length; i++){
			String gVal = (String)selectedKeys[i];
			JSONObject record = new JSONObject();
			record.put("GlobalVal",gVal);
			record.put("partyRelation","DESCRIPTOR");
			ArrayList LOV = (ArrayList)lov.get(gVal);
			String displayName = (String)LOV.get(0);
			String category = (String)LOV.get(1);
			record.put("name", displayName);
			record.put("category", category);
			dataList.put(record);
		}
		
		data.put("data", dataList);
		

		
		try {
			data = cm.handleRequest(data);
		} catch (Exception e) {
			log.debug("DemoSetup Error: ", e);
		}
	}
	
	public static void addEmailTemplate(String name, String description, String type, String template) throws JSONException{
		
		
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
		data.put("bo", "EmailTemplate");
		data.put("bc", "EmailTemplate");

		data.put("templateName",name);
		data.put("description",description);
		data.put("type",type);
		data.put("content", template);
	
		GenericMgr gm = new GenericMgr(uId);
	
		try {
			data = gm.handleRequest(data);
		} catch (Exception e) {
			log.debug("DemoSetup Error: ", e);
		}
/*
		BasicDBObject Obj = (BasicDBObject) JSON.parse(data.get("data").toString()); 
		ObjectId idObj = (ObjectId) Obj.get("_id");
		
		String Id = idObj.toString();
		
		return Id;
*/
	}
	
	
	}
