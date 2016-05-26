package arhathq.global.configuration.repository

import arhathq.global.configuration.entity.ConfigurationEntity
import arhathq.global.repository.{InMemoryStorage, IntegerIdGenerator}

import scala.concurrent.{ExecutionContext, Future}

/**
  * @author Alexander Kuleshov
  */
trait ConfigurationRepository {

  def save(configuration: ConfigurationEntity)(implicit ec: ExecutionContext): Future[Option[ConfigurationEntity]]

  def findById(configurationId: Int)(implicit ec: ExecutionContext): Future[Option[ConfigurationEntity]]

  def findAll(implicit ec: ExecutionContext): Future[Seq[ConfigurationEntity]]

}

class InMemoryConfigurationRepository extends ConfigurationRepository {

  val storage = new InMemoryStorage[Int, ConfigurationEntity](new IntegerIdGenerator())

  override def findById(configurationId: Int)(implicit ec: ExecutionContext): Future[Option[ConfigurationEntity]] = {
    Future.successful(storage.get(configurationId))
  }

  override def findAll(implicit ec: ExecutionContext): Future[Seq[ConfigurationEntity]] = Future.successful(storage.getAll)

  override def save(configuration: ConfigurationEntity)(implicit ec: ExecutionContext): Future[Option[ConfigurationEntity]] = {
    Future.successful(storage.insert(configuration))
  }
}