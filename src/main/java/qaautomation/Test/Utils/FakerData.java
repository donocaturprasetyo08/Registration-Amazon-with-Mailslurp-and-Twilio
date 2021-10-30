package qaautomation.Test.Utils;

import com.github.javafaker.Faker;

public class FakerData {
	static Faker faker = new Faker();
	public static final String firstname = faker.name().firstName();
	public static final String lastname = faker.name().lastName();
	public static final String fullname = faker.name().fullName();
	public static final String email = faker.name().username() + "@gmail.com";
	public static final String numberPhone = "+" + faker.phoneNumber().cellPhone();
}
