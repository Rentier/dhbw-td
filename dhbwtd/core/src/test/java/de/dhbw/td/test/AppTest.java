/*  Copyright (C) 2013. All rights reserved.
 *  Released under the terms of the GNU General Public License version 3 or later.
 *  
 *  Contributors:
 *  Jan-Christoph Klie - All
 */

package de.dhbw.td.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import de.dhbw.td.test.cases.EnemyTest;
import de.dhbw.td.test.cases.GameStateTest;
import de.dhbw.td.test.cases.LevelFactoryTest;
import de.dhbw.td.test.cases.WaveControllerTest;
import de.dhbw.td.test.cases.fsm.FiniteStateMachineTest;

/**
 * Unit test suite for the dhbw-td. It should be run after each checking and each
 * compiling. All tests to run have to be specified in the annotation.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses( {  WaveControllerTest.class, LevelFactoryTest.class, GameStateTest.class, EnemyTest.class, FiniteStateMachineTest.class})
public class AppTest{


}