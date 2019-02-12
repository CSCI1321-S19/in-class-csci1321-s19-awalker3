package graphicgame

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.canvas.Canvas
import scalafx.scene.image.ImageView
import scalafx.scene.image.Image

object Main extends JFXApp {
  val canvasWidth = 1000
  val canvasHeight = 800
//  val cells = Array.tabulate(2,3)(BasicMaze.apply(2,3))
  stage = new JFXApp.PrimaryStage {
    title = "MouseTrap"
    scene = new Scene(canvasWidth, canvasHeight) {
      val canvas = new Canvas(canvasWidth, canvasHeight)
      val gc = canvas.graphicsContext2D
      val renderer = new Renderer2D(gc, 10.0)
      val board = renderer.render(graphicgame.Player.level, 2.0, 4.0)

    }
  }
}

