package er.sociallogin.support;

import com.webobjects.appserver.WOContext;

import er.extensions.components.ERXComponent;

public abstract class GoogleLoginCallBackHandler extends ERXComponent{

	
	public GoogleLoginCallBackHandler(WOContext context) {
		super(context);
	}

	public abstract void handleGoogleLoginCallback(GoogleUser googleUser);
}
