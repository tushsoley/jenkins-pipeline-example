Feature:basic test for Cryptopia with docker and pipeline example
  Background: application is launched
    Given application is launched
  @test
  Scenario: make claim without captcha
    When user select to make a claim
#    Then user make a claim without captcha