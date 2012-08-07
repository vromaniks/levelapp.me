/*
 * Copyright 2012  Vasily Romanikhin  vasily.romanikhin@gmail.com
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR AS IS'' AND ANY EXPRESS OR
 * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 * NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 * THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * 3. The name of the author may not be used to endorse or promote
 *    products derived from this software without specific prior written
 *    permission.
 *
 * The advertising clause requiring mention in adverts must never be included.
 */

package ru.spb.osll.filemanager;

import java.io.File;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class FileManager extends ListActivity {
    public static String FILE_MANAGER_PATH = "file.manager.path";

    private File[] myFiles;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        final Intent intent = getIntent();
        final String path = intent.getStringExtra(FILE_MANAGER_PATH);
        final File file = (path != null) ? new File(path) : new File("/");
        setTitle(file.getAbsolutePath());
        myFiles = file.listFiles();
        
        setListAdapter(new FileAdapter());
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        final File selectedFile = myFiles[position];
        if (selectedFile.isDirectory() && selectedFile.canRead()) {
            final Intent intent = new Intent(this, FileManager.class);
            intent.putExtra(FILE_MANAGER_PATH, selectedFile.getAbsolutePath());
            startActivity(intent); 
        }
    }

    class FileAdapter extends BaseAdapter {

        public int getCount() {
            return myFiles.length;
        }

        public Object getItem(int position) {
            return myFiles[position];
        }

        public long getItemId(int position) {
            return 0;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) { 
                LayoutInflater layoutInflater = getLayoutInflater();
                convertView = layoutInflater.inflate(R.layout.list_item, parent, false); 
            }       
        
            TextView fileName = (TextView) convertView.findViewById(R.id.f_name);
            ImageView fileIcon = (ImageView) convertView.findViewById(R.id.f_icon);

            File file = myFiles[position];
            fileName.setText(file.getName());
            if (file.isDirectory())
                fileIcon.setImageResource(R.drawable.folder_yellow);
            else    
                fileIcon.setImageResource(R.drawable.file);
            return convertView;
        }
    }

}