# WxIsTestTools - Toolset for Flow Language Based Test Automation

The provided package may be mounted ephmerally for test execution, in moanual or automated manner.
Although not a very frequent pattern, the package may also be used for production testing, however in this case it should be spun in dedicated probes.

## Quick start for verification

Go into `run-configurations/is-test-tools-dev1` folder and initialize a file called '.env' as in the provided example. Alternatively ensure that the required environment variables are provided.

Start the docker compose project in `run-configurations/is-test-tools-dev1`.

You may see an example run with its results at the below URL

`http://host.docker.internal:40155/invoke/wx.isUtilities.test.automation/testAutom1`

This example is a FAILED test, showing various ways the differences can manifest between the expected and the actual values.

Documents have a `+` sign in front of them, indicating they can be expanded.

## How to use

This package can be used to test flow services present on the same IS/MSR instance or some other remote instance. In the first case it can work as a unit testing capability, in the second as a service testing capability.

Generally, for local unit testing, as shown in this project, if `serviceA` is the service to be tested, present in `packageA`, then generate a `packageALocalTest` package and a test harness flow service following the model flow `wx.isUtilities.test.automation:testAutom1`. This harness hill have to prepare all the input the data for the service `wx.isUtilities.testAutomation:invokeServiceAndCheckOutput`, which will actually call the tested service and discuss the results in detail the outcome. It's output will be presented on web pages by the means of the template `testAutomationDifferences`
