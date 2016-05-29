private Process searchProcess = null;
    private final static String SEARCHFILEPATH = "SEARCHFILEPATH";
    private ArrayList<FileInfo> searchFileInofs; 
    class SearchFileThread implements Runnable {
    	private String expr;
    	private boolean caseSense, allMatch;
    	public SearchFileThread(String expr, boolean allMatch, boolean caseSense) {
			// TODO Auto-generated constructor stub
    		this.expr = expr;
    		this.allMatch = allMatch;
    		this.caseSense = caseSense;
		}
    	@Override
    	public void run(){
    		Message msg;
    		File parentDir = new File(currentPath());
        	ArrayList<FileInfo> list = currentFileInfo();
        	searchFileInofs = list;
        	list.clear();
        	currentData.selectedId.clear();
    		listViewHandler.sendEmptyMessage(HANDLER_LIST_ADPATER_CHANGED);
    		searchDir = currentPath();
    		listViewHandler.sendEmptyMessage(HANDLER_SET_SEARCHDIR);
    		searching = true;
    		currentData.searchingTag = true;
    		listViewHandler.sendEmptyMessage(HANDLER_SET_SEARCH_VISIBLE);
    		String exprs;
			if (!allMatch) {
				if (!preIsRoot) {
					if (!expr.contains("*"))
						exprs = ".*" + expr + ".*";
					else
						exprs = expr.replace("*", ".*");
				} else {
					exprs = "*" + expr + "*";
				}
			} else {
				exprs = expr;
			}
	    	if (isRoot()) {
	    		listViewHandler.sendEmptyMessage(HANDLER_SET_SEARCHDIR);
	    		try {
	    			searchProcess = linux.shell.exec("su");
					DataOutputStream out = new DataOutputStream(searchProcess.getOutputStream());
					BufferedReader in = new BufferedReader(
							new InputStreamReader(searchProcess.getInputStream()));
					String cases = "-iname";
					if (caseSense) 
						cases = "-name";
					String cmd = "find " + searchDir + "/ " + cases 
									+ " \"" + exprs + "\" 2> /dev/null\nexit\n" ;
					if (D) Log.d(tag, cmd); 
					out.write(cmd.getBytes());
					out.flush();
					
					while ((cmd = in.readLine()) != null) {
			    		if (!searching)
			    			break;
						if (preHideFile && cmd.startsWith(".")) {
							continue;
						}
						//if (D) Log.d(tag, cmd); 
						Bundle b = new Bundle();
						b.putString(SEARCHFILEPATH, cmd);
						msg = listViewHandler.obtainMessage(HANDLER_ADD_LIST_ITEM);
						msg.setData(b);
						listViewHandler.sendMessage(msg);
					}
					/**
					 * file = new File(cmd);
						name = file.getName();
						int la = name.lastIndexOf('.');
						if (la == -1)
							suffix = null;
						else
							suffix = name.substring(la + 1).toLowerCase();
						Bundle b = new Bundle();
						
		    			synchronized (newInfo) {
			    			FileInfo info = new FileInfo(
									name,
									file.getAbsolutePath(),
									switchIcon(suffix, file),
									null, // fileSize(files[i].length()),
											// //date.toLocaleString(),
									file.isDirectory());
				    		newInfo.setNewInfo(info);
				    		Binder b = new Binder();
				    		
				    		listViewHandler.sendEmptyMessage(HANDLER_ADD_LIST_ITEM);
			    			FileManager.dbg("--- " + info.name());
			    		}
					 * */
				} catch (IOException e) {
					// TODO Auto-generated catch block
					Log.e(tag, "su find", e);
				} finally {
					searchProcess.destroy();
					searchProcess = null;
				}
	    	} else {
	    		doSearchFile(list, parentDir, exprs, caseSense);
	    	}
    		listViewHandler.sendEmptyMessage(HANDLER_SEARCHBAR_HIDE);
    		searching = false;
    		searchDir = getString(R.string.done_search, expr);
    		listViewHandler.sendEmptyMessage(HANDLER_SET_SEARCHDIR);
    				
    	}
    }