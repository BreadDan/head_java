import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

/**
 * UNIXコマンドheadをJavaで再現
 * "-n [lines]"或いは"--lines [lines]"で行数が指定できます、デフォルト行数は10
 * 複数のファイル名で順番表示することができます
 * 
 * head_java (-n [lines]) filename1 filename2 ...
 */
public class head_java {
    public static void main(String[] args) {
        // パラメータチェック
        if (args.length == 0) {
            // head_java
            System.out.println("Usage: head_java (-n [lines]) filename1 filename2 ...");
            return;
        }

        if (("-n".equals(args[0]) || "--lines".equals(args[0]))) {
            // head_java -n [lines] filename1 filename2 ...
            // 行数の取得
            int numLines = getNumLines(args);
            if (numLines <= 0) {
                System.err.println("Number of lines must be a positive integer.");
                return;
            }
            // ファイル名リストの作成
            String[] fileNames = Arrays.copyOfRange(args, 2, args.length);
            // ファイルの前numLines行数の表示
            for (String fileName : fileNames) {
                if (fileNames.length > 1) {
                    System.out.println("==> " + fileName + " <==");
                }
                viewFileLines(fileName, numLines);
            }
        } else {
            // head_java filename1 filename2 ...
            // 行数=10
            int numLines = 10;

            // ファイル名リストの作成
            String[] fileNames = Arrays.copyOfRange(args, 0, args.length);

            // ファイルの前numLines行数の表示
            for (String fileName : fileNames) {
                if (fileNames.length > 1) {
                    System.out.println("==> " + fileName + " <==");
                }
                viewFileLines(fileName, numLines);
            }
        }
    }

    /**
     * argsから行数を取得する
     *
     * @param args
     * @return 行数，失敗の場合-1
     */
    private static int getNumLines(String[] args) {
        try {
            return Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.err.println("Error parsing line numbers " + args[0] + " " + args[1]);
            return -1;
        }
    }

    /**
     * ファイルの前numLines行数の表示
     *
     * @param fileName ファイル名
     * @param numLines 行数
     */
    private static void viewFileLines(String fileName, int numLines) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            int linesRead = 0;
            while ((line = br.readLine()) != null && linesRead < numLines) {
                System.out.println(line);
                linesRead++;
            }
            System.out.println("\n");
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}