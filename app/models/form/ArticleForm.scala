package models.form

import play.api.data._
import play.api.data.Forms._

case class  ArticleForm(title: String, perex: String, content: String)

object ArticleForm {
  val articleForm : Form[ArticleForm] = Form(
    mapping(
      "title" -> text,
      "perex" -> text,
      "content" -> text
    )(ArticleForm.apply)(ArticleForm.unapply)
  )

}