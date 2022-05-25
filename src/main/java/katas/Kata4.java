package katas;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import model.BoxArt;
import model.MovieList;
import util.DataUtil;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/*
    Goal: Retrieve id, title, and a 150x200 box art url for every video
    DataSource: DataUtil.getMovieLists()
    Output: List of ImmutableMap.of("id", "5", "title", "Bad Boys", "boxart": BoxArt)
*/
public class Kata4 {
    public static List<Map> execute() {
        List<MovieList> movieLists = DataUtil.getMovieLists();
        List<Map> kataFour=movieLists.stream().flatMap(movieList -> movieList.getVideos().stream())
                .map(video->Map.of("id",video.getId(),"title",video.getTitle(),"boxArt ",video.getBoxarts().stream()
                        .filter(getBoxArtPredicate()).map(BoxArt::getUrl).findAny().get()))
                        .collect(Collectors.toList());
        kataFour.forEach(System.out::println);
        return kataFour;
    }

    private static Predicate<BoxArt> getBoxArtPredicate() {
        return box->box.getHeight()==200 &&box.getWidth()==150;
    }
}
