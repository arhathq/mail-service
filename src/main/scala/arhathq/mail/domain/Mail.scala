package arhathq.mail.domain

import java.util.Date

/**
  * @author Alexander Kuleshov
  */
case class Mail(id: Option[Long], configurationId: Int, emailId: Int, status: Int, message: String, createdDate: Date, processedDate: Date)