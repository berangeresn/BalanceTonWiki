package com.ambre.wiki.helpers;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HashtagManager {

	//public static final String REGEX_HASHTAG = "/\\s([#][\\w_-]+)/g";
	public static final String REGEX_HASHTAG = "\\s([#][\\w_-]+)";
	
	/**
	 * Search all hashtags in a string
	 * @param mySearchString
	 * @return
	 */
	public static ArrayList<String> searchHashtag(String mySearchString) {
		String hashtag = null;
		if (mySearchString!=null) {
			ArrayList<String> myList = new ArrayList<String>();
			Pattern myPattern = Pattern.compile(REGEX_HASHTAG);
			Matcher myMatcher = myPattern.matcher(mySearchString);
			while(myMatcher.find()) {
				hashtag = myMatcher.group().trim();
				myList.add(hashtag);
			}
			return myList;
		}
		return null;
	}
}
