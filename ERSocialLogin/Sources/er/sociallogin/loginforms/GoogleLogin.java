package er.sociallogin.loginforms;

import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WOSession;

import er.sociallogin.support.AbstractLoginClient;
import er.sociallogin.support.GoogleLoginClient;

public class GoogleLogin extends AbstractLogin {

    public GoogleLogin(WOContext context) {
        super(context);
    }
    

    
   
    
    public String abstractLoginClientImplementation(){
    	return GoogleLoginClient.class.getName();
    }
    
    public AbstractLoginClient createClient(){
        WOSession session = null;
        if(context().hasSession()){
        	session = session();
        }
        return new GoogleLoginClient("318261201381-ou5424hm56t8dctk6ktlbs5vmjrfmutj.apps.googleusercontent.com", "7XptugHNEe8FjFcn1ZqwgxCn",session,callBackHandlerClass());
    }
}