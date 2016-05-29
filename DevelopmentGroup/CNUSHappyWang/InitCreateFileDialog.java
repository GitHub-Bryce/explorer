private void initCreateFileDialog(){
		createFileEdit = new EditText(fileManager);
		AlertDialog.Builder b = XDialog.createInputDialog(fileManager, null,
				createFileEdit);
		b.setCancelable(false).setPositiveButton(
				fileManager.ok, new AlertDialog.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				String n = createFileEdit.getText().toString();
				String cFile = fileManager.currentPath() + "/" + n;
				File mFile = new File(cFile);
				boolean success = false;
				if (mFile.exists()) {
					Toast.makeText(fileManager, n + fileManager.getString(R.string.existed),
							Toast.LENGTH_SHORT).show();
					return;
				}
				java.lang.Process p = null;
				BufferedReader br = null;
				try {
					if (fileManager.isRoot()){
						// ¥ÅEroot
						p = fileManager.linux.shell.exec("su");
						DataOutputStream shell = new DataOutputStream(p.getOutputStream());
						String cmd;
						if (createDirectory)
							cmd = "mkdir \"" + cFile + "\"\n" + "exit\n";
						else 
							cmd = "echo > \"" + cFile + "\"\n" + "exit\n";
						shell.write(cmd.getBytes());
						shell.flush();
						shell.close();
						br = new BufferedReader(
								new InputStreamReader(p.getErrorStream()));
						if (p.waitFor() != 0) {
							Toast.makeText(fileManager, br.readLine(), Toast.LENGTH_LONG).show();
							//return;
						} else
							success = true;
					}else{
						// ≤ª¥ÅEroot
						if (createDirectory){
							if (!(success = mFile.mkdirs()))
								Toast.makeText(fileManager, 
										fileManager.getString(R.string.creat_folder_failth), 
										Toast.LENGTH_SHORT).show();
						} else {
							if (!(success =mFile.createNewFile()))
								Toast.makeText(fileManager, 
										fileManager.getString(R.string.creat_file_failth), 
										Toast.LENGTH_SHORT).show();
						}
					}
					if ( success) {
						fileManager.refreshPath(fileManager.currentPath(), true);
						fileManager.fileViewList.setSelection(fileManager.listListener.position);
					}
				} catch (IOException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					if (p != null)
						p.destroy();
				}
				createFileEdit.setText("");
			}
		}).setNegativeButton(fileManager.cancel, new AlertDialog.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				createFileEdit.setText("");
			}
		});
		
		createFileDialog = b.create();
	}