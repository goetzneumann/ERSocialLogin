package er.sociallogin.support;

import com.webobjects.appserver.WOActionResults;
import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WORequest;
import com.webobjects.appserver.WOResponse;

import er.extensions.appserver.ERXResponseRewriter;
import er.extensions.components.ERXComponent;

public abstract class FacebookLoginCallBackHandler extends ERXComponent{

	public FacebookLoginCallBackHandler(WOContext context) {
		super(context);
	}
	
	public abstract void handleFacebookLoginCallback(FacebookUser facebookUser);
	
	public abstract WOActionResults updateAction();
	
	@Override
	public WOActionResults invokeAction(WORequest request, WOContext context) {
		if(context().senderID().equals(context().elementID()+".0")){
			return updateAction();
		}
		return super.invokeAction(request, context);
	}
	@Override
	public void appendToResponse(WOResponse response, WOContext context) {
		super.appendToResponse(response, context);
		ERXResponseRewriter.addScriptCodeInHead(response, context, "opener.location.href = '"+context().componentActionURL()+".0';window.close();");
	}

}
