package er.sociallogin.support;

import com.webobjects.appserver.WOContext;

import er.extensions.components.ERXComponent;

public abstract class TwitterLoginCallBackHandler extends ERXComponent{

	public TwitterLoginCallBackHandler(WOContext context) {
		super(context);

	}

	public abstract void handleTwitterLoginCallback(TwitterUser twitterUser);
}
