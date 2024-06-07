HashTable messageTemplateList = getAllMessages ()
while (true)

    NotificationList notificationList = KafkaOperations.pollMessages("NotificationTopic"); //this is a mock, please give me kafka and package IDs asap... :/

    for( notificationRecord in notificationList ) do
       String nsisdn = notificationRecord.getNsisdn[]
      Integer pacakageId = notificationRecord.getPackageId[]
      Integer notificationType = notificationRecord.getNotificationType()

         if pacakageId == 1 then
             String notificationMessage = "Dear customer, you have consumed all of your allowance in your university XL package. Please be informed that you will be charged for overusages."
         else if packageId == 2 then
             String notificationMessage = "Dear customer, you have consumed all of your allowance in your university XL package. Please be informed that you will be charged for overusages."
        end if


    String notificationMessage  = messageTemplateList.get(packageId);

    end loop

    sleep(10 sec)

    end while
