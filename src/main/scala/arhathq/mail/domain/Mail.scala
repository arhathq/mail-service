package arhathq.mail.domain

import java.time.{ZoneId, ZonedDateTime}

import arhathq.mail.domain.MailStatus.MailStatus

/**
  * @author Alexander Kuleshov
  */
case class Mail(id: Option[Long], configurationId: Int, emailId: Int, template: String, status: MailStatus, message: Option[String], createdDate: ZonedDateTime, processDate: Option[ZonedDateTime])

object Mail {
  def apply(configurationId: Int, emailId: Int, template: String): Mail = new Mail(None, configurationId, emailId, template, MailStatus.NEW, None, ZonedDateTime.now(ZoneId.of("UTC")), None)
}

object MailStatus extends Enumeration {
  protected case class Val(code: Int) extends super.Val(nextId)

  type MailStatus = Val

  val NEW = Val(0)
  val SUSPENDED = Val(1)
  val PROCESSED = Val(2)
  val ERROR = Val(-1)
}