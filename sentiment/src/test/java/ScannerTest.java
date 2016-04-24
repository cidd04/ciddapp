import java.util.List;
import java.util.Scanner;

import javax.inject.Inject;

import org.junit.Test;

import com.cidd.sentiment.config.WebAppConfigurationAware;
import com.cidd.sentiment.model.SocialData;
import com.cidd.sentiment.service.common.TwitterService;

public class ScannerTest extends WebAppConfigurationAware {

	@Inject
	TwitterService twitterService;

	@Test
	public void saveProductTest() {
		int start = 0;
		int offset = 0;
		int count = 100;

		List<SocialData> data = twitterService.getSocialDataList();
		for (start = 0; start < offset + count; start++) {
			SocialData sd = data.get(start);
			System.out.println(sd.getText());
			String sentiment;
			Scanner scanIn = new Scanner(System.in);
			sentiment = scanIn.nextLine();
			sd.setSentiment(Integer.valueOf(sentiment.trim()));
			twitterService.saveSocialData(sd);
			scanIn.close();
		}
		System.out.println("start: " + start);
		System.out.println("offset: " + offset);

	}
}