package com.assessment;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String rawPassword = "editor";
		String encodedPassword = encoder.encode(rawPassword);

		System.out.println(encodedPassword);

	}

}
