import java.io.*;
import java.util.*;

class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    private static Map<String, TreeSet<String>> haveFolders;
    private static Map<String, TreeSet<String>> haveFiles;
    private static Map<String, String> parentFolder;	//부모 폴더의 경로를 관리
    private static int N, removeFiles, removeFolders;
    private static String currentFolder;
    public static void main(String [] args) throws IOException{
    	haveFolders = new HashMap<>();
    	haveFiles = new HashMap<>();
    	parentFolder = new HashMap<>();
    	currentFolder = "album";
    	init();
    	N = Integer.parseInt(br.readLine());
    	for(int i=0;i<N;i++) {
    		String [] commandLine = br.readLine().split(" ");
    		switch(commandLine[0]) {
    		case "mkalb":
    			createAlbum(commandLine[1]);
    			break;
    		case "rmalb":
    			removeFiles = 0;
    			removeFolders = 0;
    			handleRemoveAlbums(commandLine[1]);
    			bw.write(removeFolders+" "+ removeFiles+"\n");
    			break;
    		case "insert":
    			insertFile(commandLine[1]);
    			break;
    		case "delete":
    			removeFiles = 0;
    			removeFolders = 0;
    			handleDeleteFile(commandLine[1]);
    			bw.write(removeFiles+"\n");
    			break;
    		case "ca":
    			moveFolder(commandLine[1]);
    			bw.write(getNowFolder(currentFolder)+"\n");
    			break;
    		}
    	}
    	bw.flush();
    	bw.close();
    	br.close();
    }
    
    private static String getNowFolder(String path) {
    	String [] tokens = path.split("/");
    	return tokens[tokens.length-1];
    }
    
    private static void init() {
    	haveFolders.put(currentFolder, new TreeSet<>());
    	haveFiles.put(currentFolder, new TreeSet<>());
    	parentFolder.put("album", "album");
    }
    
    private static void handleRemoveAlbums(String option) {
    	switch(option) {
    	case "0":
    		for (String album : new ArrayList<>(haveFolders.get(currentFolder))) {
                String fullPath = currentFolder + "/" + album;
                removeFileAndFolder(fullPath);
            }
            haveFolders.get(currentFolder).clear();
    		break;
    	case "-1":	//사전순으로 가장 빠른 디렉토리 삭제
    		if(!haveFolders.get(currentFolder).isEmpty()) {
    			String targetFolder = haveFolders.get(currentFolder).first();
    			String fullPath = currentFolder +"/"+ targetFolder;
    			removeFileAndFolder(fullPath);
    			haveFolders.get(currentFolder).remove(targetFolder);
    		}
    		break;
    	case "1":
    		if(!haveFolders.get(currentFolder).isEmpty()) {
    			String targetFolder = haveFolders.get(currentFolder).last();
    			String fullPath = currentFolder + "/"+ targetFolder;
    			removeFileAndFolder(fullPath);
    			haveFolders.get(currentFolder).remove(targetFolder);
    		}
    		break;
		default:
			if(haveFolders.get(currentFolder).contains(option)) {
				String fullPath = currentFolder + "/" + option;
				removeFileAndFolder(fullPath);
				haveFolders.get(currentFolder).remove(option);
			}
			break;
    	}
	}

	private static void removeFileAndFolder(String key) {
		if(haveFolders.containsKey(key)) {
			for(String folder : new ArrayList<>(haveFolders.get(key))) {
				String fullPath = key + "/" + folder;
				removeFileAndFolder(fullPath);
			}
			
			haveFolders.remove(key);
		}
		
		if(haveFiles.containsKey(key)) {
			removeFiles+=haveFiles.get(key).size();
			haveFiles.remove(key);
		}
		
		removeFolders++;
	}

	private static void handleDeleteFile(String option) {
    	switch(option) {
    	case "-1":	//현재 앨범에 속해있는 사진이 존재한다면, 이름이 사전순으로 가장 빠른 사진을 삭제
    		if(!haveFiles.get(currentFolder).isEmpty()) {
    			String targetFile = haveFiles.get(currentFolder).first();
    			removeFiles++;
    			haveFiles.get(currentFolder).remove(targetFile);
    		}
    		break;
    	case "0":
    		int size = haveFiles.get(currentFolder).size();
    		removeFiles+=size;
    		haveFiles.get(currentFolder).clear();
    		break;
    	case "1":
    		if(!haveFiles.get(currentFolder).isEmpty()) {
    			String targetFile = haveFiles.get(currentFolder).last();
    			removeFiles++;
    			haveFiles.get(currentFolder).remove(targetFile);
    		}
    		break;
		default:	//현재 앨범에 속해있는 사진 중 S의 이름을 가진 사진이 존재한다면, 해당 사진을 삭제
    		if(haveFiles.get(currentFolder).contains(option)){
    			removeFiles++;
    			haveFiles.get(currentFolder).remove(option);
    		}
			break;
    	}
    }
    
    private static void insertFile(String fileName) throws IOException{
    	if(haveFiles.get(currentFolder).contains(fileName)) {
    		bw.write("duplicated photo name\n");
    		return;
    	}
    	
    	haveFiles.get(currentFolder).add(fileName);
    	return;
    }
    
    private static void moveFolder(String option) {
        switch (option) {
            case "..":
                if (!parentFolder.containsKey(currentFolder) || currentFolder.equals("album")) {
                    return;
                }
                currentFolder = parentFolder.get(currentFolder);
                break;
            case "/":
                currentFolder = "album";
                break;
            default:
                if (haveFolders.containsKey(currentFolder) &&
                    haveFolders.get(currentFolder).contains(option)) {
                    currentFolder = currentFolder + "/" + option;
                }
                break;
        }
    }
    
    private static void createAlbum(String folderName) throws IOException{
    	if(haveFolders.get(currentFolder).contains(folderName)) {
    		bw.write("duplicated album name\n");
    		return;
    	}
    	
    	haveFolders.get(currentFolder).add(folderName);
    	
    	String fullPath = currentFolder + "/" + folderName;
    	haveFolders.put(fullPath, new TreeSet<>());
    	haveFiles.put(fullPath, new TreeSet<>());
    	parentFolder.put(fullPath, currentFolder);
    	return;
    }
}
