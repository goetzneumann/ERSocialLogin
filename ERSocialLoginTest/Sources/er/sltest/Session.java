package er.sltest;

import er.extensions.appserver.ERXSession;
import er.sociallogin.support.FacebookUser;
import er.sociallogin.support.GoogleUser;
import er.sociallogin.support.TwitterUser;

public class Session extends ERXSession {
	private static final long serialVersionUID = 1L;
	FacebookUser facebookUser;
	private TwitterUser twitterUser;
	private GoogleUser googleUser;
	
	public Session() {
	}
	
	@Override
	public Application application() {
		return (Application)super.application();
	}
	
	public FacebookUser facebookUser(){
		return facebookUser;
	}
	
	public void setFacebookUser(FacebookUser user){
		facebookUser = user;
	}

	public TwitterUser twitterUser(){
		return twitterUser;
	}
	
	public void setTwitterUser(TwitterUser user) {
		twitterUser = user;
		
	}

	
	public GoogleUser googleUser(){
		return googleUser;	
	}
	
	public void setGoogleUser(GoogleUser user) {
		googleUser = user;	
	}
	
}
