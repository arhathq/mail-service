package arhathq.mail.domain


case class Email(id: Int, template: String, description: String)
case class EmailFlow(id: Int, configurationId: Int, emailId: Int, preProcess: String, postProcess: String, attachmentProcess: String)
case class EmailTemplate(id: Int, configurationId: Int, emailId: Int, lang: String, subject: String, text: String)

