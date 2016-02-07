package er.sociallogin.support;

import org.apache.commons.lang.StringUtils;
import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.TwitterApi;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Token;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WOSession;

import er.extensions.appserver.ERXWOContext;

public class TwitterLoginClient implements AbstractLoginClient{
	private OAuthService service;
	private Token requestToken;
	private Class<CallBackHandler> callBackHandler;
	
	public TwitterLoginClient(String apiKey, String apiSecret, WOSession session, Class<CallBackHandler> callBackHandler){
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
		String callBackURL = context.directActionURLForActionNamed(SLRedirect.class.getSimpleName()+"/twitter", null);
		  service = new ServiceBuilder()
         .provider(TwitterApi.SSL.class)
         .apiKey("z2D2bJ4LlNMQQ0zKCUOgS2rBI")
         .apiSecret("txCHcZD2kDvFAD7lqMQNUIT3TybbCZZndKMhBzjP40vANudUGD").callback(callBackURL)
         .build();




	}
	
	public String loginURL(){
		  requestToken = service.getRequestToken();
		  String authURL = service.getAuthorizationUrl(requestToken);
		  authURL = StringUtils.replace(authURL, "authorize", "authenticate");
		return authURL;
	}
	
	public void signRequest(String tokenString, OAuthRequest request){
		service.signRequest(service.getAccessToken(requestToken, new Verifier(tokenString)), request);
	}
	
	public Class<CallBackHandler> callBackHandlerClass(){
		return callBackHandler;
	}
}
