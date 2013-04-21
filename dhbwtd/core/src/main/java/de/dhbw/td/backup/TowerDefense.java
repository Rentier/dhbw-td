/*  Copyright (C) 2013 by Jan-Christoph Klie, Inc. All rights reserved.
 *  Released under the terms of the GNU General Public License version 3 or later.
 *  
 *  Contributors:
 *  Jan-Christoph Klie - All
 *  Tobias Roeding - Add support for changing to the next level/wave when finished current, Add menu support
 *  Benedict Holste - Clean up and refactoring
 */

package de.dhbw.td.backup;

import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;
import static playn.core.PlayN.keyboard;
import static playn.core.PlayN.mouse;
import de.dhbw.td.core.secret.CheatModule;
import playn.core.Game;
import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.Surface;
import playn.core.SurfaceLayer;

public class TowerDefense implements Game {

	public static final String PATH_LEVELS = "levels/";
	public static final String PATH_IMAGES = "images/";
	public static final String PATH_WAVES = "waves/";
	public static final String PATH_TOWERS = "tower/";
	public static final String PATH_MENU = "menu/";
	
	private static MouseObservable mouse;
	private static KeyboardObservable keyboard;

	private ImageLayer BACKGROUND_LAYER;
	
	private SurfaceLayer TILE_LAYER;	
	private SurfaceLayer LABEL_LAYER;
	private SurfaceLayer ENEMY_LAYER;
	private SurfaceLayer TOWER_LAYER;
	private SurfaceLayer MENU_LAYER;
	private SurfaceLayer HUD_LAYER;

	private GameState stateOftheWorld;
	private HUD hud;
	
	private MainMenu mainMenu;
	private IngameMenu ingameMenu;
	
	private CheatModule module;
	
	@Override
	public void init() {
		
		/*
		 * Register listener
		 */

		mouse = new MouseObservable();
		mouse().setListener(mouse);

		keyboard = new KeyboardObservable();
		keyboard().setListener(keyboard);
		
		/*
		 * Init components
		 */
		
		stateOftheWorld = new GameState();
		mainMenu = new MainMenu(stateOftheWorld);
		ingameMenu = new IngameMenu(stateOftheWorld);
		hud = new HUD(stateOftheWorld, ingameMenu);
		module = new CheatModule(stateOftheWorld, hud);

		/*
		 * Layer
		 */

		int width = stateOftheWorld.getCurrentlevel().width();
		int height = stateOftheWorld.getCurrentlevel().height();

		// BACKGROUND layer
		Image bg = assets().getImageSync("tiles/white.bmp");
		BACKGROUND_LAYER = graphics().createImageLayer(bg);
		BACKGROUND_LAYER.setScale(width, height);
		graphics().rootLayer().add(BACKGROUND_LAYER);

		// TILE layer
		TILE_LAYER = graphics().createSurfaceLayer(width, height);
		graphics().rootLayer().add(TILE_LAYER);

		// ENEMY layer
		ENEMY_LAYER = graphics().createSurfaceLayer(width, height);
		graphics().rootLayer().add(ENEMY_LAYER);
		
		// TOWER layer
		TOWER_LAYER = graphics().createSurfaceLayer(width, height);
		graphics().rootLayer().add(TOWER_LAYER);

		// HUD layer
		HUD_LAYER = graphics().createSurfaceLayer(width, height);
		graphics().rootLayer().add(HUD_LAYER);
		
		// LABEL layer
		LABEL_LAYER = graphics().createSurfaceLayer(width, height);
		graphics().rootLayer().add(LABEL_LAYER);
		
		MENU_LAYER = graphics().createSurfaceLayer(width, height);
		graphics().rootLayer().add(MENU_LAYER);
	}

	@Override
	public void paint(float alpha) {
		if(false) {
			mainMenu.draw(MENU_LAYER.surface());
		} else {
			
			Surface enemySurface = ENEMY_LAYER.surface();
			stateOftheWorld.drawEnemies(enemySurface);
			
			Surface menuSurface = MENU_LAYER.surface();
			ingameMenu.draw(menuSurface);
			
			Surface towerSurface = TOWER_LAYER.surface();
			stateOftheWorld.drawTowers(towerSurface);
			
			// Draw hud
			hud.drawIcons(HUD_LAYER.surface());
			Surface labelSurface = LABEL_LAYER.surface();
			labelSurface.clear();
			
			hud.drawCredit(labelSurface);
			hud.drawLifes(labelSurface);
			hud.drawSemester(labelSurface);
			
			stateOftheWorld.changeProcessed();
			
			if(stateOftheWorld.hasNewLevel()) {
				Surface tileSurface = TILE_LAYER.surface();
				stateOftheWorld.drawLevel(tileSurface);
			}
		}
	}

	@Override
	public void update(float delta) {
		if (!stateOftheWorld.isPaused()) {
			stateOftheWorld.update(delta);
		}
	}

	@Override
	public int updateRate() {
		return 24;
	}

	/**
	 * 
	 * @return
	 */
	public static MouseObservable getMouse() {
		return mouse;
	}

	/**
	 * 
	 * @return 
	 */
	public static KeyboardObservable getKeyboard() {
		return keyboard;
	}
}