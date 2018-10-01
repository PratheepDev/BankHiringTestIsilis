Feature: test

  Scenario: a client should be able to read his accounts
  	Given I am a client with '100.0' on my account 'TEST_002'
  	When  I check my account 'TEST_002' 
  	Then  My balance should be '100.0'
  	
  Scenario: a client should be able to make a deposit on his accounts
  	Given I am a client  with '100.0' on my account 'TEST_002' who wants to deposit
  	When  I deposit '10' on my account 'TEST_002' 
  	Then  My balance should be '110.0' after deposit
 
   Scenario: a client should be able to make a withdraw on his accounts
  	Given I am a client with '100.0' on my account 'TEST_002' who wants to withdraw
  	When  I withdraw '10' on my account 'TEST_002' 
  	Then  My balance should be '90.0' after withdraw
   	