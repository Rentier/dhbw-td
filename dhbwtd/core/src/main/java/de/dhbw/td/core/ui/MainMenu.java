/*  Copyright (C) 2013. All rights reserved.
 *  Released under the terms of the GNU General Public License version 3 or later.
 *  
 *  Contributors:
 *  Benedict Holste, Martin Kießling - All
 */

package de.dhbw.td.core.ui;

import static playn.core.PlayN.log;
import static de.dhbw.td.core.resources.EMenuImage.*;

import java.util.LinkedList;
import java.util.List;

import playn.core.Keyboard.Event;
import playn.core.Mouse.ButtonEvent;
import playn.core.Surface;
import de.dhbw.td.core.util.ICallback;

/**
 * UI class implementing the main menu
 */
public class MainMenu implements IDrawable, IUIEventListener {

	private final int BTN_NEWGAME_Y = 278;
	private final int BTN_HELP_Y = 365;
	private final int BTN_CREDITS_Y = 452;
	private final int BTN_QUITGAME_Y = 539;

	private List<Button> buttons;

	private boolean showHelp;
	private boolean showCredits;

	/**
	 * Constructor
	 */
	public MainMenu() {
		buttons = new LinkedList<Button>();
		createButtons();
		showHelp = false;
		showCredits = false;
	}

	private void createButtons() {
		Button newGame = new Button.Builder(NEW.image).y(
				BTN_NEWGAME_Y).build();
		newGame.setCallback(new ICallback<EUserAction>() {

			@Override
			public EUserAction execute() {
				log().debug("Pressed NewGame Button");
				return EUserAction.NEW_GAME;
			}
		});
		buttons.add(newGame);

		Button helpButton = new Button.Builder(BTN_HELP.image).y(
				BTN_HELP_Y).build();
		helpButton.setCallback(new ICallback<EUserAction>() {

			@Override
			public EUserAction execute() {
				log().debug("Pressed Help Button");
				showHelp(true);
				return EUserAction.NONE;
			}
		});
		buttons.add(helpButton);

		Button creditsButton = new Button.Builder(
				BTN_CREDITS.image).y(BTN_CREDITS_Y).build();
		creditsButton.setCallback(new ICallback<EUserAction>() {

			@Override
			public EUserAction execute() {
				log().debug("Pressed Credits Button");
				showCredits(true);
				return EUserAction.NONE;
			}
		});
		buttons.add(creditsButton);

		Button endGame = new Button.Builder(QUIT.image).y(
				BTN_QUITGAME_Y).build();
		endGame.setCallback(new ICallback<EUserAction>() {

			@Override
			public EUserAction execute() {
				log().debug("Pressed EndGame Button");
				return EUserAction.QUIT_GAME;
			}
		});
		buttons.add(endGame);
	}

	private void setButtonsEnabled(boolean enabled) {
		for (Button b : buttons) {
			b.setEnabled(enabled);
		}
	}

	private void showHelp(boolean show) {
		setButtonsEnabled(!show);
		showHelp = show;
	}

	private void showCredits(boolean show) {
		setButtonsEnabled(!show);
		showCredits = show;
	}

	@Override
	public void draw(Surface surf) {
		surf.clear();
		if (showHelp) {
			surf.drawImage(HELPSCREEN.image, 0, 0);
		} else if (showCredits) {
			surf.drawImage(CREDITS.image, 0, 0);
		} else {
			surf.drawImage(MAIN_BACKGROUND.image, 0, 0);
			for (Button b : buttons) {
				b.draw(surf);
			}
		}
	}

	@Override
	public EUserAction onClick(ButtonEvent event) {
		for (Button b : buttons) {
			if (b.isHit((int) event.x(), (int) event.y())) {
				return b.callback().execute();
			}
		}
		return EUserAction.NONE;
	}

	@Override
	public EUserAction onKey(Event event) {
		switch (event.key()) {
		
		case ESCAPE:
			if (showHelp) {
				showHelp(false);
			} else if (showCredits) {
				showCredits(false);
			}
			break;
			
		case C:
			if(!showHelp) {
				showCredits(true);
				return EUserAction.NONE;
			}
			
		case N:
			if(!(showHelp || showCredits)) {
				return EUserAction.NEW_GAME;
			}
			
		case H:
			if(!showCredits) {
				showHelp(true);
				return EUserAction.NONE;
			}
			
		case B:
			if(!(showHelp || showCredits)) {
				return EUserAction.QUIT_GAME;
			}
		
		default:
			return EUserAction.NONE;
		}
		return EUserAction.NONE;
	}

}
