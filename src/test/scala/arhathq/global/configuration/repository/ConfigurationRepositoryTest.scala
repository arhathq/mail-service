package arhathq.global.configuration.repository

import arhathq.global.configuration.entity.ConfigurationEntity
import org.scalatest.{BeforeAndAfter, FunSuite}

import scala.concurrent.ExecutionContext

/**
  * @author Alexander Kuleshov
  */
class ConfigurationRepositoryTest extends FunSuite with BeforeAndAfter {

  val configurationRepository = new InMemoryConfigurationRepository

  implicit val ec = ExecutionContext.Implicits.global

  test("check that configuration is emty") {
    for {
      c <- configurationRepository.findAll
    } yield {
      assert(c.isEmpty)
    }
  }

  test("check that configuration was added") {
    val default = ConfigurationEntity.newConfiguration(1, "default", "Base configuration")
    for {
      c <- configurationRepository.save(default)
    } yield {
      assert(c.isDefined)
    }
  }

  test("check that configuration is not emty") {
    for {
      c <- configurationRepository.findAll
    } yield {
      assert(c.nonEmpty)
    }
  }

//  test("check that configuration is not emty") {
//    for {
//      c <- configurationRepository.
//    } yield {
//      assert(c.nonEmpty)
//    }
//  }

}
