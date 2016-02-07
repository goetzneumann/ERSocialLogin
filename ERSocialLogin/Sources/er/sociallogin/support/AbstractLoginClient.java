package er.sociallogin.support;

public interface AbstractLoginClient {

	public String loginURL();
	
	public Class<CallBackHandler> callBackHandlerClass();
	
	
}
