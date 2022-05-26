package katas;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import model.Bookmark;
import model.BoxArt;
import model.Movie;
import model.MovieList;
import util.DataUtil;

import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

/*
    Goal: Retrieve the id, title, and smallest box art url for every video
    DataSource: DataUtil.getMovieLists()
    Output: List of ImmutableMap.of("id", "5", "title", "Bad Boys", "boxart": "url)
*/
public class Kata7 {
    public static List<Map> execute() {
        List<MovieList> movieLists = DataUtil.getMovieLists();
        List<Map> kataSeven= movieLists
                        .stream()
                        .flatMap(movie -> movie.getVideos().stream())
                        .map(video->ImmutableMap.of("id",video.getId(),"title",video.getTitle(),"boxArt",
                                video.getBoxarts()
                                        .stream()
                                        .reduce(getSmallest())
                                        .map(boxArt -> boxArt.getUrl()).get())).collect(Collectors.toList());
        kataSeven.forEach(System.out::println);
        return kataSeven;
    }

    private static BinaryOperator<BoxArt> getSmallest() {
        return (bArt1,bArt2)-> bArt1.getWidth()* bArt1.getHeight() < bArt2.getWidth()* bArt2.getHeight()? bArt1 : bArt2;
    }
}
