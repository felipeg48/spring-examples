/**
 * 
 */
package com.itprosmx.spring.container

import static org.junit.Assert.*

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner


/**
 * @author fgutierrezcru
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/META-INF/spring/movie-context.xml")
class MovieTest {
	
	@Autowired
	MovieRepository movieRepository
	
	@Test
	void getAllMovie() {
		assertNotNull movieRepository
		assertNotNull movieRepository.getAllMovies()
	}

}
