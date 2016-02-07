package er.sociallogin.support;

public interface ISocialLoginUser {

	public String id();
	public SocialNetwork socialNetwork();
}

enum SocialNetwork{
	FACEBOOK, TWITTER, GOOGLE;
}