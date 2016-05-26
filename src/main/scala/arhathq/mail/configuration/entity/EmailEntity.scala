package arhathq.mail.configuration.entity

import arhathq.global.entity.Entity
import arhathq.mail.configuration.Email

/**
  * @author Alexander Kuleshov
  */
case class EmailEntity(id: Int, codename: String, description: String) extends Entity[Int, EmailEntity] {
  override def entityId(): Option[Int] = Some(id)
  override def newEntity(id: Int): EmailEntity = copy(id)
}

object EmailEntity {
  def from(email: Email) = EmailEntity.apply(email.id, email.codename, email.description)
}