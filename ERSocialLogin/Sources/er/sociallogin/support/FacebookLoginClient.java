package er.sociallogin.support;

import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.FacebookApi;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Token;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WOSession;
import com.webobjects.foundation.NSArray;

import er.extensions.appserver.ERXWOContext;

public class FacebookLoginClient implements AbstractLoginClient{
	private OAuthService service;
	Token requestToken;
	private Class<CallBackHandler> callBackHandler;
	
	public FacebookLoginClient(String apiKey, String appSecret, NSArray<String> permissions, WOSession session, Class<CallBackHandler> callBackHandler) {
		super();
		this.callBackHandler = callBackHandler;
		WOContext context = null;
		if(session!=null){
			context = session.context();
		}
		else{
			context = ERXWOContext.newContext();
			context.session();
		}
		boolean completeURLs = context.doesGenerateCompleteURLs();
		if(!completeURLs){
			context.generateCompleteURLs();
		}
		String callBackURL = context.directActionURLForActionNamed(SLRedirect.class.getSimpleName()+"/facebook", null);
		if(!completeURLs){
			context.generateRelativeURLs();
		}
		ServiceBuilder serviceBuilder = new ServiceBuilder().provider(FacebookApi.class).
				apiKey(apiKey).
				apiSecret(appSecret).
				callback(callBackURL);
		if(permissions!=null&&!permissions.isEmpty()){
			serviceBuilder.scope(permissions.componentsJoinedByString(","));
		}
		service  = serviceBuilder.build();
		//requestToken = service.getRequestToken();
	}
	
	public String loginURL(){
		return  service.getAuthorizationUrl(null)+"&display=popup";
	}
	
	public void signRequest(String tokenString, OAuthRequest request){
		service.signRequest(service.getAccessToken(null, new Verifier(tokenString)), request);
	}
	
	public Class<CallBackHandler> callBackHandlerClass(){
		return callBackHandler;
	}
	
}
