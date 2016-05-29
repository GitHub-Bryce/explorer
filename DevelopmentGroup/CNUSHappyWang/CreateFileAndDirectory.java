public final Process createFile(String file) throws IOException {
		String[] cmds = {"touch", file};
		return shell.exec(cmds);
	}

public final Process createDirectory(String dire) throws IOException{
		String[] cmds = {"mkdir", dire};
		return shell.exec(cmds);
	}