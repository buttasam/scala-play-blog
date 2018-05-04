package models

import anorm.SqlParser._
import anorm._
import javax.inject.Inject
import play.api.db.Database


class UserRepo @Inject()(db: Database) {

  val tableName = "user"

  val parser: RowParser[User] = {
      int("id") ~
      str("email") map {
      case id ~ email =>
        User(id, email)
    }
  }

  def findById(id: Long): Option[User] = {
    val selectQuery = s"SELECT * FROM $tableName WHERE id = {id}"
    db.withConnection { implicit connection =>
      SQL(selectQuery).on('id -> id).as(parser.singleOpt)
    }
  }

}