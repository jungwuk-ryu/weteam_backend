package weteam.backend.application.oauth.provider;

public interface OAuth2Provider {
    String getUsername();
    String getProviderId();
    ProviderType getProvider();
    String getEmail();
    String getName();
}
