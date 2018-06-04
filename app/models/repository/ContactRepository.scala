package models.repository

import anorm.{Macro, RowParser, _}
import javax.inject.Inject
import models.entity.Contact
import models.form.ContactForm
import play.api.db.Database


class ContactRepository @Inject()(db: Database) {

  val tableName = "contact"

  val parser: RowParser[Contact] = Macro.namedParser[Contact]


  def findAll(): List[Contact] = {
    val selectQuery = s"SELECT * FROM $tableName"
    db.withConnection { implicit connection =>
      SQL(selectQuery).as(parser.*)
    }
  }

  def insert(contactForm: ContactForm): Unit = {
    val selectQuery = s"INSERT INTO $tableName (email, message) VALUES ({email}, {message})"
    db.withConnection { implicit connection =>
      SQL(selectQuery).on('email -> contactForm.email, 'message -> contactForm.message).executeInsert()
    }
  }

}