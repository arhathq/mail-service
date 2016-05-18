package arhathq.global.repository

import arhathq.global.domain.Configuration

import scala.concurrent.{ExecutionContext, Future}

/**
  * @author Alexander Kuleshov
  */
trait ConfigurationRepository {

  def findById(configurationId: String)(implicit ec: ExecutionContext): Future[Option[Configuration]]

  def findAll(implicit ec: ExecutionContext): Future[Seq[Configuration]]

}
