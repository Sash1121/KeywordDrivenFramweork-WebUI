package com.qa.hs.keyword.tests;

import org.testng.annotations.Test;

import com.qa.hs.keyword.engine.KeywordEngine;

public class LoginTest {
	
	public KeywordEngine keyEng;
	
	@Test
	public void loginTest() throws Exception {
		keyEng = new KeywordEngine();
		keyEng.startExecution("login");
	}

}
