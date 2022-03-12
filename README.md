# Team8410RapidReact

Oyster River Robotics code for FIRST robotics.

## Developers

* Code changes are expected to be made through pull requests from a feature branch to `main`.
* Pull requests run automated sanity checks on the proposed changes using [git workflows]](.github/workflows).
* Pull requests are expected to be reviewed by a teammate before merging.

### Common Issues

#### Pull request `checks have failed`

If your pull request is blocked by a message like `All checks have failed` and `Merging is blocked`:

* Open the pull request in the browser.
* Click on "Details" which should bring you to the output of the failed check to figure out what needs to be fixed on your branch.

#### Running gradle commands

To run a command like `./gradlew spotlessApply`, 

* From the WPI editor:
* Press CTRL+` which will open the terminal in the project.
* Type the gradle command and press enter.
