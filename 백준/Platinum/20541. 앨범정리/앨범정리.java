import java.io.*;
import java.util.*;

class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static String currentFolder;
    private static int N, removeFolder, removeFile;
    private static Map<String, TreeSet<String>> haveFolders; // 부모 디렉토리 -> 자식 디렉토리
    private static Map<String, TreeSet<String>> haveFiles;   // 디렉토리 -> 파일
    private static Map<String, String> parentFolder;         // 자식 디렉토리 -> 부모 디렉토리

    public static void main(String[] args) throws IOException {
        currentFolder = "album";
        haveFolders = new HashMap<>();
        haveFiles = new HashMap<>();
        parentFolder = new HashMap<>();

        haveFolders.put("album", new TreeSet<>());
        haveFiles.put("album", new TreeSet<>());
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            String[] commandLineTokens = br.readLine().split(" ");
            switch (commandLineTokens[0]) {
                case "mkalb":
                    createAlbum(commandLineTokens[1]);
                    break;
                case "rmalb":
                    removeFile = 0;
                    removeFolder = 0;
                    handleRemoveAlbums(commandLineTokens[1]);
                    System.out.println(removeFolder + " " + removeFile);
                    break;
                case "insert":
                    insertFile(commandLineTokens[1]);
                    break;
                case "delete":
                    removeFile = 0;
                    handleDeleteFile(commandLineTokens[1]);
                    System.out.println(removeFile);
                    break;
                case "ca":
                    changeAlbum(commandLineTokens[1]);
                    System.out.println(getFolderName(currentFolder));
                    break;
            }
        }
    }

    private static String getFolderName(String currentFolder) {
        String [] tokens = currentFolder.split("/");
        return tokens[tokens.length-1];
    }

    private static void changeAlbum(String target) {
        switch (target) {
            case "..": // 상위 디렉토리로 이동
                if (!currentFolder.equals("album")) {
                    currentFolder = parentFolder.get(currentFolder);
                }
                break;
            case "/": // 최상위 디렉토리로 이동
                currentFolder = "album";
                break;
            default:
                if (haveFolders.get(currentFolder).contains(target)) {
                    String newFolder = currentFolder + "/" + target;
                    currentFolder = newFolder;
                }
                break;
        }
    }

    private static void handleDeleteFile(String option) {
        switch (option) {
            case "-1": // 사전순으로 가장 빠른 파일 삭제
                if (!haveFiles.get(currentFolder).isEmpty()) {
                    String fileName = haveFiles.get(currentFolder).first();
                    haveFiles.get(currentFolder).remove(fileName);
                    removeFile++;
                }
                break;
            case "0": // 현재 디렉토리의 모든 파일 삭제
                removeFile += haveFiles.get(currentFolder).size();
                haveFiles.get(currentFolder).clear();
                break;
            case "1": // 사전순으로 가장 마지막 파일 삭제
                if (!haveFiles.get(currentFolder).isEmpty()) {
                    String fileName = haveFiles.get(currentFolder).last();
                    haveFiles.get(currentFolder).remove(fileName);
                    removeFile++;
                }
                break;
            default: // 특정 파일 삭제
                if (haveFiles.get(currentFolder).contains(option)) {
                    haveFiles.get(currentFolder).remove(option);
                    removeFile++;
                }
                break;
        }
    }

    private static void handleRemoveAlbums(String option) {
        switch (option) {
            case "0": // 현재 디렉토리의 모든 하위 디렉토리 삭제
                for (String album : new ArrayList<>(haveFolders.get(currentFolder))) {
                    String fullPath = currentFolder + "/" + album;
                    removeFileAndFolder(fullPath);
                }
                haveFolders.get(currentFolder).clear();
                break;
            case "-1": // 사전순으로 가장 빠른 디렉토리 삭제
                if (!haveFolders.get(currentFolder).isEmpty()) {
                    String firstAlbum = haveFolders.get(currentFolder).first();
                    String fullPath = currentFolder + "/" + firstAlbum;
                    removeFileAndFolder(fullPath);
                    haveFolders.get(currentFolder).remove(firstAlbum);
                }
                break;
            case "1": // 사전순으로 가장 마지막 디렉토리 삭제
                if (!haveFolders.get(currentFolder).isEmpty()) {
                    String lastAlbum = haveFolders.get(currentFolder).last();
                    String fullPath = currentFolder + "/" + lastAlbum;
                    removeFileAndFolder(fullPath);
                    haveFolders.get(currentFolder).remove(lastAlbum);
                }
                break;
            default: // 특정 이름의 디렉토리 삭제
                if (haveFolders.get(currentFolder).contains(option)) {
                    String fullPath = currentFolder + "/" + option;
                    removeFileAndFolder(fullPath);
                    haveFolders.get(currentFolder).remove(option);
                }
                break;
        }
    }

    private static void removeFileAndFolder(String key) {
        if (haveFolders.containsKey(key)) {
            for (String album : new ArrayList<>(haveFolders.get(key))) {
                String fullPath = key + "/" + album;
                removeFileAndFolder(fullPath);
            }
            haveFolders.remove(key);
        }

        if (haveFiles.containsKey(key)) {
            removeFile += haveFiles.get(key).size();
            haveFiles.remove(key);
        }
        removeFolder++;
    }

    private static void createAlbum(String albumName) {
        if (haveFolders.get(currentFolder).contains(albumName)) {
            System.out.println("duplicated album name");
        } else {
            haveFolders.get(currentFolder).add(albumName);
            String fullPath = currentFolder + "/" + albumName;
            haveFolders.put(fullPath, new TreeSet<>());
            haveFiles.put(fullPath, new TreeSet<>());
            parentFolder.put(fullPath, currentFolder);
        }
    }

    private static void insertFile(String fileName) {
        if (haveFiles.get(currentFolder).contains(fileName)) {
            System.out.println("duplicated photo name");
        } else {
            haveFiles.get(currentFolder).add(fileName);
        }
    }
}
