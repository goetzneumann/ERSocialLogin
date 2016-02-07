package er.sociallogin.support;

import com.webobjects.foundation.NSDictionary;

import er.extensions.foundation.ERXPropertyListSerialization;

public class TwitterUser implements ISocialLoginUser{
	private String id, name, pictureURL, location, language, screenName;
	private NSDictionary<String,Object> dataDict;
	
	@SuppressWarnings("unchecked")
	public TwitterUser(String data){
		dataDict = (NSDictionary<String,Object>)ERXPropertyListSerialization.propertyListFromJSONString(data);
		id = (String)dataDict.valueForKey("id_str");
		name = (String)dataDict.valueForKey("name");
		screenName = (String)dataDict.valueForKey("screen_name");
		location = (String)dataDict.valueForKey("location");
		language = (String)dataDict.valueForKey("lang");
		pictureURL = (String)dataDict.valueForKey("profile_image_url_https");	
	}
	
	public NSDictionary<String,Object> dataDict(){
		return dataDict;
	}
	
	public String id(){
		return id;
	}
	
	public String name(){
		return name;
	}
	
	public String pictureURL(){
		return pictureURL;
	}
	
	public String location(){
		return location;
	}
	
	public String language(){
		return language;
	}
	
	public String screenName(){
		return screenName;
	}
	
	public SocialNetwork socialNetwork(){
		return SocialNetwork.TWITTER;
	}
	
}
