package arhathq.global.configuration.entity

import arhathq.global.entity.Entity

/**
  * @author Alexander Kuleshov
  */
case class ConfigurationEntity(id: Int, codename: String, description: Option[String]) extends Entity[Int, ConfigurationEntity] {
  override def entityId(): Option[Int] = Some(id)

  override def newEntity(id: Int): ConfigurationEntity = copy(id)
}

object ConfigurationEntity {
  def newConfiguration(id: Int, codename: String, description: String) = ConfigurationEntity.apply(id, codename, Some(description))
}
