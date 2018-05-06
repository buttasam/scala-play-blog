package models.repository

import anorm.{Macro, RowParser, _}
import javax.inject.Inject
import models.entity.User
import play.api.db.Database


class UserRepository @Inject()(db: Database) {

  val tableName = "user"

  val parser: RowParser[User] = Macro.namedParser[User]

  def findById(id: Long): Option[User] = {
    val selectQuery = s"SELECT * FROM $tableName WHERE id = {id}"
    db.withConnection { implicit connection =>
      SQL(selectQuery).on('id -> id).as(parser.singleOpt)
    }
  }

}