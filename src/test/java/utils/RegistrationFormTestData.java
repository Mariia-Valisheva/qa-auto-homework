package utils;

import com.github.javafaker.Faker;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class RegistrationFormTestData {
    Faker faker = new Faker(new Locale("en"));

    public String
        firstName = faker.name().firstName(),
        lastName = faker.name().lastName(),
        userEmail = faker.internet().emailAddress(),
        currentAddress = faker.address().fullAddress(),
        userNumber = faker.number().digits(10),
        gender = faker.options().option("Male", "Female", "Other"),
        hobby = faker.options().option("Reading", "Sports", "Music"),
        picture = faker.options().option("images/img1.jpg", "images/img2.png", "images/img3.jpg"),
        state = faker.options().option("NCR", "Uttar Pradesh", "Haryana", "Rajasthan"),
        city = getRandomCity(state),
        subject = faker.options().option("Hindi", "English", "Maths", "Physics", "Chemistry", "Biology",
                "Computer Science", "Commerce", "Accounting", "Economics", "Arts", "Social Studies", "History",
                "Civics"),
        incorrectNumber = faker.number().digits(11),
        yearOfBirth,
        monthOfBirth,
        dayOfBirth;

    {
        Date generatedDate = faker.date().birthday(18, 50);

        SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy", Locale.UK);
        yearOfBirth = sdfYear.format(generatedDate);

        SimpleDateFormat sdfMon = new SimpleDateFormat("MMMM", Locale.UK);
        monthOfBirth = sdfMon.format(generatedDate);

        SimpleDateFormat sdfDay = new SimpleDateFormat("d", Locale.UK);
        dayOfBirth = sdfDay.format(generatedDate);

    };

    public String getRandomCity (String state) {
        switch(state) {
            case "NCR":
                return faker.options().option("Delhi", "Gurgaon", "Noida");

            case "Uttar Pradesh":
                return faker.options().option("Agra", "Lucknow", "Merrut");

            case "Haryana":
                return faker.options().option("Karnal", "Panipat");

            case "Rajasthan":
                return faker.options().option("Jaipur", "Jaiselmer");

            default:
                throw new IllegalArgumentException("Unknown state: " + state);
        }
    };

}
