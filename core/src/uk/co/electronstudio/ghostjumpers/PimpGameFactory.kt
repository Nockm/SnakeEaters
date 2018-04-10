package uk.co.electronstudio.ghostjumpers

import com.badlogic.gdx.Application
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import uk.me.fantastic.retro.App
import uk.me.fantastic.retro.games.AbstractGameFactory
import uk.me.fantastic.retro.games.Game
import uk.me.fantastic.retro.input.GamepadInput
import uk.me.fantastic.retro.input.KeyboardMouseInput
import uk.me.fantastic.retro.input.SimpleTouchscreenInput
import uk.me.fantastic.retro.isMobile
import uk.me.fantastic.retro.screens.GameSession

/*
 * Used by RetroWar to create our main class
 */
class PimpGameFactory : AbstractGameFactory("Pimp Game", null) {

    override val image by lazy { Texture(Gdx.files.internal("badlogic.jpg")) }

    override fun create(session: GameSession): Game {
        if (Gdx.app.type == Application.ApplicationType.Desktop) {
            val controller1 = App.mappedControllers.firstOrNull()
            if (controller1 != null) {
                session.preSelectedInputDevice = GamepadInput(controller1)
            } else {
                session.preSelectedInputDevice = KeyboardMouseInput(session)
                session.KBinUse = true
            }
        } else if (isMobile()) {
            session.preSelectedInputDevice = SimpleTouchscreenInput()
        }
        return PimpGame(session, 1, 0)
    }
}