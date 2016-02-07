package er.sociallogin.support;

import java.util.Locale;

import org.apache.commons.lang.StringUtils;

import com.webobjects.foundation.NSDictionary;
import com.webobjects.foundation.NSTimestamp;

import er.extensions.formatters.ERXTimestampFormatter;
import er.extensions.foundation.ERXPropertyListSerialization;

public class FacebookUser implements ISocialLoginUser{
	private String email;
	private String facebook_id;
	private String firstName;
	private String middleName;
	private Boolean verified;
	private String lastName;
	private Locale locale;
	private String hometown;
	private String pictureURL;
	private String gender;
	private String id;
	private NSTimestamp birthday;
	private NSDictionary<String, Object> dataDict;
	
	@SuppressWarnings("unchecked")
	public FacebookUser(String data){
		dataDict = (NSDictionary<String,Object>)ERXPropertyListSerialization.propertyListFromJSONString(data);
		email = (String)dataDict.valueForKey("email");
		firstName = (String)dataDict.valueForKey("first_name");
		middleName = (String)dataDict.valueForKey("middle_name");
		lastName = (String)dataDict.valueForKey("last_name");
		facebook_id = (String)dataDict.valueForKey("id");
		id = (String)dataDict.valueForKey("third_party_id");
		gender = (String)dataDict.valueForKey("gender");
		verified = (Boolean)dataDict.valueForKey("verified");
		String localeString = (String)dataDict.valueForKey("locale");
		if(StringUtils.isNotBlank(localeString)&&localeString.contains("_")){
			locale = new Locale(StringUtils.substringBefore(localeString, "_"), StringUtils.substringAfter(localeString, "_"));
		}
		hometown = (String)dataDict.valueForKey("hometown");
		String birthdayString = (String)dataDict.valueForKey("birthday");
		try{
			if(StringUtils.isNotBlank(birthdayString)){
				ERXTimestampFormatter formatter = new ERXTimestampFormatter("%m/%d/%Y");
				birthday = (NSTimestamp)formatter.parseObject(birthdayString);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		if(!(Boolean)dataDict.valueForKeyPath("picture.data.is_silhouette")){
			pictureURL = (String)dataDict.valueForKeyPath("picture.data.url");
		}
	}
	
	public NSDictionary<String,Object> dataDict(){
		return dataDict;
	}

	public String email(){
		return email;
	}
	
	public String facebookId(){
		return facebook_id;
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
	
	public Locale locale(){
		return locale;
	}
	
	public String hometown(){
		return hometown;
	}
	
	public SocialNetwork socialNetwork(){
		return SocialNetwork.FACEBOOK;
	}
}
