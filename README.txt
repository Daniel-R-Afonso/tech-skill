I've choose java to develop this mini stock market because is the language that I am most comfortable with. 
This is a simple java standalone appication, that can be ran with command prompt line :

java -jar stockmarket.jar

Or import the src and pom as a project maven and run the application.
For dependencies I hava also used maven, also to build the jar.
The implementation of the model classes, I choose use an interface for Stock,
because as we can have more than 2 different stocks and they can have different method implementations.
I also created a Util class that manages all the stuff that is not strictly related with the models.
For tests I used JUnit, since this is enough for this example, I also hava experience on TestNG but 
I use it to test services or servlets without deploy the application.