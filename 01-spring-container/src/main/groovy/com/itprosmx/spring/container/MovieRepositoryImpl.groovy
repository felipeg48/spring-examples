/**
 * 
 */
package com.itprosmx.spring.container

//B - Using Annotations
//import org.springframework.stereotype.Repository

/**
 * @author Felipe
 *
 *	This class is using options:
 *  A. Using standard bean definition
 *  B. Using Annotations
 *  C. Using Profiles
 */

//B - Using Annotations
//@Repository("movieRepository")
class MovieRepositoryImpl implements MovieRepository {
	
	List<Movie> getAllMovies() {
		[new Movie(name:"Matrix",actor:"Keanu Reves",genre:"Sci-Fi", year: 1998),
	     new Movie(name:"Ghost Protocol",actor:"Tom Cruise", genre:"Action",year:2011)]
	}

}
