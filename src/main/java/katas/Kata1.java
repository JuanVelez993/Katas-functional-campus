package katas;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import model.Movie;
import util.DataUtil;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/*
    Goal: use map() to project an array of videos into an array of {id, title}-pairs
    DataSource: DataUtil.getMovies()
    Output: List of ImmutableMap.of("id", "5", "title", "Bad Boys")
*/
public class Kata1 {
    public static List<Map<Integer, String>> execute() {
        List<Movie> movies = DataUtil.getMovies();
        List<Map<Integer, String>> kataOne;
        kataOne = movies.stream()
                .map(getMoviesFunction())
                .collect(Collectors.toList());
        kataOne.forEach(System.out::println);
        return kataOne;
 }

    private static Function<Movie, ImmutableMap<Integer, String>> getMoviesFunction() {
        return movie->ImmutableMap.of(movie.getId(), movie.getTitle());
    }
}
