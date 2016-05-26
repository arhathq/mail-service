package arhathq.global.repository

import java.util.UUID
import java.util.concurrent.atomic.{AtomicInteger, AtomicLong}
import java.util.concurrent.locks.ReentrantLock

import arhathq.global.entity.Entity

import scala.collection.mutable

/**
  * @author Alexander Kuleshov
  */
class InMemoryStorage[A, B <: Entity[A, B]](idGenerator: IdGenerator[A]) {

  private implicit val lock = new ReentrantLock()
  private val storage = mutable.Map[A, B]()

  object Locker {
    def locked(f: => Option[B])(implicit lock: ReentrantLock): Option[B] = {
      lock.lock()
      val result = f
      lock.unlock()
      result
    }
  }

  import Locker._

  def insert(entity: B): Option[B] = locked {
    val e = entity.newEntity(idGenerator.generateId())
    storage.put(e.entityId().get, e)
    println("Insert " + e)
    Some(e)
  }

  def update(entity: B): Option[B] = locked {
    if (storage.contains(entity.entityId().get)) {
      storage.put(entity.entityId().get, entity)
      println("Update " + entity)
      return Some(entity)
    }
    None
  }

  def delete(entityId: A): Option[B] = locked {
    if (storage.contains(entityId)) {
      val removed: Option[B] = storage.remove(entityId)
      println("Remove " + removed)
      return removed
    }
    None
  }

  def deleteAll(): Unit = {
    locked {
      storage.clear()
      None
    }
  }

  def get(entityId: A): Option[B] = Some(storage(entityId))

  def getAll: Seq[B] = storage.values.toIndexedSeq

}

trait IdGenerator[A] {
  def generateId(): A
}

class LongIdGenerator extends IdGenerator[Long] {
  private val generator = new AtomicLong()
  override def generateId(): Long = generator.incrementAndGet()
}

class IntegerIdGenerator extends IdGenerator[Int] {
  private val generator = new AtomicInteger()
  override def generateId(): Int = generator.incrementAndGet()
}

class StringIdGenerator extends IdGenerator[String] {
  override def generateId(): String = UUID.randomUUID().toString
}