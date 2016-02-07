package er.sociallogin.loginforms;

import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WOSession;
import com.webobjects.foundation.NSArray;

import er.sociallogin.support.AbstractLoginClient;
import er.sociallogin.support.FacebookLoginClient;

public class FacebookLogin extends AbstractLogin {
    public FacebookLogin(WOContext context) {
        super(context);
 
    }
	@Override
	public String abstractLoginClientImplementation() {
		return FacebookLoginClient.class.getName();
	}
	
	@Override
	public AbstractLoginClient createClient() {
		WOSession session = null;
        if(context().hasSession()){
        	session = session();
        }
        return new FacebookLoginClient("442207432588692", "eb4110e0d682f8759538fc4c95bada21",new NSArray<String>("email","user_friends"),session,callBackHandlerClass());

	}
    

 
    
    
}