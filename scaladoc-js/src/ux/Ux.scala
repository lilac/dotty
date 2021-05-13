package dotty.tools.scaladoc

import org.scalajs.dom._
import org.scalajs.dom.ext._

import scala.util.matching.Regex._
import scala.util.matching._

class Ux():
  def sideMenuItemsWordBreaking(): Unit =
    val matchingRegex = raw"([.A-Z])".r
    def modifyingFunc = (e: Node) => e.nodeValue = matchingRegex.replaceAllIn(e.nodeValue, m => s"\u200B${m.group(0)}")
    val nodes = document.querySelectorAll("#sideMenu2 a span").collect {
        case e: html.Element => e
      }
      .flatMap { e => e.childNodes }
      .filter(_.nodeType == 3)
    nodes.foreach(modifyingFunc)

  sideMenuItemsWordBreaking()