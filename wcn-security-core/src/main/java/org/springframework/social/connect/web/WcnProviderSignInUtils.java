package org.springframework.social.connect.web;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.ProviderSignInAttempt;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.context.request.RequestAttributes;

/**
 * Helper methods that support provider user sign-in scenarios.
 * @author Musk
 */
public class WcnProviderSignInUtils {

    private SessionStrategy sessionStrategy;
    private ConnectionFactoryLocator connectionFactoryLocator;
    private UsersConnectionRepository connectionRepository;


    public WcnProviderSignInUtils( ConnectionFactoryLocator connectionFactoryLocator,UsersConnectionRepository connectionRepository) {
        this(new HttpSessionSessionStrategy(),connectionFactoryLocator,connectionRepository);
    }

    public WcnProviderSignInUtils(SessionStrategy sessionStrategy,ConnectionFactoryLocator connectionFactoryLocator,UsersConnectionRepository connectionRepository) {
        this.sessionStrategy = sessionStrategy;
        this.connectionFactoryLocator = connectionFactoryLocator;
        this.connectionRepository = connectionRepository;
    }

    /**
     * Get the connection to the provider user the client attempted to sign-in as.
     * Using this connection you may fetch a {@link Connection#fetchUserProfile() provider user profile} and use that to pre-populate a local user registration/signup form.
     * You can also lookup the id of the provider and use that to display a provider-specific user-sign-in-attempt flash message e.g. "Your Facebook Account is not connected to a Local account. Please sign up."
     * Must be called before handlePostSignUp() or else the sign-in attempt will have been cleared from the session.
     * Returns null if no provider sign-in has been attempted for the current user session.
     * @param request the current request attributes, used to extract sign-in attempt information from the current user session
     * @return the connection
     */
    public Connection<?> getConnectionFromSession(RequestAttributes request) {
        ProviderSignInAttempt signInAttempt = getProviderUserSignInAttempt(request);
        return signInAttempt != null ? signInAttempt.getConnection(connectionFactoryLocator) : null;
    }

    /**
     * Add the connection to the provider user the client attempted to sign-in with to the new local user's set of connections.
     * Should be called after signing-up a new user in the context of a provider sign-in attempt.
     * In this context, the user did not yet have a local account but attempted to sign-in using one of his or her existing provider accounts.
     * Ensures provider sign-in attempt session context is cleaned up.
     * Does nothing if no provider sign-in was attempted for the current user session (is safe to call in that case).
     * @param userId the local application's user ID
     * @param request the current request attributes, used to extract sign-in attempt information from the current user session
     */
    public void doPostSignUp(String userId, RequestAttributes request) {
        ProviderSignInAttempt signInAttempt = (ProviderSignInAttempt) sessionStrategy.getAttribute(request, ProviderSignInAttempt.SESSION_ATTRIBUTE);
        if (signInAttempt != null) {
            signInAttempt.addConnection(userId,connectionFactoryLocator,connectionRepository);
//            sessionStrategy.removeAttribute(request,ProviderSignInAttempt.SESSION_ATTRIBUTE);
        }
    }

    // internal helpers

    private ProviderSignInAttempt getProviderUserSignInAttempt(RequestAttributes request) {
        return (ProviderSignInAttempt)sessionStrategy.getAttribute(request, ProviderSignInAttempt.SESSION_ATTRIBUTE);
    }

}
