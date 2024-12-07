package io.example.ximalaya;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RenameFileNameTestsV2 {
    public static void main(String[] args) throws Exception {
        // 下载时的顺序：360 -> 001
        ClassPathResource downloadfile = new ClassPathResource("ximalaya/download.txt");
        // 播放列表的顺序：360 -> 001
        ClassPathResource playlistfile = new ClassPathResource("ximalaya/playlist.json");
        if (downloadfile.exists() && playlistfile.exists()) {
            List<String> filenames = Files.readAllLines(Paths.get(downloadfile.getURI()))
                    .stream()
                    .map(line -> line.substring(line.lastIndexOf("/") + 1, line.indexOf("?")))
                    .toList();
            Gson gson = new Gson();
            String jsonString = playlistfile.getContentAsString(StandardCharsets.UTF_8);
            List<Player> players = gson.fromJson(jsonString, new TypeToken<List<Player>>(){}.getType());
            if (filenames.size() != players.size()) {
                System.err.println(filenames.size());
                System.err.println(players.size());
                System.err.println("总数不一致！");
            } else {
                for (int i = 0, size = filenames.size(); i < size; i++) {
                    Player player = players.get(i);
                    String fileName = filenames.get(i);
                    player.setFileName(fileName);
                    player.setFileExt(StringUtils.getFilenameExtension(fileName));
//                    System.err.println(player);
                }
                // 将list转成map，key是文件名，value是对象
                Map<String, Player> playerMap = players.stream().collect(
                        Collectors.toMap(Player::getFileName, Function.identity()));
                String dir = "d:\\影视\\阿尔法胎教音乐";
                try (Stream<Path> stream = Files.list(Paths.get(dir))) {
                    stream.forEach(path -> {
                        String s = path.toString();
                        String filename = s.substring(s.lastIndexOf("\\") + 1);
                        Optional.ofNullable(playerMap.get(filename))
                                .ifPresentOrElse(player -> {
                                    try {
                                        // 文件重命名
                                        Path target = Files.move(path, Paths.get(dir,
                                                player.getTrackName() + "." + player.getFileExt()));
                                        System.err.println("重命名\t" + target);
                                    } catch (IOException e) {
                                        throw new RuntimeException(e);
                                    }
                                }, () -> System.err.println(filename + "\t文件已重命名"));
                    });
                }
                System.err.println("done");
            }
        } else {
            System.err.println(downloadfile);
            System.err.println(playlistfile);
            System.err.println("文件不存在！");
        }
    }
}
