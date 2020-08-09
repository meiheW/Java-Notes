package com.example.demo.lock;

import lombok.Data;

/**
 * @author meihewang
 * @date 2020/08/09  21:43
 */
@Data
public class PriorityScoreStudent implements Comparable {

    private String name;

    private int score;


    PriorityScoreStudent(String name, int score){
        this.name = name;
        this.score = score;
    }



    @Override
    public int compareTo(Object o) {
        return score - ((PriorityScoreStudent)o).score;
    }
}
