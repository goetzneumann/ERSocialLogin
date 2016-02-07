package er.sociallogin.support;

import com.webobjects.foundation.NSDictionary;

public class GoogleEmailInformation {
	private String email;
	private String type;
	private Boolean primary;
	
	protected GoogleEmailInformation(NSDictionary<String,Object> data){
		this.email = (String)data.objectForKey("value");
		this.type = (String)data.objectForKey("type");
		this.primary = (Boolean)data.objectForKey("primary");
	}
	
	public String email(){
		return email;
	}
	
	public String type(){
		return type;
	}
	
	public Boolean primary(){
		return primary;
	}
}
