package arhathq.mail.repository

import arhathq.mail.domain.Email

import scala.concurrent.{ExecutionContext, Future}

/**
  * @author Alexander Kuleshov
  */
trait EmailRepository {

  def findById(emailId: Int)(implicit ec: ExecutionContext): Future[Option[Email]]

  def findAll(implicit ec: ExecutionContext): Future[Seq[Email]]

}
