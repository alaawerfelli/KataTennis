/**
 * 
 */
package com.sg.katatennis.cucumber;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

/**
 * 
 * @author awerfelli
 */
@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/ressources/Features/check_deuce_simplegame.feature", glue = "com.sg.katatennis.cucumber.steps")
public class LauncherSimpleGameTest {

}
