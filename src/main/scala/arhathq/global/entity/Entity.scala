package arhathq.global.entity

/**
  * @author Alexander Kuleshov
  */
trait Entity[A, B] {
  def entityId(): Option[A]
  def newEntity(id: A): B
}
