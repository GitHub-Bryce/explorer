SearchInputDialog.onSearchListener onSL = new SearchInputDialog.onSearchListener() {
		
		@Override
		public void onSearch(String expr, boolean allMatch, boolean caseSense) {
			// TODO Auto-generated method stub
			if (expr.contains("/")) {
				Toast.makeText(FileManager.this, getString(R.string.key_word_can_not_contain),
						Toast.LENGTH_SHORT).show();
			} else {
				new Thread(new SearchFileThread(expr, allMatch, caseSense)).start();
			}
		}
	};