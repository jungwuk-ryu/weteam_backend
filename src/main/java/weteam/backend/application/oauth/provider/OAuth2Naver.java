package weteam.backend.application.oauth.provider;

import java.util.Map;

public class OAuth2Naver implements OAuth2Provider {
    private Map<String, Object> attributes; // oauth2User.getAttributes();

    public OAuth2Naver(Map<String, Object> attributes) {
        this.attributes = (Map<String, Object>) attributes.get("response");
    }

    @Override
    public String getProviderId() {
        return attributes.get("id").toString();
    }

    @Override
    public ProviderType getProvider() {
        return ProviderType.NAVER;
    }

    @Override
    public String getName() {
        return attributes.get("name").toString();
    }
}