package er.sociallogin.callbackcomponents;

import org.apache.commons.lang.StringUtils;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Verb;

import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WOContext;

import er.sociallogin.support.FacebookLoginClient;
import er.sociallogin.support.FacebookUser;

public class FacebookCallbackHandler extends WOComponent {
	public FacebookUser user;
    public FacebookCallbackHandler(WOContext context) {
        super(context);
    }
    
    @Override
    public void awake() {
    	super.awake();
    	String accessTokenString = context().request().stringFormValueForKey("code");
    	if(StringUtils.isNotBlank(accessTokenString)){
    		OAuthRequest request = new OAuthRequest(Verb.GET, "https://graph.facebook.com/me?fields=picture,email,last_name,middle_name,first_name,birthday,verified,locale,hometown,third_party_id,birthday,gender");
    		loginClient().signRequest(accessTokenString, request);
    		Response response = request.send();
    		if(response.getCode()==200){
    			user = new FacebookUser(response.getBody());
    		}
       }
    }
    
    public FacebookLoginClient loginClient(){
    	FacebookLoginClient client = (FacebookLoginClient)valueForBinding("loginClient");
    	if(client==null){
    		client = (FacebookLoginClient)session().objectForKey(FacebookLoginClient.class.getName());
    	}
    	return client;
    }
}