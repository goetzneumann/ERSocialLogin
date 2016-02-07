package er.sltest.components;

import com.webobjects.appserver.WOContext;
import er.sociallogin.support.GoogleEmailInformation;

public class Main extends BaseComponent {
	private GoogleEmailInformation currentEmailInformation;

	public Main(WOContext context) {
		super(context);
	}

	/**
	 * @return the currentEmailInformation
	 */
	public GoogleEmailInformation currentEmailInformation() {
		return currentEmailInformation;
	}

	/**
	 * @param currentEmailInformation the currentEmailInformation to set
	 */
	public void setCurrentEmailInformation(
			GoogleEmailInformation currentEmailInformation) {
		this.currentEmailInformation = currentEmailInformation;
	}
}
