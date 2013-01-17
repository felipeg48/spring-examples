/**
 * 
 */
package com.itprosmx.spring.container

//B - Using Annotations
import org.springframework.stereotype.Repository

/**
 * @author Felipe
 *
 */

//B - Using Annotations
@Repository("movieRepository")
class MovieRepositoryImpl implements MovieRepository {
	
	List<Movie> getAllMovies() {
		return [new Movie(name:"Matrix",actor:"Keanu Reves",genre:"Sci-Fi", year: 1998),
			    new Movie(name:"Ghost Protocol",actor:"Tom Cruise", genre:"Action",year:2011)]
	}

}
