package org.core;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class DirReader {

    public DirReader() {
    }

    ArrayList<String> filelist = new ArrayList<String>();

    //Classe filtre interne
    class InternalFilter implements FilenameFilter {
        String[] acceptedFilters;

        public InternalFilter(String[] pAcceptedFilters) {
            acceptedFilters = pAcceptedFilters;
        }

        public boolean accept(File pathname, String name) {
            File curFile = new File(pathname + "/" + name);
            for (String filterExt : acceptedFilters) {
                if (name.endsWith(filterExt) || curFile.isDirectory()) {
                    return true;
                }
            }
            return false;
        }
    }

    // Chargement des File  dans un directory
    public void listDirContent(File dir) {
        File[] files = dir.listFiles();
        for (File f : files) {
            if (f.isDirectory()) {
                System.out.println(f.toString());
                listDirContent(f);
            }
            else {
                System.out.println("\t" + f.toString());
            }
        }
    }

    // Utilisation de la classe filtre interne
    public ArrayList<String> internalFilteredDirContent(File dir) {
        String[] displayedExt = {".class"};
        InternalFilter internalFilter = new InternalFilter(displayedExt);
        File[] files = dir.listFiles(internalFilter);
        for (File f : files) {
            if (f.isDirectory()) {
                internalFilteredDirContent(f);
            }
            else {
                filelist.add(f.toString());
            }
        }
        return filelist;
    }

}
