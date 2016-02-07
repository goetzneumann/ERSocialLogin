package er.sociallogin.loginforms;

import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WOSession;

import er.sociallogin.support.AbstractLoginClient;
import er.sociallogin.support.TwitterLoginClient;

public class TwitterLogin extends AbstractLogin {
	
    public TwitterLogin(WOContext context) {
        super(context);
    }
    
    
	@Override
	public String abstractLoginClientImplementation() {
		return TwitterLoginClient.class.getName();
	}
	
	@Override
	public AbstractLoginClient createClient() {
		   WOSession session = null;
	        if(context().hasSession()){
	        	session = session();
	        }
	        return new TwitterLoginClient("z2D2bJ4LlNMQQ0zKCUOgS2rBI", "txCHcZD2kDvFAD7lqMQNUIT3TybbCZZndKMhBzjP40vANudUGD",session,callBackHandlerClass());
	        
	}
    


}