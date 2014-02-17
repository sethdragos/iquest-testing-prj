Meta:

Narrative:
As a user
I want to perform an action
So that I can achieve a business goal

Scenario: Search for the hascode.com website on Google
Given The Google homepage
When I search for "site:hascode.com"
Then the text "site:hascode.com" is present
When I click the link "hasCode.com"
Then the text "Recent Articles" is present
And the page's URL should be "http://www.hascode.com/"