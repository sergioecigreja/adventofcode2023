package pt.sergioigreja.day14;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class Day14 {
    private List<String> input;
    char[][] platform;

    public Day14() {
        try {
            this.input = Files.readAllLines(Paths.get("src/main/java/pt/sergioigreja/day14/input"));

        } catch (IOException e) {
            Logger.getGlobal().warning(e.toString());
        }

        platform = new char[input.size()][input.get(0).length()];

        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < input.get(0).length(); j++) {
                platform[i][j] = input.get(i).charAt(j);
            }
        }
    }

    public void problem1() {
        int[] level = new int[platform[0].length];

        for (int i = 0; i < platform[0].length; i++) {
            int emptySpace = 0;
            for (int j = 0; j < platform.length; j++) {
                switch (platform[j][i]) {
                    case '.':
                        emptySpace += 1;
                        break;
                    case '#':
                        emptySpace = 0;
                        break;
                    default:
                        level[level.length - 1 - j + emptySpace] += 1;
                        break;
                }
            }
        }

        System.out.println(Arrays.toString(level));
        int result = 0;

        for (int i = 0; i < level.length; i++) {
            result += level[i] * (i + 1);
        }

        System.out.println(result);
    }

    private void problem2() {
        Instant start = Instant.now();

        Map<String, String> map = new HashMap<>();
        String prevKey = platformToKey(platform);
        for (int i = 0; i < 1000000000; i++) {
            if (map.containsKey(prevKey)) {
                prevKey = map.get(prevKey);
            } else {
                tiltPlatform(this.platform, 'n');
                tiltPlatform(this.platform, 'w');
                tiltPlatform(this.platform, 's');
                tiltPlatform(this.platform, 'e');
                String newKey = platformToKey(this.platform);
                map.put(prevKey, newKey);
                prevKey = newKey;
            }
        }

        int result = 0;
        int levelCount = 0;
        int j = 0;
        for (int i = 0; i < prevKey.length(); i++) {
            if (prevKey.charAt(i) == '\n') {
                result += levelCount * (platform.length - j);
                levelCount = 0;
                j += 1;
            } else if (prevKey.charAt(i) == 'O') {
                levelCount += 1;
            }

        }
        System.out.println(result);

        Instant finish = Instant.now();
        System.out.println(Duration.between(start, finish).toMillis());
    }

    private String platformToKey(char[][] platform) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < platform.length; i++) {
            for (int j = 0; j < platform[0].length; j++) {
                sb.append(platform[i][j]);
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    private void tiltPlatform(char[][] platform, char orientation) {
        switch (orientation) {
            case 'n':
                for (int i = 0; i < platform[0].length; i++) {
                    int emptySpace = 0;
                    for (int j = 0; j < platform.length; j++) {
                        switch (platform[j][i]) {
                            case '.':
                                emptySpace += 1;
                                break;
                            case '#':
                                emptySpace = 0;
                                break;
                            default:
                                if (emptySpace != 0) {
                                    platform[j - emptySpace][i] = 'O';
                                    platform[j][i] = '.';
                                }
                                break;
                        }
                    }
                }
                break;
            case 'w':
                for (int i = 0; i < platform.length; i++) {
                    int emptySpace = 0;
                    for (int j = 0; j < platform[0].length; j++) {
                        switch (platform[i][j]) {
                            case '.':
                                emptySpace += 1;
                                break;
                            case '#':
                                emptySpace = 0;
                                break;
                            default:
                                if (emptySpace != 0) {
                                    platform[i][j - emptySpace] = 'O';
                                    platform[i][j] = '.';
                                }
                                break;
                        }
                    }
                }
                break;
            case 's':
                for (int i = 0; i < platform[0].length; i++) {
                    int emptySpace = 0;
                    for (int j = platform.length - 1; j >= 0; j--) {
                        switch (platform[j][i]) {
                            case '.':
                                emptySpace += 1;
                                break;
                            case '#':
                                emptySpace = 0;
                                break;
                            default:
                                if (emptySpace != 0) {
                                    platform[j + emptySpace][i] = 'O';
                                    platform[j][i] = '.';
                                }
                                break;
                        }
                    }
                }
                break;
            default:
                for (int i = 0; i < platform.length; i++) {
                    int emptySpace = 0;
                    for (int j = platform[0].length - 1; j >= 0; j--) {
                        switch (platform[i][j]) {
                            case '.':
                                emptySpace += 1;
                                break;
                            case '#':
                                emptySpace = 0;
                                break;
                            default:
                                if (emptySpace != 0) {
                                    platform[i][j + emptySpace] = 'O';
                                    platform[i][j] = '.';
                                }
                                break;
                        }
                    }
                }
                break;
        }

    }

    public static void main(String[] args) {
        Day14 day14 = new Day14();
        day14.problem2();
    }
}
