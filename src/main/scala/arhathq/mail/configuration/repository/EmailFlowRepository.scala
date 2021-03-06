package arhathq.mail.configuration.repository

import arhathq.mail.configuration.EmailFlow

import scala.concurrent.{ExecutionContext, Future}

/**
  * @author Alexander Kuleshov
  */
trait EmailFlowRepository {

  def findByEmailAndConfig(emailId: Int, configurationId: Int)(implicit ec: ExecutionContext): Future[Option[EmailFlow]]

  def save(emailFlow: EmailFlow)(implicit ec: ExecutionContext): Future[Option[EmailFlow]]

  def delete(flowId: Int)(implicit ec: ExecutionContext): Future[Option[EmailFlow]]

}
