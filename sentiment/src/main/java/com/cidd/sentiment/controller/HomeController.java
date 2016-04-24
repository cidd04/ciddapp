package com.cidd.sentiment.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cidd.sentiment.model.SocialData;
import com.cidd.sentiment.service.business.UserService;
import com.cidd.sentiment.service.common.MailerService;
import com.cidd.sentiment.service.common.TwitterService;

@Controller
public class HomeController {

	@Autowired
	UserService userService;

	@Autowired
	MailerService mailerService;
	
	@Autowired
	TwitterService twitterService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpServletRequest request) {


		return "homeSignedIn";
	}
	
	public void getData() {
		Twitter twitter = twitterService.getInstance();
		
		Long maxId = 642153605148078079L; //0L;
		Long sinceId = 0L;//0L;
		Integer pageSize = 200;
		Boolean isLoopFinished = false;
		//String name = "2TradeAsia";
		//String name = "PHStocks";
		//String name = "AprilLeeTan";
		//String name = "UTradePH";
		//String name = "COLFinancial";
		//String name = "ABCapitalGroup";
		//String name = "Meridian_Sec";	
		//String name = "MyREPrealty";
		//String name = "BPItrade";
		//String name = "itradeph";	
		//String name = "InquirerBiz"; //ulitin kasi malaki. gumamit ng since_id para makuha lahat
		//String name = "bilyonaryo_ph";
		//String name = "TRCInsider"; //last max_id: 642153605148078079 //pg 34550 mongodb
		//String name = "PhStockExchange";finished at 20:09
		//String name = "wddeguzman";
		//String name = "firstmetrosec";
		//String name = "MakeTradePH"; //613172297042542591
		String name = "philippinestock";
		//
		
		
		List<Tweet> tweets;
		
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy");
		Date lastDate = null;
		try {
			lastDate = dateFormatter.parse("2015");
			
			while (!isLoopFinished) {
				if (maxId == 0) {
					tweets = twitter.timelineOperations().getUserTimeline(name, pageSize);
				} else {
					tweets = twitter.timelineOperations().getUserTimeline(name, pageSize, sinceId, maxId);
				}
				if (CollectionUtils.isNotEmpty(tweets)) {
					Tweet lastTweet = null;
					for (Tweet tweet : tweets) {
						
						if (tweet.getCreatedAt().before(lastDate)) {
							isLoopFinished = true;
							break;
						}
						
						SocialData socialData = new SocialData();
						socialData.setType("TWITTER");
						socialData.setName(name);
						socialData.setText(tweet.getText());
						socialData.setCreatedDate(tweet.getCreatedAt());
						twitterService.saveSocialData(socialData);
						lastTweet = tweet;
						
					}
					if (lastTweet != null) {
						maxId = lastTweet.getId() - 1L;				
					}
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
		}
		

		System.out.print("last max_id: " + maxId);
	}

}
