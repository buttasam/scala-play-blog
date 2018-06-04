package models.form

import play.api.data._
import play.api.data.Forms._

case class  ContactForm(email: String, message: String)

object ContactForm {
  val contactForm : Form[ContactForm] = Form(
    mapping(
      "email" -> text,
      "message" -> text
    )(ContactForm.apply)(ContactForm.unapply)
  )

}