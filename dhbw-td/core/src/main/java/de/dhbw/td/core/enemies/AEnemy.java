/*  Copyright (C) 2013 by Martin Kiessling, Tobias Roeding Inc. All rights reserved.
 *  Released under the terms of the GNU General Public License version 3 or later.
 *  
 *  Contributors:
 *  Martin Kiessling, Tobias Roeding - All
 */

package de.dhbw.td.core.enemies;

import static playn.core.PlayN.assets;

import java.awt.Point;
import java.util.Queue;

import playn.core.Image;
import playn.core.Surface;
import de.dhbw.td.core.TowerDefense;
import de.dhbw.td.core.game.IDrawable;
import de.dhbw.td.core.game.IUpdateable;

/**
 * abstract class for an enemy
 * 
 * @author Martin Kiessling, Tobias Roeding
 * @version 1.0
 * 
 */
public abstract class AEnemy implements IDrawable, IUpdateable {
	protected int maxHealth;
	protected int curHealth;
	protected boolean alive;
	protected double speed;
	protected int bounty;
	protected int penalty;
	protected EEnemyType enemyType;
	protected Queue<Point> waypoints;
	protected Point currentPosition;

	public enum EEnemyType {
		Math(0), TechInf(1), Code(2), TheoInf(3), Wiwi(4), Social(5);

		public final int value;

		private EEnemyType(int value) {
			this.value = value;
		}
	}

	@Override
	public void draw(Surface surf) {
		Image mathTowerImage = assets().getImageSync(TowerDefense.PATH_TOWERS + "math.png");
		surf.drawImage(mathTowerImage, currentPosition.x, currentPosition.y);
	}

	@Override
	public void update(double delta) {

	}

	/**
	 * 
	 * @return current position as Point
	 */
	public Point getCurrentPosition() {
		return currentPosition;
	}

	/**
	 * 
	 * @param newPosition
	 *            new position for enemy as Point
	 */
	public void setCurrentPosition(Point newPosition) {
		this.currentPosition = newPosition;
	}

	/**
	 * 
	 * @return current Health as integer
	 */
	public int getCurHealth() {
		return curHealth;
	}

	/**
	 * 
	 * @param curHealth
	 *            set current Health
	 */
	public void setCurHealth(int curHealth) {
		this.curHealth = curHealth;
	}

	/**
	 * 
	 * @return speed as double
	 */
	public double getSpeed() {
		return speed;
	}

	/**
	 * 
	 * @param speed
	 *            set speed as double
	 */
	public void setSpeed(double speed) {
		this.speed = speed;
	}

	/**
	 * 
	 * @return get maximum health as integer
	 */
	public int getMaxHealth() {
		return maxHealth;
	}

	/**
	 * 
	 * @return boolean if enemy is alive
	 */
	public boolean isAlive() {
		return alive;
	}

	/**
	 * 
	 * @return get bounty of enemy as integer
	 */
	public int getBounty() {
		return bounty;
	}

	/**
	 * 
	 * @return get penalty of enemy as integer
	 */
	public int getPenalty() {
		return penalty;
	}

	/**
	 * 
	 * @return get enemy type as EEnemyType
	 */
	public EEnemyType getEnemyType() {
		return enemyType;
	}

	/**
	 * 
	 * @return get waypoint queue as Queue<Point>
	 */
	public Queue<Point> getWaypoints() {
		return waypoints;
	}
}
