package er.sociallogin.support;

import org.apache.commons.lang.StringUtils;

import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSDictionary;
import com.webobjects.foundation.NSMutableArray;
import com.webobjects.foundation.NSTimestamp;

import er.extensions.formatters.ERXTimestampFormatter;
import er.extensions.foundation.ERXPropertyListSerialization;

public class GoogleUser implements ISocialLoginUser{
	private String firstName;
	private String nickName;
	private String middleName;
	private String lastName;
	private String id;
	private String pictureURL;
	private Boolean verified;
	private NSArray<GoogleEmailInformation> emails;
	private String gender;
	private NSTimestamp birthday;
	private String language;
	private String location;
	private NSDictionary<String,Object> dataDict;
	
	@SuppressWarnings("unchecked")
	public GoogleUser(String data){
		dataDict = (NSDictionary<String,Object>)ERXPropertyListSerialization.propertyListFromJSONString(data);
		id = (String)dataDict.valueForKey("id");
		gender = (String)dataDict.valueForKey("gender");
		nickName = (String)dataDict.valueForKey("nickName");
		firstName = (String)dataDict.valueForKeyPath("name.givenName");
		middleName = (String)dataDict.valueForKeyPath("name.middleName");
		lastName = (String)dataDict.valueForKeyPath("name.familyName");
		location = (String)dataDict.valueForKey("location");
		String birthdayString = (String)dataDict.valueForKey("birthday");
		try{
			if(StringUtils.isNotBlank(birthdayString)){
				ERXTimestampFormatter formatter = new ERXTimestampFormatter("%Y/%m/%d");
				birthday = (NSTimestamp)formatter.parseObject(birthdayString);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		language = (String)dataDict.valueForKey("language");
		verified = (Boolean)dataDict.valueForKey("verified");
		pictureURL = (String)dataDict.valueForKeyPath("image.url");
		NSArray<NSDictionary<String,Object>> emails = (NSArray<NSDictionary<String,Object>>)dataDict.valueForKey("emails");
		if(emails!=null){
			NSMutableArray<GoogleEmailInformation> gmails = new NSMutableArray<GoogleEmailInformation>();
			for(NSDictionary<String, Object> email : emails){
				gmails.addObject(new GoogleEmailInformation(email));
			}
			this.emails = gmails.immutableClone();
		}
	}
	
	public NSDictionary<String,Object> dataDict(){
		return dataDict;
	}
	
	public NSArray<GoogleEmailInformation> emails(){
		return emails;
	}
	
	
	public String nickName(){
		return nickName;
	}
	
	public String language(){
		return language;
	}
	
	public String firstName(){
		return firstName;
	}
	
	public String middleName(){
		return middleName;
	}
	
	public String lastName(){
		return lastName;
	}
	
	public String pictureURL(){
		return pictureURL;
	}
	
	public String gender(){
		System.out.println("Gender: "+gender);
		return gender;
	}
	
	public String id(){
		return id;
	}
	
	public NSTimestamp birthday(){
		return birthday;
	}
	
	public Boolean verified(){
		return verified;
	}
	
	public String location(){
		return location;
	}
	
	public SocialNetwork socialNetwork(){
		return SocialNetwork.GOOGLE;
	}

	
}


