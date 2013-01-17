/**
 * 
 */
package com.itprosmx.spring.container

import org.codehaus.groovy.reflection.ReflectionUtils

/**
 * @author Felipe
 *
 * This class can be use on options:
 * A. Using standard bean definition
 * C. Using Profiles
 */
class MovieRepositoryImplText implements MovieRepository {
	
	List<Movie> getAllMovies() {
		def resource = ReflectionUtils.getCallingClass(0).getResourceAsStream("/META-INF/data/movies.txt")
		def movies = []
		def map = [:]
		def text = resource.text.eachLine {
			map = it.split(",").inject([:]){result, movie ->
				movie.split(':').with { 
					result[it[0]] = (it[0] == "year")?Integer.parseInt(it[1]):it[1] 
				}
				result
			}
			movies << new Movie(map)	
		}
		movies
	}
	

}
