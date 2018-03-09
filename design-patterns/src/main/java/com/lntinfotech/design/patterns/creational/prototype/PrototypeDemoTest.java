package com.lntinfotech.design.patterns.creational.prototype;

public class PrototypeDemoTest {

	public static void main(String[] args) {

		Registry registry = new Registry();
		Movie movie = (Movie) registry.createItem("Movie");
		movie.setTitle("Hello World");
		
		System.out.println(movie);
		System.out.println(movie.getTitle());
		System.out.println(movie.getPrice());
		System.out.println(movie.getRuntime());
		
		Movie anotherMovie = (Movie) registry.createItem("Movie");
		anotherMovie.setTitle("Hello World Returns");
		
		System.out.println(anotherMovie);
		System.out.println(anotherMovie.getTitle());
		System.out.println(anotherMovie.getPrice());
		System.out.println(anotherMovie.getRuntime());
	}

}
