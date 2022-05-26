package katas;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import model.Bookmark;
import model.BoxArt;
import model.Movie;
import model.MovieList;
import util.DataUtil;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

/*
    Goal: Retrieve each video's id, title, middle interesting moment time, and smallest box art url
    DataSource: DataUtil.getMovies()
    Output: List of ImmutableMap.of("id", 5, "title", "some title", "time", new Date(), "url", "someUrl")
*/
public class Kata9 {
    public static List<Map> execute() {
        List<MovieList> movieLists = DataUtil.getMovieLists();
        List<Map> kataNine=movieLists
                .stream()
                .flatMap(movieList -> movieList.getVideos().stream())
                .map(movie->ImmutableMap.of("id",movie.getId(),"title", movie.getId(),"time",movie.getInterestingMoments()
                        .stream().filter(interestingMoment -> interestingMoment.getType().equals("Middle"))
                        .map(interestingMoment -> interestingMoment.getTime())
                        .map(moment->moment.getTime()).findFirst().get(),"url",movie.getBoxarts()
                        .stream().reduce(getSmallest())
                        .map(boxArt -> boxArt.getUrl()).get()))
                        .collect(Collectors.toList());

        kataNine.forEach(System.out::println);
        return  kataNine;
    }

    private static BinaryOperator<BoxArt> getSmallest() {
        return (bArt1,bArt2)-> bArt1.getWidth()* bArt1.getHeight() < bArt2.getWidth()* bArt2.getHeight()? bArt1 : bArt2;
    }
}
