package com.tomster.di.model;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author meihewang
 * @date 2019/11/03  23:19
 */
public class Programmer {

    List<String> books;

    Map<String, String> infos;

    String[] schools;

    public List<String> getBooks() {
        return books;
    }

    public void setBooks(List<String> books) {
        this.books = books;
    }

    public Map<String, String> getInfos() {
        return infos;
    }

    public void setInfos(Map<String, String> infos) {
        this.infos = infos;
    }

    public String[] getSchools() {
        return schools;
    }

    public void setSchools(String[] schools) {
        this.schools = schools;
    }

    @Override
    public String toString() {
        return "Programmer{" +
                "books=" + books +
                ", infos=" + infos +
                ", schools=" + Arrays.toString(schools) +
                '}';
    }
}
