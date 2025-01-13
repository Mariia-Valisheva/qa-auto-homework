package utils;

import com.github.javafaker.Faker;

public class RegistrationFormTestData {
    Faker faker = new Faker();

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
        dateMonth = faker.options().option("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"),
        dateYear = String.valueOf(faker.number().numberBetween(1996, 2024)),
        dateDay = String.valueOf(faker.number().numberBetween(1, 28));



    public String getRandomCity (String state) {
        switch(state) {
            case "NCR":
                return faker.options().option("Delhi", "Gurgaon", "Noida");

            case "Uttar Pradesh":
                return faker.options().option("Agra", "Lucknow", "Merrut");

            case "Haryana":
                return faker.options().option("Karnal", "Panipat");

            case "Rajasthanh":
                return faker.options().option("Jaipur", "Jaiselmer");

            default:
                return null;
        }
    };

}
