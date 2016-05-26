package arhathq.mail.configuration

case class Email(id: Int, codename: String, description: String)
case class EmailFlow(id: Int, configurationId: Int, emailId: Int, preProcess: String, postProcess: String, attachmentProcess: String)
case class EmailTemplate(id: Int, configurationId: Int, emailId: Int, lang: String, template: String, subject: String, text: String)

