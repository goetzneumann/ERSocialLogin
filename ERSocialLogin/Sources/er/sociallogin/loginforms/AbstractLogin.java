package er.sociallogin.loginforms;

import com.webobjects.appserver.WOActionResults;
import com.webobjects.appserver.WOContext;

import er.extensions.appserver.ERXRedirect;
import er.extensions.components.ERXComponent;
import er.sociallogin.support.AbstractLoginClient;
import er.sociallogin.support.CallBackHandler;

public abstract class AbstractLogin extends ERXComponent{
	private AbstractLoginClient client;
	
	public AbstractLogin(WOContext context) {
		super(context);
	}
	
    public abstract String abstractLoginClientImplementation();
    
    public abstract AbstractLoginClient createClient();

	@Override
	public boolean synchronizesVariablesWithBindings() {
		return false;
	}
	
	@SuppressWarnings("unchecked")
    protected Class<CallBackHandler> callBackHandlerClass() {
		Class<CallBackHandler> theClass = (Class<CallBackHandler>)valueForBinding("callBackHandlerClass");
    	if(theClass==null&&callBackHandlerClassName()!=null){
    		try{
    			theClass = (Class<CallBackHandler>)Class.forName(callBackHandlerClassName());
    		}catch (Exception e) {
				e.printStackTrace();
			}
    	}

    	return theClass;
	}
    
    public WOActionResults login(){
		ERXRedirect loginRedirect = new ERXRedirect(context());
		loginRedirect.setUrl(client().loginURL());
    	return loginRedirect;
    }
    
    public AbstractLoginClient client(){
    	if(client==null){
    		client = (AbstractLoginClient)session().objectForKey(abstractLoginClientImplementation());
    		if(client==null){
    			client = createClient();
    			session().setObjectForKey(client, abstractLoginClientImplementation());
    		}
    	}
    	return client;
    }
    
    private String callBackHandlerClassName(){
    	return (String)valueForBinding("callBackHandlerClassName");
    }
    

}
