import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        //Задание №1
        String log = "";
        StringBuilder sb = new StringBuilder(log);

        File games = new File("D://ТЕМП//Games");
        File src = new File(games, "src");
        File res = new File(games, "res");
        File saveGames = new File(games, "savegames");
        File temp = new File(games, "temp");
        File main = new File(src, "main");
        File test = new File(src, "test");
        File mainJava = new File(main, "Main.java");
        File utilsJava = new File(main, "Utils.java");
        File drawables = new File(res, "drawables");
        File vectors = new File(res, "vectors");
        File icons = new File(res, "icons");
        File tempTxt = new File(temp, "temp.txt");

        if (src.mkdir() && res.mkdir() && saveGames.mkdir() && temp.mkdir() && main.mkdir() && test.mkdir() &&
                drawables.mkdir() && vectors.mkdir() && icons.mkdir()) {
            System.out.println("Каталоги созданы");
            sb.insert(0, "В папке Games создано несколько директорий: src, res, savegames, temp \n");
            sb.insert(0, "В каталоге src создано две директории: main, test \n");
            sb.insert(0, "В подкаталоге main создано два файла: Main.java, Utils.java \n");
            sb.insert(0, "В каталоге res создано три директории: drawables, vectors, icons \n");
            sb.insert(0, "В директории temp создан файл temp.txt \n");
            log = sb.toString();
            try (FileWriter writer = new FileWriter("D://ТЕМП//Games//temp//temp.txt", false)) {
                writer.write(log);
                writer.flush();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

        } else {
            System.out.println("Каталоги уже были созданые до этого");
        }

        try {
            if (tempTxt.createNewFile() && mainJava.createNewFile() && utilsJava.createNewFile())
                System.out.println("Файл был создан");
        } catch (IOException e) {
            System.out.println("Файлы уже созданы");
        }

        // Задание №2
        GameProgress gameProgress01 = new GameProgress(1000, 5, 100, 8);
        GameProgress gameProgress02 = new GameProgress(100, 1, 10, 2);
        GameProgress gameProgress03 = new GameProgress(600, 3, 80, 4);

        //Сохранение
        String path01 = "D://ТЕМП//Games//savegames//safe1.data";
        String path02 = "D://ТЕМП//Games//savegames//safe2.data";
        String path03 = "D://ТЕМП//Games//savegames//safe3.data";
        gameProgress01.saveGame(path01, gameProgress01);
        gameProgress01.saveGame(path02, gameProgress02);
        gameProgress01.saveGame(path03, gameProgress03);

        //Архивация и разархивация
        String zipFilePath = "D://ТЕМП//Games//savegames//data.zip";
        String FilePath = "D://ТЕМП//Games//savegames//";
        //Архивация
        List<String> filesToZip = List.of(path01, path02, path03);
        GameProgress.zipFiles(zipFilePath, filesToZip);

        //Задание №3
        //Открвыаем ZIP
        GameProgress.openZip(zipFilePath, FilePath);
        //Читаем файл
        System.out.println(GameProgress.openProgress(path01));
    }
}