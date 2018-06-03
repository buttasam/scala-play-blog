package models.repository

import anorm.{Macro, RowParser, _}
import javax.inject.Inject
import models.entity.Article
import models.form.ArticleForm
import play.api.db.Database


class ArticleRepository @Inject()(db: Database) {

  val tableName = "article"

  val parser: RowParser[Article] = Macro.namedParser[Article]

  def findAll(): List[Article] = {
    val selectQuery = s"SELECT * FROM $tableName WHERE deleted = 0"
    db.withConnection { implicit connection =>
      SQL(selectQuery).as(parser.*)
    }
  }

  def findById(id: Long): Option[Article] = {
    val selectQuery = s"SELECT * FROM $tableName WHERE id = {id}"
    db.withConnection { implicit connection =>
      SQL(selectQuery).on('id -> id).as(parser.singleOpt)
    }
  }

  def insert(articleForm: ArticleForm): Unit = {
    val selectQuery = s"INSERT INTO $tableName (title, perex, content) VALUES ({title}, {perex}, {content})"
    db.withConnection { implicit connection =>
      SQL(selectQuery).on('title -> articleForm.title, 'perex -> articleForm.perex, 'content -> articleForm.content).executeInsert()
    }
  }

}