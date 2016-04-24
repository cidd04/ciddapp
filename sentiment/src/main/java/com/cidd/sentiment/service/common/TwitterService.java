package com.cidd.sentiment.service.common;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.stereotype.Service;

import com.cidd.sentiment.dao.SocialDataDao;
import com.cidd.sentiment.model.SocialData;

@Service
public class TwitterService {

	@Value("${twitter.app.consumerKey}")
    private String consumerKey;

    @Value("${twitter.app.consumerSecret}")
    private String consumerSecret;
    
	@Value("${twitter.app.accessToken}")
    private String accessToken;

    @Value("${twitter.app.accessTokenSecret}")
    private String accessTokenSecret;
    
    @Autowired
    private SocialDataDao socialDataDao;
    
    private Twitter twitter;
    
	public Twitter getInstance() {
		if (twitter == null) {
			twitter =  new TwitterTemplate(consumerKey, consumerSecret, accessToken, accessTokenSecret);			
		}
		return twitter;
	}
	
	public void saveSocialData(SocialData socialData) {
		socialDataDao.save(socialData);
	}
	
	public List<SocialData> getSocialDataList() {
		return socialDataDao.findAll();
	}
}
