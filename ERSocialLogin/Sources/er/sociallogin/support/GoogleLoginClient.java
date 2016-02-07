package er.sociallogin.support;

import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Token;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

import com.webobjects.appserver.WOSession;

import er.extensions.appserver.ERXWOContext;

public class GoogleLoginClient implements AbstractLoginClient{
	Class<CallBackHandler> callBackHandler;
	OAuthService service;
	Token requestToken;
	String sessionId;
	
	public GoogleLoginClient(String apiKey, String appSecret, WOSession session, Class<CallBackHandler> callBackHandler) {
		super();
		this.callBackHandler = callBackHandler;
		ERXWOContext context = null;
		if(session!=null){
			context = (ERXWOContext)session.context();
			sessionId = session.sessionID();
		}
		else{
			context = (ERXWOContext)ERXWOContext.newContext();
			context.session();
		}
		boolean completeURLs = context.doesGenerateCompleteURLs();
		boolean isSecure = context.secureMode();

		if(!isSecure){
			context.setSecureMode(true);
			
		}
		if(!completeURLs){
			context.generateCompleteURLs();
			
		}
		
		String callBackURL = ERXWOContext.directActionUrl(context, SLRedirect.class.getSimpleName()+"/google", Boolean.TRUE, Boolean.FALSE);
		
		if(!completeURLs){
			context.generateRelativeURLs();
		}
		if(!isSecure){
			context.setSecureMode(false);
		}
		ServiceBuilder serviceBuilder = new ServiceBuilder().provider(Google2Api.class).
				apiKey(apiKey).
				apiSecret(appSecret).
				scope("https://www.googleapis.com/auth/plus.login email").
				//scope("https://www.googleapis.com/auth/userinfo.email https://www.googleapis.com/auth/userinfo.profile").
				callback(callBackURL);
		/*if(permissions!=null&&!permissions.isEmpty()){
			serviceBuilder.scope(permissions.componentsJoinedByString(","));
		}*/
		service  = serviceBuilder.build();
		//requestToken = service.getRequestToken();
		//requestToken = service.getRequestToken();
	}
	
	public String loginURL(){
		
		return  service.getAuthorizationUrl(null)+"&state="+sessionId;
	}
	
	public void signRequest(String tokenString, OAuthRequest request){
		service.signRequest(service.getAccessToken(requestToken, new Verifier(tokenString)), request);
	}
	
	public Class<CallBackHandler> callBackHandlerClass(){
		return callBackHandler;
	}
}
