package er.sltest.components;

import com.webobjects.appserver.WOActionResults;
import com.webobjects.appserver.WOContext;

import er.sltest.Session;
import er.sociallogin.support.CallBackHandler;
import er.sociallogin.support.FacebookUser;
import er.sociallogin.support.GoogleUser;
import er.sociallogin.support.ISocialLoginUser;
import er.sociallogin.support.TwitterUser;

public class SLCallBackTest extends CallBackHandler {
	
    public SLCallBackTest(WOContext context) {
        super(context);
    }

	@Override
	public void handleLoginCallback(ISocialLoginUser user) {
		if(user instanceof FacebookUser){
			((Session)session()).setFacebookUser((FacebookUser)user);
		}
		if(user instanceof TwitterUser){
			((Session)session()).setTwitterUser((TwitterUser)user);
		}
		if(user instanceof GoogleUser){
			((Session)session()).setGoogleUser((GoogleUser)user);
		}
	}

	@Override
	public WOActionResults updateAction() {
		return pageWithName(Main.class);
	}

	@Override
	public void handleLoginErrorCallback(int code, String message) {
		// TODO Auto-generated method stub
		
	}
	

}