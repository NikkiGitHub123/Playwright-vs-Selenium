# Playwright-vs-Selenium

Playwright is a relatively new open source tool with its first version released by Microsoft in 2020, that is gaining popularity.

This repository is a part of a POC comparing Playwright with Selenium. 

**Selenium** sends each command as a separate HTTP request and receives JSON responses. After completing every request, the connection between server and client will be terminated, which needs to be re-established for the next request.

**Playwright**, on the other hand, communicates all requests through a single Web socket connection, which stays in place until test execution is completed. This reduces the points of failure and allows commands to be sent quickly on a single connection.

There are 2 test classes in this repo, both performing the same steps using both frameworks.
It was observed that, while Selenium took 29 sec to run the test, Playwright took 18 sec.

This is to prove that,
Playwright is well-suited for web applications requiring speed and reliability, while Selenium is a good choice for projects needing variety of browser compatibility and integration with other systems. 

_Disclaimer: Opinions expressed here are solely my own and do not express the views or opinions of my employer._
