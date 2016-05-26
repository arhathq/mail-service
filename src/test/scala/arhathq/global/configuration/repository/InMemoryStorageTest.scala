package arhathq.global.configuration.repository

import arhathq.global.entity.Entity
import arhathq.global.repository.{InMemoryStorage, IntegerIdGenerator, StringIdGenerator}
import org.scalatest.{BeforeAndAfter, FunSuite}

/**
  * @author Alexander Kuleshov
  */
class InMemoryStorageTest extends FunSuite with BeforeAndAfter {


  test("check that storage can insert entities") {
    val storage = new InMemoryStorage[String, StringKeyEntity](new StringIdGenerator)

    val persisted1 = storage.insert(new StringKeyEntity(None, "entity1"))
    val persisted2 = storage.insert(new StringKeyEntity(None, "entity2"))
    val persisted3 = storage.insert(new StringKeyEntity(None, "entity3"))
    val persisted4 = storage.insert(new StringKeyEntity(None, "entity4"))

    val list = storage.getAll
    assert(list.size == 4)
  }

  test("check that storage can delete entities") {
    val storage = new InMemoryStorage[String, StringKeyEntity](new StringIdGenerator)

    val persisted1 = storage.insert(new StringKeyEntity(None, "entity1"))

    val list = storage.getAll
    assert(list.nonEmpty)

    val deleted = storage.delete(persisted1.get.id.get)
    assert(persisted1 == deleted)

    assert(storage.getAll.isEmpty)
  }

  test("check that storage can update entities") {
    val storage = new InMemoryStorage[Int, IntKeyEntity](new IntegerIdGenerator)

    val entity1 = new IntKeyEntity(None, "entity1")
    val entity2 = new IntKeyEntity(None, "entity2")

    val persisted1 = storage.insert(entity1)
    val persisted2 = storage.insert(entity2)

    assert(entity1 != persisted1.get)

    val updated = persisted2.get.copy(name = "updated entity2")
    val persisted = storage.update(updated)

    assert(updated == persisted.get)
  }

  test("check that storage can search entities by id") {
    val storage = new InMemoryStorage[Int, IntKeyEntity](new IntegerIdGenerator)

    val entity1 = new IntKeyEntity(None, "entity1")

    val persisted1 = storage.insert(entity1).get

    val persisted2 = storage.get(persisted1.id.get).get

    assert(persisted1 == persisted2)
  }

}

case class StringKeyEntity(id: Option[String], name: String) extends Entity[String, StringKeyEntity] {
  override def entityId(): Option[String] = id
  override def newEntity(id: String): StringKeyEntity = copy(Some(id))
}

case class IntKeyEntity(id: Option[Int], name: String) extends Entity[Int, IntKeyEntity] {
  override def entityId(): Option[Int] = id
  override def newEntity(id: Int): IntKeyEntity = copy(Some(id))
}
