package com.ms.dfm.modules;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class CleanFolders {

	public static void cleanFolders(String FolderPath) throws IOException {
			
		Path directory =  Paths.get(FolderPath);
			
		Files.walkFileTree(directory, new SimpleFileVisitor<Path>() {
			
			@Override
			   public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
			       Files.delete(file);
			       return FileVisitResult.CONTINUE;
			   }
		});
	}
}
