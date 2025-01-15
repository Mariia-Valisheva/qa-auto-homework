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
        picture = faker.options().option("img1.jpg", "img2.png", "img3.jpg"),
        state = faker.options().option("NCR", "Uttar Pradesh", "Haryana", "Rajasthan"),
        city = getRandomCity(state),
        subject = faker.options().option("Hindi", "English", "Maths", "Physics", "Chemistry", "Biology",
                "Computer Science", "Commerce", "Accounting", "Economics", "Arts", "Social Studies", "History",
                "Civics"),
        incorrectNumber = faker.number().digits(11),
        yearOfBirth,
        monthOfBirth,
        dayOfBirth;

    public void getBirthDate() {
        Date generatedDate = faker.date().birthday(18, 50);

        SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy", Locale.UK);
        String yearOfBirth = sdfYear.format(generatedDate);
        //System.out.println(yearOfBirth);

        SimpleDateFormat sdfMon = new SimpleDateFormat("MMMM", Locale.UK);
        String monthOfBirth = sdfMon.format(generatedDate);
        //System.out.println(monthOfBirth);

        SimpleDateFormat sdfDay = new SimpleDateFormat("d", Locale.UK);
        String dayOfBirth = sdfDay.format(generatedDate);
        //System.out.println(dayOfBirth);

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
