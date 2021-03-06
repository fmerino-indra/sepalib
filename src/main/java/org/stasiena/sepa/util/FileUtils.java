package org.stasiena.sepa.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class FileUtils {
	public static void clearDirectory(File directory) throws IOException {
		clearDirectory(directory, true);
	}

	public static void clearDirectory(File directory, boolean preserveBase) throws IOException {
		if (directory.isDirectory()) {
			Files.walkFileTree(directory.toPath(), new DirectoryCleaner(directory.toPath(), preserveBase));
		}
	}

	public void copyDirContentsToDir(String from, String to) throws IOException {
		copyDirContentsToDir(new File(from), new File(to));
	}

	public void copyDirContentsToDir(File fromDir, File toDir) throws IOException {
		createDir(fromDir);
		createDir(toDir);
		EnumSet<FileVisitOption> options = EnumSet.of(FileVisitOption.FOLLOW_LINKS);
		Files.walkFileTree(fromDir.toPath(), options, Integer.MAX_VALUE, new DirectoryDuplicator(fromDir, toDir, this));
	}

	public void copyFile(String inputFile, String outputFile) throws IOException {
		copyFile(new File(inputFile), new File(outputFile));
	}

	public void copyFile(File inputFile, File outputFile) throws IOException {
		if (inputFile == null) {
			throw new IOException("Input file was null.");
		}
		if (!inputFile.isFile()) {
			throw new IOException("Can't copy a non-existent file: " + inputFile.getAbsolutePath());
		}
		createFile(outputFile);
		FileChannel source = null;
		FileChannel destination = null;
		try {
			source = new FileInputStream(inputFile).getChannel();
			destination = new FileOutputStream(outputFile).getChannel();
			destination.transferFrom(source, 0, source.size());
		} finally {
			if (source != null) {
				source.close();
			}
			if (destination != null) {
				destination.close();
			}
		}
	}

	public void createDir(File dir) throws IOException {
		if (dir != null) {
			if (!dir.exists()) {
				dir.mkdirs();
			} else if (!dir.isDirectory()) {
				throw new IOException("Can't convert a file to a directory: " + dir.getAbsolutePath());
			}
		} else {
			throw new IOException("Can't create a dir from null.");
		}
	}

	public void createFile(File file) throws IOException {
		if (file == null) {
			throw new IOException("Can't create a file from null.");
		}
		if (file.isDirectory()) {
			throw new IOException("Can't create a file that is a directory: " + file.getAbsolutePath());
		}
		if (!file.isFile()) {
			file.getParentFile().mkdirs();
			file.createNewFile();
		}
	}

	public List<Path> filePaths(FilePath directory, String extension, boolean isRecursive) throws IOException {
		List<Path> paths = new ArrayList<>();
		filePathsToList(directory.toFile(), paths, extension, isRecursive);
		return paths;
	}

	public void filePathsToList(FilePath directory, List<Path> filePaths, String extension, boolean isRecursive)
			throws IOException {
		filePathsToList(directory.toFile(), filePaths, extension, isRecursive);
	}

	public void filePathsToList(File directory, List<Path> filePaths) throws IOException {
		filePathsToList(directory, filePaths, null);
	}

	public void filePathsToList(File directory, final List<Path> filePaths, final String extension) throws IOException {
		filePathsToList(directory, filePaths, extension, true);
	}

	public void filePathsToList(final File directory, final List<Path> filePaths, final String extension,
			final boolean isRecursive) throws IOException {
		if (directory != null && directory.isDirectory() && filePaths != null) {
			Files.walkFileTree(directory.toPath(), new SimpleFileVisitor<Path>() {

				@Override
				public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {

					if (isRecursive) {
						return FileVisitResult.CONTINUE;
					} else {
						if (dir.toFile().equals(directory)) {
							return FileVisitResult.CONTINUE;
						}
						return FileVisitResult.SKIP_SUBTREE;
					}
				}

				@Override
				public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
					if (extension == null || file.toString().endsWith(extension)) {
						filePaths.add(file);
					}
					return FileVisitResult.CONTINUE;
				}
			});
		}
	}

	/**
	 * Platform independent method to retrieve a relative path.
	 * {@link #getForcedRelativePath(java.lang.String, java.lang.String)}
	 * 
	 * @param path
	 * @return
	 */
	public String getForcedRelativePath(String path) {
		return getForcedRelativePath(path, File.separator);
	}

	/**
	 * Forces paths to fit the following format:
	 * <ul>
	 * <li>Leading separator values are removed</li>
	 * <li>Proceeding separator values are removed</li>
	 * </ul>
	 * 
	 * @param path
	 * @param separator
	 *            The separator value to use for normalization.
	 * @throws IllegalArgumentException
	 *             if either path or separator are null.
	 * @return
	 */
	public String getForcedRelativePath(String path, String separator) {
		if (path == null) {
			throw new IllegalArgumentException("Can't force a relative path from null");
		}
		if (separator == null) {
			throw new IllegalArgumentException("seperator was null");
		}
		String pathToReturn = path;
		if (path.startsWith(separator)) {
			pathToReturn = path.substring(1);
		}
		pathToReturn = pathToReturn.replaceFirst(separator + "+$", "");
		return pathToReturn;
	}

	public String getString(File file) throws IOException {
		return new String(getChars(file));
	}

	public byte[] getBytes(File file) throws IOException {
		return Files.readAllBytes(file.toPath());
	}

	public char[] getChars(File file) throws IOException {
		String str;

		if (file == null) {
			throw new NullPointerException("src file was null");
		}
		if (!file.isFile()) {
			throw new IOException("The following src didn't exist or was not a file." + file);
		}

		FileReader reader = new FileReader(file);
		BufferedReader buffer = new BufferedReader(reader);
		String line;
		StringBuilder result = new StringBuilder();
		while ((line = buffer.readLine()) != null) {
			result.append(line).append("\n");
		}
		str = result.toString();
		buffer.close();
		reader.close();

		return str.toCharArray();
	}

	public boolean isDirectory(String path) {
		return path != null && new File(path).isDirectory();
	}

	public void putString(String absoluteFilePath, String contents) throws IOException {
		putString(new File(absoluteFilePath), contents);
	}

	public void putString(File file, String contents) throws IOException {
		createFile(file);
		try (FileWriter output = new FileWriter(file)) {
			output.write(contents, 0, contents.length());
		}
	}
}
