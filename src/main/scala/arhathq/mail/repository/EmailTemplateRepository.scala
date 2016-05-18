package arhathq.mail.repository

import arhathq.mail.domain.EmailTemplate

import scala.concurrent.{ExecutionContext, Future}

/**
  * @author Alexander Kuleshov
  */
trait EmailTemplateRepository {

  def findByEmailAndConfigAndLang(emailId: Int, configurationId: Int, lang: String)(implicit ec: ExecutionContext): Future[Option[EmailTemplate]]

  def save(template: EmailTemplate)(implicit ec: ExecutionContext): Future[Unit]

  def delete(templateId: Int)(implicit ec: ExecutionContext): Future[Unit]

}
