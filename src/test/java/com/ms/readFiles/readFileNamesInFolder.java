//package com.ms.readFiles;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.stream.Stream;
//
//public class readFileNamesInFolder {
//
//		public static void main(String[] args) throws IOException {
//			// TODO Auto-generated method stub
//			
//			try (Stream<Path> paths = Files.walk(Paths.get("C:\\Program Files\\Azure DevOps Server 2020\\Application Tier\\TFSJobAgent"))) {
//			    paths
//			        .filter(Files::isRegularFile)
//			        .forEach(System.out::println);
//			} 
//
//		}
//
//	}
