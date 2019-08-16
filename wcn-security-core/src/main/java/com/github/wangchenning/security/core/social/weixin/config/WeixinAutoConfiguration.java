/**
 * 
 */
package com.github.wangchenning.security.core.social.weixin.config;

import com.github.wangchenning.security.core.properties.SecurityProperties;
import com.github.wangchenning.security.core.properties.WeixinProperties;
import com.github.wangchenning.security.core.social.weixin.connect.WeixinConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactory;

/**
 * 微信登录配置
 * 
 * @author zhailiang
 *
 */
@Configuration
@ConditionalOnProperty(prefix = "wcn.security.social.weixin", name = "app-id")
public class WeixinAutoConfiguration extends SocialConfigurerAdapter {

	@Autowired
	private SecurityProperties securityProperties;

	@Override
	public void addConnectionFactories(ConnectionFactoryConfigurer configurer,
									   Environment environment) {
		configurer.addConnectionFactory(createConnectionFactory());
	}

	protected ConnectionFactory<?> createConnectionFactory() {
		WeixinProperties weixinConfig = securityProperties.getSocial().getWeixin();
		return new WeixinConnectionFactory(weixinConfig.getProviderId(), weixinConfig.getAppId(),
				weixinConfig.getAppSecret());
	}
	
//	@Bean({"connect/weixinConnect", "connect/weixinConnected"})
//	@ConditionalOnMissingBean(name = "weixinConnectedView")
//	public View weixinConnectedView() {
//		return new ImoocConnectView();
//	}
	
}
