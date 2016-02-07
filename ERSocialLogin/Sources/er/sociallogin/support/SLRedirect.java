package er.sociallogin.support;

import org.apache.commons.lang.StringUtils;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Verb;

import com.webobjects.appserver.WOActionResults;
import com.webobjects.appserver.WORequest;

import er.extensions.appserver.ERXDirectAction;
import er.extensions.appserver.ERXRedirect;

public class SLRedirect extends ERXDirectAction {

	public SLRedirect(WORequest r) {
		super(r);
	}
	
	public WOActionResults facebookAction(){
		CallBackHandler page = pageWithName(facebookLoginClient().callBackHandlerClass());
    	String accessTokenString = request().stringFormValueForKey("code");
    	if(StringUtils.isNotBlank(accessTokenString)){
    		OAuthRequest request = new OAuthRequest(Verb.GET, "https://graph.facebook.com/me?fields=picture,email,last_name,middle_name,first_name,birthday,verified,locale,hometown,third_party_id,birthday,gender");
    		facebookLoginClient().signRequest(accessTokenString, request);
    		Response response = request.send();
    		
    		if(response.getCode()==200){
    			page.handleLoginCallback(new FacebookUser(response.getBody()));
    		}
    		else{
    			page.handleLoginErrorCallback(response.getCode(),response.getBody());
    		}
       }
    	return page;
	}
	
	public WOActionResults twitterAction(){
		//String oauth_token = (String)request().stringFormValueForKey("oauth_token");
		
		String oauth_verifier = request().stringFormValueForKey("oauth_verifier");
		    OAuthRequest request = new OAuthRequest(Verb.GET, "https://api.twitter.com/1.1/account/verify_credentials.json");
		    twitterLoginClient().signRequest(oauth_verifier, request);
		    Response response = request.send();

		    
		    CallBackHandler page = pageWithName(twitterLoginClient().callBackHandlerClass());
		    if(response.getCode()==200){
		    page.handleLoginCallback(new TwitterUser(response.getBody()));
		    }
		    else{
		    	page.handleLoginErrorCallback(response.getCode(),response.getBody());
		    }
		    return page;
		
	}
	
	public WOActionResults googleAction(){
    	String state = request().stringFormValueForKey("state");
    	if(!context().hasSession()&&state!=null){
    		ERXRedirect redirect = new ERXRedirect(context());
    		redirect.setUrl(request().uri().replace("state=", "wosid="));
    		return redirect;
    	}
    	String accessTokenString = request().stringFormValueForKey("code");

		CallBackHandler page = pageWithName(googleLoginClient().callBackHandlerClass());
    	if(StringUtils.isNotBlank(accessTokenString)){
    		OAuthRequest request = new OAuthRequest(Verb.GET,"https://www.googleapis.com/plus/v1/people/me" /*?key={YOUR_API_KEY}*/);
    		googleLoginClient().signRequest(accessTokenString, request);
    		Response response = request.send();
    		if(response.getCode()==200){
    			page.handleLoginCallback(new GoogleUser(response.getBody()));
    		}
    		else{
    			page.handleLoginErrorCallback(response.getCode(),response.getBody());
    		}
       }
    	return page;
	}
	
    public FacebookLoginClient facebookLoginClient(){
    	FacebookLoginClient client = null;
    	if(client==null){
    		client = (FacebookLoginClient)session().objectForKey(FacebookLoginClient.class.getName());
    	}
    	return client;
    }
    
    public TwitterLoginClient twitterLoginClient(){
    	TwitterLoginClient client = null;
    	if(client==null){
    		client = (TwitterLoginClient)session().objectForKey(TwitterLoginClient.class.getName());
    	}
    	return client;
    }
    
    public GoogleLoginClient googleLoginClient(){
    	GoogleLoginClient client = null;
    	if(client==null){
    		client = (GoogleLoginClient)session().objectForKey(GoogleLoginClient.class.getName());
    	}
    	return client;
    }
    

}
